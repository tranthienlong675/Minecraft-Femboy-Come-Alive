package net.alumi.mfca;

import net.conczin.mca.MCA;
import net.conczin.mca.entity.ai.Traits;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MFCA {
    public static final String MOD_ID = "mfca";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final Traits.Trait FEMBOY = Traits.register(
            MCA.locate("femboy"),
            0.5f,
            0.5f
    );

    public static final ResourceKey<CreativeModeTab> MCA_TAB = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            MCA.locate("mca_tab")
    );

    public static void init() {
        FemboyItems.init();
    }
}
