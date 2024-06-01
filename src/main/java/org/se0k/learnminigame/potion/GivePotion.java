package org.se0k.learnminigame.potion;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GivePotion implements SetPotion{
    @Override
    public void healItem(Player player, String difficulty) {
        setPotion(player, difficulty, PotionEffectType.HEALTH_BOOST, "체력 재생", 3, 3, 2);
    }

    @Override
    public void buffItem(Player player, String difficulty) {
        setPotion(player, difficulty, PotionEffectType.INCREASE_DAMAGE, "데미지 증가", 20, 2, 2);
    }

    public void setPotion(Player player, String difficulty, PotionEffectType effectType, String potionName, int time, int amount, int level) {
        Inventory inventory = player.getInventory();
        PotionMeta meta;
        PotionEffect effect;
        ItemStack potionItem;
        switch (difficulty) {
            case "hard" -> {
                potionItem = new ItemStack(Material.POTION, amount);
                effect = new PotionEffect(effectType, time * 20, level);
            }
            default -> {
                potionItem = new ItemStack(Material.POTION, amount + 2);
                effect = new PotionEffect(effectType, time * 20, level + 1);
            }
        }
        meta = (PotionMeta) potionItem.getItemMeta();
        meta.setDisplayName(potionName);

        meta.addCustomEffect(effect, false);
        potionItem.setItemMeta(meta);

        inventory.addItem(potionItem);
    }

}
