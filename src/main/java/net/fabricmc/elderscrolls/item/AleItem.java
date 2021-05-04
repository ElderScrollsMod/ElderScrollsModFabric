package net.fabricmc.elderscrolls.item;

import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class AleItem extends Item {
     
  public AleItem(Settings settings) {
    super(settings);
  }

  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    return ItemUsage.consumeHeldItem(world, user, hand);
 }

  public int getMaxUseTime(ItemStack stack) {
    return 32;
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.DRINK;
  }

  @Override
  public SoundEvent getEatSound() {
    return SoundEvents.ENTITY_GENERIC_DRINK;
  }

  // TODO: Implement actual buzz effect :DDDDD

  @Override
  public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
    PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;

    user.animateDamage();

    if (!world.isClient) {
      if (!playerEntity.abilities.creativeMode) {
        stack.decrement(1);
      }
    }
    

    return super.finishUsing(stack, world, user);
  }
}