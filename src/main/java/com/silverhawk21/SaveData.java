package com.silverhawk21;

public class SaveData
{
    public String getIslandName() {
        return IslandName;
    }

    public void setIslandName(String islandName) {
        IslandName = islandName;
    }

    public String getMBStructureData() {
        return MBStructureData;
    }

    public void setMBStructureData(String MBStructureData) {
        this.MBStructureData = MBStructureData;
    }

    public double[] getStructureVectorData() {
        return StructureVectorData;
    }

    public void setStructureVectorData(double[] structureVectorData) {
        StructureVectorData = structureVectorData;
    }

    private String IslandName;
    private String MBStructureData;
    private double[] StructureVectorData;
}