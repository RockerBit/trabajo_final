import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class NuevoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	public boolean verifyFields() {
		String nombre = textField.getText();
		String apellido = textField_1.getText();
		String usuario = textField_2.getText();
		String correo = textField_3.getText();
		String telefono = textField_4.getText();
		String contrasena1 = String.valueOf(passwordField.getPassword());
		String contrasena2 = String.valueOf(passwordField_1.getPassword());
		
		if(nombre.trim().equals("") || apellido.trim().equals("") || usuario.trim().equals("") || correo.trim().equals("") || telefono.trim().equals("") || contrasena1.trim().equals("") || contrasena2.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Ninguno de los campos puede estar vacio");
			return false;
		}
		else if(contrasena1.equals(contrasena2)) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Contrasena no coincide");
			return false;
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoUsuario frame = new NuevoUsuario();
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
	public NuevoUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNombre.setBounds(35, 43, 100, 27);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Verdana", Font.BOLD, 15));
		lblApellido.setBounds(35, 93, 100, 24);
		contentPane.add(lblApellido);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 15));
		lblUsuario.setBounds(35, 141, 100, 24);
		contentPane.add(lblUsuario);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCorreo.setBounds(35, 190, 100, 24);
		contentPane.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Tefefono");
		lblTelefono.setFont(new Font("Verdana", Font.BOLD, 15));
		lblTelefono.setBounds(35, 239, 100, 24);
		contentPane.add(lblTelefono);
		
		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setFont(new Font("Verdana", Font.BOLD, 15));
		lblContrasena.setBounds(35, 287, 100, 24);
		contentPane.add(lblContrasena);
		
		JLabel lblRepetirContrasena = new JLabel("Repetir ");
		lblRepetirContrasena.setFont(new Font("Verdana", Font.BOLD, 15));
		lblRepetirContrasena.setBounds(35, 324, 74, 25);
		contentPane.add(lblRepetirContrasena);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				String apellido = textField_1.getText();
				String usuario = textField_2.getText();
				String correo = textField_3.getText();
				String telefono = textField_4.getText();
				String contrasena1 = String.valueOf(passwordField.getPassword());
				String contrasena2 = String.valueOf(passwordField_1.getPassword());
				
				if(verifyFields()) {
						PreparedStatement ps;
						String insertarRegistroQuery = "INSERT INTO usuarios (nombre,apellido,usuario,correo_electronico,numero_telefono,contrasena) VALUES(?,?,?,?,?,?)";
						try {
							ps = Conexion.getConnection().prepareStatement(insertarRegistroQuery);
							ps.setString(1, nombre);
							ps.setString(2, apellido);
							ps.setString(3, usuario);
							ps.setString(4, correo);
							ps.setString(5, telefono);
							ps.setString(6, contrasena1);
							
							if(ps.executeUpdate() != 0) {
								textField.setText("");
								textField_1.setText("");
								textField_2.setText("");
								textField_3.setText("");
								textField_4.setText("");
								passwordField.setText("");
								passwordField_1.setText("");
								JOptionPane.showMessageDialog(null, "Tu usuario fue creado");
								//Usuario Tusuario = new Usuario();
								//Tusuario.setVisible(true);
								//Tusuario.setLocationRelativeTo(null);
							}else {
								JOptionPane.showMessageDialog(null, "Hubo un error. Verifica tu informacion.");
							}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
				}
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\salvar.png"));
		btnNewButton.setBounds(79, 389, 64, 64);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
			}
		});
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\limpiar.png"));
		btnNewButton_1.setBounds(230, 384, 64, 64);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(183, 43, 140, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(183, 91, 140, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(183, 138, 140, 27);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(183, 187, 140, 27);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(183, 236, 140, 27);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contrasena");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBounds(35, 343, 118, 20);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 284, 133, 27);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(183, 329, 133, 27);
		contentPane.add(passwordField_1);
	}
}
