package me.felipeac.fpsengine.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class Hud implements Disposable {

	Stage stage;
	VisTable table;

	Viewport viewport;

	public VisLabel x;
	public VisLabel y;
	public VisLabel z;

	OrthographicCamera camera;

	public Hud() {
		camera = new OrthographicCamera();
		viewport = new ScreenViewport(camera);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);


		stage = new Stage(viewport);
		table = new VisTable();
		table.setFillParent(true);
		table.align(Alignment.TOP_LEFT.getAlignment());

		x = new VisLabel("x", Color.WHITE);
		y = new VisLabel("y", Color.WHITE);
		z = new VisLabel("z", Color.WHITE);

		table.add(x);
		table.row();
		table.add(y);
		table.row();
		table.add(z);

		stage.addActor(table);
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
	}

	public void render(float delta) {
		viewport.apply();
		camera.update();

		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
