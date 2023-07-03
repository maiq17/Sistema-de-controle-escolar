package oi;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import java.awt.EventQueue;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;

public class Disponibilidade_professor {

	public JFrame frDisponiblidade;
	private JTextField txtFldData;
	private JTextField txtFldTurno;
	private JTextField txtFldInicio;
	private JTable table;
	private String textoSelecionado;
    public int id_Professor;
    private Connection conexao;

	
	PreparedStatement mypst = null;
	ResultSet myrs = null;
	
	@SuppressWarnings("serial")
	DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Impede a edição de todas as células
        }
    };
	private JTextField textFim;
	

	
	 // Atualiza a tabela que exibe os registros
		public void updateTable() {
			//Limpa a tabela
			model.setRowCount(0);
			//cria um objeto de conexão a partir da bilioteca importada do SQLite
			conexao = oi.conexao.ConnectDb();
            if (conexao != null) {
				String sql = "SELECT id_professor,data_disponibilidade,turno_disponibilidade,hora_inicio,hora_fim from disponibilidade_professor";
				try {
					mypst = conexao.prepareStatement(sql);
					myrs = mypst.executeQuery();
					Object[] columnData = new Object[5];
					// Itera sobre o dataset recuperado pelo select e insere dados na tabela da interface
					while (myrs.next()) {
						columnData[0] = myrs.getString("id_professor");
						columnData[1] = myrs.getString("data_disponibilidade");
						columnData[2] = myrs.getString("turno_disponibilidade");
						columnData[3] = myrs.getString("hora_inicio");
						columnData[4] = myrs.getString("hora_fim");
			
						model.addRow(columnData);
						//System.out.println("Sei lá!");
					}

				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
		public int getID() {
			conexao = oi.conexao.ConnectDb();
			    String sql = "SELECT id_professor FROM professor WHERE nome_professor = '"+textoSelecionado+"'";
			    int id_Professor = 0; // Variável para armazenar o resultado

			    try {
			        mypst = conexao.prepareStatement(sql);
			        myrs = mypst.executeQuery();

			        if (myrs.next()) {
			            id_Professor = myrs.getInt("id_Professor");
			            System.out.println("ID do Professor: " + id_Professor);
			        } else {
			            // Registro não encontrado
			            System.out.println("Professor não encontrado");
			        }
			    } catch (Exception c) {
			        JOptionPane.showMessageDialog(null, c);
			    } 
			  return id_Professor;
			} 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Disponibilidade_professor window = new Disponibilidade_professor();
					window.frDisponiblidade.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Disponibilidade_professor() {
		initialize();
		Object col[] = {"ID Professor", "Data", "Turno", "Hora/Inicio", "Hora/Fim"};
    	model.setColumnIdentifiers(col);
		table.setModel(model);
		updateTable();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frDisponiblidade = new JFrame();
		frDisponiblidade.setTitle("Cadastro de Disponiblidade");
		frDisponiblidade.getContentPane().setBackground(new Color(128, 128, 255));
		frDisponiblidade.setBounds(100, 100, 629, 492);
		frDisponiblidade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frDisponiblidade.getContentPane().setLayout(null);
		
		JPanel tste = new JPanel();
		tste.setBackground(new Color(128, 128, 255));
		tste.setBounds(0, 0, 266, 193);
		frDisponiblidade.getContentPane().add(tste);
		tste.setLayout(null);
		updateTable();
		
		
		JLabel lblID = new JLabel("ID Professor:");
		lblID.setBounds(10, 11, 86, 14);
		tste.add(lblID);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 36, 106, 14);
		tste.add(lblData);
		
		JLabel lblTurno = new JLabel("Turno: ");
		lblTurno.setBounds(10, 74, 46, 14);
		tste.add(lblTurno);
		
		JLabel lblIncio = new JLabel("Hora inicio:");
		lblIncio.setBounds(10, 108, 72, 14);
		tste.add(lblIncio);;
		
		txtFldData = new JTextField();
		txtFldData.setBounds(129, 38, 86, 20);
		tste.add(txtFldData);
		txtFldData.setColumns(10);
		
		txtFldTurno = new JTextField();
		txtFldTurno.setBounds(129, 71, 86, 20);
		tste.add(txtFldTurno);
		txtFldTurno.setColumns(10);
		
		txtFldInicio = new JTextField();
		txtFldInicio.setBounds(129, 102, 86, 20);
		tste.add(txtFldInicio);
		txtFldInicio.setColumns(10);
		
		textFim = new JTextField();
		textFim.setColumns(10);
		textFim.setBounds(129, 133, 86, 20);
		tste.add(textFim);
		
		JLabel lblFim = new JLabel("Hora Fim:");
		lblFim.setBounds(10, 136, 72, 14);
		tste.add(lblFim);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(129, 7, 106, 22);
		tste.add(comboBox);
		
		conexao = oi.conexao.ConnectDb(); // Cria uma conexão com o banco de dados (supondo que MyCtrlEscolarConnexion seja a classe responsável pela conexão)

	    if (conexao != null) {
	        String sql = "SELECT Nome_professor from professor";
	        try {
	            mypst = conexao.prepareStatement(sql);
	            myrs = mypst.executeQuery();
	            comboBox.addItem(null);
	            // Itera sobre os dados retornados pela consulta e adiciona ao JComboBox
	            while (myrs.next()) {
	               
	                String Professor = myrs.getString("Nome_professor");
	                

	                // Concatena as informações em uma única string para exibir no JComboBox

	                comboBox.addItem(Professor);	                
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }
	 // Declaração das variáveis de instância da classe
	    
	 // Declaração da variável de instância da classe

	    // Primeiro ActionListener
	    comboBox.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            textoSelecionado = (String) comboBox.getSelectedItem();
	            // Faça o que for necessário com o texto selecionado
	            System.out.println("Selecionado no primeiro ActionListener: " + textoSelecionado);
	        }
	    });
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String stringSQL = new String();
			stringSQL = "INSERT INTO disponibilidade_professor (id_professor,data_disponibilidade,turno_disponibilidade,hora_inicio,hora_fim) VALUES ("; 
			stringSQL = stringSQL + "'" + getID()  + "'" + ", " + "'" + txtFldData.getText() + "'" + ", " + "'" +
			txtFldTurno.getText()  +"'" + ", " + "'" + txtFldInicio.getText() + "'" +  ", " + "'" + textFim.getText() + "'"+")";
			System.out.println(stringSQL);
			
			
			conexao = oi.conexao.ConnectDb();
			if (conexao != null) {
				try {
					Statement stmt = conexao.createStatement();
			        // Executar a instrução SQL
		            stmt.executeUpdate(stringSQL);
			        // Fechar a conexão e liberar os recursos
			        stmt.close();
			        conexao.close();
			        JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso!");			
			        updateTable();
				}
				catch (Exception eInclusao) {
					JOptionPane.showMessageDialog(null, eInclusao);
				}
			
			}
			
			
		
		} 
	});
		
		btnIncluir.setBounds(484, 7, 89, 23);
		frDisponiblidade.getContentPane().add(btnIncluir);
		

		
		JPanel panel = new JPanel();
		panel.setBounds(23, 204, 394, 211);
		frDisponiblidade.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 11, 334, 190);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID Professor", "Data", "Turno", "Hora/Inicio", "Hora/Fim"
			}
		));
		scrollPane.setViewportView(table);
		updateTable();
		// 08 - Adiciona um listener de seleção de linha à tabela
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		            public void valueChanged(ListSelectionEvent event) {
		                if (event.getValueIsAdjusting()) {
		                    // Ignora eventos intermediários enquanto a seleção está sendo alterada
		                    return;
		                }
		                int row = table.getSelectedRow();
		                if (row >= 0) {
		                    // Obtém o valor da célula da primeira coluna da linha selecionada
		                    Object nome = table.getValueAt(row, 0);
		                    
		                    // Transfere os dados para os componentes de edição
		                    
		                    txtFldData.setText(table.getValueAt(row, 1).toString());
		                    txtFldTurno.setText(table.getValueAt(row, 2).toString());
		                    txtFldInicio.setText(table.getValueAt(row, 3).toString());
		                    textFim.setText(table.getValueAt(row, 4).toString());
		                    System.out.println("Linha selecionada: " + nome);
		                    
		                }
		            }
		        });
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Atualizar um registro existente no banco - Tabela Student
				// jtxtLP.getText()
				// UPDATE table SET column_1 = new_value_1,    column_2 = new_value_2 WHERE search_condition
				if (JOptionPane.showConfirmDialog(frDisponiblidade, "Confirma a atualização do registro?", "Sistema de Controle de Notas",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					String stringSQL = new String();
					stringSQL = "UPDATE disponibilidade_professor SET   hora_inicio = '"+txtFldInicio.getText()+"', hora_fim = '"+textFim.getText()+"'  WHERE id_professor = "+getID()+"";
					//stringSQL = "UPDATE disponibilidade_professor SET data_disponibilidade,turno_disponibilidade,hora_inicio,hora_fim WHERE id_professor = ?";
					//stringSQL = stringSQL + "'" + getID()  + "'" + ", " + "'" + txtFldData.getText() + "'" + ", " + "'" +
					//txtFldTurno.getText()  +"'" + ", " + "'" + txtFldInicio.getText() + "'" +  ", " + "'" + textFim.getText() + "'"+")";
					System.out.println(stringSQL);
					
					//cria um objeto de conexão a partir da bilioteca importada do SQLite
					conexao = oi.conexao.ConnectDb();
					if (conexao != null) {
						try {
							// Cria um objeto PreparedStatement com a instrução SQL
							PreparedStatement mypst = conexao.prepareStatement(stringSQL);
							// Define os valores para os parâmetros da instrução SQL
							//mypst.setString(1, txtFldData.getText());
							//mypst.setString(2, txtFldTurno.getText());
							//mypst.setString(3, txtFldInicio.getText());
							//mypst.setString(4, textFim.getText());
							//mypst.setString(5, getID());
							

							// Executa a instrução SQL de atualização
							mypst.executeUpdate();

							// Fecha o objeto PreparedStatement e a conexão com o banco de dados
							mypst.close();
							conexao.close();	
					        JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!");			
					        updateTable();
						}
						catch (Exception eInclusao) {
							JOptionPane.showMessageDialog(null, eInclusao);
						}
					}				
				};
			}
		});
		
		btnAlterar.setBounds(484, 36, 89, 22);
		frDisponiblidade.getContentPane().add(btnAlterar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(484, 65, 89, 23);
		frDisponiblidade.getContentPane().add(btnConsultar);
		
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				// Excluir um registro existente no banco. - Tabela Student
				// jtxtLP.getText()
				// DELETE FROM Student WHERE StudentID = ?
				if (JOptionPane.showConfirmDialog(frDisponiblidade, "Confirma a exclusão do registro?", "Sistema de Controle de Notas",
							JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {

					// Cria uma instrução SQL de exclusão
					String stringSQL = new String();
					stringSQL = "DELETE FROM disponibilidade_professor WHERE id_professor="+getID()+ " AND data_disponibilidade = "+  txtFldData.getText()+ " AND turno_disponibilidade=" + txtFldTurno.getText() ;
	                System.out.print(stringSQL);
					//cria um objeto de conexão a partir da bilioteca importada do SQLite
	                conexao = oi.conexao.ConnectDb();
					if (conexao != null) {
						try {
							// Cria um objeto PreparedStatement com a instrução SQL
							PreparedStatement mypst = conexao.prepareStatement(stringSQL);

							// Define o valor para o parâmetro da instrução SQL
							//mypst.setString(1, txtFldID.getText());

							// Executa a instrução SQL de exclusão
							mypst.executeUpdate();

							// Fecha o objeto PreparedStatement e a conexão com o banco de dados
							mypst.close();
							conexao.close();
					        JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");			
					        updateTable();
						}
						catch (Exception eInclusao) {
							JOptionPane.showMessageDialog(null, eInclusao);
						}
					}	
					
				};
				
			}
		});
		
		
		
		btnExcluir.setBounds(484, 99, 89, 23);
		frDisponiblidade.getContentPane().add(btnExcluir);
		
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField temp = null;
				
				// Para cada componente existente no panel verifica se o componente
				// contém em seu nome de classe a string "javax.swing.JTextField"
				// para checar se o componente é do tipo JTextField
				for (Component c:tste.getComponents()) {
					if (c.getClass().toString().contains("javax.swing.JTextField")) {
						System.out.println("Estou Aqui");
						temp=(JTextField)c;
						temp.setText(null);
						
					}
				}
				
			}
		});
		
		/*
		if (textField instanceof JTextField) {
            System.out.println("O componente é um JTextField!");
        } else {
            System.out.println("O componente não é um JTextField.");
        }
		*/
		btnLimpar.setBounds(484, 129, 89, 23);
		frDisponiblidade.getContentPane().add(btnLimpar);
		
		
		
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(484, 163, 89, 23);
		frDisponiblidade.getContentPane().add(btnImprimir);
		
		
	}
}
