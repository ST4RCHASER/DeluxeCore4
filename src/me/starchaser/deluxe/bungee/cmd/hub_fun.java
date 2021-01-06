//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.bungee.cmd;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class hub_fun extends Command implements Listener {
  public hub_fun() {
    super("hub");
  }

  public void execute(CommandSender sender, String[] args) {
    ProxiedPlayer player = (ProxiedPlayer)sender;
    if (!player.getServer().getInfo().getName().equalsIgnoreCase("lobby/01") && !player.getServer().getInfo().getName().equalsIgnoreCase("lobby/02") && !player.getServer().getInfo().getName().equalsIgnoreCase("lobby")) {
      if (player.getServer().getInfo().getName().equalsIgnoreCase("lobbymaster")) {
        player.sendMessage(new TextComponent(ChatColor.GRAY + "CORE: " + ChatColor.RED + "/hub now allow on this server!"));
      } else if (player.getServer().getInfo().getName().equalsIgnoreCase("auth")) {
        player.sendMessage(new TextComponent(ChatColor.GRAY + "CORE: " + ChatColor.RED + "/hub now allow on this server!"));
      } else {
        player.sendMessage(new TextComponent(ChatColor.GRAY + "Portal: " + ChatColor.YELLOW + "คุณถูกย้าย " + player.getServer().getInfo().getName() + " > Lobby"));
        player.sendMessage(new TextComponent(ChatColor.GRAY + "Portal: " + ChatColor.YELLOW + "คุณกำลังถูกส่งกลับไปที่ล๊อบบี้"));
        player.connect(ProxyServer.getInstance().getServerInfo("Lobby"));
      }
    } else {
      player.sendMessage(new TextComponent(ChatColor.GRAY + "Portal: " + ChatColor.YELLOW + "คุณได้อยู่ในล๊อบบี้อยู่แล้ว!"));
    }
  }
}
