package net.thenumenorean.futuristicmaterials.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.thenumenorean.futuristicmaterials.FuturisticMaterials;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulatorContainer;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulatorGui;
import net.thenumenorean.futuristicmaterials.blocks.machines.LaserElectronManipulatorTileEntity;

public class GuiHandler implements IGuiHandler {

	public static final int LASER_ELECTRON_MANIPULATOR_GUI = 0;

	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(FuturisticMaterials.instance, this);
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		switch (id) {
		case LASER_ELECTRON_MANIPULATOR_GUI:
			if (entity != null && entity instanceof LaserElectronManipulatorTileEntity)
				return new LaserElectronManipulatorContainer(player.inventory, (LaserElectronManipulatorTileEntity) entity);
			break;
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		switch (id) {
		case LASER_ELECTRON_MANIPULATOR_GUI:
			if (entity != null && entity instanceof LaserElectronManipulatorTileEntity)
				return new LaserElectronManipulatorGui(player.inventory, (LaserElectronManipulatorTileEntity) entity);
			break;
		}

		return null;
	}

}
