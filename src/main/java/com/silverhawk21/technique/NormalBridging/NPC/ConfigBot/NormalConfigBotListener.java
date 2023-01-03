package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.BedwarsPractice;
import com.silverhawk21.technique.BotInteraction;
import com.silverhawk21.technique.Technique;
import com.silverhawk21.technique.TechniqueManager;
import com.silverhawk21.technique.NormalBridging.NormalBridging;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class NormalConfigBotListener implements Listener {
      public NormalConfigBotListener() {
            Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] §aNormalConfigBot listener registered!");
      }

      @EventHandler
      public void onInventoryClick(InventoryClickEvent event) {
            try {
                  ArenaNodes node;
                  Technique technique;
                  if (((node = TechniqueManager.Get().GetPlayerNode((Player)event.getWhoClicked())) != null ) &&
                          ((technique = node.getCurrentTechnique()) != null ) &&
                          event.getClickedInventory() != null &&
                          technique instanceof NormalBridging )
                  {
                        event.setCancelled(true);
                        if((event.getClickedInventory().getTitle().contains("session") || event.getClickedInventory().getName().contains("session")))
                        {
                              NormalConfigBotTrait trait = BedwarsPractice.registry.getNPC((Entity)event.getClickedInventory().getHolder()).getTrait(NormalConfigBotTrait.class);

                              for(BotInteraction interaction : trait.getGUI().getInteractions())
                              {
                                    if (event.getCurrentItem().getType().equals(interaction.getMaterial())) {
                                          interaction.Perform(node, ((Player)event.getWhoClicked()).getPlayer(), event);
                                    }
                              }
                        }

                  }
            } catch (Exception var7) {
                  var7.printStackTrace();
            }

      }

      @EventHandler
      public void onInventoryDrag(InventoryDragEvent event) {
            try {
                  ArenaNodes node;
                  Technique technique;
                  if (((node = TechniqueManager.Get().GetPlayerNode((Player)event.getWhoClicked())) != null ) &&
                          ((technique = node.getCurrentTechnique()) != null ) &&
                          event.getInventory() != null &&
                          technique instanceof NormalBridging)
                  {
                        event.setCancelled(true);
                  }
            } catch (Exception var4) {
                  var4.printStackTrace();
            }

      }
}
