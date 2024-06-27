package org.se0k.learnminigame.weapon;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.se0k.learnminigame.CommandEvent.difficulty;

public class GiveWeapon implements SetWeapon {
    @Override
    public void giveSword(Player player) {
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
        meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);


        sword.setItemMeta(meta);
        inventory.addItem(sword);
    }

    @Override
    public void giveBow(Player player) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        switch (difficulty) {
            case "normal" -> {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
                meta.setUnbreakable(true);
            }
            case "hard" -> {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
                meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
            }
        }
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bow.setItemMeta(meta);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
    }

    @Override
    public void giveShield(Player player) {
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemMeta meta = shield.getItemMeta();
        meta.setUnbreakable(true);
        shield.setItemMeta(meta);
        if (difficulty.equals("hard")) return;
        player.getInventory().addItem(shield);
    }
}
