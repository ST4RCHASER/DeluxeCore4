//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.blockhunt;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import me.libraryaddict.disguise.DisguiseAPI;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.YamlReader;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.DeluxeMap;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class game {
  public static boolean show_in_lobby = false;
  public static ItemStack item_icon;
  public static HashMap<Player, Location> player_last_hide;
  public static HashMap<Player, game.PlayerLastLoc> playerLocationHashMap;
  public static int game_time;
  static int item_start_time;
  public static HashMap<Material, Integer> map_block_count;
  public static boolean is_game_started;

  public game() {
  }

  public static void Select() {
    me.starchaser.deluxe.games.game.Max_Players = 42;
    me.starchaser.deluxe.games.game.Min_Players = 5;
    me.starchaser.deluxe.games.game.current_game = MINIGAMES.BLOCKHUNT;
    map_block_count = new HashMap();
    me.starchaser.deluxe.games.game.game_rand = (new Random()).nextInt(99999);
    int i;
    if (me.starchaser.deluxe.games.game.random_map) {
      File folder = new File(core.sv_path + File.separator + "maps", "Block Hunt");
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
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/Block Hunt/" + (String)list_files.get(i));
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/Block Hunt/" + (String)list_files.get(i), core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    } else {
      Bukkit.getLogger().info("extracting " + core.sv_path + "maps/Block Hunt/" + me.starchaser.deluxe.games.game.force_map + ".zip");
      me.starchaser.deluxe.games.game.unzip(core.sv_path + "maps/Block Hunt/" + me.starchaser.deluxe.games.game.force_map + ".zip", core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase());
    }

    starchaser.Logger(LOG_TYPE.GAME, "world name GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + " unzip!");
    YamlReader yaml = new YamlReader(core.sv_path + "GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase() + "/settings.yml");
    me.starchaser.deluxe.games.game.player_per_team = 42;

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
        me.starchaser.deluxe.games.game.Max_Players = 42;
      }
    } catch (NullPointerException var4) {
      me.starchaser.deluxe.games.game.Max_Players = 42;
    }

  }

  public static void gameStart() {
    map_block_count = new HashMap();
    World w = Bukkit.getWorld(me.starchaser.deluxe.games.game.game_map.getMap_id());
    if (me.starchaser.deluxe.games.game.game_map.getMap_id().equalsIgnoreCase(w.getName())) {
      Chunk[] var1 = w.getLoadedChunks();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
        Chunk c = var1[var3];
        int cx = c.getX() << 4;
        int cz = c.getZ() << 4;

        for(int x = cx; x < cx + 16; ++x) {
          for(int z = cz; z < cz + 16; ++z) {
            for(int y = 0; y < 128; ++y) {
              if (w.getBlockAt(x, y, z) != null && w.getBlockAt(x, y, z).getType() != null && w.getBlockAt(x, y, z).getType() != Material.AIR && map_block_count.containsKey(w.getBlockAt(x, y, z).getType())) {
                int count = (Integer)map_block_count.get(w.getBlockAt(x, y, z));
                map_block_count.replace(w.getBlockAt(x, y, z).getType(), count + 1);
                starchaser.Logger(LOG_TYPE.DEBUG, w.getBlockAt(x, y, z).getType().toString() + " x" + count);
                if (count == 200) {
                  starchaser.Logger(LOG_TYPE.WORLD, "Block id: " + w.getBlockAt(x, y, z).getType().toString() + " add to blacklist!");
                }
              } else {
                map_block_count.put(w.getBlockAt(x, y, z).getType(), 1);
              }
            }
          }
        }
      }
    }

    Iterator var11 = Bukkit.getOnlinePlayers().iterator();

    Player aa;
    while(var11.hasNext()) {
      aa = (Player)var11.next();
      ItemStack hunt_sword = new ItemStack(Material.IRON_SWORD);
      ItemMeta hunt_sword_meta = hunt_sword.getItemMeta();
      hunt_sword_meta.setDisplayName("§e§lHunt §f§lSword");
      hunt_sword_meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
      hunt_sword.setItemMeta(hunt_sword_meta);
      ItemStack hunt_bow = new ItemStack(Material.BOW);
      ItemMeta hunt_bow_meta = hunt_bow.getItemMeta();
      hunt_bow_meta.setDisplayName("§e§lHunt §f§lBow");
      hunt_bow_meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
      hunt_bow_meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
      hunt_bow.setItemMeta(hunt_bow_meta);
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(aa);
      if (dp.getPlayer_team().equals(TEAM_COLOR.BLOCK)) {
        ItemStack ima = new ItemStack(Material.BEDROCK);
        ima.setAmount(20);
        ItemMeta itemMeta = ima.getItemMeta();
        itemMeta.setDisplayName("§aตอนนี้คุณกำลังอยู่ในร่างของ §f" + ima.getType().toString());
        itemMeta.addEnchant(Enchantment.SILK_TOUCH, 0, true);
        itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        ima.setItemMeta(itemMeta);
        aa.getInventory().setItem(8, ima);
      }

      if (dp.getPlayer_team().equals(TEAM_COLOR.HUNT)) {
        aa.getInventory().setItem(0, hunt_sword);
        aa.getInventory().setItem(1, hunt_bow);
        aa.getInventory().setItem(2, new ItemStack(Material.ARROW));
        aa.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
        aa.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        aa.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        aa.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        aa.getInventory().setHeldItemSlot(0);
      }
    }

    playerLocationHashMap = new HashMap();
    player_last_hide = new HashMap();
    if (me.starchaser.deluxe.games.game.many_players_in_last_game > 5) {
      game_time = 30 * me.starchaser.deluxe.games.game.many_players_in_last_game + 60;
    } else {
      game_time = 300;
    }

    is_game_started = false;
    game_task().runTaskTimer(core.getDeluxe, 0L, 2L);
    var11 = Bukkit.getOnlinePlayers().iterator();

    while(var11.hasNext()) {
      aa = (Player)var11.next();
      if (DeluxePlayer.getDeluxePlayer(aa).getPlayer_team().equals(TEAM_COLOR.HUNT)) {
        aa.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 640, 50));
        aa.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 640, -10));
        aa.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 640, 0));
      }

      if (DeluxePlayer.getDeluxePlayer(aa).getPlayer_team() == TEAM_COLOR.BLOCK) {
        evt.set_block_dis(aa, aa.getInventory().getItem(8).getTypeId());
      }
    }

    (new BukkitRunnable() {
      int timer = 30;

      public void run() {
        if (this.timer == 30 || this.timer == 20 || this.timer == 15 || this.timer == 10 || this.timer < 6 && this.timer > 0) {
          starchaser.BoardCastMsg("§7Game: §aเหลือเวลาให้ซ่อนอีก §e" + this.timer + " §aวินาที");
        }

        if (this.timer < 1) {
          game.is_game_started = true;
          game.item_start_time = 30;
          this.cancel();
        }

        --this.timer;
      }
    }).runTaskTimer(core.getDeluxe, 40L, 20L);
  }

  public static void game_timer(int time_left) {
    Iterator var1 = Bukkit.getOnlinePlayers().iterator();

    while(var1.hasNext()) {
      Player p = (Player)var1.next();
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
      if (dp.getPlayer_team() == TEAM_COLOR.BLOCK) {
        if (item_start_time > 0) {
          --item_start_time;
        } else {
          ItemStack meow;
          ItemMeta meow_meta;
          if (item_start_time == 0) {
            --item_start_time;
            p.getInventory().setItem(17, new ItemStack(Material.ARROW, 5));
            meow = new ItemStack(Material.BOW);
            meow_meta = meow.getItemMeta();
            meow_meta.setDisplayName("§aG§bo§cd §dB§fo§aw");
            meow_meta.setLore(Arrays.asList("§r", "§aธนู ไว้ใช้ป้องกันตัว ยิงแรง ปลิวไกล อย่ายิงพลาดละ!"));
            meow.setItemMeta(meow_meta);
            meow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
            meow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
            p.getInventory().setItem(0, meow);
          }

          if (p.getInventory().getItem(6) == null) {
            meow = new ItemStack(Material.FIREWORK);
            meow_meta = meow.getItemMeta();
            meow_meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
            meow_meta.setDisplayName("§b§lFireworks!");
            meow_meta.setLore(Arrays.asList("§r", "§aคลิกเพื่อจุดพลุ แล้วคุณจะได้รับ 35xp", "§c§nคำเตือน§c: §bแต่ระวังผู้ล่าจะเห็นและได้ยินเอานะ"));
            meow.setItemMeta(meow_meta);
            meow.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
            p.getInventory().setItem(6, meow);
          }

          if (p.getInventory().getItem(5) == null) {
            meow = new ItemStack(Material.SULPHUR);
            meow_meta = meow.getItemMeta();
            meow_meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
            meow_meta.setDisplayName("§a§lMeow!");
            meow_meta.setLore(Arrays.asList("§r", "§aคลิกเพื่อส่งเสียง แล้วคุณจะได้รับ 15xp", "§c§nคำเตือน§c: §bแต่ระวังผู้ล่าจะได้ยินเอานะ"));
            meow.setItemMeta(meow_meta);
            meow.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
            p.getInventory().setItem(5, meow);
          }

          if (!p.getInventory().getItem(6).containsEnchantment(Enchantment.SILK_TOUCH)) {
            if (p.getInventory().getItem(6).getAmount() <= 1) {
              p.getInventory().getItem(6).addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
            } else {
              p.getInventory().getItem(6).setAmount(p.getInventory().getItem(6).getAmount() - 1);
            }
          }

          if (!p.getInventory().getItem(5).containsEnchantment(Enchantment.SILK_TOUCH)) {
            if (p.getInventory().getItem(5).getAmount() <= 1) {
              p.getInventory().getItem(5).addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
            } else {
              p.getInventory().getItem(5).setAmount(p.getInventory().getItem(5).getAmount() - 1);
            }
          }
        }
      }
    }

  }

  public static BukkitRunnable game_task() {
    return new BukkitRunnable() {
      int tricks = 0;

      public void run() {
        if (game.is_game_started) {
          if (this.tricks >= 20) {
            this.tricks = 0;
            --game.game_time;
            game.game_timer(game.game_time);
          } else {
            this.tricks += 2;
          }

          int hider_count = 0;
          Iterator var2 = Bukkit.getOnlinePlayers().iterator();

          while(var2.hasNext()) {
            Player aa = (Player)var2.next();
            if (DeluxePlayer.getDeluxePlayer(aa).getPlayer_team().equals(TEAM_COLOR.BLOCK)) {
              ++hider_count;
            }
          }

          int hunter_count = 0;
          Iterator var19 = Bukkit.getOnlinePlayers().iterator();

          Player p;
          while(var19.hasNext()) {
            p = (Player)var19.next();
            if (DeluxePlayer.getDeluxePlayer(p).getPlayer_team().equals(TEAM_COLOR.HUNT)) {
              ++hunter_count;
            }
          }

          DeluxePlayer dp;
          if (hider_count < 1) {
            starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            starchaser.BoardCastMsg("§f§l                 Block Hunt");
            starchaser.BoardCastMsg("§r");
            starchaser.BoardCastMsg("        §8[§b✶§8] §6§lWinner is §c§lHunter §6§lTeam! §8[§b✶§8]");
            starchaser.BoardCastMsg("§r");
            starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            me.starchaser.deluxe.games.game.game_end_task((String)null);
            var19 = Bukkit.getOnlinePlayers().iterator();

            while(var19.hasNext()) {
              p = (Player)var19.next();
              DisguiseAPI.undisguiseToAll(p);
              dp = DeluxePlayer.getDeluxePlayer(p);
              if (dp.getPlayer_team().equals(TEAM_COLOR.HUNT)) {
                me.starchaser.deluxe.games.game.getPlayerinPlayerXPDB(p.getName()).addKey("ชนะ");
              }
            }

            this.cancel();
          }

          if (game.game_time < 1 || hunter_count < 1) {
            starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            starchaser.BoardCastMsg("§f§l                 Block Hunt");
            starchaser.BoardCastMsg("§r");
            starchaser.BoardCastMsg("        §8[§b✶§8] §6§lWinner is §b§lHider §6§lTeam! §8[§b✶§8]");
            starchaser.BoardCastMsg("§r");
            starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            me.starchaser.deluxe.games.game.game_end_task((String)null);
            var19 = Bukkit.getOnlinePlayers().iterator();

            while(var19.hasNext()) {
              p = (Player)var19.next();
              DisguiseAPI.undisguiseToAll(p);
              dp = DeluxePlayer.getDeluxePlayer(p);
              if (dp.getPlayer_team().equals(TEAM_COLOR.BLOCK)) {
                me.starchaser.deluxe.games.game.getPlayerinPlayerXPDB(p.getName()).addKey("ชนะ");
              }
            }

            this.cancel();
          }
        }

        Iterator var16 = Bukkit.getOnlinePlayers().iterator();

        while(true) {
          while(true) {
            while(true) {
              while(true) {
                Player pl;
                do {
                  if (!var16.hasNext()) {
                    return;
                  }

                  pl = (Player)var16.next();
                } while(!DeluxePlayer.getDeluxePlayer(pl).getPlayer_team().equals(TEAM_COLOR.BLOCK));

                int x;
                int y;
                int z;
                if (game.playerLocationHashMap.containsKey(pl)) {
                  x = ((game.PlayerLastLoc)game.playerLocationHashMap.get(pl)).getX();
                  y = ((game.PlayerLastLoc)game.playerLocationHashMap.get(pl)).getY();
                  z = ((game.PlayerLastLoc)game.playerLocationHashMap.get(pl)).getZ();
                  int xx = (int)pl.getLocation().getX();
                  int yy = (int)pl.getLocation().getY();
                  int zz = (int)pl.getLocation().getZ();
                  ItemStack imaxx;
                  Iterator var11;
                  Player PLA;
                  if (x == xx && y == yy && z == zz) {
                    imaxx = pl.getInventory().getItem(8);
                    int count = imaxx.getAmount();
                    if (count < 2) {
                      if (pl.getInventory().getItem(8).getItemMeta().getDisplayName().contains("§aตอนนี้คุณกำลังเป็น §f") && pl.getInventory().getItem(8).getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                        var11 = Bukkit.getOnlinePlayers().iterator();

                        while(var11.hasNext()) {
                          PLA = (Player)var11.next();
                          if (PLA != pl) {
                            ItemStack imax = pl.getInventory().getItem(8);
                            PLA.sendBlockChange(pl.getLocation(), imax.getType(), (byte)0);
                          }
                        }

                        pl.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 6000, 1, true));
                        continue;
                      }

                      pl.playSound(pl.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                      ItemStack ima = pl.getInventory().getItem(8);
                      ima.setAmount(1);
                      ItemMeta itemMeta = ima.getItemMeta();
                      itemMeta.setDisplayName("§aตอนนี้คุณกำลังเป็น §f" + ima.getType().toString().toLowerCase());
                      itemMeta.addEnchant(Enchantment.SILK_TOUCH, 0, true);
                      itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
                      ima.setItemMeta(itemMeta);
                      pl.getInventory().setItem(8, ima);
                      Location location = pl.getLocation().add(0.0D, 0.0D, 0.0D);
                      Iterator var14 = Bukkit.getOnlinePlayers().iterator();

                      while(var14.hasNext()) {
                        Player PLAx = (Player)var14.next();
                        if (PLAx != pl) {
                          PLAx.sendBlockChange(location, ima.getType(), (byte)0);
                        }
                      }

                      game.player_last_hide.put(pl, location);
                      continue;
                    }

                    --count;
                    imaxx.setAmount(count);
                    pl.getInventory().setItem(8, imaxx);
                  } else {
                    imaxx = pl.getInventory().getItem(8);
                    imaxx.setAmount(20);
                    ItemMeta itemMetax = imaxx.getItemMeta();
                    itemMetax.setDisplayName("§aตอนนี้คุณกำลังเป็น §f" + imaxx.getType().toString().toLowerCase());
                    if (itemMetax.hasEnchant(Enchantment.SILK_TOUCH)) {
                      itemMetax.removeEnchant(Enchantment.SILK_TOUCH);
                    }

                    itemMetax.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
                    imaxx.setItemMeta(itemMetax);
                    pl.getInventory().setItem(8, imaxx);
                    var11 = pl.getActivePotionEffects().iterator();

                    while(var11.hasNext()) {
                      PotionEffect effect = (PotionEffect)var11.next();
                      pl.removePotionEffect(effect.getType());
                    }

                    if (game.player_last_hide.containsKey(pl)) {
                      ((Location)game.player_last_hide.get(pl)).getBlock().setType(Material.AIR);
                      var11 = Bukkit.getOnlinePlayers().iterator();

                      while(var11.hasNext()) {
                        PLA = (Player)var11.next();
                        PLA.sendBlockChange((Location)game.player_last_hide.get(pl), Material.AIR, (byte)0);
                      }

                      game.player_last_hide.remove(pl);
                    }
                  }

                  game.playerLocationHashMap.replace(pl, new game.PlayerLastLoc(xx, yy, zz));
                } else {
                  x = (int)pl.getLocation().getX();
                  y = (int)pl.getLocation().getY();
                  z = (int)pl.getLocation().getZ();
                  game.playerLocationHashMap.put(pl, new game.PlayerLastLoc(x, y, z));
                }
              }
            }
          }
        }
      }
    };
  }

  public void gameEnd() {
  }

  static {
    item_icon = new ItemStack(Material.GRASS);
    is_game_started = false;
  }

  public static class PlayerLastLoc {
    int x;
    int y;
    int z;

    PlayerLastLoc(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    PlayerLastLoc(Location loc) {
      this.x = (int)loc.getX();
      this.y = (int)loc.getY();
      this.z = (int)loc.getZ();
    }

    public int getX() {
      return this.x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return this.y;
    }

    public void setY(int y) {
      this.y = y;
    }

    public int getZ() {
      return this.z;
    }

    public void setZ(int z) {
      this.z = z;
    }
  }
}
