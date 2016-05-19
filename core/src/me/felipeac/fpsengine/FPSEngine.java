package me.felipeac.fpsengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.bullet.Bullet;
import me.felipeac.fpsengine.screens.GameScreen;

public class FPSEngine extends Game {

	
	@Override
	public void create () {
		Bullet.init();
		setScreen(new GameScreen());
	}
}
