//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import java.io.File;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class YamlReader {
  final String Loc;

  public YamlReader(String Location) {
    this.Loc = Location;
  }

  public ConfigurationSection getConfigSelection(String key) {
    File customYml = new File(this.Loc);
    FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
    ConfigurationSection cfg_list = customConfig.getConfigurationSection(key);
    return cfg_list;
  }

  public String getString(String key) {
    File customYml = new File(this.Loc);
    FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
    String str = customConfig.getString(key);
    return str;
  }

  public List<String> getStringList(String key) {
    File customYml = new File(this.Loc);
    FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
    List<String> str = customConfig.getStringList(key);
    return str;
  }

  public int getInt(String key) {
    File customYml = new File(this.Loc);
    FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
    int i = customConfig.getInt(key);
    return i;
  }
}
