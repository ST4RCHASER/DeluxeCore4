package me.starchaser.deluxe.bungee.cmd.blockcmd_fun;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

public class e_ban_ip extends net.md_5.bungee.api.plugin.Command implements net.md_5.bungee.api.plugin.Listener
{
  public e_ban_ip()
  {
    super("eban-ip");
  }
  
  public void execute(CommandSender sender, String[] args)
  {
    sender.sendMessage(ChatColor.GRAY + "SC_API: " + ChatColor.RED + "Disabled");
  }
}
