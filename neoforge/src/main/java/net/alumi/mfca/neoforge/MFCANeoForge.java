package net.alumi.mfca.neoforge;

import net.alumi.mfca.MFCA;
import net.alumi.mfca.config.MFCAConfigManager;
import net.neoforged.fml.common.Mod;

@Mod(MFCA.MOD_ID)
public class MFCANeoForge {
    public MFCANeoForge() {
        MFCA.init();
        MFCAConfigManager.init();
    }
}
