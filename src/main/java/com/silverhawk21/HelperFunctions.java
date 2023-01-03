package com.silverhawk21;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Chunk;
import net.minecraft.server.v1_8_R3.ChunkSection;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class HelperFunctions {
      private static Chunk oldChunk;
      private final static int CENTER_PX = 154;

      public static Vector getMinimum(Vector v1, Vector v2) {
            return new Vector(Math.min(v1.getX(), v2.getX()), Math.min(v1.getY(), v2.getY()), Math.min(v1.getZ(), v2.getZ()));
      }

      public static Vector getMaximum(Vector v1, Vector v2) {
            return new Vector(Math.max(v1.getX(), v2.getX()), Math.max(v1.getY(), v2.getY()), Math.max(v1.getZ(), v2.getZ()));
      }

      public static Vector VectorAbs(Vector vector) {
            return new Vector(Math.abs(vector.getX()), Math.abs(vector.getY()), Math.abs(vector.getZ()));
      }

      public static Vector CalculateIslandOffset(Location PlayerLocation, Location InitPos) {
            return VectorAbs(new Vector((float)(InitPos.getBlockX() - PlayerLocation.getBlockX()) + 0.5F, (float)(InitPos.getBlockY() - PlayerLocation.getBlockY()) + 0.5F, (float)(InitPos.getBlockZ() - PlayerLocation.getBlockZ()) + 0.5F));
      }

      public static void setBlockFast(Player player, World world, int x, int y, int z, int blockId, byte data) {
            net.minecraft.server.v1_8_R3.World nmsWorld = ((CraftWorld)world).getHandle();
            Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
            Location location = new Location(world, (double)x, (double)y, (double)z);
            IBlockData ibd = Block.getByCombinedId(blockId + (data << 12));
            BlockPosition bp = new BlockPosition(x, y, z);
            ChunkSection cs = nmsChunk.getSections()[y >> 4];
            if (cs == null) {
                  cs = new ChunkSection(y >> 4 << 4, !nmsChunk.world.worldProvider.o());
                  nmsChunk.getSections()[y >> 4] = cs;
            }

            if (!location.getChunk().isLoaded()) {
                  location.getChunk().load(true);
            }

            cs.setType(x & 15, y & 15, z & 15, ibd);
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutBlockChange(nmsWorld, bp));
      }

      public static double getRandom(double min, double max) {
            return Math.random() * (max - min) + min;
      }

      public static String formatTime(double time) {
            // Hours, minutes and seconds
            int hours = (int) (time / 3600);
            int minutes = (int) (time % 3600) / 60;
            int seconds = (int) time % 60;
            int milliseconds = (int) ((time * 1000) % 1000) ;

            return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds,milliseconds);
      }



      public static void sendCenteredMessage(Player player, String message){
            if(message == null || message.equals("")) player.sendMessage("");
            message = ChatColor.translateAlternateColorCodes('&', message);

            int messagePxSize = 0;
            boolean previousCode = false;
            boolean isBold = false;

            for(char c : message.toCharArray()){
                  if(c == '§'){
                        previousCode = true;
                        continue;
                  }else if(previousCode == true){
                        previousCode = false;
                        if(c == 'l' || c == 'L'){
                              isBold = true;
                              continue;
                        }else isBold = false;
                  }else{
                        DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                        messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                        messagePxSize++;
                  }
            }

            int halvedMessageSize = messagePxSize / 2;
            int toCompensate = CENTER_PX - halvedMessageSize;
            int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
            int compensated = 0;
            StringBuilder sb = new StringBuilder();
            while(compensated < toCompensate){
                  sb.append(" ");
                  compensated += spaceLength;
            }
            player.sendMessage(sb.toString() + message);
      }

}
