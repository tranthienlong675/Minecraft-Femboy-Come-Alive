package net.alumi.mfca.mixin

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition
import com.llamalad7.mixinextras.sugar.Local
import net.alumi.mfca.MFCA
import net.conczin.mca.entity.VillagerEntityMCA
import net.conczin.mca.network.c2s.VillagerEditorSyncRequest
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import java.util.function.Predicate

@Mixin(VillagerEditorSyncRequest::class)
class VillagerEditorSyncRequestMixin {
    @WrapWithCondition(
        method = ["sanitizeVisualIdentifiers"],
        at = [At(
            value = "INVOKE",
            target = "Lnet/conczin/mca/network/c2s/VillagerEditorSyncRequest;clearInvalidIdentifier(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/nbt/CompoundTag;Ljava/lang/String;Ljava/util/function/Predicate;)V",
            ordinal = 1
        )]
    )
    private fun shouldIgnoreClothesSanitization(
        instance: VillagerEditorSyncRequest,
        villagerData: CompoundTag,
        fallback: CompoundTag,
        key: String,
        validator: Predicate<String>,
        @Local entity: Entity
    ): Boolean {
        return if (entity is VillagerEntityMCA) {
            !entity.traits.hasTrait(MFCA.FEMBOY)
        } else {
            true
        }
    }
}

