package net.alumi.mfca.mixin;

import net.alumi.mfca.MFCA;
import net.conczin.mca.entity.ai.Traits;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Traits.class)
public class TraitsMixin {
    @Inject(method = "getVerticalScaleFactor", at = @At("RETURN"), cancellable = true)
    private void verticalScaleMixin(CallbackInfoReturnable<Float> cir) {
        Traits self = (Traits)(Object) this;

        if (self.hasTrait(MFCA.FEMBOY)){
            cir.setReturnValue(0.9F);
        } else {
            cir.setReturnValue(1.0F);
        }
    }

    @Inject(method = "getHorizontalScaleFactor", at = @At("RETURN"), cancellable = true)
    private void horizontalScaleMixin(CallbackInfoReturnable<Float> cir) {
        Traits self = (Traits)(Object) this;

        if (self.hasTrait(MFCA.FEMBOY)){
            cir.setReturnValue(0.9F);
        } else {
            cir.setReturnValue(1.0F);
        }
    }
}

