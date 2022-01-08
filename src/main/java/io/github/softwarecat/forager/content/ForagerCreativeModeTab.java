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

package io.github.softwarecat.forager.content;

import io.github.softwarecat.forager.AllItems;
import io.github.softwarecat.forager.foundation.item.ForagerCreativeModeTabBase;
import net.minecraft.world.item.ItemStack;

public class ForagerCreativeModeTab extends ForagerCreativeModeTabBase {

    public ForagerCreativeModeTab() {
        super("base");
    }

    @Override
    public ItemStack makeIcon() {
        return AllItems.RUBY.asStack();
    }
}
