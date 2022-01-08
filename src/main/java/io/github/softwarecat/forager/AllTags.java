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

package io.github.softwarecat.forager;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import io.github.softwarecat.forager.foundation.data.ForagerRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Function;

public class AllTags {

    private static final ForagerRegistrate REGISTRATE = Forager.getRegistrate().creativeModeTab(() -> Forager.TAB_FORAGER);

    public static <T> Tag.Named<T> tag(Function<ResourceLocation, Tag.Named<T>> wrapperFactory, String namespace,
                                       String path) {
        return wrapperFactory.apply(new ResourceLocation(namespace, path));
    }

    public static <T> Tag.Named<T> forgeTag(Function<ResourceLocation, Tag.Named<T>> wrapperFactory, String path) {
        return tag(wrapperFactory, "forge", path);
    }

    public static Tag.Named<Block> forgeBlockTag(String path) {
        return forgeTag(BlockTags::createOptional, path);
    }

    public static Tag.Named<Item> forgeItemTag(String path) {
        return forgeTag(ItemTags::createOptional, path);
    }

    public static Tag.Named<Fluid> forgeFluidTag(String path) {
        return forgeTag(FluidTags::createOptional, path);
    }

    public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, BlockBuilder<T, P>> axeOrPickaxe() {
        return b -> b.tag(BlockTags.MINEABLE_WITH_AXE)
                .tag(BlockTags.MINEABLE_WITH_PICKAXE);
    }

    public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, BlockBuilder<T, P>> axeOnly() {
        return b -> b.tag(BlockTags.MINEABLE_WITH_AXE);
    }

    public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, BlockBuilder<T, P>> pickaxeOnly() {
        return b -> b.tag(BlockTags.MINEABLE_WITH_PICKAXE);
    }

    public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, ItemBuilder<BlockItem, BlockBuilder<T, P>>> tagBlockAndItem(
            String path) {
        return b -> b.tag(forgeBlockTag(path))
                .item()
                .tag(forgeItemTag(path));
    }
}
