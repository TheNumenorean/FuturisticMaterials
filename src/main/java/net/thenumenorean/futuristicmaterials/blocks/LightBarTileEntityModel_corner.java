package net.thenumenorean.futuristicmaterials.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Date: 1/1/2014 12:02:46 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

public class LightBarTileEntityModel_corner extends ModelBase {
	// fields
	ModelRenderer flatLight;
	ModelRenderer flatSide1;
	ModelRenderer flatSide2;
	ModelRenderer cornerLight;
	ModelRenderer cornerSide1;
	ModelRenderer cornerSide2;

	public LightBarTileEntityModel_corner() {
		textureWidth = 64;
		textureHeight = 64;
		
		cornerLight = new ModelRenderer(this, 0, 8);
		cornerLight.addBox(0F, 0F, 0F, 16, 1, 3);
		cornerLight.setRotationPoint(-8F, -2F, -7F);
		cornerLight.setTextureSize(64, 64);
		cornerLight.mirror = true;
		setRotation(cornerLight, -0.7853982F, 0F, 0F);
		cornerSide1 = new ModelRenderer(this, 0, 12);
		cornerSide1.addBox(0F, 0F, 0F, 16, 3, 1);
		cornerSide1.setRotationPoint(-8F, -2F, -8F);
		cornerSide1.setTextureSize(64, 64);
		cornerSide1.mirror = true;
		setRotation(cornerSide1, 0F, 0F, 0F);
		cornerSide2 = new ModelRenderer(this, 0, 12);
		cornerSide2.addBox(0F, 0F, 0F, 16, 3, 1);
		cornerSide2.setRotationPoint(-8F, 1F, -8F);
		cornerSide2.setTextureSize(64, 64);
		cornerSide2.mirror = true;
		setRotation(cornerSide2, 1.570796F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		cornerLight.render(f5);
		cornerSide1.render(f5);
		cornerSide2.render(f5);
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