package com.ci.blocks;

import com.ci.ModMain;
import com.ci.api.block.BlockBase;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import com.ci.tileentity.TileEntityElectronicProcessing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElectronicProcessingStation extends BlockBase {

    public ElectronicProcessingStation(String name){
        super(name);
        ItemsLoader.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        BlocksLoader.BLOCKS.add(this);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
        EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityElectronicProcessing){
                TileEntityElectronicProcessing tileEntity = (TileEntityElectronicProcessing)tile;
                playerIn.openGui(ModMain.instance, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityElectronicProcessing();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
