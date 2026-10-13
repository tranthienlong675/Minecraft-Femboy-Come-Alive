package net.alumi.mfca.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.alumi.mfca.platform.MFCAPlatform;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class MFCAConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static MFCAConfig config;

    private MFCAConfigManager() {
    }

    public static void init() {
        Path path = MFCAPlatform.getConfigDirectory().resolve("mfca.json");

        try {
            if (Files.exists(path)) {
                config = GSON.fromJson(Files.readString(path), MFCAConfig.class);
            } else {
                config = new MFCAConfig();
                save(path, config);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Failed to load MFCA config",
                    e
            );
        }
    }

    public static MFCAConfig get() {
        return config;
    }

    private static void save(Path path, MFCAConfig config) {
        try {
            Files.writeString(
                    path,
                    GSON.toJson(config)
            );
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Failed to save MFCA config",
                    e
            );
        }
    }
}

