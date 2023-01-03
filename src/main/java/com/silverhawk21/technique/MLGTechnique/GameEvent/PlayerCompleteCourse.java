package com.silverhawk21.technique.MLGTechnique.GameEvent;

import com.silverhawk21.technique.GameEvent;

public class PlayerCompleteCourse extends GameEvent {
      public String getName() {
            return "PlayerCompleteCourse";
      }

      public void perform() {
      }

      public boolean ShouldTickOnPendingTasks() {
            return false;
      }
}
