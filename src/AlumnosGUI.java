import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.StringTokenizer;

public class AlumnosGUI extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfMatricula, tfNombre, tfDomicilio, tfTelefono, tfCarrera, tfPlan;
	private JButton    bRegistrar, bConsultar, bConsultarMatricula, bConsultarCarrera;
	private JTextArea  taDatos;
	public JPanel      p1,p2;

	private AlumnosAD alumnos = new AlumnosAD();

	public AlumnosGUI(){
		super("Alumnos");

		//Inicializar los atributos
		tfMatricula    = new JTextField();
		tfNombre       = new JTextField();
		tfDomicilio    = new JTextField();
		tfTelefono     = new JTextField();
		tfCarrera      = new JTextField();
		tfPlan         = new JTextField();
 		taDatos		   = new JTextArea(13, 48);
		p1			   = new JPanel();
		p2             = new JPanel();

		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(8,2));

		p1.add(new JLabel("Matricula"));
		p1.add(tfMatricula);

		p1.add(new JLabel("Nombre"));
		p1.add(tfNombre);

		p1.add(new JLabel("Domicilio"));
		p1.add(tfDomicilio);

		p1.add(new JLabel("Teléfono"));
		p1.add(tfTelefono);

		p1.add(new JLabel("Carrera"));
		p1.add(tfCarrera);

		p1.add(new JLabel("Plan"));
		p1.add(tfPlan);

		bRegistrar = new JButton("Registrar Alumno");
		bRegistrar.addActionListener(this);
		p1.add(bRegistrar);

		bConsultar = new JButton("Consultar Alumnos");
		bConsultar.addActionListener(this);
		p1.add(bConsultar);

		bConsultarMatricula = new JButton("Consultar Alumnos por Matrícula");
		bConsultarMatricula.addActionListener(this);
		p1.add(bConsultarMatricula);

		bConsultarCarrera = new JButton("Consultar Alumnos por Carrera");
		bConsultarCarrera.addActionListener(this);
		p1.add(bConsultarCarrera);
		
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
		tfMatricula.setText("");
		tfNombre.setText("");
		tfDomicilio.setText("");
		tfTelefono.setText("");
		tfCarrera.setText("");
		tfPlan.setText("");
	}

	public void habilitarBotones(boolean value){
		bRegistrar.setEnabled(value); 
		bConsultar.setEnabled(value);
	}

	private void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
					
		String matricula  = st.nextToken();
		String nombre     = st.nextToken();
		String domicilio  = st.nextToken();
		String telefono   = st.nextToken();
		String carrera    = st.nextToken();
		String plan       = st.nextToken();
					
		tfMatricula.setText(matricula);
		tfNombre.setText(nombre);
		tfDomicilio.setText(domicilio);
		tfTelefono.setText(telefono);
		tfCarrera.setText(carrera);
		tfPlan.setText(plan);
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
		int nplan;
				
        String matricula = tfMatricula.getText();
		String nombre    = tfNombre.getText();
        String domicilio = tfDomicilio.getText();
        String telefono  = tfTelefono.getText();
		String carrera   = tfCarrera.getText();
        String plan      = tfPlan.getText();
        String datos     = "";
		
		if(nombre.equals("")||carrera.equals("")||plan.equals("")||domicilio.equals("")||telefono.equals("")||matricula.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	try
        	{
        		// Comprobar que el campo "Plan" sea numérico
        		nplan = Integer.parseInt(plan);

        		//Comprobar que el campo "Plan" sea positivo
        		if(nplan <0)
        			datos = "NEGATIVO";
        		else
        		{
	    			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
				    token = notTokenizer(matricula); //Matricula
				    if(token == false)
				    {
				    	token = notTokenizer(nombre); //Nombre
				     	if(token == false){
					     	token = notTokenizer(domicilio); // Domicilio
					    	if(token == false){				    	
					     		token = notTokenizer(telefono); // Teléfono
					     		if(token == false){				     			
					     			token = notTokenizer(carrera); // Carrera
					     			if(token == false)
					     				token = notTokenizer(plan); // Plan
					     		}
					    	}
				     	}
			    	}
			    	
			   		if(token == false)
		    			datos = matricula+"_"+nombre+"_"+domicilio+"_"+telefono+"_"+carrera+"_"+plan;
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
	
		if(elemento.equals("ALUMNO")){
			String matricula = tfMatricula.getText();

			if(matricula.equals(""))
					resultado = "ALUMNO_VACIO";
			else
				resultado = alumnos.consultarPor("ALUMNO", matricula);
		}
		
		if(elemento.equals("CARRERA")){
			String carrera = tfCarrera.getText();

			if(carrera.equals(""))
					resultado = "CARRERA_VACIO";
			else
				resultado = alumnos.consultarPor("CARRERA", carrera);
		}
		
		return resultado;
	}

	private void print(String str){
		
		if((str.equals("ALUMNO_NO_ENCONTRADO"))||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("ALUMNO_VACIO")) || (str.equals("ALUMNO_DUPLICADO")) || (str.equals("CARRERA_VACIO")) || (str.equals("CARRERA_NO_REGISTRADO")) || (str.equals("NEGATIVO"))||(str.equals("NO_NUMERICO")) ||(str.equals("DATOS_GRANDES")))
		{	
			if(str.equals("ALUMNO_NO_ENCONTRADO"))
				taDatos.setText("La clave de alumno '" + tfMatricula.getText() + "' no se encontró en la base de datos.");

			if(str.equals("CARRERA_NO_REGISTRADO"))
				taDatos.setText("La carrera '" + tfCarrera.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("ALUMNO_VACIO"))
				taDatos.setText("El campo 'Matricula' se encuentra vacío.");

			if(str.equals("CARRERA_VACIO"))
				taDatos.setText("El campo 'Carrera' se encuentra vacío.");
			
			if(str.equals("ALUMNO_DUPLICADO"))
				taDatos.setText("El alumno con clave '" + tfMatricula.getText() + "' ya se encuentra registrado. \nPor favor introduce una matrícula válida distinta.");
			
			if(str.equals("NEGATIVO") || str.equals("NO_NUMERICO"))
				taDatos.setText("El campo del 'Plan' debe contener un número válido entero positivo.\nPor favor introduce un nuevo plan válido.");	
				
			if(str.equals("DATOS_GRANDES"))
				taDatos.setText("Algún campo contiene información con demasiados caracteres. \nPor favor revisa de nuevo la información y realiza los cambios donde sean necesarios.");
				
		}
		else
			taDatos.setText(str);
		
		if(str.equals("BD_VACIA"))
			taDatos.setText("No se ha registrado ningún alumno hasta el momento.");
			
	}

	/*************  ACTION PERFORMED  *************/ 

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bRegistrar){

			// 1) Obtener datos de los TextFields
			String datos = obtenerDatos();
			String resultado = "";

			//2) Comprobar que todos los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado
				if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NEGATIVO")||datos.equals("NO_NUMERICO"))
					print(datos);
				else
				{
					taDatos.setText(datos);
					//3) Enviar los datos a la clase AD a través del metodo registrarGrado()
					resultado = alumnos.registrarAlumno(datos);
	
					//4) Desplegar el resultado de la operación
					print(resultado);
					
					if(!resultado.equals("ALUMNO_DUPLICADO") && !resultado.equals("ALUMNO_NO_REGISTRADO")&& !resultado.equals("DATOS_GRANDES"))
						//5) Quitar la información de los TextFields
						clearFields();	
				}
		}

		if (e.getSource() == bConsultar){	
			String datos = alumnos.consultarAlumnos();
			print(datos);
		}

		if (e.getSource() == bConsultarMatricula){
			String resultado = consultar("ALUMNO");
			if(resultado.equals("ALUMNO_VACIO")||(resultado.equals("ERROR"))||(resultado.equals("ALUMNO_NO_ENCONTRADO")))
				print(resultado);
			else {
				//Colocar los datos en los TextFields
				mostrar(resultado);	
				print(resultado);
			}		
		}

		if (e.getSource() == bConsultarCarrera){	
				String resultado = consultar("CARRERA");
				print(resultado);
			}
	}

}
