package com.ninehead.catrunner.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;
import com.ninehead.catrunner.CRGame;

public class DesktopLauncher {
	private static final boolean rebuildAtlas = true;

	public static void main(String[] arg) {
		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 2048;
			settings.maxHeight = 2048;
			TexturePacker.process(settings, "assets-raw/Image",
					"../android/assets/Image", "CatRunner.pack");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new CRGame(), config);
	}
}
