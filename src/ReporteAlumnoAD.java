import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReporteAlumnoAD{
	
    private Connection conexion = UniversidadAD.conexion;
    private Statement statement;
    private ReporteAlumnoDP reporteAlumnoDP;

	public String reporteDelAlumno(String matricula){
        ResultSet result = null;
        String query = "";
        String respuesta = "";
        
        query = "SELECT Alumno.matricula, Alumno.nombre, Toma.clave_curso, Curso.nombre, Toma.grupo, Curso.semestre, Curso.ndepto FROM Alumno JOIN Toma ON Alumno.matricula = Toma.matricula JOIN Curso ON Toma.clave_curso = Curso.clave WHERE Alumno.matricula = '" + matricula + "'";
        
        reporteAlumnoDP = new ReporteAlumnoDP();

        try{
            
            //1) Abrir la base de datos Universidad
            statement = conexion.createStatement();
        
            //2) Procesar datos de la tabla resultante
            result = statement.executeQuery(query);
            
            while(result.next()){
                reporteAlumnoDP.setMatricula(result.getString(1));
                reporteAlumnoDP.setNombre(result.getString(2));
                reporteAlumnoDP.setCurso(result.getString(3));
                reporteAlumnoDP.setGrupo(result.getInt(5));
                reporteAlumnoDP.setNombreCurso(result.getString(4));
                reporteAlumnoDP.setSemestre(result.getInt(6));
                reporteAlumnoDP.setNdepto(result.getInt(7));
                
                respuesta = respuesta + reporteAlumnoDP.toString() + "\n";
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
