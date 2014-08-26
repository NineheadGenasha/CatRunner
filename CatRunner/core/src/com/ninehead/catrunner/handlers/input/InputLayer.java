package com.ninehead.catrunner.handlers.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.ninehead.catrunner.Constants;

public class InputLayer extends InputAdapter {
	private final Array<Button> buttons;
	private final OrthographicCamera camera;

	public InputLayer(OrthographicCamera _camera) {
		this.buttons = new Array<Button>();
		this.camera = _camera;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float offsetX = (this.camera.position.x - (this.camera.viewportWidth / 2))
				/ this.camera.viewportWidth * Constants.STANDARD_WIDTH;
		float offsetY = (this.camera.position.y - (this.camera.viewportHeight / 2))
				/ this.camera.viewportHeight * Constants.STANDARD_HEIGHT;
		float x = (this.camera.viewportWidth / Gdx.graphics.getWidth() * screenX)
				- offsetX;
		float y = (this.camera.viewportHeight / Gdx.graphics.getHeight() * (Gdx.graphics
				.getHeight() - screenY)) - offsetY;
		System.out.println(x + " " + y);
		for (int i = 0; i < this.buttons.size; i++) {
			this.buttons.get(i).isTouchDown(x, y);
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float offsetX = (this.camera.position.x - (this.camera.viewportWidth / 2))
				/ this.camera.viewportWidth * Constants.STANDARD_WIDTH;
		float offsetY = (this.camera.position.y - (this.camera.viewportHeight / 2))
				/ this.camera.viewportHeight * Constants.STANDARD_HEIGHT;
		float x = (this.camera.viewportWidth / Gdx.graphics.getWidth() * screenX)
				- offsetX;
		float y = (this.camera.viewportHeight / Gdx.graphics.getHeight() * (Gdx.graphics
				.getHeight() - screenY)) - offsetY;
		for (int i = 0; i < this.buttons.size; i++) {
			this.buttons.get(i).isTouchUp(x, y);
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}

	public void addButton(Button button) {
		this.buttons.add(button);
	}

	public void draw(SpriteBatch batch) {
		this.camera.update();
		batch.setProjectionMatrix(this.camera.combined);
		batch.begin();
		for (int i = 0; i < this.buttons.size; i++) {
			this.buttons.get(i).draw(batch);
		}
		batch.end();
	}

	public OrthographicCamera getCamera() {
		return this.camera;
	}

}
