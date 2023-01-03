package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.MoveHeight.Options;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.technique.GUIOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class HeightPreset2 implements GUIOptions {
      public String GetName() {
            return "Higher";
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event) {
            int Height = 30;
            IslandData endIsland = node.getIsland("EndIsland");
            Vector oldOffsetModify = endIsland.getOffsetModify();
            endIsland.setOffsetModify(new Vector(oldOffsetModify.getX(), (double)Height, oldOffsetModify.getZ()));
      }
}
