package com.ci.api.block;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DirectionSix extends ComponentBase{

    public DirectionSix(String name){super(name);}

    public static final PropertyInteger FACING = PropertyInteger.create("facing", 0, 5);

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.getDirectionFromEntityLiving(pos, placer);
        return this.getDefaultState().withProperty(FACING, getFacingNumber(enumfacing));
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
        if (enumFacing == EnumFacing.NORTH){
            b = 0;
        }else if (enumFacing == EnumFacing.SOUTH){
            b = 1;
        }else if (enumFacing == EnumFacing.WEST){
            b = 2;
        }else if (enumFacing == EnumFacing.EAST){
            b = 3;
        }else if (enumFacing == EnumFacing.UP){
            b = 4;
        }else{ b = 5; }
        return b;
    }

    public EnumFacing getFacing(int b){
        if (b == 0){
            return EnumFacing.NORTH;
        }else if (b == 1) {
            return EnumFacing.SOUTH;
        }else if (b == 2){
            return EnumFacing.WEST;
        }else if (b == 3){
            return EnumFacing.EAST;
        }else if (b == 4){
            return EnumFacing.UP;
        }else{ return EnumFacing.DOWN; }
    }

    public boolean canConnect(EnumFacing enumFacing, int i){
        return enumFacing == getFacing(i) || enumFacing == getFacing(i).getOpposite();
    }

}
