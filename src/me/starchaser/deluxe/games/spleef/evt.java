//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.spleef;

import java.util.Iterator;
import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class evt implements Listener {
  public DeluxePlayer Last_Survive;

  public evt() {
  }

  @EventHandler
  public void BlockSpawn(EntitySpawnEvent e) {
    if (!(e.getEntity() instanceof Player) && (game.current_game == MINIGAMES.SPLEEF && game.gameState == GameState.Live || game.gameState == GameState.Loading)) {
      e.setCancelled(true);
    }
  }

  public BukkitRunnable PlayerTaskCheck() {
    return new BukkitRunnable() {
      public void run() {
        if (game.current_game != MINIGAMES.SPLEEF && game.gameState != GameState.Live) {
          this.cancel();
        }

        int Player_Survive = 0;
        Iterator var2 = Bukkit.getOnlinePlayers().iterator();

        while(var2.hasNext()) {
          Player p = (Player)var2.next();
          DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(p);
          Material m = p.getLocation().getBlock().getType();
          if (game.current_game.equals(MINIGAMES.SPLEEF) && dp.getPlayerState().equals(PlayerState.Live) && m == Material.STATIONARY_WATER || m == Material.WATER) {
            p.sendMessage("§7Game: §cไม่สามารถลงน้ำได้! เนื่องจากน้ำเป็นน้ำกรด!");
            if (p.getHealth() - 6.0D < 1.0D) {
              game.setOUT(p);
            } else {
              p.damage(6.0D);
            }
          }

          if (dp.getPlayerState() == PlayerState.Live) {
            ++Player_Survive;
            game.getPlayerinPlayerXPDB(dp.getName()).addKey("ความยาวนานในการรอดชีวิต");
            evt.this.Last_Survive = dp;
          }
        }

        if (Player_Survive < 2) {
          me.starchaser.deluxe.games.spleef.game.gameEnd(evt.this.Last_Survive);
          this.cancel();
        }
      }
    };
  }

  @EventHandler
  public void SnowBallEvent(ProjectileHitEvent event) {
    if (event.getEntity() instanceof Snowball && game.gameState == GameState.Live && game.current_game == MINIGAMES.SPLEEF) {
      Entity snowball = event.getEntity();
      Location loc = snowball.getLocation();
      Vector vec = snowball.getVelocity();
      Location loc2 = new Location(loc.getWorld(), loc.getX() + vec.getX(), loc.getY() + vec.getY(), loc.getZ() + vec.getZ());
      if (loc2.getBlock().getType().getId() == 7) {
        return;
      }

      loc2.getBlock().setType(Material.AIR);
      loc2.getWorld().playEffect(loc2, Effect.MOBSPAWNER_FLAMES, 1);
      loc2.getWorld().playSound(loc2, Sound.ITEM_PICKUP, 1.0F, 3.0F);
    }

  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent e) {
    if (game.gameState == GameState.Live && game.current_game == MINIGAMES.SPLEEF && DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayerState() == PlayerState.Live && e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType().getId() != 7) {
      me.starchaser.deluxe.games.spleef.game.hunger_spleef.put(e.getPlayer().getName(), 0);
      e.getClickedBlock().setType(Material.AIR);
      e.getPlayer().getInventory().addItem(new ItemStack[]{new ItemStack(Material.SNOW_BALL)});
      game.getPlayerinPlayerXPDB(e.getPlayer().getName()).addKey("ทุบบล๊อค");
      e.getPlayer().getWorld().playSound(e.getClickedBlock().getLocation(), Sound.ITEM_PICKUP, 1.0F, 3.0F);
    }

  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    if (game.gameState == GameState.Live && game.current_game == MINIGAMES.SPLEEF && DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayerState() == PlayerState.Live && e.getPlayer().getLocation().getY() < 1.0D) {
      DeluxePlayer.getDeluxePlayer(e.getPlayer()).setPlayerState(PlayerState.Out);
      starchaser.BoardCastMsg("§7OUT: §c" + e.getPlayer().getName());
      e.getPlayer().setGameMode(GameMode.SPECTATOR);
      e.getPlayer().teleport((Location)game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
    }

    if (game.current_game == MINIGAMES.SPLEEF && game.gameState == GameState.End && e.getPlayer().getLocation().getY() < 1.0D) {
      Player player = e.getPlayer();
      e.setCancelled(true);
      player.setHealth(20.0D);
      player.getInventory().clear();
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(player);
      player.teleport((Location)game.game_team.getTeam(dp.getPlayer_team().toString()).getTeamSpawnLocation().get(0));
    }

  }

  @EventHandler
  public void PVPOFF(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player && e.getEntity() instanceof Player && game.current_game == MINIGAMES.SPLEEF && game.gameState == GameState.Live || game.gameState == GameState.Loading) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onPlayerDeath(EntityDamageEvent e) {
    Player player;
    DeluxePlayer dp;
    if (game.current_game == MINIGAMES.SPLEEF && e.getEntity() instanceof Player && game.gameState == GameState.Live || game.gameState == GameState.Loading) {
      try {
        player = (Player)e.getEntity();
      } catch (ClassCastException var4) {
        return;
      }

      if (player.getHealth() - e.getDamage() < 1.0D) {
        e.setCancelled(true);
        player.setHealth(20.0D);
        player.getInventory().clear();
        dp = DeluxePlayer.getDeluxePlayer(player);
        DeluxePlayer.getDeluxePlayer(player).setPlayerState(PlayerState.Out);
        starchaser.BoardCastMsg("§7OUT: §c" + player.getName());
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport((Location)game.game_team.getTeam(dp.getPlayer_team().toString()).getTeamSpawnLocation().get(0));
      }
    }

    if (e.getEntity() instanceof Player && game.current_game == MINIGAMES.SPLEEF && game.gameState == GameState.End) {
      player = (Player)e.getEntity();
      if (player.getHealth() - e.getDamage() < 1.0D) {
        e.setCancelled(true);
        player.setHealth(20.0D);
        player.getInventory().clear();
        dp = DeluxePlayer.getDeluxePlayer(player);
        player.teleport((Location)game.game_team.getTeam(dp.getPlayer_team().toString()).getTeamSpawnLocation().get(0));
      }
    }

  }
}
