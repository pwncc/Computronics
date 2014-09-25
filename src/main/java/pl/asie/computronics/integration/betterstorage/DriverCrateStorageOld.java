package pl.asie.computronics.integration.betterstorage;

import li.cil.oc.api.network.Arguments;
import li.cil.oc.api.network.Callback;
import li.cil.oc.api.network.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.mcft.copy.betterstorage.api.ICrateStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pl.asie.computronics.integration.ManagedEnvironmentOCTile;

import java.util.List;

public class DriverCrateStorageOld extends DriverTileEntity {
	public class ManagedEnvironmentCrate extends ManagedEnvironmentOCTile<ICrateStorage> {
		public ManagedEnvironmentCrate(ICrateStorage tile, String name) {
			super(tile, name);
		}

		@Callback()
		public Object[] getContents(Context c, Arguments a) {
			List<ItemStack> l = tile.getContents(ForgeDirection.UNKNOWN);
			return new Object[] { l.toArray(new ItemStack[l.size()]) };
		}
	}

	@Override
	public Class<?> getTileEntityClass() {
		return ICrateStorage.class;
	}

	@Override
	public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
		return new ManagedEnvironmentCrate((ICrateStorage) world.getTileEntity(x, y, z), "crate");
	}
}
