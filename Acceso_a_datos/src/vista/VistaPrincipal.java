package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;

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
import javax.swing.text.JTextComponent;
import javax.swing.JComboBox;

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
	private JTextField txtfCurso;
	private JTextField txtCursoMod;
	private JTextField txtfTitulacionNuevo;
	private JTextField txtfCursoNuevo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 714);
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
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.actualizarAlumno();
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.eliminarUno();
			}
		});
		
		JButton btnEliminarTodos = new JButton("Eliminar Todos");
		btnEliminarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.eliminarTodos();
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
		
		txtfCurso = new JTextField();
		txtfCurso.setText("Curso");
		txtfCurso.setColumns(10);
		
		txtCursoMod = new JTextField();
		txtCursoMod.setColumns(10);
		
		JLabel lblAadirCurso = new JLabel("A\u00F1adir Curso");
		lblAadirCurso.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblAadirTitulacion = new JLabel("A\u00F1adir Titulacion");
		lblAadirTitulacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JComboBox comboBoxTitulacion = new JComboBox();
		
		JLabel lblNewLabel = new JLabel("Titulacion");
		
		txtfTitulacionNuevo = new JTextField();
		txtfTitulacionNuevo.setColumns(10);
		
		JButton btnAñadirTitulacion = new JButton("A\u00F1adir titulacion");
		
		txtfCursoNuevo = new JTextField();
		txtfCursoNuevo.setText("Curso");
		txtfCursoNuevo.setColumns(10);
		
		JButton btnAñadirCurso = new JButton("A\u00F1adir curso");
		
		JSeparator separator_1 = new JSeparator();
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_2 = new JSeparator();
		
		JSeparator separator_3 = new JSeparator();
		
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAadirTitulacion, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtApellidoMod, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addComponent(txtNacionalidadMod, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addComponent(txtNombreMod, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addComponent(lblModificarDatos, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addComponent(txtTelefonoMod, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addComponent(txtCursoMod, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
										.addComponent(btnModificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(0)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblAadirCurso, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
											.addGap(66)
											.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBoxTitulacion, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
											.addGap(22))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtfCursoNuevo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
												.addComponent(btnAñadirCurso, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
												.addComponent(separator_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
											.addGap(10)))
									.addGap(0))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblAadirAlumno, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(txtDni, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(txtNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(txtApellido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(txtTelefono, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(txtNacionalidad, 242, 312, Short.MAX_VALUE)
										.addComponent(txtfCurso, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(btnAadir, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
									.addGap(8))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnAñadirTitulacion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
										.addComponent(txtfTitulacionNuevo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
									.addGap(12))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBuscadorDni, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtxtBuscarDni, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSubirFicher, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminarTodos, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
							.addGap(12))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
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
									.addComponent(txtfCurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(btnAadir)
									.addGap(9)
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
									.addGap(8)
									.addComponent(txtCursoMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(9)
									.addComponent(btnModificar)
									.addGap(7)
									.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblAadirCurso, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBoxTitulacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(16)
											.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(11)
									.addComponent(txtfCursoNuevo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAñadirCurso)
									.addGap(15)
									.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(lblAadirTitulacion, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addGap(36)
									.addComponent(btnAñadirTitulacion))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(583)
							.addComponent(txtfTitulacionNuevo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscadorDni)
						.addComponent(btnEliminarTodos)
						.addComponent(btnEliminar)
						.addComponent(btnSubirFicher)
						.addComponent(jtxtBuscarDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46))
		);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(final MouseEvent e) {
		       txtNombreMod.setText((String) table.getValueAt(table.getSelectedRow(), 2));
		       txtApellidoMod.setText((String) table.getValueAt(table.getSelectedRow(), 3));
		       txtTelefonoMod.setText((String) table.getValueAt(table.getSelectedRow(), 4));
		       txtNacionalidadMod.setText((String) table.getValueAt(table.getSelectedRow(), 5));
		       
		    }
		});
		
	
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	public JTable getTabla() {
		return table;
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

	public String getTxtNombreMod() {
		return txtNombreMod.getText();
	}

	public void setTxtNombreMod(JTextField txtNombreMod) {
		this.txtNombreMod = txtNombreMod;
	}

	public String getTxtApellidoMod() {
		return txtApellidoMod.getText();
	}

	public void setTxtApellidoMod(JTextField txtApellidoMod) {
		this.txtApellidoMod = txtApellidoMod;
	}

	public String getTxtTelefonoMod() {
		return txtTelefonoMod.getText();
	}

	public void setTxtTelefonoMod(JTextField txtTelefonoMod) {
		this.txtTelefonoMod = txtTelefonoMod;
	}

	public String getTxtNacionalidadMod() {
		return txtNacionalidadMod.getText();
	}

	public void setTxtNacionalidadMod(JTextField txtNacionalidadMod) {
		this.txtNacionalidadMod = txtNacionalidadMod;
	}



	public void crearTabla() {
		controlador.crearTabla();
		
	}
}
