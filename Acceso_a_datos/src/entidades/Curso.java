package entidades;

public class Curso {
	private int codCurso;
	private String nombreCurso;

	public Curso(int codCurso, String nombreCurso) {
		this.codCurso = codCurso;
		this.nombreCurso = nombreCurso;
	}

	public int getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

}
