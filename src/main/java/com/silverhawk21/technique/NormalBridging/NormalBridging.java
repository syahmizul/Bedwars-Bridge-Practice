package com.silverhawk21.technique.NormalBridging;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.libs.sidebar.Sidebar;
import com.andrei1058.bedwars.libs.sidebar.SidebarLine;
import com.andrei1058.bedwars.sidebar.BedWarsScoreboard;
import com.silverhawk21.ArenaNodes;
import com.silverhawk21.BedwarsPractice;
import com.silverhawk21.HelperFunctions;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.TickrateProgress;
import com.silverhawk21.technique.GameEvent;
import com.silverhawk21.technique.Technique;
import com.silverhawk21.technique.TechniqueManager;
import com.silverhawk21.technique.NormalBridging.GameEvent.PlayerCompleteCourse;
import com.silverhawk21.technique.NormalBridging.GameEvent.PlayerOutOfBounds;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.NormalConfigBotTrait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import redempt.redlib.multiblock.Structure;

public class NormalBridging extends Technique {
      private final int IslandDistance = 40;
      public NormalBridging() {
            this.bukkitTask().runTaskTimer(BedwarsPractice.Get(), 0L, 1L);
            this.getGameEvents().add(new PlayerCompleteCourse());
            this.getGameEvents().add(new PlayerOutOfBounds());
      }
      @Override
      public void InitOffsets(ArenaNodes node) throws Exception {
            try {
                  node.setIsland("StartIsland", this.getIsland("StartIsland").copy());
                  node.setIsland("EndIsland", this.getIsland("EndIsland").copy());
                  node.getIsland("StartIsland").setWorld_Pos(node.getWorldPos());
                  node.getIsland("EndIsland").setWorld_Pos(new Vector(node.getWorldPos().getX(), node.getWorldPos().getY(), node.getWorldPos().getZ() - 40.0D));
                  node.getIsland("StartIsland").setCuboidRegion(node.getIsland("StartIsland").getMBStructure().getRegion(node.getIsland("StartIsland").getWorld_Pos().toLocation(TechniqueManager.g_world)));
                  node.getIsland("EndIsland").setCuboidRegion(node.getIsland("EndIsland").getMBStructure().getRegion(node.getIsland("EndIsland").getWorld_Pos().toLocation(TechniqueManager.g_world)));
                  node.getIsland("StartIsland").OffsetToWorldPos();
                  node.getIsland("EndIsland").OffsetToWorldPos();
                  node.getIsland("StartIsland").setOffsetFocal(node.getIsland("StartIsland").getOffset().clone());
                  node.getIsland("EndIsland").setOffsetFocal(node.getIsland("EndIsland").getOffset().clone());
            } catch (Exception var3) {
                  throw new Exception("The islandDataList for this technique is not properly set up.Please contact an admin!");
            }

      }
      @Override
      public ArrayList<ItemStack> itemStacks() {
            ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>(
                    Arrays.asList
                            (
                            new ItemStack(Material.STONE, 64),
                            new ItemStack(Material.WOOD, 64),
                            new ItemStack(Material.COBBLESTONE, 64)
                    ));
            for(ItemStack stack : itemStacks)
            {
                  ItemMeta meta = stack.getItemMeta();
                  meta.setDisplayName("§e§lBuilding Blocks");
                  stack.setItemMeta(meta);
            }

            ItemStack ResetTool = new ItemStack(356, 1);
            ItemMeta ResetToolMeta = ResetTool.getItemMeta();
            ResetToolMeta.setDisplayName("§c§lReset");
            ResetTool.setItemMeta(ResetToolMeta);
            itemStacks.add(ResetTool);

            ItemStack LeaveTool = new ItemStack(166, 1);
            ItemMeta LeaveToolMeta  = LeaveTool .getItemMeta();
            LeaveToolMeta.setDisplayName("§c§lLeave");
            LeaveTool.setItemMeta(LeaveToolMeta);
            itemStacks.add(LeaveTool);

            return itemStacks;
      }
      @Override
      public String[] getJoinMessage() {
            return new String[]
            {
                  ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 80),
                  "§fBridging Practice",
                  "",
                  "§o§bObjective:",
                  "§ePlace blocks and build a bridge to cross to the opposite island.",
                  "§ePlace the Building Blocks on any of the Andesite blocks to start.",
                  "§eStep on the golden pressure plate at the end to complete the course.",
                  "",
                  ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + StringUtils.repeat(" ", 80)
            };
      }

      public String getName()
      {
            return "Normal";
      }

      @EventHandler
      public void onBlockPlaced(BlockPlaceEvent event) {
            if (this.GetPlayerNode(event.getPlayer()) != null && this.GetPlayerNode(event.getPlayer()).getCurrentTechnique().getName() == this.getName()) {
                  Player player = event.getPlayer();
                  if (event.getItemInHand() != null && this.GetPlayerNode(event.getPlayer()).getRunningTasksID().isEmpty() && event.getItemInHand().getItemMeta() != null && event.getItemInHand().getItemMeta().hasDisplayName() && event.getItemInHand().getItemMeta().getDisplayName().equals("§e§lBuilding Blocks")) {
                        if (event.getBlockAgainst().getTypeId() == 1 && event.getBlockAgainst().getData() == 6) {
                              this.GetPlayerNode(player).AddBlockPos(event.getBlock().getLocation());
                              this.GetPlayerNode(player).setShouldTick(true);
                              this.GetPlayerNode(player).setTimer(0.0D);
                        } else if (this.GetPlayerNode(player).getPlacedBlocks().contains(event.getBlockAgainst().getLocation().toVector())) {
                              this.GetPlayerNode(player).AddBlockPos(event.getBlock().getLocation());
                        } else {
                              player.sendMessage("§cYou can't place a block here!");
                              event.setCancelled(true);
                        }
                  } else {
                        player.sendMessage("§cPlease use the Building Blocks.");
                        event.setCancelled(true);
                  }
            }

      }

      @EventHandler
      public void onPlayerUse(PlayerInteractEvent event) {
            Player p = event.getPlayer();
            ArenaNodes node = TechniqueManager.Get().GetPlayerNode(event.getPlayer());
            if (node != null) {
                  if(event.getAction().equals(Action.PHYSICAL) && event.getClickedBlock().getType().equals(Material.GOLD_PLATE))
                  {
                        try {

                              event.getPlayer().sendMessage("§aLotus§dPractice §c>> §eCompleted Course. In " + String.format("%.2f",node.getTimer()) +" seconds!");
                              node.setShouldTick(false);
                              event.getPlayer().teleport(new Location(TechniqueManager.g_world, (double)node.getIsland("StartIsland").getOffset().getBlockX(), (double)node.getIsland("StartIsland").getOffset().getBlockY(), (double)node.getIsland("StartIsland").getOffset().getBlockZ()));
                              for (Vector block : node.getPlacedBlocks()) {
                                   event.getPlayer().getWorld().getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).setType(Material.AIR);
                              }

                              node.getPlacedBlocks().clear();
                              int i = 0;

                              for (ItemStack stack : node.getCurrentTechnique().itemStacks()) {
                                    event.getPlayer().getInventory().setItem(i, stack);
                                    i++;
                              }

                              event.getPlayer().updateInventory();

                        } catch (Exception ignored)
                        {

                        }
                  }
                  if(p.getItemInHand().getTypeId() == 356){

                        event.getPlayer().teleport((new Vector(0.5D, 0.0D, 0.5D)).add(new Vector(node.getIsland("StartIsland").getOffset().getBlockX(), node.getIsland("StartIsland").getOffset().getBlockY(), node.getIsland("StartIsland").getOffset().getBlockZ())).toLocation(event.getPlayer().getWorld()));
                        node.setShouldTick(false);
                        node.setTimer(0.0D);
                        Sidebar sidebar = BedWarsScoreboard.getSBoard(event.getPlayer().getUniqueId()).getHandle();
                        sidebar.setLine(new SidebarLine() {
                              @Override
                              public @NotNull String getLine() {
                                    return ChatColor.RESET + "§dTimer: §a" + HelperFunctions.formatTime(node.getTimer()) + " ";
                              }
                        },3);

                        for (Vector block : node.getPlacedBlocks()) {
                              event.getPlayer().getWorld().getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).setType(Material.AIR);
                        }

                        node.getPlacedBlocks().clear();
                        int i = 0;

                        for(Iterator var8 = node.getCurrentTechnique().itemStacks().iterator(); var8.hasNext(); ++i) {
                              ItemStack stack = (ItemStack)var8.next();
                              event.getPlayer().getInventory().setItem(i, stack);
                        }

                        event.getPlayer().updateInventory();
                  }
                  else if(p.getItemInHand().getTypeId() == 166)
                        p.performCommand("bwp leave");

            }


      }

      @EventHandler
      public void playerDropItem(PlayerDropItemEvent event)
      {
            if (TechniqueManager.Get().GetPlayerNode(event.getPlayer()) != null) {
                  event.setCancelled(true);
            }
      }

      @Override
      public BukkitRunnable bukkitTask() {
            return new BukkitRunnable() {
                  public void run() {
                        Iterator var1 = NormalBridging.this.getNodes().iterator();

                        label46:
                        while(true) {
                              ArenaNodes node;
                              do {
                                    if (!var1.hasNext()) {
                                          return;
                                    }

                                    node = (ArenaNodes)var1.next();
                              } while(!node.isDoneBuilding());

                              Player currentPlayer = Bukkit.getPlayer(node.getCurrentUser());

                              if(BedWarsScoreboard.getSBoard(currentPlayer.getUniqueId())!= null && BedWarsScoreboard.getSBoard(currentPlayer.getUniqueId()).getHandle() != null)
                              {
                                    Sidebar sidebar = BedWarsScoreboard.getSBoard(currentPlayer.getUniqueId()).getHandle();
                                    ArenaNodes finalNode = node;
                                    sidebar.setLine(new SidebarLine() {
                                          @Override
                                          public @NotNull String getLine() {
                                                return TickrateProgress.TickString;
                                          }
                                    },0);
                                    sidebar.setLine(new SidebarLine() {
                                          @Override
                                          public @NotNull String getLine() {

                                                return ChatColor.RESET + "§dTimer: §a" + HelperFunctions.formatTime(finalNode.getTimer()) + " ";
                                          }
                                    },3);
                                    sidebar.setLine(new SidebarLine() {
                                          @Override
                                          public @NotNull String getLine() {
                                                String ProgressString = "§dProgress: §a" + String.format("%.0f", Math.abs(currentPlayer.getLocation().toVector().distance(finalNode.getIsland("EndIsland").getOffset()) - finalNode.getIsland("StartIsland").getOffset().distance(finalNode.getIsland("EndIsland").getOffset())) / finalNode.getIsland("StartIsland").getOffset().distance(finalNode.getIsland("EndIsland").getOffset()) * 100.0D) + " %";
                                                return ChatColor.RESET + ProgressString + " ";
                                          }
                                    },4);
                              }


                              if (node.isShouldTick()) {
                                    node.setTimer(node.getTimer() + 0.05D);
                              }

                              Iterator var4 = NormalBridging.this.getGameEvents().iterator();

                              while(true) {
                                    GameEvent gameEvent;
                                    do {
                                          if (!var4.hasNext()) {
                                                continue label46;
                                          }

                                          gameEvent = (GameEvent)var4.next();
                                          gameEvent.setNode(node);
                                          gameEvent.setPlayer(currentPlayer);
                                          if (gameEvent.ShouldTickOnPendingTasks() && node.getRunningTasksID().isEmpty()) {
                                                gameEvent.perform();
                                          }
                                    } while(gameEvent.ShouldTickOnPendingTasks() && node.getRunningTasksID().isEmpty());

                                    gameEvent.perform();
                              }
                        }
                  }
            };
      }

      @Override
      public void AddPlayerToGame(final Player player, final ArenaNodes node) throws Exception{
            try{
                  Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] " + ChatColor.GREEN + player.getName() + " joined " + node.getCurrentTechnique().getName());
                  final Location SpawnOffset = node.getIsland("StartIsland").getOffset().toLocation(TechniqueManager.g_world);
                  player.getInventory().clear();
                  player.teleport(SpawnOffset);

                  Bukkit.getScheduler().scheduleSyncDelayedTask(BedwarsPractice.Get(), () -> {
                        ArmorStand armorStand = (ArmorStand) SpawnOffset.getWorld().spawnEntity(SpawnOffset,EntityType.ARMOR_STAND);
                        armorStand.setGravity(false);
                        armorStand.setPassenger(player);
                        armorStand.setVisible(false);
                        armorStand.setSmall(true);
                  },2L);

                  Technique thisTechnique = this;
                  ArenaNodes thisNode = node;
                  class CompletionCheck extends BukkitRunnable {

                        final ArenaNodes node = thisNode;
                        final Technique technique = thisTechnique;

                        @Override
                        public void run() {
                              if (this.node.getRunningTasksID().isEmpty()) {
                                    this.node.setDoneBuilding(true);
                                    if(player.getVehicle() != null)
                                    {
                                          player.getVehicle().remove();
                                    }

                                    String[] boardLines = new String[]{
                                            "§m§l§7--------------------",
                                            "Hello §e" + player.getName(),
                                            " ",
                                            "§bPlace a block to start.",
                                            " ",
                                            " ",
                                            "§dTimer: §a" + HelperFunctions.formatTime(node.getTimer()),
                                            " ",
                                            "§dTick rate: §a",
                                            TickrateProgress.TickString
                                    };
                                    BedWarsScoreboard scoreboard = BedWarsScoreboard.getSBoard(player.getUniqueId());
                                    Sidebar sidebar = scoreboard.getHandle();
                                    if(scoreboard != null && sidebar != null) {

                                          for (int i = sidebar.linesAmount(); i >= 0 ; i--) {
                                                int finalI = (i * -1) + sidebar.linesAmount();
                                                if(finalI<boardLines.length)
                                                {
                                                      SidebarLine lines = new SidebarLine() {
                                                            @Override
                                                            public @NotNull String getLine() {
                                                                  return ChatColor.RESET + boardLines[finalI] + " ";
                                                            }
                                                      };

                                                      sidebar.setLine(lines, i);
                                                }
                                                else
                                                      sidebar.removeLine(i);

                                          }
                                    }
                                    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + StringUtils.repeat("\n", 100) + "\"}"), (byte)0));
                                    for(String joinMessage : getJoinMessage())
                                    {
                                          HelperFunctions.sendCenteredMessage(player,joinMessage);
                                    }

                                    int i = 0;
                                    player.getInventory().clear();
                                    for(ItemStack stack : itemStacks())
                                    {
                                          player.getInventory().setItem(i,stack);
                                          i++;
                                    }

                                    player.updateInventory();
                                    NPC npc = BedwarsPractice.registry.createNPC(EntityType.PLAYER, "§aConfiguration Bot");
                                    npc.spawn(SpawnOffset);
                                    npc.getOrAddTrait(NormalConfigBotTrait.class);
                                    npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("BOT", "amKejLWVAowk6xO/GebsLUotVt7voSc0OhR3UHbbuUiOlzLrPrzTsDC55MTnvXH5HefrUwLL8zpxmYXa6jnrgqLcPd9/RZO/BVcO7QwYjLXGceqGg6+YkB0wf2fV99YdQWYtfEWRfaNejeeOOzDR5Ju6lozTjFTzcujA2HtoMeEPu/Jrf9+Gjcmj3kFj6BfEvbuMex+PGV7jHSVbZpkVgbYwNEblyWkBmeS17ea2qDuNKL1r5WhW8VD2s9ZdRX0EM5u0dyMaDYcfW7y7YF1EuKKuJCvsUeacldIU+ObvFOkINWlZs2jmkaRoAY2XWagiiCcrMtCrKUuKYctc/XDOk7eMh35rXOQ5aygiIiGwQRNGLtZir37X4c2Q/VzFBht6RE2xQMXuyxwOF1zpwOTuvAO6zsmzIdm0cc77qHA4CGYVlAScLMgDUT789JY27PkAWNmRufMf5g6Fzv64hIicDZ3PQVwv6TYqhh5ZAufhxlSxExjMdkGWyOtzCH7Dx1bWTh9k9Z/ZmGe46pMjaS/hMbfbXwYUPz4zojLKVwaKfaNVNvrrBs1z1fctZwSqFOJ1Uj/xuEdgzwnCOFKr5KmavYAiIreFd95ZzY7YTODYBtSooTVwjvqnLSbajUbJ0AFOfHSGYoAA6L/Aw9DXReB/Q0GhFa+2akMUafEitc/k0zI=", "ewogICJ0aW1lc3RhbXAiIDogMTYyNjU5ODAwNTk3NywKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzJiYWE2NGY1N2RjNjdkZDU3ZTExZTk4ODg4Njg3YmU4NWRjZDNiMmU2ODNlYjAxOWU5ZThhYmQzOTI2Yjc3IgogICAgfQogIH0KfQ==");
                                    npc.getOrAddTrait(LookClose.class).lookClose(true);
                                    node.addEntities(npc);

                              }

                              this.cancel();
                        }
                  }

                  node.getIsland("StartIsland").getMBStructure().buildAsync(node.getRunningTasksID(), player, node.getIsland("StartIsland").getWorld_Pos().toLocation(TechniqueManager.g_world), 15, new Consumer<Structure>() {
                        @Override
                        public void accept(Structure structure) {
                        new CompletionCheck().runTaskTimer(BedwarsPractice.Get(), 0L, 1L);
                        }
                  });
                  node.getIsland("EndIsland").getMBStructure().buildAsync(node.getRunningTasksID(), player, node.getIsland("EndIsland").getWorld_Pos().toLocation(TechniqueManager.g_world), 15, new Consumer<Structure>() {
                        @Override
                        public void accept(Structure structure) {
                              new CompletionCheck().runTaskTimer(BedwarsPractice.Get(), 0L, 1L);
                        }
                  });

            }
            catch(Exception exception)
            {
                  exception.printStackTrace();
                  throw new Exception("The islandDataList for this technique is not properly set up.Please contact an admin!");
            }


      }
      @Override
      public void RemovePlayerFromGame(final Player player, final ArenaNodes node) {
            try{
                  player.getInventory().clear();
                  if(player.getVehicle() != null)
                        player.getVehicle().remove();
                  node.setPendingForDeletion(true);

                  //The server is using BedWars 1058 - we need to handle these manually since that plugin doesn't know we exist.
                  //

                  BedWarsScoreboard.giveScoreboard(player, null, true);
                  Bukkit.getScheduler().runTaskLater(BedwarsPractice.Get(),
                          () ->
                          {
                              player.setHealthScale(player.getMaxHealth());
                              player.setExp(0.0f);
                              player.setHealthScale(20.0);
                              player.setFoodLevel(20);
                              Arena.sendLobbyCommandItems(player);
                          },
                          20L);

                  final Location configLoc = BedWars.config.getConfigLoc("lobbyLoc");
                  if (configLoc != null && configLoc.getWorld() != null)
                        player.teleport(configLoc, PlayerTeleportEvent.TeleportCause.PLUGIN);

                  //
                  //The server is using BedWars 1058 - we need to handle these manually since that plugin doesn't know we exist.

                  (new BukkitRunnable() {
                        @Override
                        public void run() {
                              Iterator var1 = node.getRunningTasksID().iterator();

                              while(true) {
                                    BukkitTask task;
                                    do {
                                          if (!var1.hasNext()) {
                                                try{
                                                      IslandData StartIsland = node.getIsland("StartIsland");
                                                      IslandData EndIsland = node.getIsland("EndIsland");
                                                      StartIsland.getCuboidRegion().forEachBlock((blockState) -> {
                                                            blockState.setType(Material.AIR);
                                                      });
                                                      EndIsland.getCuboidRegion().forEachBlock((blockState) -> {
                                                            blockState.setType(Material.AIR);
                                                      });
                                                      Iterator var3 = node.getPlacedBlocks().iterator();

                                                      while(var3.hasNext()) {
                                                            Vector block = (Vector)var3.next();
                                                            TechniqueManager.g_world.getBlockAt(block.getBlockX(), block.getBlockY(), block.getBlockZ()).setType(Material.AIR);
                                                      }

                                                      node.getPlacedBlocks().clear();

                                                      var3 = node.getEntities().iterator();


                                                      while(var3.hasNext()) {
                                                            NPC npc = (NPC)var3.next();
                                                            npc.despawn();
                                                            npc.destroy();
                                                      }
                                                }
                                                catch (Exception ignored) {}

                                                node.getCurrentTechnique().getNodes().remove(node);
                                                Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] " + ChatColor.RED + player.getName() + " left " + node.getCurrentTechnique().getName());
                                                this.cancel();
                                                return;
                                          }

                                          task = (BukkitTask)var1.next();
                                    } while(!Bukkit.getScheduler().isCurrentlyRunning(task.getTaskId()) && !Bukkit.getScheduler().isQueued(task.getTaskId()));

                                    task.cancel();
                              }
                        }
                  }).runTaskTimer(BedwarsPractice.Get(), 0L, 1L);
            }
            catch(Exception exception)
            {
                  Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] " + ChatColor.DARK_RED + exception.getMessage());
            }

      }

}
