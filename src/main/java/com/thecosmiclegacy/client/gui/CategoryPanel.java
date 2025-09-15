package com.thecosmiclegacy.client.gui;

import com.thecosmiclegacy.client.LegacyClientClient;
import com.thecosmiclegacy.client.module.Module;
import com.thecosmiclegacy.client.module.ModuleCategory;

import net.minecraft.client.gui.DrawContext;

import java.awt.Color;
import java.util.List;

public class CategoryPanel {
    private final ModuleCategory category;
    private final List<Module> modules;
    private final int x, y, width, headerHeight;

    public CategoryPanel(ModuleCategory category, int x, int y, int width, int headerHeight) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.headerHeight = headerHeight;

        this.modules = LegacyClientClient.MODULE_MANAGER.getModulesByCategory(category);
    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        int offset = y + headerHeight;

        // Draw category header box (purple branding)
        context.fill(x, y, x + width, y + headerHeight, new Color(60, 0, 80, 200).getRGB());
        context.drawTextWithShadow(LegacyClientClient.MC.textRenderer, category.name(),
                x + 5, y + 4, 0xFFFFFF);

        // Draw module boxes
        for (Module m : modules) {
            int boxHeight = 14;

            // Hover effect
            boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= offset && mouseY <= offset + boxHeight;
            int baseColor = m.isEnabled() ? new Color(90, 0, 160, 180).getRGB() : new Color(20, 20, 20, 180).getRGB();
            int color = hovered ? brighten(baseColor, 0.2f) : baseColor;

            // Draw box
            context.fill(x, offset, x + width, offset + boxHeight, color);
            context.drawTextWithShadow(LegacyClientClient.MC.textRenderer, m.getName(),
                    x + 5, offset + 3, 0xFFFFFF);

            offset += boxHeight + 2;
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int offset = y + headerHeight;
        for (Module m : modules) {
            int boxHeight = 14;
            if (mouseX >= x && mouseX <= x + width && mouseY >= offset && mouseY <= offset + boxHeight) {
                m.toggle();
                return true;
            }
            offset += boxHeight + 2;
        }
        return false;
    }

    private int brighten(int color, float factor) {
        Color c = new Color(color, true);
        int r = Math.min(255, (int)(c.getRed() * (1 + factor)));
        int g = Math.min(255, (int)(c.getGreen() * (1 + factor)));
        int b = Math.min(255, (int)(c.getBlue() * (1 + factor)));
        return new Color(r, g, b, c.getAlpha()).getRGB();
    }
}


