package com.ci.loader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventsLoader {
	public EventsLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
	public void onCropBreak(BreakEvent event) 
	{


	}
	
}
