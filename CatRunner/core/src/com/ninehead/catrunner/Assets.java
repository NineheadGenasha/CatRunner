package com.ninehead.catrunner;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	private static Assets instance;
	private final AssetManager assetManager;
	private TextureAtlas textureAtlas;
	private final HashMap<String, TextureRegion> textureRegionCache;

	private Assets() {
		this.assetManager = new AssetManager();
		this.textureRegionCache = new HashMap<String, TextureRegion>();
	}

	public void loadAllAssets() {
		this.assetManager.load(Constants.TEXTURE_PACK_OBJECT,
				TextureAtlas.class);
		this.assetManager.finishLoading();
		this.textureAtlas = this.assetManager.get(
				Constants.TEXTURE_PACK_OBJECT, TextureAtlas.class);
		saveTextureRegionToCache();
	}

	private void saveTextureRegionToCache() {
		for (AtlasRegion atlasRegion : this.textureAtlas.getRegions()) {
			this.textureRegionCache.put(atlasRegion.name, atlasRegion);
		}
	}

	public TextureRegion getTextureRegion(String _name) {
		return this.textureRegionCache.get(_name);
	}

	public void dispose() {
		this.assetManager.clear();
	}

	public static Assets getInstance() {
		if (instance == null) {
			instance = new Assets();
		}
		return instance;
	}
}
