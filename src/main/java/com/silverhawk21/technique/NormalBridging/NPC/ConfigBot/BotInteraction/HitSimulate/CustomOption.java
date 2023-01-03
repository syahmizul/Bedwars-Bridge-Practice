package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.technique.GUIOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class CustomOption implements GUIOptions {
      public abstract void Perform(ArenaNodes var1, Player var2, InventoryClickEvent var3, HitSimulate var4);
}
