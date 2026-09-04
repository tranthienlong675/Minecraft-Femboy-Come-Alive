package net.alumi.mfca.config

import com.google.gson.GsonBuilder
import net.alumi.mfca.platform.MFCAPlatform
import java.nio.file.Files
import java.nio.file.Path

object MFCAConfigManager {

    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    private lateinit var config: MFCAConfig

    fun init() {
        val path = MFCAPlatform
            .getConfigDirectory()
            .resolve("mfca.json")

        config = if (Files.exists(path)) {
            gson.fromJson(
                Files.readString(path),
                MFCAConfig::class.java
            )
        } else {
            MFCAConfig().also {
                save(path, it)
            }
        }
    }

    fun get(): MFCAConfig = config

    private fun save(path: Path, config: MFCAConfig) {
        Files.writeString(
            path,
            gson.toJson(config)
        )
    }
}