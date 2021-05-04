package net.fabricmc.example.item;

import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TypedActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class FabricItem extends Item {
     
  public FabricItem(Settings settings) {
      super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
      playerEntity.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
      return TypedActionResult.success(playerEntity.getStackInHand(hand));
  }
}