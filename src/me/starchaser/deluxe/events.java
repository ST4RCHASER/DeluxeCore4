//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import me.starchaser.deluxe.games.evt;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import me.starchaser.deluxe.starchaser.ServerGamemode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class events implements Listener {
  String kick_msg = "§7DeluxeCore: §cเกิดข้อผิดพลาดในการตรวจสอบข้อมูลของท่านโปรเลองใหม่ภายหลังหรือติตต่อแอดมิน!";
  public static HashMap<DeluxePlayer, Integer> player_inv_page = new HashMap();
  public static ArrayList<events.popupchat> chat_history = new ArrayList();

  public events() {
  }

  @EventHandler(
          priority = EventPriority.LOWEST
  )
  public void PlayerJoinEvent(final PlayerJoinEvent evt) {
    evt.getPlayer().setHealth(20.0D);
    evt.getPlayer().setFoodLevel(20);
    evt.getPlayer().setGameMode(GameMode.ADVENTURE);
    evt.setJoinMessage((String)null);

    try {

      ResultSet result_player = core.getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + evt.getPlayer().getName() + "'");
      if (!result_player.isBeforeFirst()) {
        evt.getPlayer().sendMessage("§7Account: §aเข้าเล่นครั้งแรกระบบกำลังสร้างบัญชี กรุณารอสักครู่...");
        starchaser.Logger(LOG_TYPE.PLAYER, "§bAccount §7" + evt.getPlayer().getName() + "§b not found Creating...");
        starchaser.CreateAccount(evt.getPlayer());
        evt.getPlayer().sendMessage("§7Account: §eกำลังอ่านโปรไฟล์...");
        (new BukkitRunnable() {
          public void run() {
            if (!starchaser.getPlayerData(evt.getPlayer())) {
              evt.getPlayer().kickPlayer(events.this.kick_msg);
            }

            this.cancel();
          }
        }).runTaskTimer(core.getDeluxe, 40L, 20L);
      } else {
        evt.getPlayer().sendMessage("§7Account: §eกำลังอ่านโปรไฟล์...");
        (new BukkitRunnable() {
          public void run() {
            if (!starchaser.getPlayerData(evt.getPlayer())) {
              evt.getPlayer().kickPlayer(events.this.kick_msg);
            }

            this.cancel();
          }
        }).runTaskTimer(core.getDeluxe, 40L, 20L);
      }
    } catch (SQLException var3) {
      var3.printStackTrace();
      starchaser.Logger(LOG_TYPE.PLAYER, "§cError on get player data... (TASK: events.PlayerJoinEvent) [" + evt.getPlayer().getName() + "]");
      evt.getPlayer().kickPlayer(this.kick_msg);
      return;
    }

    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY) {
      Player player = evt.getPlayer();
      ScoreBoardLobbyShow(player);
      this.giveItemLobby(evt.getPlayer());
      evt.getPlayer().teleport(starchaser.getLobbyLocaction());
    }

  }

  public static void ScoreBoardLobbyShow(final Player player) {
    (new BukkitRunnable() {
      public void run() {
        try {
          if (player == null) {
            this.cancel();
          }

          DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(player);
          if (dp == null) {
            this.cancel();
          }

          if (dp.getPlayerClass() == null) {
            this.cancel();
          }

          Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
          Objective obj = sb.registerNewObjective("LobbyScoreboard", "dummy");
          String title = dp.getTitle().getStr();
          if (title.length() > 28) {
            title = title.substring(0, 27);
          }

          obj.setDisplayName("§6§lSI§e§lAM§f§lC§e§lRA§6§lFT");
          obj.getScore("§r").setScore(14);
          obj.getScore("§fRank§7»§f" + dp.getPlayerClass().getStr()).setScore(12);
          obj.getScore("§fTitle§7»§f" + title).setScore(11);
          obj.getScore("§r§r").setScore(10);
          obj.getScore("§e◆ §6Coins§7» §e" + dp.getCoins() + " §6⛁").setScore(9);
          obj.getScore("§e◆ §bFeathers§7» §e" + dp.getFeather_points() + " §b➳").setScore(8);
          obj.getScore("§e◆ §aPaid Points§7» §e" + dp.getPaid_points() + " §a♦").setScore(7);
          obj.getScore("§r§r§r").setScore(6);
          obj.getScore("§fLevel§7» §a" + dp.getLevel().get_Int() + "§6✭").setScore(5);
          obj.getScore("§fProgress§7» §a" + dp.getLevel().getXPPercent() + "%").setScore(4);
          obj.getScore("§8[" + dp.getLevel().getXPBar() + "§8]").setScore(3);
          obj.getScore("§r§r§r§r").setScore(3);
          obj.getScore("§b◆ §fLobby: §aLobby#1").setScore(2);
          obj.getScore("§r§r§r§r§r").setScore(1);
          obj.getScore("§6➥ §eSiamCraft.net").setScore(1);
          obj.getScore("§6➥ §eSiamCraft.net").setScore(0);
          obj.setDisplaySlot(DisplaySlot.SIDEBAR);
          player.setScoreboard(sb);
        } catch (NullPointerException var5) {
          this.cancel();
        }

      }
    }).runTaskTimer(core.getDeluxe, 50L, 50L);
  }

  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    if (starchaser.ServerGamemodeMode.equals(ServerGamemode.LOBBY)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void voidTeleport(PlayerMoveEvent e) {
    if (e.getPlayer().getLocation().getY() < 1.0D && starchaser.ServerGamemodeMode.equals(ServerGamemode.LOBBY)) {
      e.getPlayer().teleport(starchaser.getLobbyLocaction());
    }

  }

  @EventHandler
  public void onItemDrop(PlayerDropItemEvent e) {
    e.setCancelled(starchaser.ServerGamemodeMode.equals(ServerGamemode.LOBBY));
  }

  @EventHandler
  public void cancelbreakblock(BlockBreakEvent e) {
    if (DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayerClass().getId() < 9 && starchaser.ServerGamemodeMode.equals(ServerGamemode.LOBBY)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void cancelplaceblock(BlockPlaceEvent e) {
    if (DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayerClass().getId() < 9 && starchaser.ServerGamemodeMode.equals(ServerGamemode.LOBBY)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void HoloMakeOnMove(PlayerMoveEvent e) {
  }

  public void giveItemLobby(Player p) {
    ItemStack clock = new ItemStack(Material.WATCH);
    ItemMeta clock_meta = clock.getItemMeta();
    clock_meta.setDisplayName("§bรายชื่อห้อง §7(คลิกขวา)");
    clock_meta.setLore(Arrays.asList("§fคลิกขวาเพื่อเปิดรายชื่อห้องมินิเกมส์"));
    clock.setItemMeta(clock_meta);
    ItemStack compass = new ItemStack(Material.COMPASS);
    ItemMeta compass_meta = compass.getItemMeta();
    compass_meta.setDisplayName("§bรายชื่อเซิร์ฟเวอร์ §7(คลิกขวา)");
    compass_meta.setLore(Arrays.asList("§fคลิกขวาเพื่อเปิดรายชื่อเซิร์ฟเวอร์่ที่เปิดอยู่"));
    compass.setItemMeta(compass_meta);
    ItemStack chest = new ItemStack(Material.CHEST);
    ItemMeta chest_meta = chest.getItemMeta();
    chest_meta.setDisplayName("§bร้านค้า §7(คลิกขวา)");
    chest_meta.setLore(Arrays.asList("§fคลิกเพื่อเปิดร้านค้า มียศขายและของอื่นๆ"));
    chest.setItemMeta(chest_meta);
    p.getInventory().clear();
    p.getInventory().setItem(3, chest);
    p.getInventory().setItem(1, clock);
    p.getInventory().setItem(0, compass);
  }

  @EventHandler
  public void onPlayerClick(PlayerInteractEvent e) {
    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand() != null) {
      if (e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().getItemMeta() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bรายชื่อห้อง §7(คลิกขวา)")) {
        player_inv_page.put(DeluxePlayer.getDeluxePlayer(e.getPlayer()), 0);
        e.getPlayer().openInventory((Inventory)core.server_inventory.get((Integer)player_inv_page.get(DeluxePlayer.getDeluxePlayer(e.getPlayer()))));
      }

      if (e.getPlayer().getItemInHand().getType() == Material.COMPASS && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bรายชื่อเซิร์ฟเวอร์ §7(คลิกขวา)")) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "bs open server " + e.getPlayer().getName());
      }

      if (e.getPlayer().getItemInHand().getType() == Material.CHEST && e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bร้านค้า §7(คลิกขวา)")) {
        starchaser.openRankShop(DeluxePlayer.getDeluxePlayer(e.getPlayer()), e.getPlayer());
      }
    }

  }

  @EventHandler
  public void onMonsterSpawn(CreatureSpawnEvent e) {
    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY || starchaser.ServerGamemodeMode.equals(ServerGamemode.MINIGAMES) && game.gameState == GameState.Recruit || game.gameState == GameState.Prepare) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onTeleport(PlayerTeleportEvent e) {
    DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(e.getPlayer());
    Player player = e.getPlayer();
    if (dp != null) {
      if (dp.isShow_title()) {
        Hologram hologram = dp.getHologram();
        hologram.teleport(Bukkit.getWorld(player.getWorld().getName()), player.getLocation().getX(), player.getLocation().getY() + 2.6D, player.getLocation().getZ());
      }

    }
  }

  @EventHandler
  public void onInvClick(InventoryClickEvent e) {
    if (e.getInventory().getName().equalsIgnoreCase("§bร้านค้า")) {
      e.setCancelled(true);
      if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§aอัพเกรดไปยศ")) {
          e.getWhoClicked().sendMessage("§7Shop: §cไม่สามารถอัพเกรดได้เนื่องจากยอดเงินของคุณไม่เพียงพอ");
          return;
        }

        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cไม่สามารถอัพเกรดได้อีกแล้ว")) {
          e.getWhoClicked().sendMessage("§7Shop: §cไม่สามารถอัพเกรดได้อีกแล้ว");
          return;
        }
      }

      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer((Player)e.getWhoClicked());
      if (e.getCurrentItem().getType().equals(Material.IRON_BLOCK)) {
        starchaser.BoardCastMsg("§7Rank: §a" + dp.getName() + " §fได้ทำการอัพเกรดยศเป็น §b§lTITAN §fเรียบร้อยแล้ว เย้ๆ");
        dp.getPlayerClass().setId(1);
        dp.setCoins(dp.getCoins() - 30000);
      }

      if (e.getCurrentItem().getType().equals(Material.DIAMOND_BLOCK)) {
        starchaser.BoardCastMsg("§7Rank: §a" + dp.getName() + " §fได้ทำการอัพเกรดยศเป็น §d§lHERO §fเรียบร้อยแล้ว เย้ๆ");
        dp.getPlayerClass().setId(2);
        dp.setCoins(dp.getCoins() - '썐');
      }

      if (e.getCurrentItem().getType().equals(Material.GOLD_BLOCK)) {
        starchaser.BoardCastMsg("§7Rank: §a" + dp.getName() + " §fได้ทำการอัพเกรดยศเป็น §6§lMASTER §fเรียบร้อยแล้ว เย้ๆ");
        dp.getPlayerClass().setId(3);
        dp.setCoins(dp.getCoins() - 120000);
      }

      if (e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
        starchaser.BoardCastMsg("§7Rank: §a" + dp.getName() + " §fได้ทำการอัพเกรดยศเป็น §a§lLEGEND §fเรียบร้อยแล้ว เย้ๆ");
        dp.getPlayerClass().setId(4);
        dp.setCoins(dp.getCoins() - 180000);
      }

      e.getWhoClicked().closeInventory();
    }

    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY && e.getInventory().getName().startsWith("§bรายชื่อห้อง")) {
      e.setCancelled(true);

      try {
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
          return;
        }

        if (e.getCurrentItem().getType() != Material.REDSTONE_BLOCK && e.getCurrentItem().getType() != Material.EMERALD_BLOCK && e.getCurrentItem().getType() != Material.GOLD_BLOCK && e.getCurrentItem().getType() != Material.LAPIS_BLOCK && e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE && e.getCurrentItem().getType() != Material.PAPER && e.getCurrentItem().getType() != Material.SULPHUR && e.getCurrentItem().getType() != Material.REDSTONE) {
          return;
        }

        if (e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
          e.getWhoClicked().sendMessage("§7Portal: §cไม่สามารถเข้าห้องนี้ได้เนื่องจากไม่รับการอนุญาติ!");
          return;
        }

        if (e.getCurrentItem().getType().equals(Material.LAPIS_BLOCK)) {
          e.getWhoClicked().sendMessage("§7Portal: §cไม่สามารถเข้าห้องนี้ได้เนื่องจากเกมกำลังดำเนินอยู่!");
          return;
        }

        int room_id;
        if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
          room_id = Integer.parseInt(((String)e.getCurrentItem().getItemMeta().getLore().get(5)).replaceFirst("§bหมายเลขห้อง: §f", ""));
          DeluxePlayer.getDeluxePlayer((Player)e.getWhoClicked()).setTargetServer(String.valueOf(room_id));
          ResultSet res = core.getConn.createStatement().executeQuery("SELECT * FROM `servers` WHERE `id` = " + room_id);
          res.next();
          String password = res.getString("server_password");
          DeluxePlayer.getDeluxePlayer((Player)e.getWhoClicked()).setEnterPassword(password);
          e.getWhoClicked().sendMessage("§a§m-----------------------");
          e.getWhoClicked().sendMessage(" " + e.getCurrentItem().getItemMeta().getDisplayName());
          e.getWhoClicked().sendMessage("§7 โปรดใส่รหัสผ่านของห้องหมายเลข " + ((String)e.getCurrentItem().getItemMeta().getLore().get(5)).replaceFirst("§bหมายเลขห้อง: §f", ""));
          e.getWhoClicked().sendMessage("§7 สามารถพิมพ์ใส่ช่องแชทได้เลย");
          e.getWhoClicked().sendMessage("§a§m-----------------------");
          e.getWhoClicked().closeInventory();
          return;
        }

        if (e.getCurrentItem().getType().equals(Material.PAPER) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§bสร้างห้อง")) {
          e.getWhoClicked().sendMessage("§7Room: §aกำลังสร้างห้องโปรดรอสักครู่...");
          ResultSet room_list = core.getConn.createStatement().executeQuery("SELECT * FROM `servers` WHERE `state` = -1");
          e.getWhoClicked().closeInventory();
          if (!room_list.isBeforeFirst()) {
            e.getWhoClicked().sendMessage("§7Room: §cไม่สามารถสร้างห้องได้ขณะนี้โปรดลองใหม่อีกครั้งภายหลัง");
            return;
          }

          room_list.next();
          room_id = room_list.getInt("id");
          e.getWhoClicked().sendMessage("§7Room: §aสร้างห้องเรียบร้อยแล้วโปรดรอสักครู่...");
          starchaser.sendToServer(e.getWhoClicked().getName(), "game/" + room_id);
          return;
        }

        if (e.getCurrentItem().getType() == Material.REDSTONE && e.getCurrentItem().getItemMeta().getDisplayName() == "§bหน้าต่อไป") {
          try {
            room_id = Integer.parseInt(e.getInventory().getName().substring(e.getInventory().getName().length() - 1)) + 1;
            core.server_inventory.get(room_id);
          } catch (IndexOutOfBoundsException var6) {
            room_id = 0;
          }

          e.getWhoClicked().openInventory((Inventory)core.server_inventory.get(room_id));
        }

        if (e.getCurrentItem().getType() == Material.SULPHUR && e.getCurrentItem().getItemMeta().getDisplayName() == "§bหน้าที่แล้ว") {
          try {
            room_id = Integer.parseInt(e.getInventory().getName().substring(e.getInventory().getName().length() - 1)) - 1;
            core.server_inventory.get(room_id);
          } catch (IndexOutOfBoundsException var5) {
            room_id = 0;
          }

          e.getWhoClicked().openInventory((Inventory)core.server_inventory.get(room_id));
        }

        if (e.getCurrentItem().getItemMeta().getLore() != null && ((String)e.getCurrentItem().getItemMeta().getLore().get(5)).startsWith("§bหมายเลขห้อง: ")) {
          room_id = Integer.parseInt(((String)e.getCurrentItem().getItemMeta().getLore().get(5)).replaceFirst("§bหมายเลขห้อง: §f", ""));
          starchaser.sendToServer(e.getWhoClicked().getName(), "game/" + room_id);
        }
      } catch (SQLException var7) {
        var7.printStackTrace();
        starchaser.Logger(LOG_TYPE.GAME, "ERROR ON INV CLICK PLEASE CHECK ERROR!");
      }
    }

  }

  @EventHandler
  public void onPlayerLeave(final PlayerQuitEvent evt) {
    evt.setQuitMessage((String)null);
    starchaser.sendPlayerData(evt.getPlayer());
    (new BukkitRunnable() {
      public void run() {
        try {
          DeluxePlayer.removeDeluxePlayer(evt.getPlayer());
          this.cancel();
        } catch (NullPointerException var2) {
          this.cancel();
        }

      }
    }).runTaskTimerAsynchronously(core.getDeluxe, 10L, 10L);
  }

  @EventHandler
  public void onPlayerChat(PlayerChatEvent evt) throws SQLException {
    evt.setCancelled(true);
    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY) {
      ResultSet resultSet = core.getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + evt.getPlayer().getName() + "'");
      if (resultSet.next()) {
        int wp_count = resultSet.getInt("wp");
        if (wp_count >= 4) {
          evt.getPlayer().sendMessage("§7Chat: §cแชทถูกระงับการใช้งานเนื่องจากคุณมี warnpoint มากว่า 3 แล้วถ้าต้องการปลดสามารถปลดได้ที่ http://shop.siamcraft.net");
          return;
        }
      }
    }

    if (!DeluxePlayer.getDeluxePlayer(evt.getPlayer()).isEnterPassword().equals("#NONE#") && !DeluxePlayer.getDeluxePlayer(evt.getPlayer()).getTargetServer().equals("#NONE#")) {
      if (evt.getMessage().equals(DeluxePlayer.getDeluxePlayer(evt.getPlayer()).isEnterPassword())) {
        evt.getPlayer().sendMessage("§7Portal: §aรหัสผ่านถูกต้อง");
        starchaser.sendToServer(evt.getPlayer().getName(), "game/" + DeluxePlayer.getDeluxePlayer(evt.getPlayer()).getTargetServer());
      } else {
        evt.getPlayer().sendMessage("§7Portal: §cรหัสผ่านผิดโปรดลองเข้าใหม่อีกครั้ง!");
        DeluxePlayer.getDeluxePlayer(evt.getPlayer()).setEnterPassword("#NONE#");
        DeluxePlayer.getDeluxePlayer(evt.getPlayer()).setTargetServer("#NONE#");
      }

    } else {
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(evt.getPlayer());
      if (evt.getMessage().startsWith("!#") && dp.getPlayerClass().getId() >= 6) {
        commands.onBotCommand(evt.getMessage().replaceFirst("!#", ""), evt.getPlayer(), true);
      } else {
        if (evt.getMessage().startsWith("!")) {
          commands.onBotCommand(evt.getMessage().replaceFirst("!", ""), evt.getPlayer(), false);
        }

        starchaser.Logger(LOG_TYPE.CHAT, "§7Chat: §a" + evt.getPlayer().getName() + ": §f" + evt.getMessage());
        if (me.starchaser.deluxe.games.evt.chat_allow_for != "#NONE#" && me.starchaser.deluxe.games.evt.chat_allow_for == evt.getPlayer().getName()) {
          me.starchaser.deluxe.games.evt.chat_allow_for = "#NONE#";
        } else {
          if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES && game.gameState == GameState.Live && !game.chat_allow_on_game_live || starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES && game.gameState == GameState.Loading && !game.chat_allow_on_game_live) {
            evt.getPlayer().sendMessage("§7Chat: §cแชทไม่อนุญาติให้ใช้ได้ในระหว่างการเล่น!");
            return;
          }

          Iterator var6 = Bukkit.getOnlinePlayers().iterator();

          while(var6.hasNext()) {
            Player p = (Player)var6.next();
            if (p instanceof Player && !p.hasMetadata("NPC")) {
              p.sendMessage("§8[" + dp.getLevel().getStr() + "§8]" + dp.getPlayerClass().getStr() + dp.getTitle().getStr() + " §f" + evt.getPlayer().getName() + ": §f" + evt.getMessage());
            }
          }

          if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES && game.gameState == GameState.Live && game.current_game == MINIGAMES.BEDWARS) {
            return;
          }

          var6 = chat_history.iterator();

          while(var6.hasNext()) {
            events.popupchat popupchat = (events.popupchat)var6.next();
            if (popupchat.getOwner().equals(evt.getPlayer())) {
              popupchat.setForce_remove(true);
            }
          }

          chat_history.add(new events.popupchat(evt.getPlayer(), evt.getMessage()));
          if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY && core.world_scam != "#NONE#" && core.world_scam != "#WIN#" && core.world_scam.equals(evt.getMessage())) {
            starchaser.BoardCastMsg("§7Reaction: §e" + evt.getPlayer().getName() + " §aได้รับ exp 20 และได้รับ 5 coins จากการชนะ ChatReaction");
            core.world_scam = "#WIN#";
            DeluxePlayer.getDeluxePlayer(evt.getPlayer()).getLevel().add_xp(20);
            DeluxePlayer.getDeluxePlayer(evt.getPlayer()).addCoins(5);
          }
        }

      }
    }
  }

  @EventHandler
  public void onWeather(WeatherChangeEvent e) {
    World ew = e.getWorld();
    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY && e.toWeatherState()) {
      e.setCancelled(true);
      ew.setThundering(false);
      ew.setWeatherDuration(0);
    }

    if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES && e.toWeatherState() && e.getWorld().getName().equalsIgnoreCase("main_lobby")) {
      e.setCancelled(true);
      ew.setThundering(false);
      ew.setWeatherDuration(0);
    }

  }

  @EventHandler
  public void onFoodLevelChange(FoodLevelChangeEvent e) {
    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY) {
      e.setCancelled(true);
    }

  }

  public class popupchat {
    final Player player;
    final String chat;
    boolean force_remove;

    popupchat(final Player player, final String chat) {
      this.player = player;
      this.chat = chat;
      final Hologram hologram = HologramsAPI.createHologram(core.getDeluxe, player.getLocation().add(0.0D, 2.0D, 0.0D));
      hologram.appendTextLine("§b" + chat);
      (new BukkitRunnable() {
        int ticksRun;
        int out_tricks = chat.length() * 5;
        int is_out_ing = 0;

        public void run() {
          try {
            ++this.ticksRun;
            if (this.out_tricks < 100) {
              this.out_tricks = 100;
            }

            if (player == null || !player.isOnline() || popupchat.this.force_remove) {
              hologram.teleport(player.getLocation().add(0.0D, 4.1D, 0.0D));
              (new BukkitRunnable() {
                public void run() {
                  hologram.delete();
                }
              }).runTaskLater(core.getDeluxe, 3L);
            }

            if (this.ticksRun > this.out_tricks) {
              hologram.teleport(player.getLocation().add(0.0D, 4.1D, 0.0D));
            } else {
              hologram.teleport(player.getLocation().add(0.0D, 2.9D, 0.0D));
            }

            if (this.ticksRun > this.out_tricks + 3) {
              hologram.delete();
              this.cancel();
            }

          } catch (IllegalArgumentException var2) {
            this.cancel();
          }
        }
      }).runTaskTimer(core.getDeluxe, 1L, 1L);
    }

    public Player getOwner() {
      return this.player;
    }

    public void setForce_remove(boolean force_remove) {
      this.force_remove = force_remove;
    }
  }
}
