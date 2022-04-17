package me.preciouso.pvpdistinguish.events;

import me.preciouso.pvpdistinguish.PvpDistinguish;
import me.preciouso.pvpdistinguish.Utils.Hider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

// Instantly vanish player upon join
public class JoinEvent implements Listener {
    PvpDistinguish plugin;

    public JoinEvent(PvpDistinguish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        new Hider(plugin).hidePlayer(player);
    }

}
