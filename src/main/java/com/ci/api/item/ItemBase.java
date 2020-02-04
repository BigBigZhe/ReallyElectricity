package com.ci.api.item;

import com.ci.loader.CreativeTabsLoader;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String name){
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabsLoader.TabAll);
    }

}
