package com.ci.blocks.components;

import com.ci.api.block.BlockBase;
import com.ci.api.block.ComponentBase;
import com.ci.api.block.DirectionThree;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Resistance extends DirectionThree {

    protected static final AxisAlignedBB NS =
            new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
    protected static final AxisAlignedBB WE =
            new AxisAlignedBB(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);
    protected static final AxisAlignedBB UD =
            new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

    public int value;

    public Resistance(String name){
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, 0));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (state.getValue(FACING) == 0){
            return NS;
        }else if (state.getValue(FACING) == 1){return WE;}
        return UD;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return getBoundingBox(state, worldIn, pos);
    }

}
