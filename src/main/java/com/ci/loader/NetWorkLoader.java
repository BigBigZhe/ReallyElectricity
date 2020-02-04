package com.ci.loader;

import com.ci.ModMain;
import com.ci.network.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetWorkLoader {

    public static SimpleNetworkWrapper instance =
            NetworkRegistry.INSTANCE.newSimpleChannel(ModMain.MODID);

    public NetWorkLoader() {
        instance.registerMessage(MessageLink.Handle.class, MessageLink.class, 1, Side.SERVER);
    }
}
