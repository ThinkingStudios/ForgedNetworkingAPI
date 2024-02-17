package org.thinkingstudio.forged.networking;

import net.fabricmc.fabric.impl.networking.CommonPacketsImpl;
import net.fabricmc.fabric.impl.networking.client.ClientNetworkingImpl;

import net.minecraft.SharedConstants;
import net.minecraft.server.command.DebugConfigCommand;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

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
            NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, RegisterCommandsEvent.class, event -> {
                if (SharedConstants.isDevelopment) {
                    // Command is registered when isDevelopment is set.
                    return;
                }

                if (!ForgedNetworkingAPI.isDevelopmentEnvironment()) {
                    // Only register this command in a dev env
                    return;
                }

                DebugConfigCommand.register(event.getDispatcher());
            });
        }
    }

    public static boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
}
