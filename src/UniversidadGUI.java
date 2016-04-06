/*
 * GradosIUG.java
 *
 *  Created on: 01/04/2015
 *		Project #2
 *      Authors: Mario Jacob Garc��a Navarro & Luis Arturo Mendoza Reyes. All Rights Reserved 2015.
 *		IN THIS PROJECT A "UNIVERSITY SYSTEM" WLL BE SIMULATED.
 *		WE WILL BE CREATING A DATA BASE & ADDING ELEMENTS TO IT. OTHER TASKS WILL BE DONE THROUGH.
 *		IT IS MAIN PURPOSE IS USE SQL METHODS IN ORDER TO UNDERSTAND HOW OPERATIONS OF 
 *		RELATIONAL ALGEBRA WORKS.
 */
 
/*	Document name: UniversidadGUI*/
import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UniversidadGUI extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar mbProyecto;
	private JMenu mProfesores, mDepartamentos, mAlumnos, mCursos, mEntidades, mReportes, mSalir;
	private JMenuItem miRegistroProfesor, miFormacion, miRegistroDepartamento, miAsignar, miRegistroAlumno, miInscripcion, miRegistroCurso, miReporteAlumno, miReporteCurso, miReporteGrupo, miSalir;
	
	public static UniversidadAD universidadAD = new UniversidadAD();

	static ServerSocket socket1;
	protected final static int port = 19999;
	static Socket connection;

	static boolean first;
	static StringBuffer process;
	
    private static ProfesoresAD profesoresAD = new ProfesoresAD();
    private static AlumnosAD alumnosAD = new AlumnosAD();
	public UniversidadGUI()
	{
		super("Universidad Tecnol��gico");
		
		//Se Crean Atributos
		mbProyecto = new JMenuBar();
		
		mProfesores = new JMenu("Profesores");
		miRegistroProfesor = new JMenuItem("Registro de Profesores");
		miRegistroProfesor.addActionListener(this);
		
		miFormacion = new JMenuItem("Formaci��n Acad��mica");
		miFormacion.addActionListener(this);
		
		miAsignar = new JMenuItem("Asignaci��n de Cursos");
		miAsignar.addActionListener(this);
					
	
		
		mDepartamentos = new JMenu("Departamentos");
		miRegistroDepartamento = new JMenuItem("Registro de Departamentos");
		miRegistroDepartamento.addActionListener(this);
		
		
		
		mAlumnos = new JMenu("Alumnos");
		miRegistroAlumno = new JMenuItem("Registro de Alumnos");
		miRegistroAlumno.addActionListener(this);
		
		miInscripcion = new JMenuItem("Inscripci��n de Cursos");
		miInscripcion.addActionListener(this);
		
		
		
		mCursos = new JMenu("Cursos");
		miRegistroCurso = new JMenuItem("Registro de Cursos");
		miRegistroCurso.addActionListener(this);

		miSalir = new JMenuItem("Salir");
		miSalir.addActionListener(this);
		
		mEntidades = new JMenu("Administraci��n de Entidades");

		mReportes = new JMenu("Generaci��n de Reportes");

		miReporteAlumno = new JMenuItem("Materias que Cursa un Alumno");
		miReporteAlumno.addActionListener(this);

		miReporteCurso = new JMenuItem("Alumnos que Llevan un Curso");
		miReporteCurso.addActionListener(this);

		miReporteGrupo = new JMenuItem("Lista del Grupo de un Profesor");
		miReporteGrupo.addActionListener(this);


		mSalir = new JMenu("Opciones");
		
		
		//Adicionar Objetos al MenuBar, Men�� "Profesores"
		mDepartamentos.add(miRegistroDepartamento);
		mDepartamentos.add(miAsignar);
		
		mAlumnos.add(miRegistroAlumno);
		mAlumnos.add(miInscripcion);
		
		mProfesores.add(miRegistroProfesor);
		mProfesores.add(miFormacion);
		mProfesores.add(miAsignar);
		
		mCursos.add(miRegistroCurso);

		mEntidades.add(mProfesores);
		mEntidades.add(mDepartamentos);
		mEntidades.add(mCursos);
		mEntidades.add(mAlumnos);

		mReportes.add(miReporteAlumno);
		mReportes.add(miReporteCurso);
		mReportes.add(miReporteGrupo);
		
		mSalir.add(miSalir);
		
		mbProyecto.add(mEntidades);
		mbProyecto.add(mReportes);
		mbProyecto.add(mSalir);
		
		//2) Visualizar Frame
		setJMenuBar(mbProyecto);
		setSize(820, 470);
		setVisible(true);
		
	}
	
	
	
	private static String map(String action, String data) {
		String response = "";
		switch (action) {
		case "Register":
			response = profesoresAD.registrarProfesor(data);
			break;
		case "Query":
			response = profesoresAD.consultarProfesores();
			break;
		case "Query By S.N.":
			String [] sn = data.split("_", 2);
			response = profesoresAD.consultarPor("PROFESOR", sn[0]);
			break;
		case "Query By Department":
			String [] department = data.split("_", 7);
			response = profesoresAD.consultarPor("DEPARTAMENTO", department[6]);
			break;
		case "Query By Gender":
			String [] gender = data.split("_", 7);
			response = profesoresAD.consultarPor("SEXO", gender[5]);
			break;
		case "RegisterStudent":
			response = alumnosAD.registrarAlumno(data);
			break;
		default:
			break;
		}
		
		return response;
	}
	
	public static void main(String args[]) 
	{
		UniversidadGUI proyecto = new UniversidadGUI();
		proyecto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		  try{
		      socket1 = new ServerSocket(port);
		      System.out.println("SingleSocketServer Initialized " + port);
		      int character;

		      while (true) {
		          connection = socket1.accept();
		          BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
		          InputStreamReader isr = new InputStreamReader(is);
		          process = new StringBuffer();
		          while((character = isr.read()) != 13) {
		              process.append((char)character);
		            }
		          String [] realData = process.toString().split("_", 2);

		          System.out.println("This is the action: " + realData[0] + " This is the data: " + realData[1]);
		          String response = UniversidadGUI.map(realData[0], realData[1]);
		          String returnCode = response + (char) 13;
		          BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
		          OutputStreamWriter osw = new OutputStreamWriter(os);
		          osw.write(returnCode);
		          osw.flush();
		       }
		      }
		  catch (IOException e) {}
		  try {
			   System.out.println("Closing connection");
		        connection.close();
		   }
		   catch (IOException e) {}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
