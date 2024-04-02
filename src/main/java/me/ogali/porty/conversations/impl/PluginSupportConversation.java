package me.ogali.porty.conversations.impl;

import me.ogali.porty.conversations.Conversation;
import me.ogali.porty.steps.impl.impl.IssueDescriptionConversationStep;
import me.ogali.porty.steps.impl.impl.PluginNameAndVersionConversationStep;
import me.ogali.porty.steps.impl.impl.ServerNameAndVersionConversationStep;
import me.ogali.porty.steps.impl.impl.TxtLogFileUploadConversationStep;
import me.ogali.porty.users.InteractionUser;

import java.util.List;

public class PluginSupportConversation extends Conversation {

    public PluginSupportConversation(InteractionUser interactionUser) {
        super(List.of(new PluginNameAndVersionConversationStep(), new ServerNameAndVersionConversationStep(),
                new IssueDescriptionConversationStep(), new TxtLogFileUploadConversationStep()), interactionUser);
    }

}
