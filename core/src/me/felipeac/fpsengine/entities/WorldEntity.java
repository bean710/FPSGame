package me.felipeac.fpsengine.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import me.felipeac.fpsengine.components.ModelComponent;
import me.felipeac.fpsengine.components.PhysicsComponent;
import me.felipeac.fpsengine.physics.BasicMotionState;

public class WorldEntity extends Entity {

	public WorldEntity(btDiscreteDynamicsWorld world, ObjLoader modelLoader) {
		Model model = modelLoader.loadModel(Gdx.files.getFileHandle("testworld.obj", Files.FileType.Internal));
		ModelInstance instance = new ModelInstance(model);
		instance.transform.translate(0, -10, 0);

		btCollisionShape shape = Bullet.obtainStaticNodeShape(model.nodes);
		btRigidBody body = new btRigidBody(0, new BasicMotionState(instance.transform), shape, new Vector3(0, 0, 0));

		world.addRigidBody(body);

		add(new PhysicsComponent(shape, body));
		add(new ModelComponent(model, instance));
	}

}
