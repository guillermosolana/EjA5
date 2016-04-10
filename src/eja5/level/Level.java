package eja5.level;

import java.util.Random;

import eja5.graphics.Shader;
import eja5.graphics.Texture;
import eja5.graphics.VertexArray;
import eja5.level.Pacman;
import eja5.maths.Matrix4f;
import eja5.maths.Vector3f;


public class Level {
	
	private Pacman pacman;
	private Rock[] rocks = new Rock[20];
	private Random random = new Random();
	

	public Level() {		
		createRocks();
		pacman = new Pacman();
	}
	
	public void update() {
		updateRocks();
		pacman.update();
		if(collision()){
			pacman.stop(pacman.getUltimaKey());
		}	
	}
	
	public void render() {
		pacman.render();
		renderRocks();
	}
	
	private boolean collision() {
		for (int i = 0; i < 20; i++) {
			float px = pacman.getX();
			float py = pacman.getY();
			float rx = rocks[i].getX();
			float ry = rocks[i].getY();
			
			float px0 = px - pacman.getSize()/2;
			float px1 = px + pacman.getSize()/2;
			float py0 = py - pacman.getSize()/2;
			float py1 = py + pacman.getSize()/2;
			
			float rx0 = rx;
			float rx1 = rx + Rock.getWidth();
			float ry0 = ry;
			float ry1 = ry + Rock.getHeight();		
			
			if (px1 > rx0 && px0 < rx1) {
				
				if (py1 > ry0 && py0 < ry1) {
					System.out.println("colision");
					System.out.println("ultima tecla: "+ pacman.getUltimaKey());
					return true;
				}
			}
		}
		return false;
	}
	
	private void createRocks() {
		Rock.create();

		
		for (int i = 0; i < 5; i++) {
			float n = i - 9.4f;
			rocks[i] = new Rock(n, 3.6f);
		}
		
		float r = random.nextFloat();
		for (int i = 5; i < 10; i++) {
			float n = i - 9.4f;
			rocks[i] = new Rock(n, 3.6f - r*10);
		}
		
		r = random.nextFloat();
		for (int i = 10; i < 15; i++) {
			float n = i - 9.4f;
			rocks[i] = new Rock(n, 3.6f - r*10);
		}
		
		r = random.nextFloat();
		for (int i = 15; i < 20; i++) {
			float n = i - 9.4f;
			rocks[i] = new Rock(n, 3.6f - r*10);
		}
	}
	
	private void updateRocks() {
	}
	
	private void renderRocks() {
		Shader.ROCK.enable();
		Shader.ROCK.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(0.0f, 0.0f, 0.0f)));
		Rock.getTexture().bind();
		Rock.getMesh().bind();
		
		for (int i = 0; i < 20; i++) {
			Shader.ROCK.setUniformMat4f("ml_matrix", rocks[i].getModelMatrix());
			Rock.getMesh().draw();
		}
		Rock.getMesh().unbind();
		Rock.getTexture().unbind();
		
	}
}

