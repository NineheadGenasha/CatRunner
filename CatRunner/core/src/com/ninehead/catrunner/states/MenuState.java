package com.ninehead.catrunner.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ninehead.catrunner.Assets;
import com.ninehead.catrunner.handlers.GameStateManager;

public class MenuState extends GameState {
	private final Stage stage;
	private Image background;
	private Image mountain;
	private Image hill;
	private Image title;

	public MenuState(GameStateManager _gameStateManager) {
		super(_gameStateManager);
		this.stage = new Stage(new FitViewport(1280, 720));
		createBackground();
		createMountain();
		createHill();
		createTitle();

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this.stage);
	}

	public void createBackground() {
		this.background = new Image(Assets.getInstance().getTextureRegion(
				"morningBackground"));
		this.stage.addActor(this.background);
	}

	public void createMountain() {
		this.mountain = new Image(Assets.getInstance().getTextureRegion(
				"morningMountain"));
		this.stage.addActor(this.mountain);
	}

	public void createHill() {
		this.hill = new Image(Assets.getInstance().getTextureRegion("Hill"));
		this.stage.addActor(this.hill);
	}

	public void createTitle() {
		this.title = new Image(Assets.getInstance().getTextureRegion("title"));
		this.title.setPosition((1280 - this.title.getWidth()) / 2, 720);
		this.title.setOrigin(this.title.getWidth() / 2,
				this.title.getHeight() / 2);
		this.title.addAction(Actions.sequence(Actions.moveBy(0f,
				-(this.title.getHeight() + 50), 0.5f), Actions.moveBy(0, 50,
				0.2f), Actions.forever(Actions.sequence(
				Actions.rotateBy(10, 1), Actions.rotateBy(-10, 1)))));
		this.stage.addActor(this.title);
	}

	@Override
	public void update(float dt) {
		this.stage.act(dt);

	}

	@Override
	public void render() {
		this.stage.draw();

	}

	@Override
	public void dispose() {
		this.stage.dispose();

	}
}
