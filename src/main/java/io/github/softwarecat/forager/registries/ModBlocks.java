/*
 * Copyright © 2022 Bowen Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.softwarecat.forager.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    protected static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    protected static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block, Item.Properties itemProperties) {
        RegistryObject<T> registryBlock = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(registryBlock.get(), itemProperties));
        return registryBlock;
    }

    public static void register() {

    }
}
