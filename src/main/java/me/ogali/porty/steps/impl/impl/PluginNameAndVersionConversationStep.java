package me.ogali.porty.steps.impl.impl;

import me.ogali.porty.interactions.PluginSupportInteraction;
import me.ogali.porty.steps.impl.StringConversationStep;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

public class PluginNameAndVersionConversationStep extends StringConversationStep {

    public PluginNameAndVersionConversationStep() {
        super(new MessageBuilder().append("Enter plugin name and version. (Ex: CustomDrops V 1.3.6-BETA)"));
    }

    @Override
    public boolean processInput(Message message, PluginSupportInteraction pluginSupportInteraction) {
        pluginSupportInteraction.setPluginNameAndVersion(message.getContent());
        return true;
    }

}
