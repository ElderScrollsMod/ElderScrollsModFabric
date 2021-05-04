package net.fabricmc.elderscrolls.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.state.StateManager;
import net.minecraft.sound.BlockSoundGroup;

public class FoxgloveBlock extends FlowerBlock implements Fertilizable{
  public static final IntProperty AGE = IntProperty.of("age", 0, 15);

  public FoxgloveBlock() {
    super(
      StatusEffects.RESISTANCE,
      0, 
      Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().ticksRandomly()
    );
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
      stateManager.add(AGE);
  }

  @Override
  public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
    return true;
  }

  @Override
  public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
    int age = state.get(AGE);

    if (canGrow(world, random, pos, state)) {
      world.setBlockState(pos, state.with(AGE, age + 1), 15);
    }
  }

  @Override
  public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
    int age = state.get(AGE);

    if (world.isAir(pos.up()) && age < 15) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    super.randomTick(state, world, pos, random);

    grow(world, random, pos, state);
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    
    int age = state.get(AGE);

    if (age == 15) {
      if (!world.isClient) {
        dropStack(world, pos, new ItemStack(Registry.ITEM.get(new Identifier("elderscrolls", "foxglove_nectar"))));
        world.setBlockState(pos, state.with(AGE, age - 7), 15);
      }
  
      return ActionResult.SUCCESS;
    }

    return super.onUse(state, world, pos, player, hand, hit);
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }
}