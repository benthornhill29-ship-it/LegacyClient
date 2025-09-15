package com.thecosmiclegacy.client.module.impl;

import com.thecosmiclegacy.client.module.Module;
import com.thecosmiclegacy.client.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class ToggleSprint extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public ToggleSprint() {
        super("ToggleSprint", "Automatically keeps you sprinting", ModuleCategory.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (!isEnabled() || mc.player == null) return;

        ClientPlayerEntity player = mc.player;
        if (player.input != null && player.input.movementForward > 0 && !player.isSprinting()) {
            player.setSprinting(true);
        }
    }
}















