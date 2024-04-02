package me.ogali.porty.steps;

import me.ogali.porty.interactions.PluginSupportInteraction;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.user.User;

public abstract class ConversationStep {

    private final MessageBuilder messageBuilderPrompt;

    protected ConversationStep(MessageBuilder messageBuilderPrompt) {
        this.messageBuilderPrompt = messageBuilderPrompt;
    }

    public MessageBuilder getMessageBuilderPrompt() {
        return messageBuilderPrompt;
    }

    public abstract void sendPrompt(User user);

    public abstract boolean processInput(Message message, PluginSupportInteraction pluginSupportInteraction);

}
