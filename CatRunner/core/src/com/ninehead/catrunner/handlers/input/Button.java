package com.ninehead.catrunner.handlers.input;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Button {

	private final TextureRegion textureRegion;
	private final Vector2 bottomLeftPosition;
	private final Rectangle rectangle;
	private final Array<ButtonListener> listeners;

	public Button(TextureRegion _textureRegion) {
		this.listeners = new Array<ButtonListener>();
		this.bottomLeftPosition = new Vector2();

		this.textureRegion = _textureRegion;
		this.rectangle = new Rectangle(this.bottomLeftPosition.x,
				this.bottomLeftPosition.y, this.textureRegion.getRegionWidth(),
				this.textureRegion.getRegionHeight());
		System.out.println(this.bottomLeftPosition.x + " "
				+ this.bottomLeftPosition.y + " "
				+ this.textureRegion.getRegionWidth()
				+ this.textureRegion.getRegionHeight());
	}

	void isTouchDown(float x, float y) {
		if (this.rectangle.contains(x, y)) {
			for (int i = 0; i < this.listeners.size; i++) {
				this.listeners.get(i).hasTouchDown();
			}
		}
	}

	void isTouchUp(float x, float y) {
		if (this.rectangle.contains(x, y)) {
			for (int i = 0; i < this.listeners.size; i++) {
				this.listeners.get(i).hasTouchUp();
			}
		}
	}

	void draw(SpriteBatch batch) {
		batch.draw(this.textureRegion, this.bottomLeftPosition.x,
				this.bottomLeftPosition.y);
	}

	public void addListener(ButtonListener _listener) {
		this.listeners.add(_listener);
	}

	public void setPosition(float bottomLeftX, float bottomLeftY) {
		this.bottomLeftPosition.x = bottomLeftX;
		this.bottomLeftPosition.y = bottomLeftY;
		this.rectangle.setPosition(bottomLeftX, bottomLeftY);
	}
}
