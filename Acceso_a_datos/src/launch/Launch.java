package launch;

import javax.swing.JOptionPane;

import controlador.Controlador;
import modelo.ModeloFicheros;
import modelo.ModeloPrincipal;
import modelo.ModeloSQL;
import vista.VistaPrincipal;

public class Launch {
  
	public static void main(String[] args) {
		VistaPrincipal vistaPrincipal = new VistaPrincipal();
		ModeloPrincipal modeloPrincipal = new ModeloPrincipal();
		Controlador controlador = new Controlador();
		
		
		
		
		
		
		//presentamos al controlador tanto al modelo principal como a la vista
		controlador.setModeloPrincipal(modeloPrincipal);
		controlador.setVistaPrincipal(vistaPrincipal);

		//presentamos a la vista tanto al controlador como al modeloPrincipal
		vistaPrincipal.setControlador(controlador);
		vistaPrincipal.setModeloPrincipal(modeloPrincipal);
		
		
		modeloPrincipal.setVistaPrincipal(vistaPrincipal);
		
	
		 modeloPrincipal.tipoAcceso(controlador.tipoAcceso());
		 
		
		
		 controlador.crearTabla();
		
		vistaPrincipal.setVisible(true);
		
		
		
		
	}

	
	
}