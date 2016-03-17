import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.StringTokenizer;

public class ProfesoresGUI extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfNombre, tfSalario, tfDomicilio,tfFNacimiento, tfTrabajaD, tfClave;
	private JButton    bRegistrar, bConsultar, bConsultarClave, bConsultarDepto, bConsultarSexo;
	private JTextArea  taDatos;
	public JPanel      p1,p2;
	
	private JComboBox<String> combo;
	private String opciones[] = {"M", "F"};

	private ProfesoresAD profesor = new ProfesoresAD();

	public ProfesoresGUI(){
		super("Profesor");

		//Inicializar los atributos
		tfNombre       = new JTextField();
		tfSalario      = new JTextField();
		tfDomicilio    = new JTextField();
		tfFNacimiento  = new JTextField();
		tfTrabajaD     = new JTextField();
		tfClave        = new JTextField();
		taDatos		   = new JTextArea(10, 58);
		p1			   = new JPanel();
		p2             = new JPanel();

		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(10,2));
		
		p1.add(new JLabel("Clave del Profesor"));
		p1.add(tfClave);

		p1.add(new JLabel("Nombre"));
		p1.add(tfNombre);
		
		p1.add(new JLabel("Domicilio"));
		p1.add(tfDomicilio);

		p1.add(new JLabel("Salario"));
		p1.add(tfSalario);

		p1.add(new JLabel("Fecha de Nacimiento"));
		p1.add(tfFNacimiento);
		
		// JComboBox
		combo = new JComboBox<String>(opciones);
		combo.addActionListener(this);
		
		p1.add(new JLabel("Sexo"));
		p1.add(combo);

		p1.add(new JLabel("Número de Departamento"));
		p1.add(tfTrabajaD);
		
		bRegistrar = new JButton("Registrar Profesor");
		bRegistrar.addActionListener(this);
		p1.add(bRegistrar);

		bConsultar = new JButton("Consultar Profesores");
		bConsultar.addActionListener(this);
		p1.add(bConsultar);
		
		bConsultarClave = new JButton("Consultar Profesores por Clave");
		bConsultarClave.addActionListener(this);
		p1.add(bConsultarClave);
		
		bConsultarDepto = new JButton("Consultar Profesores por Departamento");
		bConsultarDepto.addActionListener(this);
		p1.add(bConsultarDepto);
						
		bConsultarSexo = new JButton("Consultar Profesores por Sexo");
		bConsultarSexo.addActionListener(this);
		p1.add(bConsultarSexo);
		
		p2.setLayout(new FlowLayout());

		p2.add(p1);
		p2.add(new JScrollPane(taDatos));

		add(p2);
	}

	public JPanel getPanel2()
	{
		return this.p2;
	}

	public void clearFields(){
		tfNombre.setText("");
		tfSalario.setText("");
		tfDomicilio.setText("");
		tfFNacimiento.setText("");
		tfTrabajaD.setText("");
		tfClave.setText("");
	}

	public void habilitarBotones(boolean value){
		bRegistrar.setEnabled(value); 
		bConsultar.setEnabled(value);
	}

	private void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
		
		String clave             = st.nextToken();			
		String nombre            = st.nextToken();
		String domicilio         = st.nextToken();
		String salario	         = st.nextToken();
		String fnacimiento       = st.nextToken();
		String sexo				 = st.nextToken();
		String clavedepartamento = st.nextToken();
		int genero = 0;
					
		tfNombre.setText(nombre);
		tfSalario.setText(salario);
		tfDomicilio.setText(domicilio);
		tfFNacimiento.setText(fnacimiento);
		tfTrabajaD.setText(clavedepartamento);
		tfClave.setText(clave);
		
		if(sexo.equals("M"))
			genero = 0;
				
		if(sexo.equals("F"))
			genero = 1;
									
		combo.setSelectedIndex(genero);
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
		int nsalario;
		int ndepto;
		
		String clave               = tfClave.getText();
		String nombre              = tfNombre.getText();
        String domicilio           = tfDomicilio.getText();
        String salario        	   = tfSalario.getText();
        String sexo 			   = combo.getSelectedItem().toString();
        String fnacimiento         = tfFNacimiento.getText();
        String clavedepartamento   = tfTrabajaD.getText();
        
        String datos = "";
		
		if(nombre.equals("")|| salario.equals("")||domicilio.equals("")||fnacimiento.equals("")||clavedepartamento.equals("")||clave.equals("")||sexo.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	try
        	{
        		// Comprobar que los campos de "Salario" y de "Clave de Departamento" sea numérico
        		nsalario = Integer.parseInt(salario);
        		ndepto = Integer.parseInt(clavedepartamento);
        		
        		// Comprobar que el campo de "Salario" sea positivo
        		if(nsalario < 0 || ndepto < 0)
        			datos = "NEGATIVO";	
        		else
        		{
        			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
				     token = notTokenizer(clave); // Clave
				     if(token == false)
				     {
				     	token = notTokenizer(nombre); // Nombre
				     	if(token == false){
					     	token = notTokenizer(domicilio); // Domicilio
					     	if (token == false){
					     		token = notTokenizer(sexo); // Sexo
					     		if (token == false)
					     		token = notTokenizer(fnacimiento); // Fecha de Nacimiento
					     	}
					    }
		        	 }
		        	 
		         	 if(token == false)
		        		 datos = clave + "_" + nombre+"_" + domicilio + "_" + salario +"_" + fnacimiento + "_" + sexo + "_" + clavedepartamento;
		       	     else
		        		 datos = "TOKEN";
	        	}
        	}
        	catch(NumberFormatException nfe)
        	{
        		System.out.println("Error: " + nfe);
        		datos = "NO_NUMERICO";
        	}
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
				resultado = profesor.consultarPor("PROFESOR", clave);
		}
		
		if(elemento.equals("DEPARTAMENTO")){
			String ndepto = tfTrabajaD.getText();

			if(ndepto.equals(""))
					resultado = "DEPARTAMENTO_VACIO";
			else
				resultado = profesor.consultarPor("DEPARTAMENTO", ndepto);
		}
		
		if(elemento.equals("SEXO")){
			String sexo = combo.getSelectedItem().toString();
			resultado = profesor.consultarPor("SEXO", sexo);
		}

		return resultado;
	}
		
	private void print(String str){
		
		if((str.equals("PROFESOR_NO_ENCONTRADO"))||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("PROFESOR_VACIO")) || (str.equals("PROFESOR_DUPLICADO")) || (str.equals("DEPARTAMENTO_NO_REGISTRADO"))|| (str.equals("ERROR")) || (str.equals("DEPARTAMENTO_VACIO")) || (str.equals("SEXO_NO_REGISTRADO"))  ||(str.equals("DATOS_GRANDES"))  ||(str.equals("NO_NUMERICO"))  ||(str.equals("NEGATIVO")))
		{	
			if(str.equals("PROFESOR_NO_ENCONTRADO"))
				taDatos.setText("La clave de profesor '" + tfClave.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("PROFESOR_VACIO"))
				taDatos.setText("El campo 'Clave del Profesor' se encuentra vacío.");
						
			if(str.equals("DEPARTAMENTO_VACIO"))
				taDatos.setText("El campo 'Número de Departamento' se encuentra vacío.");
			
			if(str.equals("PROFESOR_DUPLICADO"))
				taDatos.setText("El profesor con clave '" + tfClave.getText() + "' ya se encuentra registrado. \nPor favor introduce una clave válida distinta.");
				
			if(str.equals("DEPARTAMENTO_NO_REGISTRADO"))
				taDatos.setText("El departamento con número '" + tfTrabajaD.getText() + "' no se encuentra en la base de datos. \nPor favor introduce un Departamento válido."); 
				
			if (str.equals("ERROR"))
				taDatos.setText("No se pudo realizar la consulta.");

			if (str.equals("SEXO_NO_REGISTRADO"))
				taDatos.setText("No existen registros de profesores con sexo '" + combo.getSelectedItem().toString() + "' no existen en la base de datos.");
				
							
			if(str.equals("NEGATIVO") || str.equals("NO_NUMERICO"))
				taDatos.setText("Los campos 'Salario' y 'Número de Departamento' deben contener un número válido entero positivo.\nPor favor introduce nuevos datos válidos.");	
											
							
			if(str.equals("DATOS_GRANDES"))
				taDatos.setText("Algún campo contiene información con demasiados caracteres. \nPor favor revisa de nuevo la información y realiza los cambios donde sean necesarios.");

		}
		else
			taDatos.setText(str);
		
		if(str.equals("BD_VACIA"))
			taDatos.setText("No se ha registrado ningún profesor hasta el momento.");
			
	}

		/*************  ACTION PERFORMED  *************/ 

		public void actionPerformed(ActionEvent e){
			
			if (e.getSource() == bRegistrar){
				//1) Obtener datos de los TextFields
		        String datos = obtenerDatos();
		        String resultado = "";
		        
		        //2) Comprobar que todos los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado
				if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NEGATIVO")||datos.equals("NO_NUMERICO"))
					print(datos);
				else {
					taDatos.setText(datos);
					//3) Enviar los datos a la clase AD a través del metodo registrarGrado()
					resultado = profesor.registrarProfesor(datos);
	
					//4) Desplegar el resultado de la operación
					print(resultado);
					
					if(!resultado.equals("PROFESOR_DUPLICADO") && !resultado.equals("DEPARTAMENTO_NO_REGISTRADO") && !resultado.equals("DATOS_GRANDES"))
						//5) Quitar la información de los TextFields
						clearFields();	
				}
			}
			
					
			if (e.getSource() == bConsultar){	
				String datos = profesor.consultarProfesores();
				print(datos);
			}
	
			
			if (e.getSource() == bConsultarClave){
				String resultado = consultar("PROFESOR");
				if(resultado.equals("PROFESOR_VACIO")||(resultado.equals("ERROR"))||(resultado.equals("PROFESOR_NO_ENCONTRADO")))
					print(resultado);
				else {
					//Colocar los datos en los TextFields
					mostrar(resultado);	
					print(resultado);
				}		
			}
								
			if (e.getSource() == bConsultarDepto){	
				String resultado = consultar("DEPARTAMENTO");
				print(resultado);
			}
			
			if (e.getSource() == bConsultarSexo){	
				String resultado = consultar("SEXO");
				print(resultado);
			}

		}

}
