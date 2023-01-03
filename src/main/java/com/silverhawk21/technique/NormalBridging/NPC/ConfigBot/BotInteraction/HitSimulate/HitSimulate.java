package com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.technique.BotInteraction;
import com.silverhawk21.technique.GUIOptions;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.Options.HitPreset1;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.Options.HitPreset2;
import com.silverhawk21.technique.NormalBridging.NPC.ConfigBot.BotInteraction.HitSimulate.Options.HitPreset3;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitTask;

public class HitSimulate extends BotInteraction {
      private BukkitTask currentHitTask = null;

      public void InitOptions() {
            this.getOptions().add(new HitPreset1());
            this.getOptions().add(new HitPreset2());
            this.getOptions().add(new HitPreset3());
            this.setCurrentOption((GUIOptions)this.getOptions().get(0));
      }

      public Material getMaterial() {
            return Material.DIAMOND_SWORD;
      }

      public void Perform(ArenaNodes node, Player player, InventoryClickEvent event) {
            try {
                  if (this.currentHitTask != null) {
                        this.currentHitTask.cancel();
                  }

                  this.currentHitTask = null;
                  CustomOption customOption;
                  int idx;
                  if (event.isLeftClick()) {
                        if (this.getCurrentOption().equals(this.getOptions().get(0))) {
                              this.setCurrentOption((GUIOptions)this.getOptions().get(this.getOptions().size() - 1));
                              customOption = (CustomOption)this.getCurrentOption();
                              customOption.Perform(node, player, event, this);
                        } else {
                              idx = this.getOptions().indexOf(this.getCurrentOption());
                              this.setCurrentOption((GUIOptions)this.getOptions().get(idx - 1));
                              customOption = (CustomOption)this.getCurrentOption();
                              customOption.Perform(node, player, event, this);
                        }
                  } else if (event.isRightClick()) {
                        if (this.getCurrentOption().equals(this.getOptions().get(this.getOptions().size() - 1))) {
                              this.setCurrentOption((GUIOptions)this.getOptions().get(0));
                              customOption = (CustomOption)this.getCurrentOption();
                              customOption.Perform(node, player, event, this);
                        } else {
                              idx = this.getOptions().indexOf(this.getCurrentOption());
                              this.setCurrentOption((GUIOptions)this.getOptions().get(idx + 1));
                              customOption = (CustomOption)this.getCurrentOption();
                              customOption.Perform(node, player, event, this);
                        }
                  }

                  this.UpdateItemStack(event);
            } catch (Exception var6) {
                  var6.printStackTrace();
            }

      }

      public String GetTitle() {
            return "Simulate hits";
      }

      public BukkitTask getCurrentHitTask() {
            return this.currentHitTask;
      }

      public void setCurrentHitTask(BukkitTask currentHitTask) {
            this.currentHitTask = currentHitTask;
      }
}
