//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe.games.tntrun;

import me.starchaser.deluxe.DeluxePlayer;
import me.starchaser.deluxe.starchaser;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.game.GameState;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.games.game.PlayerState;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class evt implements Listener {
  public evt() {
  }

  @EventHandler
  public void cancel_damage_event(EntityDamageEvent e) {
    if (game.current_game.equals(MINIGAMES.TNTRUN)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void BlockSpawn(EntitySpawnEvent e) {
    if (!(e.getEntity() instanceof Player) && (game.current_game == MINIGAMES.TNTRUN && game.gameState == GameState.Live || game.gameState == GameState.Loading)) {
      e.setCancelled(true);
    }

  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    if (game.gameState == GameState.Live && game.current_game == MINIGAMES.TNTRUN && DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayerState() == PlayerState.Live && e.getPlayer().getLocation().getY() < 1.0D) {
      DeluxePlayer.getDeluxePlayer(e.getPlayer()).setPlayerState(PlayerState.Out);
      starchaser.BoardCastMsg("§7OUT: §c" + e.getPlayer().getName());
      e.getPlayer().setGameMode(GameMode.SPECTATOR);
      e.getPlayer().teleport((Location)game.game_team.getTeam(DeluxePlayer.getDeluxePlayer(e.getPlayer()).getPlayer_team().toString()).getTeamSpawnLocation().get(0));
    }

    if (game.current_game == MINIGAMES.TNTRUN && game.gameState == GameState.End && e.getPlayer().getLocation().getY() < 1.0D) {
      Player player = e.getPlayer();
      e.setCancelled(true);
      player.setHealth(20.0D);
      player.getInventory().clear();
      DeluxePlayer dp = DeluxePlayer.getDeluxePlayer(player);
      player.teleport((Location)game.game_team.getTeam(dp.getPlayer_team().toString()).getTeamSpawnLocation().get(0));
    }

  }
}
