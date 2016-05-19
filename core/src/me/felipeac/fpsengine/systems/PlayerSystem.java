package me.felipeac.fpsengine.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCapsuleShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import me.felipeac.fpsengine.components.PhysicsComponent;
import me.felipeac.fpsengine.components.PlayerComponent;
import me.felipeac.fpsengine.hud.Hud;

public class PlayerSystem extends IteratingSystem {

	PerspectiveCamera camera;
	Hud hud;
	float speed = 2.5f;

	float sens = 0.1f;

	float deltaX = 0;
	float deltaY = 0;

	public PlayerSystem(PerspectiveCamera camera, Hud hud) {
		super(Family.all(PlayerComponent.class, PhysicsComponent.class).get());
		this.camera = camera;
		this.hud = hud;

		Gdx.input.setCursorCatched(true);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		btCapsuleShape shape = (btCapsuleShape) entity.getComponent(PhysicsComponent.class).shape;
		btRigidBody body = (btRigidBody) entity.getComponent(PhysicsComponent.class).object;

		float x = body.getWorldTransform().getTranslation(Vector3.Zero).x;
		float y = body.getWorldTransform().getTranslation(Vector3.Zero).y;
		float z = body.getWorldTransform().getTranslation(Vector3.Zero).z;

		camera.position.set(x, y+shape.getHalfHeight(), z);

		Vector3 finalForce = new Vector3(0, 0, 0);
		Vector3 dir = camera.direction.cpy();
		dir.crs(camera.up.cpy());

		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			finalForce.x += dir.x * speed;
			finalForce.z += dir.z * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			finalForce.x -= dir.x * speed;
			finalForce.z -= dir.z * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			finalForce.z += dir.x * speed;
			finalForce.x -= dir.z * speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			finalForce.z -= dir.x * speed;
			finalForce.x += dir.z * speed;
		}

		body.setLinearVelocity(new Vector3(finalForce.x, body.getLinearVelocity().y, finalForce.z));

		hud.x.setText("x = " + camera.direction.x);
		hud.y.setText("y = " + camera.direction.y);

		// TODO: make better first person camera controls here:

		camera.direction.rotate(camera.up, -Gdx.input.getDeltaX()/3f);
		camera.direction.rotate(camera.direction.cpy().crs(camera.up).nor(), -Gdx.input.getDeltaY()/3f);

	}

}
