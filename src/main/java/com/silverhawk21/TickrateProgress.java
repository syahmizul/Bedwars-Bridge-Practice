package com.silverhawk21;

import com.avaje.ebean.Update;
import com.silverhawk21.technique.TechniqueManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class TickrateProgress {
    private static volatile TickrateProgress instance = null;
    public static String TickString;
    private int Tick = 1;
    public String UpdateProgressBar(float Percentage) {
        StringBuilder builder = new StringBuilder();
        int PercentageDone = Math.round(Percentage / 100.0F * 8.0F);
        int PercentageNotDone = Math.round(Math.abs(Percentage - 100.0F) / 100.0F * 8.0F);
        builder.setLength(0);

        int i;
        for(i = 0; i < PercentageDone; ++i) {
            builder.append(ChatColor.GREEN + "❯");
        }

        for(i = 0; i < PercentageNotDone; ++i) {
            builder.append(ChatColor.RED + "❯");
        }

        /*builder.append(ChatColor.WHITE + " ").append(Math.round(Percentage)).append("%");*/
        return builder.toString();
    }

    public static TickrateProgress Get(){
        if(instance == null)
        {
            synchronized (TickrateProgress.class)
            {
                if(instance == null)
                    instance = new TickrateProgress();
            }
        }
        return instance;
    }

    public TickrateProgress() {
        new BukkitRunnable()
        {
            @Override
            public void run() {
                // Was planning to use the mod method here but not wasting memory storing the tick counter,just resetting it using the
                // old-fashioned logical way..
                float tick = (Tick / 20.0f);
                TickString = UpdateProgressBar(tick * 100.0f);
                Tick = (Tick <= 20) ? Tick + 1 : 1;
            }
        }.runTaskTimer(BedwarsPractice.Get(),0L,1L);
    }
}
