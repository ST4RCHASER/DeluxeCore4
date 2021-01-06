//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.starchaser.deluxe;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class holoapi {
    private static final double ABS = 0.23D;
    private static String path = Bukkit.getServer().getClass().getPackage().getName();
    private static String version;
    private static Class<?> armorStand;
    private static Class<?> worldClass;
    private static Class<?> nmsEntity;
    private static Class<?> craftWorld;
    private static Class<?> packetClass;
    private static Class<?> entityLivingClass;
    private static Constructor<?> armorStandConstructor;
    private static Class<?> destroyPacketClass;
    private static Constructor<?> destroyPacketConstructor;
    private static Class<?> nmsPacket;

    static {
        version = path.substring(path.lastIndexOf(".") + 1, path.length());

        try {
            armorStand = Class.forName("net.minecraft.server." + version + ".EntityArmorStand");
            worldClass = Class.forName("net.minecraft.server." + version + ".World");
            nmsEntity = Class.forName("net.minecraft.server." + version + ".Entity");
            craftWorld = Class.forName("org.bukkit.craftbukkit." + version + ".CraftWorld");
            packetClass = Class.forName("net.minecraft.server." + version + ".PacketPlayOutSpawnEntityLiving");
            entityLivingClass = Class.forName("net.minecraft.server." + version + ".EntityLiving");
            armorStandConstructor = armorStand.getConstructor(worldClass);
            destroyPacketClass = Class.forName("net.minecraft.server." + version + ".PacketPlayOutEntityDestroy");
            destroyPacketConstructor = destroyPacketClass.getConstructor(int[].class);
            nmsPacket = Class.forName("net.minecraft.server." + version + ".Packet");
        } catch (NoSuchMethodException | SecurityException | ClassNotFoundException var1) {
            System.err.println("Error - Classes not initialized!");
            var1.printStackTrace();
        }

    }

    private List<Object> destroyCache;
    private List<Object> spawnCache;
    private List<UUID> players;
    private List<String> lines;
    private Location loc;

    public holoapi(Location loc, String... lines) {
        this(loc, Arrays.asList(lines));
    }

    public holoapi(Location loc, List<String> lines) {
        this.lines = lines;
        this.loc = loc;
        this.players = new ArrayList();
        this.spawnCache = new ArrayList();
        this.destroyCache = new ArrayList();
        Location displayLoc = loc.clone().add(0.0D, 0.23D * (double) lines.size() - 1.97D, 0.0D);

        for (int i = 0; i < lines.size(); ++i) {
            Object packet = this.getPacket(this.loc.getWorld(), displayLoc.getX(), displayLoc.getY(), displayLoc.getZ(), (String) this.lines.get(i));
            this.spawnCache.add(packet);

            try {
                Field field = packetClass.getDeclaredField("a");
                field.setAccessible(true);
                this.destroyCache.add(this.getDestroyPacket((Integer) field.get(packet)));
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            displayLoc.add(0.0D, -0.23D, 0.0D);
        }

    }

    public boolean display(Player p) {
        for (int i = 0; i < this.spawnCache.size(); ++i) {
            this.sendPacket(p, this.spawnCache.get(i));
        }

        this.players.add(p.getUniqueId());
        return true;
    }

    public boolean display(Player p, int tick_timeout) {
        for (int i = 0; i < this.spawnCache.size(); ++i) {
            this.sendPacket(p, this.spawnCache.get(i));
        }

        this.players.add(p.getUniqueId());
        (new BukkitRunnable() {
            public void run() {
                Iterator var1 = Bukkit.getOnlinePlayers().iterator();

                while (var1.hasNext()) {
                    Player pl = (Player) var1.next();
                    holoapi.this.destroy(pl);
                }

                this.cancel();
            }
        }).runTaskTimerAsynchronously(core.getDeluxe, (long) tick_timeout, (long) tick_timeout);
        return true;
    }

    public boolean destroy(Player p) {
        if (!this.players.contains(p.getUniqueId())) {
            return false;
        } else {
            for (int i = 0; i < this.destroyCache.size(); ++i) {
                this.sendPacket(p, this.destroyCache.get(i));
            }

            this.players.remove(p.getUniqueId());
            return true;
        }
    }

    private Object getPacket(World w, double x, double y, double z, String text) {
        try {
            Object craftWorldObj = craftWorld.cast(w);
            Method getHandleMethod = craftWorldObj.getClass().getMethod("getHandle");
            Object entityObject = armorStandConstructor.newInstance(getHandleMethod.invoke(craftWorldObj));
            Method setCustomName = entityObject.getClass().getMethod("setCustomName", String.class);
            setCustomName.invoke(entityObject, text);
            Method setCustomNameVisible = nmsEntity.getMethod("setCustomNameVisible", Boolean.TYPE);
            setCustomNameVisible.invoke(entityObject, true);
            Method setGravity = entityObject.getClass().getMethod("setGravity", Boolean.TYPE);
            setGravity.invoke(entityObject, false);
            Method setLocation = entityObject.getClass().getMethod("setLocation", Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE);
            setLocation.invoke(entityObject, x, y, z, 0.0F, 0.0F);
            Method setInvisible = entityObject.getClass().getMethod("setInvisible", Boolean.TYPE);
            setInvisible.invoke(entityObject, true);
            Constructor<?> cw = packetClass.getConstructor(entityLivingClass);
            Object packetObject = cw.newInstance(entityObject);
            return packetObject;
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var19) {
            var19.printStackTrace();
            return null;
        }
    }

    private Object getDestroyPacket(int... id) {
        try {
            return destroyPacketConstructor.newInstance(id);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    private void sendPacket(Player p, Object packet) {
        try {
            Method getHandle = p.getClass().getMethod("getHandle");
            Object entityPlayer = getHandle.invoke(p);
            Object pConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            Method sendMethod = pConnection.getClass().getMethod("sendPacket", nmsPacket);
            sendMethod.invoke(pConnection, packet);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }
}
