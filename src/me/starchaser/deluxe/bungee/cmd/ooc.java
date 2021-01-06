//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.bungee.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import me.starchaser.deluxe.bungee.core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class ooc extends Command implements Listener {
  public static HashMap<String, Long> cooldown_player = new HashMap();

  public ooc() {
    super("ooc");
  }

  public void execute(CommandSender commandSender, String[] strings) {
    String listString = "";
    ProxiedPlayer player = (ProxiedPlayer) commandSender;
    if (player.getServer().getInfo().getName().equalsIgnoreCase("lobbymaster")) {
      player.sendMessage(ChatColor.GRAY + "Nginx: " + ChatColor.RED + "/ooc now allow on this server!");
    } else if (player.getServer().getInfo().getName().equalsIgnoreCase("auth")) {
      player.sendMessage(ChatColor.GRAY + "Nginx: " + ChatColor.RED + "/ooc now allow on this server!");
    } else if (strings.length < 1) {
      commandSender.sendMessage("§7OOC: §cวิธีการใช้ /ooc <ข้อความ>");
    } else {
      if (cooldown_player.get(commandSender.getName()) != null) {
        Long p_cooldown = (Long) cooldown_player.get(commandSender.getName());
        if (p_cooldown + 10000L > System.currentTimeMillis()) {
          commandSender.sendMessage("§7OOC: §cโปรดรออีกสัก 10 วินาทีแล้วจึงจะสามารถใช้ใหม่อีกครั้ง!");
          return;
        }

        String[] var11 = strings;
        int id = strings.length;

        for (int var7 = 0; var7 < id; ++var7) {
          String s = var11[var7];
          listString = listString + s + " ";
        }

        cooldown_player.put(commandSender.getName(), System.currentTimeMillis());

        int sender_ooc_count;
        try {
          ResultSet sender_result_set = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + commandSender.getName() + "'");
          sender_result_set.next();
          sender_ooc_count = sender_result_set.getInt("ooc");
          id = sender_result_set.getInt("id");
        } catch (SQLException var10) {
          var10.printStackTrace();
          return;
        }

        if (sender_ooc_count < 1) {
          commandSender.sendMessage("§7OOC: §cตั๋ว OOC ของคุณไม่เพียงพอ คุณสามารถเพิ่ม OOC ได้โดยการซื้อที่ siamcraft.net/shop");
        } else {
          try {
            --sender_ooc_count;
            commandSender.sendMessage("§7OOC: §aใช้ตั๋ว OOC ไปแล้ว 1 ชิ้น ตอนนี้คงเหลือ " + sender_ooc_count);
            ProxyServer.getInstance().broadcast("§7OOC: §a" + commandSender.getName() + " §b" + listString);
            core.getBungeeConn.createStatement().executeUpdate("UPDATE `deluxe4`.`players` SET `ooc` = '" + sender_ooc_count + "' WHERE `players`.`id` = " + id + ";");
          } catch (SQLException var9) {
            var9.printStackTrace();
          }

        }
      }
    }
  }
}
