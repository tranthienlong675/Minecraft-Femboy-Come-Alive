package net.alumi.mfca.mixin

import net.alumi.mfca.MFCA
import net.conczin.mca.entity.VillagerLike
import net.conczin.mca.entity.ai.relationship.Gender
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.ModifyVariable
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(VillagerLike::class)
interface VillagerLikeMixin {
    @Inject(
        method = ["canBeAttractedTo"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun attractionMixin(
        cir: CallbackInfoReturnable<Boolean>
    ) {
        val traits = (this as VillagerLike<*>).traits
        cir.returnValue = traits.hasTrait(MFCA.FEMBOY)
    }

    @ModifyVariable(
        method = ["randomizeHair"],
        at = At("STORE"),
        ordinal = 0
    )
    private fun femboyHair(
        gender: Gender,
    ): Gender {
        val villager = this as VillagerLike<*>
        return if (
            villager.traits.hasTrait(MFCA.FEMBOY)
        ) {
            Gender.FEMALE
        } else {
            gender
        }
    }
}