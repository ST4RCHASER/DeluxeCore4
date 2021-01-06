//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.starchaser.deluxe.games.game;
import me.starchaser.deluxe.games.game.MINIGAMES;
import me.starchaser.deluxe.starchaser.LOG_TYPE;
import me.starchaser.deluxe.starchaser.ServerGamemode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class commands {
  public commands() {
  }

  public static void onBotCommand(final String str, final Player sender, final boolean ishide) {
    (new BukkitRunnable() {
      public void run() {
        String BotName = "§7KIKIBOT: §d";
        ArrayList<String> arr = new ArrayList();
        String[] list = str.split(" ", 10);
        arr.addAll(Arrays.asList(list));
        String command = (String)arr.get(0);
        ArrayList<String> args = arr;
        arr.remove(0);
        DeluxePlayer sender_dp = DeluxePlayer.getDeluxePlayer(sender);
        if (ishide) {
          starchaser.Logger(LOG_TYPE.COMMAND, "§f" + sender.getName() + " issued deluxecore command: !#" + str);
        } else {
          starchaser.Logger(LOG_TYPE.COMMAND, "§f" + sender.getName() + " issued deluxecore command: !" + str);
        }

        if (command.length() < 1) {
          sender.sendMessage(BotName + "วิธีการใช้: !<คำสั่ง> (อาร์กิวเมนต์)");
        } else if (command.equalsIgnoreCase("help")) {
          sender.sendMessage(BotName + "คำสั่งที่ใช้งานได้:");
          sender.sendMessage(BotName + "!help - แสดงหน้านี้");
        } else {
          String kick_msg;
          if (command.equalsIgnoreCase("core_info")) {
            sender.sendMessage("§bPlayers in memory database: §f(§d" + core.PlayerRef.size() + "§f)");
            boolean i = false;
            kick_msg = "";

            for(DeluxePlayer dp : core.PlayerRef) {
              if (!i) {
                kick_msg = kick_msg + "§d" + dp.getName() + "§a,§r";
                i = true;
              } else {
                kick_msg = kick_msg + "§f" + dp.getName() + "§a,§r";
                i = false;
              }
            }

            sender.sendMessage(kick_msg);
          } else if (command.equalsIgnoreCase("debug")) {
            if (arr.size() < 1) {
              sender.sendMessage(BotName + "Usage: !debug <name>");
            }

            if (((String)arr.get(0)).equalsIgnoreCase("holo")) {
              sender.sendMessage("§dHologram list§f:");
              Iterator var18 = HologramsAPI.getHolograms(core.getDeluxe).iterator();

              while(var18.hasNext()) {
                Hologram hologram = (Hologram)var18.next();
                sender.sendMessage(hologram.getLine(0).toString() + " LOC: " + hologram.getWorld() + " X:" + hologram.getX() + " Y:" + hologram.getY() + " Z:" + hologram.getZ());
              }
            }

            if (((String)arr.get(0)).equalsIgnoreCase("spleef")) {
              sender.sendMessage(BotName + "Start debug @Class " + game.gamecooldown().getClass().toString());
              BukkitRunnable gameb = game.gamecooldown();
              game.current_game = MINIGAMES.SPLEEF;
              gameb.runTaskTimerAsynchronously(core.getDeluxe, 2L, 2L);
            }

            if (((String)arr.get(0)).equalsIgnoreCase("bctest")) {
              sender.sendMessage(BotName + "Broadcast test");
              starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
              starchaser.BoardCastMsg("§f§l                 TNTRUN GAME");
              starchaser.BoardCastMsg("§r");
              starchaser.BoardCastMsg("            §8[§b✶§8] §6§lWinner is " + sender.getName() + " §8[§b✶§8]");
              starchaser.BoardCastMsg("§r");
              starchaser.BoardCastMsg("§b§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            }

          } else {
            if (command.equalsIgnoreCase("fly") && starchaser.ServerGamemodeMode == ServerGamemode.LOBBY && sender_dp.getPlayerClass().getId() > 3) {
              if (sender.isFlying()) {
                sender.sendMessage(BotName + "§7ได้ทำการเปิดโหมด บิน ให้กับ" + sender.getName() + " แล้ว");
                sender.setFlying(true);
              } else {
                sender.sendMessage(BotName + "§7ได้ทำการปิดโหมด บิน ให้กับ" + sender.getName() + " แล้ว");
                sender.setFlying(false);
              }
            }

            if (command.equalsIgnoreCase("mp") && starchaser.ServerGamemodeMode == ServerGamemode.MINIGAMES) {
              if (arr.size() < 1) {
                sender.sendMessage(BotName + "วิธีการใช้: !mp (อาร์กิวเมนต์)");
              } else {
                if (((String)arr.get(0)).equalsIgnoreCase("hosttake")) {
                  if (sender_dp.getPlayerClass().getId() < 9) {
                    sender.sendMessage(BotName + "§cคำสั่งนี้สำหรับผู้ดูและเท่านั้น ที่สามารถใช้คำสั่งนี้ได้!");
                    return;
                  }

                  game.give_host(sender_dp);
                }

                if (((String)arr.get(0)).equalsIgnoreCase("setgame")) {
                  if (sender_dp.getPlayerClass().getId() < 9) {
                    sender.sendMessage(BotName + "§cคำสั่งนี้สำหรับผู้ดูและเท่านั้น ที่สามารถใช้คำสั่งนี้ได้!");
                  } else {
                    MINIGAMES minigames = null;
                    MINIGAMES[] var13 = MINIGAMES.values();
                    int var15 = var13.length;

                    for(int var17 = 0; var17 < var15; ++var17) {
                      MINIGAMES mg = var13[var17];
                      if (mg.name().equalsIgnoreCase((String)args.get(1))) {
                        minigames = mg;
                      }
                    }

                    if (minigames == null) {
                      sender.sendMessage(BotName + "§cไม่พบเกม " + (String)args.get(1));
                    } else {
                      game.SetGame(minigames);
                      sender.sendMessage(BotName + "§aเกมถูกเปลี่ยนเป็น §b" + game.current_game.toString() + " §aเรียบร้อยแล้ว");
                    }
                  }
                } else if (((String)arr.get(0)).equalsIgnoreCase("settings")) {
                  if (sender_dp != game.host) {
                    sender.sendMessage(BotName + "§cเฉพาะหัวหน้าห้อง ที่สามารถใช้คำสั่งนี้ได้!");
                  } else {
                    game.openRoomSettings(sender);
                  }
                } else {
                  sender.sendMessage(BotName + "§cไม่พบคำสั่งย่อยนี้ในของ !mp");
                }
              }
            } else if (command.equalsIgnoreCase("faq") && sender_dp.getPlayerClass().getId() >= 6) {
              if (arr.size() < 1) {
                starchaser.BoardCastMsg(BotName + "วิธีการใช้: !faq (ชื่อ faq)");
              } else {
                if (((String)arr.get(0)).equals("eiyuu")) {
                  starchaser.BoardCastMsg(BotName + "Eiyuu แปลว่า \"วีรบุรุษ\" จ้า");
                }

                if (((String)arr.get(0)).equals("kami")) {
                  starchaser.BoardCastMsg(BotName + "Kami แปลว่า \"พระเจ้า\" จ้า");
                }

                if (((String)arr.get(0)).equals("baka")) {
                  starchaser.BoardCastMsg(BotName + "Kami แปลว่า \"ไอ้โง่\" จ้า");
                } else if (((String)arr.get(0)).equalsIgnoreCase("dot")) {
                  starchaser.BoardCastMsg(BotName + "เซิร์ฟแลคหรอจ๊ะคุณ member พิมพ์จุดอะ");
                } else if (((String)arr.get(0)).equalsIgnoreCase("ip")) {
                  starchaser.BoardCastMsg(BotName + "ไอพีของเซิร์ฟเวอร์ play.mc-deluxe.net อย่าลืมชวนเพื่อนมาเล่นกันเยอะๆนะ!");
                } else {
                  starchaser.BoardCastMsg(BotName + "ไม่พบ faq นี้!");
                }

              }
            } else {
              Player target;
              if (sender_dp.getPlayerClass().getId() >= 9) {
                if (command.equalsIgnoreCase("tp")) {
                  if (arr.size() < 1) {
                    sender.sendMessage(BotName + "วิธีการใช้: !tp <ชื่อผู้เล่น>");
                    return;
                  }

                  target = Bukkit.getPlayer((String)arr.get(0));
                  if (target == null) {
                    sender.sendMessage(BotName + "ไม่พบเกมโหมดนี้! (" + (String)arr.get(0) + ")");
                    return;
                  }

                  sender.teleport(target.getLocation());
                  sender.sendMessage(BotName + "ได้เทเลพอร์ตไปยัง " + target.getName());
                  return;
                }

                if (command.equalsIgnoreCase("gmc")) {
                  if (arr.size() < 1) {
                    sender.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                  } else {
                    target = Bukkit.getPlayer((String)arr.get(0));
                    if (target == null) {
                      sender.sendMessage(BotName + "ไม่พบเกมโหมดนี้! (" + (String)arr.get(0) + ")");
                      return;
                    }

                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + target.getName() + " เป็น " + target.getGameMode().name() + " เรียบร้อยแล้ว!");
                  }

                  return;
                }

                if (command.equalsIgnoreCase("gms")) {
                  if (arr.size() < 1) {
                    sender.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                  } else {
                    target = Bukkit.getPlayer((String)arr.get(0));
                    if (target == null) {
                      sender.sendMessage(BotName + "ไม่พบเกมโหมดนี้! (" + (String)arr.get(0) + ")");
                      return;
                    }

                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + target.getName() + " เป็น " + target.getGameMode().name() + " เรียบร้อยแล้ว!");
                  }

                  return;
                }

                if (command.equalsIgnoreCase("gma")) {
                  if (arr.size() < 1) {
                    sender.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                  } else {
                    target = Bukkit.getPlayer((String)arr.get(0));
                    if (target == null) {
                      sender.sendMessage(BotName + "ไม่พบเกมโหมดนี้! (" + (String)arr.get(0) + ")");
                      return;
                    }

                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + target.getName() + " เป็น " + target.getGameMode().name() + " เรียบร้อยแล้ว!");
                  }

                  return;
                }

                if (command.equalsIgnoreCase("gmsp")) {
                  if (arr.size() < 1) {
                    sender.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                  } else {
                    target = Bukkit.getPlayer((String)arr.get(0));
                    if (target == null) {
                      sender.sendMessage(BotName + "ไม่พบเกมโหมดนี้! (" + (String)arr.get(0) + ")");
                      return;
                    }

                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + target.getName() + " เป็น " + target.getGameMode().name() + " เรียบร้อยแล้ว!");
                  }

                  return;
                }

                if (command.equalsIgnoreCase("gamemode") || command.equalsIgnoreCase("gm")) {
                  if (arr.size() < 1) {
                    if (sender.getGameMode() == GameMode.CREATIVE) {
                      sender.setGameMode(GameMode.SURVIVAL);
                      sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                      return;
                    }

                    if (sender.getGameMode() == GameMode.SURVIVAL) {
                      sender.setGameMode(GameMode.CREATIVE);
                      sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                      return;
                    }

                    if (sender.getGameMode() == GameMode.ADVENTURE) {
                      sender.setGameMode(GameMode.CREATIVE);
                      sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                      return;
                    }

                    if (sender.getGameMode() == GameMode.SPECTATOR) {
                      sender.setGameMode(GameMode.SURVIVAL);
                      sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                      return;
                    }

                    return;
                  }

                  if (arr.size() < 2) {
                    if (!((String)arr.get(0)).equalsIgnoreCase("0") && !((String)arr.get(0)).equalsIgnoreCase("survival") && !((String)arr.get(0)).equalsIgnoreCase("s")) {
                      if (!((String)arr.get(0)).equalsIgnoreCase("1") && !((String)arr.get(0)).equalsIgnoreCase("creative") && !((String)arr.get(0)).equalsIgnoreCase("c")) {
                        if (!((String)arr.get(0)).equalsIgnoreCase("2") && !((String)arr.get(0)).equalsIgnoreCase("adventure") && !((String)arr.get(0)).equalsIgnoreCase("a")) {
                          if (!((String)arr.get(0)).equalsIgnoreCase("3") && !((String)arr.get(0)).equalsIgnoreCase("spectator") && !((String)arr.get(0)).equalsIgnoreCase("sp")) {
                            sender.sendMessage(BotName + "ไม่พบเกมโหมดนี้! (" + (String)arr.get(0) + ")");
                            return;
                          }

                          sender.setGameMode(GameMode.SPECTATOR);
                        } else {
                          sender.setGameMode(GameMode.ADVENTURE);
                        }
                      } else {
                        sender.setGameMode(GameMode.CREATIVE);
                      }
                    } else {
                      sender.setGameMode(GameMode.SURVIVAL);
                    }

                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + sender.getName() + " เป็น " + sender.getGameMode().name() + " เรียบร้อยแล้ว!");
                    return;
                  }

                  if (arr.size() < 3) {
                    target = Bukkit.getPlayer((String)arr.get(1));
                    if (target == null) {
                      sender.sendMessage(BotName + "ไม่พบผู้เล่นนี้! (" + (String)arr.get(1) + ")");
                      return;
                    }

                    if (!((String)arr.get(0)).equalsIgnoreCase("0") && !((String)arr.get(0)).equalsIgnoreCase("survival") && !((String)arr.get(0)).equalsIgnoreCase("s")) {
                      if (!((String)arr.get(0)).equalsIgnoreCase("1") && !((String)arr.get(0)).equalsIgnoreCase("creative") && !((String)arr.get(0)).equalsIgnoreCase("c")) {
                        if (!((String)arr.get(0)).equalsIgnoreCase("2") && !((String)arr.get(0)).equalsIgnoreCase("adventure") && !((String)arr.get(0)).equalsIgnoreCase("a")) {
                          if (!((String)arr.get(0)).equalsIgnoreCase("3") && !((String)arr.get(0)).equalsIgnoreCase("spectator") && !((String)arr.get(0)).equalsIgnoreCase("sp")) {
                            target.sendMessage(BotName + "ไม่พบเกมโหมดนี้! (" + (String)arr.get(0) + ")");
                            return;
                          }

                          target.setGameMode(GameMode.SPECTATOR);
                        } else {
                          target.setGameMode(GameMode.ADVENTURE);
                        }
                      } else {
                        target.setGameMode(GameMode.CREATIVE);
                      }
                    } else {
                      target.setGameMode(GameMode.SURVIVAL);
                    }

                    sender.sendMessage(BotName + "ได้ทำการเปลี่ยนเกมโหมดผู้เล่น " + target.getName() + " เป็น " + target.getGameMode().name() + " เรียบร้อยแล้ว!");
                    return;
                  }

                  target = Bukkit.getPlayer((String)arr.get(0));
                  if (target == null) {
                    sender.sendMessage(BotName + "ไม่พบผู้เล่นนี้! (" + (String)arr.get(0) + ")");
                    return;
                  }

                  sender.teleport(target.getLocation());
                  sender.sendMessage(BotName + "ได้เทเลพอร์ตไปยัง " + target.getName());
                  return;
                }
              }

              if (command.equalsIgnoreCase("user") && sender_dp.getPlayerClass().getId() >= 9 && arr.size() > 0 && ((String)arr.get(1)).equalsIgnoreCase("rank") && ((String)arr.get(2)).equalsIgnoreCase("set")) {
                target = Bukkit.getPlayer((String)arr.get(0));
                if (target != null) {
                  DeluxePlayer.getDeluxePlayer(target).getPlayerClass().setId(Integer.parseInt((String)arr.get(3)));
                } else {
                  DeluxePlayer deluxePlayer = starchaser.MakePlayerData((String)arr.get(0));
                  deluxePlayer.getPlayerClass().setId(Integer.parseInt((String)arr.get(3)));
                  starchaser.sendPlayerData(deluxePlayer);
                }

                sender.sendMessage("§7PlayerManager: §aUser rank set! §7§o(" + (String)arr.get(1) + " moved to rank id " + (String)arr.get(3));
              }

              if (command.equalsIgnoreCase("kick") && sender_dp.getPlayerClass().getId() >= 9) {
                if (arr.size() < 1) {
                  sender.sendMessage(BotName + "วิธีการใช้: !kick <ชื่อผู้เล่น> <เหตุผล>");
                } else {
                  target = Bukkit.getPlayer((String)arr.get(0));
                  if (target == null) {
                    sender.sendMessage(BotName + "ไม่พบผู้เล่นนี้! (" + (String)arr.get(0) + ")");
                  } else {
                    if (arr.size() < 2) {
                      starchaser.BoardCastMsg("§7Kick: §b" + target.getName() + " §cถูกเชิญออกจากเซิร์ฟเวอร์โดย " + sender.getName());
                      target.kickPlayer("§cคุณถูกเชิญออกจากเซิร์ฟเวอร์");
                    } else {
                      kick_msg = "";
                      arr.remove(0);
                      for(String ma : arr){
                      kick_msg = kick_msg + " " + ma;
                      }
                      starchaser.BoardCastMsg("§7Kick: §b" + target.getName() + " §cถูกเชิญออกจากเซิร์ฟเวอร์โดย " + sender.getName() + " เนื่องจาก:§7" + kick_msg);
                      target.kickPlayer("§cคุณถูกเชิญออกจากเซิร์ฟเวอร์ เนื่องจาก:§7" + kick_msg);
                    }

                  }
                }
              } else {
                sender.sendMessage(BotName + "ไม่พบคำสั่งนี้หากสงสัยเกี่ยวกับคำสั่งโปรดพิมพ์ !help เพื่อดูคำสั่งที่สามารถใช้ได้");
              }
            }
          }
        }
      }
    }).runTask(core.getDeluxe);
  }
}
