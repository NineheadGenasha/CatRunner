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

public class PlayState extends GameState{
	
	private World world;
	private Box2DDebugRenderer b2dRenderer;
	
	private OrthographicCamera oc;
	
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
	
	public PlayState(GameStateManager stateManager){
		super(stateManager);
		
		b2dRenderer = new Box2DDebugRenderer();
		world = new World(new Vector2(0, -0.98f), true);
		
		createHill();
		createMountain();
		
		oc= new OrthographicCamera();
		oc.setToOrtho(false, Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		hill_nowX += hill_speed;
		if(hill_nowX > (float)(hill_x + hill_width))
			hill_nowX = hill_x;
		
		mountain_nowX += mountain_speed;
		if(mountain_nowX > (float)(mountain_x + mountain_width))
			mountain_nowX = mountain_x;
	}

	@Override
	public void render() {
		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(oc.combined);
		
		drawMountain();
		drawHill();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private void createHill(){
		trHill = Assets.getInstance().getTextureRegion("Hill");
		hill_x = trHill.getRegionX();
		hill_y = trHill.getRegionY();
		hill_nowX = hill_x;
		hill_width = trHill.getRegionWidth();
		hill_height = trHill.getRegionHeight();
		hill_speed = 0.5f;
	}
	
	private void createMountain(){
		trMountain = Assets.getInstance().getTextureRegion("morningBackground");
		mountain_x = trMountain.getRegionX();
		mountain_y = trMountain.getRegionY();
		mountain_nowX = mountain_x;
		mountain_width = trMountain.getRegionWidth();
		mountain_height = trMountain.getRegionHeight();
		mountain_speed = 0.2f;
	}
	
	private void drawHill(){
		batch.begin();
		trHill.setRegion((int)hill_nowX, hill_y, 
				hill_x + hill_width - (int)hill_nowX, hill_height);
		batch.draw(trHill, 0, 0);
		trHill.setRegion(hill_x, hill_y, 
				(int)hill_nowX-hill_x, hill_height);
		batch.draw(trHill, hill_x + hill_width - (int)hill_nowX, 0);
		batch.end();
	}
	
	private void drawMountain(){
		batch.begin();
		trMountain.setRegion((int)mountain_nowX, mountain_y, 
				mountain_x + mountain_width - (int)mountain_nowX, mountain_height);
		batch.draw(trMountain, 0, 0);
		trMountain.setRegion(mountain_x, mountain_y, 
				(int)mountain_nowX-hill_x, mountain_height);
		batch.draw(trMountain, mountain_x + mountain_width - (int)mountain_nowX - 2, 0);
		batch.end();
	}
	
}
