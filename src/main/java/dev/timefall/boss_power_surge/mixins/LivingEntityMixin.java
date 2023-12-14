package dev.timefall.boss_power_surge.mixins;

import dev.timefall.boss_power_surge.components.SurgeComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onDeath", at = @At("TAIL"))
    public void thing(DamageSource damageSource, CallbackInfo ci){
        Entity entity = (Entity) (Object) this;

        if (entity instanceof WitherEntity) {
            SurgeComponents.SURGE.get(entity).setWithersSpawned();
        }
    }
}
