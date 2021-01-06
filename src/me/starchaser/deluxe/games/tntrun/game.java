//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.tntrun;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.YamlReader;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.DeluxeMap;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class game {
  public static boolean show_in_lobby = true;
  public static ItemStack item_icon;
  public static int time_start_tnt;
  public static DeluxePlayer Last_Survive;

  public game() {
  }

  public static void Select() {
    me.starchaser.deluxe.games.game.Max_Players = 25;
    me.starchaser.deluxe.games.game.Min_Players = 5;
    me.starchaser.deluxe.games.game.current_game = MINIGAMES.TNTRUN;
    me.starchaser.deluxe.games.game.game_rand = (new Random()).nextInt(99999);
    int i;
    if (me.starchaser.deluxe.games.game.random_map) {
      File folder = new File(core.sv_path + File.separator + "maps", "TNTRUN");
      File[] listOfFiles = folder.listFiles();
      ArrayList<String> list_files = new ArrayList();

      for(i = 0; i < listOfFiles.length; ++i) {
        list_files.add(listOfFiles[i].getName());
      }

      i = (new Random()).nextInt(list_files.size());
      if (i == list_files.size() && i != 0) {
        --i;
      }

      Bukkit.getLogger().info("RANDOM: " + (String)list_files.get(i));
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/TNTRUN/" + (String)list_files.get(i));
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/TNTRUN/" + (String)list_files.get(i), core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    } else {
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/TNTRUN/" + me.starchaser.deluxe.games.game.force_map + ".zip");
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/TNTRUN/" + me.starchaser.deluxe.games.game.force_map + ".zip", core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    }

    starchaser.Logger(LOG_TYPE.GAME, "world name GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + " unzip!");
    YamlReader yaml = new YamlReader(core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + "/settings.yml");

    int border;
    try {
      border = yaml.getInt("BORDER_SIZE");
    } catch (Exception var6) {
      border = 1000;
    }

    DeluxeMap map = new DeluxeMap("GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase(), yaml.getString("MAP_NAME"), yaml.getString("MAP_AUTHOR"), yaml.getInt("MIN_X"), yaml.getInt("MAX_X"), yaml.getInt("MIN_Z"), yaml.getInt("MAX_Z"), border, yaml.getConfigSelection("TEAM_LIST"));
    me.starchaser.deluxe.games.game.game_map = map;
    me.starchaser.deluxe.games.game.max_x = map.getMax_x();
    me.starchaser.deluxe.games.game.min_x = map.getMin_x();
    me.starchaser.deluxe.games.game.max_z = map.getMax_z();
    me.starchaser.deluxe.games.game.min_z = map.getMin_z();
    me.starchaser.deluxe.games.game.game_team = map.getTeams();

    try {
      i = yaml.getInt("MIN_PLAYERS");
      if (i != 0) {
        me.starchaser.deluxe.games.game.Min_Players = i;
      } else {
        me.starchaser.deluxe.games.game.Min_Players = 5;
      }
    } catch (NullPointerException var5) {
      me.starchaser.deluxe.games.game.Min_Players = 5;
    }

    try {
      i = yaml.getInt("MAX_PLAYERS");
      if (i != 0) {
        me.starchaser.deluxe.games.game.Max_Players = i;
      } else {
        me.starchaser.deluxe.games.game.Max_Players = 25;
      }
    } catch (NullPointerException var4) {
      me.starchaser.deluxe.games.game.Max_Players = 25;
    }

  }

  public static void gameStart() {
    Iterator var0 = Bukkit.getOnlinePlayers().iterator();

    while(var0.hasNext()) {
      Player p = (Player)var0.next();
      p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
    }

    starchaser.sendActionbar("§7§l〔§3§lT§b§lNTRu§3§ln§7§l〕 §fเกมส์ได้เริ่มต้นขึ้นเเล้ว !");
    time_start_tnt = 11;
    (new BukkitRunnable() {
      public void run() {
        --game.time_start_tnt;
        if (game.time_start_tnt == 10) {
          starchaser.BoardCastMsg("§7TNTRun: §aเหลือเวลาเตรียมตัวอีก 10 วินาที...");
        }

        if (game.time_start_tnt <= 5 && game.time_start_tnt >= 1) {
          starchaser.BoardCastMsg("§7TNTRun: §aเหลือเวลาเตรียมตัวอีก " + game.time_start_tnt + " วินาที...");
        }

        if (game.time_start_tnt <= 0) {
          starchaser.BoardCastMsg("§7TNTRun: §aเริ่มเกมแล้ว ระวังตกหลุมละ!");
          game.time_start_tnt = 11;
          game.gameRun();
          this.cancel();
        }

      }
    }).runTaskTimer(core.getDeluxe, 1L, 20L);
  }

  public static void gameRun() {
    BukkitRunnable gametask = game_task();
    gametask.runTaskTimer(core.getDeluxe, 1L, 1L);
    BukkitRunnable playertask = PlayerTaskCheck();
    playertask.runTaskTimer(core.getDeluxe, 20L, 20L);
  }

  public static void gameEnd(DeluxePlayer last_sur) {
    me.starchaser.deluxe.games.game.ClearMonster(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()));
    starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    starchaser.BoardCastMsg("§f§l                 TNTRUN GAME");
    starchaser.BoardCastMsg("§r");
    starchaser.BoardCastMsg("            §8[§b✶§8] §6§lWinner is " + last_sur.getName() + " §8[§b✶§8]");
    starchaser.BoardCastMsg("§r");
    starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    Iterator var1 = Bukkit.getOnlinePlayers().iterator();

    while(var1.hasNext()) {
      Player p = (Player)var1.next();
      p.getInventory().clear();
      p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 2.0F);
    }

    me.starchaser.deluxe.games.game.gameState = GameState.End;
    me.starchaser.deluxe.games.game.game_end_task(last_sur.getName());
  }

  public static BukkitRunnable PlayerTaskCheck() {
    return new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.current_game != MINIGAMES.TNTRUN && me.starchaser.deluxe.games.game.gameState != GameState.Live) {
          this.cancel();
        }

        int Player_Survive = 0;
        Iterator var2 = Bukkit.getOnlinePlayers().iterator();

        while(var2.hasNext()) {
          Player p = (Player)var2.next();
          DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
          Material m = p.getLocation().getBlock().getType();
          if (me.starchaser.deluxe.games.game.current_game.equals(MINIGAMES.SPLEEF) && dp.getPlayerState().equals(PlayerState.Live) && m == Material.STATIONARY_WATER || m == Material.WATER) {
            p.sendMessage("§7Game: §cไม่สามารถลงน้ำได้! เนื่องจากน้ำเป็นน้ำกรด!");
            if (p.getHealth() - 6.0D < 1.0D) {
              me.starchaser.deluxe.games.game.setOUT(p);
            } else {
              p.damage(6.0D);
            }
          }

          if (dp.getPlayerState() == PlayerState.Live) {
            ++Player_Survive;
            me.starchaser.deluxe.games.game.getPlayerinPlayerXPDB(dp.getName()).addKey("ความยาวนานในการรอดชีวิต");
            game.Last_Survive = dp;
          }
        }

        if (Player_Survive < 2) {
          game.gameEnd(game.Last_Survive);
          this.cancel();
        }
      }
    };
  }

  public static BukkitRunnable game_task() {
    return new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.gameState != GameState.Live || me.starchaser.deluxe.games.game.current_game != MINIGAMES.TNTRUN) {
          this.cancel();
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            Location loc = p.getLocation();
            loc.add(0,-1,0);
            DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
            if (dp.getPlayerState() != PlayerState.Live) continue;
            if (p.getWorld().getName() != me.starchaser.deluxe.games.game.game_map.getMap_id()) continue;
          if (loc.getBlock().getType().equals(Material.LAVA) || loc.getBlock().getType().equals(Material.STATIONARY_LAVA)) {
            dp.setPlayerState(PlayerState.Out);
            starchaser.BoardCastMsg("§7OUT: §c" + dp.getName());
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport((Location) me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
          }

          if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.REDSTONE_BLOCK) {
            loc.getBlock().setType(Material.REDSTONE_BLOCK);
            loc.getBlock().getWorld().playSound(loc, Sound.WOOD_CLICK, 0.8F, 3.0F);
            (new BukkitRunnable() {
              public void run() {
                Block b = loc.getBlock();
                me.starchaser.deluxe.games.game.getPlayerinPlayerXPDB(dp.getName()).addKey("ระยะทาง");
                b.getWorld().playSound(b.getLocation(), Sound.ITEM_PICKUP, 1.0F, 2.0F);
                b.setType(Material.AIR);
                p.setFoodLevel(24);
                p.setHealth(20.0D);
                this.cancel();
              }
            }).runTaskTimer(core.getDeluxe, 5L, 5L);
          } else if (p.getFoodLevel() > 0) {
            p.setFoodLevel(p.getFoodLevel() - 1);
          } else if (p.getHealth() <= 5.0D) {
            dp.setPlayerState(PlayerState.Out);
            starchaser.BoardCastMsg("§7OUT: §c" + dp.getName());
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport((Location) me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
          } else {
            p.damage(4.0D);
          }
        }
      }
        };
      }
    }
