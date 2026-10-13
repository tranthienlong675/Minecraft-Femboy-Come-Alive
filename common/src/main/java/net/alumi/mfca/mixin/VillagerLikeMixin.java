package net.alumi.mfca.mixin;

import net.alumi.mfca.MFCA;
import net.conczin.mca.entity.VillagerLike;
import net.conczin.mca.entity.ai.Traits;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerLike.class)
public interface VillagerLikeMixin {
    @Inject(method = "canBeAttractedTo", at = @At("HEAD"), cancellable = true)
    private void attractionMixin(CallbackInfoReturnable<Boolean> cir) {
        VillagerLike villagerLike = (VillagerLike)(Object)this;
        Traits traits = villagerLike.getTraits();

        if (traits.hasTrait(MFCA.FEMBOY)) cir.setReturnValue(true);
    }
}
