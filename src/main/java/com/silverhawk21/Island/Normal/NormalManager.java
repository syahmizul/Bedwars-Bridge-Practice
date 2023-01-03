package com.silverhawk21.Island.Normal;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.Island;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.Island.Normal.IslandData.EndIslandData;
import com.silverhawk21.Island.Normal.IslandData.StartIslandData;
import com.silverhawk21.Island.Manager;
import com.silverhawk21.Island.Normal.Islands.EndIsland;
import com.silverhawk21.Island.Normal.Islands.StartIsland;

public class NormalManager extends Manager {



    @Override
    public String TechniqueName() {
        return "Normal";
    }

    @Override
    public void InitializeIslandsData() {
        islandDataList.add(new StartIslandData());
        islandDataList.add(new EndIslandData());
    }

    @Override
    public void GenerateIslands(ArenaNodes node) {
        for(IslandData islandData : islandDataList)
        {

        }
    }


}
