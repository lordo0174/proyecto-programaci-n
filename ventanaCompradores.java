package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionBD.ConexionMySQL;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaCompradores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaCompradores frame = new ventanaCompradores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ventanaCompradores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaEditar editar = new ventanaEditar();
		        // La hacemos visible
		        editar.setVisible(true);
		        // La centramos respecto a la principal
		        editar.setLocationRelativeTo(null);
			}
		});
		btnNewButton.setBounds(345, 0, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Mercancía");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaMercancia mercancia = new ventanaMercancia();
		        // La hacemos visible
		        mercancia.setVisible(true);
		        // La centramos respecto a la principal
		        mercancia.setLocationRelativeTo(null);
			}
		});
		btnNewButton_1.setBounds(60, 60, 124, 75);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Compras");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaRealizarCompra compra = new ventanaRealizarCompra();
		        // La hacemos visible
		        compra.setVisible(true);
		        // La centramos respecto a la principal
		        compra.setLocationRelativeTo(null);
				
			}
		});
		btnNewButton_3.setBounds(95, 175, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Gastos");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaGastos gastos = new ventanaGastos();
		        // La hacemos visible
		        gastos.setVisible(true);
		        // La centramos respecto a la principal
		        gastos.setLocationRelativeTo(null);
			}
		});
		btnNewButton_4.setBounds(239, 175, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Proveedores");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaProveedores proveedores = new ventanaProveedores();
				proveedores.setVisible(true);
				proveedores.setLocationRelativeTo(null);
			}
		});
		btnNewButton_5.setBounds(239, 60, 124, 75);
		contentPane.add(btnNewButton_5);

	}
}
