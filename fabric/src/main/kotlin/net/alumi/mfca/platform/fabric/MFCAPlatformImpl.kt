package net.alumi.mfca.platform.fabric

import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Path

object MFCAPlatformImpl {
    @JvmStatic
    fun getConfigDirectory(): Path {
        return FabricLoader.getInstance().configDir
    }
}