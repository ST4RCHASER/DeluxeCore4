//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.tnttag;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.YamlReader;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.holoapi;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.DeluxeMap;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class game {
  public static boolean show_in_lobby = true;
  public static ItemStack item_icon;
  public static int boom_time;
  public static int time_tnt_start;
  public static DeluxePlayer bomber;
  public static DeluxePlayer Last_Survive;
  public static ArrayList<game.item_tnttag> arr_item;

  public game() {
  }

  public static void Select() {
    me.starchaser.deluxe.games.game.Max_Players = 24;
    me.starchaser.deluxe.games.game.Min_Players = 5;
    me.starchaser.deluxe.games.game.current_game = MINIGAMES.TNTTAG;
    me.starchaser.deluxe.games.game.game_rand = (new Random()).nextInt(99999);
    int mi_x;
    if (me.starchaser.deluxe.games.game.random_map) {
      File folder = new File(core.sv_path + File.separator + "maps", "TNTTAG");
      File[] listOfFiles = folder.listFiles();
      ArrayList<String> list_files = new ArrayList();

      for(mi_x = 0; mi_x < listOfFiles.length; ++mi_x) {
        list_files.add(listOfFiles[mi_x].getName());
      }

      mi_x = (new Random()).nextInt(list_files.size());
      if (mi_x == list_files.size() && mi_x != 0) {
        --mi_x;
      }

      Bukkit.getLogger().info("RANDOM: " + (String)list_files.get(mi_x));
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/TNTTAG/" + (String)list_files.get(mi_x));
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/TNTTAG/" + (String)list_files.get(mi_x), core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    } else {
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/TNTTAG/" + me.starchaser.deluxe.games.game.force_map + ".zip");
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/TNTTAG/" + me.starchaser.deluxe.games.game.force_map + ".zip", core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    }

    starchaser.Logger(LOG_TYPE.GAME, "world name GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + " unzip!");
    YamlReader yaml = new YamlReader(core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + "/settings.yml");

    int border;
    try {
      border = yaml.getInt("BORDER_SIZE");
    } catch (Exception var10) {
      border = 1000;
    }

    int m_z;
    int mi_z;
    int m_x;
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

    int i;
    try {
      i = yaml.getInt("MIN_PLAYERS");
      if (i != 0) {
        me.starchaser.deluxe.games.game.Min_Players = i;
      } else {
        me.starchaser.deluxe.games.game.Min_Players = 5;
      }
    } catch (NullPointerException var8) {
      me.starchaser.deluxe.games.game.Min_Players = 5;
    }

    try {
      i = yaml.getInt("MAX_PLAYERS");
      if (i != 0) {
        me.starchaser.deluxe.games.game.Max_Players = i;
      } else {
        me.starchaser.deluxe.games.game.Max_Players = 24;
      }
    } catch (NullPointerException var7) {
      me.starchaser.deluxe.games.game.Max_Players = 24;
    }

    DeluxeMap map = new DeluxeMap("GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase(), yaml.getString("MAP_NAME"), yaml.getString("MAP_AUTHOR"), mi_x, m_x, mi_z, m_z, border, yaml.getConfigSelection("TEAM_LIST"));
    me.starchaser.deluxe.games.game.game_map = map;
    me.starchaser.deluxe.games.game.max_x = map.getMax_x();
    me.starchaser.deluxe.games.game.min_x = map.getMin_x();
    me.starchaser.deluxe.games.game.max_z = map.getMax_z();
    me.starchaser.deluxe.games.game.min_z = map.getMin_z();
    me.starchaser.deluxe.games.game.game_team = map.getTeams();
  }

  public static void gameStart() {
    Iterator var0 = Bukkit.getOnlinePlayers().iterator();

    while(var0.hasNext()) {
      Player p = (Player)var0.next();
      p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
      starchaser.sendActionbar("§7§l〔§3§lT§b§lNTTA§3§lG§7§l〕 §fเกมส์ได้เริ่มต้นขึ้นเเล้ว ขอให้โชคดี!");
    }

    arr_item = new ArrayList();
    BukkitRunnable check_task = game_first_task();
    check_task.runTaskTimer(core.getDeluxe, 0L, 20L);
  }

  public static void gameEnd(DeluxePlayer last_sur) {
    me.starchaser.deluxe.games.game.ClearMonster(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()));
    starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    starchaser.BoardCastMsg("§f§l                 TNTTAG");
    starchaser.BoardCastMsg("§r");
    starchaser.BoardCastMsg("       §8[§b✶§8] §6§lWinner is " + last_sur.getName() + " §8[§b✶§8]");
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

  public static void spawnFireworks(final Location location, final int amount, final Color color) {
    (new BukkitRunnable() {
      public void run() {
        Location loc = location;
        Firework fw = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.setPower(1);
        fwm.addEffect(FireworkEffect.builder().withColor(color).flicker(true).build());
        fw.setFireworkMeta(fwm);
        fw.detonate();

        for(int i = 0; i < amount; ++i) {
          Firework fw2 = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
          fw2.setFireworkMeta(fwm);
        }

        this.cancel();
      }
    }).runTask(core.getDeluxe);
  }

  public static BukkitRunnable game_first_task() {
    time_tnt_start = 20;
    Iterator var0 = Bukkit.getOnlinePlayers().iterator();

    while(var0.hasNext()) {
      Player pl = (Player)var0.next();
      pl.setWalkSpeed(0.5F);
    }

    return new BukkitRunnable() {
      public void run() {
        me.starchaser.deluxe.games.game.ClearMonster(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()));
        Iterator var1;
        Player p;
        if (game.time_tnt_start == 20) {
          starchaser.BoardCastMsg("§7Game: §aกำลังหาตัวระเบิดในอีก §e" + game.time_tnt_start + "§a วินาที");
          var1 = Bukkit.getOnlinePlayers().iterator();

          while(var1.hasNext()) {
            p = (Player)var1.next();
            p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 5.0F);
          }
        }

        if (game.time_tnt_start == 15) {
          starchaser.BoardCastMsg("§7Game: §aกำลังหาตัวระเบิดในอีก §e" + game.time_tnt_start + "§a วินาที");
          var1 = Bukkit.getOnlinePlayers().iterator();

          while(var1.hasNext()) {
            p = (Player)var1.next();
            p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 5.0F);
          }
        }

        if (game.time_tnt_start > 0 && game.time_tnt_start < 11) {
          starchaser.BoardCastMsg("§7Game: §aกำลังหาตัวระเบิดในอีก §e" + game.time_tnt_start + "§a วินาที");
          var1 = Bukkit.getOnlinePlayers().iterator();

          while(var1.hasNext()) {
            p = (Player)var1.next();
            p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 5.0F);
          }
        }

        if (game.time_tnt_start < 1) {
          game.game_task().runTaskTimer(core.getDeluxe, 0L, 20L);
//          game.helper_iteam_spawn_task().runTaskTimer(core.getDeluxe, 600L, 600L);
          this.cancel();
        }

        --game.time_tnt_start;
      }
    };
  }

  public static void set_bomber(Player player) {
    bomber = DeluxePlayer.getDeluxePlayer(player);
    ItemStack lhelmet = new ItemStack(Material.LEATHER_HELMET, 1);
    LeatherArmorMeta lam = (LeatherArmorMeta)lhelmet.getItemMeta();
    lam.setColor(Color.fromRGB(255, 0, 0));
    lhelmet.setItemMeta(lam);
    ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    LeatherArmorMeta chest = (LeatherArmorMeta)lchest.getItemMeta();
    chest.setColor(Color.fromRGB(255, 0, 0));
    lchest.setItemMeta(chest);
    ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    LeatherArmorMeta MLeggings = (LeatherArmorMeta)lchest.getItemMeta();
    MLeggings.setColor(Color.fromRGB(255, 0, 0));
    Leggings.setItemMeta(MLeggings);
    ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS, 1);
    LeatherArmorMeta MBoots = (LeatherArmorMeta)lchest.getItemMeta();
    MBoots.setColor(Color.fromRGB(255, 0, 0));
    Boots.setItemMeta(MBoots);
    Iterator var9 = Bukkit.getOnlinePlayers().iterator();

    while(var9.hasNext()) {
      Player player1 = (Player)var9.next();
      player1.getInventory().setHelmet(new ItemStack(Material.AIR));
      player1.getInventory().setChestplate(new ItemStack(Material.AIR));
      player1.getInventory().setLeggings(new ItemStack(Material.AIR));
      player1.getInventory().setBoots(new ItemStack(Material.AIR));
      player1.getInventory().setItem(0, new ItemStack(Material.AIR));
      player1.getInventory().setItem(1, new ItemStack(Material.AIR));
      player1.getInventory().setItem(2, new ItemStack(Material.AIR));
      player1.getInventory().setItem(3, new ItemStack(Material.AIR));
      player1.getInventory().setItem(4, new ItemStack(Material.AIR));
      player1.getInventory().setItem(5, new ItemStack(Material.AIR));
      player1.getInventory().setItem(6, new ItemStack(Material.AIR));
      player1.getInventory().setItem(7, new ItemStack(Material.AIR));
      player1.getInventory().setItem(8, new ItemStack(Material.AIR));
      player1.playSound(player1.getLocation(), Sound.NOTE_PLING, 1.0F, 4.0F);
    }

    player.getInventory().setItem(0, new ItemStack(Material.TNT));
    player.getInventory().setItem(1, new ItemStack(Material.TNT));
    player.getInventory().setItem(2, new ItemStack(Material.TNT));
    player.getInventory().setItem(3, new ItemStack(Material.TNT));
    player.getInventory().setItem(4, new ItemStack(Material.TNT));
    player.getInventory().setItem(5, new ItemStack(Material.TNT));
    player.getInventory().setItem(6, new ItemStack(Material.TNT));
    player.getInventory().setItem(7, new ItemStack(Material.TNT));
    player.getInventory().setItem(8, new ItemStack(Material.TNT));
    player.getInventory().setHelmet(new ItemStack(Material.TNT));
    player.getInventory().setChestplate(lchest);
    player.getInventory().setLeggings(Leggings);
    player.getInventory().setBoots(Boots);
  }

