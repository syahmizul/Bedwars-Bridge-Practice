package com.silverhawk21.PacketListeners;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.technique.TechniqueManager;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Client;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;
import org.bukkit.plugin.Plugin;

public class ArmorStandListener extends PacketAdapter {
      public ArmorStandListener(Plugin plugin, PacketType... types) {
            super(plugin, types);
      }

      public void onPacketReceiving(PacketEvent e) {
            ArenaNodes node = TechniqueManager.Get().GetPlayerNode(e.getPlayer());
            if (node != null && !node.isDoneBuilding()) {
                  if (e.getPacketType() == Client.STEER_VEHICLE && e.getPacket().getHandle() instanceof PacketPlayInSteerVehicle) {
                        Field f = null;

                        try {
                              f = PacketPlayInSteerVehicle.class.getDeclaredField("d");
                              f.setAccessible(true);
                              f.set(e.getPacket().getHandle(), false);
                              f.setAccessible(false);
                        } catch (Exception var4) {
                              var4.printStackTrace();
                        }
                  }

            }
      }
}
