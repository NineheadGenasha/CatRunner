package com.ninehead.catrunner.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
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
import com.ninehead.catrunner.handlers.input.Button;
import com.ninehead.catrunner.handlers.input.ButtonListener;
import com.ninehead.catrunner.handlers.input.InputLayer;
import com.ninehead.catrunner.test.ButtonTest;


public class PlayState extends GameState {
	
	private static final boolean DEBUG = true;

	private World world;
	private Box2DDebugRenderer b2dRenderer;

	private ParalaxBackgroundList bgList;
	private StageGenerator stageGen;
	
	private InputMultiplexer inputComponents;
	private InputLayer inputLayer;
	
	Cat cat;

	public PlayState(GameStateManager stateManager) {
		super(stateManager);

		this.b2dRenderer = new Box2DDebugRenderer();
		this.world = new World(new Vector2(0, Constants.GRAVITATION), true);

		bgList = new ParalaxBackgroundList();		
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningBackground")));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningMountain"), 4f));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("Hill"), 20f));
		
		stageGen = new StageGenerator(this);
		
		createPlayer(100, 600);
		
		inputComponents = new InputMultiplexer();
		inputLayer = new InputLayer(hudCam);
		
		this.inputComponents.addProcessor(new InputAdapter(){
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				if (screenX > Constants.STANDARD_WIDTH/2)
					cat.getBody().applyLinearImpulse(new Vector2(0,100),cat.getBody().getPosition(), true);
				return true;
			}
		});
		inputComponents.addProcessor(inputLayer);
		
		createNinjaCatButton();
		createSuperCatButton();
		
	}

	@Override
	public void update(float dt) {
		
		if(cat.getPosition().x > 1.5*Constants.TILEMAP_WIDTH){
			
			Vector2 playerLocation = cat.getPosition();
			playerLocation.x -= Constants.TILEMAP_WIDTH;
			
			world.dispose();
			world = new World(new Vector2(0, Constants.GRAVITATION), true);
			
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
		
		this.batch.setProjectionMatrix(this.hudCam.combined);
		inputLayer.draw(batch);
	}

	@Override
	public void dispose() {
		world.dispose();
		stageGen.dispose();
		b2dRenderer.dispose();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputComponents);
	}
	
	public void createPlayer(float x, float y){
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		bdef.position.set(x, y);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(1000f, 0);
		Body pbody = world.createBody(bdef);
		
		shape.setAsBox(40, 36, new Vector2(33, 0), 0);
		fdef.shape = shape;
		fdef.isSensor = false;
		fdef.filter.categoryBits = Constants.BIT_PLAYER;
		fdef.filter.maskBits = Constants.BIT_ROAD;
		fdef.friction = 0;
		pbody.createFixture(fdef).setUserData("cat_body");
		
		shape.setAsBox(40, 4, new Vector2(33,-36), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = Constants.BIT_PLAYER;
		fdef.filter.maskBits = Constants.BIT_ROAD;
		fdef.friction = 0;
		pbody.createFixture(fdef).setUserData("cat_foot");
		
		cat = new Cat(pbody);
		pbody.setUserData(cat);
	}
	
	public World getWorld(){
		return world;
	}
	
	private void createNinjaCatButton() {
		Button ninjaButton = new Button(Assets.getInstance().getTextureRegion(
				"NinjaCatButton"));

		ninjaButton.addListener(new ButtonListener() {

			@Override
			public void hasTouchDown() {
				System.out.println("Ninja Clicked");
				//textureRegion = Assets.getInstance().getTextureRegion("NinjaCat01");
			}

			@Override
			public void hasTouchUp() {
				//ButtonTest.this.textureRegion = Assets.getInstance().getTextureRegion("NormalCat01");
			}

		});
		ninjaButton.setPosition(0, 0.2f * Constants.STANDARD_HEIGHT);
		this.inputLayer.addButton(ninjaButton);
	}
	
	public void createSuperCatButton() {
		Button superButton = new Button(Assets.getInstance().getTextureRegion(
				"SuperCatButton"));

		superButton.addListener(new ButtonListener() {

			@Override
			public void hasTouchDown() {
				System.out.println("Super Clicked");
				//ButtonTest.this.textureRegion = Assets.getInstance().getTextureRegion("SuperCat01");
			}

			@Override
			public void hasTouchUp() {
				//ButtonTest.this.textureRegion = Assets.getInstance().getTextureRegion("NormalCat01");
			}

		});
		superButton.setPosition(0, 0.4f * Constants.STANDARD_HEIGHT);
		this.inputLayer.addButton(superButton);
	}

}
