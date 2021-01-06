//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.PacketType.Play.Client;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.StreamSerializer;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtList;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class customplayloadfixer {
    private static final Map<Player, Long> PACKET_USAGE = new ConcurrentHashMap();
    public static String dispatchCommand;
    public static String kickMessage;

    public customplayloadfixer() {
    }

    public static void customPlayloadOnEnable() {
        dispatchCommand = null;
        kickMessage = "§b_StarChaser says §cnope §e:P";
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(core.getDeluxe, new PacketType[]{Client.CUSTOM_PAYLOAD}) {
            public void onPacketReceiving(PacketEvent event) {
                customplayloadfixer.checkPacket(event);
            }
        });
        Bukkit.getScheduler().runTaskTimer(core.getDeluxe, () -> {
            Iterator iterator = PACKET_USAGE.entrySet().iterator();

            while(true) {
                Player player;
                do {
                    if (!iterator.hasNext()) {
                        return;
                    }

                    player = (Player)((Entry)iterator.next()).getKey();
                } while(player.isOnline() && player.isValid());

                iterator.remove();
            }
        }, 20L, 20L);
    }

    public static void customPlayloadOnDisable() {
        ProtocolLibrary.getProtocolManager().removePacketListeners(core.getDeluxe);
    }

    public static void checkPacket(PacketEvent event) {
        Player player = event.getPlayer();
        long lastPacket = (Long)PACKET_USAGE.getOrDefault(player, -1L);
        if (lastPacket == -2L) {
            event.setCancelled(true);
        } else {
            String name = (String)event.getPacket().getStrings().readSafely(0);
            if ("MC|BSign".equals(name) || "MC|BEdit".equals(name) || "REGISTER".equals(name)) {
                try {
                    if ("REGISTER".equals(name)) {
                        checkChannels(event);
                    } else {
                        if (!elapsed(lastPacket, 100L)) {
                            throw new IOException("Packet flood");
                        }

                        PACKET_USAGE.put(player, System.currentTimeMillis());
                        checkNbtTags(event);
                    }
                } catch (Throwable var6) {
                    PACKET_USAGE.put(player, -2L);
                    Bukkit.getScheduler().runTask(core.getDeluxe, () -> {
                        player.kickPlayer(kickMessage);
                        if (dispatchCommand != null) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), dispatchCommand.replace("%name%", player.getName()));
                        }

                    });
                    Bukkit.getLogger().warning("DeluxeCore: " + player.getName() + " tried to exploit CustomPayload: " + var6.getMessage());
                    event.setCancelled(true);
                }

            }
        }
    }

    public static void checkNbtTags(PacketEvent event) throws IOException {
        PacketContainer container = event.getPacket();
        ByteBuf buffer = ((ByteBuf)container.getSpecificModifier(ByteBuf.class).read(0)).copy();
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);

        try {
            DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(bytes));
            Throwable var5 = null;

            try {
                ItemStack itemStack = StreamSerializer.getDefault().deserializeItemStack(inputStream);
                if (itemStack == null) {
                    throw new IOException("Unable to deserialize ItemStack");
                }

                NbtCompound root = (NbtCompound)NbtFactory.fromItemTag(itemStack);
                if (root == null) {
                    throw new IOException("No NBT tag?!");
                }

                if (!root.containsKey("pages")) {
                    throw new IOException("No 'pages' NBT compound was found");
                }

                NbtList<String> pages = root.getList("pages");
                if (pages.size() > 50) {
                    throw new IOException("Too much pages");
                }
            } catch (Throwable var23) {
                var5 = var23;
                throw var23;
            } finally {
                if (inputStream != null) {
                    if (var5 != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var22) {
                            var5.addSuppressed(var22);
                        }
                    } else {
                        inputStream.close();
                    }
                }

            }
        } finally {
            buffer.release();
        }

    }

    public static void checkChannels(PacketEvent event) throws Exception {
        int channelsSize = event.getPlayer().getListeningPluginChannels().size();
        PacketContainer container = event.getPacket();
        ByteBuf buffer = ((ByteBuf)container.getSpecificModifier(ByteBuf.class).read(0)).copy();

        try {
            for(int i = 0; i < buffer.toString(Charsets.UTF_8).split("\u0000").length; ++i) {
                ++channelsSize;
                if (channelsSize > 124) {
                    throw new IOException("Too much channels");
                }
            }
        } finally {
            buffer.release();
        }

    }

    public static boolean elapsed(long from, long required) {
        return from == -1L || System.currentTimeMillis() - from > required;
    }
}
