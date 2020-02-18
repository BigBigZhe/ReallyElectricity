package com.ci.api.block;

import com.ci.loader.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class Wire extends Block {

    public Wire(String name){
        super(Material.CIRCUITS);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabsLoader.TabAll);
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

}
