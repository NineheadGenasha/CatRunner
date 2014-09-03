package com.ninehead.catrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ninehead.catrunner.handlers.GameStateManager;
import com.ninehead.catrunner.handlers.Timer;

public class CRGame extends ApplicationAdapter {

	public static final String TITLE = "Cat Runner";

	public static final float STEP = 1 / 80f;
	private Timer timer;
	private float dt;

	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera cam2;
	private OrthographicCamera hudCam;

	private FitViewport hudViewport;

	GameStateManager stateManager;

	@Override
	public void create() {

		Assets.getInstance().loadAllAssets();
		GamePreferences.getInstance().load();

		this.batch = new SpriteBatch();

		this.stateManager = new GameStateManager(this);

		this.cam = new OrthographicCamera();
		this.cam.setToOrtho(false, Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);

		this.cam2 = new OrthographicCamera();
		this.cam2.setToOrtho(false, Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);

		this.hudCam = new OrthographicCamera();
		this.hudViewport = new FitViewport(Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT, this.hudCam);
		this.hudCam.position.set(Constants.STANDARD_WIDTH / 2,
				Constants.STANDARD_HEIGHT / 2, 0);

		this.stateManager.pushState(GameStateManager.MENU);

		this.timer = new Timer();

	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.dt = Gdx.graphics.getDeltaTime();
		this.timer.update(this.dt);
		this.stateManager.update(this.dt);
		this.stateManager.render();
	}

	@Override
	public void resize(int width, int height) {
		this.hudViewport.update(width, height);
	}

	@Override
	public void pause() {
		GamePreferences.getInstance().save();
	}

	@Override
	public void dispose() {
		System.out.println("dispose");
		Assets.getInstance().dispose();
		GamePreferences.getInstance().save();
	}

	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}

	public OrthographicCamera getCamera() {
		return this.cam;
	}

	public OrthographicCamera getCamera2() {
		return this.cam2;
	}

	public OrthographicCamera getHUDCamera() {
		return this.hudCam;
	}

}
