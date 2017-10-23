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
import entidades.Titulaciones;
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
						rs.getString("APELLIDO"), rs.getInt("TELEFONO"), rs.getString("NACIONALIDAD"),rs.getInt("Titulacion"));
				arrAlumnos.add(alumno);
			}
			connection.close();
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

		String query = "INSERT INTO `alumnos` (`dni`, `nombre`, `apellido`, `telefono`, `nacionalidad`, `titulacion`) VALUES (?,?,?,?,?.?)";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, alumno.getDni().toLowerCase());
			ps.setString(2, alumno.getNombre().toLowerCase());
			ps.setString(3, alumno.getApellido().toLowerCase());
			ps.setInt(4, alumno.getTelefono());
			ps.setString(5, alumno.getNacionalidad().toLowerCase());
			ps.setInt(6, alumno.getTitulacion());

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
	public void actualizar(Alumnos alumno) {
		Connection con = getConnection();
		int r = 0;

		String query = "UPDATE alumnos SET NOMBRE = ?, APELLIDO = ?,  TELEFONO = ?, NACIONALIDAD = ?, TITULACION = ? WHERE alumnos.DNI = '"+ alumno.getDni()+"'";
		
		
		PreparedStatement pstmt;
		int last_inserted_id = -1;

		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, alumno.getNombre().toLowerCase());
			pstmt.setString(2, alumno.getApellido().toLowerCase());
			pstmt.setInt(3, alumno.getTelefono());
			pstmt.setString(4, alumno.getNacionalidad().toLowerCase());
			pstmt.setInt(5, alumno.getTitulacion());

			
			r = pstmt.executeUpdate();
			
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}



	@Override
	public ArrayList<Titulaciones> recogerDatosTitulacion() {
		ArrayList<Titulaciones> arrTitulaciones = new ArrayList<Titulaciones>();
		Connection connection = getConnection();

		String query = "SELECT * from titulaciones";
		Statement st;
		ResultSet rs;
		
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Titulaciones titulacion;
			while (rs.next()) {
				titulacion = new Titulaciones(rs.getInt("cod"),rs.getString("nombre"),rs.getString("descripcion"));
				arrTitulaciones.add(titulacion);
			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return arrTitulaciones;
		// TODO Auto-generated method stub
	}



	@Override
	public void insertarTitulacion(Titulaciones titulacion) {
		Connection con = getConnection();
		PreparedStatement ps;

		String query = "INSERT INTO `titulaciones` (`cod`, `nombre`, `descripcion`) VALUES (?,?,?)";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, titulacion.getCod());
			ps.setString(2, titulacion.getNombre().toLowerCase());
			ps.setString(3, titulacion.getDescripcion().toLowerCase());
			

			if (ps.executeUpdate() == 1) {

				JOptionPane.showMessageDialog(null, "Informaci�n almacenada satisfactoriamente");
		
			} else {
				JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser almacenada");
			}
			con.close();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public void copiarTodosTitulacion(ArrayList<Titulaciones> arrLista) {
		for (int i = 0; i < arrLista.size(); i++) {
			this.insertarTitulacion(arrLista.get(i));

		}
	}



	@Override
	public void borrarUnoTitulacion(int cod) {
		Connection con = getConnection();
		PreparedStatement ps;

		String query = "DELETE FROM `titulaciones` WHERE cod=" + cod;
		try {

			ps = con.prepareStatement(query);
			if (ps.executeUpdate() == 1) {

				JOptionPane.showMessageDialog(null, "Informaci�n borrada satisfactoriamente");
		
			} else {
				JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser borrada");
			}
			con.close();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
	}



	@Override
	public void borrarTodoTitulacion() {
		Connection con = getConnection();
		PreparedStatement ps;

		String query = "DELETE FROM `titulaciones` ";
		try {
			ps = con.prepareStatement(query);

			if (ps.executeUpdate() == 1) {

			

				JOptionPane.showMessageDialog(null, "Informaci�n borrada satisfactoriamente");
			} else {
				JOptionPane.showMessageDialog(null, "La informaci�n no pudo ser borrada");
			}
			con.close();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
	}



	@Override
	public void actualizarTitulacion(Titulaciones titulacion) {
		Connection con = getConnection();
		int r = 0;

		String query = "UPDATE titulaciones SET NOMBRE = ?, descripcion = ? WHERE titulaciones.cod = '"+ titulacion.getCod()+"'";
		
		
		PreparedStatement pstmt;
		int last_inserted_id = -1;

		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, titulacion.getNombre().toLowerCase());
			pstmt.setString(2, titulacion.getDescripcion().toLowerCase());

			
			r = pstmt.executeUpdate();
			
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
		
	}



}
