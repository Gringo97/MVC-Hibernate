package modelo;

import java.util.ArrayList;

import javax.swing.JTextField;

import entidades.Alumnos;
import entidades.Titulaciones;
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

	

	public ArrayList<Alumnos> recogerDatos2() {
		return modeloDatosAux.recogerDatos();
		
	}

	public void copiarTodos() {
		ArrayList<Alumnos> arrLista;
		arrLista = modeloDatos.recogerDatos();
		if(this.modeloDatos.getClass() == ModeloFicheros.class ) {
			modeloDatosAux.borrarTodo();
		}
		modeloDatosAux.copiarTodos(arrLista);		
		
		
	}

	public void anadirAlumno(Alumnos nuevo) {
		modeloDatos.insertar(nuevo);
		vistaPrincipal.crearTabla();
		
	}
	public void actualizar(Alumnos alumno){
		modeloDatos.actualizar(alumno);
		vistaPrincipal.crearTabla();
	}

	public void borrarTodos() {
		modeloDatos.borrarTodo();
	
	}

	public void borrarUno(int cod) {
		modeloDatos.borrarUno(cod);
		vistaPrincipal.crearTabla();
		
	}

	public void anadirCurso(Titulaciones curso) {
		// TODO Auto-generated method stub
		modeloDatos.insertarTitulacion(curso);
		
		
	}
	

	
}
