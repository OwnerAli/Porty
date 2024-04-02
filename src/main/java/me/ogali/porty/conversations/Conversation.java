package me.ogali.porty.conversations;

import me.ogali.porty.PortyBot;
import me.ogali.porty.interactions.PluginSupportInteraction;
import me.ogali.porty.steps.ConversationStep;
import me.ogali.porty.users.InteractionUser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.javacord.api.entity.message.MessageAttachment;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.JSONObject;

import java.awt.*;
import java.util.List;

public abstract class Conversation implements MessageCreateListener {

    private final List<ConversationStep> conversationStepList;
    private final InteractionUser interactionUser;

    private int stepProgress = 0;

    protected Conversation(List<ConversationStep> conversationStepList, InteractionUser interactionUser) {
        this.interactionUser = interactionUser;
        this.conversationStepList = conversationStepList;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getUserAuthor().isEmpty()) return;
        if (!event.getMessage().getUserAuthor().get().equals(interactionUser.getUser())) return;
        if (getCurrentStep() == null) return;
        if (!getCurrentStep().processInput(event.getMessage(), interactionUser.getPluginSupportInteraction())) return;

        stepProgress++;

        if (stepProgress >= conversationStepList.size()) {
            end();
            return;
        }
        event.getMessage().getUserAuthor()
                .ifPresent(user -> getCurrentStep().sendPrompt(user));
    }

    private void end() {
        interactionUser.getUser().sendMessage("Woohoo! You're done, I've created a support thread for you in the discord, go check it out! :)");
//        interactionUser.getServer().getChannelById(1210421180759154728L)
//                .ifPresent(serverTextChannel ->
//                        interactionUser.getServer()
//                                        .getForumChannelById(1210421180759154728L)
//                                                .ifPresent(serverForumChannel -> {
//
//                                                })

        String url = "https://discord.com/api/v10/channels/1210421180759154728/threads";
        String authorization = "Bot MTIxMDM2MzA3MTIxNDY0OTM4NA.G4XtfY.X-E0njBqyM9JTXi4i2H2R2wQSL96yma_DkVcrU";

        // Get the server name and version
        PluginSupportInteraction pluginInteraction = interactionUser.getPluginSupportInteraction();

        try {
            // Set up the connection
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            // Set the request headers
            httpPost.setHeader("Authorization", authorization);
            httpPost.setHeader("Content-Type", "application/json");

            // Get the server software and version
            String serverNameAndVersion = pluginInteraction.getServerSoftwareAndVersion();

            // Get the plugin name and version
            String pluginNameAndVersion = pluginInteraction.getPluginNameAndVersion();

            // Get the description
            String description = pluginInteraction.getDescription();

            // Get the latest log file path
            MessageAttachment messageAttachment = pluginInteraction.getLatestLogFile();

            // ... other code ...

            JSONObject message = new JSONObject();
            message.put("content", "I'm here to help! :)");

            JSONObject payload = new JSONObject();
            payload.put("name", serverNameAndVersion + " | " + pluginNameAndVersion);
            payload.put("message", message);

            StringEntity stringEntity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);

            // Execute the request
            HttpResponse response = httpClient.execute(httpPost);

            // Process the response if needed
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity);
            System.out.println(responseContent);

            JSONObject json = new JSONObject(responseContent);

            // Extract the value of the "id" field
            String idValue = json.getString("id");

            interactionUser.getServer()
                    .getThreadChannelById(idValue)
                    .ifPresent(threadChannel -> {
                        EmbedBuilder embed = new EmbedBuilder()
                                .setTitle("**Ticket Information**")
                                .addField("Description: ", description)
                                .addField("Created for: ", interactionUser.getUser().getMentionTag())
                                .setColor(Color.BLACK);
                        new MessageBuilder()
                                .append("Embeds don't ping people, so I'm pinging you here!" + interactionUser.getUser().getMentionTag())
                                .addAttachment(messageAttachment.getUrl())
                                .setEmbed(embed)
                                .send(threadChannel);
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

        PortyBot.discordApi.removeListener(this);

    }

    public void start() {
        if (getCurrentStep() == null) return;
        getCurrentStep().sendPrompt(interactionUser.getUser());
    }

    private ConversationStep getCurrentStep() {
        if (stepProgress >= conversationStepList.size()) {
            end();
            return null;
        }
        return conversationStepList.get(stepProgress);
    }

}
