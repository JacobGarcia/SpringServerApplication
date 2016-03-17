import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class TomaGUI extends JFrame implements ActionListener
{
	private JTextField tfMatricula, tfClaveCurso, tfGrupo;
	private JButton    bRegistrar, bConsultarAlumno, bConsultarCurso, bConsultar;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private TomaAD toma = new TomaAD(); 

	public TomaGUI(){
		super("Inscribir Curso");
		
		//Inicializar los atributos
		tfMatricula 	= new JTextField();
		tfClaveCurso   		= new JTextField();
		tfGrupo   		= new JTextField();
		taDatos    		= new JTextArea(18, 57);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(5, 2));
		
		p1.add(new JLabel("Matrícula del Alumno"));
		p1.add(tfMatricula);

		p1.add(new JLabel("Clave del Curso"));
		p1.add(tfClaveCurso);
		
		p1.add(new JLabel("Grupo"));
		p1.add(tfGrupo);

		bRegistrar = new JButton("Inscribir Curso");
		bRegistrar.addActionListener(this);
		p1.add(bRegistrar);
		
		bConsultarAlumno = new JButton("Consultar los Cursos de un Alumno");
		bConsultarAlumno.addActionListener(this);
		
		bConsultarCurso = new JButton("Consultar Alumnos que Toman un Curso");
		bConsultarCurso.addActionListener(this);
		
		bConsultar = new JButton("Consultar Cursos Inscritos");
		bConsultar.addActionListener(this);

		p1.add(bConsultar);
		p1.add(bConsultarAlumno);
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
		tfMatricula.setText("");
		tfClaveCurso.setText("");
		tfGrupo.setText("");
	}
	
	public void habilitarBotones(boolean value){
		bRegistrar.setEnabled(value); 
		bConsultarAlumno.setEnabled(value);
						
		tfMatricula.setEnabled(value);
		tfClaveCurso.setEnabled(value);
		tfGrupo.setEnabled(value);
	}
	
	public void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
					
		String claveProfesor = st.nextToken();
		String claveCurso = st.nextToken();
		String grupo = st.nextToken();
					
		tfMatricula.setText(claveProfesor);
		tfClaveCurso.setText(claveCurso);
		tfGrupo.setText(grupo);
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
		
		if (elemento.equals("ALUMNO")){
			String alumno = tfMatricula.getText();
			
			if(alumno.equals(""))
					resultado = "ALUMNO_VACIO";
			else
				resultado = toma.consultarPor("ALUMNO", alumno);
		}
		
		if (elemento.equals("CURSO")){
			String curso = tfClaveCurso.getText();
			
			if(curso.equals(""))
					resultado = "CURSO_VACIO";
			else
				resultado = toma.consultarPor("CURSO", curso);
		}

		return resultado;
	}
	
	private String obtenerDatos(){
		boolean token = false;
		int group;
		
		String claveProfesor  = tfMatricula.getText();
		String claveCurso     = tfClaveCurso.getText();
		String grupo     	  = tfGrupo.getText();

        String datos = "";
		
		if(claveProfesor.equals("")||claveCurso.equals("")||grupo.equals(""))
			datos = "CAMPO_VACIO";
        else
        { 	try {	
        		group = Integer.parseInt(grupo);
        		
        		if (group < 1)
        			datos = "NEGATIVO";
        		else {
					// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
					token = notTokenizer(claveProfesor); //Clave ALUMNO
					if(token == false)
					  token = notTokenizer(claveCurso); //Clave Curso
				   	
				   	if(token == false)
						datos = claveProfesor + "_" + claveCurso + "_" + grupo;
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
	
	private void print(String str){
		
		if(str.equals("ALUMNO_VACIO")||(str.equals("CURSO_VACIO"))||(str.equals("CLAVE_NO_ENCONTRADA"))||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("NO_NUMERICO"))||(str.equals("NEGATIVO"))||(str.equals("CLAVE_VACIA"))|| (str.equals("SEMESTRE_NO_REGISTRADO")) || (str.equals("CLAVE_DUPLICADA")) || (str.equals("CURSO_NO_REGISTRADO")) || (str.equals("DEPARTAMENTO_NO_ENCONTRADO")) || (str.equals("CLAVE_NO_REGISTRADA")) || (str.equals("CURSO_DUPLICADO"))|| (str.equals("ALUMNO_NO_ENCONTRADO"))|| (str.equals("CURSO_NO_ENCONTRADO")) ||(str.equals("DATOS_GRANDES")))
		{
			if(str.equals("ALUMNO_VACIO"))
				taDatos.setText("El campo 'Matrícula' se encuentra vacío.");
				
			if(str.equals("CURSO_VACIO"))
				taDatos.setText("El campo 'Clave Curso' se encuentra vacío.");
				
			if(str.equals("CLAVE_CURSO_NO_ENCONTRADA"))
				taDatos.setText("La Clave del Curso '" + tfClaveCurso.getText() + "' no se encontró en la base de datos.");

			if(str.equals("CLAVE_ALUMNO_NO_ENCONTRADA"))
				taDatos.setText("La Clave del Alumno '" + tfClaveCurso.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("CURSO_DUPLICADO"))
				taDatos.setText("El Alumno '" + tfMatricula.getText() + "' ya toma el curso " + tfClaveCurso.getText() + ". \nPor favor introduce otro Alumno u otro Curso.");
				
			if(str.equals("CURSO_NO_REGISTRADO"))
				taDatos.setText("El Alumno '" + tfMatricula.getText() + "' o el curso '" + tfClaveCurso.getText() + "' no están registrados en la base de datos.\nPor favor introduce nuevos datos válidos." );
				
							
			if(str.equals("ALUMNO_NO_ENCONTRADO"))
				taDatos.setText("No se tienen cursos registrados para el Alumno '" + tfMatricula.getText() + "'.");
				
											
			if(str.equals("CURSO_NO_ENCONTRADO"))
				taDatos.setText("No se tienen cursos registrados con la clave '" + tfClaveCurso.getText() + "'.");					
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
			if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN"))
				print(datos);
			else
			{
				//3) Enviar los datos a la clase AD a través del metodo registrarCurso()
			    resultado = toma.imparteCurso(datos);
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    if(!resultado.equals("CURSO_DUPLICADO") && !resultado.equals("CURSO_NO_REGISTRADO") && !resultado.equals("DATOS_GRANDES"))
			    	//5) Quitar la información de los TextFields
			    	clearFields();	
			}
		}
		
		 if (e.getSource() == bConsultar){	
		 	String datos = toma.consultarCursos();
		 	print(datos);
		 }

		 if (e.getSource() == bConsultarAlumno){
		 	String resultado = consultar("ALUMNO");
	 		print(resultado);
		 }	
		 
		  if (e.getSource() == bConsultarCurso){
		 	String resultado = consultar("CURSO");
	 		print(resultado);
		 }				
	}

	public static void main(String args[]){
		new TomaGUI();
	}
}
