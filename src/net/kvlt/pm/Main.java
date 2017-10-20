package net.kvlt.pm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JavaPlugin {

    public String password;
    public String adminPassword;

    private List<Player> loggedPlayers = new ArrayList<>();
    private List<Player> alreadyChatted = new ArrayList<>();

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        if (!new File("").exists()) {
            saveDefaultConfig();
        }

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        getCommand("pm").setExecutor(new CommandListener());
        loadPassword();

        if (Bukkit.getOnlinePlayers().size() > 0) Bukkit.broadcastMessage(ChatManager.LOGIN_REQ);

    }

    private void loadPassword() {
        if (!getConfig().contains("password") || getConfig().getString("password").isEmpty()) {
            password = String.valueOf(new Random().nextInt(1000) + 999);
            getConfig().set("password", password);
            Bukkit.getLogger().warning("Пароль не был обнаружен в конфиге. Генерация нового.");
        } else {
            password = getConfig().getString("password");
            Bukkit.getLogger().info("Пароль загружен");
        }
        if (!getConfig().contains("admin-password") || getConfig().getString("admin-password").isEmpty()) {
            adminPassword = String.valueOf(new Random().nextInt(1000) + 999);
            getConfig().set("admin-password", adminPassword);
            Bukkit.getLogger().warning("Админ-пароль не был обнаружен в конфиге. Генерация нового.");
        } else {
            adminPassword = getConfig().getString("admin-password");
            Bukkit.getLogger().info("Админ-пароль загружен");
        }
        saveConfig();
        reloadConfig();
    }

    public void changePassword(String newPwd) {
        password = newPwd;
        getConfig().set("password", password);
        saveConfig();
        reloadConfig();
    }

    public static synchronized Main get() {
        return instance;
    }

    public List<Player> getLoggedPlayers() {
        return loggedPlayers;
    }

    public List<Player> getAlreadyChatted() {
        return alreadyChatted;
    }

    @Override
    public void onDisable() {

    }

}
