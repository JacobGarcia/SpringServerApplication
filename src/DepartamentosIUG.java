import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Date;

@SuppressWarnings("serial")
public class DepartamentosIUG extends JFrame implements ActionListener
{
	private JTextField tfNumeroDepto,tfNombre, tfDate, tfAdministrador;
	private JButton    bRegistrar, bConsultar, bConsutlarAdministrador, bConsultarNumeroDepto, bConsultarNombre, bCambiar, bCancelar, bConfirmar;
	private JTextArea  taDatos;
	public JPanel 	   p1, p2;
	
	private DepartamentosAD departamentos = new DepartamentosAD(); 
	
	/* MySQL Date format */
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    
    private Timer clock;
    
    private JComboBox<String> combo;
   private String opciones[] = departamentos.opciones();
   
	public DepartamentosIUG(){
		super("Departamento");
		
		//Inicializar los atributos
		tfNumeroDepto 	= new JTextField();
		tfNombre   		= new JTextField();
		tfDate			= new JTextField();
		tfAdministrador	= new JTextField();
		taDatos    		= new JTextArea(12, 63);
		p1  	   		= new JPanel();
		p2  	   		= new JPanel();
		
		//Agregar los atributos a los paneles
		p1.setLayout(new GridLayout(8, 2));
		
		p1.add(new JLabel("Número de Departamento"));
		p1.add(tfNumeroDepto);

		p1.add(new JLabel("Nombre del Departamento"));
		p1.add(tfNombre);
		
		/*p1.add(new JLabel("Clave del Administrador"));
		p1.add(tfAdministrador);*/
		
		// JComboBox
		combo = new JComboBox<String>(opciones);
		combo.addActionListener(this);
				
		p1.add(new JLabel("Clave del Administrador"));
		p1.add(combo);

		p1.add(new JLabel("Fecha de Inicio"));
		p1.add(tfDate);
		tfDate.setEditable(false);

		bRegistrar = new JButton("Registrar Departamento");
		bRegistrar.addActionListener(this);
		p1.add(bRegistrar);
		
		bConsultar = new JButton("Consultar Departamentos");
		bConsultar.addActionListener(this);
		p1.add(bConsultar);
		
		bConsultarNumeroDepto = new JButton("Consultar Departamentos por Número");
		bConsultarNumeroDepto.addActionListener(this);
		p1.add(bConsultarNumeroDepto);

		bConsutlarAdministrador = new JButton("Consultar Departamentos por Administrador");
		bConsutlarAdministrador.addActionListener(this);
		p1.add(bConsutlarAdministrador);
		
		bConsultarNombre = new JButton("Consultar Departamentos por Nombre");
		bConsultarNombre.addActionListener(this);
		p1.add(bConsultarNombre);
		
		bCambiar = new JButton("Cambiar Administrador del Departamento");
		bCambiar.addActionListener(this);
		p1.add(bCambiar);
				
		bConfirmar = new JButton("Confirmar Transacción");
		bConfirmar.addActionListener(this);
		p1.add(bConfirmar);

		bCancelar = new JButton("Cancelar Transacción");
		bCancelar.addActionListener(this);
		p1.add(bCancelar);

		p2.setLayout(new FlowLayout());
		
		p2.add(p1);
		p2.add(new JScrollPane(taDatos));
		
		// Reloj
		clock = new Timer(1000, updateClockAction);
		clock.start();
		
		add(p2);
		/* setSize(720,400);
		setVisible(true); */
		bCancelar.setEnabled(false);
		bConfirmar.setEnabled(false);
		
		
	}
	
	public JPanel getPanel2()
	{
		return this.p2;
	}
	

	public void clearFields(){
		tfNumeroDepto.setText("");
		tfNombre.setText("");
	}
	
	public void habilitarBotones(boolean value){
		bRegistrar.setEnabled(value); 
		bConsultar.setEnabled(value);
		bConsultarNumeroDepto.setEnabled(value);
		bConsutlarAdministrador.setEnabled(value);
		bConsultarNombre.setEnabled(value);
		bCambiar.setEnabled(value);
				
		tfNumeroDepto.setEnabled(value);
		tfNombre.setEnabled(value);
		
		bCancelar.setEnabled(!value);
		bConfirmar.setEnabled(!value);
		
		if (value == true)
			combo.setSelectedItem("N/A");
	}
	
