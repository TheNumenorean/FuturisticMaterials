// Date: 12/23/2015 10:45:21 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package net.minecraft.src;

public class ModelLightBar extends ModelBase
{
  //fields
    ModelRenderer Light;
    ModelRenderer Side1;
    ModelRenderer Side1;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
  
  public ModelLightBar()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Light = new ModelRenderer(this, 0, 0);
      Light.addBox(0F, 0F, 0F, 16, 1, 4);
      Light.setRotationPoint(-8F, 0F, -2F);
      Light.setTextureSize(64, 64);
      Light.mirror = true;
      setRotation(Light, 0F, 0F, 0F);
      Side1 = new ModelRenderer(this, 0, 5);
      Side1.addBox(0F, 0F, 0F, 16, 1, 2);
      Side1.setRotationPoint(-8F, 0F, 2F);
      Side1.setTextureSize(64, 64);
      Side1.mirror = true;
      setRotation(Side1, -0.5410521F, 0F, 0F);
      Side1 = new ModelRenderer(this, 0, 5);
      Side1.addBox(0F, 0F, 0F, 16, 1, 2);
      Side1.setRotationPoint(8F, 0F, -2F);
      Side1.setTextureSize(64, 64);
      Side1.mirror = true;
      setRotation(Side1, -0.5410521F, 3.141593F, 0F);
      Shape1 = new ModelRenderer(this, 0, 8);
      Shape1.addBox(0F, 0F, 0F, 16, 1, 3);
      Shape1.setRotationPoint(-8F, -2F, -7F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, -0.7853982F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 12);
      Shape2.addBox(0F, 0F, 0F, 16, 3, 1);
      Shape2.setRotationPoint(-8F, -2F, -8F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 12);
      Shape3.addBox(0F, 0F, 0F, 16, 3, 1);
      Shape3.setRotationPoint(-8F, 1F, -8F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 1.570796F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Light.render(f5);
    Side1.render(f5);
    Side1.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
