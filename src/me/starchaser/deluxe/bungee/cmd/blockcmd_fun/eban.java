package me.starchaser.deluxe.bungee.cmd.blockcmd_fun;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

public class eban extends net.md_5.bungee.api.plugin.Command implements net.md_5.bungee.api.plugin.Listener
{
  public eban()
  {
    super("eban");
  }
  
  public void execute(CommandSender sender, String[] args)
  {
    sender.sendMessage(ChatColor.GRAY + "DeluxeCore: " + ChatColor.RED + "Disabled");
  }
}
