package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import entidades.Alumnos;
import vista.VistaPrincipal;

public class ModeloSQL extends ModeloPrincipal implements Acceso_a_datos{
		String url;
		String usuario;
		String clave;
		
		
	
	
	 public ModeloSQL() {
		
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
			File config = new File("conf/configuracion.ini");
			if (config.exists()) {
				entrada = new FileInputStream(config);

				propiedades.load(entrada);

				 url = propiedades.getProperty("BaseDatos");
				usuario = propiedades.getProperty("Usuario");
				clave = propiedades.getProperty("Clave");
			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	 

		
	 public Connection getConnection() {
			Connection con;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, usuario, clave);
				
				return con;

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"No se pudo conectar con la base de datos, modifique la informacion en la configuracion");
				return null;
				// TODO: handle exception
			}
		}
	 
	 

	@Override
	public ArrayList<Alumnos> recogerDatos() {
		
		ArrayList<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
		Connection connection = getConnection();

		String query = "SELECT * from alumnos";
		Statement st;
		ResultSet rs;
		
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Alumnos alumno;
			while (rs.next()) {
				alumno = new Alumnos(rs.getInt("cod"), rs.getString("DNI"), rs.getString("NOMBRE"),
						rs.getString("APELLIDO"), rs.getInt("TELEFONO"), rs.getString("NACIONALIDAD"));
				arrAlumnos.add(alumno);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return arrAlumnos;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertar(Alumnos alumno) {
		Connection con = getConnection();
		PreparedStatement ps;

		String query = "INSERT INTO `alumnos` (`dni`, `nombre`, `apellido`, `telefono`, `nacionalidad`) VALUES (?,?,?,?,?)";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, alumno.getDni().toLowerCase());
			ps.setString(2, alumno.getNombre().toLowerCase());
			ps.setString(3, alumno.getApellido().toLowerCase());
			ps.setInt(4, alumno.getTelefono());
			ps.setString(5, alumno.getNacionalidad().toLowerCase());

			if (ps.executeUpdate() == 1) {

				JOptionPane.showMessageDialog(null, "Informaci�n almacenada satisfactoriamente");
		
			} else {
				JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser almacenada");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
	}

	@Override
	public void borrarUno(int cod) {
		Connection con = getConnection();
		PreparedStatement ps;

		String query = "DELETE FROM `alumnos` WHERE cod=" + cod;
		try {

			ps = con.prepareStatement(query);
			if (ps.executeUpdate() == 1) {

				JOptionPane.showMessageDialog(null, "Informaci�n borrada satisfactoriamente");
		
			} else {
				JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser borrada");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
	}

	@Override
	public void borrarTodo() {
		Connection con = getConnection();
		PreparedStatement ps;

		String query = "DELETE FROM `alumnos` ";
		try {
			ps = con.prepareStatement(query);

			if (ps.executeUpdate() == 1) {

			

				JOptionPane.showMessageDialog(null, "Informaci�n borrada satisfactoriamente");
			} else {
				JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser borrada");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
	}

	


	@Override
	public void  copiarTodos(ArrayList<Alumnos> arrLista){
		for (int i = 0; i < arrLista.size(); i++) {
			this.insertar(arrLista.get(i));

		}
		
		
		
	}
	@Override
	public void actualizar(String dni,String nombre,String apellidos, int telefono,String nacionalidad) {
		Connection con = getConnection();
		int r = 0;

		String query = "UPDATE alumnos SET NOMBRE = ?, APELLIDO = ?,  TELEFONO = ?, NACIONALIDAD = ? WHERE alumnos.DNI = '"+ dni+"'";
		
		
		PreparedStatement pstmt;
		int last_inserted_id = -1;

		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nombre.toLowerCase());
			pstmt.setString(2, apellidos.toLowerCase());
			pstmt.setInt(3, telefono);
			pstmt.setString(4, nacionalidad.toLowerCase());

			
			r = pstmt.executeUpdate();
			

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}



	


}
