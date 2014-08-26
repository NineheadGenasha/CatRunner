package com.ninehead.catrunner.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParalaxBackground {

	private TextureRegion tex;
	private int x;
	private float x_now;
	private int y;
	private int width;
	private int height;
	private float speed;
	
	public ParalaxBackground(TextureRegion tex, float speed) {
		setBackground(tex, speed);
	}
	
	public ParalaxBackground(TextureRegion tex){
		setBackground(tex, 0f);
	}
	
	public void setBackground(TextureRegion tex, float speed){
		this.tex = tex;
		x = tex.getRegionX();
		y = tex.getRegionY();
		x_now = x;
		width = tex.getRegionWidth();
		height = tex.getRegionHeight();
		this.speed = speed;
	}
	
	public void draw(SpriteBatch batch){
		batch.begin();
		tex.setRegion((int) x_now, y, x
				+ width - (int) x_now, height);
		batch.draw(tex, 0, 0);
		tex.setRegion(x, y, (int) x_now
				- x, height);
		batch.draw(tex, x + width
				- (int) x_now, 0);
		batch.end();
	}
	
	public void update(float dt){
		x_now += speed*dt;
		if (x_now > x + width)
			x_now = x;
	}
	
}
