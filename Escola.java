package oi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class Escola {

	private JFrame frmCoordenador;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Escola window = new Escola();
					window.frmCoordenador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Escola() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCoordenador = new JFrame();
		frmCoordenador.setTitle("Cadastro de Coordenador");
		frmCoordenador.setBounds(100, 100, 450, 300);
		frmCoordenador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCoordenador.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(20, 39, 31, 14);
		frmCoordenador.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(61, 8, 145, 20);
		frmCoordenador.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblidCoordenador = new JLabel("Id");
		lblidCoordenador.setBounds(30, 11, 10, 14);
		frmCoordenador.getContentPane().add(lblidCoordenador);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 64, 46, 14);
		frmCoordenador.getContentPane().add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(20, 92, 46, 14);
		frmCoordenador.getContentPane().add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(61, 36, 145, 20);
		frmCoordenador.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(63, 89, 145, 20);
		frmCoordenador.getContentPane().add(textField_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(63, 61, 145, 20);
		frmCoordenador.getContentPane().add(textField_2);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(345, 60, 89, 23);
		frmCoordenador.getContentPane().add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(345, 88, 89, 23);
		frmCoordenador.getContentPane().add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(345, 122, 89, 23);
		frmCoordenador.getContentPane().add(btnConsultar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(345, 156, 89, 23);
		frmCoordenador.getContentPane().add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(345, 190, 89, 23);
		frmCoordenador.getContentPane().add(btnLimpar);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(345, 224, 89, 23);
		frmCoordenador.getContentPane().add(btnImprimir);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 139, 325, 111);
		frmCoordenador.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 305, 89);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Telefone", "Email"
			}
		));
		scrollPane.setViewportView(table);
	}
}
