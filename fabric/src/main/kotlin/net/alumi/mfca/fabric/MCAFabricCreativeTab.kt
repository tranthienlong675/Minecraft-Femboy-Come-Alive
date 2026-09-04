package net.alumi.mfca.fabric

import net.alumi.mfca.FemboyItems
import net.alumi.mfca.MFCA
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents

object MCAFabricCreativeTab {
    fun init() {
        ItemGroupEvents
            .modifyEntriesEvent(MFCA.MCA_TAB)
            .register { entries ->
                entries.accept(FemboyItems.TWENTY_DOLLARS.get())
            }
    }
}