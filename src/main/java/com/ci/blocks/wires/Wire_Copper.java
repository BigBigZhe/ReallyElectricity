package com.ci.blocks.wires;

import com.ci.api.block.Wire;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Wire_Copper extends Wire {

    protected static final AxisAlignedBB Wire_CopperAABB =
            new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 0.125D, 1.0D);

    public Wire_Copper(String name){
        super(name);
        ItemsLoader.ITEMS.add(new ItemBlock(this).setRegistryName(name));
        BlocksLoader.BLOCKS.add(this);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return Wire_CopperAABB;
    }

}
