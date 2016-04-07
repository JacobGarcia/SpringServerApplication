import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlumnosAD{
	
    private Connection conexion = UniversidadAD.conexion;
    private Statement statement;
	private AlumnosDP alumnosDP;

	public String registrarAlumno(String datos){
		String insertAlumno = "";
        String respuesta = "";
        
        alumnosDP = new AlumnosDP(datos);
        
        /* Crear String con instrucci��n SQL */

        insertAlumno = "INSERT INTO Alumno VALUES("+ alumnosDP.toSQLString()+");";
        System.out.println(insertAlumno);
        
        try {

            //1) Abrir la base de datos Banco
            statement = conexion.createStatement();
            
            //2) Capturar datos en la tabla correspondiente
            statement.executeUpdate(insertAlumno);
            
            //3) Cerrar la base de datos Banco
            statement.close();
            
            respuesta = "Datos: " + datos;
            respuesta= insertAlumno;
            System.out.println(conexion.nativeSQL(insertAlumno));
            
        }
        catch(SQLException sqle){
			System.out.println("Error: " + sqle);
			insertAlumno="ERROR - " + sqle;

        }
        return insertAlumno;
	}

    public String consultarAlumnos(){
        ResultSet result = null;
        String query = "";
        String respuesta = "";
        
        query = "SELECT * FROM Alumno";
        
        alumnosDP = new AlumnosDP();

        try{
            //1) Abrir la base de datos Universidad
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                alumnosDP.setMatricula(result.getString(1));
                alumnosDP.setNombre(result.getString(2));
                alumnosDP.setDomicilio(result.getString(3));
                alumnosDP.setTelefono(result.getString(4));
                alumnosDP.setCarrera(result.getString(5));
                alumnosDP.setPlan(result.getInt(6));
                
                respuesta = respuesta + alumnosDP.toString() + "*";
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
                query = "SELECT * FROM Alumno WHERE matricula = '" + str.toString() + "'";

            if (tipoConsulta.equals("CARRERA"))
                query = "SELECT * FROM Alumno WHERE carrera = '" + str.toString() + "'";
            
            alumnosDP = new AlumnosDP();
            
            try{
                
                //1) Abrir la base de datos Universidad
                statement = conexion.createStatement();
                
                //2) Procesar datos de la tabla resultante
                result = statement.executeQuery(query);
                
                while(result.next()){
                alumnosDP.setMatricula(result.getString(1));
                alumnosDP.setNombre(result.getString(2));
                alumnosDP.setDomicilio(result.getString(3));
                alumnosDP.setTelefono(result.getString(4));
                alumnosDP.setCarrera(result.getString(5));
                alumnosDP.setPlan(result.getInt(6));
                
                respuesta = respuesta + alumnosDP.toString() + "\n";
                }
                
                if(respuesta == ""){
                    if (tipoConsulta.equals("ALUMNO"))
                        respuesta = "ALUMNO_NO_ENCONTRADO";
                        
                    if (tipoConsulta.equals("CARRERA"))
                        respuesta = "CARRERA_NO_REGISTRADO";
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
