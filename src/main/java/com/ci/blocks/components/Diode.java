package com.ci.blocks.components;

import com.ci.api.block.DirectionSix;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class Diode extends DirectionSix {

    protected static final AxisAlignedBB NS =
            new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
    protected static final AxisAlignedBB WE =
            new AxisAlignedBB(0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
    protected static final AxisAlignedBB UD =
            new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

    public Diode(String name) {
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, 0));
        ItemsLoader.ITEMS.add(new ItemBlock(this).setRegistryName(name));
        BlocksLoader.BLOCKS.add(this);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        int facing = state.getValue(FACING);
        if (facing == 0 || facing == 1){
            return NS;
        }else if (facing == 2 || facing == 3){
            return WE;
        }
        return UD;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return getBoundingBox(state, worldIn, pos);
    }

}
