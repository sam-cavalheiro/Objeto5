import java.util.Random;

public class Particle extends Node {
	private Random random = new Random();
	
	public Particle(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
	}

	public void movement() {
		rectangle.x += -1 + random.nextInt(3);
		rectangle.y += -1 + random.nextInt(3);
		
		// TODO: Isso aqui não será necessário quando o loop ocorrer de forma suave.
		// O nosso loop está tão rápido, que para fins de testes está sendo necessário clampar
		rectangle.x = Math.max(0, Math.min(640 - rectangle.w, rectangle.x));
		rectangle.y = Math.max(0, Math.min(640 - rectangle.h, rectangle.y));
		//System.out.println(point.x + ", " + point.y);
	}
}
