package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.MoveHeight;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.technique.BotInteraction;
import com.silverhawk21.technique.GUIOptions;
import com.silverhawk21.technique.TechniqueManager;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.MoveHeight.Options.HeightPreset1;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.MoveHeight.Options.HeightPreset2;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.MoveHeight.Options.HeightPreset3;
import java.util.Iterator;
import java.util.function.Consumer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;
import redempt.redlib.multiblock.Structure;

public class MoveHeight extends BotInteraction {
      public String GetTitle() {
            return "Island Level";
      }

      public void InitOptions() {
            this.getOptions().add(new HeightPreset1());
            this.getOptions().add(new HeightPreset2());
            this.getOptions().add(new HeightPreset3());
            this.setCurrentOption((GUIOptions)this.getOptions().get(0));
      }

      public void DoReset(ArenaNodes node) {
            Iterator var2 = node.getPlacedBlocks().iterator();

            while(var2.hasNext()) {
                  Vector block = (Vector)var2.next();
                  TechniqueManager.g_world.getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).setType(Material.AIR);
            }

            node.getPlacedBlocks().clear();
            node.setShouldTick(false);
            node.setTimer(0.0D);
      }

      public void DoChangeOffsets(final ArenaNodes node, final Player player) {
            IslandData endIsland = node.getIsland("EndIsland");
            endIsland.getCuboidRegion().forEachBlock((blockState) -> {
                  blockState.setType(Material.AIR);
            });
            Location newLocation = new Location(TechniqueManager.g_world, endIsland.getWorld_Pos().getX() + endIsland.getOffsetModify().getX(), endIsland.getWorld_Pos().getY() + endIsland.getOffsetModify().getY(), endIsland.getWorld_Pos().getZ() + endIsland.getOffsetModify().getZ());
            endIsland.getMBStructure().buildAsync(node.getRunningTasksID(), player, newLocation, 15, new Consumer<Structure>() {
                  public void accept(Structure structure) {

                  }
            });
            Vector newOffset = new Vector(endIsland.getOffsetFocal().getX() + endIsland.getOffsetModify().getX(), endIsland.getOffsetFocal().getY() + endIsland.getOffsetModify().getY(), endIsland.getOffsetFocal().getZ() + endIsland.getOffsetModify().getZ());
            endIsland.setOffset(newOffset);
            endIsland.setCuboidRegion(endIsland.getMBStructure().getRegion(newLocation));
      }

      public Material getMaterial() {
            return Material.LADDER;
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event) {
            this.DoStopTasks(node);
            int idx;
            if (event.isLeftClick()) {
                  if (this.getCurrentOption().equals(this.getOptions().get(0))) {
                        this.setCurrentOption((GUIOptions)this.getOptions().get(this.getOptions().size() - 1));
                        this.getCurrentOption().Perform(node, player, event);
                  } else {
                        idx = this.getOptions().indexOf(this.getCurrentOption());
                        this.setCurrentOption((GUIOptions)this.getOptions().get(idx - 1));
                        this.getCurrentOption().Perform(node, player, event);
                  }
            } else if (event.isRightClick()) {
                  if (this.getCurrentOption().equals(this.getOptions().get(this.getOptions().size() - 1))) {
                        this.setCurrentOption((GUIOptions)this.getOptions().get(0));
                        this.getCurrentOption().Perform(node, player, event);
                  } else {
                        idx = this.getOptions().indexOf(this.getCurrentOption());
                        this.setCurrentOption((GUIOptions)this.getOptions().get(idx + 1));
                        this.getCurrentOption().Perform(node, player, event);
                  }
            }

            this.UpdateItemStack(event);
            this.DoReset(node);
            this.DoChangeOffsets(node, player);
      }
}
