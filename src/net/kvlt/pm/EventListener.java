package net.kvlt.pm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (!Main.get().getLoggedPlayers().contains(p)) {
            p.sendMessage(ChatManager.LOGIN_REQ);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if (!Main.get().getLoggedPlayers().contains(p)) {
            event.setCancelled(true);
            p.sendMessage(ChatManager.LOGIN_REQ);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (!Main.get().getLoggedPlayers().contains(p)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!Main.get().getLoggedPlayers().contains(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatManager.LOGIN_REQ);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!Main.get().getLoggedPlayers().contains(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatManager.LOGIN_REQ);
        }
    }

    @EventHandler
    public void onInt(PlayerInteractEvent event) {
        if (!Main.get().getLoggedPlayers().contains(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatManager.LOGIN_REQ);
        }
    }

    @EventHandler
    public void onEntity(PlayerInteractEntityEvent event) {
        if (!Main.get().getLoggedPlayers().contains(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatManager.LOGIN_REQ);
        }
    }

    @EventHandler
    public void onInv(InventoryInteractEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player p = (Player) event.getWhoClicked();
            if (!Main.get().getLoggedPlayers().contains(p)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String msg = event.getMessage();
        if (!Main.get().getAlreadyChatted().contains(p) && !event.isCancelled()) {
            event.setCancelled(true);
            if (!LoginManager.login(p, msg)) {
                Bukkit.getScheduler().runTask(Main.get(), ()-> {
                    p.kickPlayer(ChatManager.LOGIN_FAIL);
                });
            } else {
                Bukkit.getScheduler().runTask(Main.get(), () -> {
                    Main.get().getAlreadyChatted().add(p);
                    Main.get().getLoggedPlayers().add(p);
                });
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (Main.get().getLoggedPlayers().contains(p)) {
            Main.get().getLoggedPlayers().remove(p);
        }
    }

}
