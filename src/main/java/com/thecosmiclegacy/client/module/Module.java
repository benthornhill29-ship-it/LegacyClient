package com.thecosmiclegacy.client.module;

public abstract class Module {
    private final String name;
    private final String description;
    private final ModuleCategory category;
    private boolean enabled;

    public Module(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public ModuleCategory getCategory() { return category; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) onEnable(); else onDisable();
    }
    public void toggle() { setEnabled(!enabled); }

    public void onTick() {}
    public void onEnable() {}
    public void onDisable() {}
}








