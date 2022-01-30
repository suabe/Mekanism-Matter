package mekanismmatter.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockState;

import mekanismmatter.item.UniversalMatterItem;
import mekanismmatter.item.ScrapboxItem;
import mekanismmatter.item.ScarpItem;

import mekanismmatter.block.UniversalMatterFluidBlock;

import mekanismmatter.MekanismmatterMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public class MassFabricatorUpdateTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency x for procedure MassFabricatorUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency y for procedure MassFabricatorUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency z for procedure MassFabricatorUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency world for procedure MassFabricatorUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double energyatick = 0;
		double energyprotick = 0;
		if ((!((world instanceof World) ? ((World) world).isBlockPowered(new BlockPos((int) x, (int) y, (int) z)) : false))) {
			if ((((new Object() {
				public int getEnergyStored(IWorld world, BlockPos pos) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
					return _retval.get();
				}
			}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) > 0) && (((new Object() {
				public int getFluidTankLevel(BlockPos pos, int tank) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.getFluidInTank(tank).getAmount()));
					return _retval.get();
				}
			}.getFluidTankLevel(new BlockPos((int) x, (int) y, (int) z), (int) 1)) < (new Object() {
				public int getFluidTankCapacity(BlockPos pos, int tank) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null)
						_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
								.ifPresent(capability -> _retval.set(capability.getTankCapacity(tank)));
					return _retval.get();
				}
			}.getFluidTankCapacity(new BlockPos((int) x, (int) y, (int) z), (int) 1))) && ((new Object() {
				public int getAmount(IWorld world, BlockPos pos, int sltid) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null) {
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).getCount());
						});
					}
					return _retval.get();
				}
			}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (1))) < 64)))) {
				if ((ScarpItem.block == (new Object() {
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
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("energystored", ((new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energystored")) + ((new Object() {
								public int getEnergyStored(IWorld world, BlockPos pos) {
									AtomicInteger _retval = new AtomicInteger(0);
									TileEntity _ent = world.getTileEntity(pos);
									if (_ent != null)
										_ent.getCapability(CapabilityEnergy.ENERGY, null)
												.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
									return _retval.get();
								}
							}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) * 1.35)));
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
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
					energyprotick = (double) ((new Object() {
						public int getEnergyStored(IWorld world, BlockPos pos) {
							AtomicInteger _retval = new AtomicInteger(0);
							TileEntity _ent = world.getTileEntity(pos);
							if (_ent != null)
								_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
							return _retval.get();
						}
					}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) * 1.35);
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						int _amount = (int) (new Object() {
							public int getEnergyStored(IWorld world, BlockPos pos) {
								AtomicInteger _retval = new AtomicInteger(0);
								TileEntity _ent = world.getTileEntity(pos);
								if (_ent != null)
									_ent.getCapability(CapabilityEnergy.ENERGY, null)
											.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
								return _retval.get();
							}
						}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z)));
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
					}
				} else {
					if ((ScrapboxItem.block == (new Object() {
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
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("energystored", ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energystored")) + ((new Object() {
									public int getEnergyStored(IWorld world, BlockPos pos) {
										AtomicInteger _retval = new AtomicInteger(0);
										TileEntity _ent = world.getTileEntity(pos);
										if (_ent != null)
											_ent.getCapability(CapabilityEnergy.ENERGY, null)
													.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
										return _retval.get();
									}
								}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) * 10)));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
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
						energyprotick = (double) ((new Object() {
							public int getEnergyStored(IWorld world, BlockPos pos) {
								AtomicInteger _retval = new AtomicInteger(0);
								TileEntity _ent = world.getTileEntity(pos);
								if (_ent != null)
									_ent.getCapability(CapabilityEnergy.ENERGY, null)
											.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
								return _retval.get();
							}
						}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) * 10);
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							int _amount = (int) (new Object() {
								public int getEnergyStored(IWorld world, BlockPos pos) {
									AtomicInteger _retval = new AtomicInteger(0);
									TileEntity _ent = world.getTileEntity(pos);
									if (_ent != null)
										_ent.getCapability(CapabilityEnergy.ENERGY, null)
												.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
									return _retval.get();
								}
							}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z)));
							if (_ent != null)
								_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
						}
					} else {
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("energystored", ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energystored")) + ((new Object() {
									public int getEnergyStored(IWorld world, BlockPos pos) {
										AtomicInteger _retval = new AtomicInteger(0);
										TileEntity _ent = world.getTileEntity(pos);
										if (_ent != null)
											_ent.getCapability(CapabilityEnergy.ENERGY, null)
													.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
										return _retval.get();
									}
								}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) * 1)));
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						energyprotick = (double) ((new Object() {
							public int getEnergyStored(IWorld world, BlockPos pos) {
								AtomicInteger _retval = new AtomicInteger(0);
								TileEntity _ent = world.getTileEntity(pos);
								if (_ent != null)
									_ent.getCapability(CapabilityEnergy.ENERGY, null)
											.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
								return _retval.get();
							}
						}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) * 1);
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							int _amount = (int) (new Object() {
								public int getEnergyStored(IWorld world, BlockPos pos) {
									AtomicInteger _retval = new AtomicInteger(0);
									TileEntity _ent = world.getTileEntity(pos);
									if (_ent != null)
										_ent.getCapability(CapabilityEnergy.ENERGY, null)
												.ifPresent(capability -> _retval.set(capability.getEnergyStored()));
									return _retval.get();
								}
							}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z)));
							if (_ent != null)
								_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
						}
					}
				}
			}
			if (((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energystored")) >= 25000000)) {
				if (((true) == (new Object() {
					public boolean getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getBoolean(tag);
						return false;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "modefluid")))) {
					{
						TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
						int _amount = (int) 1;
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).ifPresent(capability -> capability
									.fill(new FluidStack(UniversalMatterFluidBlock.still, _amount), IFluidHandler.FluidAction.EXECUTE));
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("energystored", 0);
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
				} else {
					if (((new Object() {
						public int getAmount(IWorld world, BlockPos pos, int sltid) {
							AtomicInteger _retval = new AtomicInteger(0);
							TileEntity _ent = world.getTileEntity(pos);
							if (_ent != null) {
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).getCount());
								});
							}
							return _retval.get();
						}
					}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (1))) < 64)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (_ent != null) {
								final int _sltid = (int) (1);
								final ItemStack _setstack = new ItemStack(UniversalMatterItem.block);
								_setstack.setCount((int) ((new Object() {
									public int getAmount(IWorld world, BlockPos pos, int sltid) {
										AtomicInteger _retval = new AtomicInteger(0);
										TileEntity _ent = world.getTileEntity(pos);
										if (_ent != null) {
											_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
												_retval.set(capability.getStackInSlot(sltid).getCount());
											});
										}
										return _retval.get();
									}
								}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (1))) + 1));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						if (!world.isRemote()) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("energystored", 0);
							if (world instanceof World)
								((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
					}
				}
			}
			if (((true) == (new Object() {
				public boolean getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "modefluid")))) {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("mode", "Mode: \u00A79Fluid");
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			} else {
				if (!world.isRemote()) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putString("mode", "Mode: \u00A7cSolid");
					if (world instanceof World)
						((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("progress", (("\u00A78Progress: ") + "" + ((((Math.ceil((10000 * ((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energystored")) / 25000000))) / 100)) + "" + ("\u00A78%")))));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("efficiency", (("efcy: ") + "" + (((Math.round((100 * (energyprotick / 50000)))) + "" + ("%")))));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putString("umstored", (("Tank: ") + "" + (((Math.round((new Object() {
					public int getFluidTankLevel(BlockPos pos, int tank) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.getFluidInTank(tank).getAmount()));
						return _retval.get();
					}
				}.getFluidTankLevel(new BlockPos((int) x, (int) y, (int) z), (int) 1)))) + "" + ("mb")))));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
		if (!world.isRemote()) {
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putDouble("liquidum", (new Object() {
					public int getFluidTankLevel(BlockPos pos, int tank) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
									.ifPresent(capability -> _retval.set(capability.getFluidInTank(tank).getAmount()));
						return _retval.get();
					}
				}.getFluidTankLevel(new BlockPos((int) x, (int) y, (int) z), (int) 1)));
			if (world instanceof World)
				((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
	}
}
