package me.starchaser.deluxe.bungee.cmd.blockcmd_fun;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

public class mc_me extends net.md_5.bungee.api.plugin.Command implements net.md_5.bungee.api.plugin.Listener
{
  public mc_me()
  {
    super("minecraft:me");
  }
  
  public void execute(CommandSender sender, String[] args)
  {
    sender.sendMessage(ChatColor.GRAY + "Siamcraft: " + ChatColor.GREEN + "ไม่ให้ใช้แล้วนาจา จุฟ :)");
  }
}
