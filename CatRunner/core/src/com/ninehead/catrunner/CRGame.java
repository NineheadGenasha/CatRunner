package com.ninehead.catrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ninehead.catrunner.handlers.GameStateManager;
import com.ninehead.catrunner.handlers.Timer;

public class CRGame extends ApplicationAdapter {
	
	public static final String TITLE = "Cat Runner";
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static final int SCALE = 2;
	
	public static final float STEP = 1/60f;
	private Timer timer;
	private float dt;
			
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
	GameStateManager stateManager;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		
		stateManager = new GameStateManager(this);
		stateManager.pushState(GameStateManager.MENU);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, V_WIDTH, V_HEIGHT);
		
		timer = new Timer();
	}

	@Override
	public void render () {
		dt = Gdx.graphics.getDeltaTime();
		timer.update(dt);
		while(timer.now() >= STEP){
			timer.update(-STEP);
			stateManager.update(dt);
			stateManager.render();
		}
	}
	
	public SpriteBatch getSpriteBatch(){
		return batch;
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public OrthographicCamera getHUDCamera(){
		return hudCam;
	}
	
}
