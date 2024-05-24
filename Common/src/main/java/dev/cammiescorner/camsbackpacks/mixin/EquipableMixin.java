package dev.cammiescorner.camsbackpacks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.cammiescorner.camsbackpacks.item.BackpackItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Equipable.class)
public interface EquipableMixin {

    @WrapOperation(method = "swapWithEquipmentSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;hasBindingCurse(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean camsbackpacks$hasBindingCurse(ItemStack stack, Operation<Boolean> original) {
        return stack.getItem() instanceof BackpackItem || original.call(stack);
    }
}
