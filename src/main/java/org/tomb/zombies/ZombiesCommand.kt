package org.tomb.zombies

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.math.vector.Vector3d
import com.hypixel.hytale.math.vector.Vector3f
import com.hypixel.hytale.protocol.GameMode
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.hypixel.hytale.server.npc.NPCPlugin


/**
 * This is an example command that will simply print the name of the plugin in chat when used.
 */
class ZombiesCommand(pluginName: String?, pluginVersion: String?) :
    AbstractPlayerCommand("zombie", "Prints a test message from the $pluginName plugin.") {
    private val pluginName: String?
    private val pluginVersion: String?

    init {
        this.setPermissionGroup(GameMode.Adventure) // Allows the command to be used by anyone, not just OP
        this.pluginName = pluginName
        this.pluginVersion = pluginVersion
    }

    protected override fun execute(
        commandContext: CommandContext,
        store: Store<EntityStore>,
        playerEntityRef: Ref<EntityStore?>,
        playerRef: PlayerRef,
        world: World
    ) {
        // Get player position and offset for "near" spawn (e.g., 3 blocks forward on X)
        val playerPos: Vector3d = playerRef.transform.position
        val spawnPos: Vector3d = playerPos.add(3.0, 0.0, 0.0) // Adjust offset as needed
        val rotation = Vector3f(0F, 0F, 0F) // Facing forward

        // Spawn zombie NPC
        val result = NPCPlugin.get().spawnNPC(store, "Zombie", null, spawnPos, rotation)
        if (result != null) {
            val zombieRef: Ref<EntityStore?>? = result.first()
            val zombie = result.second()

            // Optional: Customize (e.g., make aggressive, add gear)
            // zombie.setAIState(AIState.AGGRESSIVE);
            playerRef.sendMessage(Message.raw("Zombie spawned nearby!"))
        } else {
            playerRef.sendMessage(Message.raw("Failed to spawn zombie."))
        }
    }

}