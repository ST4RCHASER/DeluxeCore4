//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.spleef;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class game {
  public static ItemStack item_icon = new ItemStack(Material.getMaterial(256));
  public static boolean show_in_lobby = true;
  public static int player_per_team = 1;
  public static HashMap<String, Integer> hunger_spleef;

  public game() {
  }

  public static void Select() {
    me.starchaser.deluxe.games.game.Max_Players = 20;
    me.starchaser.deluxe.games.game.Min_Players = 5;
    me.starchaser.deluxe.games.game.current_game = MINIGAMES.SPLEEF;
    me.starchaser.deluxe.games.game.game_rand = (new Random()).nextInt(99999);
    int i;
    if (me.starchaser.deluxe.games.game.random_map) {
      File folder = new File(core.sv_path + File.separator + "maps", "Super Spleef");
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
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/Super Spleef/" + (String)list_files.get(i));
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/Super Spleef/" + (String)list_files.get(i), core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    } else {
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/Super Spleef/" + me.starchaser.deluxe.games.game.force_map + ".zip");
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/Super Spleef/" + me.starchaser.deluxe.games.game.force_map + ".zip", core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    }

    starchaser.Logger(LOG_TYPE.GAME, "world name GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + " unzip!");
    YamlReader yaml = new YamlReader(core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + "/settings.yml");
    player_per_team = yaml.getInt("MAX_PLAYERS_PER_TEAM");

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
        me.starchaser.deluxe.games.game.Max_Players = 20;
      }
    } catch (NullPointerException var4) {
      me.starchaser.deluxe.games.game.Max_Players = 20;
    }

  }

  public static void gameStart() {
    Iterator var0 = Bukkit.getOnlinePlayers().iterator();

    while(var0.hasNext()) {
      Player p = (Player)var0.next();
      p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
      starchaser.sendActionbar("§7§l〔§3§lS§b§lplee§3§lf§7§l〕 §fเกมส์ได้เริ่มต้นขึ้นเเล้ว !");
    }

    BukkitRunnable check_task = (new evt()).PlayerTaskCheck();
    check_task.runTaskTimer(core.getDeluxe, 20L, 20L);
    AFK_Check().runTaskTimer(core.getDeluxe, 20L, 20L);
  }

  public static BukkitRunnable AFK_Check() {
    hunger_spleef = new HashMap();
    return new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.current_game.equals(MINIGAMES.SPLEEF) && me.starchaser.deluxe.games.game.gameState.equals(GameState.Live)) {
          Iterator var1 = Bukkit.getOnlinePlayers().iterator();

          while(var1.hasNext()) {
            Player p = (Player)var1.next();
            if (DeluxePlayer.getDeluxePlayer(p).getPlayerState().equals(PlayerState.Live)) {
              if (game.hunger_spleef.get(p.getName()) == null) {
                game.hunger_spleef.put(p.getName(), 0);
              }

              if ((Integer)game.hunger_spleef.get(p.getName()) > 31) {
                if (p.getFoodLevel() > 0) {
                  p.setFoodLevel(p.getFoodLevel() - 2);
                } else {
                  if (p.getHealth() - 2.0D < 1.0D) {
                    p.setHealth(20.0D);
                    p.getInventory().clear();
                    DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
                    DeluxePlayer.getDeluxePlayer(p).setPlayerState(PlayerState.Out);
                    starchaser.BoardCastMsg("§7OUT: §c" + p.getName());
                    p.setGameMode(GameMode.SPECTATOR);
                    p.teleport((Location)me.starchaser.deluxe.games.game.game_team.getTeam(dp.getPlayer_team().toString()).getTeamSpawnLocation().get(0));
                  } else {
                    p.damage(2.0D);
                  }

                  p.damage(2.0D);
                }

                p.sendMessage("§7Game: §cคุณจำเป็นต้องทุบบล๊อคบ้าง มิฉนั้นค่าอาหารของคุณจะลด!");
              } else {
                game.hunger_spleef.put(p.getName(), (Integer)game.hunger_spleef.get(p.getName()) + 1);
                p.setFoodLevel(20);
              }
            }
          }
        } else {
          this.cancel();
        }

      }
    };
  }

  public static void gameEnd(DeluxePlayer last_sur) {
    me.starchaser.deluxe.games.game.ClearMonster(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()));
    starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    starchaser.BoardCastMsg("§f§l                 SPLEEF GAME");
    starchaser.BoardCastMsg("§r");
    starchaser.BoardCastMsg("           §8[§b✶§8] §6§lWinner is " + last_sur.getName() + " §8[§b✶§8]");
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
}
