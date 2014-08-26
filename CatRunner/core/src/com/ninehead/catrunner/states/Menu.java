package com.ninehead.catrunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ninehead.catrunner.handlers.GameStateManager;

public class Menu extends GameState {

	Texture background;

	public Menu(GameStateManager stateManager) {
		super(stateManager);
		this.background = new Texture("badlogic.jpg");
	}

	@Override
	public void show() {

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.begin();
		this.batch.draw(this.background, 0, 0);
		this.batch.end();
	}

	@Override
	public void dispose() {
		this.background.dispose();
	}

}
