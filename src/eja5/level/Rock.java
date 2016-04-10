package eja5.level;

import eja5.graphics.Texture;
import eja5.graphics.VertexArray;
import eja5.maths.Matrix4f;
import eja5.maths.Vector3f;

public class Rock {

	private static float width = 1.0f, height = 1.0f;
	private static VertexArray mesh;
	private static Texture texture;

	private Matrix4f ml_matrix;
	private Vector3f position = new Vector3f();

	
	
	public static void create(){
		
		float[] vertices = new float[] {
				0.0f, 0.0f, 0.1f,
				0.0f, height, 0.1f,
				width, height, 0.1f,
				width, 0.0f, 0.1f
		};
		
		byte[] indices = new byte[] {
				0, 1, 2,
				2, 3, 0
		};
		
		float[] tcs = new float[] {
				0, 1,
				0, 0,
				1, 0,
				1, 1
		};
		
		mesh = new VertexArray(vertices, indices, tcs);
		texture = new Texture("res/rock.png");	
	}
	
	public Rock(float x, float y){
		position.x = x;
		position.y = y;
		ml_matrix = Matrix4f.translate(position);
	}
//	public void render(){
//		Shader.ROCK.enable();
//		Shader.ROCK.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
//		texture.bind();
//		mesh.render();
//		Shader.ROCK.disable();
//	}
//	
	public float getY() {
		return position.y;
	}


	public float getX() {
		return position.x;
	}

	
	public static float getWidth() {
		return width;
	}

	public static float getHeight() {
		return height;
	}
	
	public Matrix4f getModelMatrix() {
		return ml_matrix;
	}
	
	public static VertexArray getMesh() {
		return mesh;
	}
	
	public static Texture getTexture() {
		return texture;
	}
	
	
}
