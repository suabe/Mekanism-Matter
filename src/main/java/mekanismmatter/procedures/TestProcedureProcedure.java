package mekanismmatter.procedures;

import mekanismmatter.MekanismmatterMod;

import java.util.Map;

public class TestProcedureProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency x for procedure TestProcedure!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency z for procedure TestProcedure!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		double fgdg = 0;
		String mmm = "";
		fgdg = (double) ((x * z) / 10000);
	}
}
