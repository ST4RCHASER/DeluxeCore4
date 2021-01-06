//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.bungee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class events extends Plugin implements Listener {
  private HashMap<String, Long> cooldowns = new HashMap();
  private HashMap<String, Integer> class_cache = new HashMap();
  private HashMap<String, Long> last_cache_class = new HashMap();

  public events() {
  }

  @EventHandler
  public void onPostLogin(PostLoginEvent event) {
    ProxyServer.getInstance().getLogger().info(ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.GREEN + " has connected the network. (" + ProxyServer.getInstance().getPlayers().size() + ")");
  }

  @EventHandler
  public void onDisconnect(PlayerDisconnectEvent event) {
    ProxyServer.getInstance().getLogger().info(ChatColor.YELLOW + event.getPlayer().getName() + ChatColor.RED + " has disconnected the network. (" + (ProxyServer.getInstance().getPlayers().size() - 1) + ")");
  }

  @EventHandler
  public void onSwitch(ServerConnectEvent event) {
    Server sv = event.getPlayer().getServer();
    if (sv != null) {
      ServerInfo info = event.getPlayer().getServer().getInfo();
      if (info != null && info.equals(ProxyServer.getInstance().getServerInfo("Lobby"))) {
        try {
          ResultSet result = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + event.getPlayer().getName() + "'");
          if (result.next()) {
            int wp_count = result.getInt("wp");
            if (wp_count > 2) {
              ProxiedPlayer target = event.getPlayer();
              target.sendMessage("§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*");
              target.sendMessage(ChatColor.WHITE + "ตัวละครของคุณอยู่ในสถานะ ''ถูกระงับการใช้งาน'' นั้นอาจจะเป็นเพราะว่าคุณได้กระทำผิดเกิน 3 ครั้งจึงถูกระงับ หากพบข้อสงสัยหรือสอบถามใดๆ โปรดติตต่อที่เพจ Siamcraft https://goo.gl/nR49Zp สามารถคลิกได้ที่นี้ ขอบคุณครับ (จำนวน warnpoint ของคุณตอนนี้: " + wp_count + " )");
              target.sendMessage("§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*§f-§c*");
              event.setCancelled(true);
            }
          }
        } catch (SQLException var7) {
          var7.printStackTrace();
        }
      }

    }
  }

  @EventHandler
  public void onChat(ChatEvent e0) {
    if (!e0.getMessage().startsWith("!#")) {
      ProxiedPlayer p0 = (ProxiedPlayer)e0.getSender();
      Long time = System.currentTimeMillis();
      boolean sussces = false;

      try {
        if (this.class_cache.get(p0.getName()) == null || this.last_cache_class.get(p0.getName()) == null || (Long)this.last_cache_class.get(p0.getName()) + 300000L <= time) {
          ResultSet resultSet = core.getBungeeConn.createStatement().executeQuery("SELECT * FROM `players` WHERE `username` LIKE '" + p0.getName() + "'");
          int player_class;
          if (resultSet.isBeforeFirst()) {
            resultSet.next();
            player_class = resultSet.getInt("class");
          } else {
            player_class = 0;
          }

          this.class_cache.put(p0.getName(), player_class);
          this.last_cache_class.put(p0.getName(), time);
        }

        Long lastUse = (Long)this.cooldowns.get(p0.getName());
        if ((Integer)this.class_cache.get(p0.getName()) < 3 && lastUse + 4000L > time) {
          p0.sendMessage(ChatColor.GRAY + "Chat: §cโปรดรออย่างน้อย 3 วินาทีแล้ว จึงค่อยใช้แชทอีกครั้ง!");
          p0.sendMessage(ChatColor.GRAY + "Chat: §aหากคุณขี้เกียจทนรอดีเลย์ของแชทคุณสามารถซื้อยศเพื่อที่จะลดหย่อนดีเลย์แชทได้");
          e0.setCancelled(true);
          sussces = true;
        }

        if ((Integer)this.class_cache.get(p0.getName()) < 9 && lastUse + 1000L > time) {
          p0.sendMessage(ChatColor.GRAY + "Chat: §cโปรดรออย่างน้อย 1 วินาทีแล้ว จึงค่อยใช้แชทอีกครั้ง!");
          e0.setCancelled(true);
          sussces = true;
        }
      } catch (Exception var8) {
        ;
      }

      if (!sussces) {
        try {
          this.cooldowns.remove(p0.getName());
        } catch (Exception var7) {
          ;
        }

        this.cooldowns.put(p0.getName(), time);
      }

    }
  }
}
