package com.ninehead.catrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CRGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private MenuState menu;

	@Override
	public void create() {
		Assets.getInstance().loadAllAssets();
		this.batch = new SpriteBatch();
		this.img = new Texture("badlogic.jpg");
		this.menu = new MenuState();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.menu.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		Assets.getInstance().dispose();
	}
}
