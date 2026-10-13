package net.alumi.mfca.mixin;

import net.alumi.mfca.MFCA;
import net.alumi.mfca.config.BaseMCAConfig;
import net.alumi.mfca.config.MFCAConfigManager;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.entity.ai.Traits;
import net.conczin.mca.entity.ai.relationship.AgeState;
import net.conczin.mca.entity.ai.relationship.Gender;
import net.conczin.mca.registry.SoundsMCA;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntityMCA.class)
public abstract class VillagerEntityMCAMixin {
    @Inject(
            method = "finalizeSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/conczin/mca/entity/VillagerEntityMCA;initialize(Lnet/minecraft/world/entity/MobSpawnType;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void removeFemboyTraitIfFemale(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType spawnType,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (villager.getGenetics().getGender() == Gender.FEMALE
                && villager.getTraits().hasTrait(MFCA.FEMBOY)) {
            villager.getTraits().removeTrait(MFCA.FEMBOY);
        }
    }

    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void femboyDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_SCREAM);
        }
    }

    @Inject(method = "getSurprisedSound", at = @At("HEAD"), cancellable = true)
    private void femboySurprisedSound(CallbackInfoReturnable<SoundEvent> cir) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_SURPRISE);
        }
    }

    @Inject(method = "getAmbientSound", at = @At("HEAD"), cancellable = true)
    private void femboyAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            if (villager.getAgeState() == AgeState.BABY) {
                cir.setReturnValue(SoundsMCA.VILLAGER_BABY_LAUGH);
                return;
            }

            if (villager.isSleeping()) {
                cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_SNORE);
                return;
            }

            if (villager.getVillagerBrain().isPanicking()) {
                cir.setReturnValue(villager.getDeathSound());
                return;
            }

            if (villager.isInfected() && villager.getRandom().nextBoolean()) {
                cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_COUGH);
                return;
            }

            if (villager.getTraits().hasTrait(Traits.SIRBEN)) {
                cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_SIRBEN);
                return;
            }

            cir.setReturnValue(SoundsMCA.SILENT);
        }
    }

    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void femboyHurtSound(CallbackInfoReturnable<SoundEvent> cir) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_HURT);
        }
    }

    @Inject(method = "playWelcomeSound", at = @At("HEAD"), cancellable = true)
    private void femboyWelcomeSound(CallbackInfo ci) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            villager.playSound(SoundsMCA.VILLAGER_FEMALE_GREET);
            ci.cancel();
        }
    }

    @Inject(method = "getNotifyTradeSound", at = @At("HEAD"), cancellable = true)
    private void femboyNotifyTradeSound(CallbackInfoReturnable<SoundEvent> cir) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_YES);
        }
    }

    @Inject(method = "getNoSound", at = @At("HEAD"), cancellable = true)
    private void femboyNoSound(CallbackInfoReturnable<SoundEvent> cir) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            cir.setReturnValue(SoundsMCA.VILLAGER_FEMALE_NO);
        }
    }

    @Inject(method = "playCelebrateSound", at = @At("HEAD"), cancellable = true)
    private void femboyCelebrateSound(CallbackInfo ci) {
        VillagerEntityMCA villager = (VillagerEntityMCA) (Object) this;

        if (shouldUseFemaleVoice(villager)) {
            villager.playSound(SoundsMCA.VILLAGER_FEMALE_CELEBRATE);
            ci.cancel();
        }
    }

    private boolean shouldUseFemaleVoice(VillagerEntityMCA villager) {
        return BaseMCAConfig.useMCAVoice()
                && MFCAConfigManager.get().useFemaleVoiceForFemboy
                && villager.getTraits().hasTrait(MFCA.FEMBOY);
    }
}


