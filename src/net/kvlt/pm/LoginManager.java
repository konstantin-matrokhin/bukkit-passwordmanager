package net.kvlt.pm;

import org.bukkit.entity.Player;

public class LoginManager {

    public static boolean login(Player p, String password) {
        if (password.equals( (!p.isOp() ? Main.get().password : Main.get().adminPassword)) ) {
            p.sendMessage(ChatManager.LOGIN_SUCCESS);
            return true;
        }
        p.sendMessage(ChatManager.LOGIN_FAIL);
        return false;
    }

}
