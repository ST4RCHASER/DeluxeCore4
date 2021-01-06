//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.bungee;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import me.starchaser.deluxe.bungee.cmd.gbc;
import me.starchaser.deluxe.bungee.cmd.hub_fun;
import me.starchaser.deluxe.bungee.cmd.lobby;
import me.starchaser.deluxe.bungee.cmd.ooc;
import me.starchaser.deluxe.bungee.cmd.punisher;
import me.starchaser.deluxe.bungee.cmd.report;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.about;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.b_about;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.b_help;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.b_pl;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.b_plugins;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.b_questmark;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.b_ver;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.b_version;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.ban;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.ban_ip;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.e_ban_ip;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.eban;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.help;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.icanhasbukkit;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.mc_me;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.pl;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.plugins;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.questmark;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.ver;
import me.starchaser.deluxe.bungee.cmd.blockcmd_fun.version;
import me.starchaser.sql.MySQL;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

public class core extends Plugin {
  public static Connection getBungeeConn;
  public static Plugin getBungeeDeluxe;
  public static String version_string = "§b§lHEAVENLY HAVEN";

  public core() {
  }

  public void onEnable() {
    getBungeeDeluxe = this;
    this.getProxy().getPluginManager().registerListener(this, new events());
    this.getProxy().getPluginManager().registerCommand(this, new lobby());
    this.getProxy().getPluginManager().registerCommand(this, new hub_fun());
    this.getProxy().getPluginManager().registerCommand(this, new report());
    this.getProxy().getPluginManager().registerCommand(this, new punisher());
    this.getProxy().getPluginManager().registerCommand(this, new ooc());
    this.getProxy().getPluginManager().registerCommand(this, new gbc());
    this.getProxy().getPluginManager().registerCommand(this, new hub_fun());
    this.getProxy().getPluginManager().registerCommand(this, new b_about());
    this.getProxy().getPluginManager().registerCommand(this, new b_help());
    this.getProxy().getPluginManager().registerCommand(this, new b_pl());
    this.getProxy().getPluginManager().registerCommand(this, new b_plugins());
    this.getProxy().getPluginManager().registerCommand(this, new b_questmark());
    this.getProxy().getPluginManager().registerCommand(this, new b_ver());
    this.getProxy().getPluginManager().registerCommand(this, new b_version());
    this.getProxy().getPluginManager().registerCommand(this, new questmark());
    this.getProxy().getPluginManager().registerCommand(this, new about());
    this.getProxy().getPluginManager().registerCommand(this, new help());
    this.getProxy().getPluginManager().registerCommand(this, new pl());
    this.getProxy().getPluginManager().registerCommand(this, new plugins());
    this.getProxy().getPluginManager().registerCommand(this, new questmark());
    this.getProxy().getPluginManager().registerCommand(this, new ver());
    this.getProxy().getPluginManager().registerCommand(this, new version());
    this.getProxy().getPluginManager().registerCommand(this, new icanhasbukkit());
    this.getProxy().getPluginManager().registerCommand(this, new mc_me());
    this.getProxy().getPluginManager().registerCommand(this, new ban());
    this.getProxy().getPluginManager().registerCommand(this, new ban_ip());
    this.getProxy().getPluginManager().registerCommand(this, new eban());
    this.getProxy().getPluginManager().registerCommand(this, new e_ban_ip());

    try {
      MySQL sql = new MySQL("sql.starchaser.me", "3306", "deluxe4", "siamcraft_plugin", "5cTPz8ha0qdI1jqr");
      getBungeeConn = sql.openConnection();
      this.recheck_server();
    } catch (Exception var2) {
      var2.printStackTrace();
      ProxyServer.getInstance().stop();
    }
  }

