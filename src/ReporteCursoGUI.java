import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ReporteCursoGUI extends JFrame implements ActionListener
{
	private JTextField tfClaveCurso;
	private JButton    bReporteCurso;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private ReporteCursoAD reporteCurso = new ReporteCursoAD(); 

	public ReporteCursoGUI(){
		super("Reporte Curso");
		
		//Inicializar los atributos
		tfClaveCurso 	= new JTextField();
		taDatos    		= new JTextArea(13, 30);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(7, 2));
		
		p1.add(new JLabel("Clave del Curso"));
		p1.add(tfClaveCurso);
		
		bReporteCurso = new JButton("Generar Reporte del Curso");
		bReporteCurso.addActionListener(this);
		p1.add(bReporteCurso);

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
		tfClaveCurso.setText("");

	}
	
	public void habilitarBotones(boolean value){
		bReporteCurso.setEnabled(value);
				
		tfClaveCurso.setEnabled(value);
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
		
		String claveCurso = tfClaveCurso.getText();

        String datos = "";
		
		if(claveCurso.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
 			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
		    token = notTokenizer(claveCurso); //Clave
		    if(token == false)
		    {
	    		datos = claveCurso;
	    	}
	   	    else
    			datos = "TOKEN";
        }
    
        return datos;
	}

	private String consultar(String elemento){
		String resultado = "";
		
		if (elemento.equals("CURSO")){
			String claveCurso = tfClaveCurso.getText();
			
			if(claveCurso.equals(""))
					resultado = "CURSO_VACIO";
			else
				resultado = reporteCurso.reporteDelCurso(claveCurso);
		}

		return resultado;
	}
	
	private void print(String str){
		
		if(str.equals("CURSO_VACIO")||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("CURSO_NO_ENCONTRADA")))
		{
			if(str.equals("CURSO_VACIO"))
				taDatos.setText("El campo 'Clave del curso' se encuentra vacío.");
								
			if(str.equals("CURSO_NO_ENCONTRADA"))
				taDatos.setText("La Clave del curso '" + tfClaveCurso.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");				
		}
		else
			taDatos.setText(str);
		
		if(str.equals("BD_VACIA"))
			taDatos.setText("No se ha registrado ningún alumno a este curso hasta el momento.");
			
	}
	
	
	/*********  ACTION PERFORMED  ********/ 
			
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bReporteCurso)
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
			    resultado = consultar("CURSO");
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    clearFields();	
			}
		}				
	}

	public static void main(String args[]){
		new ReporteCursoGUI();
	}
}
