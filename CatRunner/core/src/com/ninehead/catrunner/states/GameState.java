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
	protected OrthographicCamera hudCam;
	
	public GameState(GameStateManager stateManager){
		this.stateManager = stateManager;
		game = stateManager.game();
		batch = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHUDCamera();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	
}
