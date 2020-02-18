package com.ci.loader;

import com.ci.ModMain;
import com.ci.tileentity.TileEntityElectronicProcessing;
import com.ci.tileentity.TileEntityPower;
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
        GameRegistry.registerTileEntity(TileEntityElectronicProcessing.class, new ResourceLocation(ModMain.MODID, "electronicprocessingstation"));
        GameRegistry.registerTileEntity(TileEntityPower.class, new ResourceLocation(ModMain.MODID, "power"));
    }
}
