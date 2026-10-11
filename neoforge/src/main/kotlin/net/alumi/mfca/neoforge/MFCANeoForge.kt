package net.alumi.mfca.neoforge

import net.alumi.mfca.FemboyItems
import net.alumi.mfca.MFCA
import net.alumi.mfca.config.MFCAConfigManager
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent

@Mod(MFCA.MOD_ID)
class MFCANeoForge(modEventBus: IEventBus) {
    init {
        MFCA.init()
        MFCAConfigManager.init()
        //modEventBus.addListener(::addItems)
    }
    companion object {}
    @SubscribeEvent
    fun addItems(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey == MFCA.MCA_TAB) {
            event.accept(FemboyItems.TWENTY_DOLLARS.get())
        }
    }
}