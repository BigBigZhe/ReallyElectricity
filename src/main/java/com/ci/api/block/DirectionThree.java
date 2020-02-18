package com.ci.api.block;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DirectionThree extends ComponentBase {

    public DirectionThree(String name){super(name);}

    public static final PropertyInteger FACING = PropertyInteger.create("facing", 0, 2);

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.getDirectionFromEntityLiving(pos, placer);
        int b;
        if (enumfacing == EnumFacing.NORTH || enumfacing == EnumFacing.SOUTH){
            b = 0;
        }else if (enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.EAST){
            b = 1;
        }else {b = 2;}
        return this.getDefaultState().withProperty(FACING, b);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING);
    }

    public int getFacingNumber(EnumFacing enumFacing){
        int b;
        if (enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH){
            b = 0;
        }else if (enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.EAST){
            b = 1;
        }else{ b = 2; }
        return b;
    }

    public EnumFacing getFacing(int b){
        if (b == 0){
            return EnumFacing.NORTH;
        }else if (b == 1){
            return EnumFacing.WEST;
        }else { return EnumFacing.UP; }
    }

    public boolean canConnect(EnumFacing enumFacing, int i){
        return enumFacing == getFacing(i) || enumFacing == getFacing(i).getOpposite();
    }

}
