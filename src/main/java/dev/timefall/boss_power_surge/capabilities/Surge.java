package dev.timefall.boss_power_surge.capabilities;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.timefall.boss_power_surge.components.SurgeComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;

public class Surge implements ISurge, AutoSyncedComponent {
    int initialDragon;
    int withersSpawned;
    int dragonsKilled;
    int wardensSpawned;


    @Override
    public int getInitialDragon() {
        return this.initialDragon;
    }

    @Override
    public void setInitialDragon(int initialDragon) {
        this.initialDragon = initialDragon;
    }

    @Override
    public int getDragonsKilled() {
        return this.dragonsKilled;
    }

    @Override
    public void setDragonsKilled(int dragonsKilled) {
        // TODO Make configurable
        this.dragonsKilled = MathHelper.clamp(dragonsKilled, 0, Integer.MAX_VALUE);
    }

    @Override
    public int getWithersSpawned() {
        return this.withersSpawned;
    }

    @Override
    public void setWithersSpawned(int withersSpawned) {
        // TODO Make configurable
        this.withersSpawned = MathHelper.clamp(withersSpawned, 0, Integer.MAX_VALUE);
        SurgeComponents.SURGE.sync();

    }

    @Override
    public int getWardensSpawned() {
        return this.wardensSpawned;
    }

    @Override
    public void setWardensSpawned(int wardensSpawned) {
        // TODO Make configurable
        this.wardensSpawned = MathHelper.clamp(wardensSpawned, 0, Integer.MAX_VALUE);

    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.setInitialDragon(tag.getInt("initial_dragon"));
        this.setDragonsKilled(tag.getInt("dragons_killed"));
        this.setWithersSpawned(tag.getInt("withers_spawned"));
        this.setWardensSpawned(tag.getInt("wardens_spawned"));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("initial_dragon", this.getInitialDragon());
        tag.putInt("dragons_killed", this.getDragonsKilled());
        tag.putInt("withers_spawned", this.getWithersSpawned());
        tag.putInt("wardens_spawned", this.getWardensSpawned());
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        this.writeWithersSpawnedSyncPacket(buf, recipient, false);
    }

    private void writeWithersSpawnedSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient, boolean incrementValue) {
        buf.writeVarInt(this.withersSpawned++);
        buf.writeBoolean(incrementValue);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        this.withersSpawned = buf.readVarInt();
        if (buf.readBoolean()) {
            MinecraftClient.getInstance().particleManager.addEmitter(
                    MinecraftClient.getInstance().player,
                    ParticleTypes.TOTEM_OF_UNDYING,
                    20);
        }
    }
}
