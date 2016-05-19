package me.felipeac.fpsengine.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import me.felipeac.fpsengine.components.ModelComponent;

public class Ceiling extends Entity {

	public Ceiling(ModelBuilder builder) {
		Model model = builder.createBox(10, 0.5f, 10, new Material(ColorAttribute.createDiffuse(Color.BLUE)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		ModelInstance instance = new ModelInstance(model);
		instance.transform.setTranslation(0, 5, 0);

		add(new ModelComponent(model, instance));
	}

}
