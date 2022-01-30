
package mekanismmatter.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import java.util.concurrent.atomic.AtomicInteger;

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
import net.minecraft.item.FlintAndSteelItem;

@OnlyIn(Dist.CLIENT)
public class MassFabricatorGUINewGuiWindow extends ContainerScreen<MassFabricatorGUINewGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public MassFabricatorGUINewGuiWindow(MassFabricatorGUINewGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 176;
	}
	private static final ResourceLocation texture = new ResourceLocation("mekanismmatter:textures/mass_fabricator_gui_new.png");
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
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/mass_fabricator_progressbar.png"));
		this.blit(ms, this.guiLeft + 10, this.guiTop + 81, 0, 0, 154, 10, 154, 10);

		double energyprogress1 = (double) ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energystored")) / 164473.68);
		int energyprogress = (int)energyprogress1;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/bar_fill.png"));
		this.blit(ms, this.guiLeft + 11, this.guiTop + 82, 0, 0, energyprogress, 8, energyprogress, 8);
		
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/inner_screen.png"));
		this.blit(ms, this.guiLeft + 42, this.guiTop + 16, 0, 0, 76, 50, 76, 50);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/monitor_outer.png"));
		this.blit(ms, this.guiLeft + 41, this.guiTop + 15, 0, 0, 78, 52, 78, 52);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/tank_holder_standart.png"));
		this.blit(ms, this.guiLeft + 151, this.guiTop + 11, 0, 0, 18, 60, 18, 60);

		double fluidstorage = (double) ((new Object() {
			public int getFluidTankLevel(BlockPos pos, int tank) {
				AtomicInteger _retval = new AtomicInteger(0);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null)
					_ent.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)
							.ifPresent(capability -> _retval.set(capability.getFluidInTank(tank).getAmount()));
				return _retval.get();
			}
		}.getFluidTankLevel(new BlockPos((int) x, (int) y, (int) z), (int) 1)) / 137.93);
        int fluidstorage1 = (int)fluidstorage;
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/matterfill_full.png"));
		this.blit(ms, this.guiLeft + 152, this.guiTop + 70 - fluidstorage1, 0, 0, 16, fluidstorage1, 16, fluidstorage1);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/standard.png"));
		this.blit(ms, this.guiLeft + 152, this.guiTop + 12, 0, 0, 16, 58, 16, 58);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/input.png"));
		this.blit(ms, this.guiLeft + 14, this.guiTop + 35, 0, 0, 18, 18, 18, 18);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("mekanismmatter:textures/output.png"));
		this.blit(ms, this.guiLeft + 128, this.guiTop + 35, 0, 0, 18, 18, 18, 18);
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
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "progress")) + "", 9, 70, -12829636);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "efficiency")) + "", 44, 18, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "mode")) + "", 44, 26, -16711801);
		this.font.drawString(ms, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "umstored")) + "", 44, 34, -16711801);
		this.font.drawString(ms, "Mass Fabricator", 49, 4, -12829636);
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
		this.addButton(new Button(this.guiLeft + 45, this.guiTop + 44, 70, 20, new StringTextComponent("Switch Mode"), e -> {
			if (true) {
				MekanismmatterMod.PACKET_HANDLER.sendToServer(new MassFabricatorGUINewGui.ButtonPressedMessage(0, x, y, z));
				MassFabricatorGUINewGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
