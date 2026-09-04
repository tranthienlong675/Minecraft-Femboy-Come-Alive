package net.alumi.mfca.mixin

import net.alumi.mfca.MFCA
import net.conczin.mca.entity.ai.Traits
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(Traits::class)
class TraitsMixin {
    @Inject(
        method = ["getVerticalScaleFactor"],
        at = [At("RETURN")],
        cancellable = true
    )
    private fun verticalScaleMixin(
        cir: CallbackInfoReturnable<Float>
    ) {
        val self = this as Traits

        cir.returnValue *= if (self.hasTrait(MFCA.FEMBOY)) 0.9F else 1f
    }

    @Inject(
        method = ["getHorizontalScaleFactor"],
        at = [At("RETURN")],
        cancellable = true
    )
    private fun horizontalScaleMixin(
        cir: CallbackInfoReturnable<Float>
    ) {
        val self = this as Traits

        cir.returnValue *= if (self.hasTrait(MFCA.FEMBOY)) 0.9F else 1f
    }
}