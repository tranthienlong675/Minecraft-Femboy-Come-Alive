package net.alumi.mfca

import dev.architectury.registry.registries.DeferredRegister
import net.alumi.mfca.item.TwentyDollars
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import com.google.common.base.Supplier
import dev.architectury.registry.registries.RegistrySupplier

object FemboyItems {
    private val ITEMS: DeferredRegister<Item> = DeferredRegister.create(MFCA.MOD_ID, Registries.ITEM)

    val TWENTY_DOLLARS = register("twenty_dollars") { TwentyDollars(Item.Properties()) }

    fun init() {
        ITEMS.register()
    }

    fun register(name: String, item: Supplier<Item>): RegistrySupplier<Item> {
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(MFCA.MOD_ID, name), item)
    }
}