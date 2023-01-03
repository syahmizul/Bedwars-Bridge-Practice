package com.silverhawk21.commands.subcommands.SetCommands;

import com.silverhawk21.HelperFunctions;
import com.silverhawk21.BedwarsPractice;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.commands.SubCommands;
import com.silverhawk21.technique.Technique;
import com.silverhawk21.technique.TechniqueManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import redempt.redlib.multiblock.MultiBlockStructure;

public class SetCommand extends SubCommands {
      public void RegisterCommands() {
      }

      public String getName() {
            return "set";
      }

      public String getDescription() {
            return "Sets spawn/end islandDataList and the spawn/end offsets.";
      }

      public String getSyntax() {
            return "/bwp set <Island String> <Technique Name>";
      }

      public void perform(Player player, String[] args) {
            if (args.length > 2) {
                  if (BedwarsPractice.g_Bounds.containsKey(player.getUniqueId()) && BedwarsPractice.g_Bounds.get(player.getUniqueId()).getMin() != null && BedwarsPractice.g_Bounds.get(player.getUniqueId()).getMax() != null) {
                        if (TechniqueManager.Get().GetTechniqueByString(args[2]) != null) {
                              BedwarsPractice.g_Bounds.get(player.getUniqueId()).SortMinMax();
                              IslandData newIsland = new IslandData();
                              Technique currentTechnique = TechniqueManager.Get().GetTechniqueByString(args[2]);
                              Location PlayerBoundMax = BedwarsPractice.g_Bounds.get(player.getUniqueId()).getMax();
                              Location PlayerBoundMin = BedwarsPractice.g_Bounds.get(player.getUniqueId()).getMin();
                              Vector SpawnOffset = HelperFunctions.CalculateIslandOffset(player.getLocation(), PlayerBoundMin);
                              newIsland.setOffset(SpawnOffset);
                              player.sendMessage(SpawnOffset.toString());
                              newIsland.setMBStructureString(MultiBlockStructure.stringify(PlayerBoundMin, PlayerBoundMax));
                              newIsland.setMBStructure(MultiBlockStructure.create(newIsland.getMBStructureString(), args[1]));
                              player.sendMessage(newIsland.getMBStructureString());
                              currentTechnique.setIsland(args[1], newIsland);
                        } else {
                              player.sendMessage("Technique not found.List the available Techniques with /bwp list");
                        }
                  } else {
                        player.sendMessage("Please setup the bounds first with /bwp wand");
                  }
            } else {
                  player.sendMessage("Syntax is too short,the syntax is " + getSyntax());
            }

      }
}
