package me.ogali.porty.steps.impl.impl;

import me.ogali.porty.interactions.PluginSupportInteraction;
import me.ogali.porty.steps.impl.StringConversationStep;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

public class ServerNameAndVersionConversationStep extends StringConversationStep {

    public ServerNameAndVersionConversationStep() {
        super(new MessageBuilder().append("Enter server software and version. (Ex: Paper 1.20.4)"));
    }

    @Override
    public boolean processInput(Message message, PluginSupportInteraction pluginSupportInteraction) {
        pluginSupportInteraction.setServerSoftwareAndVersion(message.getContent());
        return true;
    }

}
