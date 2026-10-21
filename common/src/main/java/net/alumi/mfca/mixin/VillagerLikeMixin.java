package net.alumi.mfca.mixin;

import net.alumi.mfca.MFCA;
import net.conczin.mca.entity.VillagerLike;
import net.conczin.mca.entity.ai.relationship.Gender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerLike.class)
public interface VillagerLikeMixin {
    @Inject(method = "canBeAttractedTo", at = @At("HEAD"), cancellable = true)
    private void attractionMixin(VillagerLike<?> other, CallbackInfoReturnable<Boolean> cir) {
        VillagerLike villager = (VillagerLike)(Object)this;

        if (villager.getTraits().hasTrait(MFCA.FEMBOY) || other.getTraits().hasTrait(MFCA.FEMBOY)) cir.setReturnValue(true);
    }

    @ModifyVariable(method = "randomizeHair", at = @At("STORE"), ordinal = 0)
    private Gender femboyHair(Gender gender) {
        VillagerLike villager = (VillagerLike)(Object)this;
        if (villager.getTraits().hasTrait(MFCA.FEMBOY)) {
            return Gender.FEMALE;
        } else {
            return gender;
        }
    }
}
