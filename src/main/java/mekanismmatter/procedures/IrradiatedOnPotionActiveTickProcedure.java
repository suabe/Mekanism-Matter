package mekanismmatter.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import mekanismmatter.MekanismmatterMod;

import java.util.Map;

public class IrradiatedOnPotionActiveTickProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MekanismmatterMod.LOGGER.warn("Failed to load dependency entity for procedure IrradiatedOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.GENERIC, (float) 2);
	}
}
