package net.thenumenorean.futuristicmaterials.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.thenumenorean.futuristicmaterials.blocks.FMBlocks;

public class LightBarTileEntityRenderer extends TileEntitySpecialRenderer {
	private LightBarTileEntityModel_flat model_flat;
	private LightBarTileEntityModel_corner model_corner;

	public LightBarTileEntityRenderer() {
		model_flat = new LightBarTileEntityModel_flat();
		model_corner = new LightBarTileEntityModel_corner();
	}

	// This method is called when minecraft renders a tile entity
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double d, double d1, double d2, float f) {
		GL11.glPushMatrix();
		// This will move our renderer so that it will be on proper place in the
		// world
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		LightBarTileEntity tileEntityYour = (LightBarTileEntity) tileEntity;
		/*
		 * Note that true tile entity coordinates (tileEntity.xCoord, etc) do
		 * not match to render coordinates (d, etc) that are calculated as [true
		 * coordinates] - [player coordinates (camera coordinates)]
		 */
		renderBlock(tileEntityYour, tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord,
				FMBlocks.light_bar);
		GL11.glPopMatrix();

	}

	// And this method actually renders your tile entity
	public void renderBlock(LightBarTileEntity tl, World world, int i, int j, int k, Block block) {

		int dir = 0;
		if (world != null && world.blockExists(i, j, k)) {
			Tessellator tessellator = Tessellator.instance;
			// This will make your block brightness dependent from surroundings
			// lighting.
			float f = block.getMixedBrightnessForBlock(world, i, j, k);
			int l = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
			int l1 = l % 65536;
			int l2 = l / 65536;
			tessellator.setColorOpaque_F(f, f, f);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1, l2);
			
			GL11.glPushMatrix();
			GL11.glTranslatef(0.5F, 0.0625F, 0.5F);
			GL11.glRotatef(-180F, 0F, 0F, 1F);
			this.bindTexture(new ResourceLocation("futuristicmaterials", "textures/blocks/LightBar.png"));
			model_corner.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			
			
		} else {

			GL11.glPushMatrix();
			GL11.glTranslatef(0.5F, 0.0625F, 0.5F);
			GL11.glRotatef(-180F, 0F, 0F, 1F);
			this.bindTexture(new ResourceLocation("futuristicmaterials", "textures/blocks/LightBar.png"));
			model_flat.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
		}
	}
}
