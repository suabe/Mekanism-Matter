
package mekanismmatter.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.Item;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.Block;

import mekanismmatter.MekanismmatterModElements;

@MekanismmatterModElements.ModElement.Tag
public class UniversalMatterFluidBlock extends MekanismmatterModElements.ModElement {
	@ObjectHolder("mekanismmatter:universal_matter_fluid")
	public static final FlowingFluidBlock block = null;
	@ObjectHolder("mekanismmatter:universal_matter_fluid_bucket")
	public static final Item bucket = null;
	public static FlowingFluid flowing = null;
	public static FlowingFluid still = null;
	private ForgeFlowingFluid.Properties fluidproperties = null;
	public UniversalMatterFluidBlock(MekanismmatterModElements instance) {
		super(instance, 21);
		FMLJavaModLoadingContext.get().getModEventBus().register(new FluidRegisterHandler());
	}
	private static class FluidRegisterHandler {
		@SubscribeEvent
		public void registerFluids(RegistryEvent.Register<Fluid> event) {
			event.getRegistry().register(still);
			event.getRegistry().register(flowing);
		}
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(still, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(flowing, RenderType.getTranslucent());
	}

	@Override
	public void initElements() {
		fluidproperties = new ForgeFlowingFluid.Properties(() -> still, () -> flowing, FluidAttributes
				.builder(new ResourceLocation("mekanismmatter:blocks/matter_still2"), new ResourceLocation("mekanismmatter:blocks/matter_flow2"))
				.luminosity(0).density(10000).viscosity(4000).temperature(300).gaseous().rarity(Rarity.COMMON)).explosionResistance(100f).tickRate(5)
						.levelDecreasePerBlock(1).slopeFindDistance(4).block(() -> block);
		still = (FlowingFluid) new ForgeFlowingFluid.Source(fluidproperties).setRegistryName("universal_matter_fluid");
		flowing = (FlowingFluid) new ForgeFlowingFluid.Flowing(fluidproperties).setRegistryName("universal_matter_fluid_flowing");
		elements.blocks
				.add(() -> new FlowingFluidBlock(still, Block.Properties.create(Material.LAVA).hardnessAndResistance(100f).setLightLevel(s -> 0)) {
				}.setRegistryName("universal_matter_fluid"));
	}
}
