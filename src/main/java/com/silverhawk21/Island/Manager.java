package com.silverhawk21.Island;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.SaveData;

import java.util.ArrayList;

public abstract class Manager {
    protected ArrayList<IslandData> islandDataList = new ArrayList<>();

    public abstract String TechniqueName();
    public abstract void InitializeIslandsData();

    public abstract void GenerateIslands(ArenaNodes node);
    public ArrayList<IslandData> getIslandDataList() {
        return islandDataList;
    }
    public void LoadIslandsData(SaveData data)
    {
        for(IslandData islandData : islandDataList)
        {
            if(islandData.IslandType().getIslandName().equals(data.getIslandName()))
            {
                islandData.setMBStructureString(data.getMBStructureData());

            }

        }
    }

}
