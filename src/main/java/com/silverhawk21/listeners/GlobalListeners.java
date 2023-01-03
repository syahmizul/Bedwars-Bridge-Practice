package com.silverhawk21.listeners;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.arena.ReJoin;
import com.andrei1058.bedwars.listeners.joinhandler.JoinHandlerCommon;
import com.andrei1058.bedwars.sidebar.BedWarsScoreboard;
import com.silverhawk21.BoundingSpace;
import com.silverhawk21.BedwarsPractice;
import com.silverhawk21.NPC.NPCTrait;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.NormalConfigBotTrait;
import com.silverhawk21.technique.TechniqueManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;

public class GlobalListeners implements Listener {

      private boolean isFirstTime = true;

      @EventHandler
      public void onPlayerInteract(PlayerInteractEvent e) {
            if (BedwarsPractice.g_Bounds.containsKey(e.getPlayer().getUniqueId()) && e.getPlayer() != null && e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equals("§l§f[§bBWP§f] §eBounds Wand")) {
                  if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                        ((BoundingSpace) BedwarsPractice.g_Bounds.get(e.getPlayer().getUniqueId())).setMin(e.getClickedBlock().getLocation().clone());
                        e.getPlayer().sendMessage("§l§f[§bBWP§f] §dFirst position set to (" + e.getClickedBlock().getLocation().getBlockX() + ", " + e.getClickedBlock().getLocation().getBlockY() + ", " + e.getClickedBlock().getLocation().getBlockZ() + ").");
                  } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        ((BoundingSpace) BedwarsPractice.g_Bounds.get(e.getPlayer().getUniqueId())).setMax(e.getClickedBlock().getLocation().clone());
                        e.getPlayer().sendMessage("§l§f[§bBWP§f] §dSecond position set to (" + e.getClickedBlock().getLocation().getBlockX() + ", " + e.getClickedBlock().getLocation().getBlockY() + ", " + e.getClickedBlock().getLocation().getBlockZ() + ").");
                  }

                  e.setCancelled(true);
            }

      }

      @EventHandler
      public void onBlockFromTo(BlockFromToEvent event) {
            if (event.getBlock().getWorld().getName().equals("Arena")) {
                  event.setCancelled(true);
            }

      }
      @EventHandler
      public void onBWJoinHandler(PlayerJoinEvent event)
      {
            final Player player = event.getPlayer();
            player.getInventory().clear();
            Bukkit.getScheduler().runTaskLater(BedwarsPractice.Get(), new Runnable() {
                  @Override
                  public void run() {
                        final Location configLoc = BedWars.config.getConfigLoc("lobbyLoc");
                        if (configLoc != null && configLoc.getWorld() != null)
                              player.teleport(configLoc, PlayerTeleportEvent.TeleportCause.PLUGIN);
                        Arena.sendLobbyCommandItems(player);
                        BedWarsScoreboard.giveScoreboard(player, null, true);
                        player.setHealthScale(player.getMaxHealth());
                        player.setExp(0.0f);
                        player.setHealthScale(20.0);
                        player.setFoodLevel(20);
                        player.updateInventory();

                  }
            },20L);
      }

      @EventHandler
      public void onChat(AsyncPlayerChatEvent event)
      {
            if(event.getPlayer().getWorld().equals(TechniqueManager.g_world))
            {
                  event.setCancelled(true);
            }
            TechniqueManager.g_world.getPlayers().forEach(event.getRecipients()::remove);
      }

      @EventHandler
      public void onFirstJoinReload(PlayerJoinEvent event)
      {
            if(isFirstTime)
            {
                  isFirstTime = false;
                  ReloadPlugin();
            }


      }

      private void ReloadPlugin()
      {
            for(int i = 0;i<2;i++)
            {
                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"citizens reload");
                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"pc reload");
            }
            Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] §aCitizens reloaded!");
            Bukkit.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] §aProCosmetics reloaded!");
      }

      @EventHandler
      public void onLobbyLoad(WorldLoadEvent event)
      {
            if(event.getWorld().getName().equals(BedWars.getLobbyWorld()))
            {
                 ReloadPlugin();
            }
      }
}
