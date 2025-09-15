// LegacyClientClient.java
package com.thecosmiclegacy.client;

import com.thecosmiclegacy.client.gui.LegacyClientClickGUI;
import com.thecosmiclegacy.client.module.ModuleManager;
import com.thecosmiclegacy.client.module.impl.ToggleSprint;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LegacyClientClient implements ClientModInitializer {
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static final MinecraftClient MC = MinecraftClient.getInstance();

    private static KeyBinding toggleSprintKey;
    private static KeyBinding openGuiKey;
    private static ToggleSprint toggleSprint;

    @Override
    public void onInitializeClient() {
        System.out.println("[LegacyClient] Initialized!");

        // Register modules
        toggleSprint = new ToggleSprint();
        MODULE_MANAGER.register(toggleSprint);

        // Register keybinds
        toggleSprintKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.legacyclient.togglesprint",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.legacyclient"
        ));

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.legacyclient.opengui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.legacyclient"
        ));

        // Tick listener
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // ToggleSprint toggle
            while (toggleSprintKey.wasPressed()) {
                toggleSprint.toggle();
            }

            // GUI toggle
            while (openGuiKey.wasPressed()) {
                client.setScreen(new LegacyClientClickGUI());
            }

            MODULE_MANAGER.onTick();
        });
    }
}












