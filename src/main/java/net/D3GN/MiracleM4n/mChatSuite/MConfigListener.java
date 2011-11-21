package net.D3GN.MiracleM4n.mChatSuite;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

public class MConfigListener {
    mChatSuite plugin;
    Boolean hasChanged = false;

    public MConfigListener(mChatSuite plugin) {
        this.plugin = plugin;
    }

    void load() {
        plugin.mConfig = YamlConfiguration.loadConfiguration(plugin.mConfigF);
        plugin.mConfig.options().indent(4);
    }

    void save() {
        try {
            plugin.mConfig.save(plugin.mConfigF);
        } catch (IOException ignored) {}
    }

    void loadConfig() {
        YamlConfiguration config = plugin.mConfig;

        plugin.dateFormat = config.getString("format.date", plugin.dateFormat);
        plugin.chatFormat = config.getString("format.chat", plugin.chatFormat);
        plugin.nameFormat = config.getString("format.name", plugin.nameFormat);
        plugin.eventFormat = config.getString("format.event", plugin.eventFormat);
        plugin.listFormat = config.getString("format.list", plugin.listFormat);
        plugin.joinMessage = config.getString("message.join", plugin.joinMessage);
        plugin.leaveMessage = config.getString("message.leave", plugin.leaveMessage);
        plugin.kickMessage = config.getString("message.kick", plugin.kickMessage);
        plugin.deathInFire = config.getString("message.deathInFire", plugin.deathInFire);
        plugin.deathOnFire = config.getString("message.deathOnFire", plugin.deathOnFire);
        plugin.deathLava = config.getString("message.deathLava", plugin.deathLava);
        plugin.deathInWall = config.getString("message.deathInWall", plugin.deathInWall);
        plugin.deathDrown = config.getString("message.deathInWall", plugin.deathInWall);
        plugin.deathStarve = config.getString("message.deathStarve", plugin.deathStarve);
        plugin.deathCactus = config.getString("message.deathCactus", plugin.deathCactus);
        plugin.deathFall = config.getString("message.deathFall", plugin.deathFall);
        plugin.deathOutOfWorld = config.getString("message.deathOutOfWorld", plugin.deathOutOfWorld);
        plugin.deathGeneric = config.getString("message.deathGeneric", plugin.deathGeneric);
        plugin.deathExplosion = config.getString("message.deathExplosion", plugin.deathExplosion);
        plugin.deathMagic = config.getString("message.deathMagic", plugin.deathMagic);
        plugin.deathEntity = config.getString("message.deathEntity", plugin.deathEntity);
        plugin.deathArrow = config.getString("message.deathArrow", plugin.deathArrow);
        plugin.deathFireball = config.getString("message.deathFireball", plugin.deathFireball);
        plugin.deathThrown = config.getString("message.deathThrown", plugin.deathThrown);
        plugin.hMasterT = config.getString("message.heroesMasterT", plugin.hMasterT);
        plugin.hMasterF = config.getString("message.heroesMasterF", plugin.hMasterF);
        plugin.mAPIOnly = config.getBoolean("mchat.apiOnly", plugin.mAPIOnly);
        plugin.formatEvents = config.getBoolean("mchat.formatEvents", plugin.formatEvents);
        plugin.chatDistance = config.getDouble("mchat.chatDistance", plugin.chatDistance);
        plugin.varIndicator = config.getString("mchat.varIndicator", plugin.varIndicator);
        plugin.spoutEnabled = config.getBoolean("mchat.spout", plugin.spoutEnabled);
        plugin.useNewInfo = config.getBoolean("info.useNewInfo", plugin.useNewInfo);
        plugin.useLeveledNodes = config.getBoolean("info.useLeveledNodes", plugin.useLeveledNodes);
        plugin.useOldNodes = config.getBoolean("info.useOldNodes", plugin.useOldNodes);
        plugin.useAddDefault = config.getBoolean("info.addNewPlayers", plugin.useAddDefault);
        plugin.mIDefaultGroup = config.getString("info.defaultGroup", plugin.mIDefaultGroup);

        plugin.mChatEB = config.getBoolean("mchate.enable", plugin.mChatEB);
        plugin.healthNotify = config.getBoolean("mchate.eHealthNotify", plugin.healthNotify);
        plugin.healthAchievement = config.getBoolean("mchate.eHealthAchievement", plugin.healthAchievement);
        plugin.mAFKHQ = config.getBoolean("mchate.eHQAFK", plugin.mAFKHQ);
        plugin.listVar = config.getString("mchate.listVar", plugin.listVar);
        plugin.AFKTimer = config.getInt("mchate.AFKTimer", plugin.AFKTimer);
        plugin.AFKKickTimer = config.getInt("mchate.AFKKickTimer", plugin.AFKKickTimer);

        plugin.mChatPB = config.getBoolean("pmchat.enable", plugin.spoutPM);
        plugin.spoutPM = config.getBoolean("pmchat.spoutPM", plugin.spoutPM);

        if (plugin.AFKTimer < 10)
            plugin.AFKTimer = 10;

        if (plugin.AFKKickTimer < 20)
            plugin.AFKKickTimer = 20;
        
    }

