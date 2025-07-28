package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Usuario;

public class DetalleUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtContrasenia;
	Usuario selected = null;
	private JTextField txtRol;
	private JTextField txtCedula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleUsuario dialog = new DetalleUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetalleUsuario(Usuario aux) {
		selected = aux;
		setTitle("Detalle usuario");
		setBounds(100, 100, 558, 263);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 515, 169);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setBounds(20, 35, 113, 14);
		panel.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setText("U-1");
		txtCodigo.setEnabled(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(98, 32, 127, 20);
		panel.add(txtCodigo);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(243, 32, 113, 14);
		panel.add(label_1);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(366, 29, 127, 20);
		panel.add(txtNombre);
		
		JLabel label_2 = new JLabel("Contrase\u00F1a:");
		label_2.setBounds(20, 79, 127, 14);
		panel.add(label_2);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setEnabled(false);
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(98, 76, 127, 20);
		panel.add(txtContrasenia);
		
		JLabel label_4 = new JLabel("Rol:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(260, 76, 96, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("C\u00E9dula m\u00E9dico:");
		label_5.setBounds(271, 126, 123, 14);
		panel.add(label_5);
		
		txtRol = new JTextField();
		txtRol.setEnabled(false);
		txtRol.setBounds(368, 76, 125, 20);
		panel.add(txtRol);
		txtRol.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setEnabled(false);
		txtCedula.setBounds(366, 123, 127, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadUsuario();
		
	}

	private void loadUsuario() {
		if(selected != null) {
			txtCodigo.setText(selected.getCodigo());
			txtNombre.setText(selected.getNombre());
			txtContrasenia.setText(selected.getContrasena());
			txtRol.setText(selected.getRol());
			/*if("Médico".equalsIgnoreCase(selected.getRol())){
				txtCedula.setText(selected.getMedicoRelacionado().getCedula());
			}*/
			if ("Médico".equalsIgnoreCase(selected.getRol())) {
			    if (selected.getMedicoRelacionado() != null) {
			        txtCedula.setText(selected.getMedicoRelacionado().getCedula());
			    } else {
			        JOptionPane.showMessageDialog(null, "El médico relacionado no se encuentra", "Error", JOptionPane.ERROR_MESSAGE);
			    }
		}
		
	}
   }
}
