package net.alumi.mfca.config;


import net.conczin.mca.Config;

public final class BaseMCAConfig {
    public static boolean useMCAVoice() {
        return Config.getInstance().useMCAVoices;
    }
}
