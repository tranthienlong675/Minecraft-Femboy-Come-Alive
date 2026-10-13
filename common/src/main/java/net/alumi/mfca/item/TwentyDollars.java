package net.alumi.mfca.item;

import net.alumi.mfca.MFCA;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.entity.ai.relationship.Gender;
import net.conczin.mca.item.SpecialCaseGift;
import net.conczin.mca.item.TooltippedItem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;

public class TwentyDollars extends TooltippedItem implements SpecialCaseGift {
    public TwentyDollars(Properties properties) {
        super(properties);
    }

    public InteractionResult handle(
            ServerPlayer player,
            VillagerEntityMCA villager
    ) {
        if (player == null || villager == null) return InteractionResult.FAIL;
        if (villager.getGenetics().getGender() == Gender.FEMALE) {
            //cant turn a female villager to femboy
            player.displayClientMessage(Component.translatable("item.mfca.twenty_dollars.use_on_female"), true);
            return InteractionResult.FAIL;
        }
        if (villager.getTraits().hasTrait(MFCA.FEMBOY)) {
            villager.getTraits().removeTrait(MFCA.FEMBOY);
        } else {
            villager.getTraits().addTrait(MFCA.FEMBOY);
        }
        villager.randomizeClothes();
        villager.randomizeHair();
        return InteractionResult.CONSUME;
    }
}
