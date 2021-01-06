//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.bedwars_classic;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class shop {
  public shop() {
  }

  public static ArrayList<ItemStack> getArmorItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
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
    GOLD_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
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
    CHAINMAIL_HELMET_META.setLore(Arrays.asList("§f2 §7Iron", "§f5 §4Bronze"));
    CHAINMAIL_HELMET.setItemMeta(CHAINMAIL_HELMET_META);
    CHAINMAIL_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    CHAINMAIL_HELMET.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_HELMET);
    ItemStack CHAINMAIL_CHESTPLATE = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    ItemMeta CHAINMAIL_CHESTPLATE_META = CHAINMAIL_CHESTPLATE.getItemMeta();
    CHAINMAIL_CHESTPLATE_META.setLore(Arrays.asList("§f6 §7Iron", "§f10 §4Bronze"));
    CHAINMAIL_CHESTPLATE.setItemMeta(CHAINMAIL_CHESTPLATE_META);
    CHAINMAIL_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    CHAINMAIL_CHESTPLATE.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_CHESTPLATE);
    ItemStack CHAINMAIL_LEGGINGS = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    ItemMeta CHAINMAIL_LEGGINGS_META = CHAINMAIL_LEGGINGS.getItemMeta();
    CHAINMAIL_LEGGINGS_META.setLore(Arrays.asList("§f6 §7Iron", "§f10 §4Bronze"));
    CHAINMAIL_LEGGINGS.setItemMeta(CHAINMAIL_LEGGINGS_META);
    CHAINMAIL_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    CHAINMAIL_LEGGINGS.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_LEGGINGS);
    ItemStack CHAINMAIL_BOOTS = new ItemStack(Material.CHAINMAIL_BOOTS);
    ItemMeta CHAINMAIL_BOOTS_META = CHAINMAIL_BOOTS.getItemMeta();
    CHAINMAIL_BOOTS_META.setLore(Arrays.asList("§f2 §7Iron", "§f5 §4Bronze"));
    CHAINMAIL_BOOTS.setItemMeta(CHAINMAIL_BOOTS_META);
    CHAINMAIL_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    CHAINMAIL_BOOTS.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(CHAINMAIL_BOOTS);
    ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET);
    ItemMeta IRON_HELMET_META = IRON_HELMET.getItemMeta();
    IRON_HELMET_META.setLore(Arrays.asList("§f4 §7Iron", "§f3 §6Gold"));
    IRON_HELMET.setItemMeta(IRON_HELMET_META);
    IRON_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    IRON_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    IRON_HELMET.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(IRON_HELMET);
    ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE);
    ItemMeta IRON_CHESTPLATE_META = IRON_CHESTPLATE.getItemMeta();
    IRON_CHESTPLATE_META.setLore(Arrays.asList("§f8 §7Iron", "§f6 §6Gold"));
    IRON_CHESTPLATE.setItemMeta(IRON_CHESTPLATE_META);
    IRON_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    IRON_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    IRON_CHESTPLATE.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(IRON_CHESTPLATE);
    ItemStack IRON_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS);
    ItemMeta IRON_LEGGINGS_META = IRON_CHESTPLATE.getItemMeta();
    IRON_LEGGINGS_META.setLore(Arrays.asList("§f8 §7Iron", "§f6 §6Gold"));
    IRON_LEGGINGS.setItemMeta(IRON_LEGGINGS_META);
    IRON_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    IRON_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    IRON_LEGGINGS.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(IRON_LEGGINGS);
    ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS);
    ItemMeta IRON_BOOTS_META = IRON_BOOTS.getItemMeta();
    IRON_BOOTS_META.setLore(Arrays.asList("§f4 §7Iron", "§f3 §6Gold"));
    IRON_BOOTS.setItemMeta(IRON_LEGGINGS_META);
    IRON_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    IRON_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    IRON_BOOTS.addUnsafeEnchantment(Enchantment.THORNS, 1);
    arrayList.add(IRON_BOOTS);
    ItemStack DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET);
    ItemMeta DIAMOND_HELMET_META = DIAMOND_HELMET.getItemMeta();
    DIAMOND_HELMET_META.setLore(Arrays.asList("§f32 §7Iron", "§f22 §6Gold"));
    DIAMOND_HELMET.setItemMeta(DIAMOND_HELMET_META);
    DIAMOND_HELMET.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    arrayList.add(DIAMOND_HELMET);
    ItemStack DIAMOND_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta DIAMOND_CHESTPLATE_META = DIAMOND_CHESTPLATE.getItemMeta();
    DIAMOND_CHESTPLATE_META.setLore(Arrays.asList("§f32 §7Iron", "§f25 §6Gold"));
    DIAMOND_CHESTPLATE.setItemMeta(DIAMOND_HELMET_META);
    DIAMOND_CHESTPLATE.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    arrayList.add(DIAMOND_CHESTPLATE);
    ItemStack DIAMOND_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS);
    ItemMeta DIAMOND_LEGGINGS_META = DIAMOND_LEGGINGS.getItemMeta();
    DIAMOND_LEGGINGS_META.setLore(Arrays.asList("§f32 §7Iron", "§f22 §6Gold"));
    DIAMOND_LEGGINGS.setItemMeta(DIAMOND_LEGGINGS_META);
    DIAMOND_LEGGINGS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
    arrayList.add(DIAMOND_LEGGINGS);
    ItemStack DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS);
    ItemMeta DIAMOND_BOOTS_META = DIAMOND_BOOTS.getItemMeta();
    DIAMOND_BOOTS_META.setLore(Arrays.asList("§f32 §7Iron", "§f20 §6Gold"));
    DIAMOND_BOOTS.setItemMeta(DIAMOND_BOOTS_META);
    DIAMOND_BOOTS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
    arrayList.add(DIAMOND_BOOTS);
    return arrayList;
  }

  public static ArrayList<ItemStack> getMeleeItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack STICK = new ItemStack(Material.STICK);
    ItemMeta STICK_META = STICK.getItemMeta();
    STICK_META.setLore(Arrays.asList("§f6 §4Bronze"));
    STICK.setItemMeta(STICK_META);
    STICK.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
    arrayList.add(STICK);
    ItemStack STICK2 = new ItemStack(Material.STICK);
    ItemMeta STICK2_META = STICK2.getItemMeta();
    STICK2_META.setLore(Arrays.asList("§f16 §4Bronze"));
    STICK2.setItemMeta(STICK2_META);
    STICK2.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    arrayList.add(STICK2);
    ItemStack STICK3 = new ItemStack(Material.STICK);
    ItemMeta STICK3_META = STICK3.getItemMeta();
    STICK3_META.setLore(Arrays.asList("§f64 §4Bronze"));
    STICK3.setItemMeta(STICK3_META);
    STICK3.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
    arrayList.add(STICK3);
    ItemStack STICK4 = new ItemStack(Material.STICK);
    ItemMeta STICK4_META = STICK4.getItemMeta();
    STICK4_META.setLore(Arrays.asList("§f128 §4Bronze", "§f6 §6Gold"));
    STICK4.setItemMeta(STICK4_META);
    STICK4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
    arrayList.add(STICK4);
    ItemStack STICK5 = new ItemStack(Material.STICK);
    ItemMeta STICK5_META = STICK5.getItemMeta();
    STICK5_META.setLore(Arrays.asList("§f256 §4Bronze", "§f12 §6Gold"));
    STICK5.setItemMeta(STICK5_META);
    STICK5.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
    STICK5.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    arrayList.add(STICK5);
    ItemStack GOLD_SWORD = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD_META = GOLD_SWORD.getItemMeta();
    GOLD_SWORD_META.setLore(Arrays.asList("§f3 §4Bronze"));
    GOLD_SWORD.setItemMeta(GOLD_SWORD_META);
    arrayList.add(GOLD_SWORD);
    ItemStack GOLD_SWORD2 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD2_META = GOLD_SWORD2.getItemMeta();
    GOLD_SWORD2_META.setLore(Arrays.asList("§f12 §4Bronze"));
    GOLD_SWORD2.setItemMeta(GOLD_SWORD2_META);
    GOLD_SWORD2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    arrayList.add(GOLD_SWORD2);
    ItemStack GOLD_SWORD3 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD3_META = GOLD_SWORD3.getItemMeta();
    GOLD_SWORD3_META.setLore(Arrays.asList("§f32 §4Bronze"));
    GOLD_SWORD3.setItemMeta(GOLD_SWORD3_META);
    GOLD_SWORD3.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    arrayList.add(GOLD_SWORD3);
    ItemStack GOLD_SWORD4 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta GOLD_SWORD4_META = GOLD_SWORD4.getItemMeta();
    GOLD_SWORD4_META.setLore(Arrays.asList("§f128 §4Bronze", "§f16 §6Gold"));
    GOLD_SWORD4.setItemMeta(GOLD_SWORD4_META);
    GOLD_SWORD4.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
    GOLD_SWORD4.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
    arrayList.add(GOLD_SWORD4);
    ItemStack ROD = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD_META = ROD.getItemMeta();
    ROD_META.setLore(Arrays.asList("§f3 §4Bronze"));
    ROD.setItemMeta(ROD_META);
    ROD.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
    arrayList.add(ROD);
    ItemStack ROD2 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD2_META = ROD2.getItemMeta();
    ROD2_META.setLore(Arrays.asList("§f6 §4Bronze"));
    ROD2.setItemMeta(ROD2_META);
    ROD2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
    arrayList.add(ROD2);
    ItemStack ROD3 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD3_META = ROD3.getItemMeta();
    ROD3_META.setLore(Arrays.asList("§f18 §4Bronze"));
    ROD3.setItemMeta(ROD3_META);
    ROD3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    arrayList.add(ROD3);
    ItemStack ROD4 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD4_META = ROD4.getItemMeta();
    ROD4_META.setLore(Arrays.asList("§f32 §4Bronze"));
    ROD4.setItemMeta(ROD4_META);
    ROD4.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
    arrayList.add(ROD4);
    ItemStack ROD5 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta ROD5_META = ROD5.getItemMeta();
    ROD5_META.setLore(Arrays.asList("§f64 §4Bronze"));
    ROD5.setItemMeta(ROD5_META);
    ROD5.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
    arrayList.add(ROD5);
    ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD);
    ItemMeta STONE_SWORD_META = STONE_SWORD.getItemMeta();
    STONE_SWORD_META.setLore(Arrays.asList("§f12 §4Bronze", "§f3 §7Iron"));
    STONE_SWORD.setItemMeta(STONE_SWORD_META);
    STONE_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    STONE_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
    STONE_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 1);
    arrayList.add(STONE_SWORD);
    ItemStack IRON_SWORD2 = new ItemStack(Material.STONE_SWORD);
    ItemMeta IRON_SWORD2_META = IRON_SWORD2.getItemMeta();
    IRON_SWORD2_META.setLore(Arrays.asList("§f24 §4Bronze", "§f6 §7Iron"));
    IRON_SWORD2.setItemMeta(IRON_SWORD2_META);
    IRON_SWORD2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    IRON_SWORD2.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
    IRON_SWORD2.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 1);
    arrayList.add(IRON_SWORD2);
    ItemStack IRON_SWORD3 = new ItemStack(Material.STONE_SWORD);
    ItemMeta IRON_SWORD3_META = IRON_SWORD3.getItemMeta();
    IRON_SWORD3_META.setLore(Arrays.asList("§f64 §4Bronze", "§f18 §7Iron"));
    IRON_SWORD3.setItemMeta(IRON_SWORD3_META);
    IRON_SWORD3.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    IRON_SWORD3.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
    IRON_SWORD3.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 2);
    arrayList.add(IRON_SWORD3);
    ItemStack IRON_SWORD4 = new ItemStack(Material.STONE_SWORD);
    ItemMeta IRON_SWORD4_META = IRON_SWORD4.getItemMeta();
    IRON_SWORD4_META.setLore(Arrays.asList("§f8 §6Gold", "§f32 §7Iron"));
    IRON_SWORD4.setItemMeta(IRON_SWORD4_META);
    IRON_SWORD4.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    IRON_SWORD4.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
    IRON_SWORD4.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 2);
    arrayList.add(IRON_SWORD4);
    ItemStack IRON_SWORD5 = new ItemStack(Material.IRON_SWORD);
    ItemMeta IRON_SWORD5_META = IRON_SWORD5.getItemMeta();
    IRON_SWORD5_META.setLore(Arrays.asList("§f8 §6Gold"));
    IRON_SWORD5.setItemMeta(IRON_SWORD5_META);
    IRON_SWORD5.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    IRON_SWORD5.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
    arrayList.add(IRON_SWORD5);
    ItemStack IRON_SWORD6 = new ItemStack(Material.IRON_SWORD);
    ItemMeta IRON_SWORD6_META = IRON_SWORD6.getItemMeta();
    IRON_SWORD6_META.setLore(Arrays.asList("§f15 §6Gold"));
    IRON_SWORD6.setItemMeta(IRON_SWORD6_META);
    IRON_SWORD6.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    IRON_SWORD6.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    IRON_SWORD6.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
    arrayList.add(IRON_SWORD6);
    ItemStack SPECIAL = new ItemStack(Material.NETHER_STAR);
    ItemMeta SPECIAL_META = SPECIAL.getItemMeta();
    SPECIAL_META.setDisplayName("§aMagic");
    SPECIAL_META.setLore(Arrays.asList("§f20 §6Gold"));
    SPECIAL.setItemMeta(SPECIAL_META);
    SPECIAL.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    SPECIAL.addUnsafeEnchantment(Enchantment.KNOCKBACK, 8);
    SPECIAL.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 6);
    arrayList.add(SPECIAL);
    ItemStack SPECIAL3 = new ItemStack(Material.IRON_AXE);
    ItemMeta SPECIAL3_META = SPECIAL3.getItemMeta();
    SPECIAL3_META.setLore(Arrays.asList("§f20 §6Gold"));
    SPECIAL3.setItemMeta(SPECIAL3_META);
    SPECIAL3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    SPECIAL3.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    SPECIAL3.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
    arrayList.add(SPECIAL3);
    ItemStack SPECIAL2 = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta SPECIAL2_META = SPECIAL2.getItemMeta();
    SPECIAL2_META.setLore(Arrays.asList("§f36 §7Iron", "§f48 §6Gold"));
    SPECIAL2.setItemMeta(SPECIAL2_META);
    SPECIAL2.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
    SPECIAL2.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    SPECIAL2.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 3);
    SPECIAL2.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
    SPECIAL2.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 3);
    SPECIAL2.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    arrayList.add(SPECIAL2);
    return arrayList;
  }

  public static ArrayList<ItemStack> getRageItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack Snowball = new ItemStack(Material.SNOW_BALL, 1);
    ItemMeta Snowball_META = Snowball.getItemMeta();
    Snowball_META.setLore(Arrays.asList("§f1 §4Bronze"));
    Snowball.setItemMeta(Snowball_META);
    arrayList.add(Snowball);
    ItemStack Arrow15 = new ItemStack(Material.BOW, 1);
    ItemMeta Arrow15_META = Arrow15.getItemMeta();
    Arrow15_META.setLore(Arrays.asList("§f32 §7Iron"));
    Arrow15.setItemMeta(Arrow15_META);
    arrayList.add(Arrow15);
    ItemStack Arrow = new ItemStack(Material.BOW, 1);
    ItemMeta Arrow_META = Arrow.getItemMeta();
    Arrow_META.setLore(Arrays.asList("§f3 §6Gold"));
    Arrow.setItemMeta(Arrow_META);
    Arrow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    arrayList.add(Arrow);
    ItemStack Bow1 = new ItemStack(Material.BOW);
    ItemMeta Bow1_META = Bow1.getItemMeta();
    Bow1_META.setLore(Arrays.asList("§f7 §6Gold"));
    Bow1.setItemMeta(Bow1_META);
    Bow1.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    Bow1.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
    arrayList.add(Bow1);
    ItemStack Bow2 = new ItemStack(Material.BOW);
    ItemMeta Bow2_META = Bow2.getItemMeta();
    Bow2_META.setLore(Arrays.asList("§f13 §6Gold"));
    Bow2.setItemMeta(Bow2_META);
    Bow2.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
    Bow2.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    arrayList.add(Bow2);
    ItemStack Bow3 = new ItemStack(Material.BOW);
    ItemMeta Bow3_META = Bow3.getItemMeta();
    Bow3_META.setLore(Arrays.asList("§f16 §6Gold"));
    Bow3.setItemMeta(Bow3_META);
    Bow3.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 2);
    Bow3.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
    arrayList.add(Bow3);
    ItemStack Bow4 = new ItemStack(Material.BOW);
    ItemMeta Bow4_META = Bow4.getItemMeta();
    Bow4_META.setLore(Arrays.asList("§f17 §6Gold"));
    Bow4.setItemMeta(Bow4_META);
    Bow4.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
    Bow4.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
    Bow4.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    arrayList.add(Bow4);
    ItemStack Bow5 = new ItemStack(Material.BOW);
    ItemMeta Bow5_META = Bow5.getItemMeta();
    Bow5_META.setLore(Arrays.asList("§f30 §6Gold"));
    Bow5.setItemMeta(Bow5_META);
    Bow5.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
    Bow5.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
    Bow5.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 2);
    Bow5.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
    arrayList.add(Bow5);
    ItemStack Bow6 = new ItemStack(Material.ARROW, 8);
    ItemMeta Bow6_META = Bow6.getItemMeta();
    Bow6_META.setLore(Arrays.asList("§f1 §7Iron"));
    Bow6.setItemMeta(Bow6_META);
    arrayList.add(Bow6);
    return arrayList;
  }

  public static ArrayList<ItemStack> getFoodItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack Cookie = new ItemStack(Material.APPLE, 3);
    ItemMeta Cookie_META = Cookie.getItemMeta();
    Cookie_META.setLore(Arrays.asList("§f1 §4Bronze"));
    Cookie.setItemMeta(Cookie_META);
    arrayList.add(Cookie);
    ItemStack Bread = new ItemStack(Material.GRILLED_PORK, 2);
    ItemMeta Bread_META = Bread.getItemMeta();
    Bread_META.setLore(Arrays.asList("§f6 §4Bronze"));
    Bread.setItemMeta(Bread_META);
    arrayList.add(Bread);
    ItemStack Beef = new ItemStack(Material.COOKED_BEEF, 4);
    ItemMeta Beef_META = Beef.getItemMeta();
    Beef_META.setLore(Arrays.asList("§f12 §4Bronze"));
    Beef.setItemMeta(Beef_META);
    arrayList.add(Beef);
    ItemStack Gold_Apple = new ItemStack(Material.GOLDEN_APPLE, 1);
    ItemMeta Gold_Apple_META = Gold_Apple.getItemMeta();
    Gold_Apple_META.setLore(Arrays.asList("§f6 §7Iron"));
    Gold_Apple.setItemMeta(Gold_Apple_META);
    arrayList.add(Gold_Apple);
    return arrayList;
  }

  public static ArrayList<ItemStack> getPickaxeItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack Pickaxe1 = new ItemStack(Material.IRON_PICKAXE, 1);
    ItemMeta Pickaxe1_META = Pickaxe1.getItemMeta();
    Pickaxe1_META.setLore(Arrays.asList("§f4 §4Bronze"));
    Pickaxe1.setItemMeta(Pickaxe1_META);
    Pickaxe1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
    arrayList.add(Pickaxe1);
    ItemStack Pickaxe2 = new ItemStack(Material.IRON_PICKAXE, 1);
    ItemMeta Pickaxe2_META = Pickaxe2.getItemMeta();
    Pickaxe2_META.setLore(Arrays.asList("§f2 §7Iron"));
    Pickaxe2.setItemMeta(Pickaxe2_META);
    Pickaxe2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);
    arrayList.add(Pickaxe2);
    ItemStack Pickaxe3 = new ItemStack(Material.IRON_PICKAXE, 1);
    ItemMeta Pickaxe3_META = Pickaxe3.getItemMeta();
    Pickaxe3_META.setLore(Arrays.asList("§f1 §6Gold"));
    Pickaxe3.setItemMeta(Pickaxe3_META);
    Pickaxe3.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
    arrayList.add(Pickaxe3);
    ItemStack Pickaxe4 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
    ItemMeta Pickaxe4_META = Pickaxe4.getItemMeta();
    Pickaxe4_META.setLore(Arrays.asList("§f8 §6Gold"));
    Pickaxe4.setItemMeta(Pickaxe4_META);
    Pickaxe4.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
    arrayList.add(Pickaxe4);
    ItemStack Pickaxe5 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
    ItemMeta Pickaxe5_META = Pickaxe5.getItemMeta();
    Pickaxe5_META.setLore(Arrays.asList("§f15 §6Gold"));
    Pickaxe5.setItemMeta(Pickaxe5_META);
    Pickaxe5.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
    Pickaxe5.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);
    arrayList.add(Pickaxe5);
    return arrayList;
  }

  public static ArrayList<ItemStack> getBlocksItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack Block2 = new ItemStack(Material.SANDSTONE, 2);
    ItemMeta Block2_META = Block2.getItemMeta();
    Block2_META.setLore(Arrays.asList("§f1 §4Bronze"));
    Block2.setItemMeta(Block2_META);
    arrayList.add(Block2);
    ItemStack Block3 = new ItemStack(Material.ENDER_STONE, 1);
    ItemMeta Block3_META = Block3.getItemMeta();
    Block3_META.setLore(Arrays.asList("§f7 §4Bronze"));
    Block3.setItemMeta(Block3_META);
    arrayList.add(Block3);
    ItemStack Block4 = new ItemStack(Material.IRON_BLOCK, 1);
    ItemMeta Block4_META = Block4.getItemMeta();
    Block4_META.setLore(Arrays.asList("§f3 §7Iron"));
    Block4.setItemMeta(Block4_META);
    arrayList.add(Block4);
    ItemStack Block5 = new ItemStack(Material.GLOWSTONE, 4);
    ItemMeta Block5_META = Block5.getItemMeta();
    Block5_META.setLore(Arrays.asList("§f15 §4Bronze"));
    Block5.setItemMeta(Block5_META);
    arrayList.add(Block5);
    ItemStack Block6 = new ItemStack(Material.GLASS, 1);
    ItemMeta Block6_META = Block6.getItemMeta();
    Block6_META.setLore(Arrays.asList("§f4 §4Bronze"));
    Block6.setItemMeta(Block6_META);
    arrayList.add(Block6);
    ItemStack Block7 = new ItemStack(Material.OBSIDIAN, 1);
    ItemMeta Block7_META = Block7.getItemMeta();
    Block7_META.setLore(Arrays.asList("§f128 §7Iron", "§f64 §6Gold"));
    Block7.setItemMeta(Block7_META);
    arrayList.add(Block7);
    return arrayList;
  }

  public static ArrayList<ItemStack> getUnitItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack Unity1 = new ItemStack(Material.LADDER, 12);
    ItemMeta Unity1_Meta = Unity1.getItemMeta();
    Unity1_Meta.setLore(Arrays.asList("§f1 §7Iron"));
    Unity1.setItemMeta(Unity1_Meta);
    arrayList.add(Unity1);
    ItemStack Unity2 = new ItemStack(Material.TNT, 1);
    ItemMeta Unity2_Meta = Unity2.getItemMeta();
    Unity2_Meta.setLore(Arrays.asList("§f3 §7Iron"));
    Unity2.setItemMeta(Unity2_Meta);
    arrayList.add(Unity2);
    ItemStack Unity3 = new ItemStack(Material.FLINT_AND_STEEL, 1);
    ItemMeta Unity3_Meta = Unity3.getItemMeta();
    Unity3_Meta.setLore(Arrays.asList("§f1 §6Gold"));
    Unity3.setItemMeta(Unity3_Meta);
    arrayList.add(Unity3);
    ItemStack Unity4 = new ItemStack(Material.CHEST, 1);
    ItemMeta Unity4_Meta = Unity4.getItemMeta();
    Unity4_Meta.setLore(Arrays.asList("§f1 §7Iron"));
    Unity4.setItemMeta(Unity4_Meta);
    arrayList.add(Unity4);
    ItemStack Unity5 = new ItemStack(Material.ENDER_CHEST, 1);
    ItemMeta Unity5_Meta = Unity5.getItemMeta();
    Unity5_Meta.setLore(Arrays.asList("§f1 §6Gold"));
    Unity5.setItemMeta(Unity5_Meta);
    arrayList.add(Unity5);
    ItemStack Unity6 = new ItemStack(Material.BLAZE_ROD, 1);
    ItemMeta Unity6_Meta = Unity6.getItemMeta();
    Unity6_Meta.setDisplayName("§aพื้นเวทมนต์");
    Unity6_Meta.setLore(Arrays.asList("§bเเผ่นกระจกช่วยชีวิต", "§bคลิกขวาเพื่อใช้งาน เมื่อตกจากเกาะ", "§f15 §7Iron"));
    Unity6.setItemMeta(Unity6_Meta);
    arrayList.add(Unity6);
    ItemStack Unity7 = new ItemStack(Material.STRING, 1);
    ItemMeta Unity7_Meta = Unity7.getItemMeta();
    Unity7_Meta.setDisplayName("§7กับดักพื้น");
    Unity7_Meta.setLore(Arrays.asList("§bใช้วางกับดักเพื่อทำให้ศัตรูติดสถานะเดินเชืองช้า", "§f3 §7Iron"));
    Unity7.setItemMeta(Unity7_Meta);
    arrayList.add(Unity7);
    ItemStack Unity8 = new ItemStack(Material.BRICK, 1);
    ItemMeta Unity8_Meta = Unity8.getItemMeta();
    Unity8_Meta.setDisplayName("§aกำเเพงเวทมนต์");
    Unity8_Meta.setLore(Arrays.asList("§bคลิกขวาเพื่อสร้างกำเเพงขนาด 5x3 ในเวลาทันที", "§f64 §4Bronze"));
    Unity8.setItemMeta(Unity8_Meta);
    arrayList.add(Unity8);
    ItemStack Unity9 = new ItemStack(Material.SULPHUR, 1);
    ItemMeta Unity9_Meta = Unity9.getItemMeta();
    Unity9_Meta.setDisplayName("§eผงวาร์ป");
    Unity9_Meta.setLore(Arrays.asList("§bคลิกขวาเพื่อ Teleport กลับจุดเกิด", "§f7 §7Iron"));
    Unity9.setItemMeta(Unity9_Meta);
    arrayList.add(Unity9);
    ItemStack Unity10 = new ItemStack(Material.MONSTER_EGG, 1);
    ItemMeta Unity10_Meta = Unity10.getItemMeta();
    Unity10_Meta.setDisplayName("§cเเกะระเบิด");
    Unity10_Meta.setLore(Arrays.asList("§bคลิกขวาเพื่อปล่อยเเกะระเบิด", "§f10 §6Gold"));
    Unity10.setItemMeta(Unity10_Meta);
    arrayList.add(Unity10);
    ItemStack Unity11 = new ItemStack(Material.COMPASS, 1);
    ItemMeta Unity11_Meta = Unity11.getItemMeta();
    Unity11_Meta.setLore(Arrays.asList("§f5 §7Iron"));
    Unity11.setItemMeta(Unity11_Meta);
    arrayList.add(Unity11);
    return arrayList;
  }

  public static ArrayList<ItemStack> getExchangeItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack EX1 = new ItemStack(Material.IRON_INGOT, 1);
    ItemMeta EX1_Meta = EX1.getItemMeta();
    EX1_Meta.setDisplayName("§7Iron");
    EX1_Meta.setLore(Arrays.asList("§f64 §4Bronze"));
    EX1.setItemMeta(EX1_Meta);
    arrayList.add(EX1);
    ItemStack EX2 = new ItemStack(Material.GOLD_INGOT, 1);
    ItemMeta EX2_Meta = EX2.getItemMeta();
    EX2_Meta.setDisplayName("§6Gold");
    EX2_Meta.setLore(Arrays.asList("§f64 §4Bronze", "§f64 §7Iron"));
    EX2.setItemMeta(EX2_Meta);
    arrayList.add(EX2);
    return arrayList;
  }

  public static ArrayList<ItemStack> getPotionItems() {
    ArrayList<ItemStack> arrayList = new ArrayList();
    ItemStack imt2 = new ItemStack(Material.POTION, 1, (short)8197);
    ItemMeta imt2_Meta = imt2.getItemMeta();
    imt2_Meta.setLore(Arrays.asList("§f3 §7Iron"));
    imt2.setItemMeta(imt2_Meta);
    arrayList.add(imt2);
    ItemStack imt3 = new ItemStack(Material.POTION, 1, (short)8229);
    ItemMeta imt3_Meta = imt3.getItemMeta();
    imt3_Meta.setLore(Arrays.asList("§f5 §7Iron"));
    imt3.setItemMeta(imt3_Meta);
    arrayList.add(imt3);
    ItemStack im = new ItemStack(Material.POTION, 1, (short)8194);
    ItemMeta im_Meta = im.getItemMeta();
    im_Meta.setLore(Arrays.asList("§f7 §7Iron"));
    im.setItemMeta(im_Meta);
    arrayList.add(im);
    ItemStack im2 = new ItemStack(Material.POTION, 1, (short)8201);
    ItemMeta im2_Meta = imt2.getItemMeta();
    im2_Meta.setLore(Arrays.asList("§f1 §6Gold"));
    im2.setItemMeta(im2_Meta);
    arrayList.add(im2);
    ItemStack im4 = new ItemStack(Material.POTION, 1, (short)8193);
    ItemMeta im4_Meta = im4.getItemMeta();
    im4_Meta.setLore(Arrays.asList("§f3 §6Gold"));
    im4.setItemMeta(im4_Meta);
    arrayList.add(im4);
    ItemStack im42 = new ItemStack(Material.POTION, 1, (short)8206);
    ItemMeta im42_Meta = im42.getItemMeta();
    im42_Meta.setLore(Arrays.asList("§f32 §6Gold"));
    im42.setItemMeta(im42_Meta);
    arrayList.add(im42);
    ItemStack im3 = new ItemStack(Material.POTION, 1, (short)16420);
    ItemMeta im3_Meta = imt3.getItemMeta();
    im3_Meta.setLore(Arrays.asList("§f8 §6Gold"));
    im3.setItemMeta(im3_Meta);
    arrayList.add(im3);
    ItemStack imx = new ItemStack(Material.POTION, 1, (short)16428);
    ItemMeta imx_Meta = imx.getItemMeta();
    imx_Meta.setLore(Arrays.asList("§f8 §6Gold"));
    imx.setItemMeta(imx_Meta);
    arrayList.add(imx);
    ItemStack im5 = new ItemStack(Material.POTION, 1, (short)16458);
    ItemMeta im5_Meta = im5.getItemMeta();
    im5_Meta.setLore(Arrays.asList("§f4 §6Gold"));
    im5.setItemMeta(im5_Meta);
    arrayList.add(im5);
    ItemStack im6 = new ItemStack(Material.POTION, 1, (short)16449);
    ItemMeta im6_Meta = im6.getItemMeta();
    im6_Meta.setLore(Arrays.asList("§f10 §6Gold"));
    im6.setItemMeta(im6_Meta);
    arrayList.add(im6);
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

    if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE && p.getInventory().getChestplate().getItemMeta().getDisplayName().contains("§fเกราะเเห่งสัจธรรม §6§lEV§e§lOL§f§lU§e§lTI§6§lON")) {
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
}
