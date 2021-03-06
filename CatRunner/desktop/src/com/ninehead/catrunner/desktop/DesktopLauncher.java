package com.ninehead.catrunner.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.ninehead.catrunner.CRGame;

public class DesktopLauncher {
	private static final boolean rebuildAtlas = false;

	public static void main(String[] arg) {
		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 4096;
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
