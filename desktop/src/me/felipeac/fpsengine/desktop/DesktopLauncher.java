package me.felipeac.fpsengine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.felipeac.fpsengine.FPSEngine;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.samples = 4;
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		new LwjglApplication(new FPSEngine(), config);
	}
}
