package com.ci.loader;

import javax.annotation.Nullable;

import com.ci.ModMain;
import com.ci.gui.*;
import com.ci.container.*;

import com.ci.tileentity.TileEntityBandaCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler{
	
	public GuiElementLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(ModMain.instance, this);
    }
	
	@Nullable
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof TileEntityBandaCore) {
			if(ID == 1) {
				TileEntityBandaCore tileEntityBandaCore = (TileEntityBandaCore)tileEntity;
				return new ContainerBandaCore(player.inventory, tileEntityBandaCore);
			}
		}
		System.out.print("NULL");
		return null;
	}

	@Nullable
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof TileEntityBandaCore) {
			if(ID == 1) {
				TileEntityBandaCore tileEntityBandaCore = (TileEntityBandaCore)tileEntity;
				return new GuiBandaCore(player.inventory, tileEntityBandaCore);
			}
		}
		System.out.print("NULL");
		return null;
	}

}
