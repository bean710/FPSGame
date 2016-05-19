package me.felipeac.fpsengine.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;

public class BasicMotionState extends btMotionState {

	Matrix4 transform;

	public BasicMotionState(Matrix4 transform) {
		this.transform = transform;
	}

	@Override
	public void getWorldTransform(Matrix4 worldTrans) {
		worldTrans.set(transform);
	}

	@Override
	public void setWorldTransform(Matrix4 worldTrans) {
		transform.set(worldTrans);
	}
}
