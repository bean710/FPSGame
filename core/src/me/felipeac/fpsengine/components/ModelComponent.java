package me.felipeac.fpsengine.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class ModelComponent implements Component {

	public Model model;
	public ModelInstance instance;

	public ModelComponent(Model model, ModelInstance instance) {
		this.model = model;
		this.instance = instance;
	}

}
