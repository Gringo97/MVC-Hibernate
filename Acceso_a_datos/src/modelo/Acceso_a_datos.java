package modelo;

import java.util.ArrayList;

import entidades.Alumnos;
import entidades.Titulaciones;

public interface Acceso_a_datos {
	
	
	
	public ArrayList<Alumnos> recogerDatos();
	public void insertar(Alumnos alumno);
	public void borrarUno(int cod);
	public void borrarTodo();
	public void actualizar(String dni, String nombre, String apellido, int telefono, String nacionalidad);
	public void copiarTodos(ArrayList<Alumnos> arrLista);

	
	public ArrayList<Titulaciones> recogerDatosTitulacion();
	public void insertarTitulacion(Titulaciones titulacion);
	public void borrarUnoTitulacion(int cod);
	public void borrarTodoTitulacion();
	public void actualizarTitulacion(Titulaciones titulacion);
	public void  copiarTodosTitulacion(ArrayList<Titulaciones> arrLista);
	/*
	 * public void insertar(Titulaciones titulacion);
	 * 
	 * 
	 */
	
	
		
	

}
