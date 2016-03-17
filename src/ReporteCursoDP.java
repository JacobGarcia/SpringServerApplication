import java.util.*;

public class ReporteCursoDP
{
	//Toma//
	private String nombreCurso;
	private String claveCurso;

	// Curso //
	private String matricula;
	private String nombre;
	private String carrera;
	private int plan;
	
	//Constructors
	public ReporteCursoDP()
	{
		this.nombreCurso = "";
		this.claveCurso    = "";
		this.matricula         = "";
		this.nombre        = "";
		this.carrera      = "";
		this.plan       = 0;
	
	}
	
	//String Tokenizer
	public ReporteCursoDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		this.claveCurso    = st.nextToken();
		this.nombreCurso = st.nextToken();
		this.matricula         = st.nextToken();
		this.nombre        = st.nextToken();
		this.carrera      = st.nextToken();
		this.plan         = Integer.parseInt(st.nextToken());
	}

	//Mutators(Setters)
	public void setNombreCurso(String nombreCurso)
	{
		this.nombreCurso = nombreCurso;
	}

	public void setClaveCurso(String claveCurso)
	{
		this.claveCurso = claveCurso;
	}

	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
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
		return this.claveCurso+"_"+this.nombreCurso+"_"+this.matricula+"_"+this.nombre+"_"+this.carrera+"_"+this.plan;
	}
}
