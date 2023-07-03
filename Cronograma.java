package oi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Cronograma {

	private JFrame frmCronograma;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cronograma window = new Cronograma();
					window.frmCronograma.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cronograma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCronograma = new JFrame();
		frmCronograma.setBounds(100, 100, 413, 358);
		frmCronograma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCronograma.getContentPane().setLayout(null);
		
		JLabel lblTurma = new JLabel("TURMA");
		lblTurma.setBounds(10, 26, 46, 14);
		frmCronograma.getContentPane().add(lblTurma);
		
		JLabel lblAluno = new JLabel("ALUNO");
		lblAluno.setBounds(10, 51, 46, 14);
		frmCronograma.getContentPane().add(lblAluno);
		
		JLabel lblCurso = new JLabel("CURSO");
		lblCurso.setBounds(10, 76, 46, 14);
		frmCronograma.getContentPane().add(lblCurso);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(66, 23, 111, 20);
		frmCronograma.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(66, 48, 111, 20);
		frmCronograma.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(66, 73, 111, 20);
		frmCronograma.getContentPane().add(textField_2);
		
		JLabel lblTurno = new JLabel("TURNO");
		lblTurno.setBounds(10, 101, 46, 14);
		frmCronograma.getContentPane().add(lblTurno);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(66, 98, 111, 20);
		frmCronograma.getContentPane().add(textField_3);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(305, 108, 89, 23);
		frmCronograma.getContentPane().add(btnLimpar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(76, 129, 89, 23);
		frmCronograma.getContentPane().add(btnPesquisar);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(305, 6, 89, 23);
		frmCronograma.getContentPane().add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(305, 40, 89, 23);
		frmCronograma.getContentPane().add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(305, 74, 89, 23);
		frmCronograma.getContentPane().add(btnExcluir);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(305, 142, 89, 23);
		frmCronograma.getContentPane().add(btnImprimir);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 176, 384, 144);
		frmCronograma.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 364, 122);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Segunda", "Ter\u00E7a", "Quarta", "Quinta", "Sexta", "S\u00E1bado"
			}
		));
		scrollPane.setViewportView(table);
	}
}
