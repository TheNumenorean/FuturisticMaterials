package net.thenumenorean.futuristicmaterials.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class AluminumBlock extends Block {

	public AluminumBlock() {
		super(Material.iron);

		setHardness(20.0F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("futuristicmaterials.blockAluminum");
		setBlockTextureName("futuristicmaterials:blockAluminum");
		setHarvestLevel("pickaxe", 2);
		setResistance(20.0F);
	}

}
