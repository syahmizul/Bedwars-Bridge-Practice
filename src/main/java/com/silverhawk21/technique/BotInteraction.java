package com.silverhawk21.technique;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.IslandData;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

public abstract class BotInteraction {
      private ArrayList<GUIOptions> options = new ArrayList<GUIOptions>();
      private GUIOptions CurrentOption;

      public BotInteraction() {
            this.InitOptions();
      }

      public abstract void InitOptions();

      public abstract Material getMaterial();

      public abstract void Perform(ArenaNodes var1, Player var2, InventoryClickEvent var3);

      public abstract String GetTitle();

      public ArrayList<GUIOptions>  getOptions() {
            return this.options;
      }

      public void setOptions(ArrayList<GUIOptions>  options) {
            this.options = options;
      }

      public GUIOptions getCurrentOption() {
            return this.CurrentOption;
      }

      public void setCurrentOption(GUIOptions currentOption) {
            this.CurrentOption = currentOption;
      }

      public ArrayList<String> LoreString() {
            ArrayList<String> LoreStrings = new ArrayList<String>();


            for(GUIOptions option : getOptions())
            {
                  if (this.getCurrentOption().equals(option)) {
                        LoreStrings.add(ChatColor.GREEN + option.GetName());
                  } else {
                        LoreStrings.add(ChatColor.GRAY + option.GetName());
                  }
            }

            return LoreStrings;
      }

      public void UpdateItemStack(InventoryClickEvent event) {
            ItemStack item = event.getCurrentItem();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(this.GetTitle());
            meta.setLore(this.LoreString());
            item.setItemMeta(meta);
      }

      public void DoStopTasks(ArenaNodes node) {
            if (!node.getRunningTasksID().isEmpty()) {
                  for(BukkitTask task : node.getRunningTasksID())
                  {
                        task.cancel();
                  }
                  node.getRunningTasksID().clear();
                  IslandData endIsland = node.getIsland("EndIsland");
                  endIsland.getCuboidRegion().forEachBlock((blockState) -> {
                        blockState.setType(Material.AIR);
                  });
            }

      }
}
