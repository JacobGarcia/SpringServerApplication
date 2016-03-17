import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CursosAD{
	
    private Connection conexion = UniversidadAD.conexion;
    private Statement statement;
	private CursosDP cursosDP;
	
	public String registrarCurso(String datos){
		String insertCurso = "";
        String respuesta = "";
        
        cursosDP = new CursosDP(datos);
        
        /* Crear String con instrucción SQL */
        insertCurso = "INSERT INTO Curso VALUES(" + cursosDP.toSQLString() + ");";
        
        try {
            //1) Abrir la base de datos Banco
            statement = conexion.createStatement();
            
            //2) Capturar datos en la tabla correspondiente
            statement.executeUpdate(insertCurso);
            
            //3) Cerrar la base de datos Banco
            statement.close();
            
            respuesta = "Datos: " + datos;
            System.out.println(conexion.nativeSQL(insertCurso));
        }
        catch(SQLException sqle){
            	System.out.println("Error: " + sqle);
            	if(sqle.getErrorCode() == 1062)
            		respuesta = "CLAVE_DUPLICADA";
            	else if(sqle.getErrorCode() == 1452)
            		respuesta = "CURSO_NO_REGISTRADO";
            	else
            		respuesta = "DATOS_GRANDES";

        }
        return respuesta;
	}
	
	public String consultarCursos(){
        ResultSet result = null;
        String query = "";
        String respuesta = "";
        
        query = "SELECT * FROM Curso";
        
        cursosDP = new CursosDP();
        try{
            
            //1) Abrir la base de datos Universidad
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                cursosDP.setClave(result.getString(1));
                cursosDP.setNombre(result.getString(2));
                cursosDP.setSemestre(result.getInt(3));
                cursosDP.setNdepto(result.getInt(4));
                
                respuesta = respuesta + cursosDP.toString() + "\n";
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
	        
	        if (tipoConsulta.equals("CLAVE"))
	        	query = "SELECT * FROM Curso WHERE clave = '" + str.toString() + "'";
	        
	        if (tipoConsulta.equals("SEMESTRE"))
	        	query = "SELECT * FROM Curso WHERE semestre = '" + str.toString() + "'";
	        
	        if (tipoConsulta.equals("DEPARTAMENTO"))
	      	    query = "SELECT * FROM Curso WHERE ndepto = '" + str.toString() + "'";
	        
	        cursosDP = new CursosDP();
	        try{
	            
	            //1) Abrir la base de datos Universidad
	            statement = conexion.createStatement();
	            
	            //2) Procesar datos de la tabla resultante
	            result = statement.executeQuery(query);
	            
	            while(result.next()){
	                cursosDP.setClave(result.getString(1));
	                cursosDP.setNombre(result.getString(2));
	                cursosDP.setSemestre(result.getInt(3));
	                cursosDP.setNdepto(result.getInt(4));
	                
	                respuesta = respuesta + cursosDP.toString() + "\n";
	            }
	            
	            if(respuesta == ""){
		            if (tipoConsulta.equals("CLAVE"))
		                respuesta = "CLAVE_NO_REGISTRADA";
		                
		            if (tipoConsulta.equals("SEMESTRE"))
		                respuesta = "SEMESTRE_NO_REGISTRADO";
		            
		            if (tipoConsulta.equals("DEPARTAMENTO"))    
		                respuesta = "DEPARTAMENTO_NO_ENCONTRADO";
	            }
	            
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
