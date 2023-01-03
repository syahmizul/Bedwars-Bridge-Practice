package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.Options;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.HelperFunctions;
import com.silverhawk21.BedwarsPractice;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.CustomOption;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.HitSimulate;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class HitPreset3 extends CustomOption {
      public String GetName() {
            return "High Intensity";
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event) {
      }

      public void Perform(final ArenaNodes node, final Player player, InventoryClickEvent event, HitSimulate hitSimulate) {
            hitSimulate.setCurrentHitTask((new BukkitRunnable() {
                  @Override
                  public void run() {
                        if (node.isShouldTick() && player.isOnGround()) {
                              player.setVelocity(new Vector(HelperFunctions.getRandom(-0.4D, 0.4D), 1.0D, HelperFunctions.getRandom(-0.4D, 0.4D)));
                        }

                  }
            }).runTaskTimer(BedwarsPractice.Get(), 0L, 60L));
      }
}
