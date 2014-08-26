package com.ninehead.catrunner.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ninehead.catrunner.Assets;
import com.ninehead.catrunner.Constants;
import com.ninehead.catrunner.handlers.GameStateManager;
import com.ninehead.catrunner.handlers.input.Button;
import com.ninehead.catrunner.handlers.input.ButtonListener;
import com.ninehead.catrunner.handlers.input.InputLayer;
import com.ninehead.catrunner.states.GameState;

public class ButtonTest extends GameState {

	private final InputMultiplexer inputComponents;
	private final InputLayer inputLayer;
	private TextureRegion textureRegion;
	private float positionX;
	private float positionY;

	public ButtonTest(GameStateManager stateManager) {
		super(stateManager);
		this.textureRegion = Assets.getInstance().getTextureRegion(
				"NormalCat01");

		this.inputComponents = new InputMultiplexer();
		this.inputLayer = new InputLayer(this.game.getHUDCamera());
		this.inputComponents.addProcessor(this.inputLayer);
		this.inputComponents.addProcessor(new InputAdapter() {

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				ButtonTest.this.positionX += 10;
				return true;
			}

		});

		this.positionX = (Constants.STANDARD_WIDTH - this.textureRegion
				.getRegionHeight()) / 2;
		this.positionY = (Constants.STANDARD_HEIGHT - this.textureRegion
				.getRegionHeight()) / 2;

		this.batch = this.game.getSpriteBatch();

		createNinjaCatButton();
		createSuperCatButton();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this.inputComponents);
	}

	public void createNinjaCatButton() {
		Button ninjaButton = new Button(Assets.getInstance().getTextureRegion(
				"NinjaCatButton"));

		ninjaButton.addListener(new ButtonListener() {

			@Override
			public void hasTouchDown() {
				ButtonTest.this.textureRegion = Assets.getInstance()
						.getTextureRegion("NinjaCat01");
			}

			@Override
			public void hasTouchUp() {
				ButtonTest.this.textureRegion = Assets.getInstance()
						.getTextureRegion("NormalCat01");
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
				ButtonTest.this.textureRegion = Assets.getInstance()
						.getTextureRegion("SuperCat01");
			}

			@Override
			public void hasTouchUp() {
				ButtonTest.this.textureRegion = Assets.getInstance()
						.getTextureRegion("NormalCat01");
			}

		});
		superButton.setPosition(0, 0.4f * Constants.STANDARD_HEIGHT);
		this.inputLayer.addButton(superButton);
	}

	@Override
	public void update(float dt) {
		// do nothing
	}

	@Override
	public void render() {

		this.inputLayer.draw(this.batch);
		this.game.getHUDCamera().update();
		this.batch.setProjectionMatrix(this.game.getHUDCamera().combined);
		this.batch.begin();
		this.batch.draw(this.textureRegion, this.positionX, this.positionY);
		this.batch.end();
	}

	@Override
	public void dispose() {
		// do nothing
	}

}
