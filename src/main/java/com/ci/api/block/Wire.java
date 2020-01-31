package com.ci.api.block;

import com.ci.loader.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;

public class Wire extends Block {

    protected static final AxisAlignedBB[] Wire_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.375D, 0.625D, 0.8125D, 0.8125D, 1.0D)};

    public Wire(String name){
        super(Material.ROCK);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsLoader.TabAll);
    }
}
