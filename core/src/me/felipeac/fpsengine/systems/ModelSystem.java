package me.felipeac.fpsengine.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import me.felipeac.fpsengine.components.ModelComponent;

public class ModelSystem extends IteratingSystem {

	ModelBatch batch;
	Environment env;

	public ModelSystem(ModelBatch batch, Environment env) {
		super(Family.all(ModelComponent.class).get());
		this.batch = batch;
		this.env = env;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		batch.render(entity.getComponent(ModelComponent.class).instance, env);
	}
}
