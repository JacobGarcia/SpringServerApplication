import java.util.*;

public class CursosDP
{
	private int ndepto;
	private String nombre;
	private String clave;
	private int semestre;
	
	//Constructors
	public CursosDP()
	{
		this.ndepto      		= 0;
		this.nombre    		    = "";
		this.clave = "";
		this.semestre		= 0;
	}
	
	//String Tokenizer
	public CursosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		this.clave		= st.nextToken();
		this.nombre 	= st.nextToken();
		this.semestre	= Integer.parseInt(st.nextToken());
		this.ndepto 	= Integer.parseInt(st.nextToken());
	}

	//Accessors (Getters)
	public int getNdepto()
	{
		return this.ndepto;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public String getClave()
	{
		return this.clave;
	}
	
	public int getSemestre()
	{
		return this.semestre;
	}
	
	//Mutators(Setters)
	public void setNdepto(int ndepto)
	{
		this.ndepto = ndepto;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setClave(String clave)
	{
		this.clave = clave;
	}

	public void setSemestre(int semestre)
	{
		this.semestre = semestre;
	}
	
	//Final String
	public String toString()
	{
		return this.clave+"_"+this.nombre+"_"+this.semestre+"_"+this.ndepto;
	}
	
    public String toSQLString(){
        return "'" + this.clave + "','" + this.nombre+"',"+this.semestre+","+this.ndepto;
    }
}
