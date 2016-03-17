import java.util.*;

public class ImparteDP
{
	private String claveProfesor;
	private String claveCurso;
	private int grupo;
	private String horario;

	//Constructors
	public ImparteDP()
	{
		this.claveProfesor = "";
		this.claveCurso    = "";
		this.grupo = 0;
		this.horario = "";
	}

	//String Tokenizer
	public ImparteDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");

			this.claveProfesor = st.nextToken();
			this.claveCurso    = st.nextToken();
			this.grupo = Integer.parseInt(st.nextToken());
			this.horario    = st.nextToken();
	}

	//Accessors (Getters)
	public String getClaveProfesor()
	{
		return this.claveProfesor;
	}

	public String getClaveCurso()
	{
		return this.claveCurso;
	}
	
	public int getGrupo()
	{
		return this.grupo;
	}
	
	public String getHorario()
	{
		return this.horario;
	}



	//Mutators (Setters)

	public void setClaveProfesor(String claveProfesor)
	{
		this.claveProfesor = claveProfesor;
	}

	public void setClaveCurso(String claveCurso)
	{
		this.claveCurso = claveCurso;
	}
	
	public void setGrupo(int grupo)
	{
		this.grupo = grupo;
	}

	public void setHorario(String horario)
	{
		this.horario = horario;
	}

	//Final String

	public String toString()
	{
		return this.claveProfesor+"_"+this.claveCurso+"_"+this.grupo+"_"+this.horario; 
	}

	public String toSQLString(){
		return "'" + this.claveProfesor + "','" + this.claveCurso+"'," + this.grupo + ",'" + this.horario + "'"; 
	}
}
