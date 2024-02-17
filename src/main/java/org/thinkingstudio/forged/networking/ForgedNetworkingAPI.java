package org.thinkingstudio.forged.networking;

import net.fabricmc.fabric.impl.networking.CommonPacketsImpl;
import net.fabricmc.fabric.impl.networking.client.ClientNetworkingImpl;

import net.minecraft.SharedConstants;
import net.minecraft.server.command.DebugConfigCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
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
        CommonPacketsImpl.init();
        if (FMLLoader.getDist().isClient()) {
            ClientNetworkingImpl.clientInit();
        }

        if (isDevelopmentEnvironment()) {
            MinecraftForge.EVENT_BUS.addListener(this::registerCommand);
        }
    }

    public void registerCommand(RegisterCommandsEvent event) {
        if (SharedConstants.isDevelopment) {
            // Command is registered when isDevelopment is set.
            return;
        }

        if (!ForgedNetworkingAPI.isDevelopmentEnvironment()) {
            // Only register this command in a dev env
            return;
        }

        DebugConfigCommand.register(event.getDispatcher());
    }

    public static boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
}
