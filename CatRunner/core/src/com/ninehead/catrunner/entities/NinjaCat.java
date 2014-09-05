package com.ninehead.catrunner.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ninehead.catrunner.Assets;

public class NinjaCat extends Cat{

	public NinjaCat(Body body) {
		super(body);
		TextureRegion[] textureList = new TextureRegion[1];
		
		textureList[0] = Assets.getInstance().getTextureRegion("NinjaCat01");
		
		setAnimation(textureList, 1/7f);
	}

	@Override
	public void action() {
		body.applyLinearImpulse(new Vector2(0, 1500),body.getPosition(), true);
		
	}

	@Override
	public void update(float dt) {
		animation.update(dt);
	}
	
}
