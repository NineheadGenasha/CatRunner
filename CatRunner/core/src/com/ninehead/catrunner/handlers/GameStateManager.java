package com.ninehead.catrunner.handlers;

import java.util.Stack;

import com.ninehead.catrunner.CRGame;
import com.ninehead.catrunner.states.GameState;
import com.ninehead.catrunner.states.MenuState;

public class GameStateManager {

	private final CRGame game;
	private final Stack<GameState> gameStates;

	public static final int MENU = 234234;

	public GameStateManager(CRGame game) {
		this.game = game;
		this.gameStates = new Stack<GameState>();
	}

	public void update(float dt) {
		this.gameStates.peek().update(dt);
	}

	public void render() {
		this.gameStates.peek().render();
	}

	public CRGame game() {
		return this.game;
	}

	public void pushState(int state) {
		this.gameStates.push(getState(state));
	}

	public void popState() {
		GameState gs = this.gameStates.pop();
		gs.dispose();
	}

	private GameState getState(int state) {
		if (state == MENU)
			return new MenuState(this);
		return null;
	}

}
