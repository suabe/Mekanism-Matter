
package mekanismmatter.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;

import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class RecyclerGUINewGuiWindow extends ContainerScreen<RecyclerGUINewGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public RecyclerGUINewGuiWindow(RecyclerGUINewGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/recycler_gui_new.png");
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar.png"));
		this.blit(ms, this.guiLeft + 74, this.guiTop + 38, 0, 0, 25, 9, 25, 9);

		double progress = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "ticks")) / 3.47);
		int progress1 = (int)progress;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/barfill.png"));
		this.blit(ms, this.guiLeft + 75, this.guiTop + 39, 0, 0, progress1, 7, progress1, 7);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input.png"));
		this.blit(ms, this.guiLeft + 42, this.guiTop + 34, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/output.png"));
		this.blit(ms, this.guiLeft + 114, this.guiTop + 34, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/recycle.png"));
		this.blit(ms, this.guiLeft + 79, this.guiTop + 52, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/energybar_vertical.png"));
		this.blit(ms, this.guiLeft + 162, this.guiTop + 15, 0, 0, 6, 54, 6, 54);

		double energy = (double) ((new Object() {
			public int getEnergyStored(IWorld world, BlockPos pos) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
				return _retval.get();
			}
		}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) / 1923.07);
		int energy1 = 52 - (int)energy;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/vertical_power.png"));
		this.blit(ms, this.guiLeft + 163, this.guiTop + 16, 0, 0, 4, 52, 4, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_negative.png"));
		this.blit(ms, this.guiLeft + 163, this.guiTop + 16, 0, 0, 4, energy1, 4, energy1);


		
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/extra.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 34, 0, 0, 18, 18, 18, 18);
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
		this.font.drawString(ms, "Recycler", 67, 4, -12829636);
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
