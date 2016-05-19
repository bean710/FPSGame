package me.felipeac.fpsengine.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import me.felipeac.fpsengine.components.BoxComponent;
import me.felipeac.fpsengine.components.ModelComponent;
import me.felipeac.fpsengine.components.PhysicsComponent;

public class BoxSystem extends IteratingSystem {


	public BoxSystem() {
		super(Family.all(BoxComponent.class, ModelComponent.class, PhysicsComponent.class).get());
	}


	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		btRigidBody o = (btRigidBody) entity.getComponent(PhysicsComponent.class).object;
		ModelInstance i = entity.getComponent(ModelComponent.class).instance;
		//entity.getComponent(ModelComponent.class).instance.transform.translate(new Vector3(0, 0.1f, 0));
	}

}
