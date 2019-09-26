package paquete;

public class TestJuego {

	public static void main(String[] args) {
		Juego.crearJuego();
		Juego j = Juego.getJuego();
		j.moverFelix(Orientacion.IZQUIERDA);
		System.out.println("Felix se ha movido a la izquierda");
		long puntaje = j.getFelix().getPuntaje();
		for (int i = 0; i < 4; i++) {
			j.darMartillazo();
			System.out.println("Felix ha dado un martillazo!");
			if (j.getFelix().getPuntaje()!=puntaje) {
				System.out.println("Felix ha ganado "+ (j.getFelix().getPuntaje()-puntaje) + " puntos");
				puntaje = j.getFelix().getPuntaje();
			}
		}
		

	}

}
