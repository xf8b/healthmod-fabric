/*
 * Copyright (c) 2020 Blue Minecraft Team.
 *
 * This file is part of HealthMod.
 *
 * HealthMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HealthMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with HealthMod.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.blueminecraftteam.healthmod.registries

import dev.onyxstudios.cca.api.v3.component.ComponentKey
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer
import io.github.blueminecraftteam.healthmod.HealthMod
import io.github.blueminecraftteam.healthmod.components.BooleanComponent
import io.github.blueminecraftteam.healthmod.components.CleanlinessComponent
import io.github.blueminecraftteam.healthmod.components.IntComponent
import io.github.blueminecraftteam.healthmod.components.SanitizedWoundComponent
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy

class ComponentRegistries : EntityComponentInitializer {
    override fun registerEntityComponentFactories(registry: EntityComponentFactoryRegistry) {
        // add component to every PlayerEntity
        registry.registerForPlayers(SANITIZED_WOUND, { SanitizedWoundComponent() }, RespawnCopyStrategy.INVENTORY)
        registry.registerForPlayers(CLEANLINESS, ::CleanlinessComponent, RespawnCopyStrategy.LOSSLESS_ONLY)
        registry.registerForPlayers(WOUNDED, { WoundedComponent() }, RespawnCopyStrategy.LOSSLESS_ONLY)
    }

    companion object {
        @JvmStatic
        @get:JvmName("getSanitizedWound")
        val SANITIZED_WOUND: ComponentKey<BooleanComponent> = ComponentRegistryV3.INSTANCE.getOrCreate(
            HealthMod.id("sanitized_wound"),
            BooleanComponent::class.java
        )

        @JvmStatic
        @get:JvmName("getCleanliness")
        val CLEANLINESS: ComponentKey<IntComponent> = ComponentRegistryV3.INSTANCE.getOrCreate(
            HealthMod.id("cleanliness"),
            IntComponent::class.java
        )

        val WOUNDED: ComponentKey<BooleanComponent> = ComponentRegistryV3.INSTANCE.getOrCreate(
            HealthMod.id("wounded"),
            BooleanComponent::class.java
        )
    }
}