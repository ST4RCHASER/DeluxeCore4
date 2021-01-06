//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.bedwars;

import java.util.ArrayList;
import java.util.Arrays;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class shop {
  public shop() {
  }

  public static ArrayList<ItemStack> getMenuItems() {
    ArrayList<ItemStack> arr = new ArrayList();
    ItemStack armor_icon = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta armor_icon_itemMeta = armor_icon.getItemMeta();
    armor_icon_itemMeta.setDisplayName("§bชุดเกราะ");
    armor_icon.setItemMeta(armor_icon_itemMeta);
    arr.add(armor_icon);
    ItemStack melee_icon = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta melee_icon_itemMeta = melee_icon.getItemMeta();
    melee_icon_itemMeta.setDisplayName("§bอาวุธระยะประชิด");
    melee_icon.setItemMeta(melee_icon_itemMeta);
    arr.add(melee_icon);
    ItemStack range_icon = new ItemStack(Material.BOW);
    ItemMeta range_icon_itemMeta = range_icon.getItemMeta();
    range_icon_itemMeta.setDisplayName("§bอาวุธระยะไกล");
    range_icon.setItemMeta(range_icon_itemMeta);
    arr.add(range_icon);
    ItemStack food_icon = new ItemStack(Material.COOKIE);
    ItemMeta food_icon_itemMeta = food_icon.getItemMeta();
    food_icon_itemMeta.setDisplayName("§bอาหาร");
    food_icon.setItemMeta(food_icon_itemMeta);
    arr.add(food_icon);
    ItemStack pickaxe_icon = new ItemStack(Material.DIAMOND_PICKAXE);
    ItemMeta pickaxe_icon_itemMeta = pickaxe_icon.getItemMeta();
    pickaxe_icon_itemMeta.setDisplayName("§bที่ขุด");
    pickaxe_icon.setItemMeta(pickaxe_icon_itemMeta);
    arr.add(pickaxe_icon);
    ItemStack blocks_icon = new ItemStack(Material.SANDSTONE);
    ItemMeta blocks_icon_itemMeta = blocks_icon.getItemMeta();
    blocks_icon_itemMeta.setDisplayName("§bบล๊อค");
    blocks_icon.setItemMeta(blocks_icon_itemMeta);
    arr.add(blocks_icon);
    ItemStack items_icon = new ItemStack(Material.ENDER_CHEST);
    ItemMeta items_icon_itemMeta = items_icon.getItemMeta();
    items_icon_itemMeta.setDisplayName("§bไอเทม");
    items_icon.setItemMeta(items_icon_itemMeta);
    arr.add(items_icon);
    ItemStack ex_icon = new ItemStack(Material.GOLD_INGOT);
    ItemMeta ex_icon_itemMeta = ex_icon.getItemMeta();
    ex_icon_itemMeta.setDisplayName("§bแลกเปลี่ยนทรัพยากร");
    ex_icon.setItemMeta(ex_icon_itemMeta);
    arr.add(ex_icon);
    ItemStack potion_icon = new ItemStack(Material.POTION);
    ItemMeta potion_icon_itemMeta = potion_icon.getItemMeta();
    potion_icon_itemMeta.setDisplayName("§bยา");
    potion_icon.setItemMeta(potion_icon_itemMeta);
    arr.add(potion_icon);
    return arr;
  }

  public static ArrayList<ItemStack> getArmorItems(DeluxePlayer deluxePlayer) {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack Leather_Cap = new ItemStack(Material.LEATHER_HELMET);
    LeatherArmorMeta Leader_Cap_Meta = (LeatherArmorMeta)Leather_Cap.getItemMeta();
    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.RED)) {
      Leader_Cap_Meta.setColor(Color.RED);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GREEN)) {
      Leader_Cap_Meta.setColor(Color.LIME);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLUE)) {
      Leader_Cap_Meta.setColor(Color.AQUA);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.YELLOW)) {
      Leader_Cap_Meta.setColor(Color.YELLOW);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLACK)) {
      Leader_Cap_Meta.setColor(Color.BLACK);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.WHITE)) {
      Leader_Cap_Meta.setColor(Color.WHITE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.LIGHT_PURPLE)) {
      Leader_Cap_Meta.setColor(Color.PURPLE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GOLD)) {
      Leader_Cap_Meta.setColor(Color.ORANGE);
    }

    Leader_Cap_Meta.setLore(Arrays.asList("§f1 §4Bronze"));
    Leather_Cap.setItemMeta(Leader_Cap_Meta);
    arrayList.add(Leather_Cap);
    ItemStack Leather_Tunic = new ItemStack(Material.LEATHER_CHESTPLATE);
    LeatherArmorMeta Leather_Tunic_Meta = (LeatherArmorMeta)Leather_Tunic.getItemMeta();
    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.RED)) {
      Leather_Tunic_Meta.setColor(Color.RED);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GREEN)) {
      Leather_Tunic_Meta.setColor(Color.GREEN);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLUE)) {
      Leather_Tunic_Meta.setColor(Color.AQUA);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.YELLOW)) {
      Leather_Tunic_Meta.setColor(Color.YELLOW);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.WHITE)) {
      Leather_Tunic_Meta.setColor(Color.WHITE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLACK)) {
      Leather_Tunic_Meta.setColor(Color.BLACK);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.LIGHT_PURPLE)) {
      Leather_Tunic_Meta.setColor(Color.PURPLE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GOLD)) {
      Leather_Tunic_Meta.setColor(Color.ORANGE);
    }

    Leather_Tunic_Meta.setLore(Arrays.asList("§f1 §4Bronze"));
    Leather_Tunic.setItemMeta(Leather_Tunic_Meta);
    arrayList.add(Leather_Tunic);
    ItemStack Leather_Pants = new ItemStack(Material.LEATHER_LEGGINGS);
    LeatherArmorMeta Leather_Pante_Meta = (LeatherArmorMeta)Leather_Pants.getItemMeta();
    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.RED)) {
      Leather_Pante_Meta.setColor(Color.RED);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GREEN)) {
      Leather_Pante_Meta.setColor(Color.GREEN);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLUE)) {
      Leather_Pante_Meta.setColor(Color.AQUA);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.YELLOW)) {
      Leather_Pante_Meta.setColor(Color.YELLOW);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.WHITE)) {
      Leather_Pante_Meta.setColor(Color.WHITE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLACK)) {
      Leather_Pante_Meta.setColor(Color.BLACK);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.LIGHT_PURPLE)) {
      Leather_Pante_Meta.setColor(Color.PURPLE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GOLD)) {
      Leather_Pante_Meta.setColor(Color.ORANGE);
    }

    Leather_Pante_Meta.setLore(Arrays.asList("§f1 §4Bronze"));
    Leather_Pants.setItemMeta(Leather_Pante_Meta);
    arrayList.add(Leather_Pants);
    ItemStack Leather_Boots = new ItemStack(Material.LEATHER_BOOTS);
    LeatherArmorMeta Leather_Boots_Meta = (LeatherArmorMeta)Leather_Boots.getItemMeta();
    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.RED)) {
      Leather_Boots_Meta.setColor(Color.RED);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GREEN)) {
      Leather_Boots_Meta.setColor(Color.GREEN);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLUE)) {
      Leather_Boots_Meta.setColor(Color.AQUA);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.YELLOW)) {
      Leather_Boots_Meta.setColor(Color.YELLOW);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.WHITE)) {
      Leather_Boots_Meta.setColor(Color.WHITE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLACK)) {
      Leather_Boots_Meta.setColor(Color.BLACK);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.LIGHT_PURPLE)) {
      Leather_Boots_Meta.setColor(Color.PURPLE);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GOLD)) {
      Leather_Boots_Meta.setColor(Color.ORANGE);
    }

    Leather_Boots_Meta.setLore(Arrays.asList("§f1 §4Bronze"));
    Leather_Boots.setItemMeta(Leather_Boots_Meta);
    arrayList.add(Leather_Boots);
    ItemStack Golden_Helmet = new ItemStack(Material.GOLD_HELMET);
    ItemMeta Golden_Helmet_Meta = Golden_Helmet.getItemMeta();
    Golden_Helmet_Meta.setLore(Arrays.asList("§f1 §7Iron"));
    Golden_Helmet.setItemMeta(Golden_Helmet_Meta);
    Golden_Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    Golden_Helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
    arrayList.add(Golden_Helmet);
    ItemStack Golden_CHESTPLATE = new ItemStack(Material.GOLD_CHESTPLATE);
    ItemMeta Golden_CHESTPLATE_Meta = Golden_CHESTPLATE.getItemMeta();
    Golden_CHESTPLATE_Meta.setLore(Arrays.asList("§f3 §7Iron"));
    Golden_CHESTPLATE.setItemMeta(Golden_CHESTPLATE_Meta);
    Golden_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    Golden_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
    arrayList.add(Golden_CHESTPLATE);
    ItemStack GOLD_LEGGINGS = new ItemStack(Material.GOLD_LEGGINGS);
    ItemMeta GOLD_LEGGINGS_META = GOLD_LEGGINGS.getItemMeta();
    GOLD_LEGGINGS_META.setLore(Arrays.asList("§f3 §7Iron"));
    GOLD_LEGGINGS.setItemMeta(Golden_CHESTPLATE_Meta);
    GOLD_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    GOLD_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
    arrayList.add(GOLD_LEGGINGS);
    ItemStack GOLD_BOOTS = new ItemStack(Material.GOLD_BOOTS);
    ItemMeta GOLD_BOOTS_META = GOLD_BOOTS.getItemMeta();
    GOLD_BOOTS_META.setLore(Arrays.asList("§f1 §7Iron"));
    GOLD_BOOTS.setItemMeta(GOLD_BOOTS_META);
    GOLD_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    GOLD_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
    arrayList.add(GOLD_BOOTS);
    ItemStack CHAINMAIL_HELMET = new ItemStack(Material.CHAINMAIL_HELMET);
    ItemMeta CHAINMAIL_HELMET_META = CHAINMAIL_HELMET.getItemMeta();
    CHAINMAIL_HELMET_META.setLore(Arrays.asList("§f1 §7Iron", "§f5 §4Bronze"));
    CHAINMAIL_HELMET.setItemMeta(CHAINMAIL_HELMET_META);
    CHAINMAIL_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    CHAINMAIL_HELMET.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_HELMET);
    ItemStack CHAINMAIL_CHESTPLATE = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    ItemMeta CHAINMAIL_CHESTPLATE_META = CHAINMAIL_CHESTPLATE.getItemMeta();
    CHAINMAIL_CHESTPLATE_META.setLore(Arrays.asList("§f6 §7Iron", "§f10 §4Bronze"));
    CHAINMAIL_CHESTPLATE.setItemMeta(CHAINMAIL_CHESTPLATE_META);
    CHAINMAIL_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    CHAINMAIL_CHESTPLATE.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_CHESTPLATE);
    ItemStack CHAINMAIL_LEGGINGS = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    ItemMeta CHAINMAIL_LEGGINGS_META = CHAINMAIL_LEGGINGS.getItemMeta();
    CHAINMAIL_LEGGINGS_META.setLore(Arrays.asList("§f6 §7Iron", "§f10 §4Bronze"));
    CHAINMAIL_LEGGINGS.setItemMeta(CHAINMAIL_LEGGINGS_META);
    CHAINMAIL_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    CHAINMAIL_LEGGINGS.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_LEGGINGS);
    ItemStack CHAINMAIL_BOOTS = new ItemStack(Material.CHAINMAIL_BOOTS);
    ItemMeta CHAINMAIL_BOOTS_META = CHAINMAIL_BOOTS.getItemMeta();
    CHAINMAIL_BOOTS_META.setLore(Arrays.asList("§f6 §7Iron", "§f10 §4Bronze"));
    CHAINMAIL_BOOTS.setItemMeta(CHAINMAIL_BOOTS_META);
    CHAINMAIL_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    CHAINMAIL_BOOTS.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_BOOTS);
    ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET);
    ItemMeta IRON_HELMET_META = IRON_HELMET.getItemMeta();
    IRON_HELMET_META.setLore(Arrays.asList("§f4 §7Iron", "§f3 §6Gold"));
    IRON_HELMET.setItemMeta(IRON_HELMET_META);
    IRON_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    IRON_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
    IRON_HELMET.addUnsafeEnchantment(Enchantment.THORNS, 2);
    arrayList.add(IRON_HELMET);
    ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE);
    ItemMeta IRON_CHESTPLATE_META = IRON_CHESTPLATE.getItemMeta();
    IRON_CHESTPLATE_META.setLore(Arrays.asList("§f8 §7Iron", "§f6 §6Gold"));
    IRON_CHESTPLATE.setItemMeta(IRON_CHESTPLATE_META);
    IRON_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    IRON_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
    IRON_CHESTPLATE.addUnsafeEnchantment(Enchantment.THORNS, 2);
    arrayList.add(IRON_CHESTPLATE);
    ItemStack IRON_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS);
    ItemMeta IRON_LEGGINGS_META = IRON_CHESTPLATE.getItemMeta();
    IRON_LEGGINGS_META.setLore(Arrays.asList("§f8 §7Iron", "§f6 §6Gold"));
    IRON_LEGGINGS.setItemMeta(IRON_LEGGINGS_META);
    IRON_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    IRON_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
    IRON_LEGGINGS.addUnsafeEnchantment(Enchantment.THORNS, 2);
    arrayList.add(IRON_LEGGINGS);
    ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS);
    ItemMeta IRON_BOOTS_META = IRON_BOOTS.getItemMeta();
    IRON_BOOTS_META.setLore(Arrays.asList("§f4 §7Iron", "§f3 §6Gold"));
    IRON_BOOTS.setItemMeta(IRON_LEGGINGS_META);
    IRON_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    IRON_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
    IRON_BOOTS.addUnsafeEnchantment(Enchantment.THORNS, 2);
    arrayList.add(IRON_BOOTS);
    ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET);
    ItemMeta DIAMOND_HELMET_META = DIAMOND_HELMET.getItemMeta();
    DIAMOND_HELMET_META.setLore(Arrays.asList("§f32 §7Iron", "§f22 §6Gold"));
    DIAMOND_HELMET.setItemMeta(DIAMOND_HELMET_META);
    DIAMOND_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    DIAMOND_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
    DIAMOND_HELMET.addUnsafeEnchantment(Enchantment.THORNS, 3);
    arrayList.add(DIAMOND_HELMET);
    ItemStack DIAMOND_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta DIAMOND_CHESTPLATE_META = DIAMOND_CHESTPLATE.getItemMeta();
    DIAMOND_CHESTPLATE_META.setLore(Arrays.asList("§f32 §7Iron", "§f25 §6Gold"));
    DIAMOND_CHESTPLATE.setItemMeta(DIAMOND_HELMET_META);
    DIAMOND_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    DIAMOND_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
    DIAMOND_CHESTPLATE.addUnsafeEnchantment(Enchantment.THORNS, 3);
    arrayList.add(DIAMOND_CHESTPLATE);
    ItemStack DIAMOND_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS);
    ItemMeta DIAMOND_LEGGINGS_META = DIAMOND_LEGGINGS.getItemMeta();
    DIAMOND_LEGGINGS_META.setLore(Arrays.asList("§f32 §7Iron", "§f25 §6Gold"));
    DIAMOND_LEGGINGS.setItemMeta(DIAMOND_LEGGINGS_META);
    DIAMOND_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    DIAMOND_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
    DIAMOND_LEGGINGS.addUnsafeEnchantment(Enchantment.THORNS, 3);
    arrayList.add(DIAMOND_LEGGINGS);
    ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS);
    ItemMeta DIAMOND_BOOTS_META = DIAMOND_BOOTS.getItemMeta();
    DIAMOND_BOOTS_META.setLore(Arrays.asList("§f32 §7Iron", "§f22 §6Gold"));
    DIAMOND_BOOTS.setItemMeta(DIAMOND_BOOTS_META);
    DIAMOND_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
    DIAMOND_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
    DIAMOND_BOOTS.addUnsafeEnchantment(Enchantment.THORNS, 3);
    arrayList.add(DIAMOND_BOOTS);
    ItemStack DIAMOND_HELMET2 = new ItemStack(Material.CHAINMAIL_HELMET);
    ItemMeta DIAMOND_HELMET2_META = DIAMOND_HELMET2.getItemMeta();
    DIAMOND_HELMET2_META.setLore(Arrays.asList("§f64 §7Iron", "§f38 §6Gold"));
    DIAMOND_HELMET2.setItemMeta(DIAMOND_HELMET2_META);
    DIAMOND_HELMET2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
    DIAMOND_HELMET2.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
    DIAMOND_HELMET2.addUnsafeEnchantment(Enchantment.THORNS, 5);
    arrayList.add(DIAMOND_HELMET2);
    ItemStack DIAMOND_CHESTPLATE2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    ItemMeta DIAMOND_CHESTPLATE2_META = DIAMOND_CHESTPLATE2.getItemMeta();
    DIAMOND_CHESTPLATE2_META.setLore(Arrays.asList("§f64 §7Iron", "§f48 §6Gold"));
    DIAMOND_CHESTPLATE2.setItemMeta(DIAMOND_CHESTPLATE2_META);
    DIAMOND_CHESTPLATE2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
    DIAMOND_CHESTPLATE2.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
    DIAMOND_CHESTPLATE2.addUnsafeEnchantment(Enchantment.THORNS, 5);
    arrayList.add(DIAMOND_CHESTPLATE2);
    ItemStack DIAMOND_LEGGINGS3 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    ItemMeta DIAMOND_LEGGINGS3_META = DIAMOND_LEGGINGS3.getItemMeta();
    DIAMOND_LEGGINGS3_META.setLore(Arrays.asList("§f64 §7Iron", "§f48 §6Gold"));
    DIAMOND_LEGGINGS3.setItemMeta(DIAMOND_LEGGINGS3_META);
    DIAMOND_LEGGINGS3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
    DIAMOND_LEGGINGS3.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
    DIAMOND_LEGGINGS3.addUnsafeEnchantment(Enchantment.THORNS, 5);
    arrayList.add(DIAMOND_LEGGINGS3);
    ItemStack DIAMOND_BOOTS4 = new ItemStack(Material.CHAINMAIL_BOOTS);
    ItemMeta DIAMOND_BOOTS4_META = DIAMOND_BOOTS4.getItemMeta();
    DIAMOND_BOOTS4_META.setLore(Arrays.asList("§f64 §7Iron", "§f38 §6Gold"));
    DIAMOND_BOOTS4.setItemMeta(DIAMOND_BOOTS4_META);
    DIAMOND_BOOTS4.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
    DIAMOND_BOOTS4.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
    DIAMOND_BOOTS4.addUnsafeEnchantment(Enchantment.THORNS, 5);
    arrayList.add(DIAMOND_BOOTS4);
    ItemStack DIAMOND_HELMET3 = new ItemStack(Material.DIAMOND_HELMET);
    ItemMeta DIAMOND_HELMET3_META = DIAMOND_HELMET3.getItemMeta();
    DIAMOND_HELMET3_META.setLore(Arrays.asList("§f76 §7Iron", "§f42 §6Gold"));
    DIAMOND_HELMET3.setItemMeta(DIAMOND_HELMET3_META);
    DIAMOND_HELMET3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
    DIAMOND_HELMET3.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
    DIAMOND_HELMET3.addUnsafeEnchantment(Enchantment.THORNS, 5);
    DIAMOND_HELMET3.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
    arrayList.add(DIAMOND_HELMET3);
    ItemStack DIAMOND_CHESTPLATE3 = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta DIAMOND_CHESTPLATE3_META = DIAMOND_CHESTPLATE3.getItemMeta();
    DIAMOND_CHESTPLATE3_META.setLore(Arrays.asList("§f82 §7Iron", "§f48 §6Gold"));
    DIAMOND_CHESTPLATE3.setItemMeta(DIAMOND_CHESTPLATE3_META);
    DIAMOND_CHESTPLATE3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
    DIAMOND_CHESTPLATE3.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
    DIAMOND_CHESTPLATE3.addUnsafeEnchantment(Enchantment.THORNS, 5);
    DIAMOND_CHESTPLATE3.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    arrayList.add(DIAMOND_CHESTPLATE3);
    ItemStack DIAMOND_LEGGINGS4 = new ItemStack(Material.DIAMOND_LEGGINGS);
    ItemMeta DIAMOND_LEGGINGS4_META = DIAMOND_LEGGINGS4.getItemMeta();
    DIAMOND_LEGGINGS4_META.setLore(Arrays.asList("§f82 §7Iron", "§f48 §6Gold"));
    DIAMOND_LEGGINGS4.setItemMeta(DIAMOND_LEGGINGS4_META);
    DIAMOND_LEGGINGS4.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
    DIAMOND_LEGGINGS4.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
    DIAMOND_LEGGINGS4.addUnsafeEnchantment(Enchantment.THORNS, 5);
    DIAMOND_LEGGINGS4.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    arrayList.add(DIAMOND_LEGGINGS4);
    ItemStack DIAMOND_BOOTS5 = new ItemStack(Material.DIAMOND_BOOTS);
    ItemMeta DIAMOND_BOOTS5_META = DIAMOND_BOOTS5.getItemMeta();
    DIAMOND_BOOTS5_META.setLore(Arrays.asList("§f76 §7Iron", "§f42 §6Gold"));
    DIAMOND_BOOTS5.setItemMeta(DIAMOND_BOOTS5_META);
    DIAMOND_BOOTS5.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
    DIAMOND_BOOTS5.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
    DIAMOND_BOOTS5.addUnsafeEnchantment(Enchantment.THORNS, 5);
    DIAMOND_BOOTS5.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    arrayList.add(DIAMOND_BOOTS5);
    ItemStack DIAMOND_CHESTPLATEG = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    ItemMeta DIAMOND_CHESTPLATEG_META = DIAMOND_CHESTPLATEG.getItemMeta();
    DIAMOND_CHESTPLATEG_META.setDisplayName("§fเกราะเเห่งสัจธรรม §6§lEV§e§lOL§f§lU§e§lTI§6§lON");
    DIAMOND_CHESTPLATEG_META.setLore(Arrays.asList("§f58 §7Iron", "§f56 §6Gold", "§r", "§8[§c✘§8] §7เพิ่มพลังชีวิต §a10 §7หน่วย"));
    DIAMOND_CHESTPLATEG.setItemMeta(DIAMOND_CHESTPLATEG_META);
    DIAMOND_CHESTPLATEG.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
    DIAMOND_CHESTPLATEG.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
    DIAMOND_CHESTPLATEG.addUnsafeEnchantment(Enchantment.THORNS, 10);
    arrayList.add(DIAMOND_CHESTPLATEG);
    return arrayList;
  }

  public static ArrayList<ItemStack> getMeleeItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack STICK = new ItemStack(Material.STICK);
    ItemMeta STICK_META = STICK.getItemMeta();
    STICK_META.setDisplayName("§fคฆาเเห่งสัทจธรรม §6§lL§e§lv§f§l.§e§l1");
    STICK_META.setLore(Arrays.asList("§f12 §4Bronze"));
    STICK.setItemMeta(STICK_META);
    STICK.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
    arrayList.add(STICK);
    ItemStack STICK2 = new ItemStack(Material.STICK);
    ItemMeta STICK2_META = STICK2.getItemMeta();
    STICK2_META.setDisplayName("§fคฆาเเห่งสัทจธรรม §6§lL§e§lv§f§l.§e§l2");
    STICK2_META.setLore(Arrays.asList("§f32 §4Bronze"));
    STICK2.setItemMeta(STICK2_META);
    STICK2.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    arrayList.add(STICK2);
    ItemStack STICK3 = new ItemStack(Material.STICK);
    ItemMeta STICK3_META = STICK3.getItemMeta();
    STICK3_META.setDisplayName("§fคฆาเเห่งสัทจธรรม §6§lL§e§lv§f§l.§e§l3");
    STICK3_META.setLore(Arrays.asList("§f64 §4Bronze", "§f8 §7Iron"));
    STICK3.setItemMeta(STICK3_META);
    STICK3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
    arrayList.add(STICK3);
    ItemStack STICK4 = new ItemStack(Material.STICK);
    ItemMeta STICK4_META = STICK4.getItemMeta();
    STICK4_META.setDisplayName("§fคฆาเเห่งสัทจธรรม §6§lL§e§lv§f§l.§e§l4");
    STICK4_META.setLore(Arrays.asList("§f128 §4Bronze", "§f16 §7Iron", "§f8 §6Gold"));
    STICK4.setItemMeta(STICK4_META);
    STICK4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
    arrayList.add(STICK4);
    ItemStack STICK5 = new ItemStack(Material.STICK);
    ItemMeta STICK5_META = STICK5.getItemMeta();
    STICK5_META.setDisplayName("§fคฆาเเห่งสัทจธรรม §6§lL§e§lv§f§l.§e§l5");
    STICK5_META.setLore(Arrays.asList("§f256 §4Bronze", "§f32 §7Iron", "§f12 §6Gold"));
    STICK5.setItemMeta(STICK5_META);
    STICK5.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
    STICK5.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    arrayList.add(STICK5);
    ItemStack GOLD_SWORD = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD_META = GOLD_SWORD.getItemMeta();
    GOLD_SWORD_META.setLore(Arrays.asList("§f12 §4Bronze"));
    GOLD_SWORD.setItemMeta(GOLD_SWORD_META);
    arrayList.add(GOLD_SWORD);
    ItemStack GOLD_SWORD2 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD2_META = GOLD_SWORD2.getItemMeta();
    GOLD_SWORD2_META.setLore(Arrays.asList("§f24 §4Bronze"));
    GOLD_SWORD2.setItemMeta(GOLD_SWORD2_META);
    GOLD_SWORD2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    arrayList.add(GOLD_SWORD2);
    ItemStack GOLD_SWORD3 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD3_META = GOLD_SWORD3.getItemMeta();
    GOLD_SWORD3_META.setLore(Arrays.asList("§f48 §4Bronze", "§f6 §7Iron"));
    GOLD_SWORD3.setItemMeta(GOLD_SWORD3_META);
    GOLD_SWORD3.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    GOLD_SWORD3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    arrayList.add(GOLD_SWORD3);
    ItemStack GOLD_SWORD4 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD4_META = GOLD_SWORD4.getItemMeta();
    GOLD_SWORD4_META.setLore(Arrays.asList("§f64 §4Bronze", "§f16 §7Iron"));
    GOLD_SWORD4.setItemMeta(GOLD_SWORD4_META);
    GOLD_SWORD4.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    GOLD_SWORD4.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    arrayList.add(GOLD_SWORD4);
    ItemStack GOLD_SWORD5 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD5_META = GOLD_SWORD5.getItemMeta();
    GOLD_SWORD5_META.setLore(Arrays.asList("§f128 §4Bronze", "§f32 §7Iron", "§f8 §6Gold"));
    GOLD_SWORD5.setItemMeta(GOLD_SWORD5_META);
    GOLD_SWORD5.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    GOLD_SWORD5.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
    GOLD_SWORD5.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
    arrayList.add(GOLD_SWORD5);
    ItemStack ROD = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD_META = ROD.getItemMeta();
    ROD_META.setDisplayName("§fเปลวเทียนเเห่งความเร่าร้อน §6§lL§e§lv§f§l. §e§l1");
    ROD_META.setLore(Arrays.asList("§f12 §4Bronze"));
    ROD.setItemMeta(ROD_META);
    ROD.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    arrayList.add(ROD);
    ItemStack ROD2 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD2_META = ROD2.getItemMeta();
    ROD2_META.setDisplayName("§fเปลวเทียนเเห่งความเร่าร้อน §6§lL§e§lv§f§l. §e§l2");
    ROD2_META.setLore(Arrays.asList("§f18 §4Bronze"));
    ROD2.setItemMeta(ROD2_META);
    ROD2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
    arrayList.add(ROD2);
    ItemStack ROD3 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD3_META = ROD3.getItemMeta();
    ROD3_META.setDisplayName("§fเปลวเทียนเเห่งความเร่าร้อน §6§lL§e§lv§f§l. §e§l3");
    ROD3_META.setLore(Arrays.asList("§f64 §4Bronze", "§f8 §7Iron"));
    ROD3.setItemMeta(ROD3_META);
    ROD3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    ROD3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
    arrayList.add(ROD3);
    ItemStack ROD4 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD4_META = ROD4.getItemMeta();
    ROD4_META.setDisplayName("§fเปลวเทียนเเห่งความเร่าร้อน §6§lL§e§lv§f§l. §e§l4");
    ROD4_META.setLore(Arrays.asList("§f128 §4Bronze", "§f16 §7Iron", "§f8 §6Gold"));
    ROD4.setItemMeta(ROD4_META);
    ROD4.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
    ROD4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    arrayList.add(ROD4);
    ItemStack ROD5 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD5_META = ROD5.getItemMeta();
    ROD5_META.setDisplayName("§fเปลวเทียนเเห่งความเร่าร้อน §6§lL§e§lv§f§l. §e§l5");
    ROD5_META.setLore(Arrays.asList("§f48 §7Iron", "§f22 §6Gold"));
    ROD5.setItemMeta(ROD5_META);
    ROD5.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
    ROD5.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
    arrayList.add(ROD5);
    ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD);
    ItemMeta IRON_SWORD_META = IRON_SWORD.getItemMeta();
    IRON_SWORD_META.setDisplayName("§6ดา§eบเ§fเห่ง§eสัจธ§6รรม");
    IRON_SWORD_META.setLore(Arrays.asList("§f32 §7Iron", "§f18 §6Gold"));
    IRON_SWORD.setItemMeta(IRON_SWORD_META);
    IRON_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    IRON_SWORD.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    IRON_SWORD.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
    arrayList.add(IRON_SWORD);
    ItemStack IRON_SWORD2 = new ItemStack(Material.IRON_SWORD);
    ItemMeta IRON_SWORD2_META = IRON_SWORD2.getItemMeta();
    IRON_SWORD2_META.setDisplayName("§4ดาบเ§cเห่งอ§fาธร§cรพ์ต้อ§4งห้าม");
    IRON_SWORD2_META.setLore(Arrays.asList("§f48 §7Iron", "§f32 §6Gold"));
    IRON_SWORD2.setItemMeta(IRON_SWORD2_META);
    IRON_SWORD2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    IRON_SWORD2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
    IRON_SWORD2.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    arrayList.add(IRON_SWORD2);
    ItemStack DIAMOND_SWORD = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta DIAMOND_SWORD_META = DIAMOND_SWORD.getItemMeta();
    DIAMOND_SWORD_META.setDisplayName("§6§lSI§e§lAM§f§lC§e§lRA§f§lFT §6§lPE§e§lR§f§lF§e§lE§6§lCT");
    DIAMOND_SWORD_META.setLore(Arrays.asList("§f52 §7Iron", "§f48 §6Gold", "§r", "§8[§c✘§8] §7เมื่อสวมใส่จะทำให้ค่าหลอดอาหารลดลงเหลือ §a1"));
    DIAMOND_SWORD.setItemMeta(DIAMOND_SWORD_META);
    DIAMOND_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
    DIAMOND_SWORD.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
    DIAMOND_SWORD.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
    arrayList.add(DIAMOND_SWORD);
    ItemStack DIAMOND_SWORD2 = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta DIAMOND_SWORD2_META = DIAMOND_SWORD2.getItemMeta();
    DIAMOND_SWORD2_META.setDisplayName("§6§lSI§e§lAM§f§lC§e§lRA§f§lFT §6§lPE§e§lR§f§lF§e§lE§6§lCT §6§lEV§e§lOL§f§lU§e§lTIO§6§lN");
    DIAMOND_SWORD2_META.setLore(Arrays.asList("§f128 §7Iron", "§f72 §6Gold", "§r", "§8[§c✘§8] §7เมื่อสวมใส่จะทำให้ค่าหลอดอาหารลดลงเหลือ §a1"));
    DIAMOND_SWORD2.setItemMeta(DIAMOND_SWORD2_META);
    DIAMOND_SWORD2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
    DIAMOND_SWORD2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 6);
    DIAMOND_SWORD2.addUnsafeEnchantment(Enchantment.KNOCKBACK, 6);
    arrayList.add(DIAMOND_SWORD2);
    ItemStack SPECIAL3 = new ItemStack(Material.IRON_AXE);
    ItemMeta SPECIAL3_META = SPECIAL3.getItemMeta();
    SPECIAL3_META.setLore(Arrays.asList("§f34 §7Iron", "§f23 §6Gold"));
    SPECIAL3.setItemMeta(SPECIAL3_META);
    SPECIAL3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
    SPECIAL3.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    SPECIAL3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
    arrayList.add(SPECIAL3);
    ItemStack SPECIAL4 = new ItemStack(Material.DIAMOND_AXE);
    ItemMeta SPECIAL4_META = SPECIAL4.getItemMeta();
    SPECIAL4_META.setLore(Arrays.asList("§f49 §7Iron", "§f37 §6Gold"));
    SPECIAL4.setItemMeta(SPECIAL4_META);
    SPECIAL4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
    SPECIAL4.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
    SPECIAL4.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 8);
    arrayList.add(SPECIAL4);
    ItemStack SPECIAL = new ItemStack(Material.NETHER_STAR);
    ItemMeta SPECIAL_META = SPECIAL.getItemMeta();
    SPECIAL_META.setDisplayName("§fเวทย์มนต์ดลบรรดาล");
    SPECIAL_META.setLore(Arrays.asList("§f32 §7Iron", "§f10 §6Gold", "§r", "§8[§c✘§8] §cเมื่อสวมใส่จะได้รับบัฟด้านลบจำนวนมาก", "§6➜ §7Slow ระดับ I", "§6➜ §7Slow Atk ระดับ II"));
    SPECIAL.setItemMeta(SPECIAL_META);
    SPECIAL.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
    arrayList.add(SPECIAL);
    ItemStack SPECIAL2 = new ItemStack(Material.NETHER_STAR);
    ItemMeta SPECIAL2_META = SPECIAL2.getItemMeta();
    SPECIAL2_META.setDisplayName("§fเวทย์มนต์ดลบรรดาล §7(§6§l2§7)");
    SPECIAL2_META.setLore(Arrays.asList("§f64 §7Iron", "§f30 §6Gold", "§r", "§8[§c✘§8] §cเมื่อสวมใส่จะได้รับบัฟด้านลบจำนวนมาก", "§6➜ §7Slow ระดับ II", "§6➜ §7Slow Atk ระดับ II"));
    SPECIAL2.setItemMeta(SPECIAL2_META);
    SPECIAL2.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
    arrayList.add(SPECIAL2);
    return arrayList;
  }

  public static ArrayList<ItemStack> getRageItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack Snowball = new ItemStack(Material.SNOW_BALL, 8);
    ItemMeta Snowball_META = Snowball.getItemMeta();
    Snowball_META.setLore(Arrays.asList("§f16 §4Bronze"));
    Snowball.setItemMeta(Snowball_META);
    arrayList.add(Snowball);
    ItemStack Arrow = new ItemStack(Material.ARROW, 16);
    ItemMeta Arrow_META = Arrow.getItemMeta();
    Arrow_META.setLore(Arrays.asList("§f8 §7Iron", "§f2 §6Gold"));
    Arrow.setItemMeta(Arrow_META);
    arrayList.add(Arrow);
    ItemStack Bow1 = new ItemStack(Material.BOW);
    ItemMeta Bow1_META = Bow1.getItemMeta();
    Bow1_META.setLore(Arrays.asList("§f8 §7Iron"));
    Bow1.setItemMeta(Bow1_META);
    arrayList.add(Bow1);
    ItemStack Bow2 = new ItemStack(Material.BOW);
    ItemMeta Bow2_META = Bow2.getItemMeta();
    Bow2_META.setLore(Arrays.asList("§f12 §7Iron", "§f4 §6Gold"));
    Bow2.setItemMeta(Bow2_META);
    Bow2.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
    arrayList.add(Bow2);
    ItemStack Bow3 = new ItemStack(Material.BOW);
    ItemMeta Bow3_META = Bow3.getItemMeta();
    Bow3_META.setLore(Arrays.asList("§f24 §7Iron", "§f12 §6Gold"));
    Bow3.setItemMeta(Bow3_META);
    Bow3.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
    Bow3.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
    arrayList.add(Bow3);
    ItemStack Bow4 = new ItemStack(Material.BOW);
    ItemMeta Bow4_META = Bow4.getItemMeta();
    Bow4_META.setLore(Arrays.asList("§f24 §7Iron", "§f14 §6Gold"));
    Bow4.setItemMeta(Bow4_META);
    Bow4.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
    Bow4.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
    arrayList.add(Bow4);
    ItemStack Bow5 = new ItemStack(Material.BOW);
    ItemMeta Bow5_META = Bow5.getItemMeta();
    Bow5_META.setLore(Arrays.asList("§f32 §7Iron", "§f18 §6Gold"));
    Bow5.setItemMeta(Bow5_META);
    Bow5.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
    Bow5.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
    Bow5.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
    arrayList.add(Bow5);
    ItemStack Bow6 = new ItemStack(Material.BOW);
    ItemMeta Bow6_META = Bow6.getItemMeta();
    Bow6_META.setLore(Arrays.asList("§f48 §7Iron", "§f28 §6Gold"));
    Bow6.setItemMeta(Bow6_META);
    Bow6.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
    Bow6.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
    Bow6.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
    arrayList.add(Bow6);
    ItemStack Bow7 = new ItemStack(Material.BOW);
    ItemMeta Bow7_META = Bow7.getItemMeta();
    Bow7_META.setDisplayName("§8〘§6§lS§e§lC§8〙§7┋ §8ธน§7ูเ§fเห่ง§7โชคช§8ะตา §7┋ §6〄");
    Bow7_META.setLore(Arrays.asList("§f64 §7Iron", "§f50 §6Gold", "§r", "§8[§c✘§8] §cเมื่อสวมใส่จะลดเลือดทุกๆหนึ่งวินาที"));
    Bow7.setItemMeta(Bow7_META);
    Bow7.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 8);
    Bow7.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
    Bow7.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 5);
    arrayList.add(Bow7);
    return arrayList;
  }

  public static ArrayList<ItemStack> getFoodItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack Cookie = new ItemStack(Material.COOKIE, 4);
    ItemMeta Cookie_META = Cookie.getItemMeta();
    Cookie_META.setLore(Arrays.asList("§f6 §4Bronze"));
    Cookie.setItemMeta(Cookie_META);
    arrayList.add(Cookie);
    ItemStack Bread = new ItemStack(Material.BREAD, 2);
    ItemMeta Bread_META = Bread.getItemMeta();
    Bread_META.setLore(Arrays.asList("§f8 §4Bronze"));
    Bread.setItemMeta(Bread_META);
    arrayList.add(Bread);
    ItemStack Beef = new ItemStack(Material.COOKED_BEEF, 4);
    ItemMeta Beef_META = Beef.getItemMeta();
    Beef_META.setLore(Arrays.asList("§f12 §4Bronze", "§f2 §7Iron"));
    Beef.setItemMeta(Beef_META);
    arrayList.add(Beef);
    ItemStack Cake = new ItemStack(Material.CAKE, 1);
    ItemMeta Cake_META = Cake.getItemMeta();
    Cake_META.setLore(Arrays.asList("§f6 §7Iron"));
    Cake.setItemMeta(Cake_META);
    arrayList.add(Cake);
    ItemStack Gold_Apple = new ItemStack(Material.GOLDEN_APPLE, 1);
    ItemMeta Gold_Apple_META = Gold_Apple.getItemMeta();
    Gold_Apple_META.setDisplayName("§8〘§6§lS§e§lC§8〙§7┋ §6เเอปเ§eปิ้ลเ§fเห่งโ§eชคช§6ะตา §7┋ §d〄");
    Gold_Apple_META.setLore(Arrays.asList("§f8 §7Iron", "§f2 §6Gold", "§r", "§8[§c✘§8] §cเมื่อสวมใส่จะได้รับบัฟด้านลบจำนวนมาก", "§6➜ §7Slow ระดับ II", "§6➜ §7Slow Atk ระดับ II", "§6➜ §7Blindness ระดับ I"));
    Gold_Apple.setItemMeta(Gold_Apple_META);
    arrayList.add(Gold_Apple);
    return arrayList;
  }

  public static ArrayList<ItemStack> getPickaxeItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack Pickaxe1 = new ItemStack(Material.WOOD_PICKAXE, 1);
    ItemMeta Pickaxe1_META = Pickaxe1.getItemMeta();
    Pickaxe1_META.setLore(Arrays.asList("§f8 §4Bronze"));
    Pickaxe1.setItemMeta(Pickaxe1_META);
    Pickaxe1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    arrayList.add(Pickaxe1);
    ItemStack Pickaxe2 = new ItemStack(Material.STONE_PICKAXE, 1);
    ItemMeta Pickaxe2_META = Pickaxe2.getItemMeta();
    Pickaxe2_META.setLore(Arrays.asList("§f16 §4Bronze", "§f2 §7Iron"));
    Pickaxe2.setItemMeta(Pickaxe2_META);
    Pickaxe2.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
    arrayList.add(Pickaxe2);
    ItemStack Pickaxe3 = new ItemStack(Material.STONE_PICKAXE, 1);
    ItemMeta Pickaxe3_META = Pickaxe3.getItemMeta();
    Pickaxe3_META.setLore(Arrays.asList("§f32 §4Bronze", "§f6 §7Iron"));
    Pickaxe3.setItemMeta(Pickaxe3_META);
    Pickaxe3.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
    Pickaxe3.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
    arrayList.add(Pickaxe3);
    ItemStack Pickaxe4 = new ItemStack(Material.IRON_PICKAXE, 1);
    ItemMeta Pickaxe4_META = Pickaxe4.getItemMeta();
    Pickaxe4_META.setLore(Arrays.asList("§f12 §7Iron", "§f2 §6Gold"));
    Pickaxe4.setItemMeta(Pickaxe4_META);
    Pickaxe4.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
    Pickaxe4.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
    arrayList.add(Pickaxe4);
    ItemStack Pickaxe5 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
    ItemMeta Pickaxe5_META = Pickaxe5.getItemMeta();
    Pickaxe5_META.setLore(Arrays.asList("§f16 §7Iron", "§f8 §6Gold"));
    Pickaxe5.setItemMeta(Pickaxe5_META);
    Pickaxe5.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
    Pickaxe5.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
    arrayList.add(Pickaxe5);
    ItemStack Pickaxe6 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
    ItemMeta Pickaxe6_META = Pickaxe6.getItemMeta();
    Pickaxe6_META.setDisplayName("§8〘§6§lS§e§lC§8〙§7┋ §6สุดยอ§eดที่ขุ§fดขย่§eมเตี§6ยง §7┋ §d〄");
    Pickaxe6_META.setLore(Arrays.asList("§f38 §7Iron", "§f28 §6Gold", "§r", "§8[§c✘§8] §eเมื่อสวมใส่จะได้รับบัฟ", "§6➜ §7Protection ระดับ I", "§6➜ §7Slow ระดับ I"));
    Pickaxe6.setItemMeta(Pickaxe6_META);
    Pickaxe6.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
    Pickaxe6.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
    arrayList.add(Pickaxe6);
    ItemStack Pickaxe7 = new ItemStack(Material.SHEARS, 1);
    ItemMeta Pickaxe7_META = Pickaxe7.getItemMeta();
    Pickaxe7_META.setLore(Arrays.asList("§f4 §7Iron"));
    Pickaxe7.setItemMeta(Pickaxe7_META);
    arrayList.add(Pickaxe7);
    return arrayList;
  }

  public static ArrayList<ItemStack> getBlocksItems(DeluxePlayer deluxePlayer) {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack Block2;
    ItemMeta Block2_META;
    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.RED)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("red")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze", "§bเมื่อวางบล๊อคแล้วหลังจาก 10 วินาทีจะกลายเป็น SandStone"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GREEN)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("lime")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLUE)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("lightblue")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze", "§bเมื่อวางบล๊อคแล้วหลังจาก 10 วินาทีจะกลายเป็น SandStone"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.YELLOW)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("yellow")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze", "§bเมื่อวางบล๊อคแล้วหลังจาก 10 วินาทีจะกลายเป็น SandStone"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.WHITE)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("white")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze", "§bเมื่อวางบล๊อคแล้วหลังจาก 10 วินาทีจะกลายเป็น SandStone"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.GOLD)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("orange")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze", "§bเมื่อวางบล๊อคแล้วหลังจาก 10 วินาทีจะกลายเป็น SandStone"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.LIGHT_PURPLE)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("magenta")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze", "§bเมื่อวางบล๊อคแล้วหลังจาก 10 วินาทีจะกลายเป็น SandStone"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    if (deluxePlayer.getPlayer_team().equals(TEAM_COLOR.BLACK)) {
      Block2 = new ItemStack(Material.WOOL, 16, (short)((byte)Str_Byte_Color("black")));
      Block2_META = Block2.getItemMeta();
      Block2_META.setLore(Arrays.asList("§f4 §4Bronze", "§bเมื่อวางบล๊อคแล้วหลังจาก 10 วินาทีจะกลายเป็น SandStone"));
      Block2.setItemMeta(Block2_META);
      arrayList.add(Block2);
    }

    Block2 = new ItemStack(Material.RED_SANDSTONE, 16);
    Block2_META = Block2.getItemMeta();
    Block2_META.setLore(Arrays.asList("§f12 §4Bronze"));
    Block2.setItemMeta(Block2_META);
    arrayList.add(Block2);
    ItemStack Block3 = new ItemStack(Material.GLASS, 4);
    ItemMeta Block3_META = Block3.getItemMeta();
    Block3_META.setLore(Arrays.asList("§f8 §4Bronze"));
    Block3.setItemMeta(Block3_META);
    arrayList.add(Block3);
    ItemStack Block4 = new ItemStack(Material.ENDER_STONE, 12);
    ItemMeta Block4_META = Block4.getItemMeta();
    Block4_META.setLore(Arrays.asList("§f16 §4Bronze"));
    Block4.setItemMeta(Block4_META);
    arrayList.add(Block4);
    ItemStack Block5 = new ItemStack(Material.LADDER, 16);
    ItemMeta Block5_META = Block5.getItemMeta();
    Block5_META.setLore(Arrays.asList("§f22 §4Bronze"));
    Block5.setItemMeta(Block5_META);
    arrayList.add(Block5);
    ItemStack Block6 = new ItemStack(Material.WOOD, 16);
    ItemMeta Block6_META = Block6.getItemMeta();
    Block6_META.setLore(Arrays.asList("§f10 §7Iron"));
    Block6.setItemMeta(Block6_META);
    arrayList.add(Block6);
    ItemStack Block7 = new ItemStack(Material.OBSIDIAN, 4);
    ItemMeta Block7_META = Block7.getItemMeta();
    Block7_META.setLore(Arrays.asList("§f8 §6Gold"));
    Block7.setItemMeta(Block7_META);
    arrayList.add(Block7);
    ItemStack Block9 = new ItemStack(Material.CHEST, 1);
    ItemMeta Block9_META = Block9.getItemMeta();
    Block9_META.setLore(Arrays.asList("§f6 §7Iron"));
    Block9.setItemMeta(Block9_META);
    arrayList.add(Block9);
    ItemStack Block8 = new ItemStack(Material.ENDER_CHEST, 1);
    ItemMeta Block8_META = Block8.getItemMeta();
    Block8_META.setLore(Arrays.asList("§f4 §6Gold"));
    Block8.setItemMeta(Block8_META);
    arrayList.add(Block8);
    return arrayList;
  }

  public static ArrayList<ItemStack> getUnitItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack Unity1 = new ItemStack(Material.EGG, 1);
    ItemMeta Unity1_Meta = Unity1.getItemMeta();
    Unity1_Meta.setDisplayName("§fใข่เเมลงสาบ §c§o(ป้องกันฐาน)");
    Unity1_Meta.setLore(Arrays.asList("§f4 §6Gold", "§r", "§7§oเมื่อเสกออกมาจะมีเเมลงสาบง่ามจำนวนหนึ่งตัวออกมาป้องกันฐานของคุณ", "§7§oมันจะคอยกำจัดศัตรูที่เข้ามาใกล้ฐานเเละทำดาเมจเล็กน้อยเเก่ศัตรู", "§7§oเเต่มันจะหายไปหลังจากเวลา 60 วินาที", "§r", "§cจำกัดการเสกทีมละ 6 ตัว"));
    Unity1.setItemMeta(Unity1_Meta);
    arrayList.add(Unity1);
    ItemStack Unity2 = new ItemStack(Material.MONSTER_EGG, 1);
    ItemMeta Unity2_Meta = Unity2.getItemMeta();
    Unity2_Meta.setDisplayName("§fใข่เสกโกเลม §c§o(ป้องกันฐาน)");
    Unity2_Meta.setLore(Arrays.asList("§f12 §6Gold", "§r", "§7§oเมื่อเสกออกมาจะมีไอร่อนโกเลมจำนวนหนึ่งตัวออกมาป้องกันฐานของคุณ", "§7§oมันจะคอยกำจัดศัตรูที่เข้ามาใกล้ฐานเเละทำดาเมจเล็กน้อยเเก่ศัตรู", "§7§oเเต่มันจะหายไปหลังจากเวลา 180 วินาที", "§r", "§cจำกัดการเสกทีมละ 3 ตัว"));
    Unity2.setItemMeta(Unity2_Meta);
    arrayList.add(Unity2);
    ItemStack Unity3 = new ItemStack(Material.NAME_TAG, 1);
    ItemMeta Unity3_Meta = Unity3.getItemMeta();
    Unity3_Meta.setDisplayName("§fใข่เสกเเกะ §c§o(โจมตีศัตรู)");
    Unity3_Meta.setLore(Arrays.asList("§f8 §6Gold", "§r", "§7§oเมื่อเสกออกมาเเล้วเเกะตัวนี้จะมีสีเหมือนทีมของคุณ", "§7§oมันจะพยายามวิ่งไปหาศัตรูที่ใกล้ที่สุดที่มันจะหาได้", "§7§oเเละมันจะระเบิดภายในเวลา 10 วินาที"));
    Unity3.setItemMeta(Unity3_Meta);
    arrayList.add(Unity3);
    ItemStack Unity4 = new ItemStack(Material.ENDER_PEARL, 1);
    ItemMeta Unity4_Meta = Unity4.getItemMeta();
    Unity4_Meta.setLore(Arrays.asList("§f6 §6Gold", "§r", "§7§oคูลดาวน์หลังการใช้งานอีกรอบ 30 วินาที ", "§c§o(ใช้ได้เพียงรอบเดียว)"));
    Unity4.setItemMeta(Unity4_Meta);
    arrayList.add(Unity4);
    ItemStack Unity5 = new ItemStack(Material.WATER_BUCKET, 1);
    ItemMeta Unity5_Meta = Unity5.getItemMeta();
    Unity5_Meta.setLore(Arrays.asList("§f22 §7Iron"));
    Unity5.setItemMeta(Unity5_Meta);
    arrayList.add(Unity5);
    ItemStack Unity6 = new ItemStack(Material.FIREBALL, 1);
    ItemMeta Unity6_Meta = Unity6.getItemMeta();
    Unity6_Meta.setLore(Arrays.asList("§f64 §4Bronze"));
    Unity6.setItemMeta(Unity6_Meta);
    arrayList.add(Unity6);
    ItemStack Unity7 = new ItemStack(Material.MILK_BUCKET, 1);
    ItemMeta Unity7_Meta = Unity7.getItemMeta();
    Unity7_Meta.setLore(Arrays.asList("§f32 §4Bronze"));
    Unity7.setItemMeta(Unity7_Meta);
    arrayList.add(Unity7);
    ItemStack Unity8 = new ItemStack(Material.SPONGE, 4);
    ItemMeta Unity8_Meta = Unity8.getItemMeta();
    Unity8_Meta.setLore(Arrays.asList("§f8 §6Gold"));
    Unity8.setItemMeta(Unity8_Meta);
    arrayList.add(Unity8);
    ItemStack Unity9 = new ItemStack(Material.BLAZE_ROD, 1);
    ItemMeta Unity9_Meta = Unity9.getItemMeta();
    Unity9_Meta.setDisplayName("§fพื้นสวรรค์ §c§o(ช่วยเหลือ)");
    Unity9_Meta.setLore(Arrays.asList("§f24 §7Iron", "§f16 §6Gold", "§r", "§7§oเมื่อคุณตกจากที่สูงทำการคลิ๊กขวาที่ไอเทมนี้", "§7§oไอเทมนี้จะทำการสร้างพื้นที่รองรับใว้ในระยะเวลาสั้นๆ", "§7§oเเละจะหายไปในเวลา 10 วินาที"));
    Unity9.setItemMeta(Unity9_Meta);
    arrayList.add(Unity9);
    ItemStack Unity10 = new ItemStack(Material.SLIME_BLOCK, 1);
    ItemMeta Unity10_Meta = Unity10.getItemMeta();
    Unity10.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    Unity10_Meta.setDisplayName("§fเชื่องช้า §c§o(ช่วยเหลือ)");
    Unity10_Meta.setLore(Arrays.asList("§f46 §7Iron", "§f10 §6Gold", "§r", "§7§oเมื่อคุณหนีจากศัตรูไม่รอดให้ทำการคลิ๊กขวาไอเทมนี้", "§7§oลงบนพื้นมันจะทำให้พื้นในระยะ 4x4 ติดสถานะ", "§7§oเชื่องช้าเเละจะหายไปในเวลา 5 วินาที"));
    Unity10.setItemMeta(Unity10_Meta);
    arrayList.add(Unity10);
    ItemStack Unity11 = new ItemStack(Material.TNT, 1);
    ItemMeta Unity11_Meta = Unity11.getItemMeta();
    Unity11_Meta.setDisplayName("§fระเบิด §c§o(โจมตีศัตรู)");
    Unity11_Meta.setLore(Arrays.asList("§f8 §7Iron", "§f1 §6Gold", "§r", "§7§oเมื่อทำการวางระเบิดจะติดตั้งอัตโนมัติทันที"));
    Unity11.setItemMeta(Unity11_Meta);
    arrayList.add(Unity11);
    ItemStack Unity12 = new ItemStack(Material.WORKBENCH, 1);
    ItemMeta Unity12_Meta = Unity12.getItemMeta();
    Unity12_Meta.setDisplayName("§fพื้นถล่ม §c§o(ป้องกัน)");
    Unity12_Meta.setLore(Arrays.asList("§f8 §7Iron", "§f4 §6Gold", "§r", "§7§oทำการวางสิ่งนี้ลงบยพื้นเมื่อศัตรู", "§7§oเดินมาเหยียบบล้อคนี้จะหายไปทันที"));
    Unity12.setItemMeta(Unity12_Meta);
    arrayList.add(Unity12);
    return arrayList;
  }

  public static ArrayList<ItemStack> getExchangeItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack EX1 = new ItemStack(Material.IRON_INGOT, 1);
    ItemMeta EX1_Meta = EX1.getItemMeta();
    EX1_Meta.setDisplayName("§7Iron");
    EX1_Meta.setLore(Arrays.asList("§f64 §4Bronze", "§bใว้ใช้สำหรับซื้อของที่ NPC"));
    EX1.setItemMeta(EX1_Meta);
    arrayList.add(EX1);
    ItemStack EX2 = new ItemStack(Material.GOLD_INGOT, 1);
    ItemMeta EX2_Meta = EX2.getItemMeta();
    EX2_Meta.setDisplayName("§6Gold");
    EX2_Meta.setLore(Arrays.asList("§f256 §4Bronze", "§f48 §7Iron", "§bใว้ใช้สำหรับซื้อของที่ NPC"));
    EX2.setItemMeta(EX2_Meta);
    arrayList.add(EX2);
    return arrayList;
  }

  public static ArrayList<ItemStack> getPotionItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack G1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G1_Meta = G1.getItemMeta();
    G1_Meta.setDisplayName("§r§r");
    G1.setItemMeta(G1_Meta);
    arrayList.add(G1);
    ItemStack G2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G2_Meta = G2.getItemMeta();
    G2_Meta.setDisplayName("§r§r§r");
    G2.setItemMeta(G2_Meta);
    arrayList.add(G2);
    ItemStack G3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G3_Meta = G3.getItemMeta();
    G3_Meta.setDisplayName("§r§r§r§r");
    G3.setItemMeta(G3_Meta);
    arrayList.add(G3);
    ItemStack G4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G4_Meta = G4.getItemMeta();
    G4_Meta.setDisplayName("§r§r§r§r§r");
    G4.setItemMeta(G4_Meta);
    arrayList.add(G4);
    ItemStack G5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G5_Meta = G5.getItemMeta();
    G5_Meta.setDisplayName("§r§r§r§r§r§r");
    G5.setItemMeta(G5_Meta);
    arrayList.add(G5);
    ItemStack G6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G6_Meta = G6.getItemMeta();
    G6_Meta.setDisplayName("§r§r§r§r§r§r§r");
    G6.setItemMeta(G6_Meta);
    arrayList.add(G6);
    ItemStack G7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G7_Meta = G7.getItemMeta();
    G7_Meta.setDisplayName("§r§r§r§r§r§r§r§r");
    G7.setItemMeta(G7_Meta);
    arrayList.add(G7);
    ItemStack G8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G8_Meta = G8.getItemMeta();
    G8_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r");
    G8.setItemMeta(G8_Meta);
    arrayList.add(G8);
    ItemStack G9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)((byte)Str_Byte_Color("black")));
    ItemMeta G9_Meta = G9.getItemMeta();
    G9_Meta.setDisplayName("§r§r§r§r§r§r§r§r§r§r");
    G9.setItemMeta(G9_Meta);
    arrayList.add(G9);
    ItemStack imt = new ItemStack(Material.POTION, 1, (short)8197);
    ItemMeta imt_Meta = imt.getItemMeta();
    imt_Meta.setLore(Arrays.asList("§f4 §6Gold"));
    imt.setItemMeta(imt_Meta);
    arrayList.add(imt);
    ItemStack imt2 = new ItemStack(Material.POTION, 1, (short)8201);
    ItemMeta imt2_Meta = imt2.getItemMeta();
    imt2_Meta.setLore(Arrays.asList("§f8 §6Gold"));
    imt2.setItemMeta(imt2_Meta);
    arrayList.add(imt2);
    ItemStack im = new ItemStack(Material.POTION, 1, (short)8226);
    ItemMeta im_Meta = im.getItemMeta();
    im_Meta.setLore(Arrays.asList("§f6 §6Gold"));
    im.setItemMeta(im_Meta);
    arrayList.add(im);
    ItemStack im2 = new ItemStack(Material.POTION, 1, (short)16394);
    ItemMeta im2_Meta = imt2.getItemMeta();
    im2_Meta.setLore(Arrays.asList("§f12 §7Iron", "§f6 §6Gold"));
    im2.setItemMeta(im2_Meta);
    arrayList.add(im2);
    ItemStack im4 = new ItemStack(Material.POTION, 1, (short)16393);
    ItemMeta im4_Meta = im4.getItemMeta();
    im4_Meta.setLore(Arrays.asList("§f10 §7Iron", "§f6 §6Gold"));
    im4.setItemMeta(im4_Meta);
    arrayList.add(im4);
    ItemStack im42 = new ItemStack(Material.POTION, 1, (short)16425);
    ItemMeta im42_Meta = im4.getItemMeta();
    im42_Meta.setLore(Arrays.asList("§f25 §7Iron", "§f12 §6Gold"));
    im42.setItemMeta(im42_Meta);
    ItemStack im3 = new ItemStack(Material.POTION, 1, (short)16396);
    ItemMeta im3_Meta = im3.getItemMeta();
    im3_Meta.setLore(Arrays.asList("§f22 §7Iron", "§f8 §6Gold"));
    im3.setItemMeta(im3_Meta);
    arrayList.add(im3);
    ItemStack imx = new ItemStack(Material.POTION, 1, (short)16428);
    ItemMeta imx_Meta = imx.getItemMeta();
    imx_Meta.setLore(Arrays.asList("§f34 §7Iron", "§f10 §6Gold"));
    imx.setItemMeta(imx_Meta);
    arrayList.add(imx);
    ItemStack im5 = new ItemStack(Material.POTION, 1, (short)16449);
    ItemMeta im5_Meta = im5.getItemMeta();
    im5_Meta.setLore(Arrays.asList("§f8 §6Gold"));
    im5.setItemMeta(im5_Meta);
    arrayList.add(im5);
    ItemStack im6 = new ItemStack(Material.POTION, 1, (short)16392);
    ItemMeta im6_Meta = im6.getItemMeta();
    im6_Meta.setLore(Arrays.asList("§f10 §6Gold"));
    im6.setItemMeta(im6_Meta);
    arrayList.add(im6);
    ItemStack im6x = new ItemStack(Material.POTION, 1, (short)16456);
    ItemMeta im6x_Meta = im6x.getItemMeta();
    im6x_Meta.setLore(Arrays.asList("§f16 §7Iron", "§f10 §6Gold"));
    im6x.setItemMeta(im6x_Meta);
    arrayList.add(im6x);
    ItemStack im7 = new ItemStack(Material.POTION, 1, (short)16451);
    ItemMeta im7_Meta = im7.getItemMeta();
    im7_Meta.setLore(Arrays.asList("§f16 §6Gold"));
    im7.setItemMeta(im7_Meta);
    arrayList.add(im7);
    return arrayList;
  }

  public static void onHoldItem(Player p) {
    Bukkit.getLogger().info("Checking player for: " + p.getName());
    if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.NETHER_STAR && p.getItemInHand().getItemMeta().getDisplayName().contains("§fเวทย์มนต์ดลบรรดาล")) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 1));
    }

    if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.NETHER_STAR && p.getItemInHand().getItemMeta().getDisplayName().contains("§fเวทย์มนต์ดลบรรดาล")) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 1));
    }

    if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.DIAMOND_PICKAXE && p.getItemInHand().getItemMeta().getDisplayName().contains("§8〘§6§lS§e§lC§8〙§7┋ §6สุดยอ§eดที่ขุ§fดขย่§eมเตี§6ยง §7┋ §d〄")) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 0));
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
    }

    if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.GOLDEN_APPLE && p.getItemInHand().getItemMeta().getDisplayName().contains("§8〘§6§lS§e§lC§8〙§7┋ §6เเอปเ§eปิ้ลเ§fเห่งโ§eชคช§6ะตา §7┋ §d〄")) {
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 1));
      p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
    }

    if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.BOW && p.getItemInHand().getItemMeta().getDisplayName().contains("§8〘§6§lS§e§lC§8〙§7┋ §8ธน§7ูเ§fเห่ง§7โชคช§8ะตา §7┋ §6〄")) {
      p.damage(2.0D);
    }

    if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE && p.getInventory().getChestplate().getItemMeta() != null && p.getInventory().getChestplate().getItemMeta().getDisplayName() != null && p.getInventory().getChestplate().getItemMeta().getDisplayName().contains("§fเกราะเเห่งสัจธรรม §6§lEV§e§lOL§f§lU§e§lTI§6§lON")) {
      p.setHealthScale(30.0D);
    } else {
      p.setHealthScale(20.0D);
    }

    if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.DIAMOND_SWORD && p.getItemInHand().getItemMeta().getDisplayName().contains("§6§lSI§e§lAM§f§lC§e§lRA§f§lFT §6§lPE§e§lR§f§lF§e§lE§6§lCT §6§lEV§e§lOL§f§lU§e§lTIO§6§lN")) {
      p.setFoodLevel(1);
    } else {
      p.setFoodLevel(20);
    }

    if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.DIAMOND_SWORD && p.getItemInHand().getItemMeta().getDisplayName().contains("§6§lSI§e§lAM§f§lC§e§lRA§f§lFT §6§lPE§e§lR§f§lF§e§lE§6§lCT")) {
      p.setFoodLevel(1);
    } else {
      p.setFoodLevel(20);
    }

  }

  public static int Str_Byte_Color(String color) {
    int data = 0;
    byte var3 = -1;
    switch(color.hashCode()) {
      case -1008851410:
        if (color.equals("orange")) {
          var3 = 1;
        }
        break;
      case -976943172:
        if (color.equals("purple")) {
          var3 = 10;
        }
        break;
      case -734239628:
        if (color.equals("yellow")) {
          var3 = 4;
        }
        break;
      case 112785:
        if (color.equals("red")) {
          var3 = 14;
        }
        break;
      case 3027034:
        if (color.equals("blue")) {
          var3 = 11;
        }
        break;
      case 3068707:
        if (color.equals("cyan")) {
          var3 = 9;
        }
        break;
      case 3181155:
        if (color.equals("gray")) {
          var3 = 7;
        }
        break;
      case 3321813:
        if (color.equals("lime")) {
          var3 = 5;
        }
        break;
      case 3441014:
        if (color.equals("pink")) {
          var3 = 6;
        }
        break;
      case 93818879:
        if (color.equals("black")) {
          var3 = 15;
        }
        break;
      case 94011702:
        if (color.equals("brown")) {
          var3 = 12;
        }
        break;
      case 98619139:
        if (color.equals("green")) {
          var3 = 13;
        }
        break;
      case 113101865:
        if (color.equals("white")) {
          var3 = 0;
        }
        break;
      case 686090864:
        if (color.equals("lightblue")) {
          var3 = 3;
        }
        break;
      case 686244985:
        if (color.equals("lightgray")) {
          var3 = 8;
        }
        break;
      case 828922025:
        if (color.equals("magenta")) {
          var3 = 2;
        }
    }

    switch(var3) {
      case 0:
        data = 0;
        break;
      case 1:
        data = 1;
        break;
      case 2:
        data = 2;
        break;
      case 3:
        data = 3;
        break;
      case 4:
        data = 4;
        break;
      case 5:
        data = 5;
        break;
      case 6:
        data = 6;
        break;
      case 7:
        data = 7;
        break;
      case 8:
        data = 8;
        break;
      case 9:
        data = 9;
        break;
      case 10:
        data = 10;
        break;
      case 11:
        data = 11;
        break;
      case 12:
        data = 12;
        break;
      case 13:
        data = 13;
        break;
      case 14:
        data = 14;
        break;
      case 15:
        data = 15;
    }

    return data;
  }

  public static boolean spawnfloor(Player player) {
    Material mat = Material.GLASS;
    Location loc = player.getLocation();
    player.setFallDistance(0.0F);
    final int r = 3;
    final int cx = loc.getBlockX();
    final int cy = loc.getBlockY() - 5;
    final int cz = loc.getBlockZ();
    final World w = loc.getWorld();
    final int rSquared = r * r;
    Boolean is_have_block = false;

    int x;
    int z;
    for(x = cx - r; x <= cx + r; ++x) {
      for(z = cz - r; z <= cz + r; ++z) {
        if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared && w.getBlockAt(x, cy, z).getType() != Material.AIR) {
          is_have_block = true;
        }
      }
    }

    if (is_have_block) {
      return false;
    } else {
      for(x = cx - r; x <= cx + r; ++x) {
        for(z = cz - r; z <= cz + r; ++z) {
          if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared) {
            w.getBlockAt(x, cy, z).setType(mat);
          }
        }
      }

      (new BukkitRunnable() {
        public void run() {
          for(int x = cx - r; x <= cx + r; ++x) {
            for(int z = cz - r; z <= cz + r; ++z) {
              if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared) {
                w.getBlockAt(x, cy, z).setType(Material.AIR);
              }
            }
          }

          this.cancel();
        }
      }).runTaskTimer(core.getDeluxe, 300L, 300L);
      return true;
    }
  }
}
