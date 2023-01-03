package com.silverhawk21.commands.subcommands;

import com.silverhawk21.commands.SubCommands;
import org.bukkit.entity.Player;

public class TestCommand extends SubCommands {
      public void RegisterCommands() {
      }

      public String getName() {
            return "test";
      }

      public String getDescription() {
            return "For testing purposes.Usually to test a function.";
      }

      public String getSyntax() {
            return "/bwp test";
      }

      public void perform(Player player, String[] args) {
      }
}
