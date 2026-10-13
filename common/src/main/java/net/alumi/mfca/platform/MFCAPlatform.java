package net.alumi.mfca.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public final class MFCAPlatform {
    @ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }
}