//  public static BukkitRunnable helper_iteam_spawn_task() {
//    return new BukkitRunnable() {
//      public void run() {
//        if (me.starchaser.deluxe.games.game.current_game == MINIGAMES.TNTTAG && me.starchaser.deluxe.games.game.gameState == GameState.Live) {
//          if (game.arr_item.size() < 3) {
//            int rand_num = (new Random()).nextInt(3);
//            int rand_spawn_get = (new Random()).nextInt(me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().size()) - 1;
//            if (rand_spawn_get < 0 || rand_spawn_get > me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().size() - 1) {
//              rand_spawn_get = 0;
//            }
//
//            Location loc_rand = ((Location)me.starchaser.deluxe.games.game.game_map.getTeams().getTeam((String)me.starchaser.deluxe.games.game.game_map.getTeams().getStringTeamsList().get(0)).getTeamSpawnLocation().get(rand_spawn_get)).add(0.0D, 1.0D, 0.0D);
//            int rand_numx = false;
//            if (!rand_numx) {
//              game.item_tnttag item = new game.item_tnttag(loc_rand, "§aเพิ่มความเร็ว", Material.POTION);
//              item.display();
//              game.arr_item.add(item);
//            }
//
//          }
//        }
//      }
//    };
//  }

  public static BukkitRunnable game_task() {
    return new BukkitRunnable() {
      public void run() {
        me.starchaser.deluxe.games.game.ClearMonster(Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id()));
        if (me.starchaser.deluxe.games.game.current_game != MINIGAMES.TNTTAG) {
          this.cancel();
        }

        int Player_Survive = 0;
        Iterator var2 = Bukkit.getOnlinePlayers().iterator();

        Player player;
        DeluxePlayer dp;
        while(var2.hasNext()) {
          player = (Player)var2.next();
          dp = DeluxePlayer.getDeluxePlayer(player);
          Material m = player.getLocation().getBlock().getType();
          if (me.starchaser.deluxe.games.game.current_game.equals(MINIGAMES.TNTTAG) && dp.getPlayerState().equals(PlayerState.Live) && m == Material.STATIONARY_WATER || m == Material.WATER) {
            player.sendMessage("§7Game: §cไม่สามารถลงน้ำได้! เนื่องจากน้ำเป็นน้ำกรด!");
            if (player.getHealth() - 6.0D < 1.0D) {
              me.starchaser.deluxe.games.game.setOUT(player);
            } else {
              player.damage(6.0D);
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
          game.bomber = null;
          game.boom_time = 20;
          this.cancel();
        } else {
          if (game.bomber != null && !game.bomber.getPlayerState().equals(PlayerState.Out) && Bukkit.getPlayerExact(game.bomber.getName()) != null) {
            if (game.boom_time < 1) {
              game.bomber.setPlayerState(PlayerState.Out);
              Bukkit.getPlayerExact(game.bomber.getName()).setGameMode(GameMode.SPECTATOR);
              starchaser.BoardCastMsg("§7OUT: §c" + game.bomber.getName());
              Bukkit.getPlayerExact(game.bomber.getName()).setWalkSpeed(0.2F);
              var2 = Bukkit.getOnlinePlayers().iterator();

              while(var2.hasNext()) {
                player = (Player)var2.next();
                player.setLevel(0);
                player.playSound(player.getLocation(), Sound.EXPLODE, 1.0F, 0.0F);
              }
            }

            if (game.bomber != null && !game.bomber.getPlayerState().equals(PlayerState.Out)) {
              game.spawnFireworks(Bukkit.getPlayerExact(game.bomber.getName()).getLocation(), 1, Color.RED);
              var2 = Bukkit.getOnlinePlayers().iterator();

              while(var2.hasNext()) {
                player = (Player)var2.next();
                if (DeluxePlayer.getDeluxePlayer(player) == game.bomber) {
                  player.setWalkSpeed(0.5F);
                } else {
                  player.setWalkSpeed(0.4F);
                }
              }
            }

            --game.boom_time;
            if (Player_Survive <= 6) {
              var2 = Bukkit.getOnlinePlayers().iterator();

              label126:
              while(true) {
                int i;
                do {
                  do {
                    if (!var2.hasNext()) {
                      break label126;
                    }

                    player = (Player)var2.next();
                    dp = DeluxePlayer.getDeluxePlayer(player);
                    i = 0;
                  } while(dp.getPlayerState() != PlayerState.Live);
                } while(dp.getName() == game.bomber.getName());

                while(i < 256) {
                  Player target = Bukkit.getPlayerExact(game.bomber.getName());
                  Location lo = player.getLocation();
                  lo.setY((double)i);
                  ++i;
                  if ((double)i != target.getLocation().getY() && (double)i != target.getLocation().getY() - 1.0D && (double)i != target.getLocation().getY() - 2.0D && (double)i != target.getLocation().getY() - 3.0D && (double)i != target.getLocation().getY() - 4.0D && (double)i != target.getLocation().getY() - 5.0D && (double)i != target.getLocation().getY() + 1.0D && (double)i != target.getLocation().getY() + 2.0D && (double)i != target.getLocation().getY() + 3.0D && (double)i != target.getLocation().getY() + 4.0D && (double)i != target.getLocation().getY() + 5.0D) {
                    target.playEffect(lo, Effect.MOBSPAWNER_FLAMES, 4);
                  }
                }
              }
            }

            var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
              player = (Player)var2.next();
              player.setLevel(game.boom_time);
              if (game.boom_time < 6) {
                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 5.0F);
              }
            }
          } else {
            ArrayList<Player> onlineplayers = new ArrayList();
            Iterator var9 = Bukkit.getOnlinePlayers().iterator();

            while(var9.hasNext()) {
              Player pla = (Player)var9.next();
              onlineplayers.add(pla);
            }

            int random_num = (new Random()).nextInt(onlineplayers.size());
            if (random_num < 1) {
              random_num = 1;
            }

            DeluxePlayer random_bomber;
            for(random_bomber = DeluxePlayer.getDeluxePlayer((Player)onlineplayers.get(random_num)); random_bomber.getPlayerState() == PlayerState.Out; random_bomber = DeluxePlayer.getDeluxePlayer((Player)onlineplayers.get(random_num))) {
              random_num = (new Random()).nextInt(onlineplayers.size());
              if (random_num < 1) {
                random_num = 1;
              }
            }

            game.set_bomber(Bukkit.getPlayerExact(random_bomber.getName()));
            game.boom_time = 20;
            starchaser.BoardCastMsg("§7Game: §e" + game.bomber.getName() + " §aคือคนถือระเบิดคนใหม่");
          }

        }
      }
    };
  }

  static {
    item_icon = new ItemStack(Material.REDSTONE_BLOCK);
    boom_time = 0;
    time_tnt_start = 0;
    bomber = null;
    Last_Survive = null;
    arr_item = new ArrayList();
  }

  public static class item_tnttag {
    holoapi holo;
    String str;
    Location loc;
    Material mat;

    item_tnttag(Location location, String string, Material material) {
      this.holo = new holoapi(location, new String[]{string});
      this.str = string;
      this.loc = location;
      this.mat = material;
    }

    public void display() {
      Iterator var1 = Bukkit.getOnlinePlayers().iterator();

      while(var1.hasNext()) {
        Player p = (Player)var1.next();
        this.holo.display(p);
      }

      ItemStack im = new ItemStack(this.mat);
      ItemMeta itemMeta = im.getItemMeta();
      itemMeta.setDisplayName(this.str);
      im.setItemMeta(itemMeta);
      this.loc.getWorld().dropItem(this.loc, im);
    }

    public void hide() {
      Iterator var1 = Bukkit.getOnlinePlayers().iterator();

      while(var1.hasNext()) {
        Player p = (Player)var1.next();
        this.holo.destroy(p);
      }

    }

    public Location getLoc() {
      return this.loc;
    }

    public String getStr() {
      return this.str;
    }

    public Material getMat() {
      return this.mat;
    }
  }
}
