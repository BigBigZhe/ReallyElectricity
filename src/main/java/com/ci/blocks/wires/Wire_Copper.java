package com.ci.blocks.wires;

import com.ci.api.block.ComponentBase;
import com.ci.api.block.DirectionSix;
import com.ci.api.block.DirectionThree;
import com.ci.api.block.Wire;
import com.ci.blocks.components.Resistance;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Wire_Copper extends Wire {

    protected static final AxisAlignedBB[] Wire_CopperAABB = new AxisAlignedBB[]{
        new AxisAlignedBB(0.375D, 0.375D, 0.375D, 0.625D, 0.625D, 0.625D),
        new AxisAlignedBB(0.375D, 0.625D, 0.375D, 0.625D, 1.0D, 0.625D),
        new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D),
        new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 0.375D),
        new AxisAlignedBB(0.375D, 0.375D, 0.625D, 0.625D, 0.625D, 1.0D),
        new AxisAlignedBB(0.0D, 0.375D, 0.375D, 0.375D, 0.625D, 0.625D),
        new AxisAlignedBB(0.625D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D)};

    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyInteger LINKNUM = PropertyInteger.create("linknum", 0, 6);

    public Wire_Copper(String name){
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LINKNUM, 0)
                .withProperty(UP, Boolean.FALSE).withProperty(DOWN, Boolean.FALSE)
                .withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE)
                .withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.FALSE));
        this.disableStats();
        ItemsLoader.ITEMS.add(new ItemBlock(this).setRegistryName(name));
        BlocksLoader.BLOCKS.add(this);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return getAABB(state.getActualState(source, pos));
    }

    private static AxisAlignedBB getAABB(IBlockState state){
        boolean flag0 = state.getValue(UP);
        boolean flag1 = state.getValue(DOWN);
        boolean flag2 = state.getValue(NORTH);
        boolean flag3 = state.getValue(SOUTH);
        boolean flag4 = state.getValue(WEST);
        boolean flag5 = state.getValue(EAST);
        AxisAlignedBB AABB = Wire_CopperAABB[0];
        if (flag0){ AABB = AABB.union(Wire_CopperAABB[1]);}
        if (flag1){ AABB = AABB.union(Wire_CopperAABB[2]);}
        if (flag2){ AABB = AABB.union(Wire_CopperAABB[3]);}
        if (flag3){ AABB = AABB.union(Wire_CopperAABB[4]);}
        if (flag4){ AABB = AABB.union(Wire_CopperAABB[5]);}
        if (flag5){ AABB = AABB.union(Wire_CopperAABB[6]);}
        return AABB;
    }

    private Boolean getAttachPosition(IBlockAccess worldIn, BlockPos pos, EnumFacing direction) {
        BlockPos blockpos = pos.offset(direction);

        if (!canConnectTo(worldIn.getBlockState(blockpos), direction)) {
            return Boolean.FALSE;
        } else { return Boolean.TRUE; }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        int num = 0;
        if (this.getAttachPosition(worldIn, pos, EnumFacing.UP)){
            state = state.withProperty(UP, Boolean.TRUE); num++; }
        if (this.getAttachPosition(worldIn, pos, EnumFacing.DOWN)){
            state = state.withProperty(DOWN, Boolean.TRUE); num++; }
        if (this.getAttachPosition(worldIn, pos, EnumFacing.NORTH)){
            state = state.withProperty(NORTH, Boolean.TRUE); num++; }
        if (this.getAttachPosition(worldIn, pos, EnumFacing.SOUTH)){
            state = state.withProperty(SOUTH, Boolean.TRUE); num++; }
        if (this.getAttachPosition(worldIn, pos, EnumFacing.WEST)){
            state = state.withProperty(WEST, Boolean.TRUE); num++; }
        if (this.getAttachPosition(worldIn, pos, EnumFacing.EAST)){
            state = state.withProperty(EAST, Boolean.TRUE); num++; }
        state = state.withProperty(LINKNUM, num);
        return state;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean b) {
        if (!b){ state = this.getActualState(state, worldIn, pos); }
        addCollisionBoxToList(pos, entityBox, collidingBoxes, Wire_CopperAABB[0]);
        if (state.getValue(UP)){ addCollisionBoxToList(pos, entityBox, collidingBoxes, Wire_CopperAABB[1]); }
        if (state.getValue(DOWN)){ addCollisionBoxToList(pos, entityBox, collidingBoxes, Wire_CopperAABB[2]); }
        if (state.getValue(NORTH)){ addCollisionBoxToList(pos, entityBox, collidingBoxes, Wire_CopperAABB[3]); }
        if (state.getValue(SOUTH)){ addCollisionBoxToList(pos, entityBox, collidingBoxes, Wire_CopperAABB[4]); }
        if (state.getValue(WEST)){ addCollisionBoxToList(pos, entityBox, collidingBoxes, Wire_CopperAABB[5]); }
        if (state.getValue(EAST)){ addCollisionBoxToList(pos, entityBox, collidingBoxes, Wire_CopperAABB[6]); }
    }

    private void notifyWireNeighborsOfStateChange(World worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos).getBlock() == this) {
            worldIn.notifyNeighborsOfStateChange(pos, this, false);

            for (EnumFacing enumfacing : EnumFacing.values()) {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
            }
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote)
        {
            for (EnumFacing enumfacing : EnumFacing.Plane.VERTICAL) {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
            }

            for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
                this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing1));
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);

        if (!worldIn.isRemote)
        {
            for (EnumFacing enumfacing : EnumFacing.values()) {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
            }

            for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
                this.notifyWireNeighborsOfStateChange(worldIn, pos.offset(enumfacing1));
            }
        }
    }

    protected static boolean canConnectTo(IBlockState blockState, @Nullable EnumFacing side) {
        Block block = blockState.getBlock();
        if (block == BlocksLoader.WIRE_COPPER) {return true;}
        if (block == BlocksLoader.RESISTANCE10 || block == BlocksLoader.RESISTANCE20 || block == BlocksLoader.CAPACITOR) {
            DirectionThree directionThree = (DirectionThree) block;
            int meta = directionThree.getMetaFromState(blockState);
            return directionThree.canConnect(side, meta);
        }
        if (block == BlocksLoader.POWER || block == BlocksLoader.DIODE) {
            DirectionSix directionSix = (DirectionSix) block;
            int meta = directionSix.getMetaFromState(blockState);
            return directionSix.canConnect(side, meta);
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, UP, DOWN, EAST, NORTH, SOUTH, WEST, LINKNUM);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(LINKNUM, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(LINKNUM);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
        return EnumBlockRenderType.MODEL;
    }
}
