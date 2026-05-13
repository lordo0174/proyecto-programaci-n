package Ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionBD.ConexionMySQL;

public class ventanaProveedores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static ConexionMySQL ConexionPrincipal = new ConexionMySQL("root", "", "productos");
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaProveedores frame = new ventanaProveedores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ventanaProveedores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 332);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("AÑADIR proveedor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(138, 53, 167, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Gestión de proveedores");
		lblNewLabel.setBounds(138, 11, 183, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel);
		
		JButton btnEditarProductos = new JButton("EDITAR proveedores");
		btnEditarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarProductos.setBounds(138, 107, 167, 40);
		contentPane.add(btnEditarProductos);
		
		JButton btnEliminarProductos = new JButton("ELIMINAR proveedores");
		btnEliminarProductos.setForeground(new Color(0, 0, 0));
		btnEliminarProductos.setBounds(138, 158, 167, 40);
		contentPane.add(btnEliminarProductos);
		
		JButton btnConsultarProductos = new JButton("CONSULTAR proveedores");
		btnConsultarProductos.setBounds(138, 209, 165, 40);
		contentPane.add(btnConsultarProductos);
		
		JButton atras = new JButton("Atrás");
		atras.setBounds(10, 227, 89, 23);
		contentPane.add(atras);
		atras.addActionListener(e -> {

			dispose();

			new ventanaCompradores().setVisible(true);

		});
	}

}
