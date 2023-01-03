package com.silverhawk21;

public class CustomBlockData {
      private int BlockID;
      private byte BlockByteData;

      public int getBlockID() {
            return this.BlockID;
      }

      public byte getBlockByte() {
            return this.BlockByteData;
      }

      public CustomBlockData(int blockID, byte blockByteData) {
            this.BlockID = blockID;
            this.BlockByteData = blockByteData;
      }
}
