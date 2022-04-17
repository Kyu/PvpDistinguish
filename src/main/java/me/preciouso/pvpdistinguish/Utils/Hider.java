package me.preciouso.pvpdistinguish.Utils;

import me.preciouso.pvpdistinguish.PvpDistinguish;
import me.preciouso.pvpdistinguish.PvpList;
import org.bukkit.entity.Player;

// Player hiding helper class
public class Hider {
    PvpDistinguish plugin;

    public Hider(PvpDistinguish plugin) {
        this.plugin = plugin;
    }

    public void hidePlayer(Player player) {
        for (Player p: plugin.getServer().getOnlinePlayers()) {
            if (! (p.equals(player))) {
                player.hidePlayer(plugin, p);
            }
        }
    }

    public void unHidePvpPlayer(Player player) {
        for (Player p: plugin.getServer().getOnlinePlayers()) {
            if (PvpList.getList().inPvp(p.getUniqueId())) {
                player.showPlayer(plugin, p);
            }
        }
    }

}
