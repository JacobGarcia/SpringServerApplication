import java.util.*;

public class AlumnosDP
{
	private String matricula;
	private String nombre;
	private String domicilio;
	private String telefono;
	private String carrera;
	private int plan;

	//Constructors
	public AlumnosDP()
	{
		this.matricula = "";
		this.nombre    = "";
		this.domicilio = "";
		this.telefono  = "";
		this.carrera   = "";
		this.plan      = 0;
	}

	//String Tokenizer
	public AlumnosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");

			this.matricula = st.nextToken();
			this.nombre    = st.nextToken();
			this.domicilio = st.nextToken();
			this.telefono  = st.nextToken();
			this.carrera   = st.nextToken();
			this.plan      = Integer.parseInt(st.nextToken());
	}

	//Accessors (Getters)
	public String getMatricula()
	{
		return this.matricula;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public String getDomicilio()
	{
		return this.domicilio;
	}

	public String getTelefono()
	{
		return this.telefono;
	}

	public String getCarrera()
	{
		return this.carrera;
	}

	public int getPlan()
	{
		return this.plan;
	}

	//Mutators (Setters)

	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setDomicilio(String domicilio)
	{
		this.domicilio = domicilio;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public void setCarrera(String carrera)
	{
		this.carrera = carrera;
	}

	public void setPlan(int plan)
	{
		this.plan = plan;
	}


	//Final String

	public String toString()
	{
		return this.matricula+"_"+this.nombre+"_"+this.domicilio+"_"+this.telefono+"_"+this.carrera+"_"+this.plan;
	}

	public String toSQLString(){
		return "'" + this.matricula + "','" + this.nombre + "','" + this.domicilio + "','" + this.telefono + "','" + this.carrera + "'," + this.plan + "";
	}

}
