package com.silverhawk21.commands.subcommands;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.commands.SubCommands;
import com.silverhawk21.technique.TechniqueManager;
import org.bukkit.entity.Player;

public class LeaveCommand extends SubCommands {
      public void RegisterCommands() {
      }

      public String getName() {
            return "leave";
      }

      public String getDescription() {
            return "Removes the player from any technique/course they're in.";
      }

      public String getSyntax() {
            return "/bwp leave";
      }

      public void perform(Player player, String[] args) {
            ArenaNodes curPlayerNode;
            if ((curPlayerNode = TechniqueManager.Get().GetPlayerNode(player)) != null) {
                  curPlayerNode.getCurrentTechnique().RemovePlayerFromGame(player, curPlayerNode);
            } else {
                  player.sendMessage("§l§f[§bBWP§f] §4You're not in any Technique!");
            }

      }
}
