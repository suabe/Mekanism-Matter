package mekanismmatter.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import mekanismmatter.MekanismmatterMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

public class PatternStorageRightClickedInAirProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency entity for procedure PatternStorageRightClickedInAir!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency itemstack for procedure PatternStorageRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double slot = 0;
		double slot2 = 0;
		double slot3 = 0;
		if ((entity.isSneaking())) {
			for (int index0 = 0; index0 < (int) (30); index0++) {
				{
					ItemStack _isc = (itemstack);
					final ItemStack _setstack = new ItemStack(Blocks.AIR);
					final int _sltid = (int) (slot);
					_setstack.setCount((int) 1);
					_isc.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
						}
					});
				}
				slot = (double) (slot + 1);
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Deleted Pattern Storage!"), (false));
			}
		}
		for (int index1 = 0; index1 < (int) (30); index1++) {
			if ((0 == (((new Object() {
				public ItemStack getItemStack(int sltid, ItemStack _isc) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					_isc.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) (slot2), (itemstack)))).getCount()))) {
				break;
			} else {
				slot2 = (double) (slot2 + 1);
			}
		}
		(itemstack).getOrCreateTag().putString("durationfree", (("\u00A7b") + "" + (((Math.round(slot2)) + "" + ("\u00A7b/30 Patterns")))));
		for (int index2 = 0; index2 < (int) (30); index2++) {
			slot = (double) (slot + 1);
			(itemstack).getOrCreateTag().putString(((Math.round(slot3)) + "" + ("")), ((new Object() {
				public ItemStack getItemStack(int sltid, ItemStack _isc) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					_isc.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) (slot3), (itemstack))).getDisplayName().getString()));
		}
	}
}
