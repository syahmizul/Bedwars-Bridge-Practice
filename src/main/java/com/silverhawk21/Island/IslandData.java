package com.silverhawk21.Island;

import com.silverhawk21.ArenaNodes;
import com.silverhawk21.technique.TechniqueManager;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.HashMap;


// Island information/template to construct the real Island data afterwards
public abstract class IslandData {

      public abstract IslandType IslandType();
      public abstract Island CreateIsland(ArenaNodes node);
      public abstract void AssignSpecificPosition();

      protected static String MBStructureString;
      protected HashMap<IslandPositions, Location> Island_Location = new HashMap<>();

      public void setMBStructureString(String structureString)
      {
            MBStructureString = structureString;
      }

      public IslandData()
      {
            InitializePositions();
            AssignSpecificPosition();
      }

      public void InitializePositions()
      {
            for(IslandPositions position : IslandPositions.values())
            {
                  //Assign default values
                  Island_Location.put(position,new Location(TechniqueManager.g_world,0,0,0));
            }
      }


      /*private Vector World_Pos = new Vector();
      private Vector Offset = new Vector();
      private Vector OffsetFocal = new Vector();
      private Vector OffsetModify = new Vector();*/


     /* public IslandData copy() {
            test.get(IslandPositions.SPAWN_POS_ORIGIN)
            IslandData newIsland = new IslandData();
            newIsland.MBStructureString = this.MBStructureString;
            newIsland.MBStructure = MultiBlockStructure.create(this.MBStructureString, this.MBStructure.getName());
            newIsland.Offset.copy(this.Offset);
            return newIsland;
      }


      public void OffsetToWorldPos() {
            this.Offset.add(new Vector(this.World_Pos.getX() + 0.5D, this.World_Pos.getY(), this.World_Pos.getZ() + 0.5D));
      }*/


}
