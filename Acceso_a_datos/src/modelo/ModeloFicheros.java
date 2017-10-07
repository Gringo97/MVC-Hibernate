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

public class ModeloFicheros extends ModeloPrincipal implements Acceso_a_datos {

	ArrayList<Alumnos> list;
	File file;
	private int nAlumnos = 0;

	@Override
	public ArrayList<Alumnos> recogerDatos() {
		list = new ArrayList<Alumnos>();
		file = new File("conf/datos.txt");

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			Alumnos alumno = null;

			while ((text = reader.readLine()) != null) {

				String[] datosaux = text.split(",");

				alumno = new Alumnos(Integer.parseInt(datosaux[0]), datosaux[1], datosaux[2], datosaux[3],
						Integer.parseInt(datosaux[4]), datosaux[5]);
				list.add(alumno);
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

		return list;

	}

	@Override
	public void copiarTodos(ArrayList<Alumnos> arrLista) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("conf/datos.txt");
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
			fichero = new FileWriter("conf/datos.txt",true);
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
		System.out.println("size----" + list.size());
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getCod()==cod){
				list.remove(i);
			}
		}
		copiarTodos(list);
	}

	@Override
	public void borrarTodo() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void actualizar(String dni,String nombre,String apellidos, int telefono,String nacionalidad) {
		/*
		 * borrarUno(cod);
		 * insertar(alumno);
		 * el alumno de insertar debe ser el actualizar
		 * Creo que teniendo los metodos borarUno() e insertar() podemos reutilizarlos
		 */
		
	}


	

}
