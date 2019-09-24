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
		
	}
}
