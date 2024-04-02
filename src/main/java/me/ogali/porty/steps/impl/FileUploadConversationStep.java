package me.ogali.porty.steps.impl;

import me.ogali.porty.interactions.PluginSupportInteraction;
import me.ogali.porty.steps.ConversationStep;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.user.User;

public abstract class FileUploadConversationStep extends ConversationStep {

    protected FileUploadConversationStep(MessageBuilder messageBuilderPrompt) {
        super(messageBuilderPrompt);
    }

    @Override
    public void sendPrompt(User user) {
        getMessageBuilderPrompt().send(user);
    }

    @Override
    public boolean processInput(Message message, PluginSupportInteraction pluginSupportInteraction) {
        if (message.getAttachments().isEmpty()) {
            System.out.printf("No attachments found in message %s%n", message.getAttachments());
            return false;
        }
        return processFile(message, pluginSupportInteraction);
    }

    protected abstract boolean processFile(Message message, PluginSupportInteraction pluginSupportInteraction);

}
