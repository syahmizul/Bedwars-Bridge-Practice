package com.silverhawk21.commands;

import org.bukkit.entity.Player;
import java.util.ArrayList;

public abstract class SubCommands {

      private ArrayList<SubCommands> subCommands = new ArrayList<>();

      public ArrayList<SubCommands> getSubCommands(){
            return subCommands;
      }

      public SubCommands getSubcommand(String name)
      {
            for(SubCommands subCommands1 : subCommands)
            {
                  if(subCommands1.getName().equalsIgnoreCase(name))
                        return subCommands1;
            }
            return null;
      }

      public void AddSubCommands(SubCommands subCmd)
      {
            subCommands.add(subCmd);
      }

      public void RemoveSubCommands(SubCommands subCmd)
      {
            subCommands.remove(subCmd);
      }

      public SubCommands()
      {
            RegisterCommands();
      }

      public abstract void RegisterCommands();

      public abstract String getName();

      public abstract String getDescription();

      public abstract String getSyntax();

      public abstract void perform(Player player, String[] args);
}
