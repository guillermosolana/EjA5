package eja5.level;

import static org.lwjgl.glfw.GLFW.*;

import eja5.graphics.Shader;
import eja5.graphics.Texture;
import eja5.graphics.VertexArray;
import eja5.input.Input;
import eja5.maths.Matrix4f;
import eja5.maths.Vector3f;

public class Pacman {

	private float size = 1.0f;
	private VertexArray mesh;
	private Texture texture;
	
	private Vector3f position = new Vector3f();
	private float rot;
	private float py = 0.0f;
	private float px = 0.0f;
	private String ultimaKey = "";

	
	
	public Pacman() {

		float[] vertices = new float[] {
				-size / 2.0f, -size / 2.0f, 0.2f,
				-size / 2.0f,  size / 2.0f, 0.2f,
				 size / 2.0f,  size / 2.0f, 0.2f,
				 size / 2.0f, -size / 2.0f, 0.2f
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
		texture = new Texture("res/pacman.gif");
		
	}
	
	public void update() {
		
		
		
		if (Input.isKeyDown(GLFW_KEY_UP)){
			//rot = 1 * 90.0f;
			ultimaKey = "UP";
			if(py > 4.2f){
				py -= 0.10f;
			}else{
				py += 0.10f;
			}
		}else if (Input.isKeyDown(GLFW_KEY_DOWN)){
			//rot = 1 * 270.0f;
			ultimaKey = "DOWN";
			if(py < -4.5f){
				py += 0.10f;
			}else{
				py -= 0.10;
			}
			
		}else if (Input.isKeyDown(GLFW_KEY_RIGHT)){
			//rot = 1 * 360.0f;			
			ultimaKey = "RIGHT";
			if(px > 8.5f){
				px -= 0.10f;
			}else{
				px += 0.10f;
			}
		}else if (Input.isKeyDown(GLFW_KEY_LEFT)){
			//rot = 1 * 180.0f;
			ultimaKey = "LEFT";
			if(px < -8.5f){
				px += 0.10f;
			}else{
				px -= 0.10f;
			}
		}
		
		if(Input.isKeyDown(GLFW_KEY_G)){
			rot += 1 * 180.0f;
		}
		
		
			
		position.y = py;
		position.x = px;
		
	}
	
	public void stop(String key){
		switch (key){
			case "UP" : {
				py -= 0.10f;
				break;
			}
			case "DOWN" : {
				py += 0.10f;
				break;
			}
			case "RIGHT" : {
				px -= 0.10f;
				break;
			}
			case "LEFT" : {
				px += 0.10f;
				break;
			}
		}
		position.x = px;
		position.y = py;
	}
	
	
	public void render() {
		Shader.PACMAN.enable();
		Shader.PACMAN.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
		texture.bind();
		mesh.render();
		Shader.PACMAN.disable();
	}

	
	public float getX(){
		return this.position.x;
	}
	public float getY(){
		return this.position.y;
	}
	
	public float getSize(){
		return this.size;
	}

	public String getUltimaKey() {
		return ultimaKey;
	}
	
	

	
}
