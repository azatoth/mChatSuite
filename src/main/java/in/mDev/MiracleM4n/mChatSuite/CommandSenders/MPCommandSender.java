package in.mDev.MiracleM4n.mChatSuite.commandSenders;

import in.mDev.MiracleM4n.mChatSuite.mChatSuite;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.getspout.spoutapi.player.SpoutPlayer;

public class MPCommandSender implements CommandExecutor {
        mChatSuite plugin;

        public MPCommandSender(mChatSuite plugin) {
            this.plugin = plugin;
        }

    String message = "";

    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
        String commandName = command.getName();

        if (!plugin.mChatPB)
            return true;

        if (!(sender instanceof Player)) {
             sender.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("Console's can't send PM's.")));
             return true;
        }

        Player player = (Player)sender;
        String pName = player.getName();

        if (commandName.equalsIgnoreCase("pmchat")) {
            if (!mChatSuite.getAPI().checkPermissions(player, "mchat.pm.pm")) {
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You are not allowed to use PM functions.")));
                return true;
            }

            if (args.length < 2)
                return false;

            message = "";
            for (int i = 1; i < args.length; ++i)
                message += " " + args[i];

            if (plugin.getServer().getPlayer(args[0]) == null) {
                player.sendMessage(formatPNF(args[0]));
                return true;
            }

            Player recipient = plugin.getServer().getPlayer(args[0]);
            String rName = recipient.getName();
            String senderName = mChatSuite.getAPI().ParsePlayerName(pName);

            player.sendMessage(formatPMSend(rName, message));

            if (plugin.spoutB) {
                if (plugin.spoutPM) {
                    final SpoutPlayer sRecipient = (SpoutPlayer) recipient;

                    if (sRecipient.isSpoutCraftEnabled()) {
                        Runnable runnable = new Runnable() {
                            public void run() {
                                for (int i = 0; i < ((message.length() / 40) + 1); i++) {
                                    sRecipient.sendNotification(formatPM(message, ((40*i)+1), ((i*40)+20)), formatPM(message, ((i*40)+21), ((i*40)+40)), Material.PAPER);
                                    waiting(2);
                                }
                            }
                        };

                        if (plugin.lastPMd != null)
                            plugin.lastPMd.remove(rName);

                        plugin.lastPMd.put(rName, pName);
                        sRecipient.sendNotification("[pmChat] From:", player.getName(), Material.PAPER);
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, runnable, 2*20);
                        return true;
                    }
                }
            }

            plugin.lastPMd.put(rName, pName);
            recipient.sendMessage(formatPMRecieve(senderName, message));
            return true;
        } else if (commandName.equalsIgnoreCase("pmchatleave")) {
            String rName = plugin.chatPartner.get(pName);
            Player recipient = plugin.getServer().getPlayer(rName);

            if (plugin.isConv.get(pName) == null)
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You are not currently in a Convo.")));
            else if (plugin.isConv.get(pName)) {
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You have left the convo.")));
                recipient.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("Conversation has been ended.")));
                plugin.isConv.put(pName, false);
                plugin.isConv.put(rName, false);
                plugin.chatPartner.remove(rName);
                plugin.chatPartner.remove(pName);
            } else
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You are not currently in a Convo.")));

            return true;
        } else if (commandName.equalsIgnoreCase("pmchataccept")) {
            String rName = plugin.getInvite.get(pName);
            Player recipient = plugin.getServer().getPlayer(rName);

            if (rName != null) {
                plugin.getInvite.remove(pName);
                plugin.isConv.put(pName, true);
                plugin.isConv.put(rName, true);
                plugin.chatPartner.put(rName, pName);
                plugin.chatPartner.put(pName, rName);
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You have started a Convo with &5'&4" + mChatSuite.getAPI().ParsePlayerName(rName) + "&5'&4.")));
                recipient.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("Convo request with &5'&4" + mChatSuite.getAPI().ParsePlayerName(pName) + "&5'&4 has been accepted.")));
            } else
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("No pending Convo request.")));

            return true;
        } else if (commandName.equalsIgnoreCase("pmchatdeny")) {
            String rName = plugin.getInvite.get(pName);
            Player recipient = plugin.getServer().getPlayer(rName);

            if (rName != null) {
                plugin.getInvite.remove(pName);
                plugin.isConv.put(pName, false);
                plugin.isConv.put(rName, false);
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You have denied a Convo request from &5'&4" + mChatSuite.getAPI().ParsePlayerName(rName) + "&5'&4.")));
                recipient.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("Convo request with &5'&4" + mChatSuite.getAPI().ParsePlayerName(pName) + "&5'&4 has been denied.")));
            } else
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("No pending Convo request.")));

            return true;
        } else if (commandName.equalsIgnoreCase("pmchatinvite")) {
            if (!mChatSuite.getAPI().checkPermissions(player, "mchat.pm.invite")) {
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You are not allowed to use Invite functions.")));
                return true;
            }

            Player recipient = plugin.getServer().getPlayer(args[0]);
            String rName = recipient.getName();

            if (recipient == null) {
                player.sendMessage(formatPNF(args[0]));
                return true;
            }

            if (plugin.getInvite.get(rName) != null) {
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("&5'&4" + mChatSuite.getAPI().ParsePlayerName(rName) + "&5'&4 Already has a Convo request.")));
                return true;
            } else {
                plugin.getInvite.put(rName, pName);
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You have invited &5'&4" + mChatSuite.getAPI().ParsePlayerName(rName) + "&5'&4 to have a Convo.")));
                recipient.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You have been invited to a Convo by &5'&4" + mChatSuite.getAPI().ParsePlayerName(pName) + "&5'&4 use /pmchat accept to accept.")));
                return true;
            }
        } else if (commandName.equalsIgnoreCase("pmchatreply")) {
            message = "";
            for (String arg : args)
                message += " " + arg;

            if (plugin.lastPMd.get(pName) != null) {
                String rName = plugin.lastPMd.get(pName);
                Player recipient = plugin.getServer().getPlayer(rName);

                if (!mChatSuite.getAPI().checkPermissions(player, "mchat.pm.reply")) {
                    player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("You are not allowed to use PM reply functions.")));
                    return true;
                }

                final String senderName = mChatSuite.getAPI().ParsePlayerName(pName);
                player.sendMessage(formatPMSend(rName, message));

                if (plugin.lastPMd != null)
                    plugin.lastPMd.remove(pName);

                plugin.lastPMd.put(rName, pName);
                if (plugin.spoutB) {
                    if (plugin.spoutPM) {
                        final SpoutPlayer sRecipient = (SpoutPlayer) recipient;
                        if (sRecipient.isSpoutCraftEnabled()) {
                            Runnable runnable = new Runnable() {
                                public void run() {
                                    for (int i = 0; i < ((message.length() / 40) + 1); i++) {
                                        sRecipient.sendNotification(formatPM(message, ((40*i)+1), ((i*40)+20)), formatPM(message, ((i*40)+21), ((i*40)+40)), Material.PAPER);
                                        waiting(2000);
                                    }
                                }
                            };

                            sRecipient.sendNotification("[pmChat] From:", player.getName(), Material.PAPER);
                            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, runnable, 2*20);
                            return true;
                        }
                    }
                }

                recipient.sendMessage(formatPMRecieve(senderName, message));
                return true;
            } else {
                player.sendMessage(formatPMessage(mChatSuite.getAPI().addColour("No one has yet PM'd you.")));
                return true;
            }
        }

        return false;
    }

    private static void waiting(int n){
        long t0, t1;
        t0 =  System.currentTimeMillis();
        do{
            t1 = System.currentTimeMillis();
        }
        while ((t1 - t0) < n * 1000);
    }

    private String formatPM(String message, Integer start, Integer finish) {
        while (message.length() < finish) message += " ";
        return message.substring(start, finish);
    }

    private String formatPMessage(String message) {
        return(mChatSuite.getAPI().addColour("&4[" + (plugin.pdfFile.getName()) + "] " + message));
    }

    private String formatPNF(String playerNotFound) {
        return(mChatSuite.getAPI().addColour("&4[" + (plugin.pdfFile.getName()) + "]" + " Player &e" + playerNotFound + " &4not found."));
    }

    private String formatPMSend(String recipient, String message) {
        return(mChatSuite.getAPI().addColour("&4[" + (plugin.pdfFile.getName()) + "] &fMe &1-&2-&3-&4> &f" + recipient + "&f: " + message));
    }

    private String formatPMRecieve(String sender, String message) {
        return(mChatSuite.getAPI().addColour("&4[" + (plugin.pdfFile.getName()) + "] &f" + sender + "&f: " + message));
    }
}