package com.ninehead.catrunner.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ParalaxBackground {

	private TextureRegion tex;
	private float x_now;
	private int width;
	private float speed;
	
	public ParalaxBackground(TextureRegion tex, float speed) {
		setBackground(tex, speed);
	}
	
	public ParalaxBackground(TextureRegion tex){
		setBackground(tex, 0f);
	}
	
	public void setBackground(TextureRegion tex, float speed){
		this.tex = tex;
		x_now = 0;
		width = tex.getRegionWidth();
		this.speed = speed;
	}
	
	public void draw(SpriteBatch batch){
		batch.begin();
		batch.draw(tex, -x_now, 0);
		batch.draw(tex, width
				- x_now, 0);
		batch.end();
	}
	
	public void update(float dt){
		x_now += speed*dt;
		if (x_now > width)
			x_now -= width;
	}
	
}
