package com.ci.api.other;

import com.ci.api.block.DirectionSix;
import com.ci.api.block.DirectionThree;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class ComponentList {

    private static final int DThree = 0;
    private static final int DSix = 1;
    private DirectionThree directionThree = new DirectionThree("");
    private DirectionSix directionSix = new DirectionSix("");
    public boolean isShortCircuit = false;
    public EnumFacing facing;
    public BlockPos pos;
    public int value;
    public int type;

    public ComponentList(){
        this.facing = EnumFacing.NORTH;
        this.pos = BlockPos.ORIGIN;
        this.value = -1;
        this.type = -1;
    }

    public ComponentList(EnumFacing enumFacing, BlockPos blockPos, DirectionThree dThree, int value){
        this.facing = enumFacing;
        this.pos = blockPos;
        this.type = DThree;
        this.value = value;
        this.directionThree = dThree;
    }

    public ComponentList(EnumFacing enumFacing, BlockPos blockPos, DirectionSix dSix, int value){
        this.facing = enumFacing;
        this.pos = blockPos;
        this.value = value;
        this.type = DSix;
        this.directionSix = dSix;
    }

    @Override
    public String toString() {
        return type + ","+ value;
    }
}
