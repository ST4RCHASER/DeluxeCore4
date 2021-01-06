//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.bungee.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import me.starchaser.deluxe.bungee.core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class report extends Command implements Listener {
  public report() {
    super("report");
  }

  public void execute(CommandSender sender, String[] args) {
    int sender_class = 0;

    try {
      ResultSet sender_result_set = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + sender.getName() + "'");
      sender_result_set.next();
      sender_class = sender_result_set.getInt("class");
    } catch (SQLException var31) {
      var31.printStackTrace();
    }

    ProxiedPlayer player = (ProxiedPlayer)sender;
    if (player.getServer().getInfo().getName().equalsIgnoreCase("lobbymaster")) {
      player.sendMessage(ChatColor.GRAY + "SC_CORE: " + ChatColor.RED + "/report now allow on this server!");
    } else if (player.getServer().getInfo().getName().equalsIgnoreCase("auth")) {
      player.sendMessage(ChatColor.GRAY + "SC_CORE: " + ChatColor.RED + "/report now allow on this server!");
    } else if (args.length < 2) {
      sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "/report <ชื่อผู้เล่น> <เหตุผลและหลักฐานเช่นลิ้งรูปภาพ>");
      sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "คำเตือน! ถ้ามีการรายงามมั่วๆฟลัตรัวๆ ท่านนั้นจะได้ warnpoint ซะเอง เพราะฉนั้นอย่าทำ!");
      sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "** จำเป็นต้องมีหลักฐานให้แน่ชัด จึงจะสามารถให้ warnpoint แก่ผู้เล่นนั้นได้");
      sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.AQUA + "ติตตามผลการรายงานได้ที่ http://siamcraft.net/report/view/");
      sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.AQUA + "ข้อมูลรายชื่อผู้ที่โดน WP http://siamcraft.net/warnpoint/view/");
    } else {
      String target_v;
      String reason;
      String newtime;
      if (args[0].equalsIgnoreCase("admin")) {
        if (sender_class < 6) {
          player.sendMessage(ChatColor.GRAY + "SC_CORE: " + ChatColor.RED + "คำสั่ง /report admin ใช้ได้เฉพาะทีมงานเท่านั้น");
          return;
        }

        if (args[1].equalsIgnoreCase("teleport_server_staff_lkasdlksajkd") && sender_class >= 6) {
          core.sendToServer((ProxiedPlayer)sender, ProxyServer.getInstance().getServerInfo(args[2]));
          return;
        }

        Statement statement;
        ResultSet res;
        int id;
        String arg;
        if (args[1].equalsIgnoreCase("claim")) {
          if (args.length > 3) {
            try {
              statement = core.getBungeeConn.createStatement();
              res = statement.executeQuery("SELECT * FROM `report_log` WHERE `id` = " + args[2] + " ORDER BY `reason` ASC");
              if (!res.isBeforeFirst()) {
                sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "ค้นหา ID: " + ChatColor.WHITE + args[2] + ChatColor.RED + " ไม่เจอ!");
                return;
              }

              res.next();
              if (res.getInt("status") == 1) {
                sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "ID: " + ChatColor.WHITE + args[2] + ChatColor.RED + " รีพอร์ตนี้ได้เคยส่งผลไปแล้วไม่สามารถส่งได้อีก");
                return;
              }

              String final_res = null;
              String res_info = null;

              for(id = 3; id < args.length; ++id) {
                arg = args[id] + " ";
                res_info = res_info + arg;
                final_res = res_info.replaceAll("null", "");
              }

              statement.executeUpdate("UPDATE `deluxe4`.`report_log` SET `status` = '1', `end_by` = '" + sender.getName() + "', `result` = '" + final_res + "' WHERE `report_log`.`id` = '" + args[2] + "';");
              sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.GREEN + "ID: " + ChatColor.WHITE + args[2] + ChatColor.GREEN + " ส่งผลการรีพอร์ตเรียบร้อยแล้ว!");
              return;
            } catch (SQLException var32) {
              var32.printStackTrace();
            }
          } else {
            sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "/report admin claim <id> <ผลการรีพอร์ต>");
          }

          return;
        }

        if (args[1].equalsIgnoreCase("view")) {
          try {
            statement = core.getBungeeConn.createStatement();
            res = statement.executeQuery("SELECT * FROM `report_log` WHERE status = 0");
            res.beforeFirst();

            int rowCount;
            for(rowCount = 0; res.next(); ++rowCount) {
              ;
            }

            sender.sendMessage(ChatColor.DARK_GRAY + "====================================================");
            sender.sendMessage(ChatColor.BLUE + "รายการ รายงานที่ยังไม่ได้ทำ" + ChatColor.DARK_GRAY + "( " + ChatColor.AQUA + rowCount + ChatColor.DARK_GRAY + " )");
            if (rowCount < 1) {
              sender.sendMessage("§r");
              sender.sendMessage(ChatColor.RED + " -- No report to view, let's feel free~ --");
              sender.sendMessage("§r");
            } else {
              int thisrow = 0;
              res.beforeFirst();
              res.next();

              do {
                sender.sendMessage(ChatColor.GRAY + "§a");
                id = res.getInt("ID");
                arg = res.getString("Time");
                String sender_v = res.getString("sender");
                target_v = res.getString("target");
                reason = res.getString("reason");
                newtime = arg.replaceAll("-", "/");
                sender.sendMessage(ChatColor.AQUA + "[ID:" + id + "] " + ChatColor.WHITE + "[" + newtime + "]");
                sender.sendMessage(ChatColor.GRAY + "รายงานโดย: " + ChatColor.GREEN + sender_v);
                sender.sendMessage(ChatColor.GRAY + "ผู้ถูกรายงาน: " + ChatColor.GREEN + target_v);
                sender.sendMessage(ChatColor.GRAY + "เหตุผลที่รายงาน: " + ChatColor.GREEN + reason);
                ProxiedPlayer target_p = ProxyServer.getInstance().getPlayer(target_v);
                TextComponent wp = new TextComponent("§cคลิกที่นี้");
                wp.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อทำการให้ warnpoint ผู้เล่น §f" + target_v)).create()));
                wp.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/punisher " + target_v + " "));
                sender.sendMessage(ChatColor.GRAY + "หากต้องการให้ warnpoint " + target_v);
                sender.sendMessage(wp);
                if (target_p != null) {
                  ServerInfo target_p_sv = target_p.getServer().getInfo();
                  TextComponent click_ptel = new TextComponent("§bคลิกที่นี้");
                  click_ptel.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อย้ายไปยัง §f" + target_p_sv.getName())).create()));
                  click_ptel.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/report admin teleport_server_staff_lkasdlksajkd " + target_p_sv.getName()));
                  TextComponent click_claim = new TextComponent("§aคลิกที่นี้");
                  click_claim.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อเรียกใช้คำสั่งรายงานผลของ §f" + target_v)).create()));
                  click_claim.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/report admin claim " + id + " "));
                  sender.sendMessage(ChatColor.GRAY + "ผู้เล่นนี้กำลังออนไลน์อยู่สามารถเทเลพอร์ตไปหาเขาได้โดยการ");
                  sender.sendMessage(click_ptel);
                  sender.sendMessage("§7หรือสามารถรับรายงานผลการ โดยการ");
                  sender.sendMessage(click_claim);
                } else {
                  TextComponent click_claim = new TextComponent("§aคลิกที่นี้");
                  click_claim.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อเรียกใช้คำสั่งรายงานผล §f" + target_v)).create()));
                  click_claim.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/report admin claim " + id + " "));
                  sender.sendMessage(new TextComponent(ChatColor.GRAY + "สามารถรับรายงานผลการรายงาน โดยการ "));
                  sender.sendMessage(click_claim);
                }

                sender.sendMessage(ChatColor.GRAY + "> ดูการรายงานที่ยังคงเหลือทั้งหมดได้โดยการพิมพ์ /report admin view");
                sender.sendMessage(ChatColor.DARK_GRAY + "====================================================");
                ++thisrow;
                res.next();
              } while(rowCount > thisrow);

              if (rowCount > 4) {
                sender.sendMessage(ChatColor.GRAY + "§r");
                sender.sendMessage(ChatColor.RED + " ^ -- เลื่อนขึ้นไปข้างบนเพื่อดูเพิ่มเติม -- ^");
                sender.sendMessage(ChatColor.GRAY + "§r");
              }
            }
          } catch (SQLException var33) {
            var33.printStackTrace();
          }

          sender.sendMessage(ChatColor.DARK_GRAY + "====================================================");
          return;
        }

        sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "Usage:");
        sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "/report admin claim <id> <ผลการรีพอร์ต>");
        sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "/report admin view");
      } else {
        Boolean is_have_account = false;
        int target_class = 0;

        try {
          ResultSet resultSet = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + args[0] + "'");
          is_have_account = resultSet.isBeforeFirst();
          if (!is_have_account) {
            sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "ไม่พบชื่อผู่เล่นนี้ §f" + args[0]);
            return;
          }

          resultSet.next();
          target_class = resultSet.getInt("class");
        } catch (SQLException var30) {
          var30.printStackTrace();
        }

        if (args[0].equalsIgnoreCase(sender.getName())) {
          sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "คนบ้าอะไร รายงานตัวเอง อิอิ");
          return;
        }

        if (target_class >= 6) {
          sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "ไม่สามารถรายงานผู้เล่นนี้ได้");
          return;
        }

        try {
          Statement statement = core.getBungeeConn.createStatement();
          ResultSet res = statement.executeQuery("SELECT * FROM `report_log` WHERE status = 0 AND target = '" + args[0] + "'");
          if (res.isBeforeFirst()) {
            sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.RED + "ผู้เล่นนี้ได้ถูกรายงานอยู่แล้วและในขณะนี้กำลังดำเนินการตรวจสอบอยู่ สามารถติตตามผลได้ที่ http://siamcraft.net/report/view");
            return;
          }

          LocalDateTime now = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          target_v = now.format(formatter);
          reason = null;
          newtime = null;

          int idreport;
          for(idreport = 1; idreport < args.length; ++idreport) {
            String arg = args[idreport] + " ";
            reason = reason + arg;
            newtime = reason.replaceAll("null", "");
          }

          statement.executeUpdate("INSERT INTO `deluxe4`.`report_log` (`id`, `time`, `sender`, `target`, `reason`, `status`, `end_by`, `result`) VALUES (NULL, '" + target_v + "', '" + sender.getName() + "', '" + args[0] + "', '" + newtime + "', '0', '', '')");
          res = statement.executeQuery("SELECT * FROM report_log ORDER BY id DESC LIMIT 1");
          res.next();
          idreport = res.getInt("id");
          sender.sendMessage(ChatColor.GRAY + "Report: " + ChatColor.GREEN + "รายงานได้ถูกส่งแล้ว เลขที่รายงานคือ " + ChatColor.WHITE + idreport + ChatColor.GREEN + " สามารถติตตามผลได้ที่ http://siamcraft.net/report/view");
          int id = res.getInt("ID");
          String time_v = res.getString("Time");
          String sender_v = res.getString("sender");
          target_v = res.getString("target");
          reason = res.getString("reason");
          ResultSet res_staff = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `class` >= 6");

          while(res_staff.next()) {
            String usern = res_staff.getString("username");
            ProxiedPlayer p = ProxyServer.getInstance().getPlayer(usern);
            if (p != null) {
              ProxiedPlayer target_p = ProxyServer.getInstance().getPlayer(target_v);
              p.sendMessage(ChatColor.DARK_GRAY + "====================================================");
              p.sendMessage(ChatColor.BLUE + "มีการรายงานใหม่");
              p.sendMessage(ChatColor.GRAY + "§a");
              newtime = time_v.replaceAll("-", "/");
              p.sendMessage(ChatColor.AQUA + "[ID:" + id + "] " + ChatColor.WHITE + "[" + newtime + "]");
              p.sendMessage(ChatColor.GRAY + "รายงานโดย: " + ChatColor.GREEN + sender_v);
              p.sendMessage(ChatColor.GRAY + "ผู้ถูกรายงาน: " + ChatColor.GREEN + target_v);
              p.sendMessage(ChatColor.GRAY + "เหตุผลที่รายงาน: " + ChatColor.GREEN + reason);
              TextComponent wp = new TextComponent("§cคลิกที่นี้");
              wp.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อทำการให้ warnpoint ผู้เล่น §f" + target_v)).create()));
              wp.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/punisher " + target_v + " "));
              p.sendMessage(ChatColor.GRAY + "หากต้องการให้ warnpoint " + target_v);
              p.sendMessage(wp);
              if (target_p == null) {
                TextComponent click_claim = new TextComponent("§aคลิกที่นี้");
                click_claim.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อเรียกใช้คำสั่งรายงานผล §f" + target_v)).create()));
                click_claim.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/report admin claim " + id + " "));
                p.sendMessage(new TextComponent(ChatColor.GRAY + "สามารถรับรายงานผลการรายงาน โดยการ "));
                p.sendMessage(click_claim);
              } else {
                ServerInfo target_p_sv = target_p.getServer().getInfo();
                TextComponent click_ptel = new TextComponent("§bคลิกที่นี้");
                click_ptel.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อย้ายไปยัง §f" + target_p_sv.getName())).create()));
                click_ptel.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/report admin teleport_server_staff_lkasdlksajkd " + target_p_sv.getName()));
                TextComponent click_claim = new TextComponent("§aคลิกที่นี้");
                click_claim.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("§bคลิกเพื่อเรียกใช้คำสั่งรายงานผลของ §f" + target_v)).create()));
                click_claim.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/report admin claim " + id + " "));
                p.sendMessage(ChatColor.GRAY + "ผู้เล่นนี้กำลังออนไลน์อยู่สามารถเทเลพอร์ตไปหาเขาได้โดยการ");
                p.sendMessage(click_ptel);
                p.sendMessage("§7หรือสามารถรับรายงานผลการ โดยการ");
                p.sendMessage(click_claim);
              }

              p.sendMessage(ChatColor.GRAY + "> ดูการรายงานที่ยังคงเหลือทั้งหมดได้โดยการพิมพ์ /report admin view");
              p.sendMessage(ChatColor.DARK_GRAY + "====================================================");
            }
          }

          return;
        } catch (SQLException var34) {
          var34.printStackTrace();
        }
      }

    }
  }
}
