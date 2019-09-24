package paquete;

public enum Orientacion {
	IZQUIERDA, DERECHA, ARRIBA, ABAJO;

	public Orientacion invertir() {
		switch (this) {
		case IZQUIERDA:
			return DERECHA;
		case DERECHA:
			return IZQUIERDA;
		case ABAJO:
			return ARRIBA;
		case ARRIBA:
			return ABAJO;
		default:
			return null;
		}
	}
}
