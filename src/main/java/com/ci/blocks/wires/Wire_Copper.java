package com.ci.blocks.wires;

import com.ci.api.block.Wire;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;

public class Wire_Copper extends Wire {

    protected static final AxisAlignedBB[] Wire_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.375D, 0.625D, 0.8125D, 0.8125D, 1.0D)};

    public Wire_Copper(String name){
        super(name);
        ItemsLoader.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        BlocksLoader.BLOCKS.add(this);
    }

}
