//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.uhc;

import java.util.Random;
import me.starchaser.deluxe.games.DeluxeMap;
import me.starchaser.deluxe.games.game.MINIGAMES;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class game {
  public static int percent_world_generator = 0;
  public static boolean show_in_lobby = false;
  public static ItemStack item_icon;

  public game() {
  }

  public static void Select() {
    me.starchaser.deluxe.games.game.current_game = MINIGAMES.UHC;
    me.starchaser.deluxe.games.game.Max_Players = 50;
    me.starchaser.deluxe.games.game.Min_Players = 10;
    me.starchaser.deluxe.games.game.game_rand = (new Random()).nextInt(99999);
    int mi_x = 0;
    int mi_z = 0;
    int m_x = 0;
    int m_z = 0;
    int border = 1000;
    DeluxeMap map = new DeluxeMap("GAME_" + me.starchaser.deluxe.games.game.game_rand + me.starchaser.deluxe.games.game.current_game.toString().toUpperCase(), "The Over World", "Mojang", mi_x, m_x, mi_z, m_z, border, (ConfigurationSection)null);
    me.starchaser.deluxe.games.game.game_map = map;
    me.starchaser.deluxe.games.game.max_x = map.getMax_x();
    me.starchaser.deluxe.games.game.min_x = map.getMin_x();
    me.starchaser.deluxe.games.game.max_z = map.getMax_z();
    me.starchaser.deluxe.games.game.min_z = map.getMin_z();
    me.starchaser.deluxe.games.game.game_team = map.getTeams();
    WorldCreator worldCreator = new WorldCreator(map.getMap_id());
    World world = worldCreator.createWorld();
    Bukkit.getServer().unloadWorld(world.getName(), false);
  }

  public static void gameStart() {
  }

  public void gameEnd() {
  }

  static {
    item_icon = new ItemStack(Material.GOLDEN_APPLE);
  }
}
