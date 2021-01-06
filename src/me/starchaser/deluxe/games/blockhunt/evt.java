//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.blockhunt;

import java.util.Iterator;
import java.util.List;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class evt implements Listener {
  public evt() {
  }

  @EventHandler
  public void onRespawn(PlayerRespawnEvent e) {
    e.setRespawnLocation((Location)game.game_team.getTeam(TEAM_COLOR.HUNT.toString()).getTeamSpawnLocation().get(0));
    e.getPlayer().teleport((Location)game.game_team.getTeam(TEAM_COLOR.HUNT.toString()).getTeamSpawnLocation().get(0));
  }

  @EventHandler
  public void onMobSpawn(CreatureSpawnEvent evt) {
    if (game.current_game == MINIGAMES.BLOCKHUNT && (game.gameState == GameState.Live || game.gameState == GameState.Loading || game.gameState == GameState.Prepare || game.gameState == GameState.End)) {
      evt.getEntity().remove();
      evt.setCancelled(true);
    }

  }

  @EventHandler
  public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player) {
      if (game.gameState == GameState.End && game.current_game == MINIGAMES.BLOCKHUNT) {
        e.setCancelled(true);
      }

      if (game.current_game == MINIGAMES.BLOCKHUNT && game.gameState == GameState.Live) {
        Player Damager;
        try {
          Damager = (Player)e.getDamager();
        } catch (ClassCastException var8) {
          Arrow arrow = (Arrow)e.getDamager();
          Damager = (Player)arrow.getShooter();
        }

        final Player player = (Player)e.getEntity();
        if (DeluxePlayer.getDeluxePlayer(player).getPlayer_team() == DeluxePlayer.getDeluxePlayer(Damager).getPlayer_team()) {
          e.setCancelled(true);
        } else {
          if (player.getHealth() - e.getDamage() < 1.0D) {
            e.setCancelled(true);
            player.setHealth(20.0D);
            game.ClearINV(player);
            game.getPlayerinPlayerXPDB(Damager.getName()).addKey("ฆ่า");
            DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(player);
            player.teleport((Location)game.game_team.getTeam(TEAM_COLOR.HUNT.toString()).getTeamSpawnLocation().get(0));
            DeluxePlayer dmg_dp = DeluxePlayer.getDeluxePlayer(Damager);
            String hunt_str;
            if (dmg_dp.getPlayer_team() == TEAM_COLOR.HUNT) {
              hunt_str = "§f[§cHUNT§f]";
            } else {
              hunt_str = "§f[§bBLOCK§f]";
            }

            String block_str;
            if (dp.getPlayer_team() == TEAM_COLOR.HUNT) {
              block_str = "§f[§cHUNT§f]";
            } else {
              block_str = "§f[§bBLOCK§f]";
            }

            starchaser.BoardCastMsg("§7Death: " + block_str + "§e " + player.getName() + " §7ถูกฆ่าโดย " + hunt_str + "§b " + Damager.getName());
            if (dp.getPlayer_team().equals(TEAM_COLOR.BLOCK)) {
              dp.setPlayer_team(TEAM_COLOR.HUNT);
              player.playSound(player.getLocation(), Sound.ENDERDRAGON_HIT, 1.0F, 2.0F);
              player.sendMessage("§7Game: §aคุณได้เข้าร่วมทีม Hunter แล้ว");
              DisguiseAPI.undisguiseToAll(player);
              if (game.game_map.getTeams().getTeam(TEAM_COLOR.BLOCK.toString()).size() > 0) {
                starchaser.BoardCastMsg("§7Game: §aเหลือผู้ซ่อนอีก §f" + game.game_map.getTeams().getTeam(TEAM_COLOR.BLOCK.toString()).size() + " §aคน");
              }
            }

            (new BukkitRunnable() {
              public void run() {
                ItemStack hunt_sword = new ItemStack(Material.IRON_SWORD);
                ItemMeta hunt_sword_meta = hunt_sword.getItemMeta();
                hunt_sword_meta.setDisplayName("§e§lHunt §f§lSword");
                hunt_sword_meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                hunt_sword.setItemMeta(hunt_sword_meta);
                ItemStack hunt_bow = new ItemStack(Material.BOW);
                ItemMeta hunt_bow_meta = hunt_bow.getItemMeta();
                hunt_bow_meta.setDisplayName("§e§lHunt §f§lBow");
                hunt_bow_meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                hunt_bow_meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                hunt_bow.setItemMeta(hunt_bow_meta);
                player.getInventory().setItem(0, hunt_sword);
                player.getInventory().setItem(1, hunt_bow);
                player.getInventory().setItem(2, new ItemStack(Material.ARROW));
                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                player.getInventory().setHeldItemSlot(0);
              }
            }).runTaskLater(core.getDeluxe, 20L);
          } else if (e.getDamager() instanceof Player && e.getEntity() instanceof Player && DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).getPlayer_team() == TEAM_COLOR.BLOCK) {
            Player entt = (Player)e.getEntity();
            Player damager = (Player)e.getDamager();
            entt.playSound(entt.getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
            damager.playSound(damager.getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
            undis((Player)e.getEntity());
          }

        }
      }
    }
  }

  @EventHandler
  public void onPlayerClick(PlayerInteractEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT && game.gameState == GameState.Live) {
      if (DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team() == TEAM_COLOR.HUNT && e.getClickedBlock() != null) {
        Iterator var2 = e.getPlayer().getWorld().getNearbyEntities(e.getClickedBlock().getLocation(), 1.0D, 1.0D, 1.0D).iterator();

        while(var2.hasNext()) {
          Entity entity = (Entity)var2.next();
          if (entity instanceof Player) {
            Player pl = (Player)entity;
            if (pl != e.getPlayer() && DeluxePlayer.getDeluxePlayer(pl).getPlayer_team() != TEAM_COLOR.HUNT && pl.getInventory().getItem(8).getAmount() <= 1) {
              e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
              pl.playSound(pl.getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
              undis(pl);
            }
          }
        }
      }

      if (DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team() == TEAM_COLOR.BLOCK) {
        if (e.getClickedBlock() != null && e.getPlayer().getInventory().getHeldItemSlot() == 8) {
          Player pl = e.getPlayer();

          try {
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
            if (me.starchaser.deluxe.games.blockhunt.game.map_block_count.containsKey(e.getClickedBlock().getType()) && (Integer)me.starchaser.deluxe.games.blockhunt.game.map_block_count.get(e.getClickedBlock().getType()) > 200) {
              e.getPlayer().sendMessage("§7Game: §cคุณไม่สามารถแปลงกายเป็นบล๊อคนี้ได้");
              return;
            }

            e.getPlayer().getInventory().getItem(8).setType(e.getClickedBlock().getType());
            undis(pl);
            set_block_dis(pl, e.getPlayer().getInventory().getItem(8).getTypeId());
            return;
          } catch (Exception var5) {
            e.getPlayer().sendMessage("§7Game: §cคุณไม่สามารถแปลงกายเป็นบล๊อคนี้ได้");
            return;
          }
        }

        if (e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getItemMeta() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§a§lMeow!")) {
          if (!e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            e.getPlayer().sendMessage("§7Item: §cโปรดรออีก " + e.getPlayer().getItemInHand().getAmount() + " วินาทีจึงจะสามารถใช้ Meow! ได้อีกครั้ง");
          } else {
            e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.HEART, 3);
            e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.CAT_MEOW, 2.0F, 1.0F);
            e.getPlayer().getItemInHand().removeEnchantment(Enchantment.SILK_TOUCH);
            e.getPlayer().getItemInHand().setAmount(6);
            e.getPlayer().sendMessage("§7Item: §aคุณได้ใช้ Meow!");
            game.getPlayerinPlayerXPDB(e.getPlayer().getName()).addKey("Meow!");
            e.setCancelled(true);
          }
        }

        if (e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getItemMeta() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§b§lFireworks!")) {
          if (!e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            e.getPlayer().sendMessage("§7Item: §cโปรดรออีก " + e.getPlayer().getItemInHand().getAmount() + " วินาทีจึงจะสามารถใช้ Meow! ได้อีกครั้ง");
          } else {
            me.starchaser.deluxe.games.tnttag.game.spawnFireworks(e.getPlayer().getLocation(), 2, Color.RED);
            e.getPlayer().getItemInHand().removeEnchantment(Enchantment.SILK_TOUCH);
            e.getPlayer().getItemInHand().setAmount(30);
            e.getPlayer().sendMessage("§7Item: §aคุณได้ใช้ Fireworks!");
            game.getPlayerinPlayerXPDB(e.getPlayer().getName()).addKey("Fireworks!");
            e.setCancelled(true);
          }
        }
      }
    }

  }

  public static void set_block_dis(Player pl, int Block_id) {
    MiscDisguise miscDisguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, Block_id, 0);
    miscDisguise.setEntity(pl);
    miscDisguise.setViewSelfDisguise(false);
    miscDisguise.startDisguise();
  }

  public static void undis(Player pl) {
    ItemStack ima = pl.getInventory().getItem(8);
    ima.setAmount(20);
    ItemMeta itemMeta = ima.getItemMeta();
    itemMeta.setDisplayName("§aตอนนี้คุณกำลังเป็น §f" + ima.getType().toString().toLowerCase());
    if (itemMeta.hasEnchant(Enchantment.SILK_TOUCH)) {
      itemMeta.removeEnchant(Enchantment.SILK_TOUCH);
    }

    itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
    ima.setItemMeta(itemMeta);
    pl.getInventory().setItem(8, ima);
    MiscDisguise miscDisguise = new MiscDisguise(DisguiseType.FALLING_BLOCK, ima.getTypeId(), 0);
    miscDisguise.setEntity(pl);
    miscDisguise.setViewSelfDisguise(false);
    miscDisguise.startDisguise();
    Iterator var4 = pl.getActivePotionEffects().iterator();

    while(var4.hasNext()) {
      PotionEffect effect = (PotionEffect)var4.next();
      pl.removePotionEffect(effect.getType());
    }

    if (me.starchaser.deluxe.games.blockhunt.game.player_last_hide.containsKey(pl)) {
      ((Location)me.starchaser.deluxe.games.blockhunt.game.player_last_hide.get(pl)).getBlock().setType(Material.AIR);
      var4 = Bukkit.getOnlinePlayers().iterator();

      while(var4.hasNext()) {
        Player PLA = (Player)var4.next();
        PLA.sendBlockChange((Location)me.starchaser.deluxe.games.blockhunt.game.player_last_hide.get(pl), Material.AIR, (byte)0);
      }

      me.starchaser.deluxe.games.blockhunt.game.player_last_hide.remove(pl);
    }

  }

  @EventHandler
  public void onEntityDamageEvent(EntityDamageEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT) {
      if (e.getEntity() instanceof Player) {
        if (e.getCause() == DamageCause.FALL) {
          e.setCancelled(true);
        }

      }
    }
  }

  @EventHandler
  public void onClick(InventoryClickEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onDrop(PlayerDropItemEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onFoodLevelChange(FoodLevelChangeEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onDeath(PlayerDeathEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT) {
      List<ItemStack> list = e.getDrops();
      Iterator i = list.iterator();

      while(i.hasNext()) {
        ItemStack item = (ItemStack)i.next();
        i.remove();
      }

    }
  }

  @EventHandler
  public void BlockPlaceEvent(BlockPlaceEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT && (game.gameState == GameState.Live || game.gameState == GameState.Loading || game.gameState == GameState.End)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void BlockBreakEvent(BlockBreakEvent e) {
    if (game.current_game == MINIGAMES.BLOCKHUNT && (game.gameState == GameState.Live || game.gameState == GameState.Loading || game.gameState == GameState.End)) {
      e.setCancelled(true);
    }

  }
}
