package com.silverhawk21.NPC;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.event.EventHandler;

public class NPCTrait extends Trait {
    public NPCTrait () {
        super("NPCTrait");
    }

    @EventHandler
    public void click(NPCRightClickEvent event) {
        if(event.getNPC() == this.getNPC())
            event.getClicker().performCommand("bwp join Normal");
    }
}
