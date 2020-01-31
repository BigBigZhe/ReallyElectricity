package com.ci.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBandaUnderBlock extends TileEntity{

    private boolean isConnect;

    public TileEntityBandaUnderBlock() {
        this.isConnect = false;
    }

    public void connect(){
        this.isConnect = true;
        markDirty();
    }

    public boolean isConnect() {
        return isConnect;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.isConnect = compound.getBoolean("isConnect");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setBoolean("isConnect", this.isConnect);
        return compound;
    }
}
