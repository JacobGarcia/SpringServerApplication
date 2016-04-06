import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfesoresAD{

	private Connection conexion = UniversidadAD.conexion;
    private Statement statement;
	private ProfesoresDP profesoresDP;

	public String registrarProfesor(String datos){
		String insertProfesor="";
		String respuesta="";
		profesoresDP = new ProfesoresDP(datos);

		/*Crear String con instrucci��n SQL*/
		insertProfesor = "INSERT INTO Profesor VALUES(" + profesoresDP.toSQLString() + ");";

		try{

			//1) Abrir la base de datos Universidad
			statement = UniversidadAD.conexion.createStatement();

			//2) Capturar datos en la tabla correspondiente
			statement.executeUpdate(insertProfesor);

			//3) Cerrar la base de datos Banco
			statement.close();
			
			respuesta = "Datos: " + datos;
	        respuesta= insertProfesor;
			System.out.println(conexion.nativeSQL(insertProfesor));

		}
		catch(SQLException sqle){
			
			System.out.println(sqle.getErrorCode());
        	respuesta= "ERROR #"+sqle.getErrorCode();
				
		}
		return respuesta;
	}
	
	public String consultarProfesores(){
        ResultSet result = null;
        String query = "";
        String respuesta = "";
        
        query = "SELECT * FROM Profesor";
        
        profesoresDP = new ProfesoresDP();

        try{
            //1) Abrir la base de datos Universidad
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                profesoresDP.setClave(result.getString(1));
                profesoresDP.setNombre(result.getString(2));
                profesoresDP.setDomicilio(result.getString(3));
                profesoresDP.setSalario(result.getInt(4));
                profesoresDP.setFNacimiento(result.getString(5));
                profesoresDP.setSexo(result.getString(6));
                profesoresDP.setClaveDepartamento(result.getInt(7));
                
                respuesta = respuesta + profesoresDP.toString() + "*";
            }
            
            if(respuesta.equals(""))
            	return "ERROR - La base de datos est�� vac��a";
            
            //3) Cerra la base de datos banco
            statement.close();
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle){
            System.out.println("Error: \n" + sqle);
            return "ERROR - " + sqle;
        }
        
        return respuesta;
    }
    
    public String consultarPor(String tipoConsulta, String str){
	        ResultSet result = null;
	        String query = "";
	        String respuesta = "";
	        
	        if (tipoConsulta.equals("PROFESOR"))
	        	query = "SELECT * FROM Profesor WHERE clave = '" + str.toString() + "'";
	        	
	        if (tipoConsulta.equals("DEPARTAMENTO"))
	        	query = "SELECT * FROM Profesor WHERE ndepto = '" + str.toString() + "'";
	        	
	       	if (tipoConsulta.equals("SEXO"))
	        	query = "SELECT * FROM Profesor WHERE sexo = '" + str.toString() + "'";
	        
	        profesoresDP = new ProfesoresDP();
	        
	        try{
	            
	            //1) Abrir la base de datos Universidad
	            statement = conexion.createStatement();
	            
	            //2) Procesar datos de la tabla resultante
	            result = statement.executeQuery(query);
	            
	            while(result.next()){
                profesoresDP.setClave(result.getString(1));
                profesoresDP.setNombre(result.getString(2));
                profesoresDP.setDomicilio(result.getString(3));
                profesoresDP.setSalario(result.getInt(4));
                profesoresDP.setFNacimiento(result.getString(5));
                profesoresDP.setSexo(result.getString(6));
                profesoresDP.setClaveDepartamento(result.getInt(7));
                
                respuesta = respuesta + profesoresDP.toString() + "*";
	            }
	            
	            if(respuesta == ""){
	            	if (tipoConsulta.equals("PROFESOR"))
		          		respuesta = "PROFESOR_NO_ENCONTRADO";
		          		
		          	if (tipoConsulta.equals("DEPARTAMENTO"))
		          		respuesta = "DEPARTAMENTO_NO_REGISTRADO";
		          		
		          	if (tipoConsulta.equals("SEXO"))
		          		respuesta = "SEXO_NO_REGISTRADO";
		        }
	            
	            //3) Cerra la base de datos banco
	            statement.close();
	            System.out.println(conexion.nativeSQL(query));
	        }
	        
	        catch(SQLException sqle){
	            System.out.println("Error: \n" + sqle);
	            respuesta = "ERROR";
	        }
	        
	        return respuesta;
	    }
}
