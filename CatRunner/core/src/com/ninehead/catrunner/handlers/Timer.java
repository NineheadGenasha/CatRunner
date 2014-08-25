package com.ninehead.catrunner.handlers;

public class Timer {

	private float accum;
	
	public Timer(){
		accum = 0f;
	}
	
	public void reset(){
		accum = 0f;
	}
	
	public float now(){
		return accum;
	}
	
	public void update(float dt){
		accum += dt;
	}
	
}
