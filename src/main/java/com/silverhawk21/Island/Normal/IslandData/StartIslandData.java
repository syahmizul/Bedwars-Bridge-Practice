package com.silverhawk21.Island.Normal.IslandData;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.Island;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.Island.IslandType;
import com.silverhawk21.Island.Normal.Islands.StartIsland;

public class StartIslandData extends IslandData{
    @Override
    public IslandType IslandType() {
        return IslandType.START_ISLAND;
    }

    @Override
    public Island CreateIsland(ArenaNodes node) {
        return null;
    }

    @Override
    public void AssignSpecificPosition() {

    }


}
