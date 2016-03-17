import java.util.*;

public class ReporteGrupoDP
{
	// Imparte //
	private String claveProfesor;
	private String claveCursoImparte;
	private String nombreProfesor;

	//Toma//
	private String matricula;
	private String nombreAlumno;
	private String    carrera;
	private int ndepto;
	
	//Constructors
	public ReporteGrupoDP()
	{
		this.claveProfesor     = "";
		this.claveCursoImparte = "";
		this.matricula         = "";
		this.nombreAlumno    = "";
		this.carrera         = "";
		this.nombreProfesor		   = "";
		this.ndepto = 0;
		
	}
	
	//String Tokenizer
	public ReporteGrupoDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");

		this.claveProfesor     = st.nextToken();
		this.nombreProfesor 		   = st.nextToken();
		this.ndepto = Integer.parseInt(st.nextToken());
		this.claveCursoImparte = st.nextToken();
		this.matricula         = st.nextToken();
		this.nombreAlumno    = st.nextToken();
		this.carrera         = st.nextToken();
	}

	//Mutators(Setters)
	public void setClaveProfesor(String claveProfesor)
	{
		this.claveProfesor = claveProfesor;
	}

	public void setClaveCursoImparte(String claveCursoImparte)
	{
		this.claveCursoImparte = claveCursoImparte;
	}

	public void setGrupoImparte(int grupoImparte)
	{

	}
	
	public void setNdepto(int ndepto)
	{
		this.ndepto = ndepto;
	}


	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	public void setNombreAlumno(String nombreAlumno)
	{
		this.nombreAlumno = nombreAlumno;
	}

	public void setCarrera(String carrera)
	{
		this.carrera = carrera;
	}
	
	public void setNombreProfesor(String nombreProfesor)
	{
		this.nombreProfesor = nombreProfesor;
	}
	
	//Final String
	public String toString()
	{
		return this.claveProfesor+"_"+this.nombreProfesor+"_"+this.ndepto+"_" + this.claveCursoImparte + "_" +this.matricula+"_"+this.nombreAlumno+"_"+this.carrera;
	}
}
