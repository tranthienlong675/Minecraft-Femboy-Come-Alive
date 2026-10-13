package net.alumi.mfca.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.alumi.mfca.MFCA;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.network.c2s.VillagerEditorSyncRequest;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Predicate;

@Mixin(VillagerEditorSyncRequest.class)
public class VillagerEditorSyncRequestMixin {
    @WrapWithCondition(method = "sanitizeVisualIdentifiers",
    at = @At(
            value = "INVOKE",
            target = "Lnet/conczin/mca/network/c2s/VillagerEditorSyncRequest;clearInvalidIdentifier(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/nbt/CompoundTag;Ljava/lang/String;Ljava/util/function/Predicate;)V",
            ordinal = 1
    ))
    private boolean shouldIgnoreClothesSanitization(
            VillagerEditorSyncRequest instance,
            CompoundTag villagerData,
            CompoundTag fallback,
            String key,
            Predicate<String> validator,
            @Local Entity entity
    ) {
        if (entity instanceof VillagerEntityMCA villager) {
            return !villager.getTraits().hasTrait(MFCA.FEMBOY);
        } else {
            return true;
        }
    }
}
