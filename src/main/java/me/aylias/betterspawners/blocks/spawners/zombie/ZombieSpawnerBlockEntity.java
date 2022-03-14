package me.aylias.betterspawners.blocks.spawners.zombie;

import me.aylias.betterspawners.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ZombieSpawnerBlockEntity extends BlockEntity {

    private final MobSpawnerLogic logic = new MobSpawnerLogic() {
        public void sendStatus(World world, BlockPos pos, int i) {
            world.addSyncedBlockEvent(pos, Main.ZOMBIE_SPAWNER, i, 0);
        }

        public void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry) {
            super.setSpawnEntry(world, pos, new MobSpawnerEntry((NbtCompound) Util.make(new NbtCompound(), (nbt) -> {
                nbt.putString("id", "minecraft:zombie");
            }), Optional.empty()));
            if (world != null) {
                BlockState blockState = world.getBlockState(pos);
                world.updateListeners(pos, blockState, blockState, 4);
            }

        }
    };

    public ZombieSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(Main.ZOMBIE_SPAWNER_BE, pos, state);
        logic.setSpawnEntry(this.world, this.pos, null);
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.logic.readNbt(this.world, this.pos, nbt);
        logic.setSpawnEntry(this.world, this.pos, null);
    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.logic.writeNbt(nbt);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, ZombieSpawnerBlockEntity blockEntity) {
        blockEntity.logic.clientTick(world, pos);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, ZombieSpawnerBlockEntity blockEntity) {
        blockEntity.logic.serverTick((ServerWorld)world, pos);
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    public boolean onSyncedBlockEvent(int type, int data) {
        return this.logic.method_8275(this.world, type) ? true : super.onSyncedBlockEvent(type, data);
    }

    public boolean copyItemDataRequiresOperator() {
        return true;
    }

    public MobSpawnerLogic getLogic() {
        return this.logic;
    }
}
