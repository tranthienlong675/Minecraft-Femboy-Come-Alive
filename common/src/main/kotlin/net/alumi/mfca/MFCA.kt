package net.alumi.mfca

import net.conczin.mca.MCA
import net.conczin.mca.entity.ai.Traits
import net.conczin.mca.entity.ai.relationship.Personality
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import org.apache.logging.log4j.LogManager

object MFCA {
    const val MOD_ID = "mfca"

    val LOGGER = LogManager.getLogger()

    val FEMBOY = Traits.register(
        MCA.locate("femboy"),
        2.0f,
        2.0f
    )

    val MCA_TAB: ResourceKey<CreativeModeTab> =
        ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            MCA.locate("mca_tab")
        )

    fun init() {
        FemboyItems.init()
    }
}