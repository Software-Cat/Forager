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

import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.softwarecat.forager.content.AllSections;
import io.github.softwarecat.forager.foundation.data.ForagerRegistrate;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;

public class AllBlocks {

    private static final ForagerRegistrate REGISTRATE = Forager.getRegistrate().creativeModeTab(() -> Forager.TAB_FORAGER);

    static {
        REGISTRATE.startSection(AllSections.PALETTES);
    }

    public static final BlockEntry<Block> RUBY_BLOCK = REGISTRATE.block("ruby_block", Block::new)
            .initialProperties(() -> Blocks.EMERALD_BLOCK)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .transform(AllTags.pickaxeOnly())
            .blockstate((c, p) -> p.simpleBlock(c.get(), p.models()
                    .cubeAll(c.getName(), p.modLoc("block/ruby_storage_block"))))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.STORAGE_BLOCKS)
            .tag(BlockTags.BEACON_BASE_BLOCKS)
            .transform(AllTags.tagBlockAndItem("storage_blocks/brass"))
            .tag(Tags.Items.STORAGE_BLOCKS)
            .build()
            .lang("Block of Ruby")
            .register();

    public static void register() {
    }
}
