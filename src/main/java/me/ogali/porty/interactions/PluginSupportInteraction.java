package me.ogali.porty.interactions;

import org.javacord.api.entity.message.MessageAttachment;

import java.io.File;

public class PluginSupportInteraction {

    private String pluginNameAndVersion;
    private String serverSoftwareAndVersion;
    private String description;
    private MessageAttachment latestLogFile;

    public PluginSupportInteraction(PluginSupportInteractionBuilder builder) {
        this.pluginNameAndVersion = builder.pluginNameAndVersion;
        this.serverSoftwareAndVersion = builder.serverSoftwareAndVersion;
        this.description = builder.description;
        this.latestLogFile = builder.latestLogFile;
    }

    public String getPluginNameAndVersion() {
        return pluginNameAndVersion;
    }

    public void setPluginNameAndVersion(String pluginNameAndVersion) {
        this.pluginNameAndVersion = pluginNameAndVersion;
    }

    public String getServerSoftwareAndVersion() {
        return serverSoftwareAndVersion;
    }

    public void setServerSoftwareAndVersion(String serverSoftwareAndVersion) {
        this.serverSoftwareAndVersion = serverSoftwareAndVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MessageAttachment getLatestLogFile() {
        return latestLogFile;
    }

    public void setLatestLogFile(MessageAttachment latestLogFile) {
        this.latestLogFile = latestLogFile;
    }

    public static class PluginSupportInteractionBuilder {
        private String pluginNameAndVersion;
        private String serverSoftwareAndVersion;
        private String description;
        private MessageAttachment latestLogFile;

        public PluginSupportInteractionBuilder pluginNameAndVersion(String pluginNameAndVersion) {
            this.pluginNameAndVersion = pluginNameAndVersion;
            return this;
        }

        public PluginSupportInteractionBuilder serverSoftwareAndVersion(String serverSoftwareAndVersion) {
            this.serverSoftwareAndVersion = serverSoftwareAndVersion;
            return this;
        }

        public PluginSupportInteractionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PluginSupportInteractionBuilder latestLogFile(MessageAttachment latestLogFile) {
            this.latestLogFile = latestLogFile;
            return this;
        }

        public PluginSupportInteraction build() {
            return new PluginSupportInteraction(this);
        }
    }

}