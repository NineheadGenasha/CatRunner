package com.ninehead.catrunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GamePreferences {
	private boolean isSoundOn;
	private boolean isMusicOn;
	private static GamePreferences instance;
	private final Preferences preferences;

	private GamePreferences() {
		this.preferences = Gdx.app.getPreferences("MyPreferences");
	}

	public static GamePreferences getInstance() {
		if (instance == null) {
			instance = new GamePreferences();
		}
		return instance;
	}

	public void load() {
		this.setSoundOn(this.preferences.getBoolean("isSoundOn", true));
		this.setMusicOn(this.preferences.getBoolean("isMusicOn", true));
	}

	public void save() {
		if (isSoundOn()) {
			System.out.println("Sound mati true");
		} else {
			System.out.println("Sound mati false");
		}
		this.preferences.putBoolean("isSoundOn", this.isSoundOn());
		this.preferences.putBoolean("isMusicOn", this.isMusicOn());
		this.preferences.flush();
	}

	public boolean isSoundOn() {
		return this.isSoundOn;
	}

	public void setSoundOn(boolean isSoundOn) {
		this.isSoundOn = isSoundOn;
	}

	public boolean isMusicOn() {
		return this.isMusicOn;
	}

	public void setMusicOn(boolean isMusicOn) {
		this.isMusicOn = isMusicOn;
	}
}
