package net.thenumenorean.futuristicmaterials.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.thenumenorean.futuristicmaterials.CommonProxy;
import net.thenumenorean.futuristicmaterials.blocks.FMBlocks;
import net.thenumenorean.futuristicmaterials.blocks.LightBarItemRenderer;
import net.thenumenorean.futuristicmaterials.blocks.LightBarTileEntity;
import net.thenumenorean.futuristicmaterials.blocks.LightBarTileEntityRenderer;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulatorItemRenderer;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulatorTileEntity;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulatorTileEntityRenderer;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {

		ClientRegistry.bindTileEntitySpecialRenderer(LaserElectronManipulatorTileEntity.class,
				new LaserElectronManipulatorTileEntityRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FMBlocks.laser_electron_manipulator),
				new LaserElectronManipulatorItemRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(LightBarTileEntity.class,
				new LightBarTileEntityRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FMBlocks.light_bar),
				new LightBarItemRenderer());
	}

}