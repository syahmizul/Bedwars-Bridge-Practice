package com.silverhawk21;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class BoundingSpace {
      private Location Min = null;
      private Location Max = null;

      public Location getMin() {
            return this.Min;
      }

      public void SortMinMax() {
            if (this.Min != null && this.Max != null) {
                  Vector min_Vector = HelperFunctions.getMinimum(this.Min.toVector(), this.Max.toVector());
                  Vector max_Vector = HelperFunctions.getMaximum(this.Min.toVector(), this.Max.toVector());
                  this.Min.setX(min_Vector.getX());
                  this.Min.setY(min_Vector.getY());
                  this.Min.setZ(min_Vector.getZ());
                  this.Max.setX(max_Vector.getX());
                  this.Max.setY(max_Vector.getY());
                  this.Max.setZ(max_Vector.getZ());
            }

      }

      public BoundingSpace copy() {
            BoundingSpace newSpace = new BoundingSpace();

            try {
                  newSpace.setMin(this.Min);
                  newSpace.setMax(this.Max);
            } catch (NullPointerException var3) {
                  newSpace.Min = null;
                  newSpace.Max = null;
            }

            return newSpace;
      }

      public void setMin(Location min) {
            this.Min = min.clone();
      }

      public Location getMax() {
            return this.Max;
      }

      public void setMax(Location max) {
            this.Max = max.clone();
      }

      public BoundingSpace() {
            this.Min = null;
            this.Max = null;
      }
}
