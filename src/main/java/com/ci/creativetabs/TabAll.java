package com.ci.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TabAll extends CreativeTabs{
	public TabAll()
    {
        super("TabAll");
    }

	@Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.APPLE);
    }
}
