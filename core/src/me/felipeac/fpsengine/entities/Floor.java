package me.felipeac.fpsengine.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btStaticPlaneShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import me.felipeac.fpsengine.components.FloorComponent;
import me.felipeac.fpsengine.components.ModelComponent;
import me.felipeac.fpsengine.components.PhysicsComponent;
import me.felipeac.fpsengine.physics.BasicMotionState;

public class Floor extends Entity {

	public Floor(btDiscreteDynamicsWorld world, ModelBuilder builder) {
		Model model = builder.createBox(10, 0.5f, 10, new Material(ColorAttribute.createDiffuse(Color.RED)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		ModelInstance instance = new ModelInstance(model);
		instance.transform.setTranslation(0, -5, 0);

		btCollisionShape shape = new btStaticPlaneShape(new Vector3(0, 1, 0), 1);
		btRigidBody body = new btRigidBody(0, new BasicMotionState(instance.transform), shape, new Vector3(0, 0, 0));

		world.addRigidBody(body);

		add(new FloorComponent());
		add(new PhysicsComponent(shape, body));
		add(new ModelComponent(model, instance));
	}

}
