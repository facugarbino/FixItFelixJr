package paquete;

public class TestJuego {

	static Juego j;

	public static void main(String[] args) {
		Juego.crearJuego("Facundo");
		j = Juego.getJuego();
		loop();
	}

	static void loop() {
		Contador timer = new Contador(750);
		System.out.println("Felix comienza en la posición " + j.getFelix().getPosicion());
		while (true) {
			if (j.estaPausado()) {
				j.hacerTodo();
				if (timer.contar()) {
					double xd = Math.random();
					if (xd < 0.25)
						j.getFelix().mover(Orientacion.IZQUIERDA);
					else if (xd < 0.50)
						j.getFelix().mover(Orientacion.DERECHA);
					else if (xd < 0.75)
						j.getFelix().mover(Orientacion.ABAJO);
					else
						j.getFelix().mover(Orientacion.ARRIBA);
					timer.resetear();
					System.out.println("Felix se ha movido a " + j.getFelix().getPosicion());
					for (int i = 0; i < 4; i++) {
						j.darMartillazo();
						System.out.println("Felix ha dado un martillazo!");
					}

				}
			}
		}
	}

}
