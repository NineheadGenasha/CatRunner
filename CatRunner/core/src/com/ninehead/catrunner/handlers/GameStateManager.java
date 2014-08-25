package com.ninehead.catrunner.handlers;

import java.util.Stack;

import com.ninehead.catrunner.CRGame;
import com.ninehead.catrunner.states.GameState;
import com.ninehead.catrunner.states.Menu;

public class GameStateManager {
	
	private CRGame game;
	private Stack<GameState> gameStates;
	
	public static final int MENU = 234234;
	
	public GameStateManager(CRGame game){
		this.game = game;
		gameStates = new Stack<GameState>();
	}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	
	public void render(){
		gameStates.peek().render();
	}
	
	public CRGame game(){
		return game;
	}
	
	public void pushState(int state){
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState gs = gameStates.pop();
		gs.dispose();
	}
	
	private GameState getState(int state){
		if(state == MENU) return new Menu(this);
		return null;
	}
	
}
