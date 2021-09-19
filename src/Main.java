public class Main {
	
	public static void main(String[] args) {
		//QuadTreeTest quadTreeTest = new QuadTreeTest();
		
		Simulation simulation = new Simulation();
		
		while (true) {
			simulation.update();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
