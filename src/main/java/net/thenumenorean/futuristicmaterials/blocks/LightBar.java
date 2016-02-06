package net.thenumenorean.futuristicmaterials.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.thenumenorean.futuristicmaterials.FuturisticMaterials;
import net.thenumenorean.futuristicmaterials.client.gui.GuiHandler;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LightBar extends BlockContainer {

	public LightBar() {
		super(Material.glass);
		setStepSound(Block.soundTypeMetal);
		setBlockName("futuristicmaterials.lightBar");
		setBlockTextureName("futuristicmaterials:lightBar");
		setHarvestLevel("pickaxe", 1);
		setLightOpacity(0);

		GameRegistry.registerTileEntity(LightBarTileEntity.class, "lightBarTileEntity");
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new LightBarTileEntity();
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase player, ItemStack is) {
		int dir = MathHelper.floor_double((player.rotationYaw * 4F) / 360F + 0.5D) & 3;
		w.setBlockMetadataWithNotify(x, y, z, dir, 0);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return meta;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new LightBarTileEntity();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
		}
		return true;

	}
	
	@Override
	public boolean canPlaceBlockOnSide(World w, int x, int y, int z, int side) {
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		switch(dir) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case NORTH:
			z++;
			break;
		case SOUTH:
			z--;
			break;
		case EAST:
			x--;
			break;
		case WEST:
			x++;
			break;
		default:
			break;
		}
		
		return w.getBlock(x, y, z).isSideSolid(w, x, y, z, dir.getOpposite());
	}
	
	@Override
	public boolean isSideSolid(IBlockAccess w, int x, int y, int z, ForgeDirection dir) {
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z) {
		
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
	
	@Override
	public int getLightValue(IBlockAccess w, int x, int y, int z) {
		return ((LightBarTileEntity)w.getTileEntity(x, y, z)).getEmittedLight();
	}
}
