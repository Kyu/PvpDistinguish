package me.preciouso.pvpdistinguish;

import me.preciouso.pvpdistinguish.Items.ItemManager;
import me.preciouso.pvpdistinguish.commands.SomeCommand;
import me.preciouso.pvpdistinguish.events.HoldingItemEvent;
import me.preciouso.pvpdistinguish.events.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PvpDistinguish extends JavaPlugin {
    ItemManager itemManager;

    @Override
    public void onEnable() {
        // Register items
        itemManager = new ItemManager();
        itemManager.init();

        // Register command
        getCommand("givepvp").setExecutor(new SomeCommand());

        // Register events
        getServer().getPluginManager().registerEvents(new HoldingItemEvent(this), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    }

    @Override
    public void onDisable() {

    }


}
