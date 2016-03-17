import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class ImparteGUI extends JFrame implements ActionListener
{
	private JTextField tfClaveProfesor, tfClaveCurso, tfGrupo, tfHorario;
	private JButton    bRegistrar, bConsultarProfesor, bConsultarCurso, bConsultar;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private ImparteAD imparte = new ImparteAD(); 

	public ImparteGUI(){
		super("Impartir Curso");
		
		//Inicializar los atributos
		tfClaveProfesor 	= new JTextField();
		tfClaveCurso   		= new JTextField();
		tfGrupo 			= new JTextField();
		tfHorario			= new JTextField();
		taDatos    		= new JTextArea(17, 63);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(6, 2));
		
		p1.add(new JLabel("Clave del Profesor"));
		p1.add(tfClaveProfesor);

		p1.add(new JLabel("Clave del Curso"));
		p1.add(tfClaveCurso);
		
		p1.add(new JLabel("Grupo"));
		p1.add(tfGrupo);
		
		p1.add(new JLabel("Horario"));
		p1.add(tfHorario);
		
		bRegistrar = new JButton("Dar de Alta al Curso");
		bRegistrar.addActionListener(this);
		p1.add(bRegistrar);
		
		bConsultarProfesor = new JButton("Consultar los Cursos de un Profesor");
		bConsultarProfesor.addActionListener(this);
		
		bConsultarCurso = new JButton("Consultar Profesores que Imparten un Curso");
		bConsultarCurso.addActionListener(this);
		
		bConsultar = new JButton("Consultar Cursos Inscritos");
		bConsultar.addActionListener(this);

		p1.add(bConsultar);
		p1.add(bConsultarProfesor);
		p1.add(bConsultarCurso);
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
		tfClaveProfesor.setText("");
		tfClaveCurso.setText("");
		tfGrupo.setText("");
		tfHorario.setText("");
	}
	
	public void habilitarBotones(boolean value){
		bRegistrar.setEnabled(value); 
		bConsultarProfesor.setEnabled(value);
						
		tfClaveProfesor.setEnabled(value);
		tfClaveCurso.setEnabled(value);
	}
	
	public void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
					
		String claveProfesor = st.nextToken();
		String claveCurso = st.nextToken();
		String grupo = st.nextToken();
		String horario = st.nextToken();
							
		tfClaveProfesor.setText(claveProfesor);
		tfClaveCurso.setText(claveCurso);
		tfGrupo.setText(grupo);
		tfHorario.setText(horario);
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
	
	private String consultar(String elemento){
		String resultado = "";
		
		if (elemento.equals("PROFESOR")){
			String profesor = tfClaveProfesor.getText();
			
			if(profesor.equals(""))
					resultado = "PROFESOR_VACIO";
			else
				resultado = imparte.consultarPor("PROFESOR", profesor);
		}
		
		if (elemento.equals("CURSO")){
			String curso = tfClaveCurso.getText();
			
			if(curso.equals(""))
					resultado = "CURSO_VACIO";
			else
				resultado = imparte.consultarPor("CURSO", curso);
		}

		return resultado;
	}
	
	private String obtenerDatos(){
		boolean token = false;
		int group;
		
		String claveProfesor  = tfClaveProfesor.getText();
		String claveCurso     = tfClaveCurso.getText();
		String grupo  		  = tfGrupo.getText();
		String horario   	  = tfHorario.getText();

        String datos = "";
		
		if(claveProfesor.equals("")||claveCurso.equals("")||grupo.equals("")||horario.equals(""))
			datos = "CAMPO_VACIO";
        else
        { 	
        	try {	
        		group = Integer.parseInt(grupo);
        		
        		if (group < 1)
        			datos = "NEGATIVO";
        		else {
					// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
					token = notTokenizer(claveProfesor); //Clave Profesor
					if(token == false){
					  token = notTokenizer(claveCurso); //Clave Curso
					   if(token == false){
					 	 token = notTokenizer(grupo); //Grupo
						 if(token == false)
						 	token = notTokenizer(horario); //Horario
						 	
						 if(token == false)
	        				datos = claveProfesor + "_" + claveCurso + "_" + grupo + "_" + horario;
						 else
							datos = "TOKEN";
					   }
				 	}
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
	
	private void print(String str){
		
		if(str.equals("PROFESOR_VACIO")||(str.equals("CURSO_VACIO"))||(str.equals("CLAVE_NO_ENCONTRADA"))||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("NO_NUMERICO"))||(str.equals("NEGATIVO"))||(str.equals("CLAVE_VACIA"))|| (str.equals("SEMESTRE_NO_REGISTRADO")) || (str.equals("CLAVE_DUPLICADA")) || (str.equals("CURSO_NO_REGISTRADO")) || (str.equals("DEPARTAMENTO_NO_ENCONTRADO")) || (str.equals("CLAVE_NO_REGISTRADA")) || (str.equals("CURSO_DUPLICADO"))|| (str.equals("PROFESOR_NO_ENCONTRADO"))|| (str.equals("CURSO_NO_ENCONTRADO")) ||(str.equals("DATOS_GRANDES")))
		{
			if(str.equals("PROFESOR_VACIO"))
				taDatos.setText("El campo 'Clave Profesor' se encuentra vacío.");
				
			if(str.equals("CURSO_VACIO"))
				taDatos.setText("El campo 'Clave Curso' se encuentra vacío.");
				
			if(str.equals("CLAVE_CURSO_NO_ENCONTRADA"))
				taDatos.setText("La Clave del Curso '" + tfClaveCurso.getText() + "' no se encontró en la base de datos.");

			if(str.equals("CLAVE_PROFESOR_NO_ENCONTRADA"))
				taDatos.setText("La Clave del Profesor '" + tfClaveCurso.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("CURSO_DUPLICADO"))
				taDatos.setText("Ya se tiene registrado el grupo '" + tfGrupo.getText() + "' para el curso " + tfClaveCurso.getText() + ". \nPor favor introduce un grupo o curso o distinto");
				
			if(str.equals("CURSO_NO_REGISTRADO"))
				taDatos.setText("El Profesor '" + tfClaveProfesor.getText() + "' o el curso '" + tfClaveCurso.getText() + "' no están registrados en la base de datos.\nPor favor introduce nuevos datos válidos." );
				
							
			if(str.equals("PROFESOR_NO_ENCONTRADO"))
				taDatos.setText("No se tienen cursos registrados para el profesor '" + tfClaveProfesor.getText() + "'.");
				
			if(str.equals("NEGATIVO") || str.equals("NO_NUMERICO"))
				taDatos.setText("El campo del 'Grupo' debe contener un número válido entero positivo.\nPor favor introduce un nuevo grupo válido.");	
											
			if(str.equals("CURSO_NO_ENCONTRADO"))
				taDatos.setText("No se tienen cursos registrados con la clave '" + tfClaveCurso.getText() + "'.");			
				
			if(str.equals("DATOS_GRANDES"))
				taDatos.setText("Algún campo contiene información con demasiados caracteres. \nPor favor revisa de nuevo la información y realiza los cambios donde sean necesarios.");
						
		}
		else
			taDatos.setText(str);
		
		if(str.equals("BD_VACIA"))
			taDatos.setText("No se ha registrado ningún artículo hasta el momento.");
			
	}
	
	
	/******************  ACTION PERFORMED  *********/ 
			
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bRegistrar)
		{
			//1) Obtener datos de los TextFields
            String datos = obtenerDatos();
            String resultado = "";
            
            //2) Comprobar que todos los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado
			if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN")||datos.equals("NEGATIVO")||datos.equals("NO_NUMERICO"))
				print(datos);
			else
			{
				//3) Enviar los datos a la clase AD a través del metodo registrarCurso()
			    resultado = imparte.imparteCurso(datos);
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    if(!resultado.equals("CURSO_DUPLICADO") && !resultado.equals("CURSO_NO_REGISTRADO") && !resultado.equals("DATOS_GRANDES"))
			    	//5) Quitar la información de los TextFields
			    	clearFields();	
			}
		}
		
		 if (e.getSource() == bConsultar){	
		 	String datos = imparte.consultarCursos();
		 	print(datos);
		 }

		 if (e.getSource() == bConsultarProfesor){
		 	String resultado = consultar("PROFESOR");
	 		print(resultado);
		 }	
		 
		  if (e.getSource() == bConsultarCurso){
		 	String resultado = consultar("CURSO");
	 		print(resultado);
		  }			
	}

	public static void main(String args[]){
		new ImparteGUI();
	}
}
