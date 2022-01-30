package mekanismmatter.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;

import mekanismmatter.block.FoodReplicatorBlock;

import mekanismmatter.MekanismmatterMod;

import java.util.Map;

public class FoodReplicatorNeighbourBlockChangesProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency x for procedure FoodReplicatorNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency y for procedure FoodReplicatorNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency z for procedure FoodReplicatorNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency world for procedure FoodReplicatorNeighbourBlockChanges!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(((!(Blocks.AIR == (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()))
				|| (!(Blocks.AIR == (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock())))
				|| ((!(Blocks.AIR == (world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock()))
						|| (!(Blocks.AIR == (world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock())))))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(FoodReplicatorBlock.block));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
