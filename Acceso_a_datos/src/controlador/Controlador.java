package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import entidades.Alumnos;
import modelo.ModeloPrincipal;
import vista.VistaPrincipal;

public class Controlador {

	private ModeloPrincipal modeloPrincipal;
	private VistaPrincipal vistaPrincipal;


	public String tipoAcceso() {
		String resp;
		String[] tipos = { "SQL", "Ficheros", };

		return resp = (String) JOptionPane.showInputDialog(null, "Seleccione tipo de acceso a datos", "Tipo",
				JOptionPane.DEFAULT_OPTION, null, tipos, tipos[0]);

	}

	public ModeloPrincipal getModeloPrincipal() {
		return modeloPrincipal;
	}

	public void setModeloPrincipal(ModeloPrincipal modeloPrincipal) {
		this.modeloPrincipal = modeloPrincipal;
	}

	public VistaPrincipal getVistaPrincipal() {
		return vistaPrincipal;
	}

	public void setVistaPrincipal(VistaPrincipal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}
	
	public void tipoExportar(String tipoReceptor){
		modeloPrincipal.tipoExportar(tipoReceptor);
		
		modeloPrincipal.copiarTodos();
	}
	
	
	public void crearTabla() {
		ArrayList<Alumnos> arrAlumnos = modeloPrincipal.recogerDatos();

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		model.addColumn("Cod");
		model.addColumn("Dni");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Telefono");
		model.addColumn("Nacionalidad");
		for (int i = 0; i < arrAlumnos.size(); i++) {
			model.addRow(new String[] { String.valueOf(arrAlumnos.get(i).getCod()), arrAlumnos.get(i).getDni(),
					arrAlumnos.get(i).getNombre(), arrAlumnos.get(i).getApellido(),
					String.valueOf(arrAlumnos.get(i).getTelefono()), arrAlumnos.get(i).getNacionalidad() });
			
			this.vistaPrincipal.getTable().setModel(model);
		}
	}

	public void subirFichero() {
		
		
	}
	
	public void actualizarAlumno(){
		String dato=String.valueOf(vistaPrincipal.getTabla().getValueAt(vistaPrincipal.getTabla().getSelectedRow(),1));
		modeloPrincipal.actualizar(dato,vistaPrincipal.getTxtNombreMod(),vistaPrincipal.getTxtApellidoMod(),Integer.parseInt(vistaPrincipal.getTxtTelefonoMod()),vistaPrincipal.getTxtNacionalidadMod());
		
	}
	
	

	public void nuevoAlumno() {
		Alumnos alumno;
		alumno = new Alumnos(vistaPrincipal.getTxtDni(),vistaPrincipal.getTxtNombre(),vistaPrincipal.getTxtApellido(),Integer.parseInt(vistaPrincipal.getTxtTelefono()),vistaPrincipal.getTxtNacionalidad());
		
		modeloPrincipal.anadirAlumno(alumno);
	}

	public void eliminarTodos() {
		modeloPrincipal.borrarTodos();
		
	}
	
	public void eliminarUno() {
		int cod = Integer.parseInt((String) vistaPrincipal.getTabla().getValueAt(vistaPrincipal.getTabla().getSelectedRow(),1));
		modeloPrincipal.borrarUno(cod);
		
	}

	
}
