
package mekanismmatter.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

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

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ReplicatorGuiWindow extends ContainerScreen<ReplicatorGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = ReplicatorGui.guistate;
	public ReplicatorGuiWindow(ReplicatorGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/replicator.png");
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/matterfill_full.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 16, 0, 0, 16, 46, 16, 46);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/small_med.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 16, 0, 0, 16, 46, 16, 46);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input_2.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 64, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/overlay_plus.png"));
		this.blit(ms, this.guiLeft + 6, this.guiTop + 64, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/inner_screen.png"));
		this.blit(ms, this.guiLeft + 49, this.guiTop + 16, 0, 0, 76, 50, 76, 50);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/monitor_outer.png"));
		this.blit(ms, this.guiLeft + 48, this.guiTop + 15, 0, 0, 78, 52, 78, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/replicator_bar.png"));
		this.blit(ms, this.guiLeft + 36, this.guiTop + 70, 0, 0, 78, 10, 78, 10);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/arrow_right_button.png"));
		this.blit(ms, this.guiLeft + 98, this.guiTop + 46, 0, 0, -1, -1, -1, -1);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/arrow_left_button.png"));
		this.blit(ms, this.guiLeft + 66, this.guiTop + 46, 0, 0, -1, -1, -1, -1);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/slot_replication.png"));
		this.blit(ms, this.guiLeft + 78, this.guiTop + 45, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/replicator_barfill.png"));
		this.blit(ms, this.guiLeft + 37, this.guiTop + 71, 0, 0, 76, 8, 76, 8);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/energybar_vertical.png"));
		this.blit(ms, this.guiLeft + 161, this.guiTop + 15, 0, 0, 6, 54, 6, 54);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/vertical_power.png"));
		this.blit(ms, this.guiLeft + 162, this.guiTop + 16, 0, 0, 4, 52, 4, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/output.png"));
		this.blit(ms, this.guiLeft + 134, this.guiTop + 33, 0, 0, 18, 18, 18, 18);
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
		this.font.drawString(ms, "Replicator", 62, 4, -12829636);
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
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "umneeded")) + "", 52, 24, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "energyneeded")) + "", 52, 32, -16711801);
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
		this.addButton(new Button(this.guiLeft + 66, this.guiTop + 46, 30, 20, new StringTextComponent("'"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new ReplicatorGui.ButtonPressedMessage(0, x, y, z));
				ReplicatorGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 98, this.guiTop + 46, 30, 20, new StringTextComponent("'"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new ReplicatorGui.ButtonPressedMessage(1, x, y, z));
				ReplicatorGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
