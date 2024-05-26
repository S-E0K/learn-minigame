package org.se0k.learnminigame.weapon;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class giveWeapon implements setWeapon{
    @Override
    public void giveSword(Player player, String difficulty) {
        Inventory inventory = player.getInventory();
        ItemStack sword;
        ItemMeta meta;
        switch (difficulty) {
            case "hard" -> {
                sword = new ItemStack(Material.IRON_SWORD);
                meta = sword.getItemMeta();
                meta.setDisplayName("하드모드");
            }
            default -> {
                sword = new ItemStack(Material.DIAMOND_SWORD);
                meta = sword.getItemMeta();
                meta.setDisplayName("노말모드");
            }
        }

        meta.setUnbreakable(true);
        meta.getEnchants().put(); // 왜 날카로움 인챈으 없음??





        inventory.addItem(sword);
    }

    @Override
    public void giveAxe(Player player, String difficulty) {

    }

    @Override
    public void giveBow(Player player, String difficulty) {

    }

    @Override
    public void giveShield(Player player, String difficulty) {

    }
}
