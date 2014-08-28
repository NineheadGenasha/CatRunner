package com.ninehead.catrunner.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ninehead.catrunner.CRGame;
import com.ninehead.catrunner.handlers.GameStateManager;

public abstract class GameState {

	protected GameStateManager stateManager;
	protected CRGame game;
	protected SpriteBatch batch;
	protected OrthographicCamera cam;
	protected OrthographicCamera cam2;
	protected OrthographicCamera hudCam;

	public GameState(GameStateManager stateManager) {
		this.stateManager = stateManager;
		this.game = stateManager.game();
		this.batch = this.game.getSpriteBatch();
		this.cam = this.game.getCamera();
		this.cam2 = this.game.getCamera2();
		this.hudCam = this.game.getHUDCamera();
	}
	
	public GameStateManager getGameStateManager(){
		return stateManager;
	}

	public abstract void show();

	public abstract void update(float dt);

	public abstract void render();

	public abstract void dispose();

}
