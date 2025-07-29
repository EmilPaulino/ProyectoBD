package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import logico.ClinicaMedica;
import logico.Usuario;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnLogin;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;

	public static void main(String[] args) {
		FileInputStream clinica;
		FileOutputStream clinica2;
		ObjectInputStream clinicaRead;
		ObjectOutputStream clinicaWrite;

		try {
			clinica = new FileInputStream("clinica.dat");
			clinicaRead = new ObjectInputStream(clinica);
			ClinicaMedica temp = (ClinicaMedica) clinicaRead.readObject();
			ClinicaMedica.setCodVacuna((Integer) clinicaRead.readObject());
			ClinicaMedica.setCodPaciente((Integer) clinicaRead.readObject());
			ClinicaMedica.setCodMedico((Integer) clinicaRead.readObject());
			ClinicaMedica.setCodEnfermedad((Integer) clinicaRead.readObject());
			ClinicaMedica.setCodCita((Integer) clinicaRead.readObject());
			ClinicaMedica.setCodConsulta((Integer) clinicaRead.readObject());
			ClinicaMedica.setCodUsuario((Integer) clinicaRead.readObject());
			ClinicaMedica.setClinicaMedica(temp);
			clinica.close();
			clinicaRead.close();
		} catch (FileNotFoundException e) {
			try {
				clinica2 = new FileOutputStream("clinica.dat");
				clinicaWrite = new ObjectOutputStream(clinica2);
				Usuario aux = new Usuario("U-1", "admin", "admin",1,1);
				ClinicaMedica.getInstance().regUser(aux);
				clinicaWrite.writeObject(ClinicaMedica.getInstance());
				clinicaWrite.writeObject(ClinicaMedica.getCodVacuna());
				clinicaWrite.writeObject(ClinicaMedica.getCodPaciente());
				clinicaWrite.writeObject(ClinicaMedica.getCodMedico());
				clinicaWrite.writeObject(ClinicaMedica.getCodEnfermedad());
				clinicaWrite.writeObject(ClinicaMedica.getCodCita());
				clinicaWrite.writeObject(ClinicaMedica.getCodConsulta());
				clinicaWrite.writeObject(ClinicaMedica.getCodUsuario());
				clinica2.close();
				clinicaWrite.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Mostrar ventana de login después de cargar datos
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setTitle("Login");
		setBounds(100, 100, 465, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lbliniciarSesion = new JLabel("INICIAR SESIÓN");
		lbliniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lbliniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbliniciarSesion.setBounds(128, 32, 186, 14);
		contentPanel.add(lbliniciarSesion);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuario.setBounds(158, 81, 117, 14);
		contentPanel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(149, 106, 146, 20);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContrasena.setBounds(158, 158, 117, 14);
		contentPanel.add(lblContrasena);

		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(149, 183, 146, 20);
		contentPanel.add(txtContrasena);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				String contrasena = new String(txtContrasena.getPassword());

				if (ClinicaMedica.getInstance().confirmarLogin(usuario, contrasena)) {
					Principal frame = new Principal();
					dispose();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de inicio de sesión",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setActionCommand("OK");
		buttonPane.add(btnLogin);
		getRootPane().setDefaultButton(btnLogin);
	}
}
