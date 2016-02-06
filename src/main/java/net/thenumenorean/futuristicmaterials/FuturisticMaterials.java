package net.thenumenorean.futuristicmaterials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.thenumenorean.futuristicmaterials.blocks.FMBlocks;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulatorTileEntity;
import net.thenumenorean.futuristicmaterials.blocks.ores.OreGenerator;
import net.thenumenorean.futuristicmaterials.client.gui.GuiHandler;
import net.thenumenorean.futuristicmaterials.items.FMItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;

@Mod(modid = FuturisticMaterials.MODID, version = FuturisticMaterials.VERSION, dependencies = "required-after:IC2")
public class FuturisticMaterials {
	public static final String MODID = "futuristicmaterials";
	public static final String VERSION = "1.0.0";
	public static FuturisticMaterials instance;

	public CreativeTabs ct;

	@SidedProxy(clientSide = "net.thenumenorean.futuristicmaterials.client.ClientProxy", serverSide = "net.thenumenorean.futuristicmaterials.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		new GuiHandler();

		//Create Creative Tab
		ct = new CreativeTabs("Futuristic Materials") {

			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(FMBlocks.laser_electron_manipulator);
			}

		};

		//Register ore generator
		OreGenerator oreGenerator = new OreGenerator(); 
		GameRegistry.registerWorldGenerator(oreGenerator, 0); 

		//Add custom blocks and items
		FMBlocks.initializeBlocks(ct);
		FMBlocks.createAndInitializeOres(ct, oreGenerator);
		
		FMItems.initializeItems(ct);

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		loadRecipes();
	}

	public void loadRecipes() {
		
		//Smelting
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		GameRegistry.addSmelting(FMBlocks.bauxite_ore, new ItemStack(FMItems.aluminum_ingot), 1.0F);


		//Normal Recipes
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		//Aluminum Block
		GameRegistry.addShapedRecipe(new ItemStack(FMBlocks.aluminum_block), "XXX", "XXX", "XXX", 'X', new ItemStack( 
		FMItems.aluminum_ingot)); 

		
		//LEM
		ItemStack lapCrystal = IC2Items.getItem("lapotronCrystal");
		lapCrystal.setItemDamage(26);
		ItemStack miningLaser = IC2Items.getItem("miningLaser");
		miningLaser.setItemDamage(26);
		GameRegistry.addShapedRecipe(new ItemStack(FMBlocks.laser_electron_manipulator, 1),
				new Object[] { "XLX", "ACA", "XZX", 'C', IC2Items.getItem("advancedMachine"), 'Z', lapCrystal, 'X',
						IC2Items.getItem("advancedAlloy"), 'D', IC2Items.getItem("advancedAlloy"), 'A',
						IC2Items.getItem("advancedCircuit"), 'L', miningLaser });
		
		//LaserElectronManipulator recipes
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		for (ItemStack bl : OreDictionary.getOres("blockAluminum"))
			LaserElectronManipulatorTileEntity.addRecipe(bl, new ItemStack(FMBlocks.transparent_aluminum));

	}

}
