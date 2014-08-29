package com.ninehead.catrunner.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ninehead.catrunner.Assets;
import com.ninehead.catrunner.Constants;
import com.ninehead.catrunner.entities.Cat;
import com.ninehead.catrunner.entities.ParalaxBackground;
import com.ninehead.catrunner.entities.ParalaxBackgroundList;
import com.ninehead.catrunner.handlers.GameStateManager;
import com.ninehead.catrunner.handlers.StageGenerator;


public class PlayState extends GameState {
	
	private static final boolean DEBUG = true;

	private World world;
	private Box2DDebugRenderer b2dRenderer;

	private ParalaxBackgroundList bgList;
	private StageGenerator stageGen;
	
	Cat cat;

	public PlayState(GameStateManager stateManager) {
		super(stateManager);

		this.b2dRenderer = new Box2DDebugRenderer();
		this.world = new World(new Vector2(0, 0), true);

		bgList = new ParalaxBackgroundList();		
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningBackground")));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningMountain"), 4f));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("Hill"), 20f));
		
		stageGen = new StageGenerator(this);
		
		createPlayer(100, 600);
	}

	@Override
	public void update(float dt) {
		
		if(cat.getPosition().x > 1.5*Constants.TILEMAP_WIDTH){
			
			Vector2 playerLocation = cat.getPosition();
			playerLocation.x -= Constants.TILEMAP_WIDTH;
			
			world.dispose();
			world = new World(new Vector2(0, 0), true);
			
			createPlayer(playerLocation.x, playerLocation.y);
			stageGen.generate();
		}
		world.step(dt, 6, 2);
		cat.update(dt);
		
		bgList.update(dt);
		
	}

	@Override
	public void render() {

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.setProjectionMatrix(this.hudCam.combined);
		
		cam.position.set(cat.getBody().getPosition().x, Constants.STANDARD_HEIGHT/2, 0);
		cam.update();
		cam2.position.set(cat.getBody().getPosition().x-Constants.TILEMAP_WIDTH, Constants.STANDARD_HEIGHT/2, 0);
		cam2.update();
		
		bgList.draw(batch);
		stageGen.render();
		
		batch.setProjectionMatrix(cam.combined);
		cat.render(batch);
		
		if(DEBUG){
			b2dRenderer.render(world, cam.combined);
		}
	}

	@Override
	public void dispose() {
		world.dispose();
		stageGen.dispose();
		b2dRenderer.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	
	public void createPlayer(float x, float y){
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		bdef.position.set(x, y);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(1000f, 0);
		Body pbody = world.createBody(bdef);
		
		shape.setAsBox(40, 36, new Vector2(33,-2), 0);
		fdef.shape = shape;
		pbody.createFixture(fdef).setUserData("box");
		
		cat = new Cat(pbody);
		pbody.setUserData(cat);
	}
	
	public World getWorld(){
		return world;
	}
	
}
