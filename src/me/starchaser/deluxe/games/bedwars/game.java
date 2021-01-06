//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.bedwars;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.YamlReader;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.DeluxeMap;
import me.starchaser.deluxe.games.DeluxeMap.BedTeamState;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class game {
  public static boolean show_in_lobby = false;
  public static ItemStack item_icon;
  public static int time_minutes;
  public static int time_secs;
  public static ArrayList<Location> bornze;
  public static ArrayList<Location> iron;
  public static ArrayList<Location> gold;
  public static HashMap<DeluxePlayer, Integer> respawn_time_players;
  public static HashMap<DeluxePlayer, Integer> respawn_full_time_players;
  public static HashMap<DeluxePlayer, Integer> players_invincible;

  public game() {
  }

  public static void Select() {
    me.starchaser.deluxe.games.game.Max_Players = 16;
    me.starchaser.deluxe.games.game.Min_Players = 4;
    me.starchaser.deluxe.games.game.current_game = MINIGAMES.BEDWARS;
    me.starchaser.deluxe.games.game.game_rand = (new Random()).nextInt(99999);
    int m_z;
    if (me.starchaser.deluxe.games.game.random_map) {
      File folder = new File(core.sv_path + File.separator + "maps", "Bedwars");
      File[] listOfFiles = folder.listFiles();
      ArrayList<String> list_files = new ArrayList();

      for(m_z = 0; m_z < listOfFiles.length; ++m_z) {
        list_files.add(listOfFiles[m_z].getName());
      }

      m_z = (new Random()).nextInt(list_files.size());
      if (m_z == list_files.size() && m_z != 0) {
        --m_z;
      }

      Bukkit.getLogger().info("RANDOM: " + (String)list_files.get(m_z));
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/Bedwars/" + (String)list_files.get(m_z));
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/Bedwars/" + (String)list_files.get(m_z), core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    } else {
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/Bedwars/" + me.starchaser.deluxe.games.game.force_map + ".zip");
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/Bedwars/" + me.starchaser.deluxe.games.game.force_map + ".zip", core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    }

    starchaser.Logger(LOG_TYPE.GAME, "world name GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + " unzip!");
    YamlReader yaml = new YamlReader(core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + "/settings.yml");
    me.starchaser.deluxe.games.game.player_per_team = yaml.getInt("PLAYERS_PER_TEAM");
    me.starchaser.deluxe.games.game.Min_Players = yaml.getInt("MIN_PLAYERS");
    me.starchaser.deluxe.games.game.Max_Players = me.starchaser.deluxe.games.game.player_per_team * yaml.getConfigSelection("TEAM_LIST").getKeys(false).size();

    int mi_z;
    int m_x;
    int mi_x;
    try {
      m_x = yaml.getInt("MAX_X");
      mi_x = yaml.getInt("MIN_X");
      mi_z = yaml.getInt("MIN_Z");
      m_z = yaml.getInt("MAX_Z");
    } catch (Exception var9) {
      m_x = 0;
      mi_x = 0;
      m_z = 0;
      mi_z = 0;
    }

    int border;
    try {
      border = yaml.getInt("BORDER_SIZE");
    } catch (Exception var8) {
      border = 1000;
    }

    DeluxeMap map = new DeluxeMap("GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase(), yaml.getString("MAP_NAME"), yaml.getString("MAP_AUTHOR"), mi_x, m_x, mi_z, m_z, border, yaml.getConfigSelection("TEAM_LIST"));
    me.starchaser.deluxe.games.game.game_map = map;
    me.starchaser.deluxe.games.game.game_map_settings = yaml;
    me.starchaser.deluxe.games.game.game_team = map.getTeams();
    if (map.getMax_x() == 0 && map.getMin_x() == 0 && map.getMax_z() == 0 && map.getMin_z() == 0) {
      starchaser.Logger(LOG_TYPE.GAME, "BORDER LOCATION IS LOST GETTING FORM SPAWN LOC AND LAST SPAWN LOC");
      int last_insize = me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().size() - 1;
      if (last_insize < 0) {
        last_insize = 0;
      }

      me.starchaser.deluxe.games.game.min_x = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam((String)me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().get(0)).getTeamSpawnLocation().get(0)).getBlockX();
      me.starchaser.deluxe.games.game.max_x = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam((String)me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().get(last_insize)).getTeamSpawnLocation().get(0)).getBlockX();
      me.starchaser.deluxe.games.game.min_z = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam((String)me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().get(0)).getTeamSpawnLocation().get(0)).getBlockZ();
      me.starchaser.deluxe.games.game.max_z = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam((String)me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().get(last_insize)).getTeamSpawnLocation().get(0)).getBlockZ();
    } else {
      me.starchaser.deluxe.games.game.min_x = map.getMin_x();
      me.starchaser.deluxe.games.game.max_z = map.getMax_z();
      me.starchaser.deluxe.games.game.max_x = map.getMax_x();
      me.starchaser.deluxe.games.game.min_z = map.getMin_z();
    }

  }

  public static void ResourceSpawner() {
    final World w = Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id());
    (new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.current_game != MINIGAMES.BEDWARS || me.starchaser.deluxe.games.game.gameState != GameState.Live || w == null) {
          this.cancel();
        }

        Iterator var1 = game.bornze.iterator();

        while(var1.hasNext()) {
          Location loc = (Location)var1.next();
          ItemStack bronze_model = new ItemStack(Material.CLAY_BRICK);
          ItemMeta bronze_meta = bronze_model.getItemMeta();
          bronze_meta.setDisplayName("§4Bronze");
          bronze_meta.setLore(Arrays.asList("§bไว้ใช้สำหรับซื้อของที่ NPC"));
          bronze_model.setItemMeta(bronze_meta);
          w.dropItem(loc, bronze_model);
          starchaser.Logger(LOG_TYPE.DEBUG, "BRONZE ITEM DROP AT W:" + loc.getWorld().getName() + " X:" + loc.getX() + " Y:" + loc.getY() + " Z:" + loc.getZ());
        }

      }
    }).runTaskTimer(core.getDeluxe, 20L, 20L);
    (new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.current_game != MINIGAMES.BEDWARS || me.starchaser.deluxe.games.game.gameState != GameState.Live || w == null) {
          this.cancel();
        }

        Iterator var1 = game.iron.iterator();

        while(var1.hasNext()) {
          Location loc = (Location)var1.next();
          ItemStack iron_model = new ItemStack(Material.IRON_INGOT);
          ItemMeta iron_meta = iron_model.getItemMeta();
          iron_meta.setDisplayName("§7Iron");
          iron_meta.setLore(Arrays.asList("§bไว้ใช้สำหรับซื้อของที่ NPC"));
          iron_model.setItemMeta(iron_meta);
          w.dropItem(loc, iron_model);
          starchaser.Logger(LOG_TYPE.DEBUG, "IRON ITEM DROP AT W:" + loc.getWorld().getName() + " X:" + loc.getX() + " Y:" + loc.getY() + " Z:" + loc.getZ());
        }

      }
    }).runTaskTimer(core.getDeluxe, 200L, 200L);
    (new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.current_game != MINIGAMES.BEDWARS || me.starchaser.deluxe.games.game.gameState != GameState.Live || w == null) {
          this.cancel();
        }

        Iterator var1 = game.gold.iterator();

        while(var1.hasNext()) {
          Location loc = (Location)var1.next();
          ItemStack gold_model = new ItemStack(Material.GOLD_INGOT);
          ItemMeta gold_meta = gold_model.getItemMeta();
          gold_meta.setDisplayName("§6Gold");
          gold_meta.setLore(Arrays.asList("§bไว้ใช้สำหรับซื้อของที่ NPC"));
          gold_model.setItemMeta(gold_meta);
          w.dropItem(loc, gold_model);
          starchaser.Logger(LOG_TYPE.DEBUG, "GOLD ITEM DROP AT W:" + loc.getWorld().getName() + " X:" + loc.getX() + " Y:" + loc.getY() + " Z:" + loc.getZ());
        }

      }
    }).runTaskTimer(core.getDeluxe, 400L, 400L);
  }

  public static void respawn(final Player p) {
    Location location_d = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0)).add(0.0D, -2.0D, 0.0D);
    Location location_c = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0)).add(0.0D, -2.0D, 0.0D);
    Location location_a = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0)).add(0.0D, -1.0D, 0.0D);
    Location location_b = (Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0);

    while(location_a.getBlock().getType() != Material.AIR && location_b.getBlock().getType() != Material.AIR) {
      location_a.add(0.0D, 1.0D, 0.0D);
      location_b.add(0.0D, 1.0D, 0.0D);
      location_c.add(0.0D, 1.0D, 0.0D);
      location_d.add(0.0D, 1.0D, 0.0D);
    }

    p.teleport(location_b);
    if (respawn_full_time_players.get(DeluxePlayer.getDeluxePlayer(p)) == null) {
      respawn_full_time_players.put(DeluxePlayer.getDeluxePlayer(p), 5);
    }

    respawn_full_time_players.replace(DeluxePlayer.getDeluxePlayer(p), (Integer)respawn_full_time_players.get(DeluxePlayer.getDeluxePlayer(p)) + 5);
    if (respawn_time_players.get(DeluxePlayer.getDeluxePlayer(p)) == null) {
      respawn_time_players.put(DeluxePlayer.getDeluxePlayer(p), respawn_full_time_players.get(DeluxePlayer.getDeluxePlayer(p)));
    } else {
      respawn_time_players.replace(DeluxePlayer.getDeluxePlayer(p), respawn_full_time_players.get(DeluxePlayer.getDeluxePlayer(p)));
    }

    (new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.gameState != GameState.Live && me.starchaser.deluxe.games.game.current_game != MINIGAMES.BEDWARS) {
          this.cancel();
        }

        try {
          if ((Integer)game.respawn_time_players.get(DeluxePlayer.getDeluxePlayer(p)) < 1) {
            Location location_d = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0)).add(0.0D, -2.0D, 0.0D);
            Location location_c = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0)).add(0.0D, -2.0D, 0.0D);
            Location location_a = ((Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0)).add(0.0D, -1.0D, 0.0D);
            Location location_b = (Location)me.starchaser.deluxe.games.game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString()).getTeamSpawnLocation().get(0);

            while(location_a.getBlock().getType() != Material.AIR && location_b.getBlock().getType() != Material.AIR) {
              location_a.add(0.0D, 1.0D, 0.0D);
              location_b.add(0.0D, 1.0D, 0.0D);
              location_c.add(0.0D, 1.0D, 0.0D);
              location_d.add(0.0D, 1.0D, 0.0D);
            }

            p.teleport(location_b);
            p.setGameMode(GameMode.SURVIVAL);
            game.players_invincible.put(DeluxePlayer.getDeluxePlayer(p), 10);
            (new BukkitRunnable() {
              public void run() {
                if (me.starchaser.deluxe.games.game.gameState != GameState.Live && me.starchaser.deluxe.games.game.current_game != MINIGAMES.BEDWARS) {
                  this.cancel();
                }

                if (game.respawn_time_players.get(DeluxePlayer.getDeluxePlayer(p)) != null && (Integer)game.respawn_time_players.get(DeluxePlayer.getDeluxePlayer(p)) > 1) {
                  this.cancel();
                }

                starchaser.sendActionbar(p, "§eปลดสถานะอมตะในอีก §f" + game.players_invincible.get(DeluxePlayer.getDeluxePlayer(p)));
                if ((Integer)game.players_invincible.get(DeluxePlayer.getDeluxePlayer(p)) < 1) {
                  starchaser.sendActionbar(p, "§aปลดสถานะอมตะแล้ว");
                  this.cancel();
                  game.players_invincible.remove(DeluxePlayer.getDeluxePlayer(p));
                } else {
                  game.players_invincible.replace(DeluxePlayer.getDeluxePlayer(p), (Integer)game.players_invincible.get(DeluxePlayer.getDeluxePlayer(p)) - 1);
                }

              }
            }).runTaskTimer(core.getDeluxe, 0L, 20L);
            this.cancel();
          } else {
            p.setGameMode(GameMode.SPECTATOR);
            game.respawn_time_players.replace(DeluxePlayer.getDeluxePlayer(p), (Integer)game.respawn_time_players.get(DeluxePlayer.getDeluxePlayer(p)) - 1);
            starchaser.sendActionbar(p, "§cจะเกิดใหม่ในอีก §f" + game.respawn_time_players.get(DeluxePlayer.getDeluxePlayer(p)));
          }
        } catch (Exception var5) {
          p.setGameMode(GameMode.SURVIVAL);
          this.cancel();
        }

      }
    }).runTaskTimer(core.getDeluxe, 0L, 20L);
  }

  public static void gameStart() {
    Iterator var0 = me.starchaser.deluxe.games.game.game_team.getStringTeamsList().iterator();

    while(var0.hasNext()) {
      String s = (String)var0.next();
      if (!s.equalsIgnoreCase("NONE")) {
        me.starchaser.deluxe.games.game.game_team.getTeam(s).setBW_BedState(BedTeamState.LIVE);
      }
    }

    ArrayList<TEAM_COLOR> last_teams = new ArrayList();
    Iterator var10 = Bukkit.getOnlinePlayers().iterator();

    Player pp;
    while(var10.hasNext()) {
      pp = (Player)var10.next();
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(pp);
      if (dp.getPlayerState() != PlayerState.Out && !last_teams.contains(dp.getPlayer_team())) {
        last_teams.add(dp.getPlayer_team());
      }
    }

    if (last_teams.size() < 2) {
      starchaser.BoardCastMsg("§7Game: §cไม่สามารถเริ่มเกมได้ เนื่องจากทีมไม่เพียงพอ...");
      me.starchaser.deluxe.games.game.game_end_task("#EMPTY#");
    } else {
      starchaser.sendActionbar("§7§l〔§3§lB§b§ledWar§3§ls§7§l〕 §fเกมส์ได้เริ่มต้นขึ้นเเล้ว !");
      me.starchaser.deluxe.games.game.gameState = GameState.Live;
      time_minutes = 60;
      time_secs = 0;
      players_invincible = new HashMap();
      respawn_time_players = new HashMap();
      respawn_full_time_players = new HashMap();
      evt.item_spawn_floor_cooldown = new HashMap();
      evt.team_inv = new HashMap();
      var10 = Bukkit.getOnlinePlayers().iterator();

      while(var10.hasNext()) {
        pp = (Player)var10.next();
        starchaser.Logger(LOG_TYPE.DEBUG, pp.getName() + " " + DeluxePlayer.getDeluxePlayer(pp).getPlayer_team());
      }

      evt.BlockPlaceList = new ArrayList();
      var10 = me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().iterator();

      int x;
      int y;
      int z;
      String keys;
      int type;
      while(var10.hasNext()) {
        keys = (String)var10.next();
        if (me.starchaser.deluxe.games.game.game_map.getTeams().getTeam(keys).size() < 1) {
          me.starchaser.deluxe.games.game.game_map.getTeams().getTeam(keys).setBW_BedState(BedTeamState.DISTORY);
          type = me.starchaser.deluxe.games.game.game_map_settings.getInt("TEAM_LIST." + keys + ".BED.x");
          x = me.starchaser.deluxe.games.game.game_map_settings.getInt("TEAM_LIST." + keys + ".BED.y");
          y = me.starchaser.deluxe.games.game.game_map_settings.getInt("TEAM_LIST." + keys + ".BED.z");
          z = me.starchaser.deluxe.games.game.game_map_settings.getInt("TEAM_LIST." + keys + ".BED.x2");
          int y_ = me.starchaser.deluxe.games.game.game_map_settings.getInt("TEAM_LIST." + keys + ".BED.y2");
          int z_ = me.starchaser.deluxe.games.game.game_map_settings.getInt("TEAM_LIST." + keys + ".BED.z2");
          Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()).getBlockAt(new Location(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()), (double)type, (double)x, (double)y)).setType(Material.AIR);
          Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()).getBlockAt(new Location(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()), (double)z, (double)y_, (double)z_)).setType(Material.AIR);
        }
      }

      bornze = new ArrayList();
      iron = new ArrayList();
      gold = new ArrayList();
      var10 = me.starchaser.deluxe.games.game.game_map_settings.getConfigSelection("RESOURCE").getKeys(false).iterator();

      while(var10.hasNext()) {
        keys = (String)var10.next();
        type = me.starchaser.deluxe.games.game.game_map_settings.getInt("RESOURCE." + keys + ".TYPE");
        x = me.starchaser.deluxe.games.game.game_map_settings.getInt("RESOURCE." + keys + ".x");
        y = me.starchaser.deluxe.games.game.game_map_settings.getInt("RESOURCE." + keys + ".y");
        z = me.starchaser.deluxe.games.game.game_map_settings.getInt("RESOURCE." + keys + ".z");
        if (type == 1) {
          bornze.add(new Location(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()), (double)x, (double)y, (double)z));
        }

        if (type == 2) {
          iron.add(new Location(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()), (double)x, (double)y, (double)z));
        }

        if (type == 3) {
          gold.add(new Location(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()), (double)x, (double)y, (double)z));
        }
      }

      me.starchaser.deluxe.games.game.ClearMonster(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()));
      game_timer().runTaskTimerAsynchronously(core.getDeluxe, 20L, 20L);
      ResourceSpawner();
    }
  }

  public static void gameEnd(TEAM_COLOR win_color_team) {
    me.starchaser.deluxe.games.game.gameState = GameState.End;
    starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    starchaser.BoardCastMsg("§f§l                   BEDWARS");
    starchaser.BoardCastMsg("§r");
    starchaser.BoardCastMsg("        §8[§b✶§8] §6§lWinner is " + win_color_team.toString() + " §6§lTeam! §8[§b✶§8]");
    starchaser.BoardCastMsg("§r");
    starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    Iterator var1 = Bukkit.getOnlinePlayers().iterator();

    while(var1.hasNext()) {
      Player p = (Player)var1.next();
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
      if (dp.getPlayer_team().equals(win_color_team)) {
        me.starchaser.deluxe.games.game.getPlayerinPlayerXPDB(p.getName()).addKey("ชนะ");
      }
    }

    respawn_time_players = new HashMap();
    respawn_full_time_players = new HashMap();
    players_invincible = new HashMap();
    evt.item_spawn_floor_cooldown = new HashMap();
    evt.team_inv = new HashMap();
    me.starchaser.deluxe.games.game.game_end_task("#EMPTY#");
  }

  public static BukkitRunnable game_timer() {
    return new BukkitRunnable() {
      public void run() {
        if (me.starchaser.deluxe.games.game.current_game != MINIGAMES.BEDWARS || me.starchaser.deluxe.games.game.gameState != GameState.Live) {
          this.cancel();
        }

        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while(var1.hasNext()) {
          Player p = (Player)var1.next();
          DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
          if (dp.getPlayerState() == PlayerState.Live) {
            shop.onHoldItem(p);
          }
        }

        ArrayList<TEAM_COLOR> last_teams = new ArrayList();
        Iterator var6 = Bukkit.getOnlinePlayers().iterator();

        while(var6.hasNext()) {
          Player onp = (Player)var6.next();
          DeluxePlayer dpx = DeluxePlayer.getDeluxePlayer(onp);
          if (dpx.getPlayerState() != PlayerState.Out && !last_teams.contains(dpx.getPlayer_team())) {
            last_teams.add(dpx.getPlayer_team());
          }
        }

        if (last_teams.size() < 2) {
          game.gameEnd((TEAM_COLOR)last_teams.get(0));
          this.cancel();
        }

        --game.time_secs;
        if (game.time_secs < 1) {
          game.time_secs = 60;
          --game.time_minutes;
        }

        if (game.time_minutes < 1 && game.time_secs < 1) {
          me.starchaser.deluxe.games.game.gameState = GameState.End;
          starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
          starchaser.BoardCastMsg("§f§l                            BEDWARS");
          starchaser.BoardCastMsg("§r");
          starchaser.BoardCastMsg("§r         §8[§b✶§8] §fผลการเเข่งขันในรอบบนี้ §a§nเสมอ§r §8[§b✶§8]");
          starchaser.BoardCastMsg("§r");
          starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
          me.starchaser.deluxe.games.game.game_end_task((String)null);
          this.cancel();
        }

      }
    };
  }

  public static void openTrading(final DeluxePlayer deluxePlayer, final Player player, final game.SHOP_TYPE shop_type) {
    (new BukkitRunnable() {
      public void run() {
        Inventory inv;
        Iterator var2;
        ItemStack im;
        if (shop_type.equals(game.SHOP_TYPE.HOME)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 9, "§bร้านค้า เลือกหมวดหมู่");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.ARMOR)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า ชุดเกราะ");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getArmorItems(deluxePlayer).iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.MELEE)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า อาวุธระยะประชิด");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getMeleeItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.RANGE)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า อาวุธระยะไกล");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getRageItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.FOOD)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า อาหาร");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getFoodItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.PICKAXE)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า ที่ขุด");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getPickaxeItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.BLOCKS)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า บล๊อค");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getBlocksItems(deluxePlayer).iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.ITEMS)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า ไอเทม");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getUnitItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.EXCHANGE)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า แลกเปลี่ยนทรัพยากร");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getExchangeItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

        if (shop_type.equals(game.SHOP_TYPE.POTION)) {
          inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bร้านค้า ยา");
          var2 = shop.getMenuItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          var2 = shop.getPotionItems().iterator();

          while(var2.hasNext()) {
            im = (ItemStack)var2.next();
            inv.addItem(new ItemStack[]{im});
          }

          player.openInventory(inv);
        }

      }
    }).runTask(core.getDeluxe);
  }

  static {
    item_icon = new ItemStack(Material.BED);
  }

  public static enum SHOP_TYPE {
    ARMOR,
    MELEE,
    RANGE,
    FOOD,
    PICKAXE,
    BLOCKS,
    ITEMS,
    EXCHANGE,
    POTION,
    HOME;

    private SHOP_TYPE() {
    }
  }
}
