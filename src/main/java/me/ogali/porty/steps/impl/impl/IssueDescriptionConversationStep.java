package me.ogali.porty.steps.impl.impl;

import me.ogali.porty.interactions.PluginSupportInteraction;
import me.ogali.porty.steps.impl.StringConversationStep;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

public class IssueDescriptionConversationStep extends StringConversationStep {

    public IssueDescriptionConversationStep() {
        super(new MessageBuilder().append("Enter a description of the issue. (Ex: The plugin drops reset when the server is restarted.)"));
    }

    @Override
    public boolean processInput(Message message, PluginSupportInteraction pluginSupportInteraction) {
        pluginSupportInteraction.setDescription(message.getContent());
        return true;
    }

}
