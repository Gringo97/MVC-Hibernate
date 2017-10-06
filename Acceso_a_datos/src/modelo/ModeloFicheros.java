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

				pw.println((i + 1) + "," + arrLista.get(i).getDni() + "," + arrLista.get(i).getNombre() + ","
						+ arrLista.get(i).getApellido() + "," + arrLista.get(i).getTelefono() + ","
						+ arrLista.get(i).getNacionalidad());

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
		// TODO Auto-generated method stub

	}

	@Override
	public void borrar() {
		// TODO Auto-generated method stub

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
		
	}


	

}
