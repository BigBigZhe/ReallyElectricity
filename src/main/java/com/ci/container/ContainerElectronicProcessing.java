package com.ci.container;

import com.ci.loader.ItemsLoader;
import com.ci.tileentity.TileEntityElectronicProcessing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerElectronicProcessing extends Container {

    private TileEntityElectronicProcessing tile;
    private int [] cachedFields;

    public ContainerElectronicProcessing(InventoryPlayer invPlayer, TileEntityElectronicProcessing tile)
    {
        this.tile = tile;
        addSlotToContainer(new SlotBlank(tile, 0, 80, 26));
        addSlotToContainer(new Slots(tile, 1, 80, 44));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 66 + i * 18)); } }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 124)); }

    }

    public class Slots extends Slot{
        public Slots(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return true;
        }
    }

    public class SlotBlank extends Slot{
        public SlotBlank(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return true;
        }

        @Override
        public int getItemStackLimit(ItemStack stack) {
            return 1;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        boolean allFieldsHaveChanged = false;
        boolean fieldHasChanged [] = new boolean[tile.getFieldCount()];
        if (cachedFields == null) {
            cachedFields = new int[tile.getFieldCount()];
            allFieldsHaveChanged = true;
        }
        for (int i = 0; i < cachedFields.length; ++i) {
            if (allFieldsHaveChanged || cachedFields[i] != tile.getField(i)) {
                cachedFields[i] = tile.getField(i);
                fieldHasChanged[i] = true;
            }
        }

        // go through the list of listeners (players using this container) and update them if necessary
        for (IContainerListener listener : this.listeners) {
            for (int fieldID = 0; fieldID < tile.getFieldCount(); ++fieldID) {
                if (fieldHasChanged[fieldID]) {
                    // Note that although sendWindowProperty takes 2 ints on a server these are truncated to shorts
                    listener.sendWindowProperty(this, fieldID, cachedFields[fieldID]);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        tile.setField(id, data);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
        Slot sourceSlot = (Slot)inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (sourceSlotIndex >= 0 && sourceSlotIndex < 36) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!mergeItemStack(sourceStack, 36, 38, false)){
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (sourceSlotIndex >= 36 && sourceSlotIndex < 38) {
            // This is a TE slot so merge the stack into the players inventory
            if (!mergeItemStack(sourceStack, 0, 36, false)) {
                return ItemStack.EMPTY;   // EMPTY_ITEM
            }
        } else {
            System.err.print("Invalid slotIndex:" + sourceSlotIndex);
            return ItemStack.EMPTY;   // EMPTY_ITEM
        }

        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {  // getStackSize
            sourceSlot.putStack(ItemStack.EMPTY);  // EMPTY_ITEM
        } else {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onTake(player, sourceStack);  //onPickupFromSlot()
        return copyOfSourceStack;
    }
}

