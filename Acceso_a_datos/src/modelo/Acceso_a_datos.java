package modelo;

import java.util.ArrayList;

import entidades.Alumnos;

public interface Acceso_a_datos {
	
	
	
	public ArrayList<Alumnos> recogerDatos();
	
	public void insertar(Alumnos alumno);
	
	public void borrarUno(int cod);
	
	public void borrarTodo();
	
	public void actualizar(String dni, String nombre, String apellido, int telefono, String nacionalidad);
	
	public void  copiarTodos(ArrayList<Alumnos> arrLista);

	
	
		
	

}
