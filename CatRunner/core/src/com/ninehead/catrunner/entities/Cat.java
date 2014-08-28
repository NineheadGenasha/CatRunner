package com.ninehead.catrunner.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.ninehead.catrunner.Assets;

public class Cat extends B2DEntity{
	
	public Cat(Body body){
		super(body);
		TextureRegion[] textureList = new TextureRegion[4];
		
		textureList[0] = Assets.getInstance().getTextureRegion("NormalCat01");
		textureList[1] = Assets.getInstance().getTextureRegion("NormalCat02");
		textureList[2] = Assets.getInstance().getTextureRegion("NormalCat03");
		textureList[3] = Assets.getInstance().getTextureRegion("NormalCat04");
		
		setAnimation(textureList, 1/7f);
	}
	
}
