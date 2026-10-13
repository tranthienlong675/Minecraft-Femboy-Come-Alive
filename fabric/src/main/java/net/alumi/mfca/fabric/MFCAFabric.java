package net.alumi.mfca.fabric;

import net.alumi.mfca.MFCA;
import net.alumi.mfca.config.MFCAConfigManager;
import net.fabricmc.api.ModInitializer;

public class MFCAFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MFCA.init();
        MFCAConfigManager.init();
    }
}
