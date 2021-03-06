package in.mDev.MiracleM4n.mChatSuite.events;

import in.mDev.MiracleM4n.mChatSuite.mChatSuite;
import in.mDev.MiracleM4n.mChatSuite.util.Messanger;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CustomListener implements Listener {
    mChatSuite plugin;

    public CustomListener(mChatSuite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onKeyPressedEvent(KeyPressedEvent event) {
        SpoutPlayer player = event.getPlayer();
        Keyboard key = event.getKey();
        Keyboard chatKey = player.getChatKey();
        Keyboard forwardKey = player.getForwardKey();
        Keyboard backwardKey = player.getBackwardKey();
        Keyboard leftKey = player.getLeftKey();
        Keyboard rightKey = player.getRightKey();

        if (plugin.chatt.get(player.getName()) == null)
            plugin.chatt.put(player.getName(), false);

        if (key == null) return;

        if (key.equals(chatKey)) {
            player.setTitle(ChatColor.valueOf(plugin.getLocale().getOption("spoutChatColour").toUpperCase()) + Messanger.addColour(plugin.getLocale().getOption("typingMessage")) + '\n' + plugin.getAPI().ParsePlayerName(player.getName(), player.getWorld().getName()));
            plugin.chatt.put(player.getName(), true);
        }

        if (plugin.chatt.get(player.getName())) {
            if ((key.equals(forwardKey)) ||
                    (key.equals(backwardKey)) ||
                    (key.equals(leftKey)) ||
                    (key.equals(rightKey))) {
                player.setTitle(plugin.getAPI().ParsePlayerName(player.getName(), player.getWorld().getName()));
                plugin.chatt.put(player.getName(), false);
            }
        }
    }
}

