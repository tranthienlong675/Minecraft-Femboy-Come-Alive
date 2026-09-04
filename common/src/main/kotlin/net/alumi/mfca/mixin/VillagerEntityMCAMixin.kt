package net.alumi.mfca.mixin

import net.alumi.mfca.config.BaseMCAConfig
import net.alumi.mfca.FemboyItems
import net.alumi.mfca.MFCA
import net.alumi.mfca.config.MFCAConfigManager
import net.conczin.mca.entity.VillagerEntityMCA
import net.conczin.mca.entity.ai.Traits
import net.conczin.mca.entity.ai.relationship.AgeState
import net.conczin.mca.entity.ai.relationship.Gender
import net.conczin.mca.registry.SoundsMCA
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.phys.Vec3
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(VillagerEntityMCA::class)
class VillagerEntityMCAMixin {
    @Inject(
        method = ["finalizeSpawn"],
        at = [
            At(
                value = "INVOKE",
                target = "Lnet/conczin/mca/entity/VillagerEntityMCA;initialize(Lnet/minecraft/world/entity/MobSpawnType;)V",
                shift = At.Shift.AFTER
            )
        ]
    )
    private fun removeFemboyTraitIfFemale(
        level: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnType: MobSpawnType,
        groupData: SpawnGroupData?,
        cir: CallbackInfoReturnable<SpawnGroupData?>
    ) {
        this as VillagerEntityMCA
        if (genetics.gender == Gender.FEMALE &&
            traits.hasTrait(MFCA.FEMBOY)
        ) {
            traits.removeTrait(MFCA.FEMBOY)
        }
    }
    @Inject(
        method = ["getDeathSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboyDeathSound(cir: CallbackInfoReturnable<SoundEvent>) { //Nooooo
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            cir.returnValue = SoundsMCA.VILLAGER_FEMALE_SCREAM
        }
    }

    @Inject(
        method = ["getSurprisedSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboySurprisedSound(cir: CallbackInfoReturnable<SoundEvent>) {
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            cir.returnValue = SoundsMCA.VILLAGER_FEMALE_SURPRISE
        }
    }

    @Inject(
        method = ["getAmbientSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboyAmbientSound(cir: CallbackInfoReturnable<SoundEvent>) {
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            if (villager.ageState == AgeState.BABY) {
                cir.returnValue = SoundsMCA.VILLAGER_BABY_LAUGH
            }
            if (villager.isSleeping) {
                cir.returnValue = SoundsMCA.VILLAGER_FEMALE_SNORE;
            }
            if (villager.villagerBrain.isPanicking) {
                cir.returnValue = villager.deathSound
            }
            if (villager.isInfected && random.nextBoolean()) {
                cir.returnValue = SoundsMCA.VILLAGER_FEMALE_COUGH
            }
            if (villager.traits.hasTrait(Traits.SIRBEN)) {
                cir.returnValue = SoundsMCA.VILLAGER_FEMALE_SIRBEN
            }
            cir.returnValue = SoundsMCA.SILENT
        }
    }
    @Inject(
        method = ["getHurtSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboyHurtSound(cir: CallbackInfoReturnable<SoundEvent>) {
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            cir.returnValue = SoundsMCA.VILLAGER_FEMALE_HURT
        }
    }

    @Inject(
        method = ["playWelcomeSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboyWelcomeSound(cir: CallbackInfo) {
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            playSound(SoundsMCA.VILLAGER_FEMALE_GREET);
            cir.cancel()
        }
    }

    @Inject(
        method = ["getNotifyTradeSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboyNotifyTradeSound(cir: CallbackInfoReturnable<SoundEvent>) {
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            cir.returnValue = SoundsMCA.VILLAGER_FEMALE_YES
        }
    }

    @Inject(
        method = ["getNoSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboyNoSound(cir: CallbackInfoReturnable<SoundEvent>) {
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            cir.returnValue = SoundsMCA.VILLAGER_FEMALE_NO
        }
    }

    @Inject(
        method = ["playCelebrateSound"],
        at = [At("HEAD")],
        cancellable = true
    )
    private fun femboyCelebrateSound(cir: CallbackInfo) {
        val villager = this as VillagerEntityMCA
        if (BaseMCAConfig.useMCAVoices && MFCAConfigManager.get().useFemaleVoiceForFemboy && villager.traits.hasTrait(MFCA.FEMBOY)) {
            playSound(SoundsMCA.VILLAGER_FEMALE_CELEBRATE)
            cir.cancel()
        }
    }
}