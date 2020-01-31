package com.ci.blocks;

import com.ci.loader.BlocksLoader;
import com.ci.loader.CreativeTabsLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BandaBlock extends Block {
    public BandaBlock(String name) {
        super(Material.ROCK);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsLoader.TabAll);
        ItemsLoader.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        BlocksLoader.BLOCKS.add(this);
    }
}
