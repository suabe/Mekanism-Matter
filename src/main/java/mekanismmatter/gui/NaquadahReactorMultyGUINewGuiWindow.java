
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
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import mekanismmatter.MekanismmatterMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class NaquadahReactorMultyGUINewGuiWindow extends ContainerScreen<NaquadahReactorMultyGUINewGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	TextFieldWidget d;
	public NaquadahReactorMultyGUINewGuiWindow(NaquadahReactorMultyGUINewGui.GuiContainerMod container, PlayerInventory inventory,
			ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 220;
		this.ySize = 195;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/naquadah_reactor_multy_gui_new.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		d.render(ms, mouseX, mouseY, partialTicks);
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 8, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 25, this.guiTop + 8, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder_54px.png"));
		this.blit(ms, this.guiLeft + 172, this.guiTop + 112, 0, 0, 18, 54, 18, 54);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/small_med_52px.png"));
		this.blit(ms, this.guiLeft + 173, this.guiTop + 113, 0, 0, 16, 52, 16, 52);
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/naquadah_fluid.png"));
		this.blit(ms, this.guiLeft + 173, this.guiTop + 113 + 52 - fluidstorage1, 0, 0, 16, fluidstorage1, 16, fluidstorage1);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder_54px.png"));
		this.blit(ms, this.guiLeft + 194, this.guiTop + 112, 0, 0, 18, 54, 18, 54);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/small_med_52px.png"));
		this.blit(ms, this.guiLeft + 195, this.guiTop + 113, 0, 0, 16, 52, 16, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 196, this.guiTop + 8, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_vertical_verylong_18px.png"));
		this.blit(ms, this.guiLeft + 178, this.guiTop + 8, 0, 0, 16, 85, 16, 85);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/efficiency_icon.png"));
		this.blit(ms, this.guiLeft + 177, this.guiTop + 92, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/temp_icon.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 94, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/atom_icon.png"));
		this.blit(ms, this.guiLeft + 25, this.guiTop + 94, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/fuel_icon.png"));
		this.blit(ms, this.guiLeft + 195, this.guiTop + 93, 0, 0, 16, 16, 16, 16);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/naquadah_reactor_screen.png"));
		this.blit(ms, this.guiLeft + 45, this.guiTop + 18, 0, 0, 128, 64, 128, 64);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/naquadah_reactor_inner_screen.png"));
		this.blit(ms, this.guiLeft + 46, this.guiTop + 19, 0, 0, 126, 62, 126, 62);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/efficiency_bar.png"));
		this.blit(ms, this.guiLeft + 179, this.guiTop + 9, 0, 0, 14, 83, 14, 83);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/fuel_bar.png"));
		this.blit(ms, this.guiLeft + 197, this.guiTop + 9, 0, 0, 14, 83, 14, 83);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/temp_bar.png"));
		this.blit(ms, this.guiLeft + 8, this.guiTop + 9, 0, 0, 14, 83, 14, 83);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/gammarays_bar.png"));
		this.blit(ms, this.guiLeft + 26, this.guiTop + 9, 0, 0, 14, 83, 14, 83);
		double temp = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Temprature")) / 141.17);
		int temp1 = (int)temp;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 7, this.guiTop + 88 - temp1, 0, 0, 16, 8, 16, 8);
		double Gamma = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "GammaRays")) / 1694117.64);
		int Gamma1 = (int)Gamma;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 25, this.guiTop + 88 - Gamma1, 0, 0, 16, 8, 16, 8);
		double efcy = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "Efficiency")) / 345.21);
		int efcy1 = 85 - (int)efcy;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 178, this.guiTop + 88 - efcy1, 0, 0, 16, 8, 16, 8);
		double fuel = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "FuelComsumption")) / 11.41);
		int fuell1 = (int)fuel;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_slider.png"));
		this.blit(ms, this.guiLeft + 196, this.guiTop + 88 - fuell1, 0, 0, 16, 8, 16, 8);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input.png"));
		this.blit(ms, this.guiLeft + 172, this.guiTop + 170, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input_2.png"));
		this.blit(ms, this.guiLeft + 194, this.guiTop + 170, 0, 0, 18, 18, 18, 18);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (d.isFocused())
			return d.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		d.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Naquadah Reactor", 69, 4, -12829636);
		this.font.drawString(ms, "Rate:", 96, 92, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line1")) + "", 48, 21, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line2")) + "", 48, 30, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line3")) + "", 48, 39, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line4")) + "", 48, 48, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line5")) + "", 48, 57, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "line6")) + "", 48, 66, -16711801);
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
		boolean safety =  (boolean) (new Object() {
			public boolean getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "safetymode"));
		boolean onoff1 =  (boolean) (new Object() {
			public boolean getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "onoff"));

		if (onoff1 == true) {
		this.addButton(new Button(this.guiLeft + 45, this.guiTop + 87, 20, 20, new StringTextComponent("§a\u23fb"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new NaquadahReactorMultyGUINewGui.ButtonPressedMessage(0, x, y, z));
				NaquadahReactorMultyGUINewGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		} else {
		this.addButton(new Button(this.guiLeft + 45, this.guiTop + 87, 20, 20, new StringTextComponent("§c\u23fb"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new NaquadahReactorMultyGUINewGui.ButtonPressedMessage(0, x, y, z));
				NaquadahReactorMultyGUINewGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		}
		
		if (safety == true) {
		this.addButton(new Button(this.guiLeft + 66, this.guiTop + 87, 20, 20, new StringTextComponent("§a\u26a0"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new NaquadahReactorMultyGUINewGui.ButtonPressedMessage(1, x, y, z));
				NaquadahReactorMultyGUINewGui.handleButtonAction(entity, 1, x, y, z);
			}
		})); } else {
		this.addButton(new Button(this.guiLeft + 66, this.guiTop + 87, 20, 20, new StringTextComponent("§c\u26a0"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new NaquadahReactorMultyGUINewGui.ButtonPressedMessage(1, x, y, z));
				NaquadahReactorMultyGUINewGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		}
		d = new TextFieldWidget(this.font, this.guiLeft + 122, this.guiTop + 87, 29, 20, new StringTextComponent(""));
		NaquadahReactorMultyGUINewGui.guistate.put("text:d", d);
		d.setMaxStringLength(3);
		this.children.add(this.d);
		this.addButton(new Button(this.guiLeft + 153, this.guiTop + 87, 20, 20, new StringTextComponent("\u2794"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new NaquadahReactorMultyGUINewGui.ButtonPressedMessage(2, x, y, z));
				NaquadahReactorMultyGUINewGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
