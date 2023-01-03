package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.Options;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.CustomOption;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.HitSimulate;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class HitPreset1 extends CustomOption {
      public String GetName() {
            return "Disabled";
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event) {
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event, HitSimulate hitSimulate) {
      }
}
