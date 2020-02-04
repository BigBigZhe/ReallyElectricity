package com.ci.api.tileentity;

import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;

public class TileEntityWire extends TileEntity {

    public int type;
    public int[] link;

    public TileEntityWire(int type){
        this.type = type;
        link = new int[12];
        setLink(link);
    }

    public void setLink(int[] b){
        Arrays.fill(b, 0);
    }

}
