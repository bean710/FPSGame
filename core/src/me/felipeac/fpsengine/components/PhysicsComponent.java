package me.felipeac.fpsengine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;

public class PhysicsComponent implements Component {

	public btCollisionShape shape;
	public btCollisionObject object;

	public PhysicsComponent(btCollisionShape shape, btCollisionObject object) {
		this.shape = shape;
		this.object = object;
	}

}
