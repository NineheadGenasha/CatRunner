package com.ninehead.catrunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ninehead.catrunner.Assets;
import com.ninehead.catrunner.Constants;
import com.ninehead.catrunner.GamePreferences;
import com.ninehead.catrunner.entities.ParalaxBackground;
import com.ninehead.catrunner.entities.ParalaxBackgroundList;
import com.ninehead.catrunner.handlers.GameStateManager;

public class MenuState extends GameState {
	private final Stage stage;
	private ParalaxBackgroundList paralaxBackgrounds;
	private Image title;
	private ImageButton soundButton;
	private ImageButton musicButton;

	public MenuState(GameStateManager _gameStateManager) {
		super(_gameStateManager);
		this.stage = new Stage(new FitViewport(1280, 720));

		createParalaxBackgrounds();

		createPlayButton();
		createSoundButton();
		createMusicButton();

		createTitle();

	}

	@Override
	public void show() {

		setMusicButtonStatus();
		setSoundButtonStatus();
		Gdx.input.setInputProcessor(this.stage);
	}

	public void setMusicButtonStatus() {
		this.musicButton.setChecked(!GamePreferences.getInstance().isMusicOn());
	}

	public void setSoundButtonStatus() {
		this.soundButton.setChecked(!GamePreferences.getInstance().isSoundOn());
	}

	public void createParalaxBackgrounds() {
		this.paralaxBackgrounds = new ParalaxBackgroundList();

		this.paralaxBackgrounds.add(new ParalaxBackground(Assets.getInstance()
				.getTextureRegion("morningBackground"), 0));

		this.paralaxBackgrounds.add(new ParalaxBackground(Assets.getInstance()
				.getTextureRegion("morningMountain"), 20));

		this.paralaxBackgrounds.add(new ParalaxBackground(Assets.getInstance()
				.getTextureRegion("Hill"), 100));

		this.paralaxBackgrounds.add(new ParalaxBackground(Assets.getInstance()
				.getTextureRegion("Clouds"), 17));

		this.paralaxBackgrounds.add(new ParalaxBackground(Assets.getInstance()
				.getTextureRegion("titleGround"), 200));

	}

	public void createTitle() {
		this.title = new Image(Assets.getInstance().getTextureRegion("title"));
		this.title.setPosition((1280 - this.title.getWidth()) / 2, 720);
		this.title.setOrigin(this.title.getWidth() / 2,
				this.title.getHeight() / 2);
		this.title.addAction(Actions.sequence(
				Actions.moveBy(0f, -(this.title.getHeight() + 50), 0.5f),
				Actions.moveBy(0, 50, 0.2f)));
		this.stage.addActor(this.title);
	}

	public void createSoundButton() {
		ImageButtonStyle style = new ImageButtonStyle();
		style.up = new TextureRegionDrawable(Assets.getInstance()
				.getTextureRegion("SoundOn"));
		style.checked = new TextureRegionDrawable(Assets.getInstance()
				.getTextureRegion("SoundOff"));
		this.soundButton = new ImageButton(style);

		this.soundButton.setPosition(0.8f * Constants.STANDARD_WIDTH,
				0.83f * Constants.STANDARD_HEIGHT);

		this.soundButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GamePreferences.getInstance().setSoundOn(
						!MenuState.this.soundButton.isChecked());
			}

		});

		this.stage.addActor(this.soundButton);
	}

	public void createMusicButton() {
		ImageButtonStyle style = new ImageButtonStyle();
		style.up = new TextureRegionDrawable(Assets.getInstance()
				.getTextureRegion("MusicOn"));
		style.checked = new TextureRegionDrawable(Assets.getInstance()
				.getTextureRegion("MusicOff"));
		this.musicButton = new ImageButton(style);

		this.musicButton.setPosition(0.9f * Constants.STANDARD_WIDTH,
				0.83f * Constants.STANDARD_HEIGHT);

		this.musicButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GamePreferences.getInstance().setMusicOn(
						!MenuState.this.musicButton.isChecked());
			}

		});

		this.stage.addActor(this.musicButton);
	}

	public void createPlayButton() {
		ImageButton playButton = new ImageButton(new TextureRegionDrawable(
				Assets.getInstance().getTextureRegion("PlayButton")));
		playButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGameStateManager().pushState(GameStateManager.PLAY);
			}

		});

		playButton.setPosition(
				(Constants.STANDARD_WIDTH - playButton.getWidth()) / 2,
				0.25f * Constants.STANDARD_HEIGHT);

		this.stage.addActor(playButton);
	}

	@Override
	public void update(float dt) {
		this.paralaxBackgrounds.update(dt);
		this.stage.act(dt);

	}

	@Override
	public void render() {
		this.game.getSpriteBatch().setProjectionMatrix(
				this.game.getHUDCamera().combined);
		this.paralaxBackgrounds.draw(this.game.getSpriteBatch());
		this.stage.draw();

	}

	@Override
	public void dispose() {
		this.stage.dispose();

	}
}
