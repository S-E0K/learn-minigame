package org.se0k.learnminigame.potion;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GivePotion implements SetPotion{
    @Override
    public void healItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("체력 재생");
        itemStack.setItemMeta(meta);
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void buffItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("힘 증가");
        itemStack.setItemMeta(meta);
        player.getInventory().addItem(itemStack);
    }

}
