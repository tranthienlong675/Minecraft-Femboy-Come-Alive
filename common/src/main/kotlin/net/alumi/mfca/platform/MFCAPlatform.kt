package net.alumi.mfca.platform

import dev.architectury.injectables.annotations.ExpectPlatform
import java.nio.file.Path

object MFCAPlatform {
    @JvmStatic
    @ExpectPlatform
    fun getConfigDirectory(): Path {
        throw AssertionError()
    }
}