package com.silverhawk21;

import com.silverhawk21.Island.Island;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.Island.IslandType;
import com.silverhawk21.technique.Technique;
import com.silverhawk21.technique.TechniqueManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class ArenaNodes implements Listener {
      private UUID CurrentUser;
      private double Timer = 0.0D;
      private boolean DidEnd = false;
      private boolean ShouldTick = false;
      private boolean isDoneBuilding = false;
      private boolean isPendingForDeletion = false;
      private Location WorldPos = TechniqueManager.Get().AssignEmptyLocation();
      private HashMap<IslandType, Island> islandMap = new HashMap<>();
      private ArrayList<Location> PlacedBlocks = new ArrayList<>();
      private ArrayList<BukkitTask> runningTasksID = new ArrayList<>();
      private Technique currentTechnique;
      private ArrayList<NPC> entities = new ArrayList<>();

      public void setIsland(String IslandName, IslandData island) {
            this.islandMap.put(IslandName, island);
      }

      public IslandData getIsland(String IslandName) {
            return (IslandData)this.islandMap.get(IslandName);
      }

      public ArrayList<NPC> getEntities() {
            return this.entities;
      }

      public void setEntities(ArrayList<NPC> entities) {
            this.entities = entities;
      }

      public void addEntities(NPC entity) {
            this.entities.add(entity);
      }

      public void removeEntities(NPC entity) {
            this.entities.remove(entity);
      }

      public ArenaNodes(Player player) {
            this.CurrentUser = player.getUniqueId();
      }

      public void addRunningTasks(BukkitTask task) {
            this.runningTasksID.add(task);
      }

      public void removeRunningTasks(BukkitTask task) {
            this.runningTasksID.remove(task);
      }

      public ArrayList<BukkitTask> getRunningTasksID() {
            return this.runningTasksID;
      }

      public void setRunningTasksID(ArrayList<BukkitTask> runningTasksID) {
            this.runningTasksID = runningTasksID;
      }

      public ArrayList<Vector> getPlacedBlocks() {
            return this.PlacedBlocks;
      }

      public void setPlacedBlocks(ArrayList<Vector> placedBlocks) {
            this.PlacedBlocks = placedBlocks;
      }

      public boolean isPendingForDeletion() {
            return isPendingForDeletion;
      }

      public void setPendingForDeletion(boolean pendingForDeletion) {
            isPendingForDeletion = pendingForDeletion;
      }


      public void AddBlockPos(Location VecBlock) {
            if (this.PlacedBlocks == null) {
                  this.PlacedBlocks = new ArrayList<>();
            }

            this.PlacedBlocks.add(VecBlock);
      }

      public Location getWorldPos() {
            return this.WorldPos;
      }

      public void setWorldPos(Location worldPos) {
            this.WorldPos = worldPos;
      }

      public Technique getCurrentTechnique() {
            return this.currentTechnique;
      }

      public void setCurrentTechnique(Technique currentTechnique) {
            this.currentTechnique = currentTechnique;
      }

      public boolean isShouldTick() {
            return this.ShouldTick;
      }

      public void setShouldTick(boolean shouldTick) {
            this.ShouldTick = shouldTick;
      }

      public boolean isDidEnd() {
            return this.DidEnd;
      }

      public void setDidEnd(boolean didEnd) {
            this.DidEnd = didEnd;
      }

      public UUID getCurrentUser() {
            return this.CurrentUser;
      }

      public void setCurrentUser(UUID currentUser) {
            this.CurrentUser = currentUser;
      }

      public double getTimer() {
            return this.Timer;
      }

      public void setTimer(double timer) {
            this.Timer = timer;
      }

      public boolean isDoneBuilding() {
            return this.isDoneBuilding;
      }

      public void setDoneBuilding(boolean doneBuilding) {
            this.isDoneBuilding = doneBuilding;
      }

      public boolean hasRunningTasks()
      {
            boolean hasRunningTask = false;
            for (BukkitTask task: runningTasksID) {
                  if(Bukkit.getScheduler().isCurrentlyRunning(task.getTaskId()) || Bukkit.getScheduler().isQueued(task.getTaskId()))
                  {
                        hasRunningTask = true;
                        break;
                  }
            }
            return hasRunningTask;
      }

      public void RemoveCompletedTask()
      {
            runningTasksID.removeIf(task -> !(Bukkit.getScheduler().isCurrentlyRunning(task.getTaskId()) || Bukkit.getScheduler().isQueued(task.getTaskId())));
      }
}
