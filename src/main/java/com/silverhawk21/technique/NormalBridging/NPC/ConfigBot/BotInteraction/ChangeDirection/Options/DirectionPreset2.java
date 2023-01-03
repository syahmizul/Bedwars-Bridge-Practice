package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.ChangeDirection.Options;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.technique.GUIOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class DirectionPreset2 implements GUIOptions {
      public String GetName() {
            return "Semi-Diagonal";
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event) {
            int Alignment = 20;
            IslandData endIsland = node.getIsland("EndIsland");
            Vector oldOffsetModify = endIsland.getOffsetModify();
            endIsland.setOffsetModify(new Vector((double)Alignment, oldOffsetModify.getY(), oldOffsetModify.getZ()));
      }
}
