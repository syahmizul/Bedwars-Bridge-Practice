package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.ChangeDistance.Options;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.technique.GUIOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class DistancePreset2 implements GUIOptions {
      public String GetName() {
            return "80 Blocks";
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event) {
            int Distance = -40;
            IslandData endIsland = node.getIsland("EndIsland");
            Vector oldOffsetModify = endIsland.getOffsetModify();
            endIsland.setOffsetModify(new Vector(oldOffsetModify.getX(), oldOffsetModify.getY(), (double)Distance));
      }
}
