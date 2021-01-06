//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.bungee.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;
import me.starchaser.deluxe.bungee.core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class punisher extends Command implements Listener {
  public punisher() {
    super("punisher");
  }

  public void execute(CommandSender sender, String[] args) {
    int targer_wp = 0;
    ProxiedPlayer player = (ProxiedPlayer)sender;
    if (player.getServer().getInfo().getName().equalsIgnoreCase("lobbymaster")) {
      player.sendMessage(ChatColor.GRAY + "CORE: " + ChatColor.RED + "/punisher now allow on this server!");
    } else if (player.getServer().getInfo().getName().equalsIgnoreCase("auth")) {
      player.sendMessage(ChatColor.GRAY + "CORE: " + ChatColor.RED + "/punisher now allow on this server!");
    } else {
      try {
        ResultSet sender_result_set = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + sender.getName() + "'");
        sender_result_set.next();
        int sender_class = sender_result_set.getInt("class");
        if (sender_class < 6) {
          sender.sendMessage("§7Punisher: §cคำสั่งนี้ใช้ได้เฉพาะทีมงานเท่านั้น");
          return;
        }

        if (args.length < 2) {
          sender.sendMessage("§7Punisher: §eสามารถลงโทษผู้เล่นได้โดยการ /punisher <ชื่อผู้เล่น> <เหตุผล>");
          return;
        }

        if (args[0].equalsIgnoreCase("kick")) {
          int i = 1;

          String aaabbbcc;
          for(aaabbbcc = ""; i < args.length; ++i) {
            aaabbbcc = aaabbbcc + args[i];
          }

          ProxiedPlayer a = ProxyServer.getInstance().getPlayer(args[1]);
          if (a == null) {
            sender.sendMessage("§7Kick: §cผู้เล่นนี้ไม่ได้ออนไลน์อยู่ในขนะนี้");
            return;
          }

          String res_str;
          for(res_str = ""; i < args.length; ++i) {
            res_str = res_str + args[i];
          }

          ProxyServer.getInstance().broadcast("§7Kick: §b" + a.getName() + " §cถูกเชิญออกจาเซิร์ฟเวอร์โดย " + a.getName() + " ในสาเหตุ: §f" + res_str);
          a.disconnect(aaabbbcc);
          return;
        }

        ResultSet target_resultset;
        if (args[0].equalsIgnoreCase("wp")) {
          target_resultset = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + args[1] + "'");
          if (!target_resultset.isBeforeFirst()) {
            sender.sendMessage("§7Punisher: §cไม่พบผู้เล่นชื่อ §f" + args[1] + " §cใน linkage");
            return;
          }

          int i = 3;

          String res_str;
          for(res_str = ""; i < args.length; ++i) {
            res_str = res_str + args[i];
          }

          this.give_wp(args[1], Integer.parseInt(args[2]), res_str);
          return;
        }

        target_resultset = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + args[0] + "'");
        if (!target_resultset.isBeforeFirst()) {
          sender.sendMessage("§7Punisher: §cไม่พบผู้เล่นชื่อ §f" + args[0] + " §cใน linkage");
          return;
        }

        target_resultset.next();
        targer_wp = target_resultset.getInt("wp");
      } catch (SQLException var14) {
        var14.printStackTrace();
      }

      int i = 1;

      String res_str;
      for(res_str = ""; i < args.length; ++i) {
        res_str = res_str + args[i];
      }

      TextComponent onewp = new TextComponent("§bให้ 1 WP");
      onewp.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bทำการเตือนผู้เล่นแบบเริ่มต้น เหมาะสำหรับผู้เล่นที่ ฟลัตแชท ใช้โปรแกรมโกงเล็กน้อย")).create()));
      onewp.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/punisher wp " + args[0] + " 1 " + res_str));
      TextComponent twowp = new TextComponent("§aให้ 2 WP");
      twowp.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bทำการเตือนผู้เล่นแบบระดับกลาง เหมาะสำหรับผู้เล่นที่ ฟลัตแชทแล้วไม่ฟัง ใช้โปรแกรมโกงที่ทำให้เสียสมุดุล")).create()));
      twowp.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/punisher wp " + args[0] + " 2 " + res_str));
      TextComponent treewp = new TextComponent("§eให้ 3 WP");
      treewp.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bผู้เล่นจะถูกระงับการเล่นทันที แต่ยังจะสามารถพิมพ์แชทใน lobby ได้")).create()));
      treewp.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/punisher wp " + args[0] + " 3 " + res_str));
      TextComponent four = new TextComponent("§cให้ 4 WP");
      four.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bผู้เล่นจะถูกระงับการเล่นทันที และจะไม่สามารถพิมพ์แชทใน lobby ได้ เหมาะสำหรับผู้เล่นที่ โปรโมทเซิร์ฟเวอร์")).create()));
      four.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/punisher wp " + args[0] + " 4 " + res_str));
      TextComponent kick = new TextComponent("§dเชิญออกจากเซิร์ฟเวอร์");
      kick.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bผู้เล่นจะติตปีกออกจากเซิร์ฟเวอร์ทันที เมื่อกด")).create()));
      kick.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/punisher kick " + args[0] + " " + res_str));
      sender.sendMessage("§7§m--------------------------------------------------");
      sender.sendMessage("§cทำโทษผู้เล่น §f" + args[0] + "§7§o(" + res_str + ")");
      sender.sendMessage("§bวิธีการใช้งาน ให้นำเมาส์ไปวาง หรือ คลิปเพื่อทำการให้ warnpoint");
      sender.sendMessage("§7ตอนนี้ผู้เล่น §f" + args[0] + "§7 มี WP อยู่แล้ว §b" + targer_wp);
      sender.sendMessage(onewp);
      sender.sendMessage(twowp);
      sender.sendMessage(treewp);
      sender.sendMessage(four);
      sender.sendMessage(kick);
      sender.sendMessage("§7§m--------------------------------------------------");
    }
  }

  public void give_wp(String name, int count, String res_str) {
    try {
      ProxiedPlayer targetplayer = ProxyServer.getInstance().getPlayer(name);
      ResultSet target_res = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + name + "'");
      target_res.next();
      int current_wp = target_res.getInt("wp");
      int id = target_res.getInt("id");
      ProxyServer.getInstance().broadcast("§7Punisher: §b" + name + " §cถูกเตือนเป็นจำนวน §f" + count + "§c wp เนื่องจาก: §f" + res_str);
      int new_wp = current_wp + count;
      core.getBungeeConn.createStatement().executeUpdate("UPDATE `deluxe4`.`players` SET `wp` = '" + new_wp + "' WHERE `players`.`id` = " + id + ";");
      if (new_wp == 3) {
        ProxyServer.getInstance().broadcast("§7Punisher: §b" + name + " §cถูกระงับการเล่น");
      }

      if (new_wp == 4) {
        ProxyServer.getInstance().broadcast("§7Punisher: §b" + name + " §cถูกระงับการเล่นและอยู่ในสถานะถูกใบ้");
      }

      if (targetplayer != null && new_wp < 3) {
        targetplayer.sendMessage("§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*");
        targetplayer.sendMessage("§fคุณถูกเตือน เนื่องจาก: " + res_str);
        targetplayer.sendMessage("§fหากคุณยังไม่หยุดการกระทำดังกล่าวอีกอาจจะทำให้คุณถูกระงับการเล่นได้ เมื่อถูกเตือนครบ 3 ครั้ง §7§o(Warnpoint ของคุณตอนนี้คือ: " + new_wp + ")");
        targetplayer.sendMessage("§fถ้าหากคุณต้องการที่จะลบ warnpoint ออกคุณจำเป็นต้องไปซื้อที่ลบ warnpoint ได้ที่ http://siamcraft.net/shop");
        targetplayer.sendMessage("§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*");
      }

      if (targetplayer != null && new_wp >= 3) {
        targetplayer.sendMessage("§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*");
        targetplayer.sendMessage("§fคุณถูกเตือน เนื่องจาก: " + res_str);
        targetplayer.sendMessage("§fคุกณถูกระงับการเล่นแล้ว ถ้าคุณต้องการที่จะเล่น คุณจำเป็นต้องซื้อที่ลบ warnpoint §7§o(Warnpoint ของคุณตอนนี้คือ: " + new_wp + ")");
        targetplayer.sendMessage("§fถ้าหากคุณต้องการที่จะลบ warnpoint ออกคุณจำเป็นต้องไปซื้อที่ลบ warnpoint ได้ที่ http://siamcraft.net/shop");
        targetplayer.sendMessage("§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*");
      }

      if (new_wp >= 3 && targetplayer != null) {
        core.sendToServer(targetplayer, ProxyServer.getInstance().getServerInfo("Lobby"));
      }

    } catch (SQLException var9) {
      var9.printStackTrace();
      ProxyServer.getInstance().broadcast("§7Punisher: §cSomething error in warnpoint system ,please contact _StarChaser §f(§e" + var9.getErrorCode() + "§f)");
    }
  }
}
