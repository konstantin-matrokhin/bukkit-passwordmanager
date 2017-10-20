package net.kvlt.pm;

import org.bukkit.ChatColor;

public class ChatManager {

    public static final String LOGIN_SUCCESS;
    public static final String LOGIN_FAIL;
    public static final String LOGIN_REQ;

    static {
        LOGIN_SUCCESS = ChatColor.GREEN + Main.get().getConfig().getString("messages.successful-login");
        LOGIN_FAIL = ChatColor.RED + Main.get().getConfig().getString("messages.wrong-password");
        LOGIN_REQ = ChatColor.YELLOW + Main.get().getConfig().getString("messages.login-require");
    }

}
