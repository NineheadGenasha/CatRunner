package com.ninehead.catrunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.ninehead.catrunner.Assets;
import com.ninehead.catrunner.Constants;
import com.ninehead.catrunner.handlers.GameStateManager;

public class PlayState extends GameState {

	private final World world;
	private final Box2DDebugRenderer b2dRenderer;

	private final OrthographicCamera oc;

	private TextureRegion trHill;
	private int hill_x;
	private int hill_y;
	private float hill_nowX;
	private int hill_width;
	private int hill_height;
	private float hill_speed;

	private TextureRegion trMountain;
	private int mountain_x;
	private int mountain_y;
	private float mountain_nowX;
	private int mountain_width;
	private int mountain_height;
	private float mountain_speed;

	public PlayState(GameStateManager stateManager) {
		super(stateManager);

		this.b2dRenderer = new Box2DDebugRenderer();
		this.world = new World(new Vector2(0, -0.98f), true);

		createHill();
		createMountain();

		this.oc = new OrthographicCamera();
		this.oc.setToOrtho(false, Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);
	}

	@Override
	public void update(float dt) {
		this.hill_nowX += this.hill_speed;
		if (this.hill_nowX > this.hill_x + this.hill_width)
			this.hill_nowX = this.hill_x;

		this.mountain_nowX += this.mountain_speed;
		if (this.mountain_nowX > this.mountain_x + this.mountain_width)
			this.mountain_nowX = this.mountain_x;
	}

	@Override
	public void render() {

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.setProjectionMatrix(this.oc.combined);

		drawMountain();
		drawHill();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private void createHill() {
		this.trHill = Assets.getInstance().getTextureRegion("Hill");
		this.hill_x = this.trHill.getRegionX();
		this.hill_y = this.trHill.getRegionY();
		this.hill_nowX = this.hill_x;
		this.hill_width = this.trHill.getRegionWidth();
		this.hill_height = this.trHill.getRegionHeight();
		this.hill_speed = 0.5f;
	}

	private void createMountain() {
		this.trMountain = Assets.getInstance().getTextureRegion(
				"morningBackground");
		this.mountain_x = this.trMountain.getRegionX();
		this.mountain_y = this.trMountain.getRegionY();
		this.mountain_nowX = this.mountain_x;
		this.mountain_width = this.trMountain.getRegionWidth();
		this.mountain_height = this.trMountain.getRegionHeight();
		this.mountain_speed = 0.2f;
	}

	private void drawHill() {
		this.batch.begin();
		this.trHill.setRegion((int) this.hill_nowX, this.hill_y, this.hill_x
				+ this.hill_width - (int) this.hill_nowX, this.hill_height);
		this.batch.draw(this.trHill, 0, 0);
		this.trHill.setRegion(this.hill_x, this.hill_y, (int) this.hill_nowX
				- this.hill_x, this.hill_height);
		this.batch.draw(this.trHill, this.hill_x + this.hill_width
				- (int) this.hill_nowX, 0);
		this.batch.end();
	}

	private void drawMountain() {
		this.batch.begin();
		this.trMountain.setRegion((int) this.mountain_nowX, this.mountain_y,
				this.mountain_x + this.mountain_width
				- (int) this.mountain_nowX, this.mountain_height);
		this.batch.draw(this.trMountain, 0, 0);
		this.trMountain.setRegion(this.mountain_x, this.mountain_y,
				(int) this.mountain_nowX - this.hill_x, this.mountain_height);
		this.batch.draw(this.trMountain, this.mountain_x + this.mountain_width
				- (int) this.mountain_nowX - 2, 0);
		this.batch.end();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
