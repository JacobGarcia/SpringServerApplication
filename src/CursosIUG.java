import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class CursosIUG extends JFrame implements ActionListener
{
	private JTextField tfClave, tfNombre, tfNumeroDepto, tfSemestre;
	private JButton    bRegistrar, bConsultar, bConsultarSemestre, bConsultarClave, bConsultarDepartamento;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private CursosAD cursos = new CursosAD(); 

	public CursosIUG(){
		super("Curso");
		
		//Inicializar los atributos
		tfClave 	= new JTextField();
		tfNombre   		= new JTextField();
		tfNumeroDepto			= new JTextField();
		tfSemestre	= new JTextField();
		taDatos    		= new JTextArea(15, 53);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(7, 2));
		
		p1.add(new JLabel("Clave del Curso"));
		p1.add(tfClave);

		p1.add(new JLabel("Nombre del Curso"));
		p1.add(tfNombre);
		
		p1.add(new JLabel("Semestre del Curso"));
		p1.add(tfSemestre);
		
		p1.add(new JLabel("No. de Departamento del Curso"));
		p1.add(tfNumeroDepto);

		bRegistrar = new JButton("Registrar Curso");
		bRegistrar.addActionListener(this);
		p1.add(bRegistrar);
		
		bConsultar = new JButton("Consultar Cursos");
		bConsultar.addActionListener(this);
		p1.add(bConsultar);
		
		bConsultarClave = new JButton("Consultar Cursos por Clave");
		bConsultarClave.addActionListener(this);
		p1.add(bConsultarClave);

		bConsultarSemestre = new JButton("Consultar Cursos por Semestre");
		bConsultarSemestre.addActionListener(this);
		p1.add(bConsultarSemestre);
		
		bConsultarDepartamento = new JButton("Consultar Cursos por Departamento");
		bConsultarDepartamento.addActionListener(this);
		p1.add(bConsultarDepartamento);

		p2.setLayout(new FlowLayout());
		
		p2.add(p1);
		p2.add(new JScrollPane(taDatos));

		add(p2);
		/* setSize(590,400);
		setVisible(true); */
	}
		
	public JPanel getPanel2()
	{
		return this.p2;
	}

	public void clearFields(){
		tfClave.setText("");
		tfNombre.setText("");
		tfSemestre.setText("");
		tfNumeroDepto.setText("");
	}
	
	public void habilitarBotones(boolean value){
		bRegistrar.setEnabled(value); 
		bConsultar.setEnabled(value);
		bConsultarClave.setEnabled(value);
		bConsultarSemestre.setEnabled(value);
		bConsultarDepartamento.setEnabled(value);
				
		tfClave.setEnabled(value);
		tfNombre.setEnabled(value);
		tfSemestre.setEnabled(value);
		tfNumeroDepto.setEnabled(value);
	}
	
	private void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
					
		String clave = st.nextToken();
		String nombre = st.nextToken();
		String semestre	= st.nextToken();
		String ndepto = st.nextToken();
					
		tfClave.setText(clave);
		tfNombre.setText(nombre);
		tfSemestre.setText(semestre);
		tfNumeroDepto.setText(ndepto);
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
		int ndepto;
		int sem;
		
		String clave      = tfClave.getText();
		String nombre     = tfNombre.getText();
        String semestre	   = tfSemestre.getText();
        String numeroDepto = tfNumeroDepto.getText();
        String datos = "";
		
		if(clave.equals("")||nombre.equals("")||semestre.equals("") || numeroDepto.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	try
        	{
        		// Comprobar que el campo de "Número de Departamento" sea numérico, así como el semestre
        		ndepto = Integer.parseInt(numeroDepto);
        		sem = Integer.parseInt(semestre);
        		
        		// Comprobar que el campo de "Número de Departamento" sea positivo, así como el semestre
        		if(ndepto < 0 || sem < 0)
        			datos = "NEGATIVO";	
        		else
        		{
        			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
				     token = notTokenizer(clave); //Clave
				     if(token == false)
				     {
				     	token = notTokenizer(nombre); //Nombre
				     	if(token == false)
					     	token = notTokenizer(semestre); // Semestre
		        	 }
		        	 
		         	 if(token == false)
		        		 datos = clave+"_"+nombre+"_"+semestre+"_"+ndepto;
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
		
		if (elemento.equals("SEMESTRE")){
			String semestre = tfSemestre.getText();
			
			if(semestre.equals(""))
					resultado = "SEMESTRE_VACIO";
			else
				resultado = cursos.consultarPor("SEMESTRE", semestre);
		}
		
		if (elemento.equals("DEPARTAMENTO")){
			String nombre = tfNumeroDepto.getText();
			
			if(nombre.equals(""))
					resultado = "DEPARTAMENTO_VACIO";
			else
				resultado = cursos.consultarPor("DEPARTAMENTO", nombre);
		}
	
		if(elemento.equals("CLAVE")){
			String clave = tfClave.getText();

			if(clave.equals(""))
					resultado = "CLAVE_VACIA";
			else
				resultado = cursos.consultarPor("CLAVE", clave);
		}

		return resultado;
	}
	
	private void print(String str){
		
		if(str.equals("DEPARTAMENTO_VACIO")||(str.equals("SEMESTRE_VACIO"))||(str.equals("CLAVE_NO_ENCONTRADA"))||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("NO_NUMERICO"))||(str.equals("NEGATIVO"))||(str.equals("CLAVE_VACIA"))|| (str.equals("SEMESTRE_NO_REGISTRADO")) || (str.equals("CLAVE_DUPLICADA")) || (str.equals("CURSO_NO_REGISTRADO")) || (str.equals("DEPARTAMENTO_NO_ENCONTRADO")) || (str.equals("CLAVE_NO_REGISTRADA")) ||(str.equals("DATOS_GRANDES")))
		{
			if(str.equals("DEPARTAMENTO_VACIO"))
				taDatos.setText("El campo 'Número de Departamento' se encuentra vacío.");
				
			if(str.equals("SEMESTRE_VACIO"))
				taDatos.setText("El campo 'Semestre' se encuentra vacío.");
				
			if(str.equals("CLAVE_NO_ENCONTRADA"))
				taDatos.setText("El curso '" + tfClave.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("NO_NUMERICO") || str.equals("NEGATIVO"))
				taDatos.setText("Los campos 'Número de Departamento' y 'Semestre' deben contener valores numéricos enteros positivos.");
			
			if(str.equals("CLAVE_VACIA"))
				taDatos.setText("El campo 'Clave' se encuentra vacío.");
			
			if(str.equals("CLAVE_DUPLICADA"))
				taDatos.setText("El curso con clave '" + tfClave.getText() + "' o nombre '" + tfNombre.getText() + "' ya se encuentra en la base de datos. \nPor favor introduce otra clave o nombre de curso.");
			
			if(str.equals("SEMESTRE_NO_REGISTRADO"))
				taDatos.setText("No se tienen cursos registrados para el semestre '" + tfSemestre.getText() + "'.");
				
			if(str.equals("CURSO_NO_REGISTRADO"))
				taDatos.setText("El número de departamento '" + tfNumeroDepto.getText() + "' no se encuentra en la base de datos. \nPor favor introduce un Departamento con un número válido.");
				
			if(str.equals("DEPARTAMENTO_NO_ENCONTRADO"))
				taDatos.setText("No se tienen cursos registrados con el departamento '" + tfNumeroDepto.getText() + "'.");
				
			if(str.equals("CLAVE_NO_REGISTRADA"))
				taDatos.setText("No se tiene ningún curso registrado bajo la clave '" + tfClave.getText() + "'.");
				
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
			if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NO_NUMERICO")||datos.equals("NEGATIVO"))
				print(datos);
			else
			{
				//3) Enviar los datos a la clase AD a través del metodo registrarCurso()
			    resultado = cursos.registrarCurso(datos);
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    if(!resultado.equals("CLAVE_DUPLICADA") && !resultado.equals("CURSO_NO_REGISTRADO") && !resultado.equals("DATOS_GRANDES"))
			    	//5) Quitar la información de los TextFields
			    	clearFields();	
			}
		}
		
		if (e.getSource() == bConsultar){	
			String datos = cursos.consultarCursos();
			print(datos);
		}

		if (e.getSource() == bConsultarClave){
			String resultado = consultar("CLAVE");
			if(resultado.equals("CLAVE_VACIA")||(resultado.equals("ERROR"))||(resultado.equals("CLAVE_NO_REGISTRADA")))
				print(resultado);
			else{
				//Colocar los datos en los TextFields
				mostrar(resultado);	
				print(resultado);
			}	
		}
		
		if (e.getSource() == bConsultarSemestre){	
			String resultado = consultar("SEMESTRE");
			print(resultado);
		}
		
		if (e.getSource() == bConsultarDepartamento){	
			String resultado = consultar("DEPARTAMENTO");
			print(resultado);
		}
				
	}

	public static void main(String args[]){
		new CursosIUG();
	}
}
