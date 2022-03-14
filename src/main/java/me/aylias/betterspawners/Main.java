package me.aylias.betterspawners;

import me.aylias.betterspawners.blocks.spawners.base.CustomSpawnerBlock;
import me.aylias.betterspawners.blocks.spawners.base.CustomSpawnerBlockEntity;
import me.aylias.betterspawners.blocks.spawners.skeleton.SkeletonSpawnerBlock;
import me.aylias.betterspawners.blocks.spawners.skeleton.SkeletonSpawnerBlockEntity;
import me.aylias.betterspawners.blocks.spawners.spider.SpiderSpawnerBlock;
import me.aylias.betterspawners.blocks.spawners.spider.SpiderSpawnerBlockEntity;
import me.aylias.betterspawners.blocks.spawners.zombie.ZombieSpawnerBlock;
import me.aylias.betterspawners.blocks.spawners.zombie.ZombieSpawnerBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String id = "betterspawners";

	public static final Logger LOGGER = LoggerFactory.getLogger("betterspawners");

	public static final Block CUSTOM_SPAWNER = new CustomSpawnerBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(5.0F).sounds(BlockSoundGroup.METAL).nonOpaque().ticksRandomly());
	public static final Block SKELETON_SPAWNER = new SkeletonSpawnerBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(5.0F).sounds(BlockSoundGroup.METAL).nonOpaque().ticksRandomly());
	public static final Block ZOMBIE_SPAWNER = new ZombieSpawnerBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(5.0F).sounds(BlockSoundGroup.METAL).nonOpaque().ticksRandomly());
	public static final Block SPIDER_SPAWNER = new SpiderSpawnerBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(5.0F).sounds(BlockSoundGroup.METAL).nonOpaque().ticksRandomly());

	public static BlockEntityType<CustomSpawnerBlockEntity> CUSTOM_SPAWNER_BE;
	public static BlockEntityType<SkeletonSpawnerBlockEntity> SKELETON_SPAWNER_BE;
	public static BlockEntityType<ZombieSpawnerBlockEntity> ZOMBIE_SPAWNER_BE;
	public static BlockEntityType<SpiderSpawnerBlockEntity> SPIDER_SPAWNER_BE;

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(id, "custom_spawner"), CUSTOM_SPAWNER);
		Registry.register(Registry.BLOCK, new Identifier(id, "skeleton_spawner"), SKELETON_SPAWNER);
		Registry.register(Registry.BLOCK, new Identifier(id, "zombie_spawner"), ZOMBIE_SPAWNER);
		Registry.register(Registry.BLOCK, new Identifier(id, "spider_spawner"), SPIDER_SPAWNER);

		CUSTOM_SPAWNER_BE = Registry.register(Registry.BLOCK_ENTITY_TYPE, id + ":custom_spawner_entity", FabricBlockEntityTypeBuilder.create(CustomSpawnerBlockEntity::new, CUSTOM_SPAWNER).build(null));
		SKELETON_SPAWNER_BE = Registry.register(Registry.BLOCK_ENTITY_TYPE, id + ":skeleton_spawner_entity", FabricBlockEntityTypeBuilder.create(SkeletonSpawnerBlockEntity::new, SKELETON_SPAWNER).build(null));
		ZOMBIE_SPAWNER_BE = Registry.register(Registry.BLOCK_ENTITY_TYPE, id + ":zombie_spawner_entity", FabricBlockEntityTypeBuilder.create(ZombieSpawnerBlockEntity::new, ZOMBIE_SPAWNER).build(null));
		SPIDER_SPAWNER_BE = Registry.register(Registry.BLOCK_ENTITY_TYPE, id + ":spider_spawner_entity", FabricBlockEntityTypeBuilder.create(SpiderSpawnerBlockEntity::new, SPIDER_SPAWNER).build(null));

		BlockRenderLayerMap.INSTANCE.putBlock(CUSTOM_SPAWNER, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(SKELETON_SPAWNER, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ZOMBIE_SPAWNER, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(SPIDER_SPAWNER, RenderLayer.getTranslucent());
	}
}
