//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import me.starchaser.deluxe.starchaser;
import org.bukkit.Bukkit;

public class ui {
  private JLabel var_current_game;
  private JLabel var_game_state;
  private JLabel var_players;
  private JLabel var_port;
  private JLabel var_id;
  public JPanel Parnel;
  private JLabel var_host;
  private JButton button1;
  private JLabel var_deubgs;
  private JLabel var_servers;
  private JLabel var_chat;
  private JLabel var_timeout;

  public ui() {
    this.button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Bukkit.getServer().shutdown();
      }
    });
  }

  public void createUIComponents() {
    this.var_port.setText(String.valueOf(Bukkit.getPort()));
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        starchaser.Last_Player_Chat = starchaser.Last_Player_Chat.replaceAll("§7", "").replaceAll("§a", "").replaceAll("§b", "").replaceAll("§c", "").replaceAll("§d", "").replaceAll("§e", "").replaceAll("§f", "");
        starchaser.Last_Server_Chat = starchaser.Last_Server_Chat.replaceAll("§7", "").replaceAll("§a", "").replaceAll("§b", "").replaceAll("§c", "").replaceAll("§d", "").replaceAll("§e", "").replaceAll("§f", "");
        starchaser.Last_Debug_Chat = starchaser.Last_Debug_Chat.replaceAll("§7", "").replaceAll("§a", "").replaceAll("§b", "").replaceAll("§c", "").replaceAll("§d", "").replaceAll("§e", "").replaceAll("§f", "");
        ui.this.var_current_game.setText(game.current_game.toString());
        ui.this.var_game_state.setText(game.gameState.toString());
        ui.this.var_id.setText(game.Server_ID);
        ui.this.var_players.setText(Bukkit.getOnlinePlayers().size() + "/" + game.Max_Players);
        ui.this.var_chat.setText(starchaser.Last_Player_Chat);
        ui.this.var_deubgs.setText(starchaser.Last_Debug_Chat);
        ui.this.var_servers.setText(starchaser.Last_Server_Chat);
        ui.this.var_timeout.setText(String.valueOf(starchaser.Server_Timeout));
        if (game.host != null) {
          ui.this.var_host.setText(game.host.getName());
        } else {
          ui.this.var_host.setText("NONE");
        }

      }
    }, 1L, 1L);
  }
}
