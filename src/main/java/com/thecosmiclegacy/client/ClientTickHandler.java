package com.thecosmiclegacy.client;

import com.thecosmiclegacy.client.module.ModuleManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class ClientTickHandler {
    public static void init(ModuleManager moduleManager) {
        // Register a callback that fires every client tick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            moduleManager.onTick();
        });
    }
}

