package net.thenumenorean.futuristicmaterials.blocks;

import ic2.api.energy.prefab.BasicSink;
import ic2.api.energy.tile.IEnergySink;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class LightBarTileEntity extends TileEntity {

	public LightBarTileEntity() {
		super();
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

	}

	@Override
	public void updateEntity() {

		if (this.worldObj.isRemote)
			return;

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	public int getEmittedLight() {
		return 7;
	}

	/**
	 * Returns a double array. Each row has two ForgeDirections, accessed as
	 * [x][0] and [x][1]. There should be a corner lamp at the side that is
	 * between the two directions.
	 * 
	 * @return A non-null, but potentially empty, array.
	 */
	public ForgeDirection[][] getCornerLights() {
		return null;

	}

	/**
	 * Returns a double array. The first column ([x][0]) contains a
	 * ForgeDirection. If the ForgeDirection exists in the array, then a flat
	 * light should be placed on that side. Then, in the second column ([x][1])
	 * if the value is null, the lamp should be oriented to the North, whereas
	 * if it isn't (it contains ForgeDirection.UNKNOWN) it should be oriented to
	 * the West.
	 * 
	 * 
	 * @return A non-null, but potentially empty, array.
	 */
	public ForgeDirection[][] getFlatLights() {
		return null;

	}

}
