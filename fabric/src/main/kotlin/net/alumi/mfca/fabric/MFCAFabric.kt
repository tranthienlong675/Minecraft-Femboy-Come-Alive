package net.alumi.mfca.fabric

import net.alumi.mfca.MFCA
import net.alumi.mfca.config.MFCAConfigManager

import net.fabricmc.api.ModInitializer

class MFCAFabric: ModInitializer {
    override fun onInitialize() {
        MFCA.init()
        MFCAConfigManager.init()
        MCAFabricCreativeTab.init()
    }
    companion object {}
}