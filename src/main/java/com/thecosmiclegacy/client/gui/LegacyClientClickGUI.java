package com.thecosmiclegacy.client.gui;

import com.thecosmiclegacy.client.LegacyClientClient;
import com.thecosmiclegacy.client.module.ModuleCategory;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class LegacyClientClickGUI extends Screen {
    private static final int PIXEL_PANEL_W = 900;
    private static final int PIXEL_PANEL_H = 520;

    public LegacyClientClickGUI() {
        super(Text.literal("LegacyClient GUI"));
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        var mc = LegacyClientClient.MC;

        double scale = mc.getWindow().getScaleFactor();
        int scaledW = mc.getWindow().getScaledWidth();
        int scaledH = mc.getWindow().getScaledHeight();

        int panelW = (int) Math.round(PIXEL_PANEL_W / scale);
        int panelH = (int) Math.round(PIXEL_PANEL_H / scale);

        int x = (scaledW - panelW) / 2;
        int y = (scaledH - panelH) / 2;

        // 🔒 Solid background (no blur!)
        ctx.fill(0, 0, this.width, this.height, 0xFF101010);

        // Main panel
        ctx.fill(0, 0, this.width, this.height, 0x80000000);

        // Header
        int headerH = (int) Math.round(48 / scale);
        ctx.fill(x, y, x + panelW, y + headerH, 0xFF7A3CF5);

        // Title
        String title = "LEGACYCLIENT";
        int tw = mc.textRenderer.getWidth(title);
        int titleY = y + (int) Math.round(16 / scale);
        ctx.drawTextWithShadow(mc.textRenderer, title, x + (panelW - tw) / 2, titleY, 0xFFFFFFFF);

        // Left rail
        int leftW = (int) Math.round(220 / scale);
        ctx.fill(x, y + headerH, x + leftW, y + panelH, 0xFF1A1A1A);

        // Content area
        int contentX = x + leftW;
        ctx.fill(contentX, y + headerH, x + panelW, y + panelH, 0xFF121212);

        // Categories
        int catY = y + headerH + (int) Math.round(10 / scale);
        int catItemH = (int) Math.round(28 / scale);
        for (var cat : ModuleCategory.values()) {
            ctx.fill(x + 10, catY, x + leftW - 10, catY + catItemH, 0xFF232323);
            ctx.drawTextWithShadow(mc.textRenderer, cat.name(), x + 20, catY + 10, 0xFFE0E0E0);
            catY += catItemH + (int) Math.round(6 / scale);
        }

        // Modules
        int modY = y + headerH + 14;
        for (var m : LegacyClientClient.MODULE_MANAGER.getModules()) {
            String line = (m.isEnabled() ? "● " : "○ ") + m.getName();
            int color = m.isEnabled() ? 0xFF6CF56C : 0xFFB0B0B0;
            ctx.drawTextWithShadow(mc.textRenderer, line, contentX + 14, modY, color);
            modY += 14;
        }

        super.render(ctx, mouseX, mouseY, delta);
    }
}




