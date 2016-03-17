import java.util.*;

public class GradosDP
{
	private String clave;
	private String grado;
	private String descripcion;
	
	//Constructors
	public GradosDP()
	{
		this.clave      		= "";
		this.grado    		    = "";
		this.descripcion = "";
	}
	
	//String Tokenizer
	public GradosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		
		this.clave 			= st.nextToken();
		this.grado 			= st.nextToken();
		this.descripcion	= st.nextToken();
	}
	
	//Accessors (Getters)
	public String getClave()
	{
		return this.clave;
	}

	public String getGrado()
	{
		return this.grado;
	}

	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	//Mutators(Setters)
	public void setClave(String clave)
	{
		this.clave = clave;
	}

	public void setGrado(String grado)
	{
		this.grado = grado;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	//Final String
	public String toString()
	{
		return this.clave+"_"+this.grado+"_"+this.descripcion+"_";
	}
	
    public String toSQLString(){
        return "'" + this.clave + "','" + this.grado+"','"+this.descripcion+"'";
    }
}
