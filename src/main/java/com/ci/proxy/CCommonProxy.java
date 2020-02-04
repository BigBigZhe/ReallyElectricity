package com.ci.proxy;

import com.ci.loader.GuiElementLoader;
import com.ci.loader.CreativeTabsLoader;

import com.ci.loader.NetWorkLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@EventBusSubscriber
public class CCommonProxy {
	
	public void preInit(FMLPreInitializationEvent event)
    {
		  new CreativeTabsLoader();
          new GuiElementLoader();
          new NetWorkLoader();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
