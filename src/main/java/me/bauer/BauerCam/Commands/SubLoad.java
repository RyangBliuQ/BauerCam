package me.bauer.BauerCam.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import me.bauer.BauerCam.Main;
import me.bauer.BauerCam.Utils;
import me.bauer.BauerCam.Path.PathHandler;
import me.bauer.BauerCam.Path.Position;
import net.minecraft.util.text.TextFormatting;

public class SubLoad extends ASubExportImport {

	@Override
	protected void derivedExecute(final String filename) {
		final File file = new File(Main.bauercamDirectory, filename + extension);

		if (!file.isFile()) {
			Utils.sendInformation(Main.fileDoesNotExist.toString(), TextFormatting.YELLOW);
			return;
		}

		final ArrayList<Position> points = new ArrayList<>();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			String s;
			while ((s = reader.readLine()) != null) {
				final Position point = Position.fromString(s);
				if (point == null) {
					reader.close();
					return;
				}
				points.add(point);
			}

			PathHandler.setWaypoints(points);
			Utils.sendInformation(Main.importSuccessful.toString());
		} catch (final IOException e) {
			Utils.sendInformation(Main.IOError.toString(), TextFormatting.RED);
			Utils.sendInformation(e.getMessage(), TextFormatting.YELLOW);
		}

		if (reader == null) {
			return;
		}
		try {
			reader.close();
		} catch (final IOException e) {
		}
	}

	@Override
	public String getBase() {
		return "load";
	}

	@Override
	public String getDescription() {
		return "/cam load <filename>";
	}

}
