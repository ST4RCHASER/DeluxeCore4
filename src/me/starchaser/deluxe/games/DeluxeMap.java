//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.YamlReader;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class DeluxeMap {
  private final String map_id;
  private final String author;
  private final String map_name;
  private int min_x;
  private int max_x;
  private int min_z;
  private int max_z;
  private int border_size;
  private HashMap<String, DeluxeMap.DeluxeTeam> teams;
  private DeluxeMap.DeluxeTeams d_teams;

  public DeluxeMap(String map_id, String map_name, String author, int min_x, int max_x, int min_z, int max_z, int border_size, ConfigurationSection team_cfg) {
    this.map_name = map_name;
    this.map_id = map_id;
    this.author = author;
    this.min_x = min_x;
    this.max_x = max_x;
    this.min_z = min_z;
    this.max_z = max_z;
    if (border_size < 800) {
      this.border_size = 1000;
    } else {
      this.border_size = border_size;
    }

    this.teams = new HashMap();
    if (team_cfg != null) {
      DeluxeMap.DeluxeTeams a = new DeluxeMap.DeluxeTeams(team_cfg);
      this.d_teams = a;
    }

  }

  public int getBorder_size() {
    return this.border_size;
  }

  public int getMax_x() {
    return this.max_x;
  }

  public int getMax_z() {
    return this.max_z;
  }

  public int getMin_x() {
    return this.min_x;
  }

  public int getMin_z() {
    return this.min_z;
  }

  public String getAuthor() {
    return this.author;
  }

  public String getMap_id() {
    return this.map_id;
  }

  public String getMap_name() {
    return this.map_name;
  }

  public DeluxeMap.DeluxeTeams getTeams() {
    return this.d_teams;
  }

  public class DeluxeTeam {
    private final String team_name;
    private final String team_color_code;
    private DeluxeMap.BedTeamState bedTeamState;
    private DeluxeMap.TeamState teamState;

    DeluxeTeam(String team_name, String team_color_code) {
      this.team_name = team_name;
      this.team_color_code = ("§" + team_color_code).replaceAll("&", "§");
    }

    public int size() {
      int i = 0;
      Iterator var2 = Bukkit.getOnlinePlayers().iterator();

      while(var2.hasNext()) {
        Player p = (Player)var2.next();
        if (DeluxePlayer.getDeluxePlayer(p).getPlayer_team() != null && DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString().equalsIgnoreCase(this.team_name)) {
          ++i;
        }
      }

      return i;
    }

    public int sizeLIVE() {
      int i = 0;
      Iterator var2 = Bukkit.getOnlinePlayers().iterator();

      while(var2.hasNext()) {
        Player p = (Player)var2.next();
        if (DeluxePlayer.getDeluxePlayer(p).getPlayer_team() != null && DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString().equalsIgnoreCase(this.team_name) && DeluxePlayer.getDeluxePlayer(p).getPlayerState() == PlayerState.Live) {
          ++i;
        }
      }

      return i;
    }

    public String getColorCode() {
      return this.team_color_code;
    }

    public String getTeam_name() {
      return this.team_name;
    }

    public DeluxeMap.BedTeamState getBW_BedState() {
      return this.bedTeamState != null && game.current_game == MINIGAMES.BEDWARS ? this.bedTeamState : DeluxeMap.BedTeamState.NONE;
    }

    public void setBW_BedState(DeluxeMap.BedTeamState bs) {
      this.bedTeamState = bs;
    }

    public DeluxeMap.TeamState getTeamState() {
      return this.teamState == null ? DeluxeMap.TeamState.NONE : this.teamState;
    }

    public void setTeamState(DeluxeMap.TeamState ts) {
      this.teamState = ts;
    }

    public ArrayList<Location> getTeamSpawnLocation() {
      YamlReader yaml = new YamlReader(core.sv_path + "GAME_" + game.game_rand + game.current_game.toString().toUpperCase() + "/settings.yml");
      ArrayList<Location> loc_list = new ArrayList();
      int y;
      int z;
      if (game.current_game == MINIGAMES.BEDWARS) {
        ConfigurationSection teamoverall = yaml.getConfigSelection("TEAM_LIST");
        int x = teamoverall.getInt(this.team_name + ".BED.x");
        y = teamoverall.getInt(this.team_name + ".BED.y") + 1;
        z = teamoverall.getInt(this.team_name + ".BED.z");
        World world = Bukkit.getWorld("GAME_" + game.game_rand + game.current_game.toString().toUpperCase());
        Location locx = new Location(world, (double)x, (double)y, (double)z);
        loc_list.add(locx);
      } else {
        String team_overall = yaml.getString("TEAM_LIST." + this.team_name);
        String[] var14 = team_overall.split("[|]");
        y = var14.length;

        for(z = 0; z < y; ++z) {
          String spawnpoint_all = var14[z];
          ArrayList<Integer> list = new ArrayList();
          String[] var9 = spawnpoint_all.split(",");
          int var10 = var9.length;

          for(int var11 = 0; var11 < var10; ++var11) {
            String spawnpoint_num = var9[var11];
            starchaser.Logger(LOG_TYPE.DEBUG, spawnpoint_num);
            list.add(Integer.parseInt(spawnpoint_num));
          }

          Location loc = new Location(Bukkit.getWorld("GAME_" + game.game_rand + game.current_game.toString().toUpperCase()), (double)(Integer)list.get(0), (double)(Integer)list.get(1), (double)(Integer)list.get(2));
          loc_list.add(loc);
        }
      }

      return loc_list;
    }
  }

  public static enum BedTeamState {
    LIVE,
    DISTORY,
    NONE;

    private BedTeamState() {
    }
  }

  public static enum TeamState {
    LIVE,
    OUT,
    NONE;

    private TeamState() {
    }
  }

  public class DeluxeTeams {
    ConfigurationSection team_cfg_select;

    public DeluxeTeams(ConfigurationSection t_color) {
      this.team_cfg_select = t_color;

      String str;
      String color_code;
      for(Iterator var3 = this.team_cfg_select.getKeys(false).iterator(); var3.hasNext(); this.addTeam(str, DeluxeMap.this.new DeluxeTeam(str, color_code))) {
        str = (String)var3.next();
        color_code = "a";
        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.GREEN.toString()) || str.equalsIgnoreCase("block")) {
          color_code = "a";
        }

        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.RED.toString()) || str.equalsIgnoreCase("hunt")) {
          color_code = "c";
        }

        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.BLUE.toString())) {
          color_code = "b";
        }

        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.YELLOW.toString())) {
          color_code = "e";
        }

        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.GOLD.toString())) {
          color_code = "6";
        }

        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.LIGHT_PURPLE.toString())) {
          color_code = "d";
        }

        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.WHITE.toString())) {
          color_code = "l";
        }

        if (str.equalsIgnoreCase(DeluxeMap.TEAM_COLOR.BLACK.toString())) {
          color_code = "0";
        }
      }

    }

    public ArrayList<String> getStringTeamsList() {
      ArrayList<String> arr = new ArrayList();
      arr.addAll(this.team_cfg_select.getKeys(false));
      return arr;
    }

    public DeluxeMap.DeluxeTeam getTeam(String team_color) {
      return (DeluxeMap.DeluxeTeam)DeluxeMap.this.teams.get(team_color);
    }

    public void addTeam(String color, DeluxeMap.DeluxeTeam team) {
      DeluxeMap.this.teams.put(color, team);
    }

    public void removeTeam(String color) {
      DeluxeMap.this.teams.remove(color);
    }
  }

  public static enum TEAM_COLOR {
    NONE,
    GREEN,
    RED,
    BLUE,
    YELLOW,
    GOLD,
    LIGHT_PURPLE,
    WHITE,
    BLACK,
    BLOCK,
    HUNT;

    private TEAM_COLOR() {
    }
  }
}
