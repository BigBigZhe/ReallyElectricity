package com.ci;

import com.ci.proxy.CCommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModMain.MODID, name = ModMain.NAME, version = ModMain.VERSION)
public class ModMain
{
    public static final String MODID = "reallyelectricitymod";
    public static final String NAME = "Really Electricity Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Instance(ModMain.MODID)
    public static ModMain instance;
    
    @SidedProxy(clientSide = "com.ci.proxy.CClientProxy",
            serverSide = "com.ci.proxy.CCommonProxy")
    public static CCommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
