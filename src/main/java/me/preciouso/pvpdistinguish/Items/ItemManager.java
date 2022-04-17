package me.preciouso.pvpdistinguish.Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemManager {
    // Custom items
    private static final HashMap<ItemDirectory, ItemStack> itemList = new HashMap<ItemDirectory, ItemStack>();

    // Getter for enum
    public static ItemStack getItem(ItemDirectory identifier) {
        return itemList.get(identifier);
    }

    public void init() {
        createPvpStick();
    }

    // Create enchanted PvP stick
    private void createPvpStick() {
        ItemStack item = new ItemStack(Material.STICK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("PvP Stick");

        List<String> lore = new ArrayList<>();
        lore.add("Hold this stick to enable PvP");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        itemList.put(ItemDirectory.PVPSTICK, item);
    }
}
