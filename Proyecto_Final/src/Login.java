import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textFieldUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Iniciar Sesion");
		btnNewButton.setBackground(new Color(51, 204, 204));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement st;
				ResultSet rs;
				
				String username = textFieldUsuario.getText();
				String password = String.valueOf(passwordField.getPassword());
				
				String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
				
				try {
					st = Conexion.getConnection().prepareStatement(query);
					
					st.setString(1, username);
					st.setString(2, password);
					rs = st.executeQuery();
					
					if(rs.next()) {
						Inicio ventana = new Inicio();
						ventana.setVisible(true);
						ventana.setLocationRelativeTo(null);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null,"Usuario o Contrasena incorrecta");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnNewButton.setBounds(115, 240, 108, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\iconologin.png"));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		lblNewLabel.setBounds(103, 21, 128, 128);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 20));
		passwordField.setBounds(70, 203, 194, 27);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\usuario.png"));
		lblNewLabel_2.setBounds(29, 153, 49, 41);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\password.png"));
		lblNewLabel_3.setBounds(29, 200, 49, 34);
		contentPane.add(lblNewLabel_3);
		
		JButton btnRegistrate = new JButton("Registrate");
		btnRegistrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro ventana = new Registro();
				ventana.setVisible(true);
				ventana.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnRegistrate.setBackground(new Color(51, 204, 204));
		btnRegistrate.setFont(new Font("Arial", Font.BOLD, 12));
		btnRegistrate.setBounds(115, 291, 108, 41);
		contentPane.add(btnRegistrate);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Verdana", Font.PLAIN, 15));
		textFieldUsuario.setBounds(70, 159, 194, 27);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
	}
}
