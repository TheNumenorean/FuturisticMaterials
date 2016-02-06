package net.thenumenorean.futuristicmaterials.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Date: 1/1/2014 12:02:46 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

public class LightBarTileEntityModel_flat extends ModelBase {
	// fields
	ModelRenderer flatLight;
	ModelRenderer flatSide1;
	ModelRenderer flatSide2;
	ModelRenderer cornerLight;
	ModelRenderer cornerSide1;
	ModelRenderer cornerSide2;

	public LightBarTileEntityModel_flat() {
		textureWidth = 64;
		textureHeight = 64;

		flatLight = new ModelRenderer(this, 0, 0);
		flatLight.addBox(0F, 0F, 0F, 16, 1, 4);
		flatLight.setRotationPoint(-8F, 0F, -2F);
		flatLight.setTextureSize(64, 64);
		flatLight.mirror = true;
		setRotation(flatLight, 0F, 0F, 0F);
		flatSide1 = new ModelRenderer(this, 0, 5);
		flatSide1.addBox(0F, 0F, 0F, 16, 1, 2);
		flatSide1.setRotationPoint(-8F, 0F, 2F);
		flatSide1.setTextureSize(64, 64);
		flatSide1.mirror = true;
		setRotation(flatSide1, -0.5410521F, 0F, 0F);
		flatSide2 = new ModelRenderer(this, 0, 5);
		flatSide2.addBox(0F, 0F, 0F, 16, 1, 2);
		flatSide2.setRotationPoint(8F, 0F, -2F);
		flatSide2.setTextureSize(64, 64);
		flatSide2.mirror = true;
		setRotation(flatSide2, -0.5410521F, 3.141593F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		flatLight.render(f5);
		flatSide1.render(f5);
		flatSide2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

}