
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
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import mekanismmatter.MekanismmatterMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class NaquadahReactorMultyGUIGuiWindow extends ContainerScreen<NaquadahReactorMultyGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = NaquadahReactorMultyGUIGui.guistate;
	TextFieldWidget Rate;
	public NaquadahReactorMultyGUIGuiWindow(NaquadahReactorMultyGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 218;
		this.ySize = 192;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/naquadah_reactor_multy_gui.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		Rate.render(ms, mouseX, mouseY, partialTicks);
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder_54px.png"));
		this.blit(ms, this.guiLeft + 171, this.guiTop + 109, 0, 0, 18, 54, 18, 54);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input.png"));
		this.blit(ms, this.guiLeft + 171, this.guiTop + 167, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/small_med_52px.png"));
		this.blit(ms, this.guiLeft + 172, this.guiTop + 110, 0, 0, 16, 52, 16, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/overlay_plus.png"));
		this.blit(ms, this.guiLeft + 171, this.guiTop + 167, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder_54px.png"));
		this.blit(ms, this.guiLeft + 192, this.guiTop + 109, 0, 0, 18, 54, 18, 54);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input_2.png"));
		this.blit(ms, this.guiLeft + 192, this.guiTop + 167, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/overlay_plus.png"));
		this.blit(ms, this.guiLeft + 192, this.guiTop + 167, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/small_med_52px.png"));
		this.blit(ms, this.guiLeft + 193, this.guiTop + 110, 0, 0, 16, 52, 16, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 7, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 25, this.guiTop + 7, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 193, this.guiTop + 7, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 175, this.guiTop + 7, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/temp_bar.png"));
		this.blit(ms, this.guiLeft + 8, this.guiTop + 8, 0, 0, 14, 83, 14, 83);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/temp_icon.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 92, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/gammarays_bar.png"));
		this.blit(ms, this.guiLeft + 26, this.guiTop + 8, 0, 0, 14, 83, 14, 83);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/efficiency_bar.png"));
		this.blit(ms, this.guiLeft + 176, this.guiTop + 8, 0, 0, 14, 83, 14, 83);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/efficiency_icon.png"));
		this.blit(ms, this.guiLeft + 175, this.guiTop + 93, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/fuel_bar.png"));
		this.blit(ms, this.guiLeft + 194, this.guiTop + 8, 0, 0, 14, 83, 14, 83);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/atom_icon.png"));
		this.blit(ms, this.guiLeft + 25, this.guiTop + 92, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 87, 0, 0, 16, 8, 16, 8);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 25, this.guiTop + 87, 0, 0, 16, 8, 16, 8);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 175, this.guiTop + 87, 0, 0, 16, 8, 16, 8);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 193, this.guiTop + 87, 0, 0, 16, 8, 16, 8);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/fuel_icon.png"));
		this.blit(ms, this.guiLeft + 193, this.guiTop + 93, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/naquadah_reactor_screen.png"));
		this.blit(ms, this.guiLeft + 44, this.guiTop + 17, 0, 0, 128, 64, 128, 64);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/naquadah_reactor_inner_screen.png"));
		this.blit(ms, this.guiLeft + 45, this.guiTop + 18, 0, 0, 126, 62, 126, 62);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (Rate.isFocused())
			return Rate.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		Rate.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Naquadah Reactor", 68, 4, -12829636);
		this.font.drawString(ms, "Rate:", 97, 90, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line1")) + "", 47, 20, -16711805);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line2")) + "", 47, 30, -16711805);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line3")) + "", 47, 40, -16711805);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line4")) + "", 47, 50, -16711805);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line5")) + "", 47, 60, -16711805);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line6")) + "", 47, 70, -16711805);
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
		this.addButton(new Button(this.guiLeft + 44, this.guiTop + 85, 50, 20, new StringTextComponent("On/Off"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new NaquadahReactorMultyGUIGui.ButtonPressedMessage(0, x, y, z));
				NaquadahReactorMultyGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		Rate = new TextFieldWidget(this.font, this.guiLeft + 124, this.guiTop + 85, 120, 20, new StringTextComponent(""));
		guistate.put("text:Rate", Rate);
		Rate.setMaxStringLength(32767);
		this.children.add(this.Rate);
		this.addButton(new Button(this.guiLeft + 44, this.guiTop + 85, 45, 20, new StringTextComponent("Safe"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new NaquadahReactorMultyGUIGui.ButtonPressedMessage(1, x, y, z));
				NaquadahReactorMultyGUIGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
