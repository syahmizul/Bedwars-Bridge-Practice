package com.silverhawk21.technique;

import com.silverhawk21.ArenaNodes;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUIOptions {
      String GetName();

      void Perform(ArenaNodes var1, Player var2, InventoryClickEvent var3);
}
