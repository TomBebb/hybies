package org.tomb.zombies

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import javax.annotation.Nonnull

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
class ZombiesPlugin(@Nonnull init: JavaPluginInit) : JavaPlugin(init) {
    init {
        LOGGER.atInfo().log("Hello from ${this.name} version ${this.manifest.version}")
    }

    override fun setup() {
        LOGGER.atInfo().log("Setting up plugin ${this.name}")
        this.commandRegistry
            .registerCommand(ZombiesCommand(this.name, this.manifest.version.toString()))
    }

    companion object {
        private val LOGGER = HytaleLogger.forEnclosingClass()
    }
}