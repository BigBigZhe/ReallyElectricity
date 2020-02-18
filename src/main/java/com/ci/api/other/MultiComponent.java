package com.ci.api.other;

import net.minecraft.util.math.BlockPos;

public class MultiComponent {

    public BlockPos[][] ComPos = new BlockPos[2][256];
    public int value;

    {
        value = 0;
    }

    public MultiComponent(){ init(); }

    public void init(){
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 256; j++){
                ComPos[i][j] = new BlockPos(0, 0, 0);
            }
        }
    }
}
