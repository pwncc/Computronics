package pl.asie.lib.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PlayerUtils {

	public static EntityPlayer find(String name) {
		//AsieLibMod.log.info("trying to find player " + name);
		if(FMLCommonHandler.instance().getMinecraftServerInstance() == null) {
			//AsieLibMod.log.info("No server found, trying client");
			if(Minecraft.getMinecraft() != null && Minecraft.getMinecraft().theWorld != null) {
				//AsieLibMod.log.info(player != null ? "client player found: " + player.toString() : "No client player found");
				return Minecraft.getMinecraft().theWorld.getPlayerEntityByName(name);
			}
			//AsieLibMod.log.info("nothing found");
			return null;
		}

		//AsieLibMod.log.info("Server found! It is " + FMLCommonHandler.instance().getMinecraftServerInstance().toString());
		for(EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerList()) {
			if(player != null) {
				//AsieLibMod.log.info("Server player found: " + target.toString());
				if(player.getName().equals(name)) {
					return player;
				}
			}
		}
		//AsieLibMod.log.info(" [2] could not find player " + name + " on server");
		return null;
	}
}