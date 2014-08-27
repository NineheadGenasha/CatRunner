package com.ninehead.catrunner.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.ninehead.catrunner.Assets;
import com.ninehead.catrunner.Constants;
import com.ninehead.catrunner.entities.ParalaxBackground;
import com.ninehead.catrunner.entities.ParalaxBackgroundList;
import com.ninehead.catrunner.handlers.GameStateManager;
public class PlayState extends GameState {

	private World world;
	private Box2DDebugRenderer b2dRenderer;

	private ParalaxBackgroundList bgList;
	
	private TiledMap tileMap;
	private TiledMap tileMap2;
	private OrthogonalTiledMapRenderer tmr;
	private OrthogonalTiledMapRenderer tmr2;
	private OrthographicCamera cam2;
	
	Body pbody;

	public PlayState(GameStateManager stateManager) {
		super(stateManager);

		this.b2dRenderer = new Box2DDebugRenderer();
		this.world = new World(new Vector2(0, 0), true);

		bgList = new ParalaxBackgroundList();		
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningBackground")));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningMountain"), 4f));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("Hill"), 20f));
		
		createTiles(new TmxMapLoader().load("assets-raw/Map/testMap.tmx"),
				new TmxMapLoader().load("assets-raw/Map/testMap.tmx"));
		createPlayer(100, 600);
		
		cam2 = new OrthographicCamera();
		cam2.setToOrtho(false, Constants.STANDARD_WIDTH,
				Constants.STANDARD_HEIGHT);

	}

	@Override
	public void update(float dt) {
		
		world.step(dt, 6, 2);
		
		checkUpdateObstacle();
		
		bgList.update(dt);
		
	}

	@Override
	public void render() {

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.setProjectionMatrix(this.hudCam.combined);
		
		cam.position.set(pbody.getPosition().x, Constants.STANDARD_HEIGHT/2, 0);
		cam.update();
		cam2.position.set(pbody.getPosition().x-Constants.TILEMAP_WIDTH, Constants.STANDARD_HEIGHT/2, 0);
		cam2.update();
		
		bgList.draw(batch);
		
		tmr.setView(cam);
		tmr.render();
		
		tmr2.setView(cam2);
		tmr2.render();
		
		b2dRenderer.render(world, cam.combined);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	
	private void createTiles(TiledMap tileMap, TiledMap tileMap2){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tmr2 = new OrthogonalTiledMapRenderer(tileMap2);
		
		MapLayer tileLayer = tileMap.getLayers().get("obstacle");
		
		for(MapObject mo : tileLayer.getObjects()){
			RectangleMapObject rectMO = (RectangleMapObject) mo;
			Rectangle rect = rectMO.getRectangle();
			
			bdef.type = BodyType.StaticBody;
			bdef.position.set(rect.getX()+rect.getWidth()/2f, rect.getY()+rect.getHeight()/2);
			Body body = world.createBody(bdef);
			
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(rect.getWidth()/2f, rect.getHeight()/2f);
			
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		tileLayer = tileMap2.getLayers().get("obstacle");
		
		for(MapObject mo : tileLayer.getObjects()){
			RectangleMapObject rectMO = (RectangleMapObject) mo;
			Rectangle rect = rectMO.getRectangle();
			
			bdef.type = BodyType.StaticBody;
			bdef.position.set(rect.getX()+rect.getWidth()/2f+ Constants.TILEMAP_WIDTH, rect.getY()+rect.getHeight()/2);
			Body body = world.createBody(bdef);
			
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(rect.getWidth()/2f, rect.getHeight()/2f);
			
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
	}
	
	private void checkUpdateObstacle(){
		if(pbody.getPosition().x > 1.5*Constants.TILEMAP_WIDTH){
			updateObstacle();
			
		}
	}
	
	private void updateObstacle(){
		Vector2 playerLocation = pbody.getPosition();
		playerLocation.x -= Constants.TILEMAP_WIDTH;
		
		world.dispose();
		world = new World(new Vector2(0, 0), true);
		
		createPlayer(playerLocation.x, playerLocation.y);
		createTiles(new TmxMapLoader().load("assets-raw/Map/testMap.tmx"), new TmxMapLoader().load("assets-raw/Map/testMap.tmx"));
		
	}
	
	private void createPlayer(float x, float y){
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		bdef.position.set(x, y);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(1000f, 0);
		pbody = world.createBody(bdef);
		
		shape.setAsBox(13, 13);
		fdef.shape = shape;
		pbody.createFixture(fdef).setUserData("box");
		
	}

}
