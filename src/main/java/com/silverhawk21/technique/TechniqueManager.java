package com.silverhawk21.technique;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.BedwarsPractice;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.SaveData;
import com.silverhawk21.technique.NormalBridging.NormalBridging;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;
import redempt.redlib.multiblock.MultiBlockStructure;

public class TechniqueManager implements Listener {
      private static volatile TechniqueManager instance = null;
      public static World g_world;
      private ArrayList<Technique> techniqueList = new ArrayList<Technique>();

      public static TechniqueManager Get(){
            if(instance == null)
            {
                  synchronized (TechniqueManager.class)
                  {
                        if(instance == null)
                              instance = new TechniqueManager();
                  }
            }
            return instance;
      }

      private void InitWorld()
      {
            try{
                  MultiverseCore core = ((MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core"));
                  if(core != null && core.isEnabled())
                  {
                        MVWorldManager worldManager = core.getMVWorldManager();
                        core.deleteWorld("Arena");

                        WorldCreator wc = new WorldCreator("Arena");
                        wc.type(WorldType.FLAT);
                        wc.generatorSettings("2;0;1;");
                        g_world = wc.createWorld();
                        g_world.setGameRuleValue("doDaylightCycle","false");
                        g_world.setGameRuleValue("doEntityDrops","false");
                        g_world.setGameRuleValue("doFireTick","false");
                        g_world.setGameRuleValue("doMobLoot","false");
                        g_world.setGameRuleValue("doMobSpawning","false");
                        g_world.setGameRuleValue("fallDamage","false");
                        g_world.setGameRuleValue("drowningDamage","false");

                        g_world.setGameRuleValue("fireDamage","false");
                        g_world.setGameRuleValue("freezeDamage","false");
                        g_world.setGameRuleValue("keepInventory","true");
                        g_world.setGameRuleValue("mobGriefing","false");
                        g_world.setGameRuleValue("pvp","false");

                        worldManager.loadDefaultWorlds();
                        MultiverseWorld MVWorld = worldManager.getMVWorld(g_world);
                        MVWorld.setEnableWeather(false);
                        MVWorld.setHunger(false);
                        MVWorld.setTime("day");
                        MVWorld.setAllowAnimalSpawn(false);
                        MVWorld.setAllowMonsterSpawn(false);
                  }
                  else
                  {
                        WorldCreator creator = new WorldCreator("Arena");
                        creator.generateStructures(false);
                        creator.generatorSettings("2;0;1;");
                        creator.type(WorldType.FLAT);
                        creator.environment(World.Environment.NORMAL);
                        World world = Bukkit.getServer().createWorld(creator);
                        if(world != null)
                        {
                              File worldFile = world.getWorldFolder();
                              Bukkit.getServer().unloadWorld(world,false);
                              FileUtils.forceDelete(worldFile);
                        }
                        g_world = Bukkit.getServer().createWorld(creator);
                        g_world.setGameRuleValue("doFireTick", "false");
                        g_world.setGameRuleValue("mobGriefing", "false");
                        g_world.setGameRuleValue("keepInventory", "true");
                        g_world.setGameRuleValue("doMobSpawning", "false");
                        g_world.setGameRuleValue("doMobLoot", "false");
                        g_world.setGameRuleValue("doTileDrops", "false");
                        g_world.setGameRuleValue("doEntityDrops", "false");
                        g_world.setGameRuleValue("commandBlockOutput", "true");
                        g_world.setGameRuleValue("naturalRegeneration", "true");
                        g_world.setGameRuleValue("doDaylightCycle", "false");
                        g_world.setGameRuleValue("logAdminCommands", "true");
                        g_world.setGameRuleValue("showDeathMessages", "false");
                        g_world.setGameRuleValue("sendCommandFeedback", "true");
                        g_world.setGameRuleValue("reducedDebugInfo", "false");
                        g_world.setPVP(false);
                        g_world.setAutoSave(false);
                        g_world.setSpawnFlags(false,false);
                        g_world.setDifficulty(Difficulty.PEACEFUL);
                        g_world.setTime(0);
                  }
            }
            catch(Exception exception)
            {
                  exception.printStackTrace();
            }
      }

      private TechniqueManager() {

            InitWorld();

            this.techniqueList.add(new NormalBridging());
            this.RegisterListeners();
      }

      public void RegisterListeners()
      {
            for(Technique technique : techniqueList)
            {
                  Bukkit.getServer().getPluginManager().registerEvents(technique, BedwarsPractice.Get());
            }
      }


      public void PrintAllNodes()
      {
            for(Technique technique: techniqueList)
            {
                  System.out.println("Technique : " + technique.getName());
                  for(ArenaNodes node: technique.getNodes())
                  {
                        System.out.println("PrintAllNodes : X" + node.getWorldPos().getX() + " Y : " + node.getWorldPos().getY() + " Z :" + node.getWorldPos().getZ());
                  }
            }

      }

      public Location AssignEmptyLocation()
      {
            Vector vector = new Vector(0.0d,100.0d,0.0d);
            boolean isFound;
            do{
                  isFound = false;
                  for(Technique technique : techniqueList)
                  {
                        for(ArenaNodes node : technique.getNodes())
                        {
                              if (node.getWorldPos().equals(vector)) {
                                    isFound = true;
                                    break;
                              }

                        }
                  }
                  if(isFound)
                  {
                        if(vector.getZ() >= 30000000.0d)
                        {
                              return null;
                        }
                        else if(vector.getX() >= 30000000.0d)
                        {
                              vector.setZ(vector.getZ() + 1000.0d);
                              vector.setX(0.0d);
                        }
                        vector.setX(vector.getX()+1000.0d);
                  }

            }
            while(isFound);

            /*System.out.println("Vector generated : X :" + vector.getX() + " Y : " + vector.getY() + " Z :" + vector.getZ());*/
            return vector.toLocation(TechniqueManager.g_world);
      }

      public void ListTechniques(Player player)
      {
            player.sendMessage("Techniques : ");
            for(Technique technique : techniqueList)
            {
                  player.sendMessage(ChatColor.GOLD + technique.getName());
            }
      }


      public Technique GetTechniqueByString(String name)
      {
            for(Technique technique : techniqueList)
            {
                  if(technique.getName().equalsIgnoreCase(name))
                        return technique;
            }
            return null;
      }

      @EventHandler
      public void OnPlayerLeave(PlayerQuitEvent event) {
            if(this.GetPlayerNode(event.getPlayer()) != null)
                  this.LeaveMethod(event.getPlayer(), event);
      }

      @EventHandler
      public void OnPlayerChangeWorld(PlayerChangedWorldEvent event) {
            if (event.getFrom().equals(g_world) && this.GetPlayerNode(event.getPlayer()) != null)
                  this.LeaveMethod(event.getPlayer(), event);
      }

      private void LeaveMethod(Player player2, PlayerEvent event) {
            ArenaNodes node;

            if ((node = this.GetPlayerNode(player2)) != null && !node.isPendingForDeletion()) {
                  node.getCurrentTechnique().RemovePlayerFromGame(player2, node);
            }

      }

      public Technique GetPlayerTechnique(Player player)
      {
            for(Technique technique : techniqueList)
            {
                  for(ArenaNodes node : technique.getNodes())
                        if(node.getCurrentUser().equals(player.getUniqueId()))
                              return technique;
            }
            return null;
      }

      public ArenaNodes GetPlayerNode(Player player) {
            for(Technique technique : techniqueList)
            {
                  for(ArenaNodes node : technique.getNodes())
                        if(node.getCurrentUser().equals(player.getUniqueId()))
                              return node;
            }
            return null;
      }

      public void SaveTechnique(){
            try{
                  ObjectMapper mapper = new ObjectMapper();

                  HashMap<String,ArrayList<SaveData>> SaveMap = new HashMap<>();
                  String PATH_STRING = BedwarsPractice.Get().getDataFolder().getAbsolutePath() +"/technique.json";

                  for(Technique technique : techniqueList)
                  {
                        ArrayList<SaveData> TechniqueData = new ArrayList<>();
                        technique.getIslandMap().forEach((s, island) -> {
                              SaveData data = new SaveData();
                              data.setIslandName(s);
                              data.setMBStructureData(island.getMBStructureString());
                              double[] VectorData = { island.getOffset().getX(),island.getOffset().getY(),island.getOffset().getZ()};
                              data.setStructureVectorData(VectorData);
                              TechniqueData.add(data);
                        });
                        SaveMap.put(technique.getName(),TechniqueData);
                  }


                  if(!BedwarsPractice.Get().getDataFolder().exists())
                        BedwarsPractice.Get().getDataFolder().mkdirs();

                  FileWriter writer = new FileWriter(PATH_STRING);
                  String saveString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(SaveMap);
                  writer.write(saveString);
                  writer.flush();
                  writer.close();
            } catch (Exception e) {
                  e.printStackTrace();
                  Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] " + ChatColor.RED + "Unable to save the techniques.Are the permissions setup correctly?");
            }

      }

      public void LoadTechnique() {
            try{
                  ObjectMapper mapper = new ObjectMapper();
                  //Technique name -> Island name -> Island Object
                  HashMap<String,ArrayList<SaveData>> SaveMap = new HashMap<>();
                  TypeReference<HashMap<String,ArrayList<SaveData>>> typeRef
                          = new TypeReference<HashMap<String,ArrayList<SaveData>>>() {};
                  String PATH_STRING = BedwarsPractice.Get().getDataFolder().getAbsolutePath() +"/technique.json";
                  if(!BedwarsPractice.Get().getDataFolder().exists())
                        return;

                  File reader = new File(PATH_STRING);
                  if(!reader.exists() || reader.length() == 0)
                        return;


                  SaveMap = mapper.readValue(reader,typeRef);
                  SaveMap.forEach((TechniqueName,IslandMap) ->
                  {
                        Technique currentTechnique = null;
                        for(Technique technique : techniqueList)
                        {
                              if(technique.getName().equals(TechniqueName))
                              {
                                    currentTechnique = technique;
                              }
                        }
                        if(currentTechnique != null)
                        {
                              for(SaveData data : IslandMap)
                              {
                                    IslandData newIsland = new IslandData();
                                    newIsland.setMBStructureString(data.getMBStructureData());
                                    newIsland.setMBStructure(MultiBlockStructure.create(newIsland.getMBStructureString(), data.getIslandName()));
                                    newIsland.setOffset(new Vector(data.getStructureVectorData()[0],data.getStructureVectorData()[1],data.getStructureVectorData()[2]));
                                    currentTechnique.setIsland(data.getIslandName(),newIsland);
                                    Bukkit.getServer().getConsoleSender().sendMessage(
                                            "§f[§bBWP§f] "
                                            + ChatColor.AQUA + "Imported Island "
                                            + ChatColor.LIGHT_PURPLE + data.getIslandName()
                                            + ChatColor.AQUA + " for Technique "
                                            + ChatColor.LIGHT_PURPLE + currentTechnique.getName());
                              }
                        }
                  });

            }
            catch (Exception e) {
                  e.printStackTrace();
                  Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] " + ChatColor.DARK_RED + "Unable to parse the techniques.Is the file valid?");

            }


      }

      public void OnDisable() {
            techniqueList.forEach(technique -> technique.getNodes().forEach(
                    arenaNodes -> {
                              technique.RemovePlayerFromGame(Bukkit.getPlayer(arenaNodes.getCurrentUser()),arenaNodes);
                    }));
      }
}
