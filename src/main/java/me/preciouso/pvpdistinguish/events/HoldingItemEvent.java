package me.preciouso.pvpdistinguish.events;

import me.preciouso.pvpdistinguish.Items.ItemDirectory;
import me.preciouso.pvpdistinguish.Items.ItemManager;
import me.preciouso.pvpdistinguish.PvpDistinguish;
import me.preciouso.pvpdistinguish.PvpList;
import me.preciouso.pvpdistinguish.Utils.Countdown;
import me.preciouso.pvpdistinguish.Utils.Hider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class HoldingItemEvent implements Listener {
    PvpDistinguish plugin;
    private final ArrayList<UUID> holding = new ArrayList<>();


    public HoldingItemEvent(PvpDistinguish plugin) {
        this.plugin = plugin;
    }

    // Right click item to remove pvp mode
    @EventHandler
    public void onRightCLick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem() != null && e.getItem().equals(ItemManager.getItem(ItemDirectory.PVPSTICK))) {
                Player p = e.getPlayer();
                if (PvpList.getList().removePvp(p.getUniqueId())) {
                    p.sendMessage("Removed from PvP!");
                    new Hider(plugin).hidePlayer(p);
                }
            }
        }
    }

    // Hold item for 5 seconds to enable pvp mode
    @EventHandler
    public void onHold(PlayerItemHeldEvent e) {
        ItemStack item = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if (item != null && item.equals(ItemManager.getItem(ItemDirectory.PVPSTICK))) {
            UUID id = e.getPlayer().getUniqueId();

            // Not already counting down, and not in pvp already
            if (! (holding.contains(id)) && ! (PvpList.getList().inPvp(id))) {
                holding.add(id);

                // Create new runnable
                new Countdown(5, plugin) {
                    @Override
                    public void count(int current) {
                        ItemStack item = e.getPlayer().getInventory().getItem(e.getNewSlot());

                        if (getTime() == 0) { // End condition
                            if (holding.contains(id)) {
                                e.getPlayer().sendMessage("You're now in PvP!");
                                holding.remove(id);
                                PvpList.getList().addPvp(id);
                                new Hider(HoldingItemEvent.this.plugin).unHidePvpPlayer(e.getPlayer());
                            }
                        }

                        // Didn't hold item for entire duration
                        // TODO test swap item in inventory mode, dont change holding slots
                        if (!(item != null && e.getPlayer().getInventory().getHeldItemSlot() == e.getNewSlot() && item.equals(ItemManager.getItem(ItemDirectory.PVPSTICK)))) {
                            task.cancel();
                            holding.remove(id);
                            e.getPlayer().sendMessage("You had" + getTime() + "s left until you were in pvp :(");
                        }
                    }
                }.start();

            }
        }
    }

}
