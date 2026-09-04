package net.alumi.mfca.item

import net.alumi.mfca.MFCA
import net.conczin.mca.entity.VillagerEntityMCA
import net.conczin.mca.entity.ai.relationship.Gender
import net.conczin.mca.item.SpecialCaseGift
import net.conczin.mca.item.TooltippedItem
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult

class TwentyDollars(properties: Properties) : TooltippedItem(properties), SpecialCaseGift {
    override fun handle(
        player: ServerPlayer?,
        villager: VillagerEntityMCA?
    ): InteractionResult {
        if (player == null || villager == null) return InteractionResult.FAIL
        if (villager.genetics.gender == Gender.FEMALE) {
            //cant turn a female villager to femboy
            player.displayClientMessage(Component.translatable("item.mfca.twenty_dollars.use_on_female"), true)
            return InteractionResult.FAIL
        }
        if (villager.traits.hasTrait(MFCA.FEMBOY)) {
            villager.traits.removeTrait(MFCA.FEMBOY)
        } else {
            villager.traits.addTrait(MFCA.FEMBOY)
        }
        villager.randomizeClothes()
        villager.randomizeHair()
        return InteractionResult.CONSUME
    }
}