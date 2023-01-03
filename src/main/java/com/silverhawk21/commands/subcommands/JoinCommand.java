package com.silverhawk21.commands.subcommands;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.CustomBlockData;
import com.silverhawk21.commands.SubCommands;
import com.silverhawk21.technique.Technique;
import com.silverhawk21.technique.TechniqueManager;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class JoinCommand extends SubCommands {
      public void RegisterCommands() {
      }

      public String getName() {
            return "join";
      }

      public String getDescription() {
            return "Join a Technique.List the available Techniques using /bwp list";
      }

      public String getSyntax() {
            return "/bwp join <Technique Name>";
      }

      public void DeepPrint(ArrayList arrayList) {

            for (Object element : arrayList) {
                  if (element instanceof CustomBlockData) {
                        System.out.println(((CustomBlockData) element).getBlockID());
                  }

                  if (element instanceof ArrayList) {
                        this.DeepPrint((ArrayList) element);
                  }
            }

      }

      public void perform(Player player, String[] args) {
            if (args.length > 1) {
                  Technique technique;
                  if ((technique = TechniqueManager.Get().GetTechniqueByString(args[1])) != null) {
                        try{
                              if (TechniqueManager.Get().GetPlayerTechnique(player) != null) {
                                    player.sendMessage("§c§lYou are already in an arena.Please leave with §a/bwp leave§c first.");
                              } else {
                                    ArenaNodes newNode = new ArenaNodes(player);
                                    technique.addNode(newNode);
                                    /*player.sendMessage("JoinCommand#perform() : X" + newNode.getWorldPos().getX() + " Y : " + newNode.getWorldPos().getY() + " Z :" + newNode.getWorldPos().getZ());
                                    TechniqueManager.Get().PrintAllNodes();*/
                                    newNode.getCurrentTechnique().AddPlayerToGame(player, newNode);
                              }
                        }
                        catch(Exception exception)
                        {
                              System.out.println(exception.getMessage());

                        }
                  } else {
                        player.sendMessage("That Technique is not available.Please list the available Techniques by using /bwp list");
                  }
            }

      }
}
