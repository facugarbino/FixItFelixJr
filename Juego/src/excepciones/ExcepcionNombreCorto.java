package excepciones;

@SuppressWarnings("serial")
public class ExcepcionNombreCorto extends ExcepcionNombreInvalido {

	public String getMessage() {
		return "Este nombre es muy corto. Debe tener al menos 2 carácteres.";
	}
}
