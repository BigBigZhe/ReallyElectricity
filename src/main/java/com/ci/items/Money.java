package com.ci.items;

import com.ci.loader.CreativeTabsLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.item.Item;

public class Money extends Item {

    private int value;

    public Money(String name, int value){
        this.value = value;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsLoader.TabAll);
    }

    public int getValue() {
        return value;
    }
}
