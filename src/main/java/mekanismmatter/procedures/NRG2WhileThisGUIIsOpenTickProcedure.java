package mekanismmatter.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.block.BlockState;

import mekanismmatter.MekanismmatterMod;

import java.util.Map;
import java.util.HashMap;

public class NRG2WhileThisGUIIsOpenTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency guistate for procedure NRG2WhileThisGUIIsOpenTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency x for procedure NRG2WhileThisGUIIsOpenTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency y for procedure NRG2WhileThisGUIIsOpenTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency z for procedure NRG2WhileThisGUIIsOpenTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency world for procedure NRG2WhileThisGUIIsOpenTick!");
			return;
		}
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Rate");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText())) >= 0)) {
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("RateNBT", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert((new Object() {
						public String getText() {
							TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:d");
							if (_tf != null) {
								return _tf.getText();
							}
							return "";
						}
					}.getText())));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("RateTextNBT", (new Object() {
						public String getText() {
							TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:d");
							if (_tf != null) {
								return _tf.getText();
							}
							return "";
						}
					}.getText()));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
	}
}
