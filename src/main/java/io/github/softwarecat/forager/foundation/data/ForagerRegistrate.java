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

package io.github.softwarecat.forager.foundation.data;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.util.NonNullLazyValue;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import io.github.softwarecat.forager.content.AllSections;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ForagerRegistrate extends AbstractRegistrate<ForagerRegistrate> {

    private static Map<RegistryEntry<?>, AllSections> sectionLookup = new IdentityHashMap<>();
    private AllSections section;

    protected ForagerRegistrate(String modid) {
        super(modid);
    }

    public static NonNullLazyValue<ForagerRegistrate> lazy(String modid) {
        return new NonNullLazyValue<>(() -> new ForagerRegistrate(modid)
                .registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
    }

    public ForagerRegistrate startSection(AllSections section) {
        this.section = section;
        return this;
    }

    public AllSections currentSection() {
        return section;
    }

    @Override
    protected <R extends IForgeRegistryEntry<R>, T extends R> @NotNull RegistryEntry<T> accept(@NotNull String name,
                                                                                               @NotNull Class<? super R> type,
                                                                                               @NotNull Builder<R, T, ?, ?> builder,
                                                                                               @NotNull NonNullSupplier<? extends T> creator,
                                                                                               @NotNull NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory) {
        RegistryEntry<T> ret = super.accept(name, type, builder, creator, entryFactory);
        sectionLookup.put(ret, currentSection());
        return ret;
    }

    public void addToSection(RegistryEntry<?> entry, AllSections section) {
        sectionLookup.put(entry, section);
    }

    public AllSections getSection(RegistryEntry<?> entry) {
        return sectionLookup.getOrDefault(entry, AllSections.UNASSIGNED);
    }

    public AllSections getSection(IForgeRegistryEntry<?> entry) {
        return sectionLookup.entrySet()
                .stream()
                .filter(e -> e.getKey()
                        .get() == entry)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(AllSections.UNASSIGNED);
    }

    public <R extends IForgeRegistryEntry<R>> Collection<RegistryEntry<R>> getAll(AllSections section,
                                                                                  Class<? super R> registryType) {
        return this.<R>getAll(registryType)
                .stream()
                .filter(e -> getSection(e) == section)
                .collect(Collectors.toList());
    }
}
