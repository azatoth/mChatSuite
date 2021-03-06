package in.mDev.MiracleM4n.mChatSuite.commands;

import in.mDev.MiracleM4n.mChatSuite.mChatSuite;
import in.mDev.MiracleM4n.mChatSuite.util.Messanger;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MChatMeCommand implements CommandExecutor {
    mChatSuite plugin;

    public MChatMeCommand(mChatSuite plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (cmd.equalsIgnoreCase("mchatme")) {
            if (args.length > 0) {
                String message = "";

                for (String arg : args)
                    message += " " + arg;

                message = message.trim();

                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    World world = player.getWorld();

                    if (plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "mchat.me"))
                        plugin.getServer().broadcastMessage(plugin.getAPI().ParseMe(player.getName(), world.getName(), message));
                    else
                        Messanger.sendMessage(sender, plugin.getLocale().getOption("noPermissions") + " " + cmd + ".");

                    return true;
                } else {
                    String senderName = "Console";
                    plugin.getServer().broadcastMessage("* " + senderName + " " + message);
                    Messanger.log("* " + senderName + " " + message);
                    return true;
                }
            }
        }

        return false;
    }
}
