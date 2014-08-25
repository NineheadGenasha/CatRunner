package com.ninehead.catrunner;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MenuState {
	private final Stage stage;
	private final Image background;
	private final Image mountain;
	private final Image hill;
	private final Image title;

	public MenuState() {
		this.stage = new Stage(new FitViewport(1280, 720));
		this.background = new Image(Assets.getInstance().getTextureRegion(
				"morningBackground"));
		this.mountain = new Image(Assets.getInstance().getTextureRegion(
				"morningMountain"));
		this.hill = new Image(Assets.getInstance().getTextureRegion("Hill"));
		this.title = new Image(Assets.getInstance().getTextureRegion("title"));

		this.stage.addActor(this.background);
		this.stage.addActor(this.mountain);
		this.stage.addActor(this.hill);

		this.title.setPosition((1280 - this.title.getWidth()) / 2, 720);
		this.title.setOrigin(this.title.getWidth() / 2,
				this.title.getHeight() / 2);
		this.title.addAction(Actions.sequence(Actions.moveBy(0f,
				-(this.title.getHeight() + 50), 0.5f), Actions.moveBy(0, 50,
				0.2f), Actions.forever(Actions.sequence(
				Actions.rotateBy(10, 1), Actions.rotateBy(-10, 1)))));
		this.stage.addActor(this.title);

	}

	public void render(float _deltaTime) {
		this.stage.act(_deltaTime);
		this.stage.draw();
	}
}