    void defaultConfig() {
        YamlConfiguration config = plugin.mConfig;
        YamlConfigurationOptions configO = config.options();

        configO.header(
            " mChat Configuration File"
        );

        config.set("format.date", plugin.dateFormat);
        config.set("format.chat", plugin.chatFormat);
        config.set("format.name", plugin.nameFormat);
        config.set("format.event", plugin.eventFormat);
        config.set("format.list", plugin.listFormat);
        config.set("message.join", plugin.joinMessage);
        config.set("message.leave", plugin.leaveMessage);
        config.set("message.kick", plugin.kickMessage);
        config.set("message.deathInFire", plugin.deathInFire);
        config.set("message.deathOnFire", plugin.deathOnFire);
        config.set("message.deathLava", plugin.deathLava);
        config.set("message.deathInWall", plugin.deathInWall);
        config.set("message.deathInWall", plugin.deathInWall);
        config.set("message.deathStarve", plugin.deathStarve);
        config.set("message.deathCactus", plugin.deathCactus);
        config.set("message.deathFall", plugin.deathFall);
        config.set("message.deathOutOfWorld", plugin.deathOutOfWorld);
        config.set("message.deathGeneric", plugin.deathGeneric);
        config.set("message.deathExplosion", plugin.deathExplosion);
        config.set("message.deathMagic", plugin.deathMagic);
        config.set("message.deathEntity", plugin.deathEntity);
        config.set("message.deathArrow", plugin.deathArrow);
        config.set("message.deathFireball", plugin.deathFireball);
        config.set("message.deathThrown", plugin.deathThrown);
        config.set("message.heroesMasterT", plugin.hMasterT);
        config.set("message.heroesMasterF", plugin.hMasterF);
        config.set("mchat.apiOnly", plugin.mAPIOnly);
        config.set("mchat.formatEvents", plugin.formatEvents);
        config.set("mchat.chatDistance", plugin.chatDistance);
        config.set("mchat.varIndicator", plugin.varIndicator);
        config.set("mchat.spout", plugin.spoutEnabled);
        config.set("info.useNewInfo", plugin.useNewInfo);
        config.set("info.useLeveledNodes", plugin.useLeveledNodes);
        config.set("info.useOldNodes", plugin.useOldNodes);
        config.set("info.addNewPlayers", plugin.useAddDefault);
        config.set("info.defaultGroup", plugin.mIDefaultGroup);

        config.set("mchate.enable", plugin.mChatEB);
        config.set("mchat.spout", plugin.spoutEnabled);
        config.set("mchate.eHealthNotify", plugin.healthNotify);
        config.set("mchate.eHealthAchievement", plugin.healthAchievement);
        config.set("mchate.eHQAFK", plugin.mAFKHQ);
        config.set("mchate.listVar", plugin.listVar);
        config.set("mchate.AFKTimer", plugin.AFKTimer);
        config.set("mchate.AFKKickTimer", plugin.AFKKickTimer);

        config.set("pmchat.enable", plugin.spoutPM);
        config.set("pmchat.spoutPM", plugin.spoutPM);

        save();
    }

