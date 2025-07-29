package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.ClinicaMedica;
import visual.cita.ListadoCitas;
import visual.cita.RegistroCita;
import visual.consulta.RegistroConsulta;
import visual.enfermedad.ControlEnfermedades;
import visual.enfermedad.ListadoEnfermedades;
import visual.enfermedad.RegistroEnfermedad;
import visual.medico.ListadoMedicos;
import visual.medico.RegistroMedico;
import visual.paciente.ListadoPacientes;
import visual.paciente.RegistroPaciente;
import visual.usuario.ListadoUsuarios;
import visual.usuario.RegistroUsuario;
import visual.vacuna.ListadoVacunasGeneral;
import visual.vacuna.RegistroVacuna;
import visual.vacuna.Vacunar;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** 
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Cl\u00EDnica M\u00E9dica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = getToolkit().getScreenSize();
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Pacientes");
		menuBar.add(mnNewMenu);
		

		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listado");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListadoPacientes lp = new ListadoPacientes();
				lp.setModal(true);
				lp.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroPaciente rp = new RegistroPaciente(null);
				rp.setModal(true);
				rp.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		if(ClinicaMedica.getLoginUsuario().getIdRol() == 1 ) {
			mntmNewMenuItem_1.setEnabled(false);
		}

		JMenu mnNewMenu_1 = new JMenu("M\u00E9dicos");
		menuBar.add(mnNewMenu_1);
		
		if(ClinicaMedica.getLoginUsuario().getIdRol() == 2) {
			mnNewMenu_1.setEnabled(false);
		}
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listado");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListadoMedicos lm = new ListadoMedicos();
				lm.setModal(true);
				lm.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroMedico rm = new RegistroMedico(null);
				rm.setModal(true);
				rm.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Citas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Listado");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListadoCitas lc = new ListadoCitas();
				lc.setModal(true);
				lc.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Registrar");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroCita rc = new RegistroCita(null);
				rc.setModal(true);
				rc.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_3 = new JMenu("Enfermedades");
		menuBar.add(mnNewMenu_3);
		
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listado");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListadoEnfermedades le = new ListadoEnfermedades();
				le.setModal(true);
				le.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_6);
		
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Registrar");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroEnfermedad re = new RegistroEnfermedad(null);
				re.setModal(true);
				re.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Control");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlEnfermedades cf = new ControlEnfermedades();
				cf.setModal(true);
				cf.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_13);
		
		if(ClinicaMedica.getLoginUsuario().getIdRol() == 2) {
			mntmNewMenuItem_7.setEnabled(false);
		
		}
		
		JMenu mnNewMenu_4 = new JMenu("Consultas");
		menuBar.add(mnNewMenu_4);
		
		if(ClinicaMedica.getLoginUsuario().getIdRol() == 3) {
			mnNewMenu_4.setEnabled(false);
		
		}
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registro");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroConsulta rc = new RegistroConsulta(null);
				rc.setModal(true);
				rc.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_5 = new JMenu("Vacunas");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Listado");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListadoVacunasGeneral lv = new ListadoVacunasGeneral();
				lv.setModal(true);
				lv.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Registrar");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroVacuna rv = new RegistroVacuna(null);
				rv.setModal(true);
				rv.setVisible(true);
				
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_11);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Vacunar");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vacunar v =  new Vacunar();
				v.setModal(true);
				v.setVisible(true);
				
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_12);
		
		if(ClinicaMedica.getLoginUsuario().getIdRol() == 3) {
			mntmNewMenuItem_12.setEnabled(false);
		} else if(ClinicaMedica.getLoginUsuario().getIdRol()== 2) {
			mntmNewMenuItem_11.setEnabled(false);
			mntmNewMenuItem_10.setEnabled(false);
		}
		
		JMenu mnNewMenu_7 = new JMenu("Usuarios");
		menuBar.add(mnNewMenu_7);
		
		if(ClinicaMedica.getLoginUsuario().getIdRol() == 3 || ClinicaMedica.getLoginUsuario().getIdRol() == 2 ) {
			mnNewMenu_7.setEnabled(false);
		}
		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Listado");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListadoUsuarios lu = new ListadoUsuarios();
				lu.setModal(true);
				lu.setVisible(true);
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_15);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Registrar");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroUsuario ru = new RegistroUsuario(null);
				ru.setModal(true);
				ru.setVisible(true);
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_6 = new JMenu("Cerrar sesi\u00F3n");
		menuBar.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Cerrar sesi\u00F3n");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileOutputStream clinica2;
				ObjectOutputStream clinicalwrite;
				try {
				    clinica2 = new FileOutputStream("clinica.dat");
				    clinicalwrite = new ObjectOutputStream(clinica2);
				    clinicalwrite.writeObject(ClinicaMedica.getInstance());
				    clinicalwrite.writeObject(ClinicaMedica.getCodVacuna());
				    clinicalwrite.writeObject(ClinicaMedica.getCodPaciente());
				    clinicalwrite.writeObject(ClinicaMedica.getCodMedico());
				    clinicalwrite.writeObject(ClinicaMedica.getCodEnfermedad());
				    clinicalwrite.writeObject(ClinicaMedica.getCodCita());
				    clinicalwrite.writeObject(ClinicaMedica.getCodConsulta());
				    clinicalwrite.writeObject(ClinicaMedica.getCodUsuario());
				} catch (FileNotFoundException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				} catch (IOException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_14);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		setSize(dim.width, dim.height-45);
		setLocationRelativeTo(null);
	}
}
