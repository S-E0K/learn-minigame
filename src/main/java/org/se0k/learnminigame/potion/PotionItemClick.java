package org.se0k.learnminigame.potion;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static org.se0k.learnminigame.CommandEvent.difficulty;
import static org.se0k.learnminigame.Learn_miniGame.plugin;


public class PotionItemClick implements Listener {
    int count_normal = 0;
    int count_hard = 0;
    @EventHandler
    public void potionItemClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        PotionEffect effect;

        if (action != Action.RIGHT_CLICK_AIR) return;
        if (player.getItemInHand().getType() != Material.GOLD_INGOT && player.getItemInHand().getType() != Material.IRON_INGOT) return;

        switch (difficulty) {
            case "normal" -> {
                if (count_normal == 0) {

                    if (player.getItemInHand().getType() == Material.IRON_INGOT) {
                        effect = new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 3);
                        player.addPotionEffect(effect);
                    }

                    if (player.getItemInHand().getType() == Material.GOLD_INGOT) {
                        effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10 * 20, 2);
                        player.addPotionEffect(effect);
                    }

                    count_normal = 10;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (count_normal > 0) {
                                count_normal--;
                            }
                            if (count_normal == 0) {
                                cancel();
                            }
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                } else {
                    event.setCancelled(true);
                    player.sendMessage(count_normal + "초 남았습니다");
                }
            }

            case "hard" -> {
                if (count_hard == 0) {

                    if (player.getItemInHand().getType() == Material.IRON_INGOT) {
                        effect = new PotionEffect(PotionEffectType.REGENERATION, 5 * 15, 2);
                        player.addPotionEffect(effect);
                    }

                    if (player.getItemInHand().getType() == Material.GOLD_INGOT) {
                        effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10 * 15, 1);
                        player.addPotionEffect(effect);
                    }

                    count_hard = 15;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (count_hard > 0) {
                                count_hard--;
                            }
                            if (count_hard == 0) {
                                cancel();
                            }
                        }
                    }.runTaskTimer(plugin, 0L, 20L);
                } else {
                    event.setCancelled(true);
                    player.sendMessage(count_hard + "초 남았습니다");
                }
            }
        }
    }
}