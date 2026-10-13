package net.alumi.mfca.mixin;

import net.conczin.mca.network.c2s.VillagerEditorSyncRequest;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Predicate;

@Mixin(VillagerEditorSyncRequest.class)
public interface VillagerEditorSyncRequestInvoker {
    @Invoker("clearInvalidIdentifier")
    void invokeClearInvalidIdentifier(
             CompoundTag mcaData,
            CompoundTag fallbackMcaData,
            String key,
            Predicate<String> validator
    );
}
