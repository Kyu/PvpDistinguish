package me.preciouso.pvpdistinguish.commands;

import me.preciouso.pvpdistinguish.Items.ItemDirectory;
import me.preciouso.pvpdistinguish.Items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Gives player a pvp stick, so they can enter/leave pvp mode
public class SomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player)) {
            return true;
        }

        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("givepvp")) {
            p.getInventory().addItem(ItemManager.getItem(ItemDirectory.PVPSTICK));
        }

        return false;
    }
}
