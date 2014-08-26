package com.ninehead.catrunner.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParalaxBackgroundList {
	ArrayList<ParalaxBackground> bgList;
	
	public ParalaxBackgroundList() {
		bgList = new ArrayList<ParalaxBackground>();
	}
	
	public void add(ParalaxBackground bg){
		bgList.add(bg);
	}
	
	public void draw(SpriteBatch batch){
		for(ParalaxBackground bg : bgList){
			bg.draw(batch);
		}
	}
	
	public void update(float dt){
		for(ParalaxBackground bg : bgList){
			bg.update(dt);
		}
	}
}
