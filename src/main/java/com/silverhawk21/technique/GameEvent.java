package com.silverhawk21.technique;

import com.silverhawk21.ArenaNodes;
import org.bukkit.entity.Player;

public abstract class GameEvent {
      private Player player;
      private ArenaNodes node;

      public abstract String getName();

      public abstract void perform();

      public abstract boolean ShouldTickOnPendingTasks();

      public Player getPlayer() {
            return this.player;
      }

      public void setPlayer(Player player) {
            this.player = player;
      }

      public ArenaNodes getNode() {
            return this.node;
      }

      public void setNode(ArenaNodes node) {
            this.node = node;
      }
}
