package me.starchaser.deluxe.bungee.cmd.blockcmd_fun;

import me.starchaser.deluxe.bungee.core;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class b_about extends Command implements Listener
{
  public b_about()
  {
    super("bukkit:about");
  }
  
  public void execute(CommandSender sender, String[] args)
  {
    core.sendhelpinfo((ProxiedPlayer)sender);
  }
}
