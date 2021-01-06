//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.tnttag;

import java.util.ArrayList;
import java.util.Iterator;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import me.starchaser.deluxe.games.tnttag.game.item_tnttag;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class evt implements Listener {
  public evt() {
  }

  @EventHandler
  public void TAG(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player && e.getEntity() instanceof Player && game.current_game == MINIGAMES.TNTTAG && game.gameState == GameState.Live) {
      Player dam = (Player)e.getDamager();
      Player vit = (Player)e.getEntity();
      vit.setVelocity(dam.getLocation().getDirection().multiply(2));
      vit.getLocation().getWorld().playSound(vit.getLocation(), Sound.DIG_GRAVEL, 1.0F, 3.0F);
      if (DeluxePlayer.getDeluxePlayer(dam) != me.starchaser.deluxe.games.tnttag.game.bomber) {
        return;
      }

      starchaser.BoardCastMsg("§7Game: §e" + dam.getName() + " §aได้โยนระเบิดไปให้ §e" + vit.getName());
      game.getPlayerinPlayerXPDB(dam.getName()).addKey("โยนระเบิด");
      me.starchaser.deluxe.games.tnttag.game.set_bomber(vit);
    }

  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, 0), false);
    if (game.gameState == GameState.Live && game.current_game == MINIGAMES.TNTTAG && DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayerState() == PlayerState.Live && e.getPlayer().getLocation().getY() < 1.0D) {
      DeluxePlayer.getDeluxePlayer(e.getPlayer()).setPlayerState(PlayerState.Out);
      starchaser.BoardCastMsg("§7OUT: §c" + e.getPlayer().getName());
      e.getPlayer().setGameMode(GameMode.SPECTATOR);
      e.getPlayer().teleport((Location)game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
    }

    if (game.current_game == MINIGAMES.TNTTAG && game.gameState == GameState.End && e.getPlayer().getLocation().getY() < 1.0D) {
      Player player = e.getPlayer();
      e.setCancelled(true);
      player.setHealth(20.0D);
      player.getInventory().clear();
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(player);
      player.teleport((Location)game.game_team.getTeam(dp.getPlayer_team().toString()).getTeamSpawnLocation().get(0));
    }

  }

  @EventHandler
  public void cancel_damage_event(EntityDamageEvent e) {
    if (game.current_game.equals(MINIGAMES.TNTTAG)) {
      e.setCancelled(true);
    }

    if (e.getEntity() instanceof Player && DeluxePlayer.getDeluxePlayer((Player)e.getEntity()).getPlayerState() == PlayerState.Out) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onPickUp(PlayerPickupItemEvent e) {
    if (e.getItem() != null && e.getItem().getItemStack() != null && e.getItem().getItemStack().getItemMeta() != null && e.getItem().getItemStack().getItemMeta().getDisplayName() != null) {
      ArrayList<item_tnttag> arrayList_to_remove = new ArrayList();
      Iterator var3 = me.starchaser.deluxe.games.tnttag.game.arr_item.iterator();

      while(var3.hasNext()) {
        item_tnttag item = (item_tnttag)var3.next();
        if (item.getStr().equalsIgnoreCase(e.getItem().getName())) {
          item.hide();
          e.setCancelled(true);
          e.getItem().setItemStack(new ItemStack(Material.AIR));
          arrayList_to_remove.add(item);
          if (item.getMat().equals(Material.POTION)) {
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3));
            starchaser.BoardCastMsg("§7Item: §f" + e.getPlayer() + " §aได้รับ speed เป็นเวลา 10 วินาที");
          }
        }
      }

      me.starchaser.deluxe.games.tnttag.game.arr_item.remove(arrayList_to_remove);
    }
  }
}
