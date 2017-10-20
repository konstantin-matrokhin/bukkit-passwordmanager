package net.kvlt.pm;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {

    private final int minLength = 2;
    private final int maxLength = 16;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
            if (!p.isOp()) return false;
        }

        if (!args[0].isEmpty()) {
            String newPwd = args[0];
            if (newPwd.length() >= minLength && newPwd.length() <= maxLength) {
                Main.get().changePassword(newPwd);
                p.sendMessage(ChatColor.GREEN + "Пароль успешно сменен!");
                return true;
            }
        }

        return false;
    }

}
