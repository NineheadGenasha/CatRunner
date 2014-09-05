package com.ninehead.catrunner.entities;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Cat extends B2DEntity{
	
	public Cat(Body body) {
		super(body);
	}
	
	public abstract void action();
	public abstract void update(float dt);
	
}
