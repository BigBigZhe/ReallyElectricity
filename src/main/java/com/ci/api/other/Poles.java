package com.ci.api.other;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Poles {

    public EnumFacing facing;
    public BlockPos pos;

    public Poles(){}

    public Poles(EnumFacing enumFacing, BlockPos blockPos){
        this.facing = enumFacing;
        this.pos = blockPos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public void setFacing(EnumFacing facing) {
        this.facing = facing;
    }

    public EnumFacing getFacing() {
        return facing;
    }
}
