package me.felipeac.fpsengine.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCapsuleShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.linearmath.btDefaultMotionState;
import me.felipeac.fpsengine.components.PhysicsComponent;
import me.felipeac.fpsengine.components.PlayerComponent;

public class Player extends Entity {

	public Player(btDiscreteDynamicsWorld world, PerspectiveCamera camera) {
		btCapsuleShape shape = new btCapsuleShape(0.45f, 1.7f);
		btRigidBody body = new btRigidBody(70, new btDefaultMotionState(), shape);
		body.setSleepingThresholds(0, 0);

		float x = body.getWorldTransform().getTranslation(Vector3.Zero).x;
		float y = body.getWorldTransform().getTranslation(Vector3.Zero).y;
		float z = body.getWorldTransform().getTranslation(Vector3.Zero).z;
		camera.position.set(x, y+shape.getHalfHeight(), z);



		world.addRigidBody(body);

		add(new PlayerComponent());
		add(new PhysicsComponent(shape, body));
	}

}
