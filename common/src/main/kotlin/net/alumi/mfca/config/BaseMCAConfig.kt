package net.alumi.mfca.config

import net.conczin.mca.Config

object BaseMCAConfig {
    val useMCAVoices: Boolean
        get() = Config.getInstance().useMCAVoices
}