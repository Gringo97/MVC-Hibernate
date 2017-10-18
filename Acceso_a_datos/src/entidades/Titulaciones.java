package entidades;

public class Titulaciones {
	private int codTitulacion;
	private String nombreTitulacion;

	public Titulaciones(int codTitulacion, String nombreTitulacion) {
		super();
		this.codTitulacion = codTitulacion;
		this.nombreTitulacion = nombreTitulacion;
	}

	public int getCodTitulacion() {
		return codTitulacion;
	}

	public void setCodTitulacion(int codTitulacion) {
		this.codTitulacion = codTitulacion;
	}

	public String getNombreTitulacion() {
		return nombreTitulacion;
	}

	public void setNombreTitulacion(String nombreTitulacion) {
		this.nombreTitulacion = nombreTitulacion;
	}

}
