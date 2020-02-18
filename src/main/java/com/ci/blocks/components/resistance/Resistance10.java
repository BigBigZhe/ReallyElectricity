package com.ci.blocks.components.resistance;

import com.ci.blocks.components.Resistance;
import com.ci.items.BlankCircuitBoard;
import com.ci.loader.BlocksLoader;
import com.ci.loader.ItemsLoader;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class Resistance10 extends Resistance {

    public Resistance10(String name, int value) {
        super(name);
        this.value = value;
        ItemsLoader.ITEMS.add(new ItemBlock(this).setRegistryName(name));
        BlocksLoader.BLOCKS.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.add("Value"+ ":" + 10);
    }
}
