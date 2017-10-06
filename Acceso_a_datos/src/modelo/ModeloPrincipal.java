package modelo;

import java.util.ArrayList;

import entidades.Alumnos;
import vista.VistaPrincipal;

public class ModeloPrincipal {

	private VistaPrincipal vistaPrincipal;
	private Acceso_a_datos modeloDatos;
	private Acceso_a_datos modeloDatosAux;

	public VistaPrincipal getVistaPrincipal() {
		return vistaPrincipal;
	}

	public void setVistaPrincipal(VistaPrincipal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}

	public void tipoAcceso(String tipoAcceso) {

		if (tipoAcceso.equals("Ficheros")) {
			modeloDatos = new ModeloFicheros();

		} else if (tipoAcceso.equals("SQL")) {
			modeloDatos = new ModeloSQL();

		}

	}

	public void tipoExportar(String tipoAcceso) {

		if (tipoAcceso.equals("Ficheros")) {
			modeloDatosAux = new ModeloFicheros();
			
		} else if (tipoAcceso.equals("SQL")) {
			modeloDatosAux = new ModeloSQL();

		}
		
		
		

	}

	public ArrayList<Alumnos> recogerDatos() {

		return modeloDatos.recogerDatos();

	}

	public void anadirAlumno() {
		// TODO Auto-generated method stub

	}

	public ArrayList<Alumnos> recogerDatos2() {
		return modeloDatosAux.recogerDatos();
		
	}

	public void copiarTodos() {
		ArrayList<Alumnos> arrLista;
		arrLista = modeloDatos.recogerDatos();
		modeloDatosAux.copiarTodos(arrLista);		
		
		
	}

}
