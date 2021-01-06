//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.YamlReader;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.DeluxeMap.DeluxeTeam;
import me.starchaser.deluxe.games.DeluxeMap.DeluxeTeams;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class game {
  static int cooldown_time = 15;
  static int cooldown_subtime = 9;
  public static int time_prepare = 61;
  public static int player_per_team = 4;
  public static game.GameState gameState;
  public static game.MINIGAMES current_game;
  public static int Max_Players;
  public static int Min_Players;
  public static String Server_ID;
  public static String Server_Name;
  public static String Server_password;
  public static DeluxePlayer host;
  public static int game_rand;
  public static DeluxeTeams game_team;
  public static DeluxeMap game_map;
  public static YamlReader game_map_settings;
  public static boolean force_start;
  public static boolean random_map;
  public static boolean auto_start;
  public static String force_map;
  public static String game_renamer;
  public static String password_renamer;
  public static boolean chat_allow_on_game_live;
  public static boolean player_xp_grade;
  public static int max_x;
  public static int min_x;
  public static int max_z;
  public static int min_z;
  public static int many_players_in_last_game;
  public static boolean EventMode;
  public static ArrayList<game.PlayerXPDB> playerXPDB_data;

  public game() {
  }

  public static void give_host(DeluxePlayer dp, Player p) {
    give_host(dp);
    p.sendMessage("§7Room: §aคุณได้เป็นหัวหน้าห้องในขณะนี้! สามารถตั้งค่าห้องได้โดยใช้คำสั่ง !mp settings");
    p.sendMessage("§7Help: §aหากสงสัยวิธีการใช้งานระบบห้องสามารถดูคลิปการสอนใช้เบื้องต้นได้ที่นี้");
    p.sendMessage("§7Help: §ehttps://www.youtube.com/watch?v=ziIEe2fPuCc");
  }

  public static void give_host(DeluxePlayer dp) {
    if (!starchaser.AutoMinigames || dp.getPlayerClass().getId() >= 9) {
      starchaser.BoardCastMsg(ChatColor.AQUA + dp.getName() + ChatColor.GRAY + " ได้เป็นหัวหน้าห้องในขณะนี้");
      host = dp;
      Player p;
      if (gameState.equals(game.GameState.Recruit) || gameState.equals(game.GameState.Prepare)) {
        for(Iterator var1 = Bukkit.getOnlinePlayers().iterator(); var1.hasNext(); p.updateInventory()) {
          p = (Player)var1.next();
          if (DeluxePlayer.getDeluxePlayer(p) == host) {
            p.getInventory().setItem(2, getRoomSettingsItem());
            p.getInventory().setItem(3, getForceStartItem(1));
          } else {
            p.getInventory().setItem(2, new ItemStack(Material.AIR));
            p.getInventory().setItem(3, new ItemStack(Material.AIR));
          }
        }
      }

    }
  }

  public static void give_looby_item(Player p) {
    p.getInventory().clear();
    p.getInventory().setHelmet(new ItemStack(Material.AIR));
    p.getInventory().setChestplate(new ItemStack(Material.AIR));
    p.getInventory().setLeggings(new ItemStack(Material.AIR));
    p.getInventory().setBoots(new ItemStack(Material.AIR));
    p.getInventory().setItem(0, getKitSelecterItem());
    ItemStack im = new ItemStack(Material.IRON_DOOR);
    ItemMeta im_meta = im.getItemMeta();
    im_meta.setDisplayName("§bกลับไปที่ล๊อบบี้ §7§o(คลิกขวา)");
    im.setItemMeta(im_meta);
    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta sword_meta = sword.getItemMeta();
    sword_meta.setDisplayName("§a§l[PVP] §cถือเพื่อเข้าสู่โหมดประลอง");
    sword.setItemMeta(sword_meta);
    p.getInventory().setItem(8, im);
    p.getInventory().setItem(7, sword);
    if (current_game != game.MINIGAMES.EMPTY && game_map != null && game_map.getTeams() != null && game_map.getTeams().getStringTeamsList().size() >= 2 && current_game != game.MINIGAMES.BLOCKHUNT) {
      p.getInventory().setItem(1, getTeamsSelecterItem(DeluxePlayer.getDeluxePlayer(p).getPlayer_team()));
    }

    if (DeluxePlayer.getDeluxePlayer(p) == host) {
      p.getInventory().setItem(2, getRoomSettingsItem());
      p.getInventory().setItem(3, getForceStartItem(1));
    }

    p.updateInventory();
  }

  public static BukkitRunnable gamecooldown() {
    cooldown_subtime = 9;
    cooldown_time = 12;
    Iterator var0 = Bukkit.getOnlinePlayers().iterator();

    while(var0.hasNext()) {
      Player p = (Player)var0.next();

      try {
        p.setGameMode(GameMode.ADVENTURE);
      } catch (IllegalStateException var3) {
        ;
      }
    }

    return new BukkitRunnable() {
      public void run() {
        game.gameState = game.GameState.Loading;
        if (game.cooldown_subtime < 1) {
          --game.cooldown_time;
          if (game.cooldown_time <= 10) {
            Iterator var1 = Bukkit.getOnlinePlayers().iterator();

            while(var1.hasNext()) {
              Player p = (Player)var1.next();
              p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 3.0F);
            }
          }

          game.cooldown_subtime = 9;
        } else {
          --game.cooldown_subtime;
        }

        String str;
        if (game.cooldown_time >= 10) {
          str = "§f§l[§c■■■■■■■■■■§f§l] §f";
        } else if (game.cooldown_time >= 9) {
          str = "§f§l[§b■§c■■■■■■■■■§f§l] §f";
        } else if (game.cooldown_time >= 8) {
          str = "§f§l[§b■■§c■■■■■■■■§f§l] §f";
        } else if (game.cooldown_time >= 7) {
          str = "§f§l[§b■■■§c■■■■■■■§f§l] §f";
        } else if (game.cooldown_time >= 6) {
          str = "§f§l[§b■■■■§c■■■■■■§f§l] §f";
        } else if (game.cooldown_time >= 5) {
          str = "§f§l[§b■■■■■§c■■■■■§f§l] §f";
        } else if (game.cooldown_time >= 4) {
          str = "§f§l[§b■■■■■■§c■■■■§f§l] §f";
        } else if (game.cooldown_time >= 3) {
          str = "§f§l[§b■■■■■■■§c■■■§f§l] §f";
        } else if (game.cooldown_time >= 2) {
          str = "§f§l[§b■■■■■■■■§c■■§f§l] §f";
        } else if (game.cooldown_time >= 1) {
          str = "§f§l[§b■■■■■■■■■§c■§f§l] §f";
        } else {
          str = "§f§l[§b■■■■■■■■■■§f§l] §f";
        }

        Iterator var7 = Bukkit.getOnlinePlayers().iterator();

        Player px;
        while(var7.hasNext()) {
          px = (Player)var7.next();
          starchaser.sendActionbar(px, "§f§l[" + game.current_game.toString().toLowerCase() + "] §aGameStart §f" + game.cooldown_time + "." + game.cooldown_subtime + " " + str);
        }

        if (game.cooldown_subtime < 1 && game.cooldown_time < 1) {
          if (Bukkit.getOnlinePlayers().size() < 2) {
            var7 = Bukkit.getOnlinePlayers().iterator();

            while(var7.hasNext()) {
              px = (Player)var7.next();
              px.playSound(px.getLocation(), Sound.NOTE_PLING, 1.0F, -1.0F);
            }

            starchaser.BoardCastMsg("§7Game: §cไม่สามารถเริ่มเกมได้เนื่องจากผู้เล่นไม่เพียงพอ...");
            game.game_end_task((String)null);
            this.cancel();
            return;
          }

          game.gameState = game.GameState.Live;

          for(var7 = Bukkit.getOnlinePlayers().iterator(); var7.hasNext(); DeluxePlayer.getDeluxePlayer(px).setPlayerState(game.PlayerState.Live)) {
            px = (Player)var7.next();

            try {
              if (!px.getWorld().getName().equalsIgnoreCase(game.game_map.getMap_id())) {
                px.sendMessage("§7Error: §cมีบางอย่างผิดพลาด คุณจึงส่งกลับมาที่ Lobby");
                starchaser.sendToServer(px.getName(), "Lobby");
              }

              px.setGameMode(GameMode.SURVIVAL);
              game.ClearINV(px);
            } catch (IllegalStateException var5) {
              ;
            }
          }

          if (Bukkit.getOnlinePlayers().size() >= 5) {
            game.player_xp_grade = true;
          } else {
            game.player_xp_grade = false;
          }

          if (game.current_game == game.MINIGAMES.SPLEEF) {
            me.starchaser.deluxe.games.spleef.game.gameStart();
          }

          if (game.current_game == game.MINIGAMES.TNTTAG) {
            me.starchaser.deluxe.games.tnttag.game.gameStart();
          }

          if (game.current_game == game.MINIGAMES.TNTRUN) {
            me.starchaser.deluxe.games.tntrun.game.gameStart();
          }

          if (game.current_game == game.MINIGAMES.BEDWARS) {
            me.starchaser.deluxe.games.bedwars.game.gameStart();
          }

          if (game.current_game == game.MINIGAMES.UHC) {
            me.starchaser.deluxe.games.uhc.game.gameStart();
          }

          if (game.current_game == game.MINIGAMES.BLOCKHUNT) {
            me.starchaser.deluxe.games.blockhunt.game.gameStart();
          }

          game.many_players_in_last_game = Bukkit.getOnlinePlayers().size();
          this.cancel();
        }

      }
    };
  }

  public static ItemStack getForceStartItem(int count) {
    ItemStack is = new ItemStack(Material.DIAMOND, count);
    ItemMeta lmeta = is.getItemMeta();
    lmeta.setDisplayName("§bเริ่มเกมทันที §7§o(คลิกขวา)");
    if (count > 1) {
      lmeta.setLore(Arrays.asList("§r", "§fคลิกเพื่อเริ่มเกมทันที", "§ดเหลือเวลาอีก: §a" + count + " §fวินาที"));
    } else {
      lmeta.setLore(Arrays.asList("§r", "§fคลิกเพื่อเริ่มเกมทันที"));
    }

    is.setItemMeta(lmeta);
    evt.host_afk_timer = 0;
    return is;
  }

  public static ItemStack getTeamsSelecterItem(TEAM_COLOR color) {
    ItemStack is = new ItemStack(Material.LEATHER_CHESTPLATE);
    LeatherArmorMeta lmeta = (LeatherArmorMeta)is.getItemMeta();
    lmeta.setDisplayName("§bเลือกทีม §7§o(คลิกขวา)");
    if (color == TEAM_COLOR.GREEN) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §aสีเขียว §7§o(คลิกขวา)");
      lmeta.setColor(Color.LIME);
    }

    if (color == TEAM_COLOR.BLUE) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §bสีฟ้า §7§o(คลิกขวา)");
      lmeta.setColor(Color.AQUA);
    }

    if (color == TEAM_COLOR.YELLOW) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §eสีเหลือง §7§o(คลิกขวา)");
      lmeta.setColor(Color.YELLOW);
    }

    if (color == TEAM_COLOR.RED) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §cสีแดง §7§o(คลิกขวา)");
      lmeta.setColor(Color.RED);
    }

    if (color == TEAM_COLOR.BLACK) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §0ดำ §7§o(คลิกขวา)");
      lmeta.setColor(Color.BLACK);
    }

    if (color == TEAM_COLOR.WHITE) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §fสีขาว §7§o(คลิกขวา)");
      lmeta.setColor(Color.WHITE);
    }

    if (color == TEAM_COLOR.GOLD) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §6สีส้ม §7§o(คลิกขวา)");
      lmeta.setColor(Color.ORANGE);
    }

    if (color == TEAM_COLOR.LIGHT_PURPLE) {
      lmeta.setDisplayName("§bตอนนี้คุณอยู่ทีม §dชมพู §7§o(คลิกขวา)");
      lmeta.setColor(Color.PURPLE);
    }

    is.setItemMeta(lmeta);
    return is;
  }

  public static ItemStack getKitSelecterItem() {
    ItemStack is = new ItemStack(Material.ENDER_CHEST);
    ItemMeta im = is.getItemMeta();
    im.setDisplayName("§bเลือกชุดรูปแบบ §7§o(คลิกขวา)");
    is.setItemMeta(im);
    return is;
  }

  public static ItemStack getRoomSettingsItem() {
    ItemStack is = new ItemStack(Material.PAPER);
    ItemMeta im = is.getItemMeta();
    im.setDisplayName("§bตั้งค่าห้อง §7§o(คลิกขวา)");
    im.setLore(Arrays.asList("§r", "§fหรือจะสามารถใช้คำสั่ง §a!mp settings §fก็ได้"));
    is.setItemMeta(im);
    evt.host_afk_timer = 0;
    return is;
  }

  public static void openMapSettings(Player p) {
    Inventory inv = Bukkit.createInventory((InventoryHolder)null, 18, "§bเลือกแมพ");
    ItemStack rand_item = new ItemStack(Material.MAP);
    ItemMeta rand_item_im = rand_item.getItemMeta();
    rand_item_im.setDisplayName("§b#สุ่มแมพ#");
    if (random_map) {
      rand_item_im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      rand_item_im.addEnchant(Enchantment.SILK_TOUCH, 1, true);
      rand_item_im.setLore(Arrays.asList("§r", "§aกำลังเลือกอยู่ในขณะนี้"));
    }

    rand_item.setItemMeta(rand_item_im);
    inv.addItem(new ItemStack[]{rand_item});
    File folder = null;
    if (current_game.equals(game.MINIGAMES.SPLEEF)) {
      folder = new File(core.sv_path + File.separator + "maps", "Super Spleef");
    }

    if (current_game.equals(game.MINIGAMES.TNTTAG)) {
      folder = new File(core.sv_path + File.separator + "maps", "TNTTAG");
    }

    if (current_game.equals(game.MINIGAMES.BEDWARS)) {
      folder = new File(core.sv_path + File.separator + "maps", "Bedwars");
    }

    if (current_game.equals(game.MINIGAMES.TNTRUN)) {
      folder = new File(core.sv_path + File.separator + "maps", "TNTRUN");
    }

    if (!current_game.equals(game.MINIGAMES.EMPTY) && folder != null) {
      File[] listOfFiles = folder.listFiles();

      for(int i = 0; i < listOfFiles.length; ++i) {
        ItemStack is = new ItemStack(Material.EMPTY_MAP);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§b" + listOfFiles[i].getName().replaceAll(".zip", ""));
        if (!random_map && force_map.equals(listOfFiles[i].getName().replaceFirst(".zip", ""))) {
          im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
          im.addEnchant(Enchantment.SILK_TOUCH, 1, true);
          im.setLore(Arrays.asList("§r", "§aกำลังเลือกอยู่ในขณะนี้"));
        }

        is.setItemMeta(im);
        inv.addItem(new ItemStack[]{is});
      }

      p.openInventory(inv);
      evt.host_afk_timer = 0;
    } else {
      p.openInventory(inv);
    }
  }

  public static void setOUT(Player p) {
    DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
    p.setHealth(20.0D);
    p.getInventory().clear();
    DeluxePlayer.getDeluxePlayer(p).setPlayerState(game.PlayerState.Out);
    starchaser.BoardCastMsg("§7OUT: §c" + p.getName());
    p.setGameMode(GameMode.SPECTATOR);
    p.teleport((Location)game_team.getTeam(dp.getPlayer_team().toString()).getTeamSpawnLocation().get(0));
  }

  public static void openGameInv(Player p) {
    Inventory inv = Bukkit.createInventory((InventoryHolder)null, 18, "§bเลือกเกม");
    game.MINIGAMES[] var2 = game.MINIGAMES.values();
    int var3 = var2.length;

    for(int var4 = 0; var4 < var3; ++var4) {
      game.MINIGAMES mg = var2[var4];
      if (!mg.equals(game.MINIGAMES.EMPTY)) {
        ItemStack item = new ItemStack(Material.BEDROCK);
        if (mg.equals(game.MINIGAMES.SPLEEF)) {
          if (!me.starchaser.deluxe.games.spleef.game.show_in_lobby) {
            continue;
          }

          item = me.starchaser.deluxe.games.spleef.game.item_icon;
        }

        if (mg.equals(game.MINIGAMES.BEDWARS)) {
          if (!me.starchaser.deluxe.games.bedwars.game.show_in_lobby) {
            continue;
          }

          item = me.starchaser.deluxe.games.bedwars.game.item_icon;
        }

        if (mg.equals(game.MINIGAMES.TNTTAG)) {
          if (!me.starchaser.deluxe.games.tnttag.game.show_in_lobby) {
            continue;
          }

          item = me.starchaser.deluxe.games.tnttag.game.item_icon;
        }

        if (mg.equals(game.MINIGAMES.TNTRUN)) {
          if (!me.starchaser.deluxe.games.tntrun.game.show_in_lobby) {
            continue;
          }

          item = me.starchaser.deluxe.games.tntrun.game.item_icon;
        }

        if (mg.equals(game.MINIGAMES.UHC)) {
          if (!me.starchaser.deluxe.games.uhc.game.show_in_lobby) {
            continue;
          }

          item = me.starchaser.deluxe.games.uhc.game.item_icon;
        }

        if (mg.equals(game.MINIGAMES.BLOCKHUNT)) {
          if (!me.starchaser.deluxe.games.blockhunt.game.show_in_lobby) {
            continue;
          }

          item = me.starchaser.deluxe.games.blockhunt.game.item_icon;
        }

        ItemMeta item_im = item.getItemMeta();
        if (current_game.equals(mg)) {
          item_im.setDisplayName("§a§l" + mg.toString());
          item_im.setLore(Arrays.asList("§r", "§fกำลังเลือกเกมนี้อยู่แล้ว"));
          item_im.addEnchant(Enchantment.SILK_TOUCH, 1, true);
          item_im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        } else {
          item_im.removeEnchant(Enchantment.SILK_TOUCH);
          item_im.setDisplayName("§f§l" + mg.toString());
          item_im.setLore(Arrays.asList("§r", "§aคลิกเพื่อเลือกเกมนี้"));
        }

        if (mg.equals(game.MINIGAMES.BEDWARS)) {
          item_im.getLore().add("§r");
          item_im.getLore().add("§bระบบและไอเทมยังทำไม่เสร็จดี ระบบอาจจะยังไม่สเถียร และไอเทมพิเศษจะยังใช้ไม่ได้!!");
        }

        item.setItemMeta(item_im);
        inv.addItem(new ItemStack[]{item});
        p.openInventory(inv);
      }
    }

  }

  public static void openKickPlayerInv(Player p) {
    Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bเชิญผู้เล่นออก");
    Iterator var2 = Bukkit.getOnlinePlayers().iterator();

    while(var2.hasNext()) {
      Player kick_pl = (Player)var2.next();
      ItemStack kick_item = new ItemStack(Material.TNT);
      ItemMeta kick_item_im = kick_item.getItemMeta();
      kick_item_im.setDisplayName(kick_pl.getName());
      kick_item_im.setLore(Arrays.asList("§r", "§cคลิกเพื่อเชิญผู้เล่นนี้ออก"));
      if (host.equals(DeluxePlayer.getDeluxePlayer(kick_pl))) {
        kick_item_im.setLore(Arrays.asList("§r", "§aคนนี้คือคุณ คุณไม่สามารถเชิญตนเองออกได้!"));
        kick_item_im.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        kick_item_im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      }

      kick_item.setItemMeta(kick_item_im);
      inv.addItem(new ItemStack[]{kick_item});
    }

    p.openInventory(inv);
  }

  public static void openHostChangeInv(Player p) {
    Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bโอนสิทธ์หัวหน้าห้อง");
    Iterator var2 = core.PlayerRef.iterator();

    while(var2.hasNext()) {
      DeluxePlayer host_pl = (DeluxePlayer)var2.next();
      ItemStack host_item = new ItemStack(Material.FEATHER);
      ItemMeta host_item_im = host_item.getItemMeta();
      host_item_im.setDisplayName(host_pl.getName());
      host_item_im.setLore(Arrays.asList("§r", "§eคลิกเพื่อโอนสิทธ์หัวห้องทันที"));
      if (host.equals(host_pl)) {
        host_item_im.setLore(Arrays.asList("§r", "§aคนนี้คือคุณ ซึ่งคุณเป็นหัวห้องอยู่แล้ว!"));
        host_item_im.addEnchant(Enchantment.SILK_TOUCH, 1, true);
        host_item_im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      }

      host_item.setItemMeta(host_item_im);
      inv.addItem(new ItemStack[]{host_item});
    }

    p.openInventory(inv);
  }

  public static void openRoomSettings(Player p) {
    Inventory inv = Bukkit.createInventory((InventoryHolder)null, 9, "§bตั้งค่าห้อง");
    ItemStack map = new ItemStack(Material.MAP);
    ItemMeta map_im = map.getItemMeta();
    map_im.setDisplayName("§bเลือกแมพ");
    if (random_map) {
      map_im.setLore(Arrays.asList("§r", "§bแมพที่เลือกอยู่ตอนนี้:", "§f§o#สุ่ม#"));
    } else {
      map_im.setLore(Arrays.asList("§r", "§bแมพที่เลือกอยู่ตอนนี้:", "§f§o" + force_map));
    }

    map.setItemMeta(map_im);
    ItemStack wool;
    ItemMeta wool_im;
    if (!starchaser.AutoMinigames) {
      wool = new ItemStack(Material.FIREWORK);
      wool_im = wool.getItemMeta();
      wool_im.setDisplayName("§bเลือกเกม");
      wool_im.setLore(Arrays.asList("§r", "§bเกมที่เลือกตอนนี้:", "§f" + current_game.toString().toLowerCase()));
      wool.setItemMeta(wool_im);
      inv.setItem(0, wool);
    }

    if (auto_start) {
      wool = new ItemStack(Material.WOOL, 1, (short)DyeColor.GREEN.getData());
      wool_im = wool.getItemMeta();
      wool_im.setDisplayName("§bเริ่มเกมอัตโนมัติ §aเปิด");
      wool_im.setLore(Arrays.asList("§r", "§bคลิกเพื่อ§cปิด§bการเริ่มเกมอัตโนมัติเมื่อผู้เล่นในล๊อบบี้ถึง §a" + Min_Players + "§b ผู้เล่น"));
      wool.setItemMeta(wool_im);
    } else {
      wool = new ItemStack(Material.WOOL, 1, (short)DyeColor.RED.getData());
      wool_im = wool.getItemMeta();
      wool_im.setDisplayName("§bเริ่มเกมอัตโนมัติ §cปิด");
      wool_im.setLore(Arrays.asList("§r", "§bคลิกเพื่อ§aเปิด§bการเริ่มเกมอัตโนมัติเมื่อผู้เล่นในล๊อบบี้ถึง §a" + Min_Players + "§b ผู้เล่น"));
      wool.setItemMeta(wool_im);
    }

    ItemStack host_change_item = new ItemStack(Material.FEATHER);
    ItemMeta host_change_item_im = host_change_item.getItemMeta();
    host_change_item_im.setDisplayName("§bโอนสิทธ์หัวหน้าห้อง");
    host_change_item_im.setLore(Arrays.asList("§r", "§bหัวหน้าห้องตอนนี้คือ: §f" + host.getName()));
    ItemStack rename_room_item = new ItemStack(Material.BOOK_AND_QUILL);
    ItemMeta im = rename_room_item.getItemMeta();
    im.setDisplayName("§bเปลี่ยนชื่อห้อง");
    im.setLore(Arrays.asList("§r", "§bชื่อห้องในตอนนี้:", "§f" + Server_Name));
    host_change_item.setItemMeta(host_change_item_im);
    rename_room_item.setItemMeta(im);
    ItemStack password_room_item = new ItemStack(Material.REDSTONE);
    ItemMeta password_im = password_room_item.getItemMeta();
    password_im.setDisplayName("§bรหัสผ่านห้อง");
    if (Server_password.equals("#NONE#")) {
      password_im.setLore(Arrays.asList("§r", "§bรหัสของห้องในตอนนี้:", "§f#ไม่ได้ใส่#"));
    } else {
      password_im.setLore(Arrays.asList("§r", "§bรหัสของห้องในตอนนี้:", "§f" + Server_password));
    }

    password_room_item.setItemMeta(password_im);
    ItemStack kick_player_item = new ItemStack(Material.TNT);
    ItemMeta kick_player_item_im = kick_player_item.getItemMeta();
    kick_player_item_im.setDisplayName("§bเชิญผู้เล่นออก");
    kick_player_item_im.setLore(Arrays.asList("§r", "§bมีผู้เล่นรวมคุณแล้วทั้งหมด: §a" + Bukkit.getOnlinePlayers().size() + " §bผู้เล่น ในขณะนี้"));
    kick_player_item.setItemMeta(kick_player_item_im);
    ItemStack chat_disable_item = new ItemStack(Material.NOTE_BLOCK);
    ItemMeta mt;
    if (!chat_allow_on_game_live) {
      chat_disable_item = new ItemStack(Material.BEDROCK);
      mt = chat_disable_item.getItemMeta();
      mt.setDisplayName("§aเปิด §aการแชทในระหว่างการเล่นเกม");
      mt.setLore(Arrays.asList("§r", "§aการแชทในระหว่างการเล่นเกมถูกปิดอยู่ คลิกที่นี้เพื่อเปิด"));
      chat_disable_item.setItemMeta(mt);
    } else {
      mt = chat_disable_item.getItemMeta();
      mt.setDisplayName("§cปิด §aการแชทในระหว่างการเล่นเกม");
      mt.setLore(Arrays.asList("§r", "§aการแชทในระหว่างการเล่นเกมถูกเปิดอยู่ คลิกที่นี้เพื่อปิด"));
      chat_disable_item.setItemMeta(mt);
    }

    inv.setItem(1, map);
    inv.setItem(3, chat_disable_item);
    inv.setItem(4, rename_room_item);
    inv.setItem(5, password_room_item);
    inv.setItem(7, kick_player_item);
    inv.setItem(8, host_change_item);
    inv.setItem(6, wool);
    p.openInventory(inv);
  }

  public static void openTeamSelecterInv(Player p) {
    byte size;
    if (game_map.getTeams().getStringTeamsList().size() > 9) {
      size = 18;
    } else {
      size = 9;
    }

    Inventory inv = Bukkit.createInventory((InventoryHolder)null, size, "§bเลือกทีม");
    Iterator var3 = game_map.getTeams().getStringTeamsList().iterator();

    while(true) {
      TEAM_COLOR team;
      Wool wool;
      ItemStack is;
      ItemMeta im;
      ArrayList lore;
      Iterator var10;
      Player pp;
      do {
        if (!var3.hasNext()) {
          p.openInventory(inv);
          return;
        }

        String str_team = (String)var3.next();
        team = starchaser.StringToTeamColor(str_team);
        if (team.equals(TEAM_COLOR.GREEN)) {
          wool = new Wool(DyeColor.GREEN);
          is = new ItemStack(Material.WOOL);
          is.setDurability((short)wool.getData());
          im = is.getItemMeta();
          im.setDisplayName("§aสีเขียว");
          lore = new ArrayList();
          lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
          var10 = Bukkit.getOnlinePlayers().iterator();

          while(var10.hasNext()) {
            pp = (Player)var10.next();
            if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.GREEN)) {
              lore.add(pp.getName());
            }
          }

          im.setLore(lore);
          is.setItemMeta(im);
          inv.addItem(new ItemStack[]{is});
        }

        if (team.equals(TEAM_COLOR.RED)) {
          wool = new Wool(DyeColor.RED);
          is = new ItemStack(Material.WOOL);
          is.setDurability((short)wool.getData());
          im = is.getItemMeta();
          im.setDisplayName("§cสีแดง");
          lore = new ArrayList();
          lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
          var10 = Bukkit.getOnlinePlayers().iterator();

          while(var10.hasNext()) {
            pp = (Player)var10.next();
            if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.RED)) {
              lore.add(pp.getName());
            }
          }

          im.setLore(lore);
          is.setItemMeta(im);
          inv.addItem(new ItemStack[]{is});
        }

        if (team.equals(TEAM_COLOR.YELLOW)) {
          wool = new Wool(DyeColor.YELLOW);
          is = new ItemStack(Material.WOOL);
          is.setDurability((short)wool.getData());
          im = is.getItemMeta();
          im.setDisplayName("§eสีเหลือง");
          lore = new ArrayList();
          lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
          var10 = Bukkit.getOnlinePlayers().iterator();

          while(var10.hasNext()) {
            pp = (Player)var10.next();
            if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.YELLOW)) {
              lore.add(pp.getName());
            }
          }

          im.setLore(lore);
          is.setItemMeta(im);
          inv.addItem(new ItemStack[]{is});
        }

        if (team.equals(TEAM_COLOR.BLUE)) {
          wool = new Wool(DyeColor.BLUE);
          is = new ItemStack(Material.WOOL);
          is.setDurability((short)wool.getData());
          im = is.getItemMeta();
          im.setDisplayName("§bสีฟ้า");
          lore = new ArrayList();
          lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
          var10 = Bukkit.getOnlinePlayers().iterator();

          while(var10.hasNext()) {
            pp = (Player)var10.next();
            if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.BLUE)) {
              lore.add(pp.getName());
            }
          }

          im.setLore(lore);
          is.setItemMeta(im);
          inv.addItem(new ItemStack[]{is});
        }

        if (team.equals(TEAM_COLOR.LIGHT_PURPLE)) {
          wool = new Wool(DyeColor.MAGENTA);
          is = new ItemStack(Material.WOOL);
          is.setDurability((short)wool.getData());
          im = is.getItemMeta();
          im.setDisplayName("§bสีชมพู");
          lore = new ArrayList();
          lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
          var10 = Bukkit.getOnlinePlayers().iterator();

          while(var10.hasNext()) {
            pp = (Player)var10.next();
            if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.LIGHT_PURPLE)) {
              lore.add(pp.getName());
            }
          }

          im.setLore(lore);
          is.setItemMeta(im);
          inv.addItem(new ItemStack[]{is});
        }

        if (team.equals(TEAM_COLOR.GOLD)) {
          wool = new Wool(DyeColor.ORANGE);
          is = new ItemStack(Material.WOOL);
          is.setDurability((short)wool.getData());
          im = is.getItemMeta();
          im.setDisplayName("§bส้ม");
          lore = new ArrayList();
          lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
          var10 = Bukkit.getOnlinePlayers().iterator();

          while(var10.hasNext()) {
            pp = (Player)var10.next();
            if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.GOLD)) {
              lore.add(pp.getName());
            }
          }

          im.setLore(lore);
          is.setItemMeta(im);
          inv.addItem(new ItemStack[]{is});
        }

        if (team.equals(TEAM_COLOR.WHITE)) {
          wool = new Wool(DyeColor.WHITE);
          is = new ItemStack(Material.WOOL);
          is.setDurability((short)wool.getData());
          im = is.getItemMeta();
          im.setDisplayName("§bสีขาว");
          lore = new ArrayList();
          lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
          var10 = Bukkit.getOnlinePlayers().iterator();

          while(var10.hasNext()) {
            pp = (Player)var10.next();
            if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.WHITE)) {
              lore.add(pp.getName());
            }
          }

          im.setLore(lore);
          is.setItemMeta(im);
          inv.addItem(new ItemStack[]{is});
        }
      } while(!team.equals(TEAM_COLOR.BLACK));

      wool = new Wool(DyeColor.BLACK);
      is = new ItemStack(Material.WOOL);
      is.setDurability((short)wool.getData());
      im = is.getItemMeta();
      im.setDisplayName("§bดำ");
      lore = new ArrayList();
      lore.addAll(Arrays.asList("§r", team.toString(), "§r"));
      var10 = Bukkit.getOnlinePlayers().iterator();

      while(var10.hasNext()) {
        pp = (Player)var10.next();
        if (DeluxePlayer.getDeluxePlayer(pp).getPlayer_team().equals(TEAM_COLOR.BLACK)) {
          lore.add(pp.getName());
        }
      }

      im.setLore(lore);
      is.setItemMeta(im);
      inv.addItem(new ItemStack[]{is});
    }
  }

  public static void SetGame(game.MINIGAMES game) {
    if (game.equals(me.starchaser.deluxe.games.game.MINIGAMES.SPLEEF)) {
      me.starchaser.deluxe.games.spleef.game.Select();
    }

    if (game.equals(me.starchaser.deluxe.games.game.MINIGAMES.TNTTAG)) {
      me.starchaser.deluxe.games.tnttag.game.Select();
    }

    if (game.equals(me.starchaser.deluxe.games.game.MINIGAMES.TNTRUN)) {
      me.starchaser.deluxe.games.tntrun.game.Select();
    }

    if (game.equals(me.starchaser.deluxe.games.game.MINIGAMES.BEDWARS)) {
      me.starchaser.deluxe.games.bedwars.game.Select();
    }

    if (game.equals(me.starchaser.deluxe.games.game.MINIGAMES.UHC)) {
      me.starchaser.deluxe.games.uhc.game.Select();
    }

    if (game.equals(me.starchaser.deluxe.games.game.MINIGAMES.BLOCKHUNT)) {
      me.starchaser.deluxe.games.blockhunt.game.Select();
    }

  }

  public static void reload_game() {
    World world = Bukkit.getWorld("GAME_" + game_rand + current_game.toString().toUpperCase());
    if (world != null) {
      starchaser.unloadWorld(world);
    }

    try {
      starchaser.deleteWorld(new File(core.sv_path + "GAME_" + game_rand + current_game.toString().toUpperCase()));
    } catch (Exception var3) {
      ;
    }

    SetGame(current_game);
    Iterator var1 = Bukkit.getOnlinePlayers().iterator();

    while(var1.hasNext()) {
      Player p = (Player)var1.next();
      p.getInventory().clear();
      DeluxePlayer.getDeluxePlayer(p).setPlayer_team(TEAM_COLOR.NONE);
      give_looby_item(p);
    }

  }

  public static void game_end_task(String winer_str) {
    if (winer_str != null) {
      Player player = Bukkit.getPlayerExact(winer_str);
      if (player != null) {
        getPlayerinPlayerXPDB(player.getName()).addKey("ชนะ");
      }
    }

    if (!chat_allow_on_game_live) {
      starchaser.BoardCastMsg("§7Chat: §aแชทสามารถใช้ได้แล้วในขณะนี้");
    }

    (new BukkitRunnable() {
      public void run() {
        game.gameState = game.GameState.Recruit;
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while(var1.hasNext()) {
          Player p = (Player)var1.next();
          p.teleport(new Location(Bukkit.getWorld("Main_lobby"), -4.0D, 202.0D, 3.0D));
          p.setHealth(20.0D);
          p.setFoodLevel(20);
          p.setFireTicks(0);
          p.setLevel(0);
          p.setWalkSpeed(0.2F);
          Iterator var3 = p.getActivePotionEffects().iterator();

          while(var3.hasNext()) {
            PotionEffect effect = (PotionEffect)var3.next();
            p.removePotionEffect(effect.getType());
          }

          game.ClearINV(p);
          p.getInventory().setHeldItemSlot(0);
          p.setGameMode(GameMode.ADVENTURE);
          p.getInventory().setHelmet(new ItemStack(Material.AIR));
          p.getInventory().setChestplate(new ItemStack(Material.AIR));
          p.getInventory().setLeggings(new ItemStack(Material.AIR));
          p.getInventory().setBoots(new ItemStack(Material.AIR));
          game.give_looby_item(p);
          game.givexp_result(p, game.current_game);
          var3 = Bukkit.getOnlinePlayers().iterator();

          while(var3.hasNext()) {
            Player p2 = (Player)var3.next();
            p.showPlayer(p2);
          }
        }

        if (game.auto_start && Bukkit.getOnlinePlayers().size() >= game.Min_Players) {
          game.getPrepareCountdown().runTaskTimer(core.getDeluxe, 20L, 20L);
        }

        World world = Bukkit.getWorld("GAME_" + game.game_rand + game.current_game.toString().toUpperCase());

        try {
          starchaser.unloadWorld(world);
          starchaser.deleteWorld(world.getWorldFolder());
        } catch (Exception var5) {
          ;
        }

        game.reload_game();
        this.cancel();
      }
    }).runTaskTimer(core.getDeluxe, 200L, 10L);
  }

  public static void unzip(String source, String destination) {
    try {
      File directory = new File(destination);
      starchaser.Logger(LOG_TYPE.DEBUG, "first creating directory " + destination);
      boolean resu = false;
      if (!directory.exists()) {
        resu = directory.mkdir();
      }

      starchaser.Logger(LOG_TYPE.DEBUG, "first creating directory " + destination + " result " + Boolean.toString(resu));
      ZipInputStream zip = new ZipInputStream(new FileInputStream(source));
      byte[] buffer = new byte[1024];
      ZipEntry zipEntry = zip.getNextEntry();

      while(true) {
        while(zipEntry != null) {
          String fileName = zipEntry.getName();
          File newFile = new File(destination, fileName);
          if (zipEntry.isDirectory()) {
            newFile.mkdir();
            zipEntry = zip.getNextEntry();
          } else {
            if (!newFile.exists()) {
              newFile.getParentFile().mkdirs();
              starchaser.Logger(LOG_TYPE.DEBUG, "creating directory " + zipEntry.getName());
            }

            FileOutputStream fos = new FileOutputStream(newFile);
            if (!zipEntry.getName().equalsIgnoreCase("session.lock") && !zipEntry.getName().equalsIgnoreCase("uuid.dat")) {
              starchaser.Logger(LOG_TYPE.DEBUG, "creating file " + zipEntry.getName());

              int len;
              while((len = zip.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
              }

              fos.flush();
              fos.close();
              zip.closeEntry();
              zipEntry = zip.getNextEntry();
            } else {
              starchaser.Logger(LOG_TYPE.DEBUG, "Skip " + zipEntry.getName());
              zipEntry = zip.getNextEntry();
            }
          }
        }

        zip.closeEntry();
        zip.close();
        break;
      }
    } catch (Exception var11) {
      var11.printStackTrace();
      starchaser.Logger(LOG_TYPE.GAME, "Error! on extract map files " + source + " to " + destination);
    }

  }

  public static void ClearMonster(World world) {
    Iterator var1 = world.getEntities().iterator();

    while(true) {
      Entity en;
      do {
        do {
          do {
            do {
              do {
                if (!var1.hasNext()) {
                  return;
                }

                en = (Entity)var1.next();
              } while(en.getType().equals(EntityType.VILLAGER) && current_game == game.MINIGAMES.BEDWARS);
            } while(en.getType().equals(EntityType.ARMOR_STAND));
          } while(en instanceof ArmorStand);
        } while(en instanceof Player);
      } while(en instanceof Item && current_game == game.MINIGAMES.TNTTAG);

      if (!HologramsAPI.isHologramEntity(en)) {
        en.remove();
      }
    }
  }

  public static void ClearINV(Player p) {
    p.getInventory().clear();
    p.getInventory().setBoots(new ItemStack(Material.AIR));
    p.getInventory().setLeggings(new ItemStack(Material.AIR));
    p.getInventory().setHelmet(new ItemStack(Material.AIR));
    p.getInventory().setChestplate(new ItemStack(Material.AIR));
  }

  public static BukkitRunnable getPrepareCountdown() {
    return new BukkitRunnable() {
      public void run() {
        Iterator var1;
        Player p;
        if (game.gameState == game.GameState.Recruit && !game.auto_start && !game.force_start) {
          var1 = Bukkit.getOnlinePlayers().iterator();

          while(var1.hasNext()) {
            p = (Player)var1.next();
            if (game.host == DeluxePlayer.getDeluxePlayer(p)) {
              p.getInventory().setItem(3, game.getForceStartItem(1));
            } else {
              p.getInventory().setItem(3, new ItemStack(Material.AIR));
            }
          }

          this.cancel();
        } else {
          --game.time_prepare;
          game.gameState = game.GameState.Prepare;
          if (game.current_game.equals(game.MINIGAMES.EMPTY)) {
            starchaser.BoardCastMsg("§7Game: §cไม่สามารถเริ่มเกมได้เนื่องจาก เกมที่กำลังเลือกอยู่ในสถานะว่างเปล่า");
            game.time_prepare = 61;
            game.gameState = game.GameState.Recruit;
            game.force_start = false;
            var1 = Bukkit.getOnlinePlayers().iterator();

            while(var1.hasNext()) {
              p = (Player)var1.next();
              if (game.host == DeluxePlayer.getDeluxePlayer(p)) {
                p.getInventory().setItem(3, game.getForceStartItem(1));
              } else {
                p.getInventory().setItem(3, new ItemStack(Material.AIR));
              }
            }

            this.cancel();
          } else if (Bukkit.getOnlinePlayers().size() > game.Max_Players) {
            starchaser.BoardCastMsg("§7Game: §cไม่สามารถเริ่มเกมได้เนื่องจาก จำนวนเกินกว่าที่จะรับได้");
            game.time_prepare = 61;
            game.gameState = game.GameState.Recruit;
            game.force_start = false;
            var1 = Bukkit.getOnlinePlayers().iterator();

            while(var1.hasNext()) {
              p = (Player)var1.next();
              if (game.host == DeluxePlayer.getDeluxePlayer(p)) {
                p.getInventory().setItem(3, game.getForceStartItem(1));
              } else {
                p.getInventory().setItem(3, new ItemStack(Material.AIR));
              }
            }

            this.cancel();
          } else if ((Bukkit.getOnlinePlayers().size() >= game.Min_Players || game.force_start) && Bukkit.getOnlinePlayers().size() >= 2) {
            if (game.time_prepare == 60) {
              starchaser.BoardCastMsg("§7Game: §aเกมกำลังจะเริ่มต้นในอีก 60 วินาที...");
              var1 = Bukkit.getOnlinePlayers().iterator();

              while(var1.hasNext()) {
                p = (Player)var1.next();
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 3.0F);
              }
            }

            if (game.time_prepare == 30) {
              starchaser.BoardCastMsg("§7Game: §aเกมกำลังจะเริ่มต้นในอีก 30 วินาที...");
              var1 = Bukkit.getOnlinePlayers().iterator();

              while(var1.hasNext()) {
                p = (Player)var1.next();
                p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 5.0F);
              }
            }

            if (game.time_prepare > 15) {
              var1 = Bukkit.getOnlinePlayers().iterator();

              while(var1.hasNext()) {
                p = (Player)var1.next();
                if (game.host == DeluxePlayer.getDeluxePlayer(p)) {
                  p.getInventory().setItem(3, game.getForceStartItem(game.time_prepare));
                } else {
                  p.getInventory().setItem(3, new ItemStack(Material.AIR));
                }
              }
            } else {
              var1 = Bukkit.getOnlinePlayers().iterator();

              while(var1.hasNext()) {
                p = (Player)var1.next();
                p.getInventory().setItem(3, new ItemStack(Material.AIR));
              }
            }

            if (game.time_prepare == 10) {
              starchaser.BoardCastMsg("§7Game: §aเกมกำลังจะเริ่มต้นในอีก 10 วินาที...");
              var1 = Bukkit.getOnlinePlayers().iterator();

              while(var1.hasNext()) {
                p = (Player)var1.next();
                p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 5.0F);
              }
            }

            if (game.time_prepare <= 5 && game.time_prepare > 0) {
              starchaser.BoardCastMsg("§7Game: §aเกมกำลังจะเริ่มต้นในอีก " + game.time_prepare + " วินาที...");
              var1 = Bukkit.getOnlinePlayers().iterator();

              while(var1.hasNext()) {
                p = (Player)var1.next();
                p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 5.0F);
              }
            }

            if (game.time_prepare < 1) {
              starchaser.BoardCastMsg("§7Game: §aกำลังเริ่มเกมโปรดรอสักครู่...");
              WorldCreator world = new WorldCreator("GAME_" + game.game_rand + game.current_game.toString().toUpperCase());
              world.generator("DeluxeCore4");
              world.createWorld();
              starchaser.Logger(LOG_TYPE.GAME, "world name GAME_" + game.game_rand + game.current_game.toString().toUpperCase() + " loaded!");
              HashMap<TEAM_COLOR, Integer> spawnpoint_count = new HashMap();
              World w = Bukkit.getWorld("GAME_" + game.game_rand + game.current_game.toString().toUpperCase());
              if (game.max_x == 0 && game.max_z == 0) {
                game.max_x = (int)((Location)game.game_map.getTeams().getTeam((String)game.game_map.getTeams().getStringTeamsList().get(0)).getTeamSpawnLocation().get(0)).getX();
                game.min_z = (int)((Location)game.game_map.getTeams().getTeam((String)game.game_map.getTeams().getStringTeamsList().get(0)).getTeamSpawnLocation().get(0)).getZ();
              }

              Location loc = new Location(w, (double)game.max_x, 0.0D, (double)game.min_z);
              int border_size = game.game_map.getBorder_size();
              if (border_size < 2) {
                border_size = 1000;
              }

              w.getWorldBorder().setSize((double)border_size);
              w.getWorldBorder().setCenter(loc);
              starchaser.Logger(LOG_TYPE.GAME, "SETTING BORDER CENTER TO X:" + w.getWorldBorder().getCenter().getX() + " Z:" + w.getWorldBorder().getCenter().getZ());
              starchaser.Logger(LOG_TYPE.GAME, "SETTING BORDER SIZE TO: " + w.getWorldBorder().getSize());
              Iterator var6 = Bukkit.getOnlinePlayers().iterator();

              label263:
              while(var6.hasNext()) {
                Player px = (Player)var6.next();
                px.getInventory().clear();
                px.playSound(px.getLocation(), Sound.LEVEL_UP, 1.0F, 3.0F);
                px.setGameMode(GameMode.SURVIVAL);
                DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(px);
                int no_team_count = 0;
                Iterator var10 = Bukkit.getOnlinePlayers().iterator();

                while(true) {
                  Player pp;
                  do {
                    if (!var10.hasNext()) {
                      ArrayList team_loc;
                      int rand_teamid;
                      if (game.current_game != game.MINIGAMES.BLOCKHUNT) {
                        if (game.game_team.getStringTeamsList().size() <= 1) {
                          var10 = Bukkit.getOnlinePlayers().iterator();

                          while(var10.hasNext()) {
                            pp = (Player)var10.next();
                            DeluxePlayer.getDeluxePlayer(pp).setPlayer_team(starchaser.StringToTeamColor((String)game.game_team.getStringTeamsList().get(0)));
                          }
                        } else {
                          label249:
                          while(no_team_count >= 1) {
                            no_team_count = 0;
                            var10 = Bukkit.getOnlinePlayers().iterator();

                            while(true) {
                              do {
                                if (!var10.hasNext()) {
                                  var10 = Bukkit.getOnlinePlayers().iterator();

                                  while(true) {
                                    DeluxePlayer dp2;
                                    do {
                                      if (!var10.hasNext()) {
                                        continue label249;
                                      }

                                      pp = (Player)var10.next();
                                      dp2 = DeluxePlayer.getDeluxePlayer(pp);
                                    } while(dp2.getPlayer_team() != null && dp2.getPlayer_team() != TEAM_COLOR.NONE);

                                    rand_teamid = (new Random()).nextInt(game.game_team.getStringTeamsList().size());
                                    DeluxeTeam teamx = game.game_team.getTeam((String)game.game_team.getStringTeamsList().get(rand_teamid));
                                    if (teamx.size() < game.player_per_team) {
                                      dp2.setPlayer_team(starchaser.StringToTeamColor(teamx.getTeam_name()));
                                      pp.sendMessage("§7Team: §aคุณได้เข้ามร่วมทีม " + teamx.getColorCode() + teamx.getTeam_name());
                                    }
                                  }
                                }

                                pp = (Player)var10.next();
                              } while(DeluxePlayer.getDeluxePlayer(pp).getPlayer_team() != null && DeluxePlayer.getDeluxePlayer(pp).getPlayer_team() != TEAM_COLOR.NONE);

                              ++no_team_count;
                            }
                          }
                        }
                      } else {
                        int hunt_count_max = Bukkit.getOnlinePlayers().size() / 5 + 1;
                        if (hunt_count_max < 1) {
                          hunt_count_max = 1;
                        }

                        int current_hunt = game.game_map.getTeams().getTeam(TEAM_COLOR.HUNT.toString()).size();

                        label260:
                        while(true) {
                          if (current_hunt >= hunt_count_max) {
                            Iterator var23 = Bukkit.getOnlinePlayers().iterator();

                            while(true) {
                              if (!var23.hasNext()) {
                                break label260;
                              }

                              Player player = (Player)var23.next();
                              if (DeluxePlayer.getDeluxePlayer(player).getPlayer_team() == TEAM_COLOR.NONE) {
                                DeluxePlayer.getDeluxePlayer(player).setPlayer_team(TEAM_COLOR.BLOCK);
                              }
                            }
                          }

                          current_hunt = game.game_map.getTeams().getTeam(TEAM_COLOR.HUNT.toString()).size();
                          team_loc = new ArrayList();
                          Iterator var13 = Bukkit.getOnlinePlayers().iterator();

                          Player picked;
                          while(var13.hasNext()) {
                            picked = (Player)var13.next();
                            team_loc.add(picked);
                          }

                          rand_teamid = (new Random()).nextInt(team_loc.size());
                          picked = (Player)team_loc.get(rand_teamid);
                          if (DeluxePlayer.getDeluxePlayer(picked).getPlayer_team() != TEAM_COLOR.HUNT) {
                            DeluxePlayer.getDeluxePlayer(picked).setPlayer_team(TEAM_COLOR.HUNT);
                          }
                        }
                      }

                      if (spawnpoint_count.get(dp.getPlayer_team()) == null) {
                        spawnpoint_count.put(dp.getPlayer_team(), 0);
                      } else {
                        try {
                          spawnpoint_count.replace(dp.getPlayer_team(), (Integer)spawnpoint_count.get(dp.getPlayer_team()) + 1);
                        } catch (IndexOutOfBoundsException var17) {
                          spawnpoint_count.replace(dp.getPlayer_team(), 0);
                        }
                      }

                      String team_str = dp.getPlayer_team().toString().toUpperCase();
                      DeluxeTeam team = game.game_team.getTeam(team_str);
                      team_loc = team.getTeamSpawnLocation();
                      rand_teamid = (Integer)spawnpoint_count.get(dp.getPlayer_team());

                      Location loca;
                      try {
                        loca = (Location)team_loc.get(rand_teamid);
                      } catch (Exception var16) {
                        loca = (Location)team_loc.get(0);
                        spawnpoint_count.replace(dp.getPlayer_team(), 0);
                      }

                      game.ClearINV(px);
                      px.teleport(loca);
                      continue label263;
                    }

                    pp = (Player)var10.next();
                  } while(DeluxePlayer.getDeluxePlayer(pp).getPlayer_team() != null && DeluxePlayer.getDeluxePlayer(pp).getPlayer_team() != TEAM_COLOR.NONE);

                  ++no_team_count;
                }
              }

              if (!game.chat_allow_on_game_live) {
                starchaser.BoardCastMsg("§7Chat: §cแชทไม่สามารถใช้ได้ในขณะนี้");
              }

              game.playerXPDB_data = new ArrayList();
              if (game.current_game == game.MINIGAMES.SPLEEF) {
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                starchaser.BoardCastMsg("§aเกมส์ที่เลือกเล่น §7- §f§lSpleef Game");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§7▪ §fทำลาย §cบล้อค §fโดยการโจมตีที่บล็อก");
                starchaser.BoardCastMsg("§7▪ §fหากคุณไม่ตีบล็อกเป็นระยะเวลานานคุณจะเสียค่าอาหาร");
                starchaser.BoardCastMsg("§7▪ §fผู้ที่มี§a§nชีวิตรอด§fคนสุดท้ายจะได้รับชัยชนะ");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§aเเผนที่ - §f§l" + game.game_map.getMap_name());
                starchaser.BoardCastMsg("      §7ถูกสร้างขึ้นโดย: §e" + game.game_map.getAuthor());
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              }

              if (game.current_game == game.MINIGAMES.BEDWARS) {
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                starchaser.BoardCastMsg("§aเกมส์ที่เลือกเล่น §7- §f§lBedWars Game");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§7▪ §fทำลาย §cเตียง §fของฐานศัตรูเด้วยกลยุธ");
                starchaser.BoardCastMsg("§7▪ §fเมื่อถูกทำลายเตียงจะ §cไม่มี §brespawn §fการเกิดใหม่อีกในรอบนั้น");
                starchaser.BoardCastMsg("§7▪ §fทีมที่มี§a§nชีวิตรอด§fทีมสุดท้ายจะได้รับชัยชนะ");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§aเเผนที่ - §f§l" + game.game_map.getMap_name());
                starchaser.BoardCastMsg("      §7ถูกสร้างขึ้นโดย: §e" + game.game_map.getAuthor());
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              }

              if (game.current_game == game.MINIGAMES.TNTRUN) {
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                starchaser.BoardCastMsg("§aเกมส์ที่เลือกเล่น - §f§lTNTRun Game");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§7▪ §fพยายามอย่า §cตกลงไป §fด้านล่างของสนามเเข่งขัน");
                starchaser.BoardCastMsg("§7▪ §fผู้ที่มี§a§nชีวิตรอด§fคนสุดท้ายจะได้รับชัยชนะ");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§aเเผนที่ - §f§l" + game.game_map.getMap_name());
                starchaser.BoardCastMsg("      §7ถูกสร้างขึ้นโดย: §e" + game.game_map.getAuthor());
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              }

              if (game.current_game == game.MINIGAMES.TNTTAG) {
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                starchaser.BoardCastMsg("§aเกมส์ที่เลือกเล่น - §f§lTNTTag Game");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§7▪  §bผู้รอดชีวิต §e- §fพยายามหลบหนีหรือซ่อนจาก §cมือระเบิด");
                starchaser.BoardCastMsg("§7▪  §bผู้รอดชีวิต §e- §fพยายามอย่าโดนโจมตีจาก §cมือระเบิด");
                starchaser.BoardCastMsg("§7▪  §cมือระเบิด §e- §fพยายามไปโจมตี §bผู้รอดชีวิต §fก่อนที่จะสายเกินไป");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§aเเผนที่ - §f§l" + game.game_map.getMap_name());
                starchaser.BoardCastMsg("      §7ถูกสร้างขึ้นโดย: §e" + game.game_map.getAuthor());
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              }

              if (game.current_game == game.MINIGAMES.BLOCKHUNT) {
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                starchaser.BoardCastMsg("§aเกมส์ที่เลือกเล่น - §f§lBlockHunt Game");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§7▪  §bฝ่ายซ่อน §e- §fหาที่ซ่อนและเนียนเป็นบล๊อค §cอยู่ให้รอดในเวลาที่กำหนด");
                starchaser.BoardCastMsg("§7▪  §cฝ่ายค้นหา §e- §fหาผู้ที่ซ่อนให้ครบ §cภายในเวลาที่กำหนด");
                starchaser.BoardCastMsg(" ");
                starchaser.BoardCastMsg("§aเเผนที่ - §f§l" + game.game_map.getMap_name());
                starchaser.BoardCastMsg("      §7ถูกสร้างขึ้นโดย: §e" + game.game_map.getAuthor());
                starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              }

              BukkitRunnable start = game.gamecooldown();
              start.runTaskTimer(core.getDeluxe, 2L, 2L);
              game.force_start = false;
              game.time_prepare = 61;
              this.cancel();
            }

          } else {
            starchaser.BoardCastMsg("§7Game: §cไม่สามารถเริ่มเกมได้เนื่องจากผู้เล่นไม่เพียงพอ...");
            game.time_prepare = 61;
            game.gameState = game.GameState.Recruit;
            game.force_start = false;
            var1 = Bukkit.getOnlinePlayers().iterator();

            while(var1.hasNext()) {
              p = (Player)var1.next();
              if (game.host == DeluxePlayer.getDeluxePlayer(p)) {
                p.getInventory().setItem(3, game.getForceStartItem(1));
              } else {
                p.getInventory().setItem(3, new ItemStack(Material.AIR));
              }
            }

            this.cancel();
          }
        }
      }
    };
  }

  public static void givexp_result(Player player, game.MINIGAMES minigames) {
    if (!player_xp_grade) {
      player.sendMessage("§7Level: §cไม่สามารถจัดการให้ §aEXP §cได้เนื่องจากมีผู้เล่นเกมล่าสุดในห้องน้อยกว่า §a5 §cคน");
    } else {
      int Total_xp = 0;
      player.sendMessage("§r");
      player.sendMessage("§8§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
      player.sendMessage("§r");
      String Game_Name = "§f§l" + minigames.toString();
      if (minigames.equals(game.MINIGAMES.BEDWARS)) {
        Game_Name = "§f§lBed§c§lwars";
      }

      if (minigames.equals(game.MINIGAMES.SPLEEF)) {
        Game_Name = "§e§lSpleef";
      }

      if (minigames.equals(game.MINIGAMES.TNTTAG)) {
        Game_Name = "§c§lTNT§e§lTag";
      }

      if (minigames.equals(game.MINIGAMES.TNTRUN)) {
        Game_Name = "§c§lTNT§a§lRun";
      }

      player.sendMessage("§aสรุปผลเกมส์ล่าสุด §a§l(§f§l" + Game_Name + "§a§l)");
      player.sendMessage("§r");
      game.PlayerXPDB xpdb;
      Iterator var6;
      String key_kat;
      int count;
      float xp_per_count;
      int sum_xp;
      if (minigames.equals(game.MINIGAMES.BEDWARS)) {
        xpdb = getPlayerinPlayerXPDB(player.getName());

        for(var6 = xpdb.getKeys().iterator(); var6.hasNext(); Total_xp += sum_xp) {
          key_kat = (String)var6.next();
          count = xpdb.getKeyCount(key_kat);
          xp_per_count = 0.0F;
          if (key_kat.contains("ทำลายเตียง")) {
            xp_per_count = 60.0F;
          }

          if (key_kat.contains("ชนะ")) {
            xp_per_count = 250.0F;
          }

          if (key_kat.contains("สร้างความเสียหาย")) {
            xp_per_count = 0.6F;
          }

          if (key_kat.contains("ทุบบล๊อค")) {
            xp_per_count = 0.2F;
          }

          if (key_kat.contains("วางบล๊อค")) {
            xp_per_count = 0.2F;
          }

          if (key_kat.contains("สังหาร")) {
            xp_per_count = 50.0F;
          }

          sum_xp = (int)((float)count * xp_per_count);
          player.sendMessage("§7" + key_kat + ": §b" + count + " §8 | §d+" + sum_xp + " XP");
        }
      }

      if (minigames.equals(game.MINIGAMES.SPLEEF)) {
        xpdb = getPlayerinPlayerXPDB(player.getName());

        for(var6 = xpdb.getKeys().iterator(); var6.hasNext(); Total_xp += sum_xp) {
          key_kat = (String)var6.next();
          count = xpdb.getKeyCount(key_kat);
          xp_per_count = 0.0F;
          if (key_kat.contains("ความยาวนานในการรอดชีวิต")) {
            xp_per_count = 0.4F;
          }

          if (key_kat.contains("ทุบบล๊อค")) {
            xp_per_count = 0.5F;
          }

          if (key_kat.contains("ชนะ")) {
            xp_per_count = 250.0F;
          }

          sum_xp = (int)((float)count * xp_per_count);
          player.sendMessage("§7" + key_kat + ": §b" + count + " §8 | §d+" + sum_xp + " XP");
        }
      }

      if (minigames.equals(game.MINIGAMES.TNTTAG)) {
        xpdb = getPlayerinPlayerXPDB(player.getName());

        for(var6 = xpdb.getKeys().iterator(); var6.hasNext(); Total_xp += sum_xp) {
          key_kat = (String)var6.next();
          count = xpdb.getKeyCount(key_kat);
          xp_per_count = 0.0F;
          if (key_kat.contains("โยนระเบิด")) {
            xp_per_count = 16.0F;
          }

          if (key_kat.contains("ความยาวนานในการรอดชีวิต")) {
            xp_per_count = 0.8F;
          }

          if (key_kat.contains("ชนะ")) {
            xp_per_count = 350.0F;
          }

          sum_xp = (int)((float)count * xp_per_count);
          player.sendMessage("§7" + key_kat + ": §b" + count + " §8 | §d+" + sum_xp + " XP");
        }
      }

      if (minigames.equals(game.MINIGAMES.TNTRUN)) {
        xpdb = getPlayerinPlayerXPDB(player.getName());

        for(var6 = xpdb.getKeys().iterator(); var6.hasNext(); Total_xp += sum_xp) {
          key_kat = (String)var6.next();
          count = xpdb.getKeyCount(key_kat);
          xp_per_count = 0.0F;
          if (key_kat.contains("ระยะทาง")) {
            xp_per_count = 0.9F;
          }

          if (key_kat.contains("ความยาวนานในการรอดชีวิต")) {
            xp_per_count = 0.6F;
          }

          if (key_kat.contains("ชนะ")) {
            xp_per_count = 250.0F;
          }

          sum_xp = (int)((float)count * xp_per_count);
          player.sendMessage("§7" + key_kat + ": §b" + count + " §8 | §d+" + sum_xp + " XP");
        }
      }

      if (minigames.equals(game.MINIGAMES.BLOCKHUNT)) {
        xpdb = getPlayerinPlayerXPDB(player.getName());

        for(var6 = xpdb.getKeys().iterator(); var6.hasNext(); Total_xp += sum_xp) {
          key_kat = (String)var6.next();
          count = xpdb.getKeyCount(key_kat);
          xp_per_count = 0.0F;
          if (key_kat.contains("ชนะ")) {
            xp_per_count = 300.0F;
          }

          if (key_kat.contains("ฆ่า")) {
            xp_per_count = 100.0F;
          }

          if (key_kat.contains("Meow!")) {
            xp_per_count = 15.0F;
          }

          if (key_kat.contains("Fireworks!")) {
            xp_per_count = 35.0F;
          }

          sum_xp = (int)((float)count * xp_per_count);
          player.sendMessage("§7" + key_kat + ": §b" + count + " §8 | §d+" + sum_xp + " XP");
        }
      }

      player.sendMessage("§7จำนวนผู้เล่นที่ร่วมเล่นด้วย: §b" + (many_players_in_last_game - 1) + " §8 | §d+" + (many_players_in_last_game - 1) * 2 + " XP");
      Total_xp += (many_players_in_last_game - 1) * 2;
      int Total_coins = (int)((double)Total_xp * 0.3D);
      player.sendMessage("§r");
      player.sendMessage("§6Total Earned Coins: " + Total_coins + " §e⛁");
      player.sendMessage("§bTotal Earned Exp: " + Total_xp);
      player.sendMessage("§r");
      player.sendMessage("§8§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
      player.sendMessage("§r");
      DeluxePlayer.getDeluxePlayer(player).getLevel().give_xp(Total_xp);
      DeluxePlayer.getDeluxePlayer(player).addCoins(Total_coins);
      player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
    }
  }

  public static game.PlayerXPDB getPlayerinPlayerXPDB(String name) {
    game.PlayerXPDB send = null;
    Iterator var2 = playerXPDB_data.iterator();

    while(var2.hasNext()) {
      game.PlayerXPDB pl = (game.PlayerXPDB)var2.next();
      if (pl.getPlayerName().equalsIgnoreCase(name)) {
        send = pl;
      }
    }

    if (send == null) {
      send = new game.PlayerXPDB(name);
      playerXPDB_data.add(send);
      return getPlayerinPlayerXPDB(name);
    } else {
      return send;
    }
  }

  static {
    gameState = game.GameState.Standby;
    current_game = game.MINIGAMES.EMPTY;
    Max_Players = 16;
    Min_Players = 4;
    Server_ID = "null";
    Server_Name = "#ไม่มีชื่อห้อง#";
    Server_password = "#NONE#";
    game_rand = 0;
    force_start = false;
    random_map = true;
    auto_start = true;
    force_map = "null";
    game_renamer = "#NULL#";
    password_renamer = "#NULL#";
    chat_allow_on_game_live = false;
    player_xp_grade = true;
    max_x = 0;
    min_x = 0;
    max_z = 0;
    min_z = 0;
    many_players_in_last_game = 0;
    EventMode = false;
  }

  public static class PlayerXPDB {
    ArrayList<game.PlayerXPDBDATA> data;
    public final String name;

    PlayerXPDB(String name) {
      this.name = name;
      this.data = new ArrayList();
    }

    public boolean addKey(String str_data, int c_count) {
      boolean is_have_in_array = false;
      Iterator var4 = this.data.iterator();

      game.PlayerXPDBDATA xpdbdata;
      while(var4.hasNext()) {
        xpdbdata = (game.PlayerXPDBDATA)var4.next();
        if (xpdbdata.getKey().equals(str_data)) {
          is_have_in_array = true;
        }
      }

      if (is_have_in_array) {
        var4 = this.data.iterator();

        while(var4.hasNext()) {
          xpdbdata = (game.PlayerXPDBDATA)var4.next();
          if (xpdbdata.getKey().contains(str_data)) {
            int count = xpdbdata.getCount();
            starchaser.Logger(LOG_TYPE.DEBUG, this.getPlayerName() + " New Key XP Added! " + str_data + " " + count + " > " + (count + 1));
            xpdbdata.setCount(count + c_count);
          }
        }
      } else {
        this.data.add(new game.PlayerXPDBDATA(str_data, c_count));
      }

      return false;
    }

    public boolean addKey(String str_data) {
      boolean is_have_in_array = false;
      Iterator var3 = this.data.iterator();

      game.PlayerXPDBDATA xpdbdata;
      while(var3.hasNext()) {
        xpdbdata = (game.PlayerXPDBDATA)var3.next();
        if (xpdbdata.getKey().equals(str_data)) {
          is_have_in_array = true;
        }
      }

      if (is_have_in_array) {
        var3 = this.data.iterator();

        while(var3.hasNext()) {
          xpdbdata = (game.PlayerXPDBDATA)var3.next();
          if (xpdbdata.getKey().contains(str_data)) {
            int count = xpdbdata.getCount();
            starchaser.Logger(LOG_TYPE.DEBUG, this.getPlayerName() + " New Key XP Added! " + str_data + " " + count + " > " + (count + 1));
            xpdbdata.setCount(count + 1);
          }
        }
      } else {
        this.data.add(new game.PlayerXPDBDATA(str_data, 1));
      }

      return false;
    }

    public String getPlayerName() {
      return this.name;
    }

    public ArrayList<String> getKeys() {
      ArrayList<String> key_list = new ArrayList();
      Iterator var2 = this.data.iterator();

      while(var2.hasNext()) {
        game.PlayerXPDBDATA playerXPDBDATA = (game.PlayerXPDBDATA)var2.next();
        key_list.add(playerXPDBDATA.getKey());
      }

      return key_list;
    }

    public int getKeyCount(String key) {
      int num = 0;
      Iterator var3 = this.data.iterator();

      game.PlayerXPDBDATA pl;
      do {
        if (!var3.hasNext()) {
          return num;
        }

        pl = (game.PlayerXPDBDATA)var3.next();
      } while(pl.getKey() != key);

      return pl.getCount();
    }
  }

  public static class PlayerXPDBDATA {
    String key;
    int count;

    PlayerXPDBDATA(String key, int count) {
      this.key = key;
      this.count = count;
    }

    public int getCount() {
      return this.count;
    }

    public String getKey() {
      return this.key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public void setCount(int count) {
      this.count = count;
    }
  }

  public static enum PlayerState {
    Live,
    Out,
    Lobby;

    private PlayerState() {
    }
  }

  public static enum GameState {
    Loading,
    Recruit,
    Prepare,
    Live,
    End,
    Closed,
    Standby;
  }

  public static enum MINIGAMES {
    EMPTY,
    SPLEEF,
    BEDWARS,
    TNTTAG,
    TNTRUN,
    UHC,
    BLOCKHUNT;
  }
}
