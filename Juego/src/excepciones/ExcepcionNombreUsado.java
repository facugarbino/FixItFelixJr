package excepciones;

@SuppressWarnings("serial")
public class ExcepcionNombreUsado extends ExcepcionNombreInvalido {

	public String getMessage() {
		return "Este nombre ya está en uso. Elija otro.";
	}
}
