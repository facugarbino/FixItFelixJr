package paquete;

public class Juego {
	private Ranking ranking;
	private Jugador jugador;
	private Nivel nivel;
	private Mapa mapa;
	
	public Juego() {
		nivel = new Nivel(10,10,10,10,10,2,200,10,40);
		mapa = nivel.generarMapaSiguiente();
	}
	
	public void loop() {
		
		if (mapa.felixMartilla()) {
			if (!mapa.getEdificio().getSeccion().estaSana()) {
				jugador.sumarPuntos(100);
			} else {
				jugador.sumarPuntos(500);
			}
		}
	}
}
