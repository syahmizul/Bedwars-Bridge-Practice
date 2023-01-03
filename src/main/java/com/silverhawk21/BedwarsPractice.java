package com.silverhawk21;

import com.silverhawk21.NPC.NPCTrait;
import com.silverhawk21.PacketListeners.ArmorStandListener;
import com.silverhawk21.commands.CommandManager;
import com.silverhawk21.listeners.GlobalListeners;
import com.silverhawk21.technique.TechniqueManager;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.NormalConfigBotListener;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.NormalConfigBotTrait;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

import net.citizensnpcs.Citizens;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.exception.NPCLoadException;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BedwarsPractice extends JavaPlugin {
      private static volatile BedwarsPractice instance = null;
      public static NPCRegistry registry;
      public static HashMap<UUID, BoundingSpace> g_Bounds = new HashMap<>();
      public static BedwarsPractice Get(){
            return instance;
      }

      public void onEnable() {

            instance = this;
            registry = CitizensAPI.createAnonymousNPCRegistry(new MemoryNPCDataStore());
            CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(NormalConfigBotTrait.class).withName("ConfigBot-Normal"));
            CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(NPCTrait.class).withName("NPCTrait"));
            TickrateProgress.Get();
            ProtocolLibrary.getProtocolManager().addPacketListener(new ArmorStandListener(this, PacketType.Play.Client.STEER_VEHICLE));

            TechniqueManager.Get();

            this.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] §aBedWarsPractice plugin has started!");
            this.getCommand("bwp").setExecutor(new CommandManager());

            this.getServer().getPluginManager().registerEvents(new GlobalListeners(), this);
            this.getServer().getPluginManager().registerEvents(TechniqueManager.Get(), this);
            this.getServer().getPluginManager().registerEvents(new NormalConfigBotListener(), this);

            TechniqueManager.Get().LoadTechnique();

            getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
      }

      public void onDisable() {
            this.getServer().getConsoleSender().sendMessage("§f[§bBWP§f] §4BedwarsPractice plugin has stopped!");
            TechniqueManager.Get().OnDisable();
            TechniqueManager.Get().SaveTechnique();
      }
}
