package me.ogali.porty.steps.impl.impl;

import me.ogali.porty.interactions.PluginSupportInteraction;
import me.ogali.porty.steps.impl.FileUploadConversationStep;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAttachment;
import org.javacord.api.entity.message.MessageBuilder;

public class TxtLogFileUploadConversationStep extends FileUploadConversationStep {

    public TxtLogFileUploadConversationStep() {
        super(new MessageBuilder().append("Please upload your latest log file. (Must be a .txt file)"));
    }

    @Override
    protected boolean processFile(Message message, PluginSupportInteraction pluginSupportInteraction) {
        MessageAttachment messageAttachment = message.getAttachments().get(0);

        if (!messageAttachment.getFileName().endsWith(".txt")) {
            message.getUserAuthor()
                    .ifPresent(user -> user.sendMessage("The file you uploaded is not a .txt file. Please upload a .txt file."));
            return false;
        } else {
            message.getUserAuthor()
                    .ifPresent(user -> user.sendMessage("Thank you for uploading your log file!"));
            pluginSupportInteraction.setLatestLogFile(messageAttachment);
            return true;
        }

    }

}
