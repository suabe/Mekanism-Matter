
package mekanismmatter.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import mekanismmatter.itemgroup.MekanismMatterItemGroup;

import mekanismmatter.MekanismmatterModElements;

import java.util.List;

@MekanismmatterModElements.ModElement.Tag
public class ScrapboxItem extends MekanismmatterModElements.ModElement {
	@ObjectHolder("mekanismmatter:scrapbox")
	public static final Item block = null;
	public ScrapboxItem(MekanismmatterModElements instance) {
		super(instance, 10);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MekanismMatterItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("scrapbox");
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

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("\u00A77Multiplier: 10x"));
		}
	}
}
