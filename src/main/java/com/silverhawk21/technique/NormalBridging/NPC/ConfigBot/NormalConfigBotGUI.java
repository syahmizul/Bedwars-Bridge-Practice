package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot;

import com.silverhawk21.technique.BotInteraction;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.ChangeDirection.ChangeDirection;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.ChangeDistance.ChangeDistance;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.HitSimulate;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.MoveHeight.MoveHeight;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NormalConfigBotGUI {
      private final Inventory inv;
      private ArrayList<BotInteraction> interactions = new ArrayList<>();
      private InventoryHolder holder;

      public ArrayList<BotInteraction> getInteractions() {
            return this.interactions;
      }

      public void setInteractions(ArrayList<BotInteraction> interactions) {
            this.interactions = interactions;
      }

      public NormalConfigBotGUI(InventoryHolder holder) {
            this.inv = Bukkit.createInventory(holder, 9, "Configure this session.");
            this.initializeItems();
      }

      public void initializeItems() {
            this.interactions.add(new ChangeDistance());
            this.interactions.add(new MoveHeight());
            this.interactions.add(new ChangeDirection());
            /*this.interactions.add(new HitSimulate());*/

            for(BotInteraction interaction : interactions)
            {
                  this.inv.addItem(this.createGuiItem(interaction.getMaterial(), interaction.GetTitle(), interaction.LoreString()));
            }


      }

      protected ItemStack createGuiItem(final Material material,final String name,final String... lore) {
            ItemStack item = new ItemStack(material, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
            return item;
      }

      protected ItemStack createGuiItem(final Material material, final String name, final ArrayList<String> lore) {
            final ItemStack item = new ItemStack(material, 1);
            final ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
      }

      public void openInventory(HumanEntity ent) {
            ent.openInventory(this.inv);
      }
}
