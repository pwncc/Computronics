package pl.asie.computronics;

import li.cil.oc.api.machine.Robot;
import li.cil.oc.api.network.Environment;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pl.asie.computronics.storage.StorageManager;
import pl.asie.computronics.tile.TileChatBox;
import pl.asie.computronics.oc.RobotUpgradeChatBox;
import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.network.packet.NetHandler;
//import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.world.WorldEvent;

public class ComputronicsEventHandler {
	@SubscribeEvent
	public void chatEvent(ServerChatEvent event) {
		for(Object o: event.player.worldObj.loadedTileEntityList) {
			if(o instanceof TileChatBox) {
				TileChatBox te = (TileChatBox)o;
				if(te.isCreative() || event.player.getDistance(te.xCoord, te.yCoord, te.zCoord) < te.getDistance()) {
					te.receiveChatMessage(event);
				}
			} else if(o instanceof Robot && o instanceof TileEntity) {
				Robot r = (Robot)o;
				TileEntity te = (TileEntity)o;
				if(event.player.getDistance(te.xCoord, te.yCoord, te.zCoord) < Computronics.CHATBOX_DISTANCE) {
					for(int i = 0; i < r.getSizeInventory(); i++) {
						Environment e = r.getComponentInSlot(i);
						if(e instanceof RobotUpgradeChatBox) {
							((RobotUpgradeChatBox)e).receiveChatMessage(event);
						}
					}
				}
			}
		}
	}
	
/*	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onSound(SoundLoadEvent event) {
		event.manager.addSound("computronics:tape_eject.ogg");
		event.manager.addSound("computronics:tape_rewind.ogg");
		event.manager.addSound("computronics:tape_insert.ogg");
		event.manager.addSound("computronics:tape_button.ogg");
	}

	@Override
	public Packet3Chat serverChat(NetHandler handler, Packet3Chat message) {
		if(!(message.message.startsWith("/"))) return message;
		
		for(Object o: handler.getPlayer().worldObj.loadedTileEntityList) {
			if(o instanceof TileChatBox) {
				TileChatBox te = (TileChatBox)o;
				if(te.isCreative() || (handler.getPlayer().getDistance(te.xCoord, te.yCoord, te.zCoord) < Computronics.CHATBOX_DISTANCE && message.message.startsWith("/me") && Computronics.CHATBOX_ME_DETECT)) {
					te.receiveChatMessage(new ServerChatEvent((EntityPlayerMP)handler.getPlayer(), message.message, ChatMessageComponent.createFromText(message.message)));
				}
			}
		}
		return message;
	}

	@Override
	public Packet3Chat clientChat(NetHandler handler, Packet3Chat message) {
		return message;
	} */
}
