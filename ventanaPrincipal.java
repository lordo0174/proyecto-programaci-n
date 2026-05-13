package Ventanas;

import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ConexionBD.ConexionMySQL;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JLabel;

public class ventanaPrincipal extends JFrame
{
	//Creamos y abrimos conexion con BBDD
	public static ConexionMySQL ConexionPrincipal = new ConexionMySQL("root", "", "proyecto");

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; //Ventana
	private JTextField campoID; //Campo del ID
	private JPasswordField campoPass;
	
	public static int id_usuario =0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal frame = new ventanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ventanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Indicamos acciones del boton.
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Declaramos el texto que corresponde a cada campo.
				String ID = campoID.getText();
				String pass = campoPass.getText();
				//Comprobar que los campos no esten vacios, si lo estan muestra mensaje de error.
				if (ID.isEmpty() || pass.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Error: Alguno de los datos no han sido rellenado.");
					return;
				}
				
				//Codigo que muestra que sucede si los campos están rellenos. (Validación de ID).
				try
				{
					ConexionPrincipal.conectar(); //Conectamos con la base de datos.
					
					String consulta = "SELECT * FROM buyers WHERE ID_Buyer = '"+ID+"' AND Password = '"+pass+"'"; // Comprobamos si existe el ID y contraseña introducidos.
				
					ResultSet rs = ConexionPrincipal.ejecutarSelect(consulta); //Los resultados de la consulta se ejecutan
					
					if (rs.next()) // Si el resultado es correcto, se abre un mensaje que estás entrando y se abre la ventana de gestion de compradores.
					{
						JOptionPane.showMessageDialog(null, "Entrando al sistema.");
						
						id_usuario=rs.getInt("ID_Buyer");
											
						ventanaCompradores gestiones= new ventanaCompradores();
						gestiones.setVisible(true);
						ConexionPrincipal.desconectar();
						dispose(); // La ventana principal se esconde detrás.
					}
					else // Si no funciona da un mensaje de error.
					{
						JOptionPane.showMessageDialog(null, "ID o Contraseña son incorrectos.");
					}
				}
				catch(Exception ex)
				{
				    ex.printStackTrace(); // Esto imprimirá el error real en la consola de Eclipse
				    JOptionPane.showMessageDialog(null, "Error crítico: " + ex.getMessage());
				}
			}
		});
		
		
		btnNewButton.setBounds(168, 189, 95, 23);
		contentPane.add(btnNewButton);
		
		
		
		campoID = new JTextField();
		campoID.setColumns(10);
		campoID.setBounds(242, 92, 117, 20);
		contentPane.add(campoID);
		
		Label label_1 = new Label("Introduzca su ID:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_1.setBounds(54, 92, 117, 22);
		contentPane.add(label_1);
		
		campoPass = new JPasswordField();
		campoPass.setBounds(242, 144, 117, 20);
		contentPane.add(campoPass);
		
		Label label_1_1 = new Label("Introduzca su contraseña:");
		label_1_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_1_1.setBounds(54, 142, 162, 22);
		contentPane.add(label_1_1);
		
		JLabel lblNewLabel = new JLabel("Gestión de Almacén");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(126, 0, 205, 70);
		contentPane.add(lblNewLabel);
		
		
	
	}
}
