package com.silverhawk21.technique.NormalBridging.GameEvent;

import com.silverhawk21.technique.GameEvent;

public class PlayerStartCourse extends GameEvent {
      public String getName() {
            return "PlayerStartCourse";
      }

      public void perform() {
      }

      public boolean ShouldTickOnPendingTasks() {
            return false;
      }
}