    void checkConfig() {
        YamlConfiguration config = plugin.mConfig;
        YamlConfigurationOptions configO = config.options();

        if (!(new File(plugin.getDataFolder(), "config.yml")).exists()) {
            defaultConfig();
        }

        removeOption(config, "auto-Changed");

        editOption(config, "mchat-date-format", "format.date");
        editOption(config, "mchat-message-format", "format.chat");
        editOption(config, "mchat-name-format", "format.name");
        editOption(config, "mchat-playerEvent-format", "format.event");
        editOption(config, "mchat-playerList-format", "format.list");
        editOption(config, "mchat-join-message", "message.join");
        editOption(config, "mchat-leave-message", "message.leave");
        editOption(config, "mchat-kick-message", "message.kick");
        editOption(config, "mchat-API-only", "mchat.apiOnly");
        editOption(config, "mchat-format-events", "mchat.formatEvents");
        editOption(config, "mchat-chat-distance", "mchat.chatDistance");
        editOption(config, "mchat-info-only", "info.useNewInfo");
        editOption(config, "mchat-oldNodes-only", "info.useOldNodes");
        editOption(config, "mchat-add-info-players", "info.addNewPlayers");

        checkOption(config, "format.date", plugin.dateFormat);
        checkOption(config, "format.chat", plugin.chatFormat);
        checkOption(config, "format.name", plugin.nameFormat);
        checkOption(config, "format.event", plugin.eventFormat);
        checkOption(config, "format.list", plugin.listFormat);
        checkOption(config, "message.join", plugin.joinMessage);
        checkOption(config, "message.leave", plugin.leaveMessage);
        checkOption(config, "message.kick", plugin.kickMessage);
        checkOption(config, "message.deathInFire", plugin.deathInFire);
        checkOption(config, "message.deathOnFire", plugin.deathOnFire);
        checkOption(config, "message.deathLava", plugin.deathLava);
        checkOption(config, "message.deathInWall", plugin.deathInWall);
        checkOption(config, "message.deathInWall", plugin.deathInWall);
        checkOption(config, "message.deathStarve", plugin.deathStarve);
        checkOption(config, "message.deathCactus", plugin.deathCactus);
        checkOption(config, "message.deathFall", plugin.deathFall);
        checkOption(config, "message.deathOutOfWorld", plugin.deathOutOfWorld);
        checkOption(config, "message.deathGeneric", plugin.deathGeneric);
        checkOption(config, "message.deathExplosion", plugin.deathExplosion);
        checkOption(config, "message.deathMagic", plugin.deathMagic);
        checkOption(config, "message.deathEntity", plugin.deathEntity);
        checkOption(config, "message.deathArrow", plugin.deathArrow);
        checkOption(config, "message.deathFireball", plugin.deathFireball);
        checkOption(config, "message.deathThrown", plugin.deathThrown);
        checkOption(config, "message.heroesMasterT", plugin.hMasterT);
        checkOption(config, "message.heroesMasterF", plugin.hMasterF);
        checkOption(config, "mchat.apiOnly", plugin.mAPIOnly);
        checkOption(config, "mchat.formatEvents", plugin.formatEvents);
        checkOption(config, "mchat.chatDistance", plugin.chatDistance);
        checkOption(config, "mchat.varIndicator", plugin.varIndicator);
        checkOption(config, "mchat.spout", plugin.spoutEnabled);
        checkOption(config, "info.useNewInfo", plugin.useNewInfo);
        checkOption(config, "info.useLeveledNodes", plugin.useLeveledNodes);
        checkOption(config, "info.useOldNodes", plugin.useOldNodes);
        checkOption(config, "info.addNewPlayers", plugin.useAddDefault);
        checkOption(config, "info.defaultGroup", plugin.mIDefaultGroup);

        checkOption(config, "mchate.enable", plugin.mChatEB);
        checkOption(config, "mchate.eSpout", plugin.spoutEnabled);
        checkOption(config, "mchate.eHealthNotify", plugin.healthNotify);
        checkOption(config, "mchate.eHealthAchievement", plugin.healthAchievement);
        checkOption(config, "mchate.eHQAFK", plugin.mAFKHQ);
        checkOption(config, "mchate.listVar", plugin.listVar);
        checkOption(config, "mchate.AFKTimer", plugin.AFKTimer);
        checkOption(config, "mchate.AFKKickTimer", plugin.AFKKickTimer);

        checkOption(config, "pmchat.enable", plugin.mChatPB);
        checkOption(config, "pmchat.spoutPM", plugin.spoutPM);

        if (hasChanged) {
            configO.header(
                " mChat Configuration File"
            );

            save();
        }
    }

    void checkOption(YamlConfiguration config, String option, Object defValue) {
        if (!config.isSet(option)) {
            config.set(option, defValue);
            hasChanged = true;
        }
    }

    void editOption(YamlConfiguration config, String oldOption, String newOption) {
        if (config.isSet(oldOption)) {
            config.set(newOption, config.get(oldOption));
            config.set(oldOption, null);
            hasChanged = true;
        }
    }

    void removeOption(YamlConfiguration config, String option) {
        if (config.isSet(option)) {
            config.set(option, null);
            hasChanged = true;
        }
    }
}