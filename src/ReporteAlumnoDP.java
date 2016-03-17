import java.util.*;

public class ReporteAlumnoDP
{
	// ALumno //
	private String matricula;
	private String nombre;
	private String curso;
	private int grupo;
	private String nombreCurso;
	private int    semestre;
	private int ndepto;
	
	//Constructors
	public ReporteAlumnoDP()
	{
		this.matricula     = "";
		this.nombre        = "";
		this.curso     = "";
		this.nombreCurso      = "";
		this.semestre          = 0;
		this.grupo         = 0;
	}
	
	//String Tokenizer
	public ReporteAlumnoDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		this.matricula     = st.nextToken();
		this.nombre        = st.nextToken();
		this.curso     = st.nextToken();
		this.nombreCurso      = st.nextToken();
		this.grupo         = Integer.parseInt(st.nextToken());
		this.semestre          = Integer.parseInt(st.nextToken());
		this.ndepto = Integer.parseInt(st.nextToken());
	}

	//Mutators(Setters)
	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setCurso(String curso)
	{
		this.curso = curso;
	}

	public void setNombreCurso(String nombreCurso)
	{
		this.nombreCurso = nombreCurso;
	}

	public void setSemestre(int semestre)
	{
		this.semestre = semestre;
	}

	public void setNdepto(int ndepto)
	{
		this.ndepto = ndepto;
	}

	public void setGrupo(int grupo)
	{
		this.grupo = grupo;
	}
	
	//Final String
	public String toString()
	{
		return this.matricula+"_"+this.nombre+"_"+this.curso+"_"+this.nombreCurso+"_"+this.grupo+"_"+this.semestre+"_"+this.ndepto;
	}
}
