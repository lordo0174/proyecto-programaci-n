package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionBD.ConexionMySQL;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class ventanaRealizarCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static ConexionMySQL ConexionPrincipal = new ConexionMySQL("root", "", "productos");
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaRealizarCompra frame = new ventanaRealizarCompra();
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
	public ventanaRealizarCompra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRealizarCompraDe = new JLabel("Realizar compra de productos");
		lblRealizarCompraDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRealizarCompraDe.setBounds(110, 11, 222, 35);
		contentPane.add(lblRealizarCompraDe);
		
		JButton atras = new JButton("Atrás");
		atras.setBounds(10, 227, 89, 23);
		contentPane.add(atras);
		atras.addActionListener(e -> {

			dispose();

			new ventanaCompradores().setVisible(true);

		});

	}
}
