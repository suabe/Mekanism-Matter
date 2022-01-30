
package mekanismmatter.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import mekanismmatter.MekanismmatterMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ReplicatorGUINewGuiWindow extends ContainerScreen<ReplicatorGUINewGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public ReplicatorGUINewGuiWindow(ReplicatorGUINewGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/replicator_gui_new.png");
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 15, 0, 0, 18, 48, 18, 48);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/small_med.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 16, 0, 0, 16, 46, 16, 46);
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/inner_screen.png"));
		this.blit(ms, this.guiLeft + 49, this.guiTop + 15, 0, 0, 76, 50, 76, 50);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/monitor_outer.png"));
		this.blit(ms, this.guiLeft + 48, this.guiTop + 14, 0, 0, 78, 52, 78, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/slot_replication.png"));
		this.blit(ms, this.guiLeft + 78, this.guiTop + 45, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input_2.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 64, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/overlay_plus.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 64, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/extra.png"));
		this.blit(ms, this.guiLeft + 132, this.guiTop + 64, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/output.png"));
		this.blit(ms, this.guiLeft + 132, this.guiTop + 35, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/replicator_bar.png"));
		this.blit(ms, this.guiLeft + 48, this.guiTop + 69, 0, 0, 78, 10, 78, 10);

		 double progress = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "time12")) / 1.28);
		int progress1 = (int)progress;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/replicator_barfill.png"));
		this.blit(ms, this.guiLeft + 49, this.guiTop + 70, 0, 0, progress1, 8, progress1, 8);
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
		}.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) / 19230.7);
		int energy1 = 52 - (int)energy;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/vertical_power.png"));
		this.blit(ms, this.guiLeft + 163, this.guiTop + 16, 0, 0, 4, 52, 4, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_negative.png"));
		this.blit(ms, this.guiLeft + 163, this.guiTop + 16, 0, 0, 4, energy1, 4, energy1);
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
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "progress")) + "", 52, 16, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "umneeded")) + "", 52, 25, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "energyneeded")) + "", 52, 34, -16711801);
		this.font.drawString(ms, "Matter Synthesizer", 49, 4, -12829636);

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
		this.addButton(new Button(this.guiLeft + 26, this.guiTop + 15, 20, 20, new StringTextComponent("§a\u2192"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new ReplicatorGUINewGui.ButtonPressedMessage(0, x, y, z));
				ReplicatorGUINewGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 26, this.guiTop + 37, 20, 20, new StringTextComponent("§b\u2192"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new ReplicatorGUINewGui.ButtonPressedMessage(1, x, y, z));
				ReplicatorGUINewGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 26, this.guiTop + 59, 20, 20, new StringTextComponent("§c\u2b1b"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new ReplicatorGUINewGui.ButtonPressedMessage(2, x, y, z));
				ReplicatorGUINewGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 98, this.guiTop + 44, 20, 20, new StringTextComponent(">"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new ReplicatorGUINewGui.ButtonPressedMessage(3, x, y, z));
				ReplicatorGUINewGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 55, this.guiTop + 44, 20, 20, new StringTextComponent("<"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new ReplicatorGUINewGui.ButtonPressedMessage(4, x, y, z));
				ReplicatorGUINewGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
	}
}
