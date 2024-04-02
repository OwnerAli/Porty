package me.ogali.porty.users;

import me.ogali.porty.interactions.PluginSupportInteraction;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class InteractionUser {

    private final User user;
    private final Server server;
    private final PluginSupportInteraction pluginSupportInteraction;

    public InteractionUser(User user, Server server, PluginSupportInteraction pluginSupportInteraction) {
        this.user = user;
        this.server = server;
        this.pluginSupportInteraction = pluginSupportInteraction;
    }

    public User getUser() {
        return user;
    }

    public Server getServer() {
        return server;
    }

    public PluginSupportInteraction getPluginSupportInteraction() {
        return pluginSupportInteraction;
    }

}
