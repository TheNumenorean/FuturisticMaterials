package net.thenumenorean.futuristicmaterials.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class TransparentAlmuninum extends Block {

	public TransparentAlmuninum() {
		super(Material.glass);

		setHardness(40.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("futuristicmaterials.transparentAluminum");
		setBlockTextureName("futuristicmaterials:transparentAluminum");
		setHarvestLevel("pickaxe", 3);
		setResistance(160.0F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess w, int x, int y, int z, int side) {
		Block b = w.getBlock(x, y, z);
		return !b.isAssociatedBlock(this) && !b.isSideSolid(w, x, y, z, ForgeDirection.getOrientation(side).getOpposite());
	}

}
