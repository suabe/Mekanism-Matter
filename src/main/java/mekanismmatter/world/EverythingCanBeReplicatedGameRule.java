package mekanismmatter.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import mekanismmatter.MekanismmatterModElements;

import java.lang.reflect.Method;

@MekanismmatterModElements.ModElement.Tag
public class EverythingCanBeReplicatedGameRule extends MekanismmatterModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("everythingCanBeReplicated",
			GameRules.Category.PLAYER, create(false));
	public EverythingCanBeReplicatedGameRule(MekanismmatterModElements instance) {
		super(instance, 107);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
