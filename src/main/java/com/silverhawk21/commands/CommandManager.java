package com.silverhawk21.commands;

import com.silverhawk21.commands.subcommands.*;
import com.silverhawk21.commands.subcommands.SetCommands.SetCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

      private ArrayList<SubCommands> subCommands = new ArrayList<>();

      public CommandManager()
      {

            subCommands.add(new JoinCommand());
            subCommands.add(new LeaveCommand());
            subCommands.add(new WandCommand());
            subCommands.add(new SetCommand());
            subCommands.add(new ListCommand());
            subCommands.add(new TestCommand());
            subCommands.add(new AddNPCCommand());
      }



      @Override
      public boolean onCommand( CommandSender sender, Command command,  String label, String[] args)
      {
            if(sender instanceof Player)
            {
                  Player p = (Player) sender;

                  if(args.length > 0 )
                  {
                        for(int i = 0;i<getSubCommands().size();i++)
                        {
                              if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName()))
                              {
                                    getSubCommands().get(i).perform(p,args);
                              }
                        }
                  }
                  else {
                        sender.sendMessage("--------------§aBedWarsPractice Commands§r--------------");
                        subCommands.forEach(subCommands1 -> sender.sendMessage(ChatColor.AQUA + subCommands1.getSyntax() + " - " + subCommands1.getDescription()));
                  }

            }
            else
                  sender.sendMessage("You need to be a Player to execute this command.");
            return true;
      }
      public ArrayList<SubCommands> getSubCommands(){
            return subCommands;
      }
}
