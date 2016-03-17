import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ReporteGrupoGUI extends JFrame implements ActionListener
{
	private JTextField tfClaveProfesor, tfClaveCurso, tfGrupo;
	private JButton    bReporteGrupo;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private ReporteGrupoAD reporteGrupo = new ReporteGrupoAD(); 

	public ReporteGrupoGUI(){
		super("Reporte Alumno");
		
		//Inicializar los atributos
		tfClaveProfesor   = new JTextField();
		tfClaveCurso      = new JTextField();
		tfGrupo           = new JTextField();
		taDatos    		  = new JTextArea(13, 30);
		p1  	   		  = new JPanel();
		p2  	   		  = new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(10, 2));
		
		p1.add(new JLabel("Clave Profesor"));
		p1.add(tfClaveProfesor);
		
		p1.add(new JLabel("Clave Curso"));
		p1.add(tfClaveCurso);

		p1.add(new JLabel("Grupo"));
		p1.add(tfGrupo);

		bReporteGrupo = new JButton("Generar Reporte del Grupo");
		bReporteGrupo.addActionListener(this);
		p1.add(bReporteGrupo);

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
		tfGrupo.setText("");
		tfClaveCurso.setText("");

	}
	
	public void habilitarBotones(boolean value){
		bReporteGrupo.setEnabled(value);
				
		tfClaveProfesor.setEnabled(value);
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
		int ngrupo;
		
		String claveProfesor = tfClaveProfesor.getText();
		String grupo         = tfGrupo.getText();

        String datos = "";
		
		if(claveProfesor.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	try
        	{
        		// Comprobar que el campo "grupo" sea numérico
        		ngrupo = Integer.parseInt(grupo);

        		//Comprobar que el campo "grupo" sea positivo
        		if(ngrupo <0)
        			datos = "NEGATIVO";
        		else
        		{

		 			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
				    token = notTokenizer(claveProfesor); //Clave profesor
				    if(token == false)
				    {
				    	if(token == false){
					    	token = notTokenizer(grupo); //Grupo
					    	if (token == false)
			    				datos = claveProfesor;
				    	}

			    	}
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
		
		if (elemento.equals("GRUPO")){
			String claveProfesor = tfClaveProfesor.getText();
			String claveCurso    = tfClaveCurso.getText();
			String grupo         = tfGrupo.getText();
			
			if(claveProfesor.equals("") || claveCurso.equals("")||grupo.equals(""))
					resultado = "GRUPO_VACIO";
			else
				resultado = reporteGrupo.reporteDelGrupo(claveProfesor,claveCurso, grupo);
		}

		return resultado;
	}
	
	private void print(String str){
		
		if(str.equals("GRUPO_VACIO")||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("GRUPO_NO_ENCONTRADA")))
		{
			if(str.equals("GRUPO_VACIO"))
				taDatos.setText("El campo 'Clave de Profesor' se encuentra vacío.");
								
			if(str.equals("GRUPO_NO_ENCONTRADA"))
				taDatos.setText("La Clave de Profesor '" + tfClaveProfesor.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");				
		}
		else
			taDatos.setText(str);
		
		if(str.equals("BD_VACIA"))
			taDatos.setText("No se ha registrado ningún artículo hasta el momento.");
			
	}
	
	
	/*********  ACTION PERFORMED  ********/ 
			
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bReporteGrupo)
		{
			//1) Obtener datos de los TextFields
            String datos = obtenerDatos();
            String resultado = "";
            
            //2) Comprobar que todos los campos cumplan con los diversos requisitos, y en caso de que estos no se respeten, evitar enviar los datos en ese estado
			if(datos.equals("CAMPO_VACIO")||datos.equals("TOKEN"))
				print(datos);
			else
			{
				//3) Enviar los datos a la clase AD a través del metodo consultar()
			    resultado = consultar("GRUPO");
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    clearFields();	
			}
		}				
	}

	public static void main(String args[]){
		new ReporteGrupoGUI();
	}
}
