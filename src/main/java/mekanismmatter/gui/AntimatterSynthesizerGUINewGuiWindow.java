
package mekanismmatter.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;


import java.util.concurrent.atomic.AtomicInteger;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import mekanismmatter.MekanismmatterMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class AntimatterSynthesizerGUINewGuiWindow extends ContainerScreen<AntimatterSynthesizerGUINewGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public AntimatterSynthesizerGUINewGuiWindow(AntimatterSynthesizerGUINewGui.GuiContainerMod container, PlayerInventory inventory,
			ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/antimatter_synthesizer_gui_new.png");
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder_standart.png"));
		this.blit(ms, this.guiLeft + 150, this.guiTop + 13, 0, 0, 18, 60, 18, 60);
		double fluidstorage = (double) ((new Object() {
			public int getFluidTankLevel(BlockPos pos, int tank) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _retval.set(capability.getFluidInTank(tank).getAmount()));
				return _retval.get();
			}
		}.getFluidTankLevel(new BlockPos((int) x, (int) y, (int) z), (int) 1)) / 153.84);
        int fluidstorage1 = (int)fluidstorage;

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/matterfill_full_58px.png"));
		this.blit(ms, this.guiLeft + 151, this.guiTop + 14 + 58 - fluidstorage1, 0, 0, 16, fluidstorage1, 16, fluidstorage1);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/standard.png"));
		this.blit(ms, this.guiLeft + 151, this.guiTop + 14, 0, 0, 16, 58, 16, 58);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/large_right.png"));
		this.blit(ms, this.guiLeft + 62, this.guiTop + 41, 0, 0, 48, 8, 48, 8);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input.png"));
		this.blit(ms, this.guiLeft + 33, this.guiTop + 36, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/output.png"));
		this.blit(ms, this.guiLeft + 123, this.guiTop + 36, 0, 0, 18, 18, 18, 18);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/energybar_vertical.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 15, 0, 0, 6, 54, 6, 54);

		double energy = (double) ((new Object() {
			public int getEnergyStored(IWorld world, BlockPos pos) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
				return _retval.get();
			}
		}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) / 1538.4);
		int energy1 = 52 - (int)energy;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/vertical_power.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 16, 0, 0, 4, 52, 4, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_negative.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 16, 0, 0, 4, energy1, 4, energy1);
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
		this.font.drawString(ms, "Antimatter Synthesizer", 32, 4, -12829636);
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
		boolean mmm = (boolean) (new Object() {
			public boolean getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "fluidmode"));
		if (mmm == false) {
		this.addButton(new Button(this.guiLeft + 62, this.guiTop + 61, 50, 20, new StringTextComponent("Solid"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new AntimatterSynthesizerGUINewGui.ButtonPressedMessage(0, x, y, z));
				AntimatterSynthesizerGUINewGui.handleButtonAction(entity, 0, x, y, z);
			}
		})); } else {
this.addButton(new Button(this.guiLeft + 62, this.guiTop + 61, 50, 20, new StringTextComponent("Fluid"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new AntimatterSynthesizerGUINewGui.ButtonPressedMessage(0, x, y, z));
				AntimatterSynthesizerGUINewGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		}
		
	}
}