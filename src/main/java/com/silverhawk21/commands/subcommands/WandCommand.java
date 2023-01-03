package com.silverhawk21.commands.subcommands;

import com.silverhawk21.BoundingSpace;
import com.silverhawk21.BedwarsPractice;
import com.silverhawk21.commands.SubCommands;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WandCommand extends SubCommands {
      @Override
      public void RegisterCommands() {

      }

      @Override
      public String getName() {
            return "wand";
      }

      @Override
      public String getDescription() {
            return "Gives you a tool that lets you select the boundaries for a Node.";
      }

      @Override
      public String getSyntax() {
            return "/bwp wand";
      }

      @Override
      public void perform(Player player, String[] args) {
            if(!BedwarsPractice.g_Bounds.containsKey(player.getUniqueId()))
            {
                  BedwarsPractice.g_Bounds.put(player.getUniqueId(),new BoundingSpace());
                  boolean DoesWandExist = false;
                  for(ItemStack item : player.getInventory().getContents()){
                        if(item != null && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§l§f[§bBWP§f] §eBounds Wand")){
                              DoesWandExist = true;
                        }
                  }
                  if(!DoesWandExist)
                  {
                        ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
                        ItemMeta meta = wand.getItemMeta();
                        meta.setDisplayName("§l§f[§bBWP§f] §eBounds Wand");
                        wand.setItemMeta(meta);
                        player.getInventory().addItem(wand);
                        player.updateInventory();
                  }

                  player.sendMessage("Bounding mode activated!");
            }
            else
            {
                  BedwarsPractice.g_Bounds.remove(player.getUniqueId());
                  for(ItemStack item : player.getInventory().getContents()){
                        if(item != null && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§l§f[§bBWP§f] §eBounds Wand")){
                              player.getInventory().remove(item);
                              player.updateInventory();
                        }
                  }
                  player.updateInventory();
                  player.sendMessage("Bounding mode deactivated!");
            }
      }
}
