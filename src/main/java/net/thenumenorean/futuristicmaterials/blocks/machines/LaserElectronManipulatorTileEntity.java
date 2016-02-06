package net.thenumenorean.futuristicmaterials.blocks.machines;

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

public class LaserElectronManipulatorTileEntity extends TileEntity implements ISidedInventory, IEnergySink {

	private final ItemStack[] inventory;
	private BasicSink ic2EnergySink;
	private boolean isCooking;
	private int time_remaining;

	private static final ArrayList<ItemStack> recipeIngredients = new ArrayList<ItemStack>(), recipeProducts = new ArrayList<ItemStack>();
	private static final double ENERGY_PER_TICK = 10;
	private static final int COOK_TIME = 300;

	public LaserElectronManipulatorTileEntity() {
		super();

		inventory = new ItemStack[2];
		ic2EnergySink = new BasicSink(this, 1000, 1);
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
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack itemstack = getStackInSlot(slot);
		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(slot, null);
			} else {
				itemstack = itemstack.splitStack(count);
				this.updateEntity();
				//onInventoryChanged();
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemstack = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		inventory[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if (itemstack == null)
			return true;

		for (ItemStack is : recipeIngredients)
			if (itemstack.isItemEqual(is))
				return true;

		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		ic2EnergySink.writeToNBT(compound);

		NBTTagCompound input = new NBTTagCompound(), output = new NBTTagCompound();
		
		ItemStack tmp;
		if((tmp = getStackInSlot(0)) != null)
			tmp.writeToNBT(input);
		if((tmp = getStackInSlot(1)) != null)
			tmp.writeToNBT(output);
		
		compound.setTag("LEMInput", input);
		compound.setTag("LEMOutput", output);

		compound.setBoolean("cooking", isCooking);
		compound.setInteger("remaining", time_remaining);
		
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		ic2EnergySink.readFromNBT(compound);

		setInventorySlotContents(0, ItemStack.loadItemStackFromNBT(compound.getCompoundTag("LEMInput")));
		setInventorySlotContents(1, ItemStack.loadItemStackFromNBT(compound.getCompoundTag("LEMOutput")));

		isCooking = compound.getBoolean("cooking");
		time_remaining = compound.getInteger("remaining");
	}

	public static void addRecipe(ItemStack in, ItemStack out) {
		recipeIngredients.add(in);
		recipeProducts.add(out);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		int[] tmp = new int[2];
		tmp[0] = 0;
		tmp[1] = 1;
		return tmp;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return i == 0 && isItemValidForSlot(i, itemstack); //Only input to input slot
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return i == 1; //only extract from output
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return true;
	}

	@Override
	public void invalidate() {
		ic2EnergySink.invalidate(); // notify the energy sink

		super.invalidate();
	}

	@Override
	public void onChunkUnload() {
		ic2EnergySink.onChunkUnload(); // notify the energy sink

	}

	@Override
	public void updateEntity() {
		ic2EnergySink.updateEntity(); // notify the energy sink

		if (this.worldObj.isRemote)
			return;

		if (!validIngredient()) {
			isCooking = false;
			time_remaining = 0;
			return;
		}

		if (time_remaining > 0 && ic2EnergySink.useEnergy(ENERGY_PER_TICK)) {
			time_remaining--;
		}

		if (isCooking && time_remaining == 0) {

			{

				ItemStack tmp = inventory[1];

				ItemStack product = getProduct(inventory[0]);

				if (tmp == null) {
					isCooking = false;
					inventory[1] = product.copy();
				} else if (tmp.getMaxStackSize() >= tmp.stackSize + product.stackSize && tmp.getItem().equals(product.getItem())) {
					isCooking = false;
					inventory[1].stackSize += product.stackSize;

				}

				if (!isCooking)
					if (inventory[0].stackSize <= 1)
						inventory[0] = null;
					else
						inventory[0].stackSize--;
			}

		}

		if (!isCooking && validIngredient()) {

			time_remaining = COOK_TIME;
			isCooking = true;

		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	private boolean validIngredient() {
		if (inventory[0] != null)
			for (ItemStack is : recipeIngredients)
				if (inventory[0].getItem().equals(is.getItem()))
					return true;
		return false;
	}

	private ItemStack getProduct(ItemStack ingredient) {
		for (int i = 0; i < recipeIngredients.size(); i++)
			if (recipeIngredients.get(i).getItem().equals(ingredient.getItem()))
				return recipeProducts.get(i);
		return null;
	}

	public int getBurnTimeRemainingScaled(int outOf) {
		return outOf - outOf * time_remaining / COOK_TIME;
	}

	public boolean isCooking() {
		return isCooking;
	}

	@Override
	public String getInventoryName() {
		return "Laser Electron Manipulator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDemandedEnergy() {
		return ic2EnergySink.getDemandedEnergy();
	}

	@Override
	public int getSinkTier() {
		return ic2EnergySink.getSinkTier();
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		return ic2EnergySink.injectEnergy(directionFrom, amount, voltage);
	}

}
