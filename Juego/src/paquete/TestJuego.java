package paquete;

public class TestJuego {

	public static void main(String[] args) {
		Juego.crearJuego();
		Juego j = Juego.getJuego();
		j.moverFelix(Orientacion.IZQUIERDA);
	}

}
