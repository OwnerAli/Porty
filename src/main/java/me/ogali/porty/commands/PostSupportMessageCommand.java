package me.ogali.porty.commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.ButtonBuilder;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PostSupportMessageCommand implements SlashCommandCreateListener {

    public PostSupportMessageCommand(DiscordApi api) {
        Set<SlashCommandBuilder> builders = new HashSet<>();
        builders.add(new SlashCommandBuilder().setName("ssm").setDescription("Send a support message for users to create a new support ticket."));

        api.bulkOverwriteGlobalApplicationCommands(builders).join();
    }

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event) {
        SlashCommandInteraction interaction = event.getSlashCommandInteraction();

        if (!interaction.getCommandName().equals("ssm")) return;

        EmbedBuilder embedBuilder = new EmbedBuilder();
        MessageBuilder messageBuilder = new MessageBuilder();
        Button button = new ButtonBuilder()
                .setStyle(ButtonStyle.SUCCESS)
                .setLabel("New Ticket")
                .setCustomId("create_support_ticket")
                .build();

        messageBuilder.addActionRow(button);
        embedBuilder
                .setTitle("Support")
                .setDescription("Click the button below to create a new support ticket.")
                .setColor(Color.BLACK);

        messageBuilder.setEmbed(embedBuilder);

        interaction.getChannel().ifPresent(channel -> messageBuilder.send(channel).join());
    }

}
