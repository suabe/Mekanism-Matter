
package mekanismmatter.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import mekanismmatter.item.NaquadahItem;

@Mod.EventBusSubscriber
public class NaquadahFuelFuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == NaquadahItem.block)
			event.setBurnTime(32000);
	}
}
