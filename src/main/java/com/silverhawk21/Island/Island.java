package com.silverhawk21.Island;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import redempt.redlib.multiblock.MultiBlockStructure;
import redempt.redlib.region.CuboidRegion;

import java.util.HashMap;

public abstract class Island {



    private IslandType islandType;
    protected MultiBlockStructure MBStructure;
    protected CuboidRegion cuboidRegion;
    protected HashMap<IslandPositions, Location> Island_Location = new HashMap<>();














    public IslandType getIslandType() {
        return islandType;
    }
    public void setIslandType(IslandType islandType) {
        this.islandType = islandType;
    }
    public MultiBlockStructure getMBStructure() {
        return MBStructure;
    }
    public void setMBStructure(MultiBlockStructure MBStructure) {
        this.MBStructure = MBStructure;
    }
    public CuboidRegion getCuboidRegion() {
        return cuboidRegion;
    }
    public void setCuboidRegion(CuboidRegion cuboidRegion) {
        this.cuboidRegion = cuboidRegion;
    }
    public void setIslandLocation(IslandPositions position,Location location) {
        Island_Location.put(position,location);
    }
    public Location getIslandPosition(IslandPositions position)
    {
        return Island_Location.get(position);
    }
}