  public void recheck_server() {
    this.getProxy().getScheduler().schedule(this, new Runnable() {
      public void run() {
        try {
          if (core.getBungeeConn == null || core.getBungeeConn.isClosed()) {
            MySQL sql = new MySQL("sql.starchaser.me", "3306", "deluxe4", "siamcraft_plugin", "5cTPz8ha0qdI1jqr");
            core.getBungeeConn = sql.openConnection();
          }
        } catch (Exception var8) {
          var8.printStackTrace();
          core.this.getProxy().getConsole().sendMessage(new TextComponent("§f[§bServerManager§f] §cError on check or open sql please check console error"));
        }

        try {
          ResultSet res = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `servers`");
          if (res.isBeforeFirst()) {
            res.last();
            int server_count = res.getRow();
            res.beforeFirst();

            int idx;
            for(int count_now = 0; count_now < server_count; ++count_now) {
              res.next();
              int id = res.getInt("id");
              idx = res.getInt("port");
              if (!ProxyServer.getInstance().getServers().containsKey("game/" + id)) {
                core.addServer("game/" + id, new InetSocketAddress("siamcraftsv2.starchaser.me", idx), "§a§lMinecraft §e§lDeluxe §b§lMIN-" + id, false);
                core.this.getProxy().getConsole().sendMessage(new TextComponent("§f[§bServerManager§f] §aNew server created! (game/" + id + ")"));
              }
            }

            res.beforeFirst();
            ArrayList arr = new ArrayList();

            while(res.next()) {
              idx = res.getInt("id");
              arr.add("game/" + idx);
            }

            Iterator var11 = ProxyServer.getInstance().getServers().entrySet().iterator();

            while(var11.hasNext()) {
              Entry sv = (Entry)var11.next();
              if (!arr.contains(sv.getKey().toString()) && sv.getKey().toString().contains("game/")) {
                core.this.getProxy().getConsole().sendMessage(new TextComponent("§f[§bServerManager§f] §cServer removed! (" + sv.getKey().toString() + ")"));
                core.removeServer(sv.getKey().toString());
              }
            }

          }
        } catch (Exception var7) {
          var7.printStackTrace();
          core.this.getProxy().getConsole().sendMessage(new TextComponent("§f[§bSQLManager§f] §cERROR! on run-servercheck SQL connection please check error!"));
        }
      }
    }, 500L, 500L, TimeUnit.MILLISECONDS);
  }

  public static void addServer(String name, InetSocketAddress address, String motd, boolean restricted) {
    ProxyServer.getInstance().getServers().put(name, ProxyServer.getInstance().constructServerInfo(name, address, motd, restricted));
  }

  public static void removeServer(String name) {
    Iterator var1 = ProxyServer.getInstance().getServerInfo(name).getPlayers().iterator();

    while(var1.hasNext()) {
      ProxiedPlayer p = (ProxiedPlayer)var1.next();
      p.disconnect(new TextComponent("§cเซิร์ฟเวอร์นี้ถูกบังคับให้ปิด.\n§eโปรดส่งข้อความนี้ให้ผู้ดูแลเพื่อทำการแก้ไข"));
    }

    ProxyServer.getInstance().getServers().remove(name);
  }

  public static void sendToServer(ProxiedPlayer p, ServerInfo sv) {
    p.connect(sv);
    p.sendMessage(ChatColor.GRAY + "Portal: " + ChatColor.YELLOW + "คุณถูกย้าย " + p.getServer().getInfo().getName() + " > " + sv.getName());
  }

  public static void teleportsv(ProxiedPlayer p, ServerInfo sv) {
    p.connect(sv);
    p.sendMessage(ChatColor.GRAY + "Portal: " + ChatColor.YELLOW + "คุณถูกย้าย " + p.getServer().getInfo().getName() + " > " + sv.getName());
  }

  public static void sendhelpinfo(ProxiedPlayer p) {
    p.sendMessage(ChatColor.GRAY + "DeluxeCore: " + ChatColor.GREEN + "This server run as §eDeluxeCore4 §fv§a" + getBungeeDeluxe.getDescription().getVersion());
    p.sendMessage(ChatColor.GRAY + "DeluxeCore: " + ChatColor.GREEN + "( " + version_string + ChatColor.GREEN + " )");
    p.sendMessage(ChatColor.GRAY + "DeluxeCore: " + ChatColor.GREEN + " by _StarChaser w/ siamcraft mode");
    p.sendMessage(ChatColor.GRAY + "DeluxeCore: " + ChatColor.RED + "Now you can't access this command, you need to login to linkage by command !deluxecore <someargs>");
  }
}
