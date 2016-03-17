import java.util.*;

public class ProfesoresDP
{
	private String nombre;
	private String sexo;
	private int salario;
	private String domicilio;
	private String fNacimiento;
	private int clavedepartamento;
	private String clave;

	//Constructors
	public ProfesoresDP()
	{
		this.nombre              = "";
		this.sexo                = "";
		this.salario             = 0;
		this.domicilio           = "";
		this.fNacimiento         = "";
		this.clavedepartamento   = 0;
		this.clave               = "";
	}

	//String Tokenizer
	public ProfesoresDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos, "_");
			this.clave               = st.nextToken();
			this.nombre              = st.nextToken();
			this.domicilio           = st.nextToken();
			this.salario             = Integer.parseInt(st.nextToken());
			this.fNacimiento         = st.nextToken();
			this.sexo              	 = st.nextToken();
			this.clavedepartamento   = Integer.parseInt(st.nextToken());

	}

	//Accessors (Getters)
	public String getNombre()
	{
		return this.nombre;
	}

	public String getSexo()
	{
		return this.sexo;
	}

	public int getSalario()
	{
		return this.salario;
	}

	public String getDomicilio()
	{
		return this.domicilio;
	}

	public String getFNacimiento()
	{
		return this.fNacimiento;
	}

	public int getClaveDepartamento()
	{
		return this.clavedepartamento;
	}

	public String getClave()
	{
		return this.clave;
	}

	//Mutators (Setters)

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}

	public void setSalario(int salario)
	{
		this.salario = salario;
	}

	public void setDomicilio(String domicilio)
	{
		this.domicilio = domicilio;
	}

	public void setFNacimiento(String fNacimiento)
	{
		this.fNacimiento = fNacimiento;
	}

	public void setClaveDepartamento(int clavedepartamento)
	{
		this.clavedepartamento = clavedepartamento;
	}

	public void setClave(String clave)
	{
		this.clave = clave;
	}

	//Final String

	public String toString()
	{
		return this.clave + "_" + this.nombre + "_" + this.domicilio + "_" + this.salario + "_" + this.fNacimiento + "_" + this.sexo + "_" + this.clavedepartamento;
	}

	public String toSQLString(){
		return "'" + this.clave + "','" + this.nombre + "','" + this.domicilio + "'," + this.salario + ",'" + this.fNacimiento + "','" + this.sexo + "','" + this.clavedepartamento + "'";
	}




}
