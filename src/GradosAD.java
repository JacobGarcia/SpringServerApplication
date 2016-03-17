import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GradosAD{
	private Connection conexion = UniversidadAD.conexion;
    private Statement statement;
	private GradosDP gradosDP;

	public String registrarGrado(String datos){
		String insertDepartamento = "";
        String respuesta = "";
        
        gradosDP = new GradosDP(datos);
        
        /* Crear String con instrucción SQL */
        insertDepartamento = "INSERT INTO Grado VALUES(" + gradosDP.toSQLString() + ");";
        
        try {
            //1) Abrir la base de datos Banco
            statement = conexion.createStatement();
            
            //2) Capturar datos en la tabla correspondiente
            statement.executeUpdate(insertDepartamento);
            
            //3) Cerrar la base de datos Banco
            statement.close();
            
            respuesta = "Datos: " + datos;
            System.out.println(conexion.nativeSQL(insertDepartamento));
        }
        catch(SQLException sqle){
            	System.out.println("Error: " + sqle);
            	if(sqle.getErrorCode() == 1062)
            		respuesta = "GRADO_DUPLICADO";
            	else if(sqle.getErrorCode() == 1452)
            		respuesta = "PROFESOR_NO_REGISTRADO";
            	else
            		respuesta = "DATOS_GRANDES";

        }
        return respuesta;
	}
	
	public String consultarGrados(){
        ResultSet result = null;
        String query = "";
        String respuesta = "";
        
        query = "SELECT * FROM Grado";
        
        gradosDP = new GradosDP();

        try{
            //1) Abrir la base de datos Universidad
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                gradosDP.setClave(result.getString(1));
                gradosDP.setGrado(result.getString(2));
                gradosDP.setDescripcion(result.getString(3));
                
                respuesta = respuesta + gradosDP.toString() + "\n";
            }
            
            if(respuesta.equals(""))
            	return "BD_VACIA";
            
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
	   
	   public String consultarPor(String tipoConsulta, String str){
	        ResultSet result = null;
	        String query = "";
	        String respuesta = "";
	        
	        if (tipoConsulta.equals("PROFESOR"))
	        	query = "SELECT * FROM Grado WHERE clave_profesor = '" + str.toString() + "'";
	        
	        gradosDP = new GradosDP();
	        try{
	            
	            //1) Abrir la base de datos Universidad
	            statement = conexion.createStatement();
	            
	            //2) Procesar datos de la tabla resultante
	            result = statement.executeQuery(query);
	            
	            while(result.next()){
		            gradosDP.setClave(result.getString(1));
		            gradosDP.setGrado(result.getString(2));
		            gradosDP.setDescripcion(result.getString(3));
		            
		            respuesta = respuesta + gradosDP.toString() + "\n";
	            }
	            
	            if(respuesta == "")
		          respuesta = "PROFESOR_NO_ENCONTRADO";
	            
	            //3) Cerra la base de datos banco
	            statement.close();
	            System.out.println(conexion.nativeSQL(query));
	        }
	        
	        catch(SQLException sqle){
	            System.out.println("Error: \n" + sqle);
	            respuesta = "No se pudo realizar la consulta";
	        }
	        
	        return respuesta;
	    }
	    
}
