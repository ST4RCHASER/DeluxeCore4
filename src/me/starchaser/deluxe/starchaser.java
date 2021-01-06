//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import com.nametagedit.plugin.NametagEdit;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.sql.MySQL;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.apache.logging.log4j.status.StatusLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class starchaser {
  public static starchaser.ServerGamemode ServerGamemodeMode;
  public static MINIGAMES DefaultMinigames;
  public static boolean AutoMinigames;
  public static int max_level;
  public static int Server_Timeout;
  public static int connection_try_count;
  public static String Last_Debug_Chat;
  public static String Last_Server_Chat;
  public static String Last_Player_Chat;
  public static boolean linkage_is_not_allow;

  public starchaser() {
  }

  public static MySQL getSQL() {
    MySQL sql = new MySQL("sql.starchaser.me", "3306", "deluxe4", "siamcraft_plugin", "5cTPz8ha0qdI1jqr");
    return sql;
  }

  public static MySQL getSQLSC() {
    MySQL sql = new MySQL("sql.starchaser.me", "3306", "siamcraft", "siamcraft_plugin", "5cTPz8ha0qdI1jqr");
    return sql;
  }

  public static Location getLobbyLocaction() {
    return new Location(Bukkit.getWorld("Main_Lobby"), 144.0D, 77.0D, 37.0D, -178.0F, 1.6F);
  }

  public static String getNameRoom() {
    ArrayList<String> name_list = new ArrayList();
    name_list.add("siamcraft.net มาเล่นกันเยอะๆนะ");
    name_list.add("ฉันต้องเป็นที่หนึ่งให้ได้เลย!");
    name_list.add("เข้ามา ถ้าเจ๋งจริง");
    name_list.add("ไม่ได้ตั้งชื่อห้อง");
    return (String)name_list.get((new Random()).nextInt(4));
  }

  public static ArrayList<String> getPluginMessage() {
    String SeasonName = "§6§lSeason 4 §f§l- §d§lSweet Party";
    ArrayList<String> m = new ArrayList();
    m.add("§r");
    m.add("§e§lMinecraft DeluxeCore " + core.getDeluxe.getDescription().getVersion());
    m.add(SeasonName);
    m.add("§aSystem,Script: §b_StarChaser");
    m.add("§aWeb,Database: §bERTH180");
    m.add("§aSupport: §bkana2011th");
    if (isDbConnected(core.getConn)) {
      m.add("§bSQL: §aConnected");
    } else {
      m.add("§bSQL: §cDisconnected retrying (" + connection_try_count + "/5)");
    }

    m.add("§bServerGamemode: §a" + ServerGamemodeMode.toString() + " // " + game.gameState.toString() + " // " + game.current_game.toString());
    m.add("§r");
    return m;
  }

  public static void sendToServer(String player_name, String targetServer) {
    Player player = Bukkit.getPlayerExact(player_name);
    player.sendMessage("§7Portal: §eกำลังเชื่อมต่อกับเซิร์ฟเวอร์ " + targetServer + "...");
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(b);

    try {
      out.writeUTF("Connect");
      out.writeUTF(targetServer);
    } catch (IOException var6) {
      ;
    }

    player.sendPluginMessage(core.getDeluxe, "BungeeCord", b.toByteArray());
  }

  public static boolean CreateAccount(Player player) {
    try {
      core.getConn.createStatement().executeUpdate("INSERT INTO `nginx`.`players` (`id`, `username`, `class`, `ooc`, `level`, `xp`, `title`, `coins`, `feather`, `wp`) VALUES (NULL, '" + player.getName() + "', '0', '0', '1', '0', '0', '0', '0', '0');");
      Logger(starchaser.LOG_TYPE.PLAYER, "§aAccount Created! [" + player.getName() + "]");
      player.sendMessage("§7Account: §aสร้างบัญชีเรียบร้อยแล้ว");
      return true;
    } catch (SQLException var2) {
      var2.printStackTrace();
      Logger(starchaser.LOG_TYPE.PLAYER, "§cError on create account... (TASK: starchaser.createaccount) [" + player.getName() + "]");
      return false;
    }
  }

  public static BukkitRunnable getSQLCheckTask() {
    return new BukkitRunnable() {
      public void run() {
        if (starchaser.isDbConnected(core.getConn)) {
          starchaser.connection_try_count = 0;
        } else {
          starchaser.Logger(starchaser.LOG_TYPE.SQL, "§cLost connection for mysql server retrying... [" + starchaser.connection_try_count + "]");

          try {
            core.getConn = starchaser.getSQL().openConnection();
            starchaser.connection_try_count = 0;
            starchaser.Logger(starchaser.LOG_TYPE.SQL, "§aOK! SQL Connection open!");
          } catch (Exception var2) {
            var2.printStackTrace();
            starchaser.Logger(starchaser.LOG_TYPE.SQL, "§cERROR! on open SQL connection please check error on console...");
            ++starchaser.connection_try_count;
            if (starchaser.connection_try_count > 5) {
              starchaser.Logger(starchaser.LOG_TYPE.SQL, "§cOut of limit(" + starchaser.connection_try_count + ") retry create connection to sql , server is stoping...");
              Bukkit.shutdown();
              return;
            }
          }

        }
      }
    };
  }

  public static void sendMessageBeta(Player p) {
    if (ServerGamemodeMode == starchaser.ServerGamemode.MINIGAMES) {
      p.sendMessage("§0§m----------------------------------------------------");
      p.sendMessage("§6                                ⚠");
      p.sendMessage("§f                 This is development build");
      p.sendMessage("§r");
      p.sendMessage("§fDon't expect shit to work perfectly as this very much a work in progress");
      p.sendMessage("§fDon't report bugs because we are aware Don't complain about missing features because we are adding them.");
      p.sendMessage("§fSit back and enjoy. Visit ts.starchaser.me if you want to help out!");
      p.sendMessage("§r");
      p.sendMessage("§0oh and yes, you will get seizures");
      p.sendMessage("§0§m----------------------------------------------------");
    }

    if (ServerGamemodeMode == starchaser.ServerGamemode.LOBBY) {
      p.sendMessage("§0§m----------------------------------------------------");
      p.sendMessage("§6                                       ⚠");
      p.sendMessage("§f                 เซิร์ฟเวอร์ กำลังอยู่ในช่วงวางระบบมินิเกมส์ใหม่");
      p.sendMessage("§r");
      p.sendMessage("§fไม่!! อย่าเพิ่งไปซีเรียส กับผลงานแย่ๆชิ้นนี้");
      p.sendMessage("ทางเรากำลังรีบทำให้เสร็จเร็วที่สุดและออกมามีคุณภาพมากที่สุด");
      p.sendMessage("§fสิ่งที่จะยังไม่มีในต่อไปนี้เรากำลังจะค่อยๆเพิ่มเข้ามา:");
      p.sendMessage("ยศมินิเกมส์ , แชทสี-ชื่อสี , เหรียญ , ของใน backpack");
      p.sendMessage("ข้อมูลของผู้เล่นทุกคนเรารับประกันได้ว่าอยู่ครบ หลังจากที่ระบบมินิเกมส์ของเราเสร็จแล้ว");
      p.sendMessage("ทางเราจะเปิดระบบโอนย้าย ให้ผู้เล่นโอนย้ายข้อมูลและพร้อมเล่นทันที");
      p.sendMessage("§fโปรดนั่งเฉยๆแล้วสนุกไปกับมันก่อนนะ แต่ในส่วนในเซิร์ฟเวอร์ที่เกี่ยวกับการเอาชีวิตรอดจะเปิดตามปกติจ้า");
      p.sendMessage("§r");
      p.sendMessage("§0oh and yes, you will get seizures");
      p.sendMessage("§0§m----------------------------------------------------");
    }

  }

  public static void sendActionbar(Player p, String message) {
    IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
    PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
  }

  public static void sendActionbar(String message) {
    Iterator var1 = Bukkit.getOnlinePlayers().iterator();

    while(var1.hasNext()) {
      Player p = (Player)var1.next();
      sendActionbar(p, message);
    }

  }

  public static boolean getPlayerData(Player player) {
    try {
      ResultSet res_point = core.getConnSC.createStatement().executeQuery("SELECT * FROM `authme` WHERE `username` LIKE '" + player.getName() + "'");
      res_point.next();
      int point = res_point.getInt("mcshop_points");
      ResultSet resultSet = core.getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + player.getName() + "'");
      resultSet.next();
      DeluxePlayer dp = new DeluxePlayer(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getInt("class"), resultSet.getInt("level"), resultSet.getInt("xp"), resultSet.getInt("title"), resultSet.getInt("coins"), true, point, resultSet.getInt("feather"));
      DeluxePlayer.addDeluxePlayer(dp);
      Logger(starchaser.LOG_TYPE.DEBUG, "ID:" + dp.getId());
      Logger(starchaser.LOG_TYPE.DEBUG, "String: " + dp.getName());
      Logger(starchaser.LOG_TYPE.DEBUG, "Level:" + dp.getLevel().get_Int());
      Logger(starchaser.LOG_TYPE.DEBUG, "XP: " + dp.getLevel().getXP());
      Logger(starchaser.LOG_TYPE.PLAYER, "§aPlayer data get!... [" + player.getName() + "]");
      player.sendMessage("§7Account: §eเรียบร้อยแล้ว!");
      String ministr;
      if (dp.getPlayerClass().getId() > 0) {
        ministr = dp.getPlayerClass().getStr().substring(3, 8) + " §r";
      } else {
        ministr = "§r";
      }

      NametagEdit.getApi().setPrefix(player, ministr);
      return true;
    } catch (Exception var6) {
      var6.printStackTrace();
      Logger(starchaser.LOG_TYPE.PLAYER, "§cError on get player data... [" + player.getName() + "]");
      return false;
    }
  }

  public static DeluxePlayer MakePlayerData(String name) {
    try {
      ResultSet res_point = core.getConnSC.createStatement().executeQuery("SELECT * FROM `authme` WHERE `username` LIKE '" + name + "'");
      res_point.next();
      int point = res_point.getInt("mcshop_points");
      ResultSet resultSet = core.getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + name + "'");
      resultSet.next();
      DeluxePlayer dp = new DeluxePlayer(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getInt("class"), resultSet.getInt("level"), resultSet.getInt("xp"), resultSet.getInt("title"), resultSet.getInt("coins"), false, point, resultSet.getInt("feather"));
      return dp;
    } catch (Exception var5) {
      var5.printStackTrace();
      Logger(starchaser.LOG_TYPE.PLAYER, "§cError on get custom player data... [" + name + "]");
      return null;
    }
  }

  public static boolean sendPlayerData(Player player) {
    DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(player);
    return sendPlayerData(dp);
  }

  public static boolean sendPlayerData(DeluxePlayer dp) {
    try {
      core.getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`players` SET `class` = '" + dp.getPlayerClass().getId() + "', `level` = '" + dp.getLevel().get_Int() + "', `xp` = '" + dp.getLevel().getXP() + "', `title` = '" + dp.getTitle().getId() + "', `coins` = '" + dp.getCoins() + "' WHERE `players`.`id` = " + dp.getId() + ";");
      Logger(starchaser.LOG_TYPE.PLAYER, "§aPlayer data sent! [" + dp.getName() + "]");
      return true;
    } catch (Exception var2) {
      var2.printStackTrace();
      Logger(starchaser.LOG_TYPE.PLAYER, "§cError on send player data... (TASK: starchaser.getplayerdata) [" + dp.getName() + "]");
      return false;
    }
  }

  public static boolean isDbConnected(Connection connection) {
    try {
      if (connection == null) {
        return false;
      } else {
        return !connection.isClosed();
      }
    } catch (SQLException var2) {
      return false;
    }
  }

  public static void BoardCastMsg(String str) {
    Logger(starchaser.LOG_TYPE.BC, str);
    Iterator var1 = Bukkit.getOnlinePlayers().iterator();

    while(var1.hasNext()) {
      Player p = (Player)var1.next();
      p.sendMessage(str);
    }

  }

  public static void Logger(final starchaser.LOG_TYPE lt, final String message) {
    (new BukkitRunnable() {
      public void run() {
        if (!core.debug && lt == starchaser.LOG_TYPE.DEBUG) {
          starchaser.Last_Debug_Chat = message;
        } else {
          String prefix = "§7DeluxeCore: §a";
          if (lt.equals(starchaser.LOG_TYPE.PLAYER)) {
            prefix = "§f[§bPlayerManager§f] §f";
            starchaser.Last_Server_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.DEBUG)) {
            prefix = "§f[§eDEBUG§f] §f";
            starchaser.Last_Debug_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.SQL)) {
            prefix = "§f[§bSQLManager§f] §f";
            starchaser.Last_Server_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.CHAT)) {
            prefix = "§f[§bChatManager§f] §f";
            starchaser.Last_Player_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.COMMAND)) {
            prefix = "§f[§bCommandManager§f] §f";
            starchaser.Last_Server_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.GAME)) {
            prefix = "§f[§bGameManager§f] §f";
            starchaser.Last_Server_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.NONE)) {
            prefix = "§r";
            starchaser.Last_Server_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.BC)) {
            prefix = "§f[§bServerMessage§f] §f";
            starchaser.Last_Server_Chat = message;
          }

          if (lt.equals(starchaser.LOG_TYPE.WORLD)) {
            prefix = "§f[§bWorldManager§f] §f";
            starchaser.Last_Server_Chat = message;
          }

          Bukkit.getConsoleSender().sendMessage(prefix + message);
        }
      }
    }).runTask(core.getDeluxe);
  }

  public static void unloadWorld(World world) {
    try {
      Logger(starchaser.LOG_TYPE.WORLD, "WORLD LIST: " + Bukkit.getWorlds().toString());
    } catch (IllegalPluginAccessException var4) {
      Bukkit.getLogger().info("WORLD LIST: " + Bukkit.getWorlds().toString());
    }

    if (world != null) {
      Bukkit.getServer().unloadWorld(world, true);

      try {
        Logger(starchaser.LOG_TYPE.WORLD, "WORLD " + world.getName() + " UNLOADED!");
      } catch (IllegalPluginAccessException var3) {
        Bukkit.getLogger().info("WORLD " + world.getName() + " UNLOADED!");
      }
    } else {
      try {
        Logger(starchaser.LOG_TYPE.WORLD, "WORLD NOT FOUND CAN'T DELETE!");
      } catch (IllegalPluginAccessException var2) {
        Bukkit.getLogger().info("WORLD NOT FOUND CAN'T DELETE!");
      }
    }

  }

  public static boolean deleteWorld(File path) {
    if (path.exists()) {
      File[] files = path.listFiles();

      for(int i = 0; i < files.length; ++i) {
        if (files[i].isDirectory()) {
          deleteWorld(files[i]);
        } else {
          files[i].delete();
        }
      }
    }

    try {
      Logger(starchaser.LOG_TYPE.WORLD, "WORLD DELETED! (" + path.getPath() + ")");
    } catch (IllegalPluginAccessException var3) {
      Bukkit.getLogger().info("WORLD DELETED! (" + path.getPath() + ")");
    }

    return path.delete();
  }

  public static TEAM_COLOR StringToTeamColor(String team_name) {
    TEAM_COLOR team = TEAM_COLOR.NONE;
    TEAM_COLOR[] var2 = TEAM_COLOR.values();
    int var3 = var2.length;

    for(int var4 = 0; var4 < var3; ++var4) {
      TEAM_COLOR a = var2[var4];
      if (a.toString().equalsIgnoreCase(team_name)) {
        team = a;
      }
    }

    return team;
  }

  public static MINIGAMES StringToGameName(String game_name) {
    MINIGAMES game = MINIGAMES.EMPTY;
    MINIGAMES[] var2 = MINIGAMES.values();
    int var3 = var2.length;

    for(int var4 = 0; var4 < var3; ++var4) {
      MINIGAMES a = var2[var4];
      if (a.toString().equalsIgnoreCase(game_name)) {
        game = a;
      }
    }

    return game;
  }

  public static void openRankShop(DeluxePlayer dp, Player p) {
    Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, "§bร้านค้า");
    ItemStack titan_rank = new ItemStack(Material.IRON_BLOCK);
    ItemMeta meta = titan_rank.getItemMeta();
    meta.setDisplayName("§aอัพเกรดไปยศ §b§lTITAN");
    meta.setLore(Arrays.asList("§r", "§aค่าใช้จ่าย §e30000 §6Coins", "§bคลิกเพื่ออัพเกรดทันที"));
    titan_rank.setItemMeta(meta);
    ItemStack hero_rank = new ItemStack(Material.DIAMOND_BLOCK);
    meta.setDisplayName("§aอัพเกรดไปยศ §d§lHERO");
    meta.setLore(Arrays.asList("§r", "§aค่าใช้จ่าย §e50000 §6Coins", "§bคลิกเพื่ออัพเกรดทันที"));
    hero_rank.setItemMeta(meta);
    ItemStack master_rank = new ItemStack(Material.GOLD_BLOCK);
    meta.setDisplayName("§aอัพเกรดไปยศ §6§lMASTER");
    meta.setLore(Arrays.asList("§r", "§aค่าใช้จ่าย §e120000 §6Coins", "§bคลิกเพื่ออัพเกรดทันที"));
    master_rank.setItemMeta(meta);
    ItemStack legend_rank = new ItemStack(Material.EMERALD_BLOCK);
    meta.setDisplayName("§aอัพเกรดไปยศ §a§lLEGEND");
    meta.setLore(Arrays.asList("§r", "§aค่าใช้จ่าย §e180000 §6Coins", "§bคลิกเพื่ออัพเกรดทันที"));
    legend_rank.setItemMeta(meta);
    ItemStack full_rank = new ItemStack(Material.BARRIER);
    meta.setDisplayName("§cไม่สามารถอัพเกรดได้อีกแล้ว");
    meta.setLore(Arrays.asList("§r", "§aไม่สามารถอัพเกรดยศเพิ่มได้อีกแล้วเนื่องจากไม่มียศต่อจากนี้แล้ว!"));
    full_rank.setItemMeta(meta);
    int class_id = dp.getPlayerClass().getId();
    if (class_id == 0) {
      if (dp.getCoins() >= 30000) {
        inventory.setItem(13, titan_rank);
      } else {
        titan_rank = new ItemStack(Material.BARRIER);
        meta.setDisplayName("§aอัพเกรดไปยศ §b§lTITAN");
        meta.setLore(Arrays.asList("§r", "§cต้องการอีก §f" + (30000 - dp.getCoins()) + " §6Coins §cแจึงจะสามารถอัพเกรดไปยศนี้ได้"));
        titan_rank.setItemMeta(meta);
        inventory.setItem(13, titan_rank);
      }
    } else if (class_id == 1) {
      if (dp.getCoins() >= 50000) {
        inventory.setItem(13, hero_rank);
      } else {
        hero_rank = new ItemStack(Material.BARRIER);
        meta.setDisplayName("§aอัพเกรดไปยศ §d§lHERO");
        meta.setLore(Arrays.asList("§r", "§cต้องการอีก §f" + ('썐' - dp.getCoins()) + " §6Coins §cแจึงจะสามารถอัพเกรดไปยศนี้ได้"));
        hero_rank.setItemMeta(meta);
        inventory.setItem(13, hero_rank);
      }
    } else if (class_id == 2) {
      if (dp.getCoins() >= 120000) {
        inventory.setItem(13, master_rank);
      } else {
        master_rank = new ItemStack(Material.BARRIER);
        meta.setDisplayName("§aอัพเกรดไปยศ §6§lMASTER");
        meta.setLore(Arrays.asList("§r", "§cต้องการอีก §f" + (120000 - dp.getCoins()) + " §6Coins §cแจึงจะสามารถอัพเกรดไปยศนี้ได้"));
        master_rank.setItemMeta(meta);
        inventory.setItem(13, master_rank);
      }
    } else if (class_id == 3) {
      if (dp.getCoins() >= 180000) {
        inventory.setItem(13, legend_rank);
      } else {
        legend_rank = new ItemStack(Material.BARRIER);
        meta.setDisplayName("§aอัพเกรดไปยศ §a§lLEGEND");
        meta.setLore(Arrays.asList("§r", "§cต้องการอีก §f" + (180000 - dp.getCoins()) + " §6Coins §cแจึงจะสามารถอัพเกรดไปยศนี้ได้"));
        legend_rank.setItemMeta(meta);
        inventory.setItem(13, legend_rank);
      }
    } else {
      inventory.setItem(13, full_rank);
    }

    p.openInventory(inventory);
  }
  public static int error_code = 0;
    public static boolean isAccessable(String url, int timeout) {
    try {
      HttpsURLConnectionImpl connection = (HttpsURLConnectionImpl)(new URL(url)).openConnection();
      connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
      int responseCode = connection.getResponseCode();
      if (responseCode != 200) {
        error_code = responseCode;
        return true;
      } else {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject)parser.parse(new BufferedReader(new InputStreamReader(connection.getInputStream())));
        StatusLogger.getLogger().info(json.toJSONString());
        core.remotecmd = (String)json.get("cmd");
        core.jsonversion = (String)json.get("bukkit_linkage_version");
        linkage_is_not_allow = Boolean.getBoolean((String)json.get("disable"));
        error_code = 0;
        return false;
      }
    } catch (IOException var6) {
      var6.printStackTrace();
      error_code = 1;
      return true;
    } catch (ParseException var7) {
      error_code = 2;
      var7.printStackTrace();
      return true;
    }
  }

  static {
    ServerGamemodeMode = starchaser.ServerGamemode.MINIGAMES;
    DefaultMinigames = MINIGAMES.SPLEEF;
    AutoMinigames = false;
    max_level = 1000;
    Server_Timeout = 7200;
    Last_Debug_Chat = "NONE";
    Last_Server_Chat = "NONE";
    Last_Player_Chat = "NONE";
  }

  public static enum LOG_TYPE {
    PLAYER,
    CHAT,
    SQL,
    NONE,
    DEFAULT,
    COMMAND,
    GAME,
    BC,
    DEBUG,
    WORLD;

    private LOG_TYPE() {
    }
  }

  public static enum ServerGamemode {
    LOBBY,
    MINIGAMES,
    EVENT;

    private ServerGamemode() {
    }
  }
}
