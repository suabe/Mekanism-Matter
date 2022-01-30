
package mekanismmatter.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import mekanismmatter.block.MassFabricatorBlock;

import mekanismmatter.MekanismmatterModElements;

@MekanismmatterModElements.ModElement.Tag
public class MekanismMatterItemGroup extends MekanismmatterModElements.ModElement {
	public MekanismMatterItemGroup(MekanismmatterModElements instance) {
		super(instance, 31);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabmekanism_matter") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(MassFabricatorBlock.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
