/*
 * Copyright (c) 2020 Blue Minecraft Team.
 *
 * This file is part of HealthMod.
 *
 * HealthMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HealthMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HealthMod.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.blueminecraftteam.healthmod.blocks.entities

import io.github.blueminecraftteam.healthmod.client.gui.screen.BloodTestMachineScreenHandler
import io.github.blueminecraftteam.healthmod.inventories.SimpleBlockEntityInventory
import io.github.blueminecraftteam.healthmod.registries.BlockEntityTypeRegistries

class BloodTestMachineBlockEntity : SimpleBlockEntityInventory(
    type = BlockEntityTypeRegistries.BLOOD_TEST_MACHINE,
    menuFactory = ::BloodTestMachineScreenHandler,
    size = 3
)