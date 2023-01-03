package com.silverhawk21.Island;

import java.util.HashMap;

public enum IslandType {


    START_ISLAND("StartIsland"),
    END_ISLAND("EndIsland");

    private final String IslandName;

    IslandType(String islandName) {
        this.IslandName = islandName;
    }

    public String getIslandName()
    {
        return this.IslandName;
    }

}
