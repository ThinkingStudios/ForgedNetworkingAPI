package org.thinkingstudio.forged.networking;

import net.minecraft.SharedConstants;
import net.minecraft.server.command.DebugConfigCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEventsHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
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
}
