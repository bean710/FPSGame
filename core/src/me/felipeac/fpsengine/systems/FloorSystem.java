package me.felipeac.fpsengine.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import me.felipeac.fpsengine.components.FloorComponent;
import me.felipeac.fpsengine.components.ModelComponent;
import me.felipeac.fpsengine.components.PhysicsComponent;

public class FloorSystem extends IteratingSystem {
	public FloorSystem() {
		super(Family.all(FloorComponent.class, PhysicsComponent.class, ModelComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		btCollisionObject o = entity.getComponent(PhysicsComponent.class).object;
		Vector3 trs = o.getWorldTransform().getTranslation(new Vector3(0,0,0));
	}
}
