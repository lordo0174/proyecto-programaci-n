package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionBD.ConexionMySQL;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ventanaMercancia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static ConexionMySQL ConexionPrincipal = new ConexionMySQL("root", "", "productos");
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaMercancia frame = new ventanaMercancia();
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
	public ventanaMercancia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("AÑADIR productos");
		btnNewButton.setBounds(140, 63, 150, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Gestión de mercancía");
		lblNewLabel.setBounds(140, 11, 160, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel);
		
		JButton btnEditarProductos = new JButton("EDITAR productos");
		btnEditarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarProductos.setBounds(140, 105, 150, 31);
		contentPane.add(btnEditarProductos);
		
		JButton btnEliminarProductos = new JButton("ELIMINAR productos");
		btnEliminarProductos.setForeground(new Color(0, 0, 0));
		btnEliminarProductos.setBounds(140, 147, 150, 31);
		contentPane.add(btnEliminarProductos);
		
		JButton btnConsultarProductos = new JButton("CONSULTAR productos");
		btnConsultarProductos.setBounds(140, 189, 150, 31);
		contentPane.add(btnConsultarProductos);
		
		JButton atras = new JButton("Atrás"); //Botón para ir hacia la ventana anterior
		atras.setBounds(10, 227, 89, 23);
		contentPane.add(atras);
		atras.addActionListener(e -> {

			dispose();

			new ventanaCompradores().setVisible(true);

		});

	}
}
