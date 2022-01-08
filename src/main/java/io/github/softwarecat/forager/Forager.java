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

import com.tterrag.registrate.util.NonNullLazyValue;
import io.github.softwarecat.forager.content.ForagerCreativeModeTab;
import io.github.softwarecat.forager.foundation.data.ForagerRegistrate;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Forager.ID)
public class Forager {

    public static final String ID = "forager";
    public static final String NAME = "Forager";

    public static final CreativeModeTab TAB_FORAGER = new ForagerCreativeModeTab();

    private static final Logger LOGGER = LogManager.getLogger();

    private static final NonNullLazyValue<ForagerRegistrate> REGISTRATE = ForagerRegistrate.lazy(ID);

    public Forager() {
        onCtor();
    }

    public static void onCtor() {
        AllItems.register();
        AllBlocks.register();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(Forager::init);
        modEventBus.addListener(EventPriority.LOWEST, Forager::gatherData);
    }

    public static void init(final FMLCommonSetupEvent event) {
    }

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
    }

    public static ForagerRegistrate getRegistrate() {
        return REGISTRATE.get();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }
}
