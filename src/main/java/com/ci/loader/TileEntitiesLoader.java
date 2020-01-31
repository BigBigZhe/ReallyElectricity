package com.ci.loader;

import com.ci.ModMain;
import com.ci.tileentity.TileEntityBandaCore;
import com.ci.tileentity.TileEntityBandaUnderBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class TileEntitiesLoader {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        GameRegistry.registerTileEntity(TileEntityBandaCore.class, new ResourceLocation(ModMain.MODID, "bandacore"));
        GameRegistry.registerTileEntity(TileEntityBandaUnderBlock.class, new ResourceLocation(ModMain.MODID, "bandaunderblock"));
    }
}
