import java.util.*;

public class TomaDP
{
	private String matricula;
	private String claveCurso;
	private int grupo;

	//Constructors
	public TomaDP()
	{
		this.matricula = "";
		this.claveCurso    = "";
		this.grupo = 0;
	}

	//String Tokenizer
	public TomaDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");

			this.matricula = st.nextToken();
			this.claveCurso    = st.nextToken();
			this.grupo = Integer.parseInt(st.nextToken());
	}

	//Accessors (Getters)
	public String getMatricula()
	{
		return this.matricula;
	}

	public String getClaveCurso()
	{
		return this.claveCurso;
	}


	//Mutators (Setters)

	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	public void setClaveCurso(String claveCurso)
	{
		this.claveCurso = claveCurso;
	}
	
	public void setGrupo(int grupo)
	{
		this.grupo = grupo;
	}

	//Final String

	public String toString()
	{
		return this.matricula+"_"+this.claveCurso + "_" + this.grupo; 
	}

	public String toSQLString(){
		return "'" + this.matricula + "','" + this.claveCurso+"'," + this.grupo; 
	}
}
