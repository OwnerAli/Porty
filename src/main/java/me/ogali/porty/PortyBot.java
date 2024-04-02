package me.ogali.porty;

import me.ogali.porty.commands.PostSupportMessageCommand;
import me.ogali.porty.listeners.CreateTicketButtonListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class PortyBot {

    public static DiscordApi discordApi;

    public static void main(String[] args) {
        String token = "MTIxMDM2MzA3MTIxNDY0OTM4NA.G4XtfY.X-E0njBqyM9JTXi4i2H2R2wQSL96yma_DkVcrU";

        discordApi = new DiscordApiBuilder()
                .setToken(token)
                .setAllIntents()
                .login().join();

        discordApi.addSlashCommandCreateListener(new PostSupportMessageCommand(discordApi));
        discordApi.addButtonClickListener(new CreateTicketButtonListener(discordApi));
    }

}