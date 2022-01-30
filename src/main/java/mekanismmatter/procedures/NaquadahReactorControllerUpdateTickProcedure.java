package mekanismmatter.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tags.ItemTags;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import mekanismmatter.potion.IrradiatedPotionEffect;

import mekanismmatter.particle.ReactorParticlesParticle;

import mekanismmatter.item.RefinedNaquadahItem;

import mekanismmatter.block.ReactorOutputPortBlock;
import mekanismmatter.block.ReactorInputPortBlock;
import mekanismmatter.block.LiquidNaquadahBlock;

import mekanismmatter.MekanismmatterMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

public class NaquadahReactorControllerUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency x for procedure NaquadahReactorControllerUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency y for procedure NaquadahReactorControllerUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency z for procedure NaquadahReactorControllerUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency world for procedure NaquadahReactorControllerUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean assembled = false;
		String TagReactor = "";
		double energy = 0;
		double fluid = 0;
		if ((RefinedNaquadahItem.block == (new Object() {
			public ItemStack getItemStack(BlockPos pos, int sltid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null) {
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
				}
				return _retval.get();
			}
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
			{
				TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				if (_ent != null) {
					final int _sltid = (int) (0);
					final int _amount = (int) 1;
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							ItemStack _stk = capability.getStackInSlot(_sltid).copy();
							_stk.shrink(_amount);
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
						}
					});
				}
			}
			{
				TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				int _amount = (int) 1;
				if (_ent != null)
					_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
							capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
			}
		}
		TagReactor = (String) "forge:naquadah_reactor_item_tag";
		if ((((((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
				.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 1), (int) (z + 2)))).getBlock())).getItem()))
				&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH))).contains(
						(new ItemStack((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z + 2)))).getBlock())).getItem())))
				&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH))).contains(
						(new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 2)))).getBlock())).getItem())))
				&& ((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH))).contains(
						(new ItemStack((world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 2), (int) (z + 2)))).getBlock())).getItem()))
						&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z + 2)))).getBlock()))
										.getItem())))
						&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z + 2)))).getBlock()))
										.getItem())))
						&& (((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 3), (int) (z + 2)))).getBlock()))
										.getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 3), (int) (z + 2)))).getBlock()))
														.getItem())))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 3), (int) (z + 2)))).getBlock()))
														.getItem())))))
				&& ((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH))).contains(
						(new ItemStack((world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 1), (int) (z - 2)))).getBlock())).getItem()))
						&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 2)))).getBlock()))
										.getItem())))
						&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 2)))).getBlock()))
										.getItem())))
						&& ((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 2), (int) (z - 2)))).getBlock()))
										.getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z - 2)))).getBlock()))
														.getItem())))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z - 2)))).getBlock()))
														.getItem())))
								&& (((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x - 0), (int) (y - 3), (int) (z - 2)))).getBlock()))
														.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 3), (int) (z - 2)))).getBlock()))
																.getItem())))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 3), (int) (z - 2)))).getBlock()))
																.getItem()))))))
				&& (((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH))).contains(
						(new ItemStack((world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 1), (int) (z + 1)))).getBlock())).getItem()))
						&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 1), (int) (z + 0)))).getBlock()))
										.getItem())))
						&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 1), (int) (z - 1)))).getBlock()))
										.getItem())))
						&& ((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 1)))).getBlock()))
										.getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 0)))).getBlock()))
														.getItem())))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z - 1)))).getBlock()))
														.getItem())))
								&& (((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 3), (int) (z + 1)))).getBlock()))
														.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 3), (int) (z + 0)))).getBlock()))
																.getItem())))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 3), (int) (z - 1)))).getBlock()))
																.getItem())))))
						&& ((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 1), (int) (z + 1)))).getBlock()))
										.getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 1), (int) (z + 0)))).getBlock()))
														.getItem())))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 1), (int) (z - 1)))).getBlock()))
														.getItem())))
								&& ((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 1)))).getBlock()))
														.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 0)))).getBlock()))
																.getItem())))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z - 1)))).getBlock()))
																.getItem())))
										&& (((ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 3), (int) (z + 1)))).getBlock()))
																.getItem()))
												&& (ItemTags.getCollection()
														.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
														.contains((new ItemStack(
																(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 3), (int) (z + 0))))
																		.getBlock())).getItem())))
												&& (ItemTags.getCollection()
														.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
														.contains((new ItemStack(
																(world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 3), (int) (z - 1))))
																		.getBlock())).getItem())))))))
				&& ((((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
						.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock())).getItem()))
						&& ((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock())).getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock()))
												.getItem()))))
						&& ((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock())).getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()))
												.getItem()))))
						&& (((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH))).contains(
								(new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1)))).getBlock())).getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains(
												(new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)))).getBlock()))
														.getItem())))
								&& ((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains(
												(new ItemStack((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1)))).getBlock()))
														.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1)))).getBlock()))
																.getItem())))))
						&& (((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getBlock())).getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getBlock()))
												.getItem())))
								&& ((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getBlock()))
												.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getBlock()))
																.getItem())))))
						&& (((((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
								.contains((new ItemStack((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 4), (int) z))).getBlock()))
										.getItem()))
								&& (ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains(
												(new ItemStack((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 4), (int) z))).getBlock()))
														.getItem())))
								&& ((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains(
												(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z + 1)))).getBlock()))
														.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z - 1)))).getBlock()))
																.getItem()))))
								&& (((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains((new ItemStack(
												(world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 4), (int) (z + 1)))).getBlock()))
														.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 4), (int) (z - 1)))).getBlock()))
																.getItem())))
										&& ((ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 4), (int) (z - 1)))).getBlock()))
																.getItem()))
												&& (ItemTags.getCollection()
														.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
														.contains((new ItemStack(
																(world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 4), (int) (z + 1))))
																		.getBlock())).getItem())))))
								&& (((ItemTags.getCollection().getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
										.contains(
												(new ItemStack((world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 4), (int) z))).getBlock()))
														.getItem()))
										&& (ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 4), (int) z))).getBlock()))
																.getItem())))
										&& ((ItemTags.getCollection()
												.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
												.contains((new ItemStack(
														(world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z + 2)))).getBlock()))
																.getItem()))
												&& (ItemTags.getCollection()
														.getTagByID(new ResourceLocation((TagReactor).toLowerCase(java.util.Locale.ENGLISH)))
														.contains((new ItemStack(
																(world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z - 2))))
																		.getBlock())).getItem())))))))) {
			assembled = (boolean) (true);
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) (z + 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z + 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) y, (int) (z + 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z + 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z + 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) (z + 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 4), (int) (z + 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z + 1)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 4), (int) (z + 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 4), (int) (z + 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) y, (int) (z - 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z - 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) y, (int) (z - 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) (z - 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z - 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) (y - 4), (int) (z - 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 4), (int) (z - 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) (z - 1)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 4), (int) (z - 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 4), (int) (z - 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 1), (int) y, (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 1), (int) y, (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 4), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 1), (int) (y - 4), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) (y - 4), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 4), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 1), (int) (y - 4), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) (y - 4), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 1), (int) y, (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 1), (int) y, (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 4), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 1), (int) (y - 4), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) (y - 4), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 4), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 1), (int) (y - 4), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) (y - 4), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 4), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 0), (int) (y - 4), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 0), (int) (y - 4), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 0), (int) (y - 4), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 0), (int) (y - 4), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 0), (int) (y - 4), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 1), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 2), (int) (y - 1), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 1), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 1), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 2), (int) (y - 1), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 1), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 1), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 2), (int) (y - 1), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 1), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 1), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 2), (int) (y - 1), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 1), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 2), (int) (y - 2), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 2), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 2), (int) (y - 2), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 2), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 2), (int) (y - 2), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 2), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 2), (int) (y - 2), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 2), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z - 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 2), (int) (y - 2), (int) (z - 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z - 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z - 1))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 2), (int) (y - 2), (int) (z - 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z - 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z - 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 2), (int) (y - 2), (int) (z - 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z - 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z - 1))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 2), (int) (y - 2), (int) (z - 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z - 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 1))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 2), (int) (z + 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 1)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 1)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 1)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 1))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 1)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 2), (int) (z + 1)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 3), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 2), (int) (y - 3), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 3), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 2), (int) (y - 3), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 2), (int) (y - 3), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 2), (int) (y - 3), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 3), (int) z))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 2), (int) (y - 3), (int) z), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 3), (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 2), (int) (y - 3), (int) z))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 2), (int) (y - 3), (int) z), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 2), (int) (y - 3), (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) (y - 1), (int) (z - 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) (z - 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 2)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 1), (int) (z - 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) (z - 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) (y - 1), (int) (z + 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) (z + 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 2)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 1), (int) (z + 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) (z + 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) (z - 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) (y - 2), (int) (z - 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 2), (int) (z - 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) (z - 2)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 2), (int) (z - 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 2), (int) (z - 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) (z + 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) (y - 2), (int) (z + 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 2), (int) (z + 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) (z + 2)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 2), (int) (z + 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 2), (int) (z + 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z - 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 1), (int) (y - 2), (int) (z - 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z - 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z - 2))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 1), (int) (y - 2), (int) (z - 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z - 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z + 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x + 1), (int) (y - 2), (int) (z + 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z + 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z + 2))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x + 1), (int) (y - 2), (int) (z + 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x + 1), (int) (y - 2), (int) (z + 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z - 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 1), (int) (y - 2), (int) (z - 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z - 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z - 2))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 1), (int) (y - 2), (int) (z - 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z - 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z + 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) (x - 1), (int) (y - 2), (int) (z + 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z + 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z + 2))))
					.getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) (x - 1), (int) (y - 2), (int) (z + 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) (x - 1), (int) (y - 2), (int) (z + 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 3), (int) (z - 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) (y - 3), (int) (z - 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 3), (int) (z - 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 3), (int) (z - 2)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 3), (int) (z - 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 3), (int) (z - 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if ((ReactorInputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 3), (int) (z + 2)))).getBlock())) {
				fluid = (double) (new Object() {
					public int drainTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.drain(amount, IFluidHandler.FluidAction.SIMULATE).getAmount()));
						return _retval.get();
					}
				}.drainTankSimulate(world, new BlockPos((int) x, (int) (y - 3), (int) (z + 2)), (int) 1000));
				fluid = (double) (new Object() {
					public int fillTankSimulate(IWorld world, BlockPos pos, int amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> _retval
									.set(capability.fill(new FluidStack(LiquidNaquadahBlock.still, amount), IFluidHandler.FluidAction.SIMULATE)));
						return _retval.get();
					}
				}.fillTankSimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) fluid));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 3), (int) (z + 2)));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) fluid;
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(
								capability -> capability.fill(new FluidStack(LiquidNaquadahBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
				}
			} else if ((ReactorOutputPortBlock.block == (world.getBlockState(new BlockPos((int) x, (int) (y - 3), (int) (z + 2)))).getBlock())) {
				energy = (double) (new Object() {
					public int extractEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.extractEnergy(_amount, true)));
						return _retval.get();
					}
				}.extractEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 400000000));
				energy = (double) (new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) (y - 3), (int) (z + 2)), (int) energy));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
				}
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 3), (int) (z + 2)));
					int _amount = (int) energy;
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(capability -> capability.receiveEnergy(_amount, false));
				}
			}
			if (((true) == assembled)) {
				if ((((((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "fuelmycrobuckets")) < 0) || ((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) >= (new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBT")))) || ((false) == (new Object() {
					public boolean getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getBoolean(tag);
						return false;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "onoff")))) && ((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) > 0))) {
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("RateNBTScaled", ((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) - 1));
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
				if (((((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "fuelmycrobuckets")) > 0) && ((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) <= (new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBT")))) && ((true) == (new Object() {
					public boolean getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getBoolean(tag);
						return false;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "onoff"))))) {
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("RateNBTScaled", ((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) + 1));
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
				if ((((true) == (new Object() {
					public boolean getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getBoolean(tag);
						return false;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "safetymode"))) && ((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Temprature")) >= 3500))) {
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putBoolean("onoff", (false));
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
				if (((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Temprature")) >= 12000)) {
					if (world instanceof World && !((World) world).isRemote) {
						((World) world).createExplosion(null, (int) x, (int) y, (int) z, (float) 25, Explosion.Mode.DESTROY);
					}
					{
						List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (100 / 2d), y - (100 / 2d), z - (100 / 2d), x + (100 / 2d), y + (100 / 2d), z + (100 / 2d)),
								null).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
									}
								}.compareDistOf(x, y, z)).collect(Collectors.toList());
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof LivingEntity)
								((LivingEntity) entityiterator)
										.addPotionEffect(new EffectInstance(IrradiatedPotionEffect.potion, (int) 10000, (int) 1));
						}
					}
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("Output", (((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) * (29343.6299 - ((3 * Math.pow(((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) - 10), 2)) / 100))) * 1));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Output")) < 0)) {
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("Output", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("Efficiency", (29343.6299 - ((3 * Math.pow(((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) - 10), 2)) / 100)));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("Temprature", (Math.sqrt(((Math.pow(((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) - 10), 3) * 0.01) + 10)) * 5));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("FuelComsumption", ((((Math.pow(((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBTScaled")) - 10), 3) * 0.01) + 10) / 10000) + 0.0001));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("GammaRays", Math.pow((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Temprature")), 2));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ReactorParticlesParticle.particle, (x + 0.5), (y - 1.5), (z + 0.5), (int) 1, 0, 0, 0, 0.12);
			}
			{
				TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				int _amount = (int) (new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Output"));
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
			}
			if (((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "fuelmycrobuckets")) > 0)) {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("fuelmycrobuckets", ((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "fuelmycrobuckets")) - (new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "FuelComsumption"))));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if ((((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "fuelmycrobuckets")) <= 0) && ((new Object() {
				public int getFluidTankLevel(BlockPos pos, int tank) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.getFluidInTank(tank).getAmount()));
					return _retval.get();
				}
			}.getFluidTankLevel(new BlockPos((int) x, (int) y, (int) z), (int) 1)) >= Math.ceil(((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "FuelComsumption")) / 1000))))) {
				{
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					int _amount = (int) Math.ceil(((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "FuelComsumption")) / 1000));
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> capability.drain(_amount, IFluidHandler.FluidAction.EXECUTE));
				}
				if (((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "FuelComsumption")) < 1000)) {
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("fuelmycrobuckets", 1000);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else {
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("fuelmycrobuckets", (new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "FuelComsumption")));
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				}
			}
		}
		if (((true) == assembled)) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line1", (((("Temprature: ") + "" + (Math.round((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Temprature")))))) + "" + ("\u00B0C")));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line2", (("Gamma Rays: ") + "" + ((((Math.round((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "GammaRays"))) / 1000)) + "" + ("mSv")))));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line3", (("Efficiency: ") + "" + (((Math.round(((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Efficiency")) / 293.436299))) + "" + ("%")))));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "fuelmycrobuckets")) > 0)) {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("line4", (("Fuel Usage: ") + "" + ((((Math.round(((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "FuelComsumption")) * 1000)) / 1000)) + "" + ("\u00B5B/t")))));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("fuel", (true));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("line4", "\u00A7cOut of Fuel!");
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putBoolean("fuel", (false));
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line5", (("Rate: ") + "" + (Math.round((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "RateNBT"))))));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line6", (("Output: ") + "" + (((Math.round((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Output")))) + "" + (" Fe/t")))));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		} else {
			if (((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Temprature")) > 1000)) {
				{
					List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class,
							new AxisAlignedBB(x - (100 / 2d), y - (100 / 2d), z - (100 / 2d), x + (100 / 2d), y + (100 / 2d), z + (100 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (entityiterator instanceof LivingEntity)
							((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(IrradiatedPotionEffect.potion, (int) 10000, (int) 1));
					}
				}
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("onoff", (false));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line1", "\u00A7cNot Assembled!");
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line2", "");
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line3", "");
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line4", "");
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line5", "");
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("line6", "");
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
	}
}
