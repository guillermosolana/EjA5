package eja5.maths;

public class PtoMedio {
	private float x = 0.0f, y = 0.0f;
	
	public PtoMedio(){
		
	}
	private PtoMedio(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public PtoMedio getPtoMedio(float x0, float x1, float y0, float y1){
		
		return new PtoMedio((x0 + x1)/2 , (y0 + y1)/2);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	
	
}
