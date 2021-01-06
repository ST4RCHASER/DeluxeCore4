//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.nametagedit.plugin.NametagEdit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.PlayerState;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import me.starchaser.deluxe.starchaser.ServerGamemode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeluxePlayer {
  final String name;
  private DeluxePlayer.DeluxeTitle title;
  private DeluxePlayer.PlayerClass playerClass;
  private DeluxePlayer.PlayerLevel player_level;
  private int id;
  private int coins;
  private PlayerState mg_state;
  private String is_enter_password;
  private String server_target;
  private TEAM_COLOR player_team;
  private final boolean show_title;
  private Hologram hologram;
  private final int feather_points;
  private final int paid_points;

  DeluxePlayer(int id, final String name, int class_id, int level, int xp, int title_id, int coins, boolean show_title_on_head, int paid_point, int feather_point) {
    this.id = id;
    this.name = name;
    this.coins = coins;
    this.is_enter_password = "#NONE#";
    this.server_target = "#NONE#";
    this.playerClass = new DeluxePlayer.PlayerClass(class_id);
    this.title = new DeluxePlayer.DeluxeTitle(title_id);
    this.mg_state = PlayerState.Lobby;
    this.player_team = TEAM_COLOR.NONE;
    this.player_level = new DeluxePlayer.PlayerLevel(level, xp, name);
    this.show_title = show_title_on_head;
    this.feather_points = feather_point;
    this.paid_points = paid_point;
    if (this.show_title) {
      final Player player1 = Bukkit.getPlayerExact(name);
      this.hologram = HologramsAPI.createHologram(core.getDeluxe, player1.getLocation().add(0.0D, 2.0D, 0.0D));
      this.hologram.getVisibilityManager().hideTo(player1);
      this.hologram.appendTextLine(this.getTitle().getStr());
      BukkitRunnable bukkitRunnable = new BukkitRunnable() {
        public void run() {
          if (starchaser.ServerGamemodeMode != ServerGamemode.MINIGAMES || (game.gameState == GameState.Prepare || game.gameState == GameState.Recruit) && (player1 == null || player1.getWorld().getName().equalsIgnoreCase("main_lobby"))) {
            Player player = Bukkit.getPlayerExact(name);
            boolean is_null_pointer = false;

            try {
              player.getLocation();
            } catch (NullPointerException var6) {
              is_null_pointer = true;
            }

            if (player == null || is_null_pointer) {
              DeluxePlayer.this.hologram.delete();
              this.cancel();
            }

            if (DeluxePlayer.this.hologram != null && player != null) {
              try {
                DeluxePlayer.this.hologram.teleport(player.getLocation().add(0.0D, 2.6D, 0.0D));
              } catch (IllegalArgumentException var7) {
                if (DeluxePlayer.this.hologram != null && !DeluxePlayer.this.hologram.isDeleted()) {
                  var7.printStackTrace();
                } else {
                  this.cancel();
                }
              }

              boolean set_hide = false;
              if (player.isSneaking()) {
                set_hide = true;
              }

              if (game.gameState == GameState.Live) {
                set_hide = true;
              }

              Iterator var4;
              Player target;
              if (set_hide) {
                var4 = Bukkit.getOnlinePlayers().iterator();

                while(var4.hasNext()) {
                  target = (Player)var4.next();
                  DeluxePlayer.this.hologram.getVisibilityManager().hideTo(target);
                }
              } else {
                var4 = Bukkit.getOnlinePlayers().iterator();

                while(var4.hasNext()) {
                  target = (Player)var4.next();
                  if (target != player) {
                    DeluxePlayer.this.hologram.getVisibilityManager().showTo(target);
                  }
                }
              }
            }

          }
        }
      };
      bukkitRunnable.runTaskTimer(core.getDeluxe, 1L, 1L);
    }

  }

  public DeluxePlayer.PlayerClass getPlayerClass() {
    return this.playerClass;
  }

  public void setPlayer_team(TEAM_COLOR team_color) {
    this.player_team = team_color;
    Player pl = Bukkit.getPlayer(this.name);
    String ministr;
    if (this.playerClass.getId() > 0) {
      ministr = this.playerClass.getStr().substring(3);
    } else {
      ministr = "§b§lSC §7";
    }

    if (team_color == TEAM_COLOR.NONE) {
      NametagEdit.getApi().setPrefix(pl, ministr + " §f");
    } else {
      NametagEdit.getApi().setPrefix(pl, ministr + " §r" + game.game_team.getTeam(team_color.toString()).getColorCode());
    }

  }

  public TEAM_COLOR getPlayer_team() {
    return this.player_team;
  }

  public DeluxePlayer.DeluxeTitle getTitle() {
    return this.title;
  }

  public void setTitle(int id) {
    this.title.title_id = id;
    if (this.hologram != null) {
      this.hologram.removeLine(0);
      this.hologram.appendTextLine(this.title.getStr());
    }

  }

  public int getFeather_points() {
    return this.feather_points;
  }

  public int getPaid_points() {
    return this.paid_points;
  }

  public boolean isShow_title() {
    return this.show_title;
  }

  public Hologram getHologram() {
    return this.hologram;
  }

  public DeluxePlayer.PlayerLevel getLevel() {
    return this.player_level;
  }

  public int getOOC_Count() {
    try {
      ResultSet res = core.getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `id` = " + this.getId() + ";");
      res.next();
      int ooc_count = res.getInt("ooc");
      return ooc_count;
    } catch (SQLException var3) {
      var3.printStackTrace();
      starchaser.Logger(LOG_TYPE.PLAYER, "Error on geting ooc count for player " + this.getName());
      return 0;
    }
  }

  public boolean setOOC_Count(int count) {
    try {
      core.getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`players` SET `ooc` = '" + count + "' WHERE `players`.`id` = " + this.getId() + ";");
      return true;
    } catch (SQLException var3) {
      var3.printStackTrace();
      return false;
    }
  }

  public void addOOC_Count(int num) {
    int new_count_ooc = num + this.getOOC_Count();
    this.setOOC_Count(new_count_ooc);
  }

  public void setCoins(int num) {
    this.coins = num;
  }

  public void addCoins(int num) {
    this.coins += num;
  }

  public String isEnterPassword() {
    return this.is_enter_password;
  }

  public void setEnterPassword(String b) {
    this.is_enter_password = b;
  }

  public String getTargetServer() {
    return this.server_target;
  }

  public void setTargetServer(String b) {
    this.server_target = b;
  }

  public int getId() {
    return this.id;
  }

  public int getCoins() {
    return this.coins;
  }

  public String getName() {
    return this.name;
  }

  public PlayerState getPlayerState() {
    return this.mg_state;
  }

  public void setPlayerState(PlayerState ps) {
    this.mg_state = ps;
  }

  public static DeluxePlayer getDeluxePlayer(Player p) {
    DeluxePlayer result = null;
    Iterator var2 = core.PlayerRef.iterator();

    while(var2.hasNext()) {
      DeluxePlayer dp = (DeluxePlayer)var2.next();
      if (dp.getName().equalsIgnoreCase(p.getName())) {
        result = dp;
      }
    }

    return result;
  }

  public static void addDeluxePlayer(DeluxePlayer dp) {
    core.PlayerRef.add(dp);
  }

  public static void removeDeluxePlayer(Player p) {
    List<DeluxePlayer> toRemove = new ArrayList();
    Iterator var2 = core.PlayerRef.iterator();

    while(var2.hasNext()) {
      DeluxePlayer dp = (DeluxePlayer)var2.next();
      if (p.getName().equalsIgnoreCase(dp.getName())) {
        toRemove.add(dp);
      }
    }

    core.PlayerRef.removeAll(toRemove);
    starchaser.Logger(LOG_TYPE.PLAYER, "§fPlayer memory removed! [§e" + p.getName() + "§f]");
  }

  public class DeluxeTitle {
    private int title_id;
    private String title_data;

    DeluxeTitle(Integer id) {
      this.title_id = id;

      try {
        ResultSet res = core.getConn.createStatement().executeQuery("SELECT * FROM `title_data` WHERE `id` = " + this.title_id + "");
        res.next();
        this.title_data = res.getString("name").replaceAll("&", "§");
      } catch (Exception var4) {
        var4.printStackTrace();
        starchaser.Logger(LOG_TYPE.PLAYER, "§cError on get title data... (TASK: DeluxeTitle.DeluxeTitle) [ID:" + this.title_id + "]");
        this.title_data = "§r";
      }

    }

    public String getStr() {
      return this.title_data;
    }

    public int getId() {
      return this.title_id;
    }
  }

  public class PlayerClass {
    private int id;

    public void setId(int id) {
      this.id = id;
      this.updateNTE();
    }

    public void updateNTE() {
      Player pl = Bukkit.getPlayer(DeluxePlayer.this.name);
      String ministr;
      if (DeluxePlayer.this.playerClass.getId() > 0) {
        ministr = DeluxePlayer.this.playerClass.getStr().substring(3) + "§f";
      } else {
        ministr = "§b§lSC §7";
      }

      NametagEdit.getApi().setPrefix(pl, ministr);
    }

    public int getId() {
      return this.id;
    }

    public String getStr() {
      String p_class_str;
      if (this.id == 9) {
        p_class_str = "§r §c§lAdmin §r";
      } else if (this.id == 6) {
        p_class_str = "§r §5§lStaff §r";
      } else if (this.id == 4) {
        p_class_str = "§r §a§lLegend §r";
      } else if (this.id == 3) {
        p_class_str = "§r §6§lMaster §r";
      } else if (this.id == 2) {
        p_class_str = "§r §d§lHero §r";
      } else if (this.id == 1) {
        p_class_str = "§r §b§lTitan §r";
      } else if (this.id == 0) {
        p_class_str = "§r ";
      } else {
        p_class_str = "§r §cERROR_CLASS_ID_" + this.id + "_NOT_FOUND §r";
      }

      return p_class_str;
    }

    PlayerClass(int id) {
      this.id = id;
    }
  }

  public class PlayerLevel {
    int level;
    int xp;
    String owner;

    PlayerLevel(int level, int xp, String dp_name) {
      this.level = level;
      this.xp = xp;
      this.owner = dp_name;
    }

    public void add_level(int level) {
      DeluxePlayer.this.getLevel().set(level);
    }

    public void take_level(int level) {
      DeluxePlayer.this.getLevel().set(level);
    }

    public int setXP(int xp) {
      return this.xp = xp;
    }

    public int getXP() {
      return this.xp;
    }

    public void give_xp(int xp) {
      this.add_xp(xp);
      this.level_up_task();
    }

    public void add_xp(int xp) {
      this.setXP(this.getXP() + xp);
    }

    public void take_xp(int xp) {
      this.setXP(DeluxePlayer.this.getLevel().getXP() - xp);
      if (DeluxePlayer.this.getLevel().getXP() < 0) {
        DeluxePlayer.this.getLevel().setXP(0);
      }

    }

    public String getOwner() {
      return this.owner;
    }

    public void level_up_task() {
      final DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(Bukkit.getPlayerExact(this.getOwner()));
      int c_xp = dp.getLevel().getXP();
      if (c_xp >= 2500 && c_xp >= 2500) {
        c_xp -= 2500;
        int c_level = dp.getLevel().get_Int();
        this.add_level(c_level + 1);
        this.setXP(c_xp);
        (new BukkitRunnable() {
          public void run() {
            Player p = Bukkit.getPlayerExact(dp.getName());
            int given_ooc = DeluxePlayer.this.getLevel().get_Int() / 22;
            int coins_given = (int)((double)DeluxePlayer.this.getLevel().get_Int() * 1.8D);
            if (p != null) {
              p.sendMessage("§r");
              p.sendMessage("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬§8( §6§lSiamCraft §f» §e§lLevel §8)§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              p.sendMessage("§r");
              p.sendMessage("        §8[§b❆§8] §fยินดีด้วยคุณเลเวลอัพเเล้ว ! §7( §c" + (DeluxePlayer.this.getLevel().get_Int() - 1) + "§6✭ §6➞ §a" + DeluxePlayer.this.getLevel().get_Int() + "§6✭ §7) §8[§b❆§8]");
              p.sendMessage("§r");
              p.sendMessage("§a● §eคุณได้รับไอเทม : ");
              if (given_ooc > 0) {
                p.sendMessage("    §6➥ §dOOC     §7(§ax" + given_ooc + "§7)");
              }

              p.sendMessage("    §6➥ §6Coins   §7(§ax" + coins_given + "§7) §6⛁");
              p.sendMessage("§r");
              p.sendMessage("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              p.sendMessage("§r");
            }

            DeluxePlayer.this.addOOC_Count(given_ooc);
            DeluxePlayer.this.addCoins(coins_given);
            starchaser.BoardCastMsg("§7Level: §a" + dp.getName() + " ได้บรรลุเลเวล §f" + DeluxePlayer.this.getLevel().get_Int() + " §aแล้ว");
            this.cancel();
          }
        }).runTaskTimer(core.getDeluxe, 100L, 100L);
      }

    }

    public int get_Int() {
      return this.level;
    }

    public int set(int level) {
      return this.level = level;
    }

    public String getXPBar() {
      int percent = this.getXPPercent();
      String bar;
      if (percent >= 99) {
        bar = "§b■■■■■■■■■■§8";
      } else if (percent >= 90) {
        bar = "§b■■■■■■■■■§7■§8";
      } else if (percent >= 80) {
        bar = "§b■■■■■■■■§7■■§8";
      } else if (percent >= 70) {
        bar = "§b■■■■■■■§7■■■§8";
      } else if (percent >= 60) {
        bar = "§b■■■■■■§7■■■■§8";
      } else if (percent >= 50) {
        bar = "§b■■■■■§7■■■■■§8";
      } else if (percent >= 40) {
        bar = "§b■■■■§7■■■■■■§8";
      } else if (percent >= 30) {
        bar = "§b■■■§7■■■■■■■§8";
      } else if (percent >= 20) {
        bar = "§b■■§7■■■■■■■■§8";
      } else if (percent >= 10) {
        bar = "§b■§7■■■■■■■■■§8";
      } else {
        bar = "§7■■■■■■■■■■§8";
      }

      return bar;
    }

    public int getXPPercent() {
      int percentx = (int)((float)this.getXP() * 100.0F / 2500.0F);
      return percentx;
    }

    public String getStr() {
      int level = this.level;
      String color_level;
      if (level >= 800) {
        color_level = "d";
      } else if (level >= 700) {
        color_level = "c";
      } else if (level >= 600) {
        color_level = "a";
      } else if (level >= 500) {
        color_level = "2";
      } else if (level >= 400) {
        color_level = "6";
      } else if (level >= 300) {
        color_level = "3";
      } else if (level >= 200) {
        color_level = "b";
      } else if (level >= 100) {
        color_level = "f";
      } else {
        color_level = "7";
      }

      String newword;
      if (level >= starchaser.max_level) {
        newword = "§b§l§k:§d§lM§e§lA§a§lX§b§l§k:§r";
      } else {
        newword = "§" + color_level + level;
      }

      return newword;
    }
  }
}
