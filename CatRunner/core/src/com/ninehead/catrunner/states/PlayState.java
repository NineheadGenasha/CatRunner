package com.ninehead.catrunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.ninehead.catrunner.Assets;
import com.ninehead.catrunner.entities.ParalaxBackground;
import com.ninehead.catrunner.entities.ParalaxBackgroundList;
import com.ninehead.catrunner.handlers.GameStateManager;

public class PlayState extends GameState {

	private final World world;
	private final Box2DDebugRenderer b2dRenderer;

	private ParalaxBackgroundList bgList;

	public PlayState(GameStateManager stateManager) {
		super(stateManager);

		this.b2dRenderer = new Box2DDebugRenderer();
		this.world = new World(new Vector2(0, -0.98f), true);

		bgList = new ParalaxBackgroundList();		
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningBackground")));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("morningMountain"), 2f));
		bgList.add(new ParalaxBackground(Assets.getInstance().getTextureRegion("Hill"), 10f));
		
	}

	@Override
	public void update(float dt) {
		bgList.update(dt);
	}

	@Override
	public void render() {

		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.setProjectionMatrix(this.cam.combined);

		bgList.draw(batch);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
