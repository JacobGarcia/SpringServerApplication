import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UniversidadAD{
	
    public static Connection conexion;

	public UniversidadAD(){
		try {
			System.out.println("JDBC Driver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/UniversidadSpring?user=root&&password=");
            
			System.out.println("Conexi��n exit��sa a la Base de Datos Universidad, Driver JDBC Tipo 4");
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("Error con el Driver JDBC: " + cnfe);
		}
		catch(InstantiationException ie){
			System.out.println("Error al crear la instancia: " + ie);
		}
		catch(IllegalAccessException iae){
			System.out.println("Error. Acceso ilegal a la base de datos: " + iae);
		}
		catch(SQLException sqle){
			System.out.println("Error SQL: " + sqle);
		
		}
	}
	
	public Connection getConnection(){
		return UniversidadAD.conexion;
	}
}
	
