package com.ci.proxy;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class CClientProxy extends CCommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event)
    {
		super.preInit(event);
    }
	@Override
    public void init(FMLInitializationEvent event)
    {
    	super.init(event);
    }
	@Override
    public void postInit(FMLPostInitializationEvent event)
    {
    	super.postInit(event);
    }
}
