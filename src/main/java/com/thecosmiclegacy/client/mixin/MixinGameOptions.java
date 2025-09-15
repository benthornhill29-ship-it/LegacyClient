package com.thecosmiclegacy.client.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class MixinGameOptions {

    @Shadow
    private SimpleOption<Double> menuBackgroundBlurriness;

    // After options loaded, force that to 0
    @Inject(method = "load", at = @At("TAIL"))
    private void onOptionsLoad(CallbackInfo ci) {
        if (this.menuBackgroundBlurriness != null) {
            this.menuBackgroundBlurriness.setValue(0.0D);
        }
    }
}



