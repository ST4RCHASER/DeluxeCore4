//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.bedwars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.core;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.DeluxeMap.BedTeamState;
import me.starchaser.deluxe.games.DeluxeMap.TEAM_COLOR;
import me.starchaser.deluxe.games.bedwars.game.SHOP_TYPE;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import me.starchaser.deluxe.starchaser.ServerGamemode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class evt implements Listener {
  public static ArrayList<Location> BlockPlaceList;
  public static HashMap<TEAM_COLOR, Inventory> team_inv;
  public static HashMap<Player, Integer> item_spawn_floor_cooldown;

  public evt() {
  }

  @EventHandler
  public void BlockPlace(final BlockPlaceEvent e) {
    if (game.current_game == MINIGAMES.BEDWARS && game.gameState == GameState.Live) {
      game.getPlayerinPlayerXPDB(e.getPlayer().getName()).addKey("วางบล๊อค");
      BlockPlaceList.add(e.getBlock().getLocation());
    }

    if (e.getBlock().getType().equals(Material.WOOL)) {
      (new BukkitRunnable() {
        public void run() {
          if (e.getBlock().getType().equals(Material.WOOL)) {
            e.getBlock().setType(Material.SANDSTONE);
          }

          this.cancel();
        }
      }).runTaskTimer(core.getDeluxe, 200L, 200L);
    }

  }

  @EventHandler
  public void onBlockDamage(EntityExplodeEvent event) {
    if (game.current_game == MINIGAMES.BEDWARS && game.gameState == GameState.Live) {
      ArrayList<Block> blocks_to_not_remove = new ArrayList();
      Iterator var3 = event.blockList().iterator();

      while(var3.hasNext()) {
        Block b = (Block)var3.next();
        if (!BlockPlaceList.contains(b.getLocation())) {
          blocks_to_not_remove.add(b);
        }
      }

      event.blockList().removeAll(blocks_to_not_remove);
    }

  }

  @EventHandler
  public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
    Entity entity = event.getRightClicked();
    if (entity instanceof NPC && game.current_game == MINIGAMES.BEDWARS) {
      me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(event.getPlayer()), event.getPlayer(), SHOP_TYPE.HOME);
    }
  }

  @EventHandler
  public void ItemUse(final PlayerInteractEvent event) {
    if (game.gameState == GameState.Live && game.current_game == MINIGAMES.BEDWARS) {
      if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) && event.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§fพื้นสวรรค์ §c§o(ช่วยเหลือ)")) {
        if (item_spawn_floor_cooldown.get(event.getPlayer()) == null) {
          if (shop.spawnfloor(event.getPlayer())) {
            event.getPlayer().sendMessage("§7Item: §aคุณได้ใช้ไอเทม §rพื้นสวรรค์");
            ItemStack Unity9 = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta Unity9_Meta = Unity9.getItemMeta();
            Unity9_Meta.setDisplayName("§fพื้นสวรรค์ §c§o(ช่วยเหลือ)");
            Unity9_Meta.setLore(Arrays.asList("§r", "§7§oเมื่อคุณตกจากที่สูงทำการคลิ๊กขวาที่ไอเทมนี้", "§7§oไอเทมนี้จะทำการสร้างพื้นที่รองรับใว้ในระยะเวลาสั้นๆ", "§7§oเเละจะหายไปในเวลา 10 วินาที"));
            Unity9.setItemMeta(Unity9_Meta);
            event.getPlayer().getInventory().removeItem(new ItemStack[]{Unity9});
            item_spawn_floor_cooldown.put(event.getPlayer(), 10);
            (new BukkitRunnable() {
              public void run() {
                int new_time = (Integer)evt.item_spawn_floor_cooldown.get(event.getPlayer()) - 1;
                evt.item_spawn_floor_cooldown.replace(event.getPlayer(), new_time);
                if (new_time < 1) {
                  evt.item_spawn_floor_cooldown.remove(event.getPlayer());
                  this.cancel();
                }

              }
            }).runTaskTimerAsynchronously(core.getDeluxe, 20L, 20L);
          } else {
            event.getPlayer().sendMessage("§7Item: §cไม่สามารถใช้ไอเทม §r" + event.getPlayer().getItemInHand().getItemMeta().getDisplayName() + "§c ในพื้นที่นี้ได้");
          }
        } else {
          event.getPlayer().sendMessage("§7Item: §cไม่สามารถใช้ไอเทม §r" + event.getPlayer().getItemInHand().getItemMeta().getDisplayName() + "§c ได้ในขณะนี้ โปรดรออีกสัก §r" + item_spawn_floor_cooldown.get(event.getPlayer()) + "§c จึงจะสามารถใช้งานได้อีกครั้ง");
        }
      }
    }
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent e) {
    if (game.gameState == GameState.Live && game.current_game == MINIGAMES.BEDWARS && DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayerState() == PlayerState.Live && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
      DeluxePlayer deluxePlayer = DeluxePlayer.getDeluxePlayer(e.getPlayer());
      if (team_inv.get(deluxePlayer.getPlayer_team()) == null) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "กล่องของทีม " + game.game_map.getTeams().getTeam(deluxePlayer.getPlayer_team().toString()).getColorCode() + deluxePlayer.getPlayer_team().toString());
        team_inv.put(deluxePlayer.getPlayer_team(), inv);
      }

      e.setCancelled(true);
      e.getPlayer().openInventory((Inventory)team_inv.get(deluxePlayer.getPlayer_team()));
    }

  }

  @EventHandler
  public void onPlayerClickInv(InventoryClickEvent e) {
    if (starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES && game.gameState == GameState.Live && game.current_game == MINIGAMES.BEDWARS) {
      int price_bronze = 0;
      int price_iron = 0;
      int price_gold = 0;
      Player p = (Player)e.getWhoClicked();
      if (e.getClickedInventory() != null && e.getClickedInventory().getName().contains("ร้านค้า")) {
        e.setCancelled(true);
        if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bชุดเกราะ")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.ARMOR);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bอาวุธระยะประชิด")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.MELEE);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bอาวุธระยะไกล")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.RANGE);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bอาหาร")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.FOOD);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bที่ขุด")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.PICKAXE);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bบล๊อค")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.BLOCKS);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bไอเทม")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.ITEMS);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bแลกเปลี่ยนทรัพยากร")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.EXCHANGE);
            return;
          }

          if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§bยา")) {
            me.starchaser.deluxe.games.bedwars.game.openTrading(DeluxePlayer.getDeluxePlayer(p), p, SHOP_TYPE.POTION);
            return;
          }
        }

        if (e.getCurrentItem().getItemMeta().getLore().size() > 0) {
          Iterator var6 = e.getCurrentItem().getItemMeta().getLore().iterator();

          while(var6.hasNext()) {
            String lores = (String)var6.next();
            if (lores.endsWith("Bronze")) {
              price_bronze = Integer.parseInt(lores.replaceAll("§4Bronze", "").replaceAll("§f", "").replaceAll(" ", ""));
            }

            if (lores.endsWith("Iron")) {
              price_iron = Integer.parseInt(lores.replaceAll("§7Iron", "").replaceAll("§f", "").replaceAll(" ", ""));
            }

            if (lores.endsWith("Gold")) {
              price_gold = Integer.parseInt(lores.replaceAll("§6Gold", "").replaceAll("§f", "").replaceAll(" ", ""));
            }
          }
        }

        ItemStack[] inv_item_list = e.getWhoClicked().getInventory().getContents();
        int player_current_bronze = 0;
        int player_current_gold = 0;
        int player_current_iron = 0;
        ItemStack[] var10 = inv_item_list;
        int var11 = inv_item_list.length;
        int var12 = 0;

        while(true) {
          if (var12 >= var11) {
            if (price_bronze == 0 && price_iron == 0 && price_gold == 0) {
              return;
            }

            if (price_bronze > player_current_bronze || price_iron > player_current_iron || price_gold > player_current_gold) {
              e.getWhoClicked().sendMessage("§7Shop: §cทรัพยากรของคุณไม่เพียงพอที่จะแลกเปลี่ยน โปรดตรวจสอบ");
              return;
            }

            ItemStack bronze_model = new ItemStack(Material.CLAY_BRICK, price_bronze);
            ItemMeta bronze_meta = bronze_model.getItemMeta();
            bronze_meta.setDisplayName("§4Bronze");
            bronze_meta.setLore(Arrays.asList("§bไว้ใช้สำหรับซื้อของที่ NPC"));
            bronze_model.setItemMeta(bronze_meta);
            ItemStack iron_model = new ItemStack(Material.IRON_INGOT, price_iron);
            ItemMeta iron_meta = iron_model.getItemMeta();
            iron_meta.setDisplayName("§7Iron");
            iron_meta.setLore(Arrays.asList("§bไว้ใช้สำหรับซื้อของที่ NPC"));
            iron_model.setItemMeta(iron_meta);
            ItemStack gold_model = new ItemStack(Material.GOLD_INGOT, price_gold);
            ItemMeta gold_meta = gold_model.getItemMeta();
            gold_meta.setDisplayName("§6Gold");
            gold_meta.setLore(Arrays.asList("§bไว้ใช้สำหรับซื้อของที่ NPC"));
            gold_model.setItemMeta(gold_meta);
            e.getWhoClicked().getInventory().removeItem(new ItemStack[]{bronze_model});
            e.getWhoClicked().getInventory().removeItem(new ItemStack[]{iron_model});
            e.getWhoClicked().getInventory().removeItem(new ItemStack[]{gold_model});
            p.updateInventory();
            ArrayList<String> new_lores = new ArrayList();
            Iterator var17 = e.getCurrentItem().getItemMeta().getLore().iterator();

            while(var17.hasNext()) {
              String str = (String)var17.next();
              if (!str.endsWith("Bronze") && !str.endsWith("Iron") && !str.endsWith("Gold") && !str.contains("Price") && !str.contains("Prices") && !str.contains("ราคา")) {
                new_lores.add(str);
              }
            }

            ItemStack itemStack = e.getCurrentItem().clone();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(new_lores);
            itemStack.setItemMeta(itemMeta);
            if (e.getWhoClicked().getInventory().firstEmpty() == -1) {
              e.getWhoClicked().getWorld().dropItem(e.getWhoClicked().getLocation(), itemStack);
            } else {
              e.getWhoClicked().getInventory().addItem(new ItemStack[]{itemStack});
              p.updateInventory();
            }
            break;
          }

          ItemStack im = var10[var12];
          if (im != null) {
            if (im.getType().equals(Material.CLAY_BRICK)) {
              player_current_bronze += im.getAmount();
            }

            if (im.getType().equals(Material.GOLD_INGOT)) {
              player_current_gold += im.getAmount();
            }

            if (im.getType().equals(Material.IRON_INGOT)) {
              player_current_iron += im.getAmount();
            }
          }

          ++var12;
        }
      }
    }

  }

  @EventHandler
  public void BlockBreak(BlockBreakEvent e) {
    if (game.current_game == MINIGAMES.BEDWARS) {
      if (game.gameState != GameState.Live && game.gameState != GameState.End) {
        game.getPlayerinPlayerXPDB(e.getPlayer().getName()).addKey("ทุบบล๊อค");
      } else {
        Player player = e.getPlayer();
        DeluxePlayer deluxePlayer = DeluxePlayer.getDeluxePlayer(player);
        if (e.getBlock().getType().equals(Material.BED_BLOCK) || e.getBlock().getType().equals(Material.BED)) {
          Iterator var4 = game.game_map_settings.getConfigSelection("TEAM_LIST").getKeys(false).iterator();

          label50:
          while(true) {
            String team_str_color;
            Location bw_head;
            Location bw_block;
            do {
              if (!var4.hasNext()) {
                break label50;
              }

              team_str_color = (String)var4.next();
              bw_head = new Location(Bukkit.getWorld(game.game_map.getMap_id()), (double)game.game_map_settings.getInt("TEAM_LIST." + team_str_color + ".BED.x"), (double)game.game_map_settings.getInt("TEAM_LIST." + team_str_color + ".BED.y"), (double)game.game_map_settings.getInt("TEAM_LIST." + team_str_color + ".BED.z"));
              bw_block = new Location(Bukkit.getWorld(game.game_map.getMap_id()), (double)game.game_map_settings.getInt("TEAM_LIST." + team_str_color + ".BED.x2"), (double)game.game_map_settings.getInt("TEAM_LIST." + team_str_color + ".BED.y2"), (double)game.game_map_settings.getInt("TEAM_LIST." + team_str_color + ".BED.z2"));
            } while((e.getBlock().getLocation().getX() != bw_head.getX() || e.getBlock().getLocation().getY() != bw_head.getY() || e.getBlock().getLocation().getZ() != bw_head.getZ()) && (e.getBlock().getLocation().getX() != bw_block.getX() || e.getBlock().getLocation().getY() != bw_block.getY() || e.getBlock().getLocation().getZ() != bw_block.getZ()));

            if (deluxePlayer.getPlayer_team().toString().equalsIgnoreCase(team_str_color)) {
              e.setCancelled(true);
            } else {
              starchaser.BoardCastMsg("§7Game: §e" + e.getPlayer().getName() + " §aได้ทุบเตียงของทีม " + game.game_team.getTeam(team_str_color).getColorCode() + team_str_color + " §aไปแล้ว ผู้เล่นทีม " + game.game_team.getTeam(team_str_color).getColorCode() + team_str_color + " §aจะไม่สามารถเกิดใหม่ได้อีก");
              bw_block.getBlock().getDrops().clear();
              bw_head.getBlock().getDrops().clear();
              e.getBlock().getDrops().clear();
              game.game_team.getTeam(team_str_color).setBW_BedState(BedTeamState.DISTORY);
              game.getPlayerinPlayerXPDB(e.getPlayer().getName()).addKey("ทำลายเตียง");
            }
          }
        }

        if (!BlockPlaceList.contains(e.getBlock().getLocation()) && e.getBlock().getType() != Material.BED_BLOCK && e.getBlock().getType() != Material.BED) {
          e.setCancelled(true);
        }
      }
    }

  }

  @EventHandler
  public void onPickup(PlayerPickupItemEvent e) {
    if (e.getItem().getItemStack().getType().equals(Material.BED) || e.getItem().getItemStack().getType().equals(Material.BED_BLOCK)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onPVP(EntityDamageByEntityEvent e) {
    if (game.current_game == MINIGAMES.BEDWARS && (game.gameState == GameState.Live || game.gameState == GameState.End)) {
      if (e.getEntity() instanceof NPC) {
        e.setCancelled(true);
      }

      if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
        if (DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).getPlayer_team() == DeluxePlayer.getDeluxePlayer((Player)e.getDamager()).getPlayer_team()) {
          e.setCancelled(true);
          return;
        }

        game.getPlayerinPlayerXPDB(e.getDamager().getName()).addKey("สร้างความเสียหาย", (int)e.getDamage());
        if (((Player)e.getEntity()).getHealth() - e.getDamage() < 1.0D) {
          starchaser.BoardCastMsg("§7Death: §c" + e.getEntity().getName() + "§b ถูกฆ่าโดย §e" + e.getDamager().getName());
          game.getPlayerinPlayerXPDB(e.getDamager().getName()).addKey("สังหาร");
          game.ClearINV((Player)e.getEntity());
          ((Player)e.getEntity()).setHealth(20.0D);
          ((Player)e.getEntity()).setFoodLevel(20);
          e.setCancelled(true);
          if (game.game_team.getTeam(DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).getPlayer_team().toString()).getBW_BedState() == BedTeamState.LIVE) {
            me.starchaser.deluxe.games.bedwars.game.respawn((Player)e.getEntity());
          } else {
            ((Player)e.getEntity()).setGameMode(GameMode.SPECTATOR);
            DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).setPlayerState(PlayerState.Out);
            e.getEntity().teleport((Location)game.game_team.getTeam(DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
          }
        }
      }
    }

  }

  @EventHandler
  public void onCraft(CraftItemEvent e) {
    if (game.current_game == MINIGAMES.BEDWARS && (game.gameState == GameState.Live || game.gameState == GameState.End)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    if ((game.gameState == GameState.Live || game.gameState == GameState.End) && game.current_game == MINIGAMES.BEDWARS) {
      if (e.getEntity() instanceof NPC) {
        e.setCancelled(true);
      }

      if (e.getEntity() instanceof Player && me.starchaser.deluxe.games.bedwars.game.players_invincible.get(DeluxePlayer.getDeluxePlayer((Player)e.getEntity())) != null && (Integer)me.starchaser.deluxe.games.bedwars.game.players_invincible.get(DeluxePlayer.getDeluxePlayer((Player)e.getEntity())) > 0) {
        e.setCancelled(true);
        return;
      }

      if (e.getEntity().getType().equals(EntityType.VILLAGER)) {
        e.setCancelled(true);
      }

      if (e.getEntity() instanceof Player && e.getCause() != DamageCause.ENTITY_ATTACK && ((Player)e.getEntity()).getHealth() - e.getDamage() < 1.0D) {
        if (game.game_team.getTeam(DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).getPlayer_team().toString()).getBW_BedState() == BedTeamState.LIVE) {
          me.starchaser.deluxe.games.bedwars.game.respawn((Player)e.getEntity());
        } else {
          ((Player)e.getEntity()).setGameMode(GameMode.SPECTATOR);
          DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).setPlayerState(PlayerState.Out);
          e.getEntity().teleport((Location)game.game_team.getTeam(DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
        }

        starchaser.BoardCastMsg("§7Death: §c" + e.getEntity().getName() + "§b ตาย");
        game.ClearINV((Player)e.getEntity());
        ((Player)e.getEntity()).setFoodLevel(20);
        ((Player)e.getEntity()).setHealth(20.0D);
        e.setCancelled(true);
      }
    }

  }

  @EventHandler
  public void OnPlayerMove(PlayerMoveEvent e) {
    if ((game.gameState == GameState.Live || game.gameState == GameState.End) && e.getPlayer().getLocation().getY() < 1.0D && game.current_game == MINIGAMES.BEDWARS) {
      if (me.starchaser.deluxe.games.bedwars.game.respawn_time_players.get(DeluxePlayer.getDeluxePlayer(e.getPlayer())) != null && (Integer)me.starchaser.deluxe.games.bedwars.game.respawn_time_players.get(DeluxePlayer.getDeluxePlayer(e.getPlayer())) > 0) {
        e.getPlayer().teleport((Location)game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
        return;
      }

      if (game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team().toString()).getBW_BedState() == BedTeamState.LIVE) {
        me.starchaser.deluxe.games.bedwars.game.respawn(e.getPlayer());
      } else {
        e.getPlayer().setGameMode(GameMode.SPECTATOR);
        DeluxePlayer.getDeluxePlayer(e.getPlayer()).setPlayerState(PlayerState.Out);
        e.getPlayer().teleport((Location)game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
      }

      starchaser.BoardCastMsg("§7Death: §c" + e.getPlayer().getName() + "§b ตาย");
      game.ClearINV(e.getPlayer());
      e.getPlayer().setFallDistance(0.0F);
      e.getPlayer().setHealth(20.0D);
      e.getPlayer().setFoodLevel(20);
    }

  }
}
