package org.se0k.learnminigame.potion;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GivePotion implements SetPotion{
    @Override
    public void healItem(Player player) {
        itemManager(player, Material.IRON_INGOT, "체력 재생");
    }

    @Override
    public void buffItem(Player player) {
        itemManager(player, Material.GOLD_INGOT, "힘 증가");
    }

    void itemManager(Player player, Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        player.getInventory().addItem(itemStack);
    }


}
