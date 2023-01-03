package com.silverhawk21.technique.NormalBridging.GameEvent;

import com.silverhawk21.technique.GameEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerOutOfBounds extends GameEvent {
      public String getName() {
            return null;
      }

      public void perform() {
            try {
                  if (this.getPlayer().getLocation().getY() <= Math.min(this.getNode().getIsland("StartIsland").getCuboidRegion().getStart().getY(), this.getNode().getIsland("EndIsland").getCuboidRegion().getStart().getY())) {
                        this.getPlayer().sendMessage("§aLotus§dPractice §c>> §eYou fell!");
                        this.getNode().setShouldTick(false);
                        this.getNode().setTimer(0.0D);
                        this.getPlayer().teleport(new Location(this.getPlayer().getWorld(), (double)this.getNode().getIsland("StartIsland").getOffset().getBlockX(), (double)this.getNode().getIsland("StartIsland").getOffset().getBlockY(), (double)this.getNode().getIsland("StartIsland").getOffset().getBlockZ()));

                        for(Vector block : getNode().getPlacedBlocks())
                              getPlayer().getWorld().getBlockAt(block.getBlockX(),block.getBlockY(),block.getBlockZ()).setType(Material.AIR);

                        this.getNode().getPlacedBlocks().clear();
                        int i = 0;

                        for(ItemStack stack : getNode().getCurrentTechnique().itemStacks())
                        {
                              getPlayer().getInventory().setItem(i,stack);
                              i++;
                        }

                        this.getPlayer().updateInventory();
                  }
            } catch (Exception var4) {
            }

      }

      public boolean ShouldTickOnPendingTasks() {
            return true;
      }
}
