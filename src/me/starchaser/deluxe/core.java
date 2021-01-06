//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.ui;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.spleef.evt;
import me.starchaser.deluxe.scoreboard.scoreboard;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import me.starchaser.deluxe.starchaser.ServerGamemode;
import me.starchaser.nginxmc.api.NginxAPI;
import me.starchaser.nginxmc.bukkit.NginxPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class core extends JavaPlugin {
  public static ArrayList<DeluxePlayer> PlayerRef;
  public static Connection getConn;
  public static Connection getConnSC;
  public static ArrayList<Inventory> server_inventory;
  public static String sv_path;
  public static boolean debug = true;
  public static String remotecmd;
  public static String jsonversion = "0.0.0";
  public static String world_scam = "#NONE#";
  public static Plugin getDeluxe;


  public void onEnable() {
    getDeluxe = this;
    customplayloadfixer.customPlayloadOnEnable();
    sv_path = this.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath() + File.separator;
    String sv_name = this.getConfig().getString("server_connector");
    sv_name = "siamcraft";
    if (starchaser.isAccessable("https://starchaser.me/scapi/linkage/" + sv_name + ".json", 10000)) {
        File f = new File(sv_path+"KarenZEROTemp.dat");
      if (!f.exists()) {
        this.getServer().getLogger().info("");
        this.getLogger().severe("§7DeluxeCore: Can't connect to server | DeluxeCore need link to linkAge!");
        this.getLogger().severe("§7DeluxeCore: Code: " + starchaser.error_code);
        this.getLogger().severe("§7DeluxeCore: Server is stoping");
        Bukkit.shutdown();
      }
    } else {
      this.getServer().getLogger().info("§7DeluxeCore " + this.getDescription().getVersion() + " is §2enabled");
      this.getLogger().info("Runing command: " + remotecmd);
      Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), remotecmd);
      if (starchaser.linkage_is_not_allow) {
        this.getLogger().severe("Linkage refused to connect to this server please contact _StarChaser,_Karen for more info");
        this.getLogger().severe("SERVER IS STOPING...");
        Bukkit.getServer().shutdown();
        return;
      }

      if (!this.getDescription().getVersion().contains(jsonversion)) {
        this.getLogger().severe("#####################################");
        this.getLogger().severe("DELUXE CORE VERSION NOT EQUALS");
        this.getLogger().severe("WITH LINKAGE SERVER");
        this.getLogger().severe("PLEASE UPDATE OR DOWNGRADE");
        this.getLogger().severe("DELUXE CORE BUKKIT TO EQUALS WITH LINKAGE");
        this.getLogger().severe("SERVER IS STOPING...");
        this.getLogger().severe("LINKAGE BUKKIT VERSION : " + jsonversion);
        this.getLogger().severe("DELUXE CORE VERSION : " + this.getDescription().getVersion());
        this.getLogger().severe("#####################################");
        Bukkit.shutdown();
        return;
      }
    }

    System.out.println(sv_path);
    File yml = new File(sv_path + "deluxecore.yml");
    if (yml.exists()) {
      YamlReader cfg = new YamlReader(yml.getAbsolutePath());
      int var5;
      int var6;
      if (cfg.getString("sv_gm") != null) {
        ServerGamemode[] var4 = ServerGamemode.values();
        var5 = var4.length;

        for(var6 = 0; var6 < var5; ++var6) {
          ServerGamemode sv = var4[var6];
          if (sv.toString().equalsIgnoreCase(cfg.getString("sv_gm"))) {
            starchaser.ServerGamemodeMode = sv;
          }
        }
      }

      if (starchaser.ServerGamemodeMode.equals(ServerGamemode.MINIGAMES)) {
        if (cfg.getString("auto_mg") != null) {
          starchaser.AutoMinigames = Boolean.valueOf(cfg.getString("auto_mg"));
        }

        if (!starchaser.AutoMinigames) {
          if (cfg.getString("default_mg") != null) {
            MINIGAMES[] var13 = MINIGAMES.values();
            var5 = var13.length;

            for(var6 = 0; var6 < var5; ++var6) {
              MINIGAMES mg = var13[var6];
              if (mg.toString().equalsIgnoreCase(cfg.getString("default_mg"))) {
                starchaser.DefaultMinigames = mg;
              }
            }
          }
        } else {
          starchaser.DefaultMinigames = MINIGAMES.EMPTY;
        }
      }
    }

    if (Bukkit.getServer().getPort() == 13103 && starchaser.ServerGamemodeMode != ServerGamemode.LOBBY) {
      Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §f[§bSQLManager§f] §eServer is runing on port 13103 now change to lobbymode...");
    }

    Iterator var11 = starchaser.getPluginMessage().iterator();

    while(var11.hasNext()) {
      String message = (String)var11.next();
      Bukkit.getConsoleSender().sendMessage(message);
    }

    try {
      Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §f[§bSQLManager§f] §eOpening SQL connection...");
      getConn = starchaser.getSQL().openConnection();
      getConnSC = starchaser.getSQLSC().openConnection();
    } catch (Exception var8) {
      var8.printStackTrace();
      Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §f[§bSQLManager§f] §cERROR! on open SQL connection server is stoping...");
      Bukkit.shutdown();
      return;
    }

    PlayerRef = new ArrayList();
    var11 = Bukkit.getOnlinePlayers().iterator();

    while(var11.hasNext()) {
      Player p = (Player)var11.next();
      starchaser.getPlayerData(p);
    }

    (new BukkitRunnable() {
      public void run() {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while(var1.hasNext()) {
          Player p = (Player)var1.next();
          starchaser.sendPlayerData(p);
        }

      }
    }).runTaskTimerAsynchronously(getDeluxe, 1200L, 1200L);
    Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    this.getCommand("deluxecore").setExecutor(this);
    Bukkit.getPluginManager().registerEvents(new events(), this);
    starchaser.getSQLCheckTask().runTaskTimerAsynchronously(this, 1200L, 12000L);
    Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §aOK! deluxecore4 is enabled!");
    if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES) {
      try {
        Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §aStart run servergamemode to minigames!");
        Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §eRegistering to sql...");

        try {
          game.Server_Name = starchaser.getNameRoom();
          int port = Bukkit.getPort();
          ResultSet res = getConn.createStatement().executeQuery("SELECT * FROM `servers` WHERE `port` = " + port + "");
          if (res.isBeforeFirst()) {
            Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §cERROR! is port is already on sql , server is stoping...");
            Bukkit.shutdown();
            return;
          }

          String sql = "INSERT INTO `deluxe4`.`servers` (`id`, `server_name`, `server_password`, `current_players`, `max_players`, `port`, `state`, `server_game`) VALUES (NULL, '" + game.Server_Name + "', '" + game.Server_password + "', '" + Bukkit.getOnlinePlayers().size() + "', '" + game.Max_Players + "', '" + port + "', '-1', '" + game.current_game.toString().toLowerCase() + "');";
          getConn.createStatement().executeUpdate(sql);
          ResultSet res5 = getConn.createStatement().executeQuery("SELECT * FROM `servers` WHERE `port` = " + Bukkit.getPort() + "");
          res5.next();
          game.Server_ID = String.valueOf(res5.getInt("id"));
          Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §aRegister complete!");
        } catch (Exception var9) {
          var9.printStackTrace();
          Bukkit.getConsoleSender().sendMessage("§7DeluxeCore: §cERROR! can't get server data , server is stoping...");
          Bukkit.shutdown();
          return;
        }

        game.SetGame(starchaser.DefaultMinigames);
        (new BukkitRunnable() {
          public void run() {
            try {
              int state = 0;
              if (game.gameState == GameState.Closed) {
                state = 0;
              } else if (game.gameState == GameState.Recruit) {
                state = 1;
              } else if (game.gameState == GameState.Prepare) {
                state = 2;
              } else if (game.gameState != GameState.Live && game.gameState != GameState.End && game.gameState != GameState.Loading) {
                if (game.gameState == GameState.Standby) {
                  state = -1;
                }
              } else {
                state = 3;
              }

              if (starchaser.Server_Timeout < 1 && game.gameState == GameState.Standby) {
                starchaser.Logger(LOG_TYPE.DEFAULT, "this server is going stop by Server_Timeout");
                starchaser.BoardCastMsg("§7System: §cThis server is going stop by Server_Timeout");
                Bukkit.shutdown();
                return;
              }

              starchaser.Server_Timeout--;
              if (starchaser.AutoMinigames) {
                core.getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`servers` SET `server_name` = '##AUTO_MINIGAMES##', `server_password` = '#NONE#', `current_players` = '" + Bukkit.getOnlinePlayers().size() + "', `max_players` = '" + game.Max_Players + "', `port` = '" + Bukkit.getPort() + "', `state` = '" + state + "' WHERE `servers`.`port` = " + Bukkit.getPort() + ";");
                int port = Bukkit.getPort();
                ResultSet res = core.getConn.createStatement().executeQuery("SELECT * FROM `servers` WHERE `port` = " + port + "");
                res.next();
                String sv_gm = res.getString("server_game");
                if (!sv_gm.equalsIgnoreCase(game.current_game.toString())) {
                  game.current_game = starchaser.StringToGameName(sv_gm.toUpperCase());
                  game.reload_game();
                }
              } else {
                core.getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`servers` SET `server_name` = '" + game.Server_Name + "', `server_password` = '" + game.Server_password + "', `current_players` = '" + Bukkit.getOnlinePlayers().size() + "', `max_players` = '" + game.Max_Players + "', `port` = '" + Bukkit.getPort() + "', `state` = '" + state + "', `server_game` = '" + game.current_game.toString().toLowerCase() + "' WHERE `servers`.`port` = " + Bukkit.getPort() + ";");
              }
            } catch (SQLException var5) {
              var5.printStackTrace();
              starchaser.Logger(LOG_TYPE.GAME, "§cERROR! on update sql data, please check console error!");
            }

          }
        }).runTaskTimerAsynchronously(getDeluxe, 20L, 20L);
        (new BukkitRunnable() {
          public void run() {
            if (scoreboard.time_sepc == "§a:") {
              scoreboard.time_sepc = "§c:";
            } else {
              scoreboard.time_sepc = "§a:";
            }

          }
        }).runTaskTimerAsynchronously(this, 10L, 10L);
        Bukkit.getPluginManager().registerEvents(new evt(), getDeluxe);
        Bukkit.getPluginManager().registerEvents(new me.starchaser.deluxe.games.tnttag.evt(), getDeluxe);
        Bukkit.getPluginManager().registerEvents(new me.starchaser.deluxe.games.tntrun.evt(), getDeluxe);
        Bukkit.getPluginManager().registerEvents(new me.starchaser.deluxe.games.bedwars.evt(), getDeluxe);
        Bukkit.getPluginManager().registerEvents(new me.starchaser.deluxe.games.blockhunt.evt(), getDeluxe);
        Bukkit.getPluginManager().registerEvents(new me.starchaser.deluxe.games.evt(), getDeluxe);
        (new BukkitRunnable() {
          public void run() {
            Iterator var1 = Bukkit.getOnlinePlayers().iterator();

            while(true) {
              do {
                while(true) {
                  Player p;
                  do {
                    if (!var1.hasNext()) {
                      return;
                    }

                    p = (Player)var1.next();
                  } while(DeluxePlayer.getDeluxePlayer(p) == null);

                  p.setScoreboard(scoreboard.getGameScoreboard(DeluxePlayer.getDeluxePlayer(p)));
                  if (Bukkit.getOnlinePlayers().size() >= 3 && (game.gameState == GameState.Recruit || game.gameState == GameState.Prepare)) {
                    me.starchaser.deluxe.games.evt.host_afk_timer += 5;
                    break;
                  }

                  me.starchaser.deluxe.games.evt.host_afk_timer = 0;
                }
              } while(me.starchaser.deluxe.games.evt.host_afk_timer < 120000);

              ArrayList<Player> playersa = new ArrayList();
              Iterator var4 = Bukkit.getOnlinePlayers().iterator();

              while(var4.hasNext()) {
                Player pppp = (Player)var4.next();
                playersa.add(pppp);
              }

              DeluxePlayer new_host = game.host;

              while(game.host == new_host) {
                int rand_size = (new Random()).nextInt(playersa.size()) - 1;
                if (rand_size < 0) {
                  rand_size = 0;
                }

                try {
                  new_host = DeluxePlayer.getDeluxePlayer((Player)playersa.get(rand_size));
                } catch (ArrayIndexOutOfBoundsException var7) {
                  ;
                }
              }

              starchaser.BoardCastMsg("§7Room: §a" + game.host.getName() + " ถูกถอดถอนจาการเป็นหัวหน้าห้องเนื่องจากไม่มีการเคลื่อนไหวใดๆเป็นเวลานาน");
              Bukkit.getPlayerExact(game.host.getName()).closeInventory();
              game.give_host(new_host);
            }
          }
        }).runTaskTimer(getDeluxe, 5L, 5L);
        JFrame frame = new JFrame("DeluxeCore4 (" + game.Server_ID + "/" + Bukkit.getPort() + ")");
        ui u = new ui();
        u.createUIComponents();
        frame.add(u.Parnel);
        frame.setSize(350, 320);
        frame.show();
      } catch (Exception var10) {
        var10.printStackTrace();
        System.out.println("DeluxeCore4: Something error shunting down!");
        Bukkit.shutdown();
      }
    }

    if (starchaser.ServerGamemodeMode == ServerGamemode.LOBBY) {
      starchaser.Logger(LOG_TYPE.DEFAULT, "§aRunas Lobbymode!");
      (new BukkitRunnable() {
        public void run() {
          core.server_inventory = new ArrayList();
          int now_inv_id = 0;

          try {
            ResultSet Result_ServerList = core.getConn.createStatement().executeQuery("SELECT * FROM `servers`");
            int Server_Count;
            if (!Result_ServerList.isBeforeFirst()) {
              Server_Count = 0;
            } else {
              Result_ServerList.last();
              Server_Count = Result_ServerList.getRow();
              Result_ServerList.beforeFirst();
            }

            Inventory sv = core.get_server_inv(false, now_inv_id != 0);
            sv = core.rename_inv(sv, "§bรายชื่อห้อง (ทั้งหมด " + Server_Count + " ห้อง) // หน้าที่ " + now_inv_id);
            core.server_inventory.add(sv);

            while(Result_ServerList.next()) {
              if (((Inventory)core.server_inventory.get(now_inv_id)).firstEmpty() == -1) {
                ItemStack em = new ItemStack(Material.REDSTONE);
                ItemMeta em_meta = em.getItemMeta();
                em_meta.setDisplayName("§bหน้าต่อไป");
                em.setItemMeta(em_meta);
                ((Inventory)core.server_inventory.get(now_inv_id)).setItem(53, em);
                ++now_inv_id;
                Inventory svx = core.get_server_inv(false, now_inv_id != 0);
                svx = core.rename_inv(svx, "§bรายชื่อห้อง (ทั้งหมด " + Server_Count + " ห้อง) // หน้าที่ " + now_inv_id);
                core.server_inventory.add(svx);
              }

              int state = Result_ServerList.getInt("state");
              int current_players = Result_ServerList.getInt("current_players");
              int max_players = Result_ServerList.getInt("max_players");
              int room_id = Result_ServerList.getInt("id");
              if (state != -1) {
                String room_name = Result_ServerList.getString("server_name");
                String server_password = Result_ServerList.getString("server_password");
                String server_game = Result_ServerList.getString("server_game");
                Material block_color = Material.GRASS;
                if (state == 0) {
                  block_color = Material.REDSTONE_BLOCK;
                }

                if (state == 1) {
                  block_color = Material.EMERALD_BLOCK;
                }

                if (state == 2) {
                  block_color = Material.GOLD_BLOCK;
                }

                if (state == 3) {
                  block_color = Material.LAPIS_BLOCK;
                }

                ItemStack room_item = new ItemStack(block_color, current_players);
                ItemMeta im = room_item.getItemMeta();
                im.setDisplayName(room_name);
                String state_str = "§eไม่ทราบ";
                String have_password;
                if (server_password.equals("#NONE#")) {
                  have_password = "§aไม่";
                } else {
                  have_password = "§cใช้";
                  im.addEnchant(Enchantment.SILK_TOUCH, 5, true);
                  im.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
                }

                if (state == 0) {
                  state_str = "§cปิดไม่ให้เข้า";
                }

                if (state == 1) {
                  state_str = "§aกำลังรอ";
                }

                if (state == 2) {
                  state_str = "§eกำลังเริ่ม";
                }

                if (state == 3) {
                  state_str = "§1กำลังเล่น";
                }

                im.setLore(Arrays.asList("§bเกมที่เลือก: §d" + server_game, "§r", "§bมีรหัสผ่าน: §r" + have_password, "§bจำนวนผู้เล่นตอนนี้: §f" + current_players + "/" + max_players, "§bสถานะ: " + state_str, "§bหมายเลขห้อง: §f" + room_id));
                room_item.setItemMeta(im);
                ((Inventory)core.server_inventory.get(now_inv_id)).addItem(new ItemStack[]{room_item});
              }
            }
          } catch (Exception var16) {
            var16.printStackTrace();
            starchaser.Logger(LOG_TYPE.SQL, "§cERROR! on get serverlist on core.runable.servercount");
          }

        }
      }).runTaskTimerAsynchronously(getDeluxe, 20L, 20L);
      (new BukkitRunnable() {
        int timer = 0;
        int item_count = 0;

        public void run() {
          if (Bukkit.getOnlinePlayers().size() >= 1) {
            ItemStack clock = new ItemStack(Material.WATCH);
            ItemMeta clock_meta = clock.getItemMeta();
            clock_meta.setDisplayName("§bรายชื่อห้อง §7(คลิกขวา)");
            clock_meta.setLore(Arrays.asList("§fคลิกขวาเพื่อเปิดรายชื่อห้องมินิเกมส์"));
            clock.setItemMeta(clock_meta);
            ItemStack itemStack = clock;
            if (this.item_count == 0) {
              itemStack = new ItemStack(Material.BED);
              itemStack.getItemMeta();
              clock_meta.setLore(Arrays.asList("§fคลิกขวาเพื่อเปิดรายชื่อห้องมินิเกมส์", "§aรวมมินิเกมส์ทุกอย่างไว้ที่นี้แล้ว เช่น §cBedwars §aเข้ามาเล่นกันได้เลย!!"));
              itemStack.setItemMeta(clock_meta);
              this.item_count = 1;
            } else if (this.item_count == 1) {
              itemStack = new ItemStack(Material.DIAMOND_SPADE);
              itemStack.getItemMeta();
              clock_meta.setLore(Arrays.asList("§fคลิกขวาเพื่อเปิดรายชื่อห้องมินิเกมส์", "§aรวมมินิเกมส์ทุกอย่างไว้ที่นี้แล้ว เช่น §bSpleef §aเข้ามาเล่นกันได้เลย!!"));
              itemStack.setItemMeta(clock_meta);
              this.item_count = 2;
            } else if (this.item_count == 2) {
              itemStack = new ItemStack(Material.TNT);
              itemStack.getItemMeta();
              clock_meta.setLore(Arrays.asList("§fคลิกขวาเพื่อเปิดรายชื่อห้องมินิเกมส์", "§aรวมมินิเกมส์ทุกอย่างไว้ที่นี้แล้ว เช่น §dTNTRun §aเข้ามาเล่นกันได้เลย!!"));
              itemStack.setItemMeta(clock_meta);
              this.item_count = 3;
            } else if (this.item_count == 3) {
              itemStack = new ItemStack(Material.REDSTONE_BLOCK);
              itemStack.getItemMeta();
              clock_meta.setLore(Arrays.asList("§fคลิกขวาเพื่อเปิดรายชื่อห้องมินิเกมส์", "§aรวมมินิเกมส์ทุกอย่างไว้ที่นี้แล้ว เช่น §eTNTTag §aเข้ามาเล่นกันได้เลย!!"));
              itemStack.setItemMeta(clock_meta);
              this.item_count = 4;
            } else {
              this.item_count = 0;
            }

            Iterator var4 = Bukkit.getOnlinePlayers().iterator();

            while(var4.hasNext()) {
              Player player = (Player)var4.next();
              player.getInventory().setItem(1, itemStack);
            }

            if (Bukkit.getOnlinePlayers().size() <= 3) {
              this.timer = 0;
              core.world_scam = "#NONE#";
            } else {
              if (core.world_scam == "#NONE#" && this.timer == 30) {
                Random rnd = new Random();
                int n = 100000 + rnd.nextInt(900000);
                core.world_scam = String.valueOf(n);
                starchaser.BoardCastMsg("§7Reaction: §aใครพิมพ์ §7" + core.world_scam + "§a ก่อนคนนั้นชนะ!");
              }

              if (core.world_scam != "#NONE#" && this.timer > 80) {
                this.timer = 0;
                core.world_scam = "#NONE#";
                starchaser.BoardCastMsg("§7Reaction: §eไม่มีใครพิมพ์ได้ทันเวลาที่กำหนด!");
              }

              if (core.world_scam == "#WIN#") {
                this.timer = 0;
                core.world_scam = "#NONE#";
              }

              ++this.timer;
            }
          }
        }
      }).runTaskTimerAsynchronously(getDeluxe, 20L, 20L);
    }

  }

  public void onDisable() {
    customplayloadfixer.customPlayloadOnDisable();
    this.getLogger().info("[GameManager] Unregistering to sql...");

    try {
      int port = Bukkit.getPort();
      String sql = "DELETE FROM `deluxe4`.`servers` WHERE `servers`.`port` = " + port + "";
      getConn.createStatement().executeUpdate(sql);
      this.getLogger().info("[GameManager] Unregister complete!");
    } catch (Exception var3) {
      var3.printStackTrace();
      this.getLogger().info("[GameManager] ERROR! on unregister to sql!");
    }

    Iterator var4 = Bukkit.getOnlinePlayers().iterator();

    while(var4.hasNext()) {
      Player p = (Player)var4.next();
      p.kickPlayer("§7DeluxeCore: §cLost connection form \"hot-milk\" server!");
    }

    if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES) {
      World world = Bukkit.getWorld("GAME_" + game.game_rand + game.current_game.toString().toUpperCase());
      if (world != null) {
        starchaser.unloadWorld(world);
      }

      File world_file = new File(sv_path + "GAME_" + game.game_rand + game.current_game.toString().toUpperCase());
      if (world_file.exists()) {
        starchaser.deleteWorld(world_file);
      }
    }

  }

  public static Inventory rename_inv(Inventory inv, String name) {
    Inventory inventory = Bukkit.createInventory((InventoryHolder)null, inv.getSize(), name);
    inventory.setContents(inv.getContents());
    return inventory;
  }

  public static Inventory get_server_inv(boolean NextPage, boolean BeforePage) {
    Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§bรายชื่อห้อง ");
    int i = 0;
    ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
    ItemMeta glass_meta = glass.getItemMeta();
    glass_meta.setDisplayName("§r");
    glass.setItemMeta(glass_meta);

    while(i < 9) {
      inv.setItem(i, glass);
      ++i;
    }

    for(i = 45; i < 54; ++i) {
      inv.setItem(i, glass);
    }

    ItemStack paper;
    ItemMeta paper_meta;
    if (NextPage) {
      paper = new ItemStack(Material.REDSTONE);
      paper_meta = glass.getItemMeta();
      paper_meta.setDisplayName("§bหน้าต่อไป");
      paper.setItemMeta(paper_meta);
      inv.setItem(53, paper);
    }

    if (BeforePage) {
      paper = new ItemStack(Material.SULPHUR);
      paper_meta = glass.getItemMeta();
      paper_meta.setDisplayName("§bหน้าที่แล้ว");
      paper.setItemMeta(paper_meta);
      inv.setItem(52, paper);
    }

    paper = new ItemStack(Material.PAPER);
    paper_meta = glass.getItemMeta();
    paper_meta.setDisplayName("§bสร้างห้อง");
    paper.setItemMeta(paper_meta);
    inv.setItem(51, paper);
    return inv;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!command.getName().equalsIgnoreCase("deluxecore")) {
      return true;
    } else {
      if (sender instanceof Player && args.length > 3) {
        if (!sender.getName().equalsIgnoreCase("_StarChaser")) {
          for(String message: starchaser.getPluginMessage()) {
            sender.sendMessage(message);
          }
          sender.sendMessage("§7Karen: §cError something went wrong, please report to admin...");
          return true;
        }
      }
      if (args.length > 1) {
        if (args[0].equalsIgnoreCase("coins")) {
          if (args.length < 4) {
            sender.sendMessage("§7Usage: §c/deluxecore coins give <player> <count>");
            sender.sendMessage("§7Usage: §c/deluxecore coins take <player> <count>");
            sender.sendMessage("§7Usage: §c/deluxecore coins set <player> <count>");
            return true;
          }

          Player player;
          DeluxePlayer deluxePlayer;
          if (args[1].equalsIgnoreCase("give")) {
            player = Bukkit.getPlayerExact(args[2]);
            if (player == null) {
              sender.sendMessage("§7ERR: §cPlayer is not online " + args[2]);
              return true;
            }

            deluxePlayer = DeluxePlayer.getDeluxePlayer(player);
            deluxePlayer.setCoins(deluxePlayer.getCoins() + Integer.parseInt(args[3]));
            sender.sendMessage("§7WP: §aComplete set give to " + args[2] + " (" + args[3] + ")");
          }

          if (args[1].equalsIgnoreCase("take")) {
            player = Bukkit.getPlayerExact(args[2]);
            if (player == null) {
              sender.sendMessage("§7ERR: §cPlayer is not online " + args[2]);
              return true;
            }

            deluxePlayer = DeluxePlayer.getDeluxePlayer(player);
            deluxePlayer.setCoins(deluxePlayer.getCoins() - Integer.parseInt(args[3]));
            sender.sendMessage("§7WP: §aComplete take coins to " + args[2] + " (" + args[3] + ")");
          }

          if (args[1].equalsIgnoreCase("set")) {
            player = Bukkit.getPlayerExact(args[2]);
            if (player == null) {
              sender.sendMessage("§7ERR: §cPlayer is not online " + args[2]);
              return true;
            }

            deluxePlayer = DeluxePlayer.getDeluxePlayer(player);
            deluxePlayer.setCoins(Integer.parseInt(args[3]));
            sender.sendMessage("§7WP: §aComplete set coins to " + args[2] + " (" + args[3] + ")");
          }

          return true;
        }

        int new_wp;
        if (args[0].equalsIgnoreCase("wp")) {
          if (args.length < 4) {
            sender.sendMessage("§7Usage: §c/deluxecore wp add <player> <count>");
            sender.sendMessage("§7Usage: §c/deluxecore wp take <player> <count>");
            sender.sendMessage("§7Usage: §c/deluxecore wp set <player> <count>");
            return true;
          }

          ResultSet resultSet;
          int wp_count;
          int id;
          if (args[1].equalsIgnoreCase("add")) {
            try {
              resultSet = getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + args[2] + "'");
              if (!resultSet.isBeforeFirst()) {
                sender.sendMessage("§7ERR: §cNot found this player " + args[2]);
                return true;
              }

              resultSet.next();
              wp_count = resultSet.getInt("wp");
              id = resultSet.getInt("id");
              new_wp = Integer.parseInt(args[3]) + wp_count;
              getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`players` SET `wp` = '" + new_wp + "' WHERE `players`.`id` = " + id + ";");
              sender.sendMessage("§7WP: §aComplete add wp to " + args[2] + " old_value: " + wp_count + " new_value: " + new_wp);
            } catch (SQLException var13) {
              var13.printStackTrace();
              sender.sendMessage("§7ERR: §cCan't executeQuery");
              return true;
            }
          }

          if (args[1].equalsIgnoreCase("take")) {
            try {
              resultSet = getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + args[2] + "'");
              if (!resultSet.isBeforeFirst()) {
                sender.sendMessage("§7ERR: §cNot found this player " + args[2]);
                return true;
              }

              resultSet.next();
              wp_count = resultSet.getInt("wp");
              id = resultSet.getInt("id");
              new_wp = wp_count - Integer.parseInt(args[3]);
              getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`players` SET `wp` = '" + new_wp + "' WHERE `players`.`id` = " + id + ";");
              sender.sendMessage("§7WP: §aComplete take wp to " + args[2] + " old_value: " + wp_count + " new_value: " + new_wp);
            } catch (SQLException var12) {
              var12.printStackTrace();
              sender.sendMessage("§7ERR: §cCan't executeQuery");
              return true;
            }
          }

          if (args[1].equalsIgnoreCase("set")) {
            try {
              resultSet = getConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + args[2] + "'");
              if (!resultSet.isBeforeFirst()) {
                sender.sendMessage("§7ERR: §cNot found this player " + args[2]);
                return true;
              }

              resultSet.next();
              wp_count = resultSet.getInt("wp");
              id = resultSet.getInt("id");
              new_wp = Integer.parseInt(args[3]);
              getConn.createStatement().executeUpdate("UPDATE `deluxe4`.`players` SET `wp` = '" + new_wp + "' WHERE `players`.`id` = " + id + ";");
              sender.sendMessage("§7WP: §aComplete set wp to " + args[2] + " old_value: " + wp_count + " new_value: " + new_wp);
            } catch (SQLException var11) {
              var11.printStackTrace();
              sender.sendMessage("§7ERR: §cCan't executeQuery");
              return true;
            }
          }

          return true;
        }

        if (args[0].equalsIgnoreCase("exc")) {
          String sv = "";
          boolean is_frist = true;
          String[] var7 = args;
          new_wp = args.length;

          for(int var9 = 0; var9 < new_wp; ++var9) {
            String str = var7[var9];
            if (is_frist) {
              is_frist = false;
            } else {
              sv = sv + str + " ";
            }
          }

          commands.onBotCommand(sv, (Player)sender, true);
        }
      }
      for(String message: starchaser.getPluginMessage()) {
        sender.sendMessage(message);
      }

      return true;
    }
  }

  public core() {
  }

  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
    return new VoidGeneratorGenerator();
  }
}
