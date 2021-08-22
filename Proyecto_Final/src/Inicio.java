import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnUsuario = new JButton("");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				usuario.setVisible(true);
				usuario.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnUsuario.setBackground(UIManager.getColor("Button.background"));
		btnUsuario.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\usuarios.png"));
		btnUsuario.setBounds(20, 109, 223, 208);
		contentPane.add(btnUsuario);
		
		JButton btnProducto = new JButton("");
		btnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto producto = new Producto();
				producto.setVisible(true);
				producto.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnProducto.setBackground(UIManager.getColor("Button.background"));
		btnProducto.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\productos.png"));
		btnProducto.setBounds(253, 109, 223, 208);
		contentPane.add(btnProducto);
		
		JButton btnCerrarSesion = new JButton("");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnCerrarSesion.setBackground(UIManager.getColor("Button.background"));
		btnCerrarSesion.setIcon(new ImageIcon("C:\\Users\\thero\\eclipse-workspace\\Proyecto_Final\\imagen\\power.png"));
		btnCerrarSesion.setBounds(396, 10, 64, 64);
		contentPane.add(btnCerrarSesion);
	}

}
