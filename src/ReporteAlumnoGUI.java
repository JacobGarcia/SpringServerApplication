import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ReporteAlumnoGUI extends JFrame implements ActionListener
{
	private JTextField tfMatricula;
	private JButton    bReporteAlumno;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private ReporteAlumnoAD reporteAlumno = new ReporteAlumnoAD(); 

	public ReporteAlumnoGUI(){
		super("Reporte Alumno");
		
		//Inicializar los atributos
		tfMatricula 	= new JTextField();
		taDatos    		= new JTextArea(13, 30);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(7, 2));
		
		p1.add(new JLabel("Matricula"));
		p1.add(tfMatricula);
		
		bReporteAlumno = new JButton("Generar Reporte del Alumno");
		bReporteAlumno.addActionListener(this);
		p1.add(bReporteAlumno);

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

	}
	
	public void habilitarBotones(boolean value){
		bReporteAlumno.setEnabled(value);
				
		tfMatricula.setEnabled(value);
	}
	
	// private void mostrar(String str){
	// 	StringTokenizer st = new StringTokenizer(str, "_");
					
	// 	String matricula = setText(matricula);
					
	// 	tfMatricula.setText(matricula);
	// }
		
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
		
		String matricula = tfMatricula.getText();

        String datos = "";
		
		if(matricula.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
 			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
		    token = notTokenizer(matricula); //Clave
		    if(token == false)
		    {
	    		datos = matricula;
	    	}
	   	    else
    			datos = "TOKEN";
        }
    
        return datos;
	}

	private String consultar(String elemento){
		String resultado = "";
		
		if (elemento.equals("ALUMNO")){
			String matricula = tfMatricula.getText();
			
			if(matricula.equals(""))
					resultado = "MATRICULA_VACIO";
			else
				resultado = reporteAlumno.reporteDelAlumno(matricula);
		}

		return resultado;
	}
	
	private void print(String str){
		
		if(str.equals("MATRICULA_VACIO")||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("MATRICULA_NO_ENCONTRADA")))
		{
			if(str.equals("MATRICULA_VACIO"))
				taDatos.setText("El campo 'Matricula' se encuentra vacío.");
								
			if(str.equals("MATRICULA_NO_ENCONTRADA"))
				taDatos.setText("La Matricula '" + tfMatricula.getText() + "' no se encontró en la base de datos.");
				
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
		if (e.getSource() == bReporteAlumno)
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
			    resultado = consultar("ALUMNO");
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    clearFields();	
			}
		}				
	}

	public static void main(String args[]){
		new ReporteAlumnoGUI();
	}
}
