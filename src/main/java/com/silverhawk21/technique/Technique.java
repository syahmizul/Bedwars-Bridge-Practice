package com.silverhawk21.technique;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.IslandData;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Technique implements Listener {
      private final ArrayList<ArenaNodes> nodes = new ArrayList<>();
      private HashMap<String, IslandData> islandMap = new HashMap<>();
      private ArrayList<GameEvent> gameEvents = new ArrayList<>();

      public void setIsland(String IslandName, IslandData island) {
            this.islandMap.put(IslandName, island);
      }

      public IslandData getIsland(String IslandName) {
            return (IslandData)this.islandMap.get(IslandName);
      }

      public HashMap<String, IslandData> getIslandMap()
      {
            return this.islandMap;
      }

      public void setIslandMap(HashMap<String, IslandData> mapping)
      {
            this.islandMap = mapping;
      }

      public ArrayList<GameEvent> getGameEvents() {
            return this.gameEvents;
      }

      public void setGameEvents(ArrayList<GameEvent> gameEvents) {
            this.gameEvents = gameEvents;
      }

      public ArrayList<ArenaNodes> getNodes() {
            return this.nodes;
      }

      public void addNode(ArenaNodes currentNode) throws Exception{
            currentNode.setCurrentTechnique(this);
            this.InitOffsets(currentNode);
            this.nodes.add(currentNode);


      }

      public void removeNode(Player player) {

            this.nodes.removeIf((node) -> {
                  
                  return node.getCurrentUser().equals(player.getUniqueId());
            });
      }

      public ArenaNodes GetPlayerNode(Player player) {
            for(ArenaNodes node : nodes)
                  if(node.getCurrentUser().equals(player.getUniqueId()))
                        return node;
            return null;
      }

      public abstract String[] getJoinMessage();

      public abstract String getName();

      public abstract BukkitRunnable bukkitTask();

      public abstract ArrayList<ItemStack> itemStacks();

      public abstract void AddPlayerToGame(Player var1, ArenaNodes var2) throws Exception;

      public abstract void RemovePlayerFromGame(Player var1, ArenaNodes var2);

      public abstract void InitOffsets(ArenaNodes var1) throws Exception;

      @EventHandler
      public void onPlayerLoseHealth(EntityDamageEvent event) {
            if (event.getEntityType().equals(EntityType.PLAYER)) {
                  Player p = (Player)event.getEntity();
                  if (TechniqueManager.Get().GetPlayerTechnique(p) != null) {
                        event.setCancelled(true);
                  }
            }

      }

      @EventHandler
      public void onPlayerLoseHunger(FoodLevelChangeEvent event) {
            if (event.getEntityType().equals(EntityType.PLAYER)) {
                  Player p = (Player)event.getEntity();
                  if (TechniqueManager.Get().GetPlayerTechnique(p) != null) {
                        event.setCancelled(true);
                  }
            }

      }

      @EventHandler
      public void onBlockBreak(BlockBreakEvent event) {
            if (this.GetPlayerNode(event.getPlayer()) != null) {
                  event.setCancelled(true);
            }

      }
}
