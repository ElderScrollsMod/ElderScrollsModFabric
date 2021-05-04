package net.fabricmc.elderscrolls;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.ItemStack;

import net.fabricmc.elderscrolls.block.*;
import net.fabricmc.elderscrolls.item.*;

public class ElderScrolls implements ModInitializer {

  public static final Block EXAMPLE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
  public static final FoxgloveBlock FOXGLOVE_BLOCK = new FoxgloveBlock();
  public static final AleItem ALE_ITEM = new AleItem(new FabricItemSettings().maxCount(16));
  public static final AleItem NORDMEAD_ITEM = new AleItem(new FabricItemSettings().maxCount(16));
  public static final Item FOXGLOVE_NECTAR_ITEM = new Item(
    new FabricItemSettings()
    .group(ItemGroup.BREWING)
  );

  public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(
		new Identifier("elderscrolls", "all"))
    .icon(() -> new ItemStack(ALE_ITEM))
    .appendItems(stacks -> {
      stacks.add(new ItemStack(EXAMPLE_BLOCK));
      stacks.add(new ItemStack(FOXGLOVE_BLOCK));
      stacks.add(new ItemStack(FOXGLOVE_NECTAR_ITEM));
      stacks.add(new ItemStack(ALE_ITEM));
    })
		.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

    Registry.register(Registry.BLOCK, new Identifier("elderscrolls", "test"), EXAMPLE_BLOCK);
    Registry.register(Registry.ITEM, new Identifier("elderscrolls", "test"), new BlockItem(EXAMPLE_BLOCK, new FabricItemSettings()));

    Registry.register(Registry.BLOCK, new Identifier("elderscrolls", "foxglove"), FOXGLOVE_BLOCK);
    BlockRenderLayerMap.INSTANCE.putBlock(FOXGLOVE_BLOCK, RenderLayer.getCutout());
    Registry.register(Registry.ITEM, new Identifier("elderscrolls", "foxglove"), new BlockItem(FOXGLOVE_BLOCK, new FabricItemSettings()));

    Registry.register(Registry.ITEM, new Identifier("elderscrolls", "foxglove_nectar"), FOXGLOVE_NECTAR_ITEM);



    Registry.register(Registry.ITEM, new Identifier("elderscrolls", "ale"), ALE_ITEM);
    Registry.register(Registry.ITEM, new Identifier("elderscrolls", "nord_mead"), NORDMEAD_ITEM);
  }
  

}