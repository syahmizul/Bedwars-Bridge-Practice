package com.silverhawk21.technique.NormalBridging.GameEvent;

import com.silverhawk21.technique.GameEvent;

public class PlayerCompleteCourse extends GameEvent {
      public String getName() {
            return "PlayerCompleteCourse";
      }

      public void perform() {
            //Using pressure plate method now
            /*try {
                  if (this.getNode().getIsland("EndIsland").getCuboidRegion().contains(this.getPlayer().getLocation())) {
                        this.getPlayer().sendMessage("Completed course!");
                        this.getNode().setShouldTick(false);
                        this.getPlayer().teleport(new Location(TechniqueManager.g_world, (double)this.getNode().getIsland("StartIsland").getOffset().getBlockX(), (double)this.getNode().getIsland("StartIsland").getOffset().getBlockY(), (double)this.getNode().getIsland("StartIsland").getOffset().getBlockZ()));
                        for (Vector block : getNode().getPlacedBlocks()) {
                              getPlayer().getWorld().getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).setType(Material.AIR);
                        }

                        this.getNode().getPlacedBlocks().clear();
                        int i = 0;

                        for (ItemStack stack : getNode().getCurrentTechnique().itemStacks()) {
                              getPlayer().getInventory().setItem(i, stack);
                              i++;
                        }

                        this.getPlayer().updateInventory();
                  }
            } catch (Exception var4)
            {

            }*/

      }

      public boolean ShouldTickOnPendingTasks() {
            return false;
      }
}
