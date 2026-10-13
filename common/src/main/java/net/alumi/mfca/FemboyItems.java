package net.alumi.mfca;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.alumi.mfca.item.TwentyDollars;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public final class FemboyItems {
    private static DeferredRegister<Item> ITEMS = DeferredRegister.create(MFCA.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> TWENTY_DOLLARS = register("twenty_dollars",
                    () -> new TwentyDollars(new Item.Properties()));


    static RegistrySupplier<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(ResourceLocation.fromNamespaceAndPath(MFCA.MOD_ID, name), item);
    }

    public static void init() {
        ITEMS.register();
    }
}
