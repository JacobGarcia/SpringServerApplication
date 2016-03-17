import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReporteGrupoAD{
	
    private Connection conexion = UniversidadAD.conexion;
    private Statement statement;
    private ReporteGrupoDP reporteGrupoDP;

	public String reporteDelGrupo(String claveProfesor, String claveCurso, String grupo){
        ResultSet result = null;
        String query = "";
        String respuesta = "";
        
        query = "SELECT Imparte.clave_profesor, Profesor.nombre, Profesor.ndepto, Imparte.clave_curso, Imparte.grupo, Alumno.matricula, Alumno.nombre, Alumno.carrera FROM Imparte JOIN Toma ON Imparte.clave_curso = Toma.clave_curso JOIN Profesor ON Imparte.clave_profesor = Profesor.clave JOIN Alumno ON Toma.matricula = Alumno.matricula WHERE Imparte.clave_curso = '"+claveCurso+"' AND Imparte.clave_profesor = '"+claveProfesor+"' AND Imparte.grupo = "+grupo;
        
        reporteGrupoDP = new ReporteGrupoDP();

        try{
            
            //1) Abrir la base de datos Universidad
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                reporteGrupoDP.setClaveProfesor(result.getString(1));
                reporteGrupoDP.setNombreProfesor(result.getString(2));
                reporteGrupoDP.setNdepto(result.getInt(3));
                reporteGrupoDP.setClaveCursoImparte(result.getString(4));
                reporteGrupoDP.setGrupoImparte(result.getInt(5));
                reporteGrupoDP.setMatricula(result.getString(6));
                reporteGrupoDP.setNombreAlumno(result.getString(7));
                reporteGrupoDP.setCarrera(result.getString(8));
                                
                respuesta = respuesta + reporteGrupoDP.toString() + "\n";
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
}
