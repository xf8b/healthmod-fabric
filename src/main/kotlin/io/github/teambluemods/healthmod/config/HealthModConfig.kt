/*
 * Copyright (c) 2020, 2021 Team Blue.
 *
 * This file is part of HealthMod Fabric.
 *
 * HealthMod Fabric is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HealthMod Fabric is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with HealthMod Fabric.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.teambluemods.healthmod.config

import io.github.teambluemods.healthmod.HealthMod
import me.sargunvohra.mcmods.autoconfig1u.ConfigData
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry
import org.apache.logging.log4j.LogManager
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

/**
 * Config for HealthMod containing all the config settings. Field values can vary as the config can be set at runtime.
 */
@Config(name = HealthMod.MOD_ID)
class HealthModConfig : ConfigData {
    @ConfigEntry.Category("woundInfection")
    @ConfigEntry.Gui.Tooltip
    var bandageInfectionChance = 8

    @ConfigEntry.Category("woundInfection")
    @ConfigEntry.Gui.Tooltip
    var bandageInfectionChanceWhenHealthy = 16

    @ConfigEntry.Category("woundInfection")
    @ConfigEntry.Gui.Tooltip
    var damagedInfectionChance = 10

    @ConfigEntry.Category("woundInfection")
    @ConfigEntry.Gui.Tooltip
    var damagedInfectionChanceWhenHealthy = 25

    @ConfigEntry.Category("easterEggs")
    @ConfigEntry.Gui.Tooltip
    var extraSplashTexts = true

    @ConfigEntry.Category("other")
    @ConfigEntry.Gui.Tooltip
    var bacterialResistanceChance = 500

    /**
     * Validates the config. If there are any invalid values, this will set them to a valid value.
     */
    override fun validatePostLoad() {
        val logger = LogManager.getLogger()
        val properties = HealthModConfig::class.memberProperties
            .filterIsInstance<KMutableProperty1<HealthModConfig, Int>>()
            .filter { Number::class.isInstance(it.returnType) }

        for (field in properties) {
            if (field.get(this) < 1) {
                logger.warn("Invalid config option for ${field.name} (value ${field.get(this)}), defaulting to 1")
                field.set(this, 1)
            }
        }
    }
}