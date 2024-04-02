package me.ogali.porty.steps.impl;

import me.ogali.porty.steps.ConversationStep;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.user.User;

public abstract class StringConversationStep extends ConversationStep {

    public StringConversationStep(MessageBuilder messageBuilderPrompt) {
        super(messageBuilderPrompt);
    }

    @Override
    public void sendPrompt(User user) {
        getMessageBuilderPrompt().send(user);
    }

}
