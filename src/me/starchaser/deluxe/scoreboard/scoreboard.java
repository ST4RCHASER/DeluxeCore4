//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.scoreboard;

import java.util.Iterator;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.DeluxeMap.BedTeamState;
import me.starchaser.deluxe.games.DeluxeMap.DeluxeTeam;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class scoreboard {
  public static String time_sepc = "§c:";
  public static ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

  public scoreboard() {
  }

  public static Scoreboard getGameScoreboard(DeluxePlayer dp) {
    Player playerExact = Bukkit.getPlayerExact(dp.getName());
    Scoreboard sb = scoreboardManager.getNewScoreboard();
    Objective objective = sb.registerNewObjective("GameBoard", "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    if (game.current_game.equals(MINIGAMES.EMPTY)) {
      objective.setDisplayName("§e" + core.getDeluxe.getDescription().getName() + " v" + core.getDeluxe.getDescription().getVersion());
      objective.getScore("§o#EmptyMode#").setScore(15);
      return sb;
    } else {
      if (game.gameState == GameState.Recruit) {
        objective.setDisplayName("§aกำลังรอผู้เล่นคนอื่นๆ");
      }

      if (game.gameState == GameState.Prepare) {
        objective.setDisplayName("§fจะเริ่มในอีก §a" + game.time_prepare + " วินาที...");
      }

      if (game.gameState == GameState.Live || game.gameState == GameState.End || game.gameState == GameState.Loading) {
        objective.setDisplayName("§6§lSIAMCRAFT.NET");
      }

      if (game.gameState != GameState.Live && game.gameState != GameState.End && game.gameState != GameState.Loading) {
        objective.getScore("§r").setScore(15);
        objective.getScore("§eจำนวนผู้เล่น").setScore(14);
        objective.getScore("§f" + Bukkit.getOnlinePlayers().size() + "/" + game.Max_Players).setScore(13);
        if (game.host != null) {
          objective.getScore("§bหัวหน้าห้องคือ §l" + game.host.getName()).setScore(12);
        }

        objective.getScore("§r§r").setScore(11);
        objective.getScore("§dเกมที่เลือก").setScore(10);
        String map_str;
        if (game.random_map) {
          map_str = "สุ่มแมพ";
        } else {
          map_str = game.force_map;
        }

        objective.getScore("§r§f" + game.current_game.toString().toLowerCase() + " (" + map_str + ")").setScore(9);
        objective.getScore("§r§r§r").setScore(9);
        objective.getScore("§aคอยน์").setScore(8);
        objective.getScore("§f§f" + dp.getCoins()).setScore(7);
        objective.getScore("§r§r§r§r").setScore(6);
        if (!starchaser.AutoMinigames) {
          objective.getScore("§bห้อง (" + game.Server_ID + ")").setScore(5);
          objective.getScore("§f" + game.Server_Name).setScore(4);
        } else {
          objective.getScore("§bห้อง").setScore(5);
          objective.getScore("§f" + game.current_game.toString().toUpperCase() + " " + game.Server_ID).setScore(4);
        }
      } else {
        int sb_int;
        Iterator var5;
        Player p;
        int secs;
        if (game.gameState == GameState.Live && game.current_game == MINIGAMES.SPLEEF) {
          sb_int = 0;
          var5 = Bukkit.getOnlinePlayers().iterator();

          while(var5.hasNext()) {
            p = (Player)var5.next();
            if (DeluxePlayer.getDeluxePlayer(p).getPlayerState() == PlayerState.Live) {
              ++sb_int;
            }
          }

          if (sb_int > 10) {
            objective.getScore("§r").setScore(15);
            objective.getScore("§aผู้เล่นที่เหลือรอด").setScore(14);
            objective.getScore("§r" + String.valueOf(sb_int)).setScore(13);
            objective.getScore("§r§r").setScore(12);
            objective.getScore("§cผู้เล่นที่ตกรอบไปแล้ว").setScore(11);
            objective.getScore("§r§r" + String.valueOf(Bukkit.getOnlinePlayers().size() - sb_int)).setScore(10);
          } else {
            secs = 15;
            Iterator var17 = Bukkit.getOnlinePlayers().iterator();

            while(var17.hasNext()) {
              p = (Player)var17.next();
              if (DeluxePlayer.getDeluxePlayer(p).getPlayerState() == PlayerState.Live) {
                objective.getScore(game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(p).getPlayer_team().toString().toUpperCase()).getColorCode() + p.getName()).setScore(secs);
                --secs;
              }
            }
          }
        }

        if (game.gameState == GameState.Live && game.current_game == MINIGAMES.BEDWARS) {
          sb_int = 12;
          objective.getScore("§r").setScore(15);
          String min;
          if (me.starchaser.deluxe.games.bedwars.game.time_minutes < 10) {
            min = "0" + me.starchaser.deluxe.games.bedwars.game.time_minutes;
          } else {
            min = String.valueOf(me.starchaser.deluxe.games.bedwars.game.time_minutes);
          }

          String sec;
          if (me.starchaser.deluxe.games.bedwars.game.time_secs < 10) {
            sec = "0" + me.starchaser.deluxe.games.bedwars.game.time_secs;
          } else {
            sec = String.valueOf(me.starchaser.deluxe.games.bedwars.game.time_secs);
          }

          objective.getScore("§fเวลาคงเหลือ: §a" + min + time_sepc + "§a" + sec).setScore(14);
          objective.getScore("§r§r").setScore(13);
          Iterator var19 = game.game_team.getStringTeamsList().iterator();

          label233:
          while(true) {
            while(true) {
              if (!var19.hasNext()) {
                ItemStack[] inv_item_list = playerExact.getInventory().getContents();
                int player_current_bronze = 0;
                int player_current_gold = 0;
                int player_current_iron = 0;
                ItemStack[] var11 = inv_item_list;
                int var12 = inv_item_list.length;

                for(int var13 = 0; var13 < var12; ++var13) {
                  ItemStack im = var11[var13];
                  if (im != null) {
                    if (im.getType().equals(Material.CLAY_BRICK)) {
                      player_current_bronze += im.getAmount();
                    }

                    if (im.getType().equals(Material.GOLD_INGOT)) {
                      player_current_gold += im.getAmount();
                    }

                    if (im.getType().equals(Material.IRON_INGOT)) {
                      player_current_iron += im.getAmount();
                    }
                  }
                }

                Iterator var25 = playerExact.getNearbyEntities(5.0D, 5.0D, 5.0D).iterator();

                Entity e;
                do {
                  if (!var25.hasNext()) {
                    break label233;
                  }

                  e = (Entity)var25.next();
                } while(e == null || !e.getType().equals(EntityType.VILLAGER));

                if (player_current_bronze > 0 || player_current_gold > 0 || player_current_iron > 0) {
                  objective.getScore("§r§r§r").setScore(sb_int);
                  --sb_int;
                }

                if (player_current_gold > 0) {
                  objective.getScore("§6Gold§f: §c" + player_current_gold).setScore(sb_int);
                  --sb_int;
                }

                if (player_current_iron > 0) {
                  objective.getScore("§7Iron§f: §c" + player_current_iron).setScore(sb_int);
                  --sb_int;
                }

                if (player_current_bronze > 0) {
                  objective.getScore("§4Bronze§f: §c" + player_current_bronze).setScore(sb_int);
                  --sb_int;
                }

                if (playerExact.getInventory().firstEmpty() == -1) {
                  objective.getScore("§f(§cช่องกระเป๋าเต็ม§f)").setScore(sb_int);
                  --sb_int;
                }
                break label233;
              }

              String str = (String)var19.next();
              DeluxeTeam c_team = game.game_team.getTeam(str);
              if (c_team.getBW_BedState().equals(BedTeamState.LIVE)) {
                objective.getScore("§a✔ " + c_team.getColorCode() + c_team.getTeam_name()).setScore(sb_int);
                break;
              }

              if (game.game_map.getTeams().getTeam(str).size() >= 1) {
                objective.getScore("§c✘ " + c_team.getColorCode() + c_team.getTeam_name() + " §f(§c" + game.game_map.getTeams().getTeam(str).sizeLIVE() + "§f/§c" + game.game_map.getTeams().getTeam(str).size() + "§f)").setScore(sb_int);
                break;
              }
            }

            --sb_int;
          }
        }

        if (game.gameState == GameState.Live && game.current_game == MINIGAMES.TNTRUN) {
          sb_int = 0;
          var5 = Bukkit.getOnlinePlayers().iterator();

          while(var5.hasNext()) {
            p = (Player)var5.next();
            if (DeluxePlayer.getDeluxePlayer(p).getPlayerState() == PlayerState.Live) {
              ++sb_int;
            }
          }

          if (sb_int > 5) {
            objective.getScore("§r").setScore(15);
            objective.getScore("§aผู้เล่นที่เหลือรอด").setScore(14);
            objective.getScore("§r§r" + String.valueOf(sb_int)).setScore(13);
            objective.getScore("§r§r").setScore(12);
            objective.getScore("§cผู้เล่นที่ตกรอบไปแล้ว").setScore(11);
            objective.getScore("§r§r§r" + String.valueOf(Bukkit.getOnlinePlayers().size() - sb_int)).setScore(10);
          } else {
            objective.getScore("§r").setScore(99);
            objective.getScore("§aผู้เล่นที่เหลือรอด §f(§e" + sb_int + "§f)  §r").setScore(98);
            objective.getScore("§d§m--------------------").setScore(97);
            var5 = Bukkit.getOnlinePlayers().iterator();

            while(var5.hasNext()) {
              p = (Player)var5.next();
              if (DeluxePlayer.getDeluxePlayer(p).getPlayerState() == PlayerState.Live) {
                objective.getScore("§b" + p.getName()).setScore((int)p.getLocation().getY());
              }
            }
          }
        }

        if (game.gameState == GameState.Live && game.current_game == MINIGAMES.TNTTAG) {
          sb_int = 0;
          var5 = Bukkit.getOnlinePlayers().iterator();

          while(var5.hasNext()) {
            p = (Player)var5.next();
            if (DeluxePlayer.getDeluxePlayer(p).getPlayerState() == PlayerState.Live) {
              ++sb_int;
            }
          }

          objective.getScore("§r").setScore(15);
          objective.getScore("§aผู้เล่นที่เหลือรอด").setScore(14);
          objective.getScore("§r§r" + String.valueOf(sb_int)).setScore(13);
          objective.getScore("§r§r").setScore(12);
          objective.getScore("§cผู้เล่นที่ตกรอบไปแล้ว").setScore(11);
          objective.getScore("§r§r§r" + String.valueOf(Bukkit.getOnlinePlayers().size() - sb_int)).setScore(10);
          objective.getScore("§r§r§r").setScore(9);
          objective.getScore("§eมือระเบิดตอนนี้คือ").setScore(8);
          if (me.starchaser.deluxe.games.tnttag.game.bomber != null) {
            objective.getScore(me.starchaser.deluxe.games.tnttag.game.bomber.getName()).setScore(7);
          } else {
            objective.getScore("§o#ยังไม่มี#").setScore(7);
          }

          objective.getScore("§r§r§r§r").setScore(6);
          objective.getScore("§bเวลาคงเหลืออีก").setScore(5);
          objective.getScore("§r" + String.valueOf(me.starchaser.deluxe.games.tnttag.game.boom_time)).setScore(4);
        }

        if (game.gameState == GameState.Live && game.current_game == MINIGAMES.BLOCKHUNT) {
          sb_int = 0;
          var5 = Bukkit.getOnlinePlayers().iterator();

          while(var5.hasNext()) {
            p = (Player)var5.next();
            if (DeluxePlayer.getDeluxePlayer(p).getPlayer_team() == TEAM_COLOR.BLOCK) {
              ++sb_int;
            }
          }

          objective.getScore("§r").setScore(15);
          objective.getScore("§aผู้ซ่อนคงเหลือ").setScore(14);
          objective.getScore("§r§r" + String.valueOf(sb_int)).setScore(13);
          objective.getScore("§r§r").setScore(12);
          objective.getScore("§cผู้ล่าคงเหลือ").setScore(11);
          objective.getScore("§r§r§r" + String.valueOf(Bukkit.getOnlinePlayers().size() - sb_int)).setScore(10);
          objective.getScore("§r§r§r").setScore(9);
          objective.getScore("§bเวลาคงเหลืออีก").setScore(8);
          if (me.starchaser.deluxe.games.blockhunt.game.game_time > 60) {
            secs = me.starchaser.deluxe.games.blockhunt.game.game_time;

            int min;
            for(min = 0; secs > 60; ++min) {
              secs -= 60;
            }

            objective.getScore(min + "." + secs + " §bนาที").setScore(7);
          } else {
            objective.getScore(me.starchaser.deluxe.games.blockhunt.game.game_time + " §bวินาที").setScore(7);
          }
        }
      }

      return sb;
    }
  }
}
