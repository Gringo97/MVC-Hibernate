package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entidades.Alumnos;
import entidades.Titulaciones;

public class ModeloFicheros extends ModeloPrincipal implements Acceso_a_datos {

	ArrayList<Alumnos> listAumno;
	File fAlumnos;
	private int nAlumnos = 0;
	ArrayList<Titulaciones> listTitulacion;
	File fTitulaciones;
	private int nTitulaciones = 0;

	@Override
	public ArrayList<Alumnos> recogerDatos() {
		listAumno = new ArrayList<Alumnos>();
		fAlumnos = new File("bbdd/alumnos.txt");

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fAlumnos));
			String text = null;
			Alumnos alumno = null;

			while ((text = reader.readLine()) != null) {

				String[] datosaux = text.split(",");

				alumno = new Alumnos(Integer.parseInt(datosaux[0]), datosaux[1], datosaux[2], datosaux[3],
						Integer.parseInt(datosaux[4]), datosaux[5],datosaux[6]);
				listAumno.add(alumno);
				nAlumnos = Integer.parseInt(datosaux[0]);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listAumno;

	}

	@Override
	public void copiarTodos(ArrayList<Alumnos> arrLista) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("bbdd/alumnos.txt");
			pw = new PrintWriter(fichero);

			for (int i = 0; i < arrLista.size(); i++) {

				pw.println((i + 1) + "," + arrLista.get(i).getDni().toLowerCase() + "," + arrLista.get(i).getNombre().toLowerCase() + ","
						+ arrLista.get(i).getApellido().toLowerCase() + "," + arrLista.get(i).getTelefono() + ","
						+ arrLista.get(i).getNacionalidad().toLowerCase());

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (fichero != null) {
				pw.close();
				fichero.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insertar(Alumnos alumno) {
		
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("bbdd/alumnos.txt",true);
			pw = new PrintWriter(fichero);

				pw.println((nAlumnos+1) + "," + alumno.getDni().toLowerCase() + "," + alumno.getNombre().toLowerCase() + ","
						+ alumno.getApellido().toLowerCase() + "," + alumno.getTelefono() + ","
						+ alumno.getNacionalidad().toLowerCase());
				nAlumnos++;
				System.out.println(nAlumnos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (fichero != null) {
				pw.close();
				fichero.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see modelo.ModeloPrincipal#borrarUno(int)
	 * trato de coger el array que deja recogerDatos() y con ese array hacer un .remove por codigo
	 */
	@Override
	public void borrarUno(int cod) {
		//recogerDatos();
		System.out.println("size----" + listAumno.size());
		for (int i = 0; i < listAumno.size(); i++) {
			if(listAumno.get(i).getCod()==cod){
				listAumno.remove(i);
			}
		}
		copiarTodos(listAumno);
	}

	@Override
	public void borrarTodo() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fAlumnos);
			writer.print("");
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void actualizar(Alumnos alumno) {
		
		 borrarUno(alumno.getCod());
		 insertar(alumno);
		
		 
		/*
		for (int i = 0; i < listAumno.size(); i++) {
			int cod;
			if(listAumno.get(i).getDni()==dni){
				cod = listAumno.get(i).getCod();
				listAumno.remove(i);
				Alumnos alumno = new Alumnos(cod,dni, nombre, apellidos, telefono, nacionalidad);
				listAumno.add(alumno);
				
			}
		}
		copiarTodos(listAumno);
		*/
	}

	@Override
	public ArrayList<Titulaciones> recogerDatosTitulacion() {
		listTitulacion = new ArrayList<Titulaciones>();
		fTitulaciones = new File("bbdd/Titulaciones.txt");

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fTitulaciones));
			String text = null;
			Titulaciones titulacion = null;

			while ((text = reader.readLine()) != null) {

				String[] datosaux = text.split(",");

				titulacion = new Titulaciones(Integer.parseInt(datosaux[0]),datosaux[1],datosaux[2]);
				listTitulacion.add(titulacion);
				nTitulaciones = Integer.parseInt(datosaux[0]);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listTitulacion;
	}

	@Override
	public void insertarTitulacion(Titulaciones titulacion) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("bbdd/Titulaciones.txt",true);
			pw = new PrintWriter(fichero);

				pw.println((nTitulaciones+1) + "," + titulacion.getNombre().toLowerCase() +","+titulacion.getDescripcion().toLowerCase());
				nTitulaciones++;
				System.out.println("Numero Titulaciones: "+nTitulaciones);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (fichero != null) {
				pw.close();
				fichero.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void copiarTodosTitulacion(ArrayList<Titulaciones> arrLista) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("bbdd/Titulaciones.txt");
			pw = new PrintWriter(fichero);

			for (int i = 0; i < arrLista.size(); i++) {

				pw.println((i + 1) + "," + arrLista.get(i).getCod() + "," + arrLista.get(i).getNombre().toLowerCase() + ","
						+ arrLista.get(i).getDescripcion().toLowerCase());

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (fichero != null) {
				pw.close();
				fichero.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void borrarUnoTitulacion(int cod) {
		System.out.println("size----" + listTitulacion.size());
		for (int i = 0; i < listTitulacion.size(); i++) {
			if(listTitulacion.get(i).getCod()==cod){
				listTitulacion.remove(i);
			}
		}
		copiarTodosTitulacion(listTitulacion);
		
	}

	@Override
	public void borrarTodoTitulacion() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fTitulaciones);
			writer.print("");
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizarTitulacion(Titulaciones titulacion) {
		// borrarUno(cod);
		// insertar(alumno);
		borrarUnoTitulacion(titulacion.getCod());
		insertarTitulacion(titulacion);
		
	}

	



	

}
