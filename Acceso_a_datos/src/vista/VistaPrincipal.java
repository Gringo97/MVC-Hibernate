package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controlador.Controlador;

import modelo.ModeloPrincipal;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;
	private ModeloPrincipal modeloPrincipal;
	private JTable table;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNacionalidad;
	private JTextField txtNombreMod;
	private JTextField txtApellidoMod;
	private JTextField txtTelefonoMod;
	private JTextField txtNacionalidadMod;
	
	private TableRowSorter trOrden;
	private JTextField jtxtBuscarDni;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblAadirAlumno = new JLabel("A\u00F1adir Alumno");
		lblAadirAlumno.setFont(new Font("Tahoma", Font.BOLD, 15));

		txtDni = new JTextField();
		TextPrompt placeholder = new TextPrompt("DNI", txtDni);
		txtDni.setColumns(10);
		placeholder.changeAlpha(0.50f);
		placeholder.changeStyle(Font.ITALIC);

		txtNombre = new JTextField();
		TextPrompt placeholder2 = new TextPrompt("Nombre", txtNombre);
		txtNombre.setColumns(10);
		placeholder2.changeAlpha(0.50f);
		placeholder2.changeStyle(Font.ITALIC);

		txtApellido = new JTextField();
		TextPrompt placeholder3 = new TextPrompt("Apellido", txtApellido);
		txtApellido.setColumns(10);
		placeholder3.changeAlpha(0.50f);
		placeholder3.changeStyle(Font.ITALIC);

		txtTelefono = new JTextField();
		TextPrompt placeholder4 = new TextPrompt("Telefono", txtTelefono);
		txtTelefono.setColumns(10);
		placeholder4.changeAlpha(0.50f);
		placeholder4.changeStyle(Font.ITALIC);

		txtNacionalidad = new JTextField();
		TextPrompt placeholder5 = new TextPrompt("Nacionalidad", txtNacionalidad);
		txtNacionalidad.setColumns(10);
		placeholder5.changeAlpha(0.50f);
		placeholder5.changeStyle(Font.ITALIC);

		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controlador.nuevoAlumno();
			}
		});

		JLabel lblModificarDatos = new JLabel("Modificar Datos");
		lblModificarDatos.setFont(new Font("Tahoma", Font.BOLD, 15));

		txtNombreMod = new JTextField();
		txtNombreMod.setColumns(10);

		txtApellidoMod = new JTextField();
		txtApellidoMod.setColumns(10);

		txtTelefonoMod = new JTextField();
		txtTelefonoMod.setColumns(10);

		txtNacionalidadMod = new JTextField();
		txtNacionalidadMod.setColumns(10);

		JButton btnModificar = new JButton("Modificar");

		JSeparator separator = new JSeparator();

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//controlador.eliminarPersona();
			}
		});
		
		JButton btnNewButton = new JButton("Guardar en Fichero");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controlador.guardaTabla();
			}
		});
		
		JButton btnEliminarTodos = new JButton("Eliminar Todos");
		btnEliminarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//controlador.eliminarTodos();
			}
		});
		
		
		
		
		
		jtxtBuscarDni = new JTextField();
		jtxtBuscarDni.setColumns(10);
		jtxtBuscarDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				jtxtBuscarDni.addKeyListener(new KeyAdapter() {
					public void keyReleased(final KeyEvent arg0) {

						filtroDni();

					}

					public void filtroDni() {
		trOrden.setRowFilter(RowFilter.regexFilter(jtxtBuscarDni.getText(), 0));

	}
				});
				trOrden = new TableRowSorter(table.getModel());
				table.setRowSorter(trOrden);
			}
		});
		
		JLabel lblBuscadorDni = new JLabel("Buscador DNI:");
		
		JButton btnSubirFicher = new JButton("Exportar a");
		btnSubirFicher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoReceptor  = controlador.tipoAcceso();
				controlador.tipoExportar(tipoReceptor);
			}
		});
		
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBuscadorDni)
							.addGap(24)
							.addComponent(jtxtBuscarDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnSubirFicher))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblAadirAlumno))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAadir, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
								.addComponent(txtDni, 242, 242, Short.MAX_VALUE)
								.addComponent(txtNombre, 242, 242, Short.MAX_VALUE)
								.addComponent(txtApellido, 242, 242, Short.MAX_VALUE)
								.addComponent(txtTelefono, 242, 242, Short.MAX_VALUE)
								.addComponent(txtNacionalidad, 242, 242, Short.MAX_VALUE)
								.addComponent(lblModificarDatos, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNombreMod, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtApellidoMod, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefonoMod, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtNacionalidadMod, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
									.addComponent(btnEliminar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
									.addComponent(btnEliminarTodos, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAadirAlumno)
							.addGap(2)
							.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtNacionalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(btnAadir)
							.addGap(15)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(lblModificarDatos, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(txtNombreMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtApellidoMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtTelefonoMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtNacionalidadMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(btnModificar)
							.addGap(27)
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnEliminarTodos)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(jtxtBuscarDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBuscadorDni)
						.addComponent(btnSubirFicher))
					.addGap(12))
		);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//controlador.mostrarDatos();
			}
		});
		
	
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	


	public void onLoadTable() {
		//controlador.MostrarTabla();
	}

	public TableModel getTablaInfo() {
		return table.getModel();
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloPrincipal(ModeloPrincipal modeloPrincipal) {
		this.modeloPrincipal = modeloPrincipal;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public String getTxtDni() {
		return txtDni.getText();
	}
	
	public String getTxtDniMod() {
		return txtDni.getText();
	}

	public String getTxtNombre() {
		return txtNombre.getText();
	}

	public String getTxtApellido() {
		return txtApellido.getText();
	}

	public String getTxtTelefono() {
		return txtTelefono.getText();
	}

	public String getTxtNacionalidad() {
		return txtNacionalidad.getText();
	}

	public JTextField getTxtNombreMod() {
		return txtNombreMod;
	}

	public void setTxtNombreMod(JTextField txtNombreMod) {
		this.txtNombreMod = txtNombreMod;
	}

	public JTextField getTxtApellidoMod() {
		return txtApellidoMod;
	}

	public void setTxtApellidoMod(JTextField txtApellidoMod) {
		this.txtApellidoMod = txtApellidoMod;
	}

	public JTextField getTxtTelefonoMod() {
		return txtTelefonoMod;
	}

	public void setTxtTelefonoMod(JTextField txtTelefonoMod) {
		this.txtTelefonoMod = txtTelefonoMod;
	}

	public JTextField getTxtNacionalidadMod() {
		return txtNacionalidadMod;
	}

	public void setTxtNacionalidadMod(JTextField txtNacionalidadMod) {
		this.txtNacionalidadMod = txtNacionalidadMod;
	}



	

}
