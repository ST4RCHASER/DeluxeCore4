package me.starchaser.deluxe;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class VoidGeneratorGenerator extends org.bukkit.generator.ChunkGenerator
{
  public VoidGeneratorGenerator() {}
  
  public java.util.List<BlockPopulator> getDefaultPopulators(World world)
  {
    return java.util.Arrays.asList(new BlockPopulator[0]);
  }
  
  public boolean canSpawn(World world, int x, int z) {
    return true;
  }
  
  public int xyzToByte(int x, int y, int z) {
    return (x * 16 + z) * 128 + y;
  }
  
  public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
    byte[] result = new byte[32768];
    if ((chunkx == 0) && (chunkz == 0)) {
      result[xyzToByte(0, 64, 0)] = ((byte)Material.AIR.getId());
    }
    return result;
  }
  
  public Location getFixedSpawnLocation(World world, Random random) {
    return new Location(world, 0.0D, 66.0D, 0.0D);
  }
}
