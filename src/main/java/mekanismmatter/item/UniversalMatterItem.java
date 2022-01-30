
package mekanismmatter.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import mekanismmatter.itemgroup.MekanismMatterItemGroup;

import mekanismmatter.MekanismmatterModElements;

@MekanismmatterModElements.ModElement.Tag
public class UniversalMatterItem extends MekanismmatterModElements.ModElement {
	@ObjectHolder("mekanismmatter:universal_matter")
	public static final Item block = null;
	public UniversalMatterItem(MekanismmatterModElements instance) {
		super(instance, 11);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MekanismMatterItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("universal_matter");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
