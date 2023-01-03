package com.silverhawk21.commands.subcommands;

import com.silverhawk21.commands.SubCommands;
import com.silverhawk21.technique.TechniqueManager;
import org.bukkit.entity.Player;

public class ListCommand extends SubCommands {
      public void RegisterCommands() {
      }

      public String getName() {
            return "list";
      }

      public String getDescription() {
            return "Lists all the available Techniques.";
      }

      public String getSyntax() {
            return "/bwp list";
      }

      public void perform(Player player, String[] args) {
            TechniqueManager.Get().ListTechniques(player);
      }
}
