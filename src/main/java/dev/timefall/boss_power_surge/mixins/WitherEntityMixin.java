package dev.timefall.boss_power_surge.mixins;

import dev.timefall.boss_power_surge.components.SurgeComponents;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {
    @Unique
    static WitherEntity witherEntity;
    @Unique
    @Inject(method = "createWitherAttributes", at = @At("RETURN"), cancellable = true)
    private static void boss_power_surge$surgeWither(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(
                HostileEntity.createHostileAttributes().add(
                        EntityAttributes.GENERIC_MAX_HEALTH,
                        300 * SurgeComponents.SURGE.get(WitherEntityMixin.witherEntity).getWithersSpawned()
                )
        );
    }
}
