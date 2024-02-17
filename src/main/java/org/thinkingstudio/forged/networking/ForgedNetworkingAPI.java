package org.thinkingstudio.forged.networking;

import net.fabricmc.fabric.impl.networking.CommonPacketsImpl;
import net.fabricmc.fabric.impl.networking.client.ClientNetworkingImpl;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ForgedNetworkingAPI.MODID)
public class ForgedNetworkingAPI {
    public static final String MODID = "forgednetworking";
    public static final String MODNAME = "ForgedNetworkingAPI";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODNAME);

    public ForgedNetworkingAPI() {
        if (FMLLoader.getDist().isClient()) {
            ClientNetworkingImpl.clientInit();
        }
        CommonPacketsImpl.init();
    }

    public static boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
}
