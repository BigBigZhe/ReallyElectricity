package com.ci.blocks.components;

import com.ci.ModMain;
import com.ci.api.block.DirectionSix;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import com.ci.tileentity.TileEntityElectronicProcessing;
import com.ci.tileentity.TileEntityPower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Power extends DirectionSix {

    protected static final AxisAlignedBB NS =
            new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
    protected static final AxisAlignedBB WE =
            new AxisAlignedBB(0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
    protected static final AxisAlignedBB UD =
            new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

    public Power(String name){
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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
                                    EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityPower){
                TileEntityPower tileEntity = (TileEntityPower) tile;
                tileEntity.setAnodeFacing(this.getFacing(this.getMetaFromState(state)));
                tileEntity.setAnodePos();
                int i = tileEntity.ininin();
                System.out.println(i);
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPower(10, this.getFacing(this.getMetaFromState(state)));
    }

}
