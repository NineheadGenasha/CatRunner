package com.ninehead.catrunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ninehead.catrunner.handlers.GameStateManager;
import com.ninehead.catrunner.handlers.Timer;

public class CRGame extends ApplicationAdapter {

	public static final String TITLE = "Cat Runner";

	public static final float STEP = 1 / 60f;
	private Timer timer;
	private float dt;

	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;

	GameStateManager stateManager;

	@Override
	public void create() {

		Assets.getInstance().loadAllAssets();

		this.batch = new SpriteBatch();

		this.stateManager = new GameStateManager(this);

		this.cam = new OrthographicCamera();
		this.cam.setToOrtho(false, Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);
		this.hudCam = new OrthographicCamera();
		this.hudCam.setToOrtho(false, Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);

		this.hudCam = new OrthographicCamera(Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);
		this.hudCam.position.set(Constants.STANDARD_WIDTH / 2,
				Constants.STANDARD_HEIGHT / 2, 0);

		this.stateManager.pushState(GameStateManager.PLAY);

		this.timer = new Timer();
	}

	@Override
	public void render() {
		this.dt = Gdx.graphics.getDeltaTime();
		this.timer.update(this.dt);
		while (this.timer.now() >= STEP) {
			this.timer.update(-STEP);
			this.stateManager.update(this.dt);
			this.stateManager.render();
		}
	}

	@Override
	public void dispose() {
		Assets.getInstance().dispose();
	}

	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}

	public OrthographicCamera getCamera() {
		return this.cam;
	}

	public OrthographicCamera getHUDCamera() {
		return this.hudCam;
	}

}
