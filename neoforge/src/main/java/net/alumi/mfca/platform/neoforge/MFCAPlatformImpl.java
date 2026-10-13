package net.alumi.mfca.platform.neoforge;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class MFCAPlatformImpl {
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
