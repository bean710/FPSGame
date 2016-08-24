package me.felipeac.fpsengine.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import me.felipeac.fpsengine.entities.*;
import me.felipeac.fpsengine.hud.Hud;
import me.felipeac.fpsengine.systems.BoxSystem;
import me.felipeac.fpsengine.systems.FloorSystem;
import me.felipeac.fpsengine.systems.ModelSystem;
import me.felipeac.fpsengine.systems.PlayerSystem;

public class GameScreen extends ScreenAdapter {

	private PerspectiveCamera camera;
	private ModelBatch batch;
	private Environment lights;

	private Viewport viewport;

	private ModelBuilder builder;
	private ObjLoader modelLoader;

	private Engine engine;

	private btBroadphaseInterface broadphase;
	private btCollisionConfiguration collisionConfiguration;
	private btCollisionDispatcher collisionDispatcher;
	private btSequentialImpulseConstraintSolver solver;
	private btDiscreteDynamicsWorld world;
	private DebugDrawer debugDrawer;

	private Hud hud;

	public GameScreen() {
		VisUI.load();
		hud = new Hud();

		camera = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.lookAt(0, 0, 0);
		camera.near = 0.01f;
		camera.far = 300f;

		viewport = new FillViewport(800, 800, camera);
		viewport.apply();

		batch = new ModelBatch();

		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 0.1f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));


		broadphase = new btDbvtBroadphase();
		collisionConfiguration = new btDefaultCollisionConfiguration();
		collisionDispatcher = new btCollisionDispatcher(collisionConfiguration);
		solver = new btSequentialImpulseConstraintSolver();

		world = new btDiscreteDynamicsWorld(collisionDispatcher, broadphase, solver, collisionConfiguration);
		world.setGravity(new Vector3(0, -10f, 0));

		debugDrawer = new DebugDrawer();
		debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
		world.setDebugDrawer(debugDrawer);

		builder = new ModelBuilder();
		modelLoader = new ObjLoader();

		engine = new Engine();
		engine.addSystem(new BoxSystem());
		engine.addSystem(new ModelSystem(batch, lights));
		engine.addSystem(new FloorSystem());
		engine.addSystem(new PlayerSystem(camera, hud));

		engine.addEntity(new Ceiling(builder));
		engine.addEntity(new Box(world, builder));
		//engine.addEntity(new Floor(world, builder));
		engine.addEntity(new Player(world, camera));
		engine.addEntity(new WorldEntity(world, modelLoader));


	}


	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		viewport.apply();
		camera.update();

		batch.begin(camera);

		world.stepSimulation(1/60f);

		engine.update(delta);

		batch.end();

		debugDrawer.begin(camera);

		if(Gdx.input.isKeyPressed(Input.Keys.B)) {
			world.debugDrawWorld();
		}

		debugDrawer.end();

		hud.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		hud.resize(width, height);
	}

	@Override
	public void dispose() {
		hud.dispose();
		VisUI.dispose();
		batch.dispose();
		world.dispose();
		solver.dispose();
		collisionConfiguration.dispose();
		broadphase.dispose();
	}
}
