package com.ninehead.catrunner.handlers;

import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ninehead.catrunner.CRGame;
import com.ninehead.catrunner.Constants;
import com.ninehead.catrunner.entities.NormalCat;
import com.ninehead.catrunner.states.PlayState;

public class StageGenerator {
	
   private PlayState playState;
   private CRGame game;
   private World world;
   
   private TiledMap[] tm;
   private int firstMap;
   private int secondMap;
   
   private OrthogonalTiledMapRenderer tmr;
   private OrthogonalTiledMapRenderer tmr2;
   private OrthographicCamera cam;
   private OrthographicCamera cam2;
   
   private Random randomGen;
   
   public StageGenerator(PlayState playState){
	  this.playState = playState;
	  game = playState.getGameStateManager().game();
	  world = playState.getWorld();
	  
	  cam = game.getCamera();
	  cam2 = game.getCamera2();
	  
      tm = new TiledMap[2];
      tm[0] = new TmxMapLoader().load("assets-raw/Map/testMap.tmx");
      tm[1] = new TmxMapLoader().load("assets-raw/Map/testMap2.tmx");
      
      randomGen = new Random();
      
      firstMap = 0;
      secondMap = abs(randomGen.nextInt() % tm.length);
      
      createTiles(tm[firstMap], tm[secondMap]);
     
   }
   
   public void render(){
		tmr.setView(cam);
		tmr.render();
		
		tmr2.setView(cam2);
		tmr2.render();
   }
   
   public void dispose(){
	  for(TiledMap tileMap : tm){
		  tileMap.dispose();
	  }
   }
   
   public void generate(){
		
		firstMap = secondMap;
		secondMap = abs(randomGen.nextInt() % tm.length);
		
		createTiles(tm[firstMap], tm[secondMap]);
   }
   
	private void createTiles(TiledMap tileMap, TiledMap tileMap2){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		world = playState.getWorld();
		
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tmr2 = new OrthogonalTiledMapRenderer(tileMap2);
		
		MapLayer tileLayer = tileMap.getLayers().get("obstacle");
		
		fdef.filter.categoryBits = Constants.BIT_ROAD;
		fdef.filter.maskBits = Constants.BIT_PLAYER;
		fdef.isSensor = false;
		
		for(MapObject mo : tileLayer.getObjects()){
			RectangleMapObject rectMO = (RectangleMapObject) mo;
			Rectangle rect = rectMO.getRectangle();
			
			bdef.type = BodyType.StaticBody;
			bdef.position.set(rect.getX()+rect.getWidth()/2f, rect.getY()+rect.getHeight()/2);
			Body body = world.createBody(bdef);
			
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(rect.getWidth()/2f, rect.getHeight()/2f);
			
			fdef.shape = shape;
			fdef.friction = 0;
			body.createFixture(fdef).setUserData("road");
			
			body.setUserData("road");
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
			body.createFixture(fdef).setUserData("obstacle");
			
			body.setUserData("obstacle");
		}
		
	}
	
	private int abs(int i){
		if(i<0)
			return i*-1;
		else
			return i;
	}
   
}
