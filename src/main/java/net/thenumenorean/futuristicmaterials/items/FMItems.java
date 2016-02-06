package net.thenumenorean.futuristicmaterials.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Contains and initializes all FuturisticMaterials Items
 * 
 * @author The Numenorean
 * 
 */
public class FMItems {

	public static Item aluminum_ingot;

	public static void initializeItems(CreativeTabs ct) {

		// Ingots
		aluminum_ingot = new AluminumIngot().setCreativeTab(ct);
		GameRegistry.registerItem(aluminum_ingot, "ingotAluminum");
		OreDictionary.registerOre("ingotAluminum", aluminum_ingot);

	}

}
