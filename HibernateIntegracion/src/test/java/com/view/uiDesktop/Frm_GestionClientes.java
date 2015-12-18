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

import com.hibernateintegracion.controllers.ClienteController;
import com.hibernateintegracion.domain.Clientes;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Frm_GestionClientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JLabel lblCedulaCliente;
	private JTextField txtCedula;
	private JLabel lblNombres;
	private JTextField txtNombres;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JButton btnGrabar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnCerrar;
	private JPanel panel;
	private JTable table;
	
	ApplicationContext context = new GenericXmlApplicationContext("appContext.xml");
	ClienteController clienteController = context.getBean("clienteController", ClienteController.class);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_GestionClientes frame = new Frm_GestionClientes();
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
	public Frm_GestionClientes() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigoDeCliente = new JLabel("Codigo de Cliente");
		lblCodigoDeCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCodigoDeCliente.setBounds(10, 11, 127, 22);
		contentPane.add(lblCodigoDeCliente);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCodigo.setBounds(140, 11, 106, 22);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		lblCedulaCliente = new JLabel("No. de C\u00E9dula");
		lblCedulaCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCedulaCliente.setBounds(10, 44, 127, 22);
		contentPane.add(lblCedulaCliente);
		
		txtCedula = new JTextField();
		txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCedula.setColumns(10);
		txtCedula.setBounds(140, 44, 106, 22);
		contentPane.add(txtCedula);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombres.setBounds(10, 77, 110, 22);
		contentPane.add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombres.setColumns(10);
		txtNombres.setBounds(140, 77, 331, 22);
		contentPane.add(txtNombres);
		
		btnNuevo = new JButton("Nuevo");
		btnCancelar = new JButton("Cancelar");
		btnGrabar = new JButton("Grabar");
		btnGrabar.setEnabled(false);
		btnActualizar = new JButton("Actualizar");
		btnEliminar = new JButton("Eliminar");
		
		panel = new JPanel();
		panel.setBounds(147, 110, 324, 227);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		final DefaultTableModel modelo = new DefaultTableModel();
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int f = table.getSelectedRow();
				
				txtCodigo.setText(table.getValueAt(f, 0).toString());
				txtCedula.setText(table.getValueAt(f, 1).toString());
				txtNombres.setText(table.getValueAt(f, 2).toString());
			}
		});
		modelo.addColumn("Codigo");
		modelo.addColumn("Cedula");
		modelo.addColumn("Nombres");
		
		table.setModel(modelo);
		
		List<Clientes> cli = clienteController.consultarClientes();
		
		for(Clientes client: cli){
			Object [] ob = new Object[3];
	        ob[0]=(client.getCodigo());
	        ob[1]=(client.getCedula());
	        ob[2]=(client.getNombres());
	        modelo.addRow(ob);
	        ob = null;
		}
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTexto();
				btnNuevo.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGrabar.setEnabled(true);
				btnActualizar.setEnabled(false);
				txtCodigo.setEditable(true);
			}
		});
		btnNuevo.setBounds(10, 110, 127, 29);
		contentPane.add(btnNuevo);
				
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTexto();
				btnNuevo.setEnabled(true);
				btnGrabar.setEnabled(false);
				btnActualizar.setEnabled(true);
				btnEliminar.setEnabled(true);
				txtCodigo.setEditable(false);
			}
		});
		btnCancelar.setBounds(10, 151, 127, 29);
		contentPane.add(btnCancelar);
		
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((txtCodigo.getText().length() != 0)
						&&(txtCedula.getText().length() != 0)
						&&(txtNombres.getText().length() != 0)){
					
					clienteController.insertar(objCliente());
										
					btnNuevo.setEnabled(true);
					btnGrabar.setEnabled(false);
					btnActualizar.setEnabled(true);
					btnEliminar.setEnabled(true);
					txtCodigo.setEditable(false);
					
					Object [] ob = new Object[3];
			        ob[0]=(txtCodigo.getText());
			        ob[1]=(txtCedula.getText());
			        ob[2]=(txtNombres.getText());
			        modelo.addRow(ob);
			        ob = null;
			        
			        LimpiarTexto();
					
				} else{
					JOptionPane.showMessageDialog(btnGrabar, "Existen campos vac�os...");
				}
			}
		});
		btnGrabar.setBounds(10, 191, 127, 29);
		contentPane.add(btnGrabar);
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText().length() != 0){

					clienteController.cambiar(objCliente());
					
					// REMUEVE LA FILA DEL JTABLE
					int f = table.getSelectedRow();
					modelo.removeRow(f);
					
					// A�ADE LA FILA CON NUEVOS CAMPOS
					Object [] ob = new Object[3];
			        ob[0]=(txtCodigo.getText());
			        ob[1]=(txtCedula.getText());
			        ob[2]=(txtNombres.getText());
			        modelo.addRow(ob);
			        ob = null;
					
				}
			}
		});
		btnActualizar.setBounds(10, 231, 127, 29);
		contentPane.add(btnActualizar);
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCodigo.getText().length() != 0){

					clienteController.eliminar(objCliente());
					
					// REMUEVE LA FILA DEL JTABLE
					int f = table.getSelectedRow();
					modelo.removeRow(f);
			
				}
			}
		});
		btnEliminar.setBounds(10, 271, 127, 29);
		contentPane.add(btnEliminar);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(10, 308, 127, 29);
		contentPane.add(btnCerrar);

	}
	
	private Clientes objCliente() {
		return new Clientes(Integer.parseInt(txtCodigo.getText()), 
				txtCedula.getText(), txtNombres.getText());
	}
	
	private void LimpiarTexto() {
		txtCodigo.setText("");
		txtCedula.setText("");
		txtNombres.setText("");
	}
	
}
