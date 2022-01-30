package mekanismmatter.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;

import mekanismmatter.item.UniversalMatterItem;
import mekanismmatter.item.ScarpItem;

import mekanismmatter.MekanismmatterMod;

import java.util.Map;

public class ScrapboxRightClickedInAirProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency itemstack for procedure ScrapboxRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency x for procedure ScrapboxRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency y for procedure ScrapboxRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency z for procedure ScrapboxRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency world for procedure ScrapboxRightClickedInAir!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double RandomNumber = 0;
		((itemstack)).shrink((int) 1);
		RandomNumber = (double) ((Math.random() * 10) * (Math.random() * 10));
		if (((RandomNumber < 20) && (RandomNumber > 0))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(ScarpItem.block));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 40) && (RandomNumber > 20))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Blocks.STONE));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 50) && (RandomNumber > 40))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Blocks.SPRUCE_SAPLING));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 60) && (RandomNumber > 50))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Items.COOKIE));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 70) && (RandomNumber > 60))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Blocks.MOSSY_COBBLESTONE));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 75) && (RandomNumber > 70))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Items.IRON_HOE));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 80) && (RandomNumber > 75))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Items.GOLDEN_PICKAXE));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 90) && (RandomNumber > 80))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Items.BRICK));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 95) && (RandomNumber > 90))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Items.COOKED_CHICKEN));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 97) && (RandomNumber > 95))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(Blocks.CREEPER_HEAD));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		} else if (((RandomNumber < 100) && (RandomNumber > 97))) {
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(UniversalMatterItem.block));
				entityToSpawn.setPickupDelay((int) 0);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
