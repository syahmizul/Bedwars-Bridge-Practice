package com.silverhawk21;


import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {
      private PacketPlayOutChat packet;

      public ActionBar(String text) {
            this.packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte)2);
      }

      public void sendToPlayer(Player p) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(this.packet);
      }

      public void sendToAll() {

            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                  ((CraftPlayer) p).getHandle().playerConnection.sendPacket(this.packet);
            }

      }
}
