package com.silverhawk21.commands.subcommands;

import com.silverhawk21.NPC.NPCTrait;
import com.silverhawk21.commands.SubCommands;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class AddNPCCommand extends SubCommands {
    @Override
    public void RegisterCommands() {

    }

    @Override
    public String getName() {
        return "addnpc";
    }

    @Override
    public String getDescription() {
        return "Adds a NPC for user interaction.";
    }

    @Override
    public String getSyntax() {
        return "/bwp addnpc";
    }

    @Override
    public void perform(Player player, String[] args) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "[§a§lNEW§r] §eBridging Practice");
        npc.spawn(player.getLocation());
        npc.getOrAddTrait(NPCTrait.class);
        ((SkinTrait)npc.getOrAddTrait(SkinTrait.class)).setSkinPersistent("BOT", "amKejLWVAowk6xO/GebsLUotVt7voSc0OhR3UHbbuUiOlzLrPrzTsDC55MTnvXH5HefrUwLL8zpxmYXa6jnrgqLcPd9/RZO/BVcO7QwYjLXGceqGg6+YkB0wf2fV99YdQWYtfEWRfaNejeeOOzDR5Ju6lozTjFTzcujA2HtoMeEPu/Jrf9+Gjcmj3kFj6BfEvbuMex+PGV7jHSVbZpkVgbYwNEblyWkBmeS17ea2qDuNKL1r5WhW8VD2s9ZdRX0EM5u0dyMaDYcfW7y7YF1EuKKuJCvsUeacldIU+ObvFOkINWlZs2jmkaRoAY2XWagiiCcrMtCrKUuKYctc/XDOk7eMh35rXOQ5aygiIiGwQRNGLtZir37X4c2Q/VzFBht6RE2xQMXuyxwOF1zpwOTuvAO6zsmzIdm0cc77qHA4CGYVlAScLMgDUT789JY27PkAWNmRufMf5g6Fzv64hIicDZ3PQVwv6TYqhh5ZAufhxlSxExjMdkGWyOtzCH7Dx1bWTh9k9Z/ZmGe46pMjaS/hMbfbXwYUPz4zojLKVwaKfaNVNvrrBs1z1fctZwSqFOJ1Uj/xuEdgzwnCOFKr5KmavYAiIreFd95ZzY7YTODYBtSooTVwjvqnLSbajUbJ0AFOfHSGYoAA6L/Aw9DXReB/Q0GhFa+2akMUafEitc/k0zI=", "ewogICJ0aW1lc3RhbXAiIDogMTYyNjU5ODAwNTk3NywKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzJiYWE2NGY1N2RjNjdkZDU3ZTExZTk4ODg4Njg3YmU4NWRjZDNiMmU2ODNlYjAxOWU5ZThhYmQzOTI2Yjc3IgogICAgfQogIH0KfQ==");
        ((LookClose)npc.getOrAddTrait(LookClose.class)).lookClose(true);
    }
}
