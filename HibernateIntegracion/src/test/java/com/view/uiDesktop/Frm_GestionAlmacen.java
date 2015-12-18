package com.view.uiDesktop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hibernateintegracion.controllers.AlmacenesController;
import com.hibernateintegracion.domain.Almacenes;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Frm_GestionAlmacen extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTable table;
	
	ApplicationContext context = new GenericXmlApplicationContext("appContext.xml");
	AlmacenesController almacenesController = context.getBean("almacenesController", AlmacenesController.class);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_GestionAlmacen frame = new Frm_GestionAlmacen();
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
	public Frm_GestionAlmacen() {
		setResizable(false);
		setTitle("Gesti\u00F3n de Almacenes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCodigo.setBounds(20, 13, 77, 17);
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCodigo.setBounds(107, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(20, 38, 77, 17);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBounds(20, 58, 226, 20);
		contentPane.add(txtNombre);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin.setBounds(256, 37, 77, 17);
		contentPane.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(256, 58, 226, 20);
		contentPane.add(txtDireccion);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelfono.setBounds(20, 89, 77, 17);
		contentPane.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(107, 87, 139, 20);
		contentPane.add(txtTelefono);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 186, 462, 256);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		final DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int f = table.getSelectedRow();
				txtCodigo.setText(table.getValueAt(f, 0).toString());
				txtNombre.setText(table.getValueAt(f, 1).toString());
				txtDireccion.setText(table.getValueAt(f, 2).toString());
				txtTelefono.setText(table.getValueAt(f, 3).toString());
				
			}
		});
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Direcci�n");
		modelo.addColumn("Tel�fono");
		table.setModel(modelo);
		
		List<Almacenes> almacenes = almacenesController.obtenerAlmacenes();
		for(Almacenes al: almacenes){
			Object [] ob = new Object[4];
	        ob[0]=(al.getCodigo());
	        ob[1]=(al.getNombres());
	        ob[2]=(al.getDireccion());
	        ob[3]=(al.getTelefono());
	        modelo.addRow(ob);
	        ob = null;
		}
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		/* DECLARO LOS BOTONES EN BLOQUE PARA QUE NO ME SALGA ERROR AL NO ENCONTRAR EL BOTON PARA JUGAR
		 * CON LOS SETENABLED */
		final JButton btnGrabar = new JButton("Grabar");
		final JButton btnActualizar = new JButton("Actualizar");
		final JButton btnEliminar = new JButton("Eliminar");
		JButton btnCerrar = new JButton("Cerrar");
		final JButton btnNuevo = new JButton("Nuevo");
		
		btnGrabar.setEnabled(false);
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((txtCodigo.getText().length() != 0)
						&&(txtNombre.getText().length() != 0)
						&&(txtDireccion.getText().length() != 0)
						&&(txtTelefono.getText().length() != 0)){
					
					/* Llamo a mi m�todo insertar y le paso como par�metro 
					 * un objeto almacen que viene de otro m�todo que lo carga de
					 * las cajas de texto existentes*/
					almacenesController.insertar(objAlmacen()); 
					
					/* Creo un objeto con la cantidad de elementos a utilizar
					 * as� voy a�adi�ndolos a mi jTable*/
					Object [] ob = new Object[4];
			        ob[0]=(txtCodigo.getText());
			        ob[1]=(txtNombre.getText());
			        ob[2]=(txtDireccion.getText());
			        ob[3]=(txtTelefono.getText());
			        modelo.addRow(ob);
			        ob = null;
					
					LimpiarTexto(); // Borro las cajas de texto para evitar errores.
					
					// JUEGO CON LOS SETENABLED
					btnGrabar.setEnabled(false);
					btnActualizar.setEnabled(true);
					btnEliminar.setEnabled(true);
					btnNuevo.setEnabled(true);
					txtCodigo.setEditable(false);
					table.setEnabled(true);
				}
			}
		});
		btnGrabar.setBounds(138, 118, 108, 27);
		contentPane.add(btnGrabar);
		
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText().length() != 0){
					almacenesController.cambiar(objAlmacen());
					
					// REMUEVE LA FILA DEL JTABLE
					int f = table.getSelectedRow();
					modelo.removeRow(f);
					
					// A�ADE LA FILA CON NUEVOS CAMPOS
					Object [] ob = new Object[4];
			        ob[0]=(txtCodigo.getText());
			        ob[1]=(txtNombre.getText());
			        ob[2]=(txtDireccion.getText());
			        ob[3]=(txtTelefono.getText());
			        modelo.addRow(ob);
			        ob = null;
					
					LimpiarTexto();						
				} else{
					JOptionPane.showMessageDialog(btnActualizar, "No se ha cargado un Almacen para actualizar...");
				}
			}
		});
		btnActualizar.setBounds(256, 118, 108, 27);
		contentPane.add(btnActualizar);
		
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText().length() != 0){
					almacenesController.eliminar(objAlmacen());
					
					// REMUEVE LA FILA DEL JTABLE
					int f = table.getSelectedRow();
					modelo.removeRow(f);
										
					LimpiarTexto();						
				} else{
					JOptionPane.showMessageDialog(btnActualizar, "No se ha cargado un Almacen para eliminaci�n...");
				}
			}
		});
		btnEliminar.setBounds(374, 118, 108, 27);
		contentPane.add(btnEliminar);
		

		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(374, 148, 108, 27);
		contentPane.add(btnCerrar);
		

		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(false);
				txtCodigo.setEditable(true);
				LimpiarTexto();
				btnNuevo.setEnabled(false);
				btnActualizar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGrabar.setEnabled(true);
			}
		});
		btnNuevo.setBounds(20, 117, 108, 27);
		contentPane.add(btnNuevo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTexto();
				btnNuevo.setEnabled(true);
				btnActualizar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnGrabar.setEnabled(false);
				table.setEnabled(true);
			}
		});
		btnCancelar.setBounds(20, 148, 108, 27);
		contentPane.add(btnCancelar);
				
	}
	
	private Almacenes objAlmacen (){
		return new Almacenes(Integer.parseInt(txtCodigo.getText()),
				txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText());
	}
	
	private void LimpiarTexto() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
	}

}
