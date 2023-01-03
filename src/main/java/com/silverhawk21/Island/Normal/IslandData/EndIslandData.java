package com.silverhawk21.Island.Normal.IslandData;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.Island.Island;
import com.silverhawk21.Island.IslandData;
import com.silverhawk21.Island.IslandPositions;
import com.silverhawk21.Island.IslandType;
import com.silverhawk21.Island.Normal.Islands.EndIsland;
import com.silverhawk21.technique.TechniqueManager;
import org.bukkit.Location;
import redempt.redlib.multiblock.MultiBlockStructure;

import java.util.Vector;

public class EndIslandData extends IslandData{

    @Override
    public IslandType IslandType() {
        return IslandType.END_ISLAND;
    }

    @Override
    public Island CreateIsland(ArenaNodes node) {
        Island newIsland = new EndIsland();

        newIsland.setIslandType(IslandType());
        newIsland.setIslandLocation(IslandPositions.WORLD_POS_ORIGIN,node.getWorldPos()
                        .add(Island_Location.get(IslandPositions.NODE_RELATIVE_OFFSET))
                        .add(Island_Location.get(IslandPositions.WORLD_POS_OFFSET))
                        );
        newIsland.setIslandLocation(IslandPositions.SPAWN_POS_ORIGIN,Island_Location.get(IslandPositions.SPAWN_POS_OFFSET)
                .add(Island_Location.get(IslandPositions.WORLD_POS_ORIGIN)));

        MultiBlockStructure blockStructure = MultiBlockStructure.create(MBStructureString,IslandType().getIslandName());

        newIsland.setMBStructure(blockStructure);
        blockStructure.getRegion(Island_Location.get(IslandPositions.WORLD_POS_ORIGIN));
        newIsland.setCuboidRegion(blockStructure.getRegion(Island_Location.get(IslandPositions.WORLD_POS_ORIGIN)));
        return null;
    }

    @Override
    public void AssignSpecificPosition() {

    }


}