	private void mostrar(String str){
		StringTokenizer st = new StringTokenizer(str, "_");
					
		String numeroDepto = st.nextToken();
		String nombre = st.nextToken();
		String administrador	= st.nextToken();
					
		tfNumeroDepto.setText(numeroDepto);
		tfNombre.setText(nombre);
		//tfAdministrador.setText(administrador);
		combo.setSelectedItem(administrador);
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
		
		String numeroDepto      = tfNumeroDepto.getText();
		String nombre     = tfNombre.getText();
        String administrador	   = combo.getSelectedItem().toString();
        String fecha = tfDate.getText();
        String datos = "";
		
		if(numeroDepto.equals("")||nombre.equals(""))
			datos = "CAMPO_VACIO";
        else
        {
        	try
        	{
        		// Comprobar que el campo de "Número de Departamento" sea numérico
        		ndepto = Integer.parseInt(numeroDepto);
        		
        		// Comprobar que el campo de "Número de Departamento" sea positivo
        		if(ndepto < 0)
        			datos = "NEGATIVO";	
        		else
        		{
        			// Verificar que no existan tokens en los strings, en este caso '_' que puedan llegar a comprometer el correcto funcionamiento del sistema
				     token = notTokenizer(numeroDepto); //Número de Departamento
				     if(token == false)
				     	token = notTokenizer(nombre); //Nombre
		        	 
		         	 if(token == false)
		        		 datos = numeroDepto+"_"+nombre+"_"+administrador+"_"+fecha;
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
		
		if (elemento.equals("ADMINISTRADOR")){
			String administrador = combo.getSelectedItem().toString();
			
			if(administrador.equals("N/A"))
					resultado = "ADMINISTRADOR_VACIO";
			else
				resultado = departamentos.consultarPor("ADMINISTRADOR", administrador);
		}
		
		if (elemento.equals("NOMBRE")){
			String nombre = tfNombre.getText();
			
			if(nombre.equals(""))
					resultado = "NOMBRE_VACIO";
			else
				resultado = departamentos.consultarPor("NOMBRE", nombre);
		}
	
		if(elemento.equals("DEPARTAMENTO")){
			String numeroDepto = tfNumeroDepto.getText();

			if(numeroDepto.equals(""))
					resultado = "DEPARTAMENTO_VACIO";
			else
				resultado = departamentos.consultarPor("DEPARTAMENTO", numeroDepto);
		}

		return resultado;
	}
	
	private void print(String str){
		
		if(str.equals("NOMBRE_VACIO")||(str.equals("ADMINISTRADOR_VACIO"))||(str.equals("DEPARTAMENTO_NO_ENCONTRADO"))||(str.equals("CAMPO_VACIO"))||(str.equals("TOKEN"))||(str.equals("NO_NUMERICO"))||(str.equals("NEGATIVO"))||(str.equals("DEPARTAMENTO_VACIO"))||(str.equals("NO_VENTA"))|| (str.equals("ADMINISTRADOR_NO_REGISTRADO")) || (str.equals("DEPARTAMENTO_DUPLICADO")) || (str.equals("PROFESOR_NO_REGISTRADO")) || (str.equals("NOMBRE_NO_REGISTRADO"))||(str.equals("DATOS_GRANDES")))
		{
			if(str.equals("NOMBRE_VACIO"))
				taDatos.setText("El campo 'Nombre' se encuentra vacío.");
				
			if(str.equals("ADMINISTRADOR_VACIO"))
				taDatos.setText("El campo 'Clave del Administrador' se encuentra vacío.");
				
			if(str.equals("DEPARTAMENTO_NO_ENCONTRADO"))
				taDatos.setText("El número de departamento '" + tfNumeroDepto.getText() + "' no se encontró en la base de datos.");
				
			if(str.equals("CAMPO_VACIO"))
				taDatos.setText("Todos los campos deben contener datos.");
				
			if(str.equals("TOKEN"))
				taDatos.setText("Los datos que se capturan no pueden contener un '_'");
			
			if(str.equals("NO_NUMERICO") || str.equals("NEGATIVO"))
				taDatos.setText("El campo 'Número de Departamento' debe contener valores numéricos enteros positivos.");
			
			if(str.equals("DEPARTAMENTO_VACIO"))
				taDatos.setText("El campo 'Número de Departamento' se encuentra vacío.");
			
			if(str.equals("DEPARTAMENTO_DUPLICADO"))
				taDatos.setText("El departamento con número '" + tfNumeroDepto.getText() + "' o nombre '" + tfNombre.getText() + "' ya se encuentra en la base de datos. \nPor favor introduce otro Nombre o Número de Departamento.");
			
			if(str.equals("ADMINISTRADOR_NO_REGISTRADO"))
				taDatos.setText("No se tienen departamentos registrados para el administrador '" + tfAdministrador.getText() + "'.");
				
			if(str.equals("PROFESOR_NO_REGISTRADO"))
				taDatos.setText("La clave del administrador '" + combo.getSelectedItem().toString() + "' no se encuentra en la base de datos. \nPor favor introduce un Profesor con una clave válida.");
				
			if(str.equals("NOMBRE_NO_REGISTRADO"))
				taDatos.setText("No se tienen departamentos registrados con el nombre '" + tfNombre.getText() + "'.");
				
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
				//3) Enviar los datos a la clase AD a través del metodo registrarDepartamento()
			    resultado = departamentos.registrarDepartamento(datos);
	
			    //4) Desplegar el resultado de la operación
			    print(resultado);
			    
			    if(!resultado.equals("DEPARTAMENTO_DUPLICADO") && !resultado.equals("PROFESOR_NO_REGISTRADO") && !resultado.equals("DATOS_GRANDES"))
			    	//5) Quitar la información de los TextFields
			    	clearFields();	
			}
		}
		
		if (e.getSource() == bConsultar){	
			String datos = departamentos.consultarDepartamentos();
			print(datos);
		}

		if (e.getSource() == bConsultarNumeroDepto){
			String resultado = consultar("DEPARTAMENTO");
			if(resultado.equals("DEPARTAMENTO_VACIO")||(resultado.equals("ERROR"))||(resultado.equals("DEPARTAMENTO_NO_ENCONTRADO")))
				print(resultado);
			else{
				//Colocar los datos en los TextFields
				mostrar(resultado);	
				print(resultado);
			}	
		}
		
		if (e.getSource() == bConsutlarAdministrador){	
			String resultado = consultar("ADMINISTRADOR");
			print(resultado);
		}
		
		if (e.getSource() == bConsultarNombre){	
			String resultado = consultar("NOMBRE");
			if(resultado.equals("NOMBRE_VACIO")||(resultado.equals("ERROR"))||(resultado.equals("NOMBRE_NO_REGISTRADO")))
				print(resultado);
			else {
				//Colocar los datos en los TextFields
				mostrar(resultado);	
				print(resultado);
			}	
		}
		
		if (e.getSource() == bCambiar){
			String resultado = consultar("DEPARTAMENTO");
			if(resultado.equals("DEPARTAMENTO_VACIO")||(resultado.equals("ERROR"))||(resultado.equals("DEPARTAMENTO_NO_ENCONTRADO")))
				print(resultado);
			else{
				//Colocar los datos en los TextFields
				mostrar(resultado);	
				habilitarBotones(false);
			}	
		}	
		
		if (e.getSource() == bCancelar){
			habilitarBotones(true);
			clearFields();
		}	
		
		if (e.getSource() == bConfirmar){
			String admin = combo.getSelectedItem().toString();
			String ndepto = tfNumeroDepto.getText();
			
			String resultado = departamentos.cambiarAdministrador(admin, ndepto);
			print(resultado);
			
			habilitarBotones(true);
		}
	}
		
	ActionListener updateClockAction = new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
			Date date = new Date();

			/* Adicionar la fecha al textfield */
			String fecha = formatoFecha.format(date);
			tfDate.setText(fecha);
		}
	};

	public static void main(String args[]){
		new DepartamentosIUG();
	}
}
