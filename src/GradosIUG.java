import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 


import java.util.StringTokenizer;

public class GradosIUG extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfClave,tfGrado, tfDescripcion;
	private JButton    bRegistrar, bConsultar, bConsultarClave;
	private JTextArea  taDatos;
	public  JPanel 	   p1, p2;
	
	private GradosAD grados = new GradosAD(); 

	public GradosIUG(){
		super("Grados Académicos");
		
		//Inicializar los atributos
		tfClave 		= new JTextField();
		tfGrado   		= new JTextField();
		tfDescripcion	= new JTextField();
		taDatos    		= new JTextArea(18, 45);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(5, 2));
		
		p1.add(new JLabel("Clave del Profesor"));
		p1.add(tfClave);

		p1.add(new JLabel("Grado Académico"));
		p1.add(tfGrado);
		
		p1.add(new JLabel("Descripción del Grado Académico"));
		p1.add(tfDescripcion);

		bRegistrar = new JButton("Registrar Grado Académico");
		bRegistrar.addActionListener(this);
		p1.add(bRegistrar);
		
		bConsultar = new JButton("Consultar Grados");
		bConsultar.addActionListener(this);
		p1.add(bConsultar);
		
		bConsultarClave = new JButton("Consultar Grados por Profesor");
		bConsultarClave.addActionListener(this);
		p1.add(bConsultarClave);
				
		p2.setLayout(new FlowLayout());
		
		p2.add(p1);
		p2.add(new JScrollPane(taDatos));
		
		add(p2);
		/* setSize(520,350);
		setVisible(true); */
	}
	
	public JPanel getPanel2()
	{
		return this.p2;
	}
	
	public void clearFields(){
		tfClave.setText("");
		tfGrado.setText("");
		tfDescripcion.setText("");
	}
	
	public void habilitarBotones(boolean value){
		bRegistrar.setEnabled(value); 
		bConsultar.setEnabled(value);
		bConsultarClave.setEnabled(value);
				
		tfClave.setEnabled(value);
		tfGrado.setEnabled(value);
		tfDescripcion.setEnabled(value);
	}
	
	public void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
					
		String clave = st.nextToken();
		String grado = st.nextToken();
		String descripcion	= st.nextToken();
					
		tfClave.setText(clave);
		tfGrado.setText(grado);
		tfDescripcion.setText(descripcion);
	}
		
	public boolean notTokenizer(String str){
		Character array[] = new Character[str.length()];
		int i = 0;
		boolean token = false;
		
		//1) Transformar el String en un arreglo de caracteres
	    for(i = 0; i < str.length(); i++) 
      		array[i] = new Character(str.charAt(i));
      		
      	//2) Verificar que no existan tokens en el string, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
      	i = 0; // Reinicializar contador
      	while((i < str.length())&&(token == false))
      	{
      		if(array[i] == '_')
      			token = true;
      		i++;
      	}
      	
      	return token;
	}
	
	private String obtenerDatos(){
		boolean token = false;
		
		String clave      = tfClave.getText();
		String grado     = tfGrado.getText();
        String descripcion	   = tfDescripcion.getText();
        String datos = "";
		
		if(clave.equals("")||grado.equals("")||descripcion.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
			token = notTokenizer(clave); //Clave
			if(token == false)
			{
				token = notTokenizer(grado); //Grado
				if(token == false)
					token = notTokenizer(descripcion); // Descripción
		    }
		         if(token == false)
		        	datos = clave+"_"+grado+"_"+descripcion;
		        else
		        	datos = "TOKEN";
        }
    
        return datos;
	}

	private String consultar(String elemento){
		String resultado = "";
	
		if(elemento.equals("PROFESOR")){
			String clave = tfClave.getText();

			if(clave.equals(""))
					resultado = "PROFESOR_VACIO";
			else
				resultado = grados.consultarPor("PROFESOR", clave);
		}

		return resultado;
	}
	
	private void print(String str){
		
		if((str.equals("PROFESOR_NO_ENCONTRADO"))||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("PROFESOR_VACIO")) || (str.equals("GRADO_DUPLICADO")) || (str.equals("PROFESOR_NO_REGISTRADO")) ||(str.equals("DATOS_GRANDES")))
		{	
			if(str.equals("PROFESOR_NO_ENCONTRADO"))
				taDatos.setText("La clave de profesor '" + tfClave.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("PROFESOR_VACIO"))
				taDatos.setText("El campo 'Clave del Profesor' se encuentra vacío.");
			
			if(str.equals("GRADO_DUPLICADO"))
				taDatos.setText("El grado académico '" + tfGrado.getText() + "' para el profesor '" + tfClave.getText() + "' ya se encuentra registrado. \nPor favor introduce datos válidos distintos.");
				
			if(str.equals("PROFESOR_NO_REGISTRADO"))
				taDatos.setText("El profesor con clave '" + tfClave.getText() + "' no se encuentra en la base de datos. \nPor favor introduce un Profesor con una clave válida.");
				
			if(str.equals("DATOS_GRANDES"))
				taDatos.setText("Algún campo contiene información con demasiados caracteres. \nPor favor revisa de nuevo la información y realiza los cambios donde sean necesarios.");
				

		}
		else
			taDatos.setText(str);
		
		if(str.equals("BD_VACIA"))
			taDatos.setText("No se ha registrado ningún artículo hasta el momento.");
			
	}
	
	
	/********************************************************************  ACTION PERFORMED  ***************************************************************************************************/  
		/*******************************************************************************************************************************************************************************/ 
			
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bRegistrar)
		{
			//1) Obtener datos de los TextFields
            String datos = obtenerDatos();
            String resultado = "";
            
            //2) Comprobar que todos los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado
			if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN"))
				print(datos);
			else
			{
				//3) Enviar los datos a la clase AD a través del metodo registrarGrado()
			    resultado = grados.registrarGrado(datos);
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    if(!resultado.equals("GRADO_DUPLICADO")&& !resultado.equals("PROFESOR_NO_REGISTRADO")&& !resultado.equals("DATOS_GRANDES"))
			    	//5) Quitar la información de los TextFields
			    	clearFields();	
			}
		}
		
		if (e.getSource() == bConsultar){	
			String datos = grados.consultarGrados();
			print(datos);
		}

		if (e.getSource() == bConsultarClave){
			String resultado = consultar("PROFESOR");
			print(resultado);
		}
				
	}

	public static void main(String args[]){
		new GradosIUG();
	}
}
