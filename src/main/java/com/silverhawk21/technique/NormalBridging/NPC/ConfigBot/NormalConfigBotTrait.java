package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot;

import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.HitSimulate;
import java.util.Collection;
import java.util.Iterator;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.InventoryHolder;

public class NormalConfigBotTrait extends Trait {
      private NormalConfigBotGUI GUI;

      public NormalConfigBotGUI getGUI() {
            return this.GUI;
      }

      public NormalConfigBotTrait() {
            super("ConfigBot-Normal");
      }

      @EventHandler
      public void click(NPCRightClickEvent event) {
            if (event.getNPC() == this.getNPC()) {
                  this.GUI.openInventory(event.getClicker());
            }

      }

      /** @deprecated */
      @Deprecated
      public double[] CalcAngle(Location Source, Location Destination) {
            double[] delta = new double[]{Source.getX() - Destination.getX(), Source.getY() - Destination.getY(), Source.getZ() - Destination.getZ()};
            double hyp = Math.sqrt(delta[0] * delta[0] + delta[1] * delta[1]);
            double[] angles = new double[]{Math.atan2(delta[2], hyp) * 57.295780181884766D, Math.atan(delta[1] / delta[0]) * 57.295780181884766D};
            if (delta[0] >= 0.0D) {
                  angles[1] += 180.0D;
            }

            return angles;
      }

      public Entity GetNearestEntity() {
            Collection entityList = this.getNPC().getStoredLocation().getWorld().getNearbyEntities(this.npc.getStoredLocation(), 100.0D, 50.0D, 50.0D);
            double MAX_DIST = 9999.0D;
            Entity curEntity = null;
            Iterator var5 = entityList.iterator();

            while(var5.hasNext()) {
                  Entity entity = (Entity)var5.next();
                  double distance = entity.getLocation().clone().distanceSquared(this.getNPC().getStoredLocation());
                  if (distance <= MAX_DIST) {
                        MAX_DIST = distance;
                        curEntity = entity;
                  }
            }

            return curEntity;
      }

      public void run() {
      }

      public void onAttach() {
      }

      public void onDespawn() {
      }

      public void onSpawn() {
            this.GUI = new NormalConfigBotGUI((InventoryHolder)this.getNPC().getEntity());
      }

      public void onRemove() {
            /*if (((HitSimulate)this.GUI.getInteractions().get(3)).getCurrentHitTask() != null) {
                  ((HitSimulate)this.GUI.getInteractions().get(3)).getCurrentHitTask().cancel();
            }*/

      }
}
