package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ConexionBD.ConexionMySQL;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

public class ventanaEditar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	private JTextField contraActual;
	private JTextField contraNueva;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaEditar frame = new ventanaEditar();
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
	public ventanaEditar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//boton para volver atras
		JButton atras = new JButton("Atrás");
		atras.setBounds(10, 227, 89, 23);
		contentPane.add(atras);
		atras.addActionListener(e -> {
			
			dispose();

			new ventanaCompradores().setVisible(true);

		});
		
		JLabel lblNewLabel = new JLabel("CAMBIAR");
		lblNewLabel.setBounds(191, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña actual:");    // Aqui te dice la contraseña actual
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 113, 138, 39);
		contentPane.add(lblNewLabel_1);
		
		contraActual = new JTextField();
		contraActual.setBounds(195, 124, 86, 20);
		contentPane.add(contraActual);
		contraActual.setEditable(false);
		contraActual.setColumns(10);
		
		addWindowListener(new WindowAdapter()   //Es para que se ejecute cuando se abra la ventana automaticamente
		{
			@Override
			public void windowOpened(WindowEvent e)
			{
				mostrarActual();   //Es el metodo que queremos que se ejecute (el de abajo del todo)
			}
		});
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña nueva:");    // Aqui te dice la contraseña nueva
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(37, 158, 138, 39);
		contentPane.add(lblNewLabel_1_1);
		
		
		
		contraNueva = new JTextField();
		contraNueva.setColumns(10);
		contraNueva.setBounds(195, 169, 86, 20);
		contentPane.add(contraNueva);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(192, 205, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(e ->
		{
			actualizarPass();		//Implementamos una acción (metodo actualizarPass() más abajo) al botón.
		});
		
	}
	
	public void mostrarActual()
	{
		
		try
		{
			ventanaPrincipal.ConexionPrincipal.conectar();				// Volvemos a conectar a la BBDD ya que se desconectó previamente en ventanaPrincipal
			String query = "SELECT Password FROM buyers WHERE ID_Buyer = "+ventanaPrincipal.id_usuario;     	//Seleccionamos la contraseña del ID que se registro en ventanaPrincipal
			ResultSet resultado = ventanaPrincipal.ConexionPrincipal.ejecutarSelect(query);     //Muestra el resultado de la conexión de la BBDD y ejecuta el select
			
			if (resultado.next())
			{
				contraActual.setText(resultado.getString("Password"));   //Obtiene la contraseña y la muestra
			}
			ventanaPrincipal.ConexionPrincipal.desconectar();   //Se desconecta la BBDD
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	
	}
	
	
	public void actualizarPass()
	{
		String nuevaPass = contraNueva.getText();    //Obtiene el texto del campo contraseña nueva (En la ventana).
		try
		{
			if(!nuevaPass.isEmpty())	//Si el campo no está vacío:
			{
				ventanaPrincipal.ConexionPrincipal.conectar();		//Abrimos la conexión con la BBDD.
				String actualizacionPass = "UPDATE buyers SET Password = '"+nuevaPass+"' WHERE ID_Buyer="+ventanaPrincipal.id_usuario;		//Sentencia SQL para actualizar la contraseña
				if(ventanaPrincipal.ConexionPrincipal.ejecutarInsertDeleteUpdate(actualizacionPass)>0)		//Si la sentencia tiene más de 0 caracteres:
				{
					JOptionPane.showMessageDialog(this, "Contraseña actualizada.");		//Salta mensaje diciendo que ya está actualizada.
					contraActual.setText(nuevaPass);		//El texto del campo contraseña actual se reemplaza por la nueva.
					contraNueva.setText("");		//El texto del campo contraseña nueva se reinicia.
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Escribe una nueva contraseña.");
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error crítico: "+ex.getMessage());
		}
	}
}
