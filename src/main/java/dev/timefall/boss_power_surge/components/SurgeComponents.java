package dev.timefall.boss_power_surge.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.timefall.boss_power_surge.BossPowerSurge;
import dev.timefall.boss_power_surge.capabilities.ISurge;
import dev.timefall.boss_power_surge.capabilities.Surge;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.util.Identifier;

public class SurgeComponents implements EntityComponentInitializer {
    public static final ComponentKey<ISurge> SURGE = ComponentRegistry.getOrCreate(new Identifier(BossPowerSurge.MOD_ID, "surge"), ISurge.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(WitherSkeletonEntity.class, SURGE, surge -> new Surge());
        registry.registerFor(WardenEntity.class, SURGE, surge -> new Surge());
        registry.registerFor(EnderDragonEntity.class, SURGE, surge -> new Surge());
    }
}
