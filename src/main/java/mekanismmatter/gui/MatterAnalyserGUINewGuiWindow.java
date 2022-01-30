
package mekanismmatter.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class MatterAnalyserGUINewGuiWindow extends ContainerScreen<MatterAnalyserGUINewGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public MatterAnalyserGUINewGuiWindow(MatterAnalyserGUINewGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/matter_analyser_gui_new.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input_2.png"));
		this.blit(ms, this.guiLeft + 78, this.guiTop + 34, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 64, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 15, 0, 0, 18, 48, 18, 48);
		double fluidstorage = (double) ((new Object() {
			public int getFluidTankLevel(BlockPos pos, int tank) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _retval.set(capability.getFluidInTank(tank).getAmount()));
				return _retval.get();
			}
		}.getFluidTankLevel(new BlockPos((int) x, (int) y, (int) z), (int) 1)) / 173.91);
        int fluidstorage1 = (int)fluidstorage;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/matterfill_full.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 16 + 46 - fluidstorage1, 0, 0, 16, fluidstorage1, 16, fluidstorage1);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/small_med.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 16, 0, 0, 16, 46, 16, 46);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/long_energy_holder.png"));
		this.blit(ms, this.guiLeft + 46, this.guiTop + 73, 0, 0, 81, 6, 81, 6);
		double energy = (double) ((new Object() {
			public int getEnergyStored(IWorld world, BlockPos pos) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
				return _retval.get();
			}
		}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) / 12658.22);
		int energy1 = 79 - (int)energy;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/horizontal_power_long.png"));
		this.blit(ms, this.guiLeft + 47, this.guiTop + 74, 0, 0, 79, 4, 79, 4);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_negative.png"));
		this.blit(ms, this.guiLeft + 47 + 79 - energy1, this.guiTop + 74, 0, 0, energy1, 4, energy1, 4);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/vertical_rate_holder.png"));
		this.blit(ms, this.guiLeft + 155, this.guiTop + 13, 0, 0, 8, 60, 8, 60);
		double rate = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "productionin")) / 862.06);

		int rate1 = 58 - (int)rate;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/vertical_rate.png"));
		this.blit(ms, this.guiLeft + 156, this.guiTop + 14, 0, 0, 6, 58, 6, 58);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_negative.png"));
		this.blit(ms, this.guiLeft + 156, this.guiTop + 14, 0, 0, 6, rate1, 6, rate1);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/overlay_plus.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 64, 0, 0, 18, 18, 18, 18);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "production")) + "", 46, 60, -12829636);
		this.font.drawString(ms, "Matter Analyser", 49, 3, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
	}
}
