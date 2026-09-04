package net.alumi.mfca.mixin;

import net.alumi.mfca.MFCA;
import net.conczin.mca.entity.VillagerLike;
import net.conczin.mca.entity.ai.relationship.Gender;
import net.conczin.mca.resources.SkinSelection;
import net.conczin.mca.resources.data.skin.Clothing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Collection;
import java.util.Map;

@Mixin(SkinSelection.class)
public class SkinSelectionMixin {

    @ModifyVariable(
            method = "clothingForVillager",
            at = @At("STORE"),
            ordinal = 0
    )
    private static Gender clothingForFemboy(
            Gender gender,
            Collection<Clothing> available,
            VillagerLike<?> villager,
            Map<String, String> professionConversions
    ) {
        if (villager.getTraits().hasTrait(MFCA.INSTANCE.getFEMBOY())) {
            return Gender.FEMALE;
        }
        return gender;
    }
}
