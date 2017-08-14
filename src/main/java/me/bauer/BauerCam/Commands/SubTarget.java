package me.bauer.BauerCam.Commands;

import me.bauer.BauerCam.Main;
import me.bauer.BauerCam.Utils;
import me.bauer.BauerCam.Path.PathHandler;
import me.bauer.BauerCam.Path.Vector3D;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextFormatting;

public class SubTarget implements ISubCommand {

	@Override
	public void execute(final String[] args) {
		if (args.length == 1) {
			Utils.sendInformation(getDescription(), TextFormatting.RED);
			return;
		}

		final String op = args[1].toLowerCase();

		if ("set".equals(op)) {
			final EntityPlayerSP player = Utils.mc.player;
			final Vector3D target = new Vector3D(player.posX, player.posY, player.posZ);
			PathHandler.setTarget(target);
			Utils.sendInformation(Main.pathTargetSet.toString());
		} else if ("off".equals(op)) {
			PathHandler.removeTarget();
			Utils.sendInformation(Main.pathTargetRemoved.toString());
		} else {
			Utils.sendInformation(getDescription(), TextFormatting.RED);
		}
	}

	@Override
	public String getBase() {
		return "target";
	}

	@Override
	public String getDescription() {
		return "/cam target <set/off>";
	}

}
