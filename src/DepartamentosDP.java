import java.util.*;

public class DepartamentosDP
{
	private int ndepto;
	private String nombre;
	private String claveAdministrador;
	private String fecha;
	
	//Constructors
	public DepartamentosDP()
	{
		this.ndepto      		= 0;
		this.nombre    		    = "";
		this.claveAdministrador = "";
		this.fecha		= "";
	}
	
	//String Tokenizer
	public DepartamentosDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
		
			this.ndepto 		= Integer.parseInt(st.nextToken());
			this.nombre 	= st.nextToken();
			this.claveAdministrador		= st.nextToken();
			this.fecha		= st.nextToken();
			
			if (this.claveAdministrador.equals("N/A"))
				this.claveAdministrador = "NULL";
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

	public String getClaveAdministrador()
	{
		return this.claveAdministrador;
	}
	
	public String getFecha()
	{
		return this.fecha;
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

	public void setClaveAdministrador(String claveAdministrador)
	{
		if (claveAdministrador == null)
			this.claveAdministrador = "N/A";
		else
			this.claveAdministrador = claveAdministrador;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}
	
	//Final String
	public String toString()
	{
		return this.ndepto+"_"+this.nombre+"_"+this.claveAdministrador+"_"+this.fecha;
	}
	
    public String toSQLString(){
   		if(this.claveAdministrador.equals("NULL"))
       	 	return "" + this.ndepto + ",'" + this.nombre+"',"+this.claveAdministrador+",'"+this.fecha+"'";
       else
       	 	return "" + this.ndepto + ",'" + this.nombre+"','"+this.claveAdministrador+"','"+this.fecha+"'";
    }
}
