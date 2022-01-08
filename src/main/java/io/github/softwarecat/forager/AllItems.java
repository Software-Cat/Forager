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

import com.tterrag.registrate.util.entry.ItemEntry;
import io.github.softwarecat.forager.foundation.data.ForagerRegistrate;
import net.minecraft.world.item.Item;

import static io.github.softwarecat.forager.content.AllSections.MATERIALS;

public class AllItems {

    private static final ForagerRegistrate REGISTRATE = Forager.getRegistrate().creativeModeTab(() -> Forager.TAB_FORAGER, "Forager");

    static {
        REGISTRATE.startSection(MATERIALS);
    }

    public static final ItemEntry<Item> RUBY = REGISTRATE.item("ruby", Item::new)
            .lang("Ruby")
            .register();

    public static void register() {
    }
}
