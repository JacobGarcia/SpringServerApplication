/*
 * GradosIUG.java
 *
 *  Created on: 01/04/2015
 *		Project #2
 *      Authors: Mario Jacob García Navarro & Luis Arturo Mendoza Reyes. All Rights Reserved 2015.
 *		IN THIS PROJECT A "UNIVERSITY SYSTEM" WLL BE SIMULATED.
 *		WE WILL BE CREATING A DATA BASE & ADDING ELEMENTS TO IT. OTHER TASKS WILL BE DONE THROUGH.
 *		IT IS MAIN PURPOSE IS USE SQL METHODS IN ORDER TO UNDERSTAND HOW OPERATIONS OF 
 *		RELATIONAL ALGEBRA WORKS.
 */
 

import javax.swing.*;
import java.awt.event.*;

public class UniversidadGUI extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar mbProyecto;
	private JMenu mProfesores, mDepartamentos, mAlumnos, mCursos, mEntidades, mReportes, mSalir;
	private JMenuItem miRegistroProfesor, miFormacion, miRegistroDepartamento, miAsignar, miRegistroAlumno, miInscripcion, miRegistroCurso, miReporteAlumno, miReporteCurso, miReporteGrupo, miSalir;
	private JPanel panel;
	
	UniversidadAD universidadAD = new UniversidadAD();
	private GradosIUG grados = new GradosIUG();
	private DepartamentosIUG departamentos = new DepartamentosIUG();
	private CursosIUG cursos = new CursosIUG();
	private ProfesoresGUI profesor = new ProfesoresGUI();
	private AlumnosGUI alumno = new AlumnosGUI();
	private ImparteGUI imparte = new ImparteGUI();
	private TomaGUI toma = new TomaGUI();
	private ReporteAlumnoGUI reporteAlumno = new ReporteAlumnoGUI();
	private ReporteCursoGUI reporteCurso = new ReporteCursoGUI();
	private ReporteGrupoGUI reporteGrupo = new ReporteGrupoGUI();
	
	public UniversidadGUI()
	{
		super("Universidad Tecnológico");
		
		//Se Crean Atributos
		mbProyecto = new JMenuBar();
		
		mProfesores = new JMenu("Profesores");
		miRegistroProfesor = new JMenuItem("Registro de Profesores");
		miRegistroProfesor.addActionListener(this);
		
		miFormacion = new JMenuItem("Formación Académica");
		miFormacion.addActionListener(this);
		
		miAsignar = new JMenuItem("Asignación de Cursos");
		miAsignar.addActionListener(this);
					
	
		
		mDepartamentos = new JMenu("Departamentos");
		miRegistroDepartamento = new JMenuItem("Registro de Departamentos");
		miRegistroDepartamento.addActionListener(this);
		
		
		
		mAlumnos = new JMenu("Alumnos");
		miRegistroAlumno = new JMenuItem("Registro de Alumnos");
		miRegistroAlumno.addActionListener(this);
		
		miInscripcion = new JMenuItem("Inscripción de Cursos");
		miInscripcion.addActionListener(this);
		
		
		
		mCursos = new JMenu("Cursos");
		miRegistroCurso = new JMenuItem("Registro de Cursos");
		miRegistroCurso.addActionListener(this);

		miSalir = new JMenuItem("Salir");
		miSalir.addActionListener(this);
		
		mEntidades = new JMenu("Administración de Entidades");

		mReportes = new JMenu("Generación de Reportes");

		miReporteAlumno = new JMenuItem("Materias que Cursa un Alumno");
		miReporteAlumno.addActionListener(this);

		miReporteCurso = new JMenuItem("Alumnos que Llevan un Curso");
		miReporteCurso.addActionListener(this);

		miReporteGrupo = new JMenuItem("Lista del Grupo de un Profesor");
		miReporteGrupo.addActionListener(this);


		mSalir = new JMenu("Opciones");
		
		
		//Panel
		panel = new JPanel();
		
		//Adicionar Objetos al MenuBar, Menú "Profesores"
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
		
		System.out.println("Successfully rendered app");
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == miFormacion)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = grados.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}
		
		if(event.getSource() == miRegistroDepartamento)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = departamentos.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}
		
		if(event.getSource() == miRegistroCurso)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = cursos.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}
		
		if(event.getSource() == miRegistroProfesor)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = profesor.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}
		
		if(event.getSource() == miRegistroAlumno)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = alumno.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}

		if(event.getSource() == miAsignar)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = imparte.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}
		
		if(event.getSource() == miInscripcion)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = toma.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}

		if(event.getSource() == miReporteAlumno)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = reporteAlumno.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}

		if(event.getSource() == miReporteCurso)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = reporteCurso.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}

		if(event.getSource() == miReporteGrupo)
		{
			if(panel != null)
			{
				panel.setVisible(false);
			}
			panel = reporteGrupo.getPanel2();
			panel.setVisible(true);
			add(panel);
			setVisible(true);
		}
		
		if (event.getSource() == miSalir)
			System.exit(0);
	}
	
	public static void main(String args[])
	{
		UniversidadGUI proyecto = new UniversidadGUI();
		proyecto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
