package net.thenumenorean.futuristicmaterials.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulator;
import net.thenumenorean.futuristicmaterials.blocks.ores.Ore;
import net.thenumenorean.futuristicmaterials.blocks.ores.OreGenerator;

/**
 * Container for all blocks
 * 
 * @author TheNumenorean
 * 
 */
public class FMBlocks {

	public static Block transparent_aluminum;
	public static Block laser_electron_manipulator;
	public static Block light_bar;
	public static Block aluminum_block;
	
	//Ores
	public static Ore bauxite_ore;

	public static void initializeBlocks(CreativeTabs ct) {

		laser_electron_manipulator = new LaserElectronManipulator().setCreativeTab(ct);
		GameRegistry.registerBlock(laser_electron_manipulator, "laserElectronManipulator");

		transparent_aluminum = new TransparentAlmuninum().setCreativeTab(ct);
		GameRegistry.registerBlock(transparent_aluminum, "transparentAluminum");
		
		light_bar = new LightBar().setCreativeTab(ct);
		GameRegistry.registerBlock(light_bar, "lightBar");
		
		aluminum_block = new AluminumBlock().setCreativeTab(ct);
		GameRegistry.registerBlock(aluminum_block, "blockAluminum");
		OreDictionary.registerOre("blockAluminum", aluminum_block);
		

	}
	
	public static void createAndInitializeOres(CreativeTabs ct, OreGenerator og) {

		bauxite_ore = makeAndRegisterOre(ct, og, "Bauxite", 3, 40, 10);
		
		
	}
	
	/**
	 * Creates an ore and registers it to relevant databases.
	 * @param ct CreativeTab to add it to
	 * @param og OreGenerator to add it to
	 * @param name Name of the ore (Uppercase first letter) (dont include "ore")
	 * @param veinPerChunk How many veins to generate per chunk
	 * @param maxGenHeight Highest to generate veins
	 * @param perVein Blocks per vein
	 * @return The generated ore
	 */
	private static Ore makeAndRegisterOre(CreativeTabs ct, OreGenerator og, String name, final int veinPerChunk, final int maxGenHeight, final int perVein) {
		Ore ore = new Ore(name, 3){

			@Override
			public int getNumberPerChunk(int dimension) {
				return veinPerChunk;
			}

			@Override
			public int getMaxGenHeight(int dimension) {
				return maxGenHeight;
			}

			@Override
			public int blocksPerVein(int dimension) {
				return perVein;
			}
		};
		ore.setCreativeTab(ct).setHardness(1.0f);
		GameRegistry.registerBlock(ore, "ore" + name);
		OreDictionary.registerOre("ore" + name, ore);
		og.addOre(ore);
		return ore;
	}

}
