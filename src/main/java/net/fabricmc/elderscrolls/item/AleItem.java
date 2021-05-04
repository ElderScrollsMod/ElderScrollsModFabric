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
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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

  @Override
  public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
    PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;

    user.animateDamage();

    if (playerEntity != null) {
      playerEntity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.0F);
    }

    if (!world.isClient && playerEntity != null) {
      if (!playerEntity.abilities.creativeMode) {
        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.NAUSEA, 120, 1);

        user.addStatusEffect(effect);

        stack.decrement(1);
      }
    }
    

    return super.finishUsing(stack, world, user);
  }
}