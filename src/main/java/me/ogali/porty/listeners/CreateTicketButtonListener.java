package me.ogali.porty.listeners;

import me.ogali.porty.conversations.impl.PluginSupportConversation;
import me.ogali.porty.interactions.PluginSupportInteraction;
import me.ogali.porty.users.InteractionUser;
import org.javacord.api.DiscordApi;
import org.javacord.api.event.interaction.ButtonClickEvent;
import org.javacord.api.interaction.ButtonInteraction;
import org.javacord.api.listener.interaction.ButtonClickListener;

public class CreateTicketButtonListener implements ButtonClickListener {

    private final DiscordApi api;

    public CreateTicketButtonListener(DiscordApi api) {
        this.api = api;
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        ButtonInteraction interaction = event.getButtonInteraction();

        if (!(interaction.getCustomId().equals("create_support_ticket"))) return;
        interaction.getServer().ifPresentOrElse(server -> {
            interaction.createImmediateResponder().setContent("HEY! Check your dms :)").respond();

            PluginSupportConversation supportConversation = new PluginSupportConversation(new InteractionUser(interaction.getUser(), server,
                    new PluginSupportInteraction.PluginSupportInteractionBuilder().build()));

            api.addMessageCreateListener(supportConversation);
            supportConversation.start();

        }, () -> interaction.createImmediateResponder().setContent("You must be in a server to create a ticket.").respond());
    }
}