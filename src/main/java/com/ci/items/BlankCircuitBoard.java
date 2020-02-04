package com.ci.items;

import com.ci.api.item.ItemBase;
import com.ci.loader.ItemsLoader;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class BlankCircuitBoard extends ItemBase {

    public BlankCircuitBoard(String name){
        super(name);
        this.setHasSubtypes(true);
        setMaxDamage(0);
        setNoRepair();
        ItemsLoader.ITEMS.add(this);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (this.isInCreativeTab(tab)) {
            for (EnumLinkType enumLinkType:EnumLinkType.values()){
                int meta = enumLinkType.getMetadata();
                list.add(new ItemStack(this, 1, meta));
            }
        }
    }

    public static enum EnumLinkType implements IStringSerializable{
        EMPTY(0, "None"),N(1, "North"),
        W(2, "West"),S(3, "South"),
        E(4, "East"),C(5, "Center"),
        V(6, "Vertical"),NS(7, "NorthSouth"),
        WE(8, "WestEast"),CV(9, "CenterVertical");

        private final int meta;
        private final String name;
        private static final EnumLinkType[] META_LOOKUP = new EnumLinkType[values().length];

        public int getMetadata()
        {
            return this.meta;
        }

        public static EnumLinkType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) { meta = 0; }
            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        private EnumLinkType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        static {
            for (EnumLinkType value : values()) {
                META_LOOKUP[value.getMetadata()] = value;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        int meta = stack.getMetadata();
        EnumLinkType e = EnumLinkType.byMetadata(meta);
        list.add(e.getName());
    }

}
