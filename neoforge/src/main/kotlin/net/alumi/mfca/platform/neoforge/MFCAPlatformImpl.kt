package net.alumi.mfca.platform.neoforge

import net.neoforged.fml.loading.FMLPaths
import java.nio.file.Path

object MFCAPlatformImpl {
    @JvmStatic
    fun getConfigDirectory(): Path {
        return FMLPaths.CONFIGDIR.get()
    }
}