import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TomaAD{

	private Connection conexion = UniversidadAD.conexion;
    private Statement statement;
	private TomaDP TomaDP;

	public String imparteCurso(String datos){
		String insertImparte="";
		String respuesta = "";

		TomaDP = new TomaDP(datos);

		/*Crear String con instrucción SQL*/
		insertImparte = "INSERT INTO Toma VALUES(" + TomaDP.toSQLString() + ");";

		try{

			//1) Abrir la base de datos Universidad
			statement = conexion.createStatement();

			//2) Capturar datos en la tabla correspondiente
			statement.executeUpdate(insertImparte);

			//3) Cerrar la base de datos Banco
			statement.close();

			respuesta = "Datos: " + datos;
            System.out.println(conexion.nativeSQL(insertImparte));
		}
		catch(SQLException sqle){
			System.out.println("Error: " + sqle);
				if(sqle.getErrorCode() == 1062)
            		respuesta = "CURSO_DUPLICADO";
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
        
        query = "SELECT * FROM Toma";
        
        TomaDP = new TomaDP();

        try{
            //1) Abrir la base de datos Universidad
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                TomaDP.setMatricula(result.getString(1));
                TomaDP.setClaveCurso(result.getString(2));
                TomaDP.setGrupo(result.getInt(3));
                
                respuesta = respuesta + TomaDP.toString() + "\n";
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
	        
	        if (tipoConsulta.equals("ALUMNO"))
	        	query = "SELECT * FROM Toma WHERE matricula = '" + str.toString() + "'";
	        	
	        if (tipoConsulta.equals("CURSO"))
	        	query = "SELECT * FROM Toma WHERE clave_curso = '" + str.toString() + "'";
	        
	        TomaDP = new TomaDP();
	        
	        try{
	            
	            //1) Abrir la base de datos Universidad
	            statement = conexion.createStatement();
	            
	            //2) Procesar datos de la tabla resultante
	            result = statement.executeQuery(query);
	            
	            while(result.next()){
                TomaDP.setMatricula(result.getString(1));
                TomaDP.setClaveCurso(result.getString(2));
                TomaDP.setGrupo(result.getInt(3));
                
                respuesta = respuesta + TomaDP.toString() + "\n";
	            }
	            
	            if(respuesta == ""){
	            	if (tipoConsulta.equals("ALUMNO"))
		          		respuesta = "ALUMNO_NO_ENCONTRADO";
		          		
		          	if (tipoConsulta.equals("CURSO"))
		          		respuesta = "CURSO_NO_ENCONTRADO";
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
