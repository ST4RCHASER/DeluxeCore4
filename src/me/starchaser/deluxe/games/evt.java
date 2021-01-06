//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import me.libraryaddict.disguise.DisguiseAPI;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import me.starchaser.deluxe.starchaser.ServerGamemode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class evt implements Listener {
  public static int auto_start_click_cooldown = 0;
  public static String chat_allow_for = "#NONE#";
  public static int host_afk_timer = 0;

  public evt() {
  }

  @EventHandler
  public void onPlayerClick(PlayerInteractEvent e) {
    if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES && e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getItemMeta() != null && e.getPlayer().getItemInHand().getType() != null && (game.gameState == GameState.Recruit || game.gameState == GameState.Prepare) && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
      if (e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getItemMeta() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bเลือกทีม §7§o(คลิกขวา)") || e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§bตอนนี้คุณอยู่ทีม ")) {
          game.openTeamSelecterInv(e.getPlayer());
          e.setCancelled(true);
        }

        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bกลับไปที่ล๊อบบี้ §7§o(คลิกขวา)")) {
          starchaser.sendToServer(e.getPlayer().getName(), "Lobby");
        }
      }

      if (e.getPlayer().getItemInHand().getType().equals(Material.PAPER) && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bตั้งค่าห้อง §7§o(คลิกขวา)")) {
        if (DeluxePlayer.getDeluxePlayer(e.getPlayer()) == game.host) {
          game.openRoomSettings(e.getPlayer());
        } else {
          e.getPlayer().getItemInHand().setType(Material.AIR);
        }
      }

      if (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND) && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bเริ่มเกมทันที §7§o(คลิกขวา)")) {
        if (DeluxePlayer.getDeluxePlayer(e.getPlayer()) == game.host) {
          game.force_start = true;
          e.getPlayer().getInventory().setItem(3, new ItemStack(Material.AIR));
          starchaser.BoardCastMsg("§7Game: §b" + e.getPlayer().getName() + " §aได้บังคับให้เกมเริ่มเดียวนี้");
          if (game.gameState == GameState.Prepare) {
            game.time_prepare = 15;
          } else {
            game.time_prepare = 15;
            game.getPrepareCountdown().runTaskTimer(core.getDeluxe, 20L, 20L);
          }
        } else {
          e.getPlayer().getItemInHand().setType(Material.AIR);
        }
      }
    }

  }

  @EventHandler
  public void onPlayerDropItem(PlayerDropItemEvent e) {
    if (game.gameState.equals(GameState.Recruit) || game.gameState.equals(GameState.Prepare)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onItemSpawn(ItemSpawnEvent e) {
    if (game.gameState.equals(GameState.Recruit) || game.gameState.equals(GameState.Prepare)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent e) {
    if (!e.getMessage().startsWith("!")) {
      if (game.password_renamer == e.getPlayer().getName()) {
        if (e.getMessage().equalsIgnoreCase("cancel")) {
          e.getPlayer().sendMessage("§7Room: §aยกเลิกการเปลี่ยนรหัสผ่านห้องเรียบร้อยแล้ว");
          game.password_renamer = "#NULL#";
          return;
        }

        if (e.getMessage().equalsIgnoreCase("#NONE#")) {
          game.Server_password = e.getMessage();
          game.password_renamer = "#NULL#";
          starchaser.BoardCastMsg("§7Room: §aผู้เล่น §b" + e.getPlayer().getName() + " §aได้ทำการนำรหัสผ่านของห้องออกเรียบร้อยแล้ว");
          e.getPlayer().sendMessage("§7Room: §aนำรหัสของห้องออกเรียบร้อยแล้ว!");
        } else {
          game.Server_password = e.getMessage();
          game.password_renamer = "#NULL#";
          starchaser.BoardCastMsg("§7Room: §aผู้เล่น §b" + e.getPlayer().getName() + " §aได้ทำการอัพเดทรหัสผ่านห้องเรียบร้อยแล้ว");
          e.getPlayer().sendMessage("§7Room: §aรหัสถูกอัพเดทเป็น §b" + game.Server_password + "§a เรียบร้อยแล้ว");
          chat_allow_for = "#NONE#";
        }
      }

      if (game.game_renamer == e.getPlayer().getName()) {
        if (e.getMessage().equalsIgnoreCase("cancel")) {
          e.getPlayer().sendMessage("§7Room: §aยกเลิกการเปลี่ยนชื่อห้องเรียบร้อยแล้ว");
          game.game_renamer = "#NULL#";
          return;
        }

        if (e.getMessage().length() > 30) {
          game.game_renamer = "#NULL#";
          e.getPlayer().sendMessage("§7Room: §cการเปลี่ยนชื่อห้องถูกยกเลิกเนื่องจากตัวอักษรที่กำลังจะตั้งนั้นมีมากกว่า 30 ตัวอักศร");
          return;
        }

        game.game_renamer = "#NULL#";
        game.Server_Name = e.getMessage();
        starchaser.BoardCastMsg("§7Room: §a" + e.getPlayer().getName() + " ได้ทำการเปลี่ยนชื่อห้องเป็น §b" + game.Server_Name + " §aเรียบร้อยแล้ว");
      }

    }
  }

  @EventHandler
  public void roomlocker(PlayerLoginEvent e) {
    if (game.gameState != GameState.Prepare && game.gameState != GameState.Recruit && game.gameState != GameState.Standby) {
      e.disallow(Result.KICK_OTHER, "§cเกมกำลังดำเนินอยู่ โปรดรอจบก่อนจึงจะเข้าได้...");
    }

    if (game.time_prepare <= 5) {
      e.disallow(Result.KICK_OTHER, "§cเกมใกล้จะเริ่มแล้ว ไม่สามารถเข้าไปได้ รอจนจบก่อนนะ...");
    }

  }

  @EventHandler
  public void onPlayerClickInv(InventoryClickEvent e) {
    if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES && game.gameState == GameState.Recruit || game.gameState == GameState.Prepare) {
      e.setCancelled(true);
      if (e.getClickedInventory() != null && e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
        Player target;
        if (e.getClickedInventory().getName().equals("§bเชิญผู้เล่นออก")) {
          e.getWhoClicked().closeInventory();
          if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
            e.getWhoClicked().sendMessage("§7Room: §cคุณไม่สามารถเชิญตนเองออกจากห้องได้");
            return;
          }

          target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
          if (target != null) {
            starchaser.sendToServer(e.getCurrentItem().getItemMeta().getDisplayName(), "Lobby");
            starchaser.BoardCastMsg("§7Room: §4" + e.getCurrentItem().getItemMeta().getDisplayName() + " §7ถูกเชิญออกจากห้อง โดย §e" + e.getWhoClicked().getName());
          } else {
            e.getWhoClicked().sendMessage("§7Room: §cไม่พบผู้เล่น §b" + e.getCurrentItem().getItemMeta().getDisplayName() + " §cอยู่ในห้องนี้!");
          }

          return;
        }

        if (e.getClickedInventory().getName().equals("§bเลือกเกม")) {
          if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
            e.getWhoClicked().sendMessage("§7Game: §cคุณได้เลือกเกมนี้ไปอยู่แล้ว");
            return;
          }

          if (game.gameState.equals(GameState.Prepare) && game.time_prepare < 15) {
            starchaser.BoardCastMsg("§7Game: §eการเปลี่ยนเกมในระหว่างใกล้จะเริ่มต้นจะทำให้เวลาที่จะเริ่มต้นกลับมาเป็น 15 วินาที");
            game.time_prepare = 16;
          }

          starchaser.BoardCastMsg("§7Room: §aเกมถูกเปลี่ยนเป็น §b" + e.getCurrentItem().getItemMeta().getDisplayName().replaceFirst("§f§l", "") + "§a เรียบร้อยแล้ว");
          game.current_game = starchaser.StringToGameName(e.getCurrentItem().getItemMeta().getDisplayName().replaceFirst("§f§l", ""));
          game.random_map = true;
          game.reload_game();
          game.openGameInv((Player)e.getWhoClicked());
          return;
        }

        if (e.getClickedInventory().getName().equals("§bโอนสิทธ์หัวหน้าห้อง")) {
          e.getWhoClicked().closeInventory();
          if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
            e.getWhoClicked().sendMessage("§7Room: §cคุณเป็นหัวห้องอยู่แล้ว ไม่สามารถโอนให้ได้อีก!");
            return;
          }

          target = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
          if (target != null) {
            game.give_host(DeluxePlayer.getDeluxePlayer(target));
          } else {
            e.getWhoClicked().sendMessage("§7Room: §cไม่พบผู้เล่น §b" + e.getCurrentItem().getItemMeta().getDisplayName() + " §cอยู่ในห้องนี้!");
          }

          return;
        }

        if (e.getClickedInventory().getName().equals("§bตั้งค่าห้อง")) {
          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aเปิด §aการแชทในระหว่างการเล่นเกม")) {
            game.chat_allow_on_game_live = true;
            starchaser.BoardCastMsg("§7Game: §e" + e.getWhoClicked().getName() + " §aได้เปิดให้ใช้แชทในระหว่างเล่นแล้ว");
            game.openRoomSettings((Player)e.getWhoClicked());
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cปิด §aการแชทในระหว่างการเล่นเกม")) {
            game.chat_allow_on_game_live = false;
            starchaser.BoardCastMsg("§7Game: §e" + e.getWhoClicked().getName() + " §aได้§cปิด§aให้ใช้แชทในระหว่างเล่นแล้ว");
            game.openRoomSettings((Player)e.getWhoClicked());
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bเปลี่ยนชื่อห้อง")) {
            e.getWhoClicked().closeInventory();
            game.game_renamer = e.getWhoClicked().getName();
            game.password_renamer = "#NULL#";
            e.getWhoClicked().sendMessage("§7Rename: §aโปรดใส่ชื่อห้องใหม่ลงในช่องแชทเพื่อทำการเปลี่ยนชื่อห้อง หากต้องการยกเลิกให้พิมพ์ \"cancel\"");
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bเลือกเกม")) {
            game.openGameInv((Player)e.getWhoClicked());
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bเชิญผู้เล่นออก")) {
            game.openKickPlayerInv((Player)e.getWhoClicked());
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bโอนสิทธ์หัวหน้าห้อง")) {
            game.openHostChangeInv((Player)e.getWhoClicked());
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bรหัสผ่านห้อง")) {
            if (DeluxePlayer.getDeluxePlayer((Player)e.getWhoClicked()).getPlayerClass().getId() < 1) {
              e.getWhoClicked().sendMessage("§7Room: §cต้องมี Rank §b§lTitan §cเป็นอย่างน้อยจึงจะสามารถเปลี่ยนรหัสผ่านห้องได้!");
              return;
            }

            e.getWhoClicked().closeInventory();
            game.game_renamer = "#NULL#";
            game.password_renamer = e.getWhoClicked().getName();
            chat_allow_for = e.getWhoClicked().getName();
            e.getWhoClicked().sendMessage("§7Password: §aโปรดใส่รหัสของห้องนี้ใหม่ลงในช่องแชทเพื่อทำการเปลี่ยนแปลงรหัสของห้องนี้ หากต้องการยกเลิกให้พิมพ์ \"cancel\" หรือต้องการนำรหัสผ่านออกให้พิมพ์ \"#NONE#\"");
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§bเริ่มเกมอัตโนมัติ ")) {
            if (auto_start_click_cooldown > 0) {
              e.getWhoClicked().sendMessage("§7Room: §cคุณต้องรออีก " + auto_start_click_cooldown + " วินาที จึงจะสามารถกดได้อีกครั้ง!");
              return;
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§cปิด")) {
              game.auto_start = true;
              if (Bukkit.getOnlinePlayers().size() >= game.Min_Players && game.gameState.equals(GameState.Recruit)) {
                game.time_prepare = 61;
                BukkitRunnable gametask = game.getPrepareCountdown();
                starchaser.Logger(LOG_TYPE.GAME, "§aMin player direct starting game...");
                gametask.runTaskTimer(core.getDeluxe, 1L, 20L);
              }
            } else {
              game.auto_start = false;
              if (game.gameState == GameState.Prepare) {
                game.gameState = GameState.Recruit;
              }
            }

            auto_start_click_cooldown = 5;
            game.openRoomSettings((Player)e.getWhoClicked());
            (new BukkitRunnable() {
              public void run() {
                if (evt.auto_start_click_cooldown > 0) {
                  --evt.auto_start_click_cooldown;
                } else {
                  this.cancel();
                }

              }
            }).runTaskTimerAsynchronously(core.getDeluxe, 20L, 20L);
            Iterator var4 = Bukkit.getOnlinePlayers().iterator();

            while(var4.hasNext()) {
              Player p = (Player)var4.next();
              if (game.host == DeluxePlayer.getDeluxePlayer(p)) {
                p.getInventory().setItem(3, game.getForceStartItem(1));
              } else {
                p.getInventory().setItem(3, new ItemStack(Material.AIR));
              }
            }
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bเลือกแมพ")) {
            game.openMapSettings((Player)e.getWhoClicked());
          }
        }

        if (e.getClickedInventory().getName().equals("§bเลือกแมพ")) {
          if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
            e.getWhoClicked().closeInventory();
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§b#สุ่มแมพ#")) {
            game.random_map = true;
            starchaser.BoardCastMsg("§7Game: §a" + e.getWhoClicked().getName() + " ได้ทำการเปลี่ยนแมพเป็น §bสุ่ม §aเรียบร้อยแล้ว");
          } else {
            game.random_map = false;
            game.force_map = e.getCurrentItem().getItemMeta().getDisplayName().replaceFirst("§b", "");
            starchaser.BoardCastMsg("§7Game: §a" + e.getWhoClicked().getName() + " ได้ทำการเปลี่ยนแมพเป็น §b" + game.force_map + " §aเรียบร้อยแล้ว");
          }

          if (game.gameState.equals(GameState.Prepare) && game.time_prepare < 15) {
            starchaser.BoardCastMsg("§7Game: §eการเปลี่ยนแมพในระหว่างใกล้จะเริ่มต้นจะทำให้เวลาที่จะเริ่มต้นกลับมาเป็น 15 วินาที");
            game.time_prepare = 16;
          }

          game.openMapSettings((Player)e.getWhoClicked());
          e.getWhoClicked().closeInventory();
          game.reload_game();
        }

        if (e.getClickedInventory().getName().equalsIgnoreCase("§bเลือกทีม")) {
          e.getWhoClicked().closeInventory();
          String color_id = (String)e.getCurrentItem().getItemMeta().getLore().get(1);
          TEAM_COLOR color = starchaser.StringToTeamColor(color_id);
          if (DeluxePlayer.getDeluxePlayer((Player)e.getWhoClicked()).getPlayer_team() == color) {
            e.getWhoClicked().sendMessage("§7Team: §cคุณได้เลือกทีมนี้อยู่แล้ว");
            return;
          }

          if (game.game_team.getTeam(color.toString()).size() >= game.player_per_team) {
            e.getWhoClicked().sendMessage("§7Team: §aทีมนี้เต็มแล้ว ไม่สามารถเข้าร่วมได้");
            return;
          }

          e.getWhoClicked().sendMessage("§7Team: §aคุณได้เข้ามร่วมทีม " + game.game_map.getTeams().getTeam(color.toString()).getColorCode() + color.toString());
          DeluxePlayer.getDeluxePlayer((Player)e.getWhoClicked()).setPlayer_team(color);
          e.getWhoClicked().getInventory().setItem(1, game.getTeamsSelecterItem(DeluxePlayer.getDeluxePlayer((Player)e.getWhoClicked()).getPlayer_team()));
        }
      }
    }

  }

  @EventHandler
  public void onPlayerPlaceBlock(BlockPlaceEvent e) {
    if (game.gameState == GameState.Loading || game.gameState == GameState.End || game.current_game.equals(MINIGAMES.TNTTAG)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onPlayerBreakBlock(BlockBreakEvent e) {
    if (game.gameState == GameState.Loading || game.gameState == GameState.End || game.current_game.equals(MINIGAMES.SPLEEF) || game.current_game.equals(MINIGAMES.TNTTAG)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onPlayerItemHoldEvent(PlayerItemHeldEvent e) {
    if (game.gameState == GameState.Prepare || game.gameState == GameState.Recruit) {
      try {
        ItemStack im = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if (im != null && im.getType().equals(Material.DIAMOND_SWORD)) {
          e.getPlayer().getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
          e.getPlayer().getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
          e.getPlayer().getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
          e.getPlayer().getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
          e.getPlayer().sendMessage("§7PVP: §aเข้าสู่โหมดท้าประลอง เลื่อนไปช่องอื่นเพื่อออกจากโหมดท้าประลอง");
        } else if (e.getPlayer().getInventory().getItem(e.getPreviousSlot()) != null && e.getPlayer().getInventory().getItem(e.getPreviousSlot()).getType().equals(Material.DIAMOND_SWORD)) {
          e.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
          e.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
          e.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
          e.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
          e.getPlayer().sendMessage("§7PVP: §eออกจากโหมดท้าประลองแล้ว");
        }
      } catch (NullPointerException var3) {
        e.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
      }
    }

  }

  @EventHandler
  public void PVPManager(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player && e.getEntity() instanceof Player && game.gameState == GameState.End || game.gameState == GameState.Loading) {
      e.setCancelled(true);
    }

    if (e.getDamager() instanceof Player && e.getEntity() instanceof Player && game.gameState == GameState.Recruit || game.gameState == GameState.Prepare) {
      Player damager = (Player)e.getDamager();
      Player vit = (Player)e.getEntity();
      if (damager.getItemInHand() != null && vit.getItemInHand() != null && damager.getItemInHand().getType().equals(Material.DIAMOND_SWORD) && vit.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
        if (e.getDamage() >= vit.getHealth()) {
          vit.setFoodLevel(20);
          vit.setHealth(20.0D);
          starchaser.BoardCastMsg("§7PVP: §c" + vit.getName() + " §bถูกฆ่าโดย §a" + damager.getName());
          vit.getInventory().setHeldItemSlot(0);
          vit.getInventory().setChestplate(new ItemStack(Material.AIR));
          vit.getInventory().setHelmet(new ItemStack(Material.AIR));
          vit.getInventory().setLeggings(new ItemStack(Material.AIR));
          vit.getInventory().setBoots(new ItemStack(Material.AIR));
          vit.teleport(new Location(Bukkit.getWorld("Main_lobby"), -4.0D, 202.0D, 3.0D));
          e.setCancelled(true);
        }

        e.setCancelled(false);
        return;
      }

      e.setCancelled(true);
    }

  }

  @EventHandler(
          priority = EventPriority.HIGHEST
  )
  public void onPlayerJoin(final PlayerJoinEvent e) {
    DisguiseAPI.undisguiseToAll(e.getPlayer());
    e.getPlayer().getInventory().setHeldItemSlot(0);
    game.ClearINV(e.getPlayer());
    e.getPlayer().teleport(new Location(Bukkit.getWorld("Main_lobby"), -4.0D, 202.0D, 3.0D));
    if (Bukkit.getOnlinePlayers().size() == 1 && !starchaser.AutoMinigames) {
      (new BukkitRunnable() {
        public void run() {
          game.give_host(DeluxePlayer.getDeluxePlayer(e.getPlayer()), e.getPlayer());
          game.gameState = GameState.Recruit;
          game.openRoomSettings(e.getPlayer());
          this.cancel();
        }
      }).runTaskTimer(core.getDeluxe, 48L, 48L);
    } else if (game.gameState.equals(GameState.Recruit) || game.gameState.equals(GameState.Prepare)) {
      starchaser.BoardCastMsg("§a" + e.getPlayer().getName() + " §7เข้าเร่วมเกมแล้ว");
      if (game.Min_Players - Bukkit.getOnlinePlayers().size() > 0 && game.auto_start) {
        starchaser.BoardCastMsg("§7Game: §aต้องการอีก §b" + (game.Min_Players - Bukkit.getOnlinePlayers().size()) + " §aผู้เล่นเพื่อที่จะเริ่มเกมได้");
      }
    }

    (new BukkitRunnable() {
      public void run() {
        game.give_looby_item(e.getPlayer());
        e.getPlayer().updateInventory();
        this.cancel();
      }
    }).runTaskTimer(core.getDeluxe, 55L, 55L);
    e.getPlayer().setWalkSpeed(0.2F);
    if (game.auto_start && Bukkit.getOnlinePlayers().size() >= game.Min_Players && game.gameState.equals(GameState.Recruit)) {
      BukkitRunnable gametask = game.getPrepareCountdown();
      starchaser.Logger(LOG_TYPE.GAME, "§aMin player direct starting game...");
      gametask.runTaskTimer(core.getDeluxe, 1L, 20L);
    }

  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getAction() == Action.PHYSICAL) {
      Block block = event.getClickedBlock();
      if (block == null) {
        return;
      }

      if (block.getType() == Material.SOIL && (game.gameState == GameState.Recruit || game.gameState == GameState.Prepare || event.getPlayer().getWorld().getName().equalsIgnoreCase("Main_Lobby"))) {
        event.setUseInteractedBlock(org.bukkit.event.Event.Result.DENY);
        event.setCancelled(true);
        block.setTypeIdAndData(block.getType().getId(), block.getData(), true);
      }
    }

  }

  @EventHandler(
          priority = EventPriority.NORMAL
  )
  public void onPlayerLeave(PlayerQuitEvent e) {
    if (game.gameState.equals(GameState.Recruit) || game.gameState.equals(GameState.Prepare)) {
      starchaser.BoardCastMsg("§c" + e.getPlayer().getName() + " §7ออกจากเกมแล้ว");
      if (game.Min_Players - Bukkit.getOnlinePlayers().size() + 1 > 0 && game.auto_start) {
        starchaser.BoardCastMsg("§7Game: §aต้องการอีก §b" + (game.Min_Players - Bukkit.getOnlinePlayers().size() + 1) + " §aผู้เล่นเพื่อที่จะเริ่มเกมได้");
      }
    }

    ArrayList players;
    Iterator var3;
    Player player;
    Player p;
    if (Bukkit.getOnlinePlayers().size() == 1 && game.host != DeluxePlayer.getDeluxePlayer(e.getPlayer())) {
      players = new ArrayList();
      var3 = Bukkit.getOnlinePlayers().iterator();

      while(var3.hasNext()) {
        player = (Player)var3.next();
        players.add(player);
      }

      Collections.shuffle(players);
      p = (Player)players.get(0);
      if (DeluxePlayer.getDeluxePlayer(p) == game.host) {
        p = (Player)players.get(1);
      }

      game.give_host(DeluxePlayer.getDeluxePlayer(p), p);
    }

    if (Bukkit.getOnlinePlayers().size() - 1 < 1) {
      game.Server_Name = starchaser.getNameRoom();
      game.random_map = true;
      game.host = null;
      game.max_x = 0;
      game.min_x = 0;
      game.max_z = 0;
      game.min_z = 0;
      game.auto_start = true;
      game.Server_password = "#NONE#";
      game.game_renamer = "#NULL#";
      game.password_renamer = "NULL";
      game.force_start = false;
      game.Max_Players = 16;
      game.Min_Players = 4;
      game.chat_allow_on_game_live = false;
      if (!starchaser.AutoMinigames) {
        game.gameState = GameState.Standby;
        game.current_game = starchaser.DefaultMinigames;
        game.reload_game();
        game.gameState = GameState.Standby;
        starchaser.Logger(LOG_TYPE.GAME, "All players leave going standby mode...");
      } else {
        game.gameState = GameState.Recruit;
        starchaser.Logger(LOG_TYPE.GAME, "All players leave going Recruit (Waiting) mode...");
        game.current_game = MINIGAMES.EMPTY;

        try {
          core.getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`servers` SET `server_game` = 'empty' WHERE `servers`.`id` = " + game.Server_ID + ";");
        } catch (SQLException var5) {
          var5.printStackTrace();
        }

        game.reload_game();
      }

    } else {
      if (DeluxePlayer.getDeluxePlayer(e.getPlayer()) == game.host) {
        players = new ArrayList();
        var3 = Bukkit.getOnlinePlayers().iterator();

        while(var3.hasNext()) {
          player = (Player)var3.next();
          players.add(player);
        }

        Collections.shuffle(players);
        p = (Player)players.get(0);
        if (DeluxePlayer.getDeluxePlayer(p) == game.host) {
          p = (Player)players.get(1);
        }

        game.give_host(DeluxePlayer.getDeluxePlayer(p), p);
      }

      e.getPlayer().updateInventory();
    }
  }

  @EventHandler
  public void PlayerDMG(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player && (game.gameState == GameState.Recruit || game.gameState == GameState.Prepare)) {
      if (e.getCause().equals(DamageCause.ENTITY_ATTACK)) {
        return;
      }

      e.setDamage(0.0D);
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void PlayerFood(FoodLevelChangeEvent e) {
    if (game.gameState == GameState.Recruit || game.gameState == GameState.Prepare || game.current_game == MINIGAMES.SPLEEF || game.current_game == MINIGAMES.TNTTAG || game.current_game == MINIGAMES.TNTRUN) {
      e.setCancelled(true);
    }

  }

  public boolean isOutsideOfBorder(Player p) {
    Location loc = p.getLocation();
    WorldBorder border = p.getWorld().getWorldBorder();
    double size = border.getSize() / 2.0D;
    Location center = border.getCenter();
    double x = loc.getX() - center.getX();
    double z = loc.getZ() - center.getZ();
    return x > size || -x > size || z > size || -z > size;
  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    if (DeluxePlayer.getDeluxePlayer(e.getPlayer()) != null && DeluxePlayer.getDeluxePlayer(e.getPlayer()) == game.host) {
      host_afk_timer = 0;
    }

    if (e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("main_lobby") && e.getPlayer().getLocation().getY() < 65.0D) {
      e.getPlayer().teleport(new Location(Bukkit.getWorld("Main_lobby"), -4.0D, 202.0D, 3.0D));
    }

    Location from;
    Location to;
    double x;
    double z;
    if (this.isOutsideOfBorder(e.getPlayer())) {
      e.getPlayer().sendMessage("§7Border: §cไม่สามารถออกไปจากสนามเล่นได้!");
      from = e.getFrom();
      to = e.getTo();
      x = Math.floor(from.getX());
      z = Math.floor(from.getZ());
      if (Math.floor(to.getX()) != x || Math.floor(to.getZ()) != z) {
        ++x;
        ++z;
        e.getPlayer().teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));
      }
    }

    if (game.gameState == GameState.Loading) {
      from = e.getFrom();
      to = e.getTo();
      x = Math.floor(from.getX());
      z = Math.floor(from.getZ());
      if (Math.floor(to.getX()) != x || Math.floor(to.getZ()) != z) {
        x += 0.5D;
        z += 0.5D;
        e.getPlayer().teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));
      }
    }

  }
}
