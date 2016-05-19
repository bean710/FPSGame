package me.felipeac.fpsengine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.felipeac.fpsengine.FPSEngine;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.samples = 4;
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		new LwjglApplication(new FPSEngine(), config);
	}
}
