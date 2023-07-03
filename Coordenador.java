package oi;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
public class Coordenador {

	public JFrame frmCoordenador;
	private JTextField txtFIdID;
	private JLabel lblnome_coordenador;
	private JLabel lblcel_coordenador;
	private JLabel lblemail_coordenador;
	private JTextField textFldnome_coordenador;
	private JTextField textFldTelefeno;
	private JTextField textFldemail_coordenador;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExculir;
	private JButton btnLimpar;
	private JButton btnImprimir;
	private JButton btnIncluir;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	Connection conexao = null;
	 
	//Armazena comando SQL
	PreparedStatement mypst = null;
	ResultSet myrs = null;
	
	 DefaultTableModel model = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Impede a edição de todas as células
	        }
	  };

	  
		 public void atualizar() {
				//Limpa a tabela
				model.setRowCount(0);
				//cria um objeto de conexão a partir da bilioteca importada do SQLite
				conexao = ConnectDb();
				if (conexao != null) {
					String sql = "SELECT id_coordenador,nome_coordenador_coordenador,cel_coordenador,email_coordenador from coordenacao";
					try {
						mypst = conexao.prepareStatement(sql);
						myrs = mypst.executeQuery();
						Object[] columnData = new Object[4];
						// Itera sobre o dataset recuperado pelo select e insere dados na tabela da interface
						while (myrs.next()) {
							columnData[0] = myrs.getString("id_coordenador");
							columnData[1] = myrs.getString("nome_coordenador");
							columnData[2] = myrs.getString("cel_coordenador");
							columnData[3] = myrs.getString("email_coordenador");
							
							model.addRow(columnData);
							//System.out.println("Sei lá!");
						}

					}
					catch (Exception e) {  
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
	private Connection ConnectDb() {
			// TODO Auto-generated method stub
			return null;
		}
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coordenador window = new Coordenador();
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
	public Coordenador() {
		initialize();
		Object col[] = {"id_coordenador ", "nome_coordenador", "cel_coordenador", "email_coordenador"};
    	model.setColumnIdentifiers(col);
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting()) {
                    // Ignora eventos intermediários enquanto a seleção está sendo alterada
                    return;
                }
                int row = table.getSelectedRow();
                if (row >= 0) {
                    // Obtém o valor da célula da primeira coluna da linha selecionada
                    Object nome_coordenador = table.getValueAt(row, 0);
                    
                    // Transfere os dados para os componentes de edição
                    txtFIdID.setText(table.getValueAt(row, 0).toString());
                    textFldnome_coordenador.setText(table.getValueAt(row, 1).toString());
                    textFldTelefeno.setText(table.getValueAt(row, 2).toString());
                    textFldemail_coordenador.setText(table.getValueAt(row, 3).toString());
                    System.out.println("Linha selecionada: " + nome_coordenador);
                    
                }
            }
        });
		
		
		// 03 - Atualiza a tabela para visualizar registros
	
		atualizar();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCoordenador =  new JFrame();
		frmCoordenador.setTitle("Cadastro de Coordenador ");
		frmCoordenador.getContentPane().setBackground(new Color(128, 128, 128));
		frmCoordenador.setBounds(100, 100, 533, 379);
		frmCoordenador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCoordenador.getContentPane().setLayout(null);
		
		JPanel pnlCamposTela = new JPanel();
		pnlCamposTela.setBackground(Color.GRAY);
		pnlCamposTela.setBounds(10, 0, 223, 155);
		frmCoordenador.getContentPane().add(pnlCamposTela);
		pnlCamposTela.setLayout(null);
		
		JLabel lblID = new JLabel("id_coordenador: ");
		lblID.setBounds(10, 49, 101, 14);
		pnlCamposTela.add(lblID);
		
		lblnome_coordenador = new JLabel("nome_coordenador: ");
		lblnome_coordenador.setBounds(10, 71, 68, 14);
		pnlCamposTela.add(lblnome_coordenador);
		
		lblcel_coordenador = new JLabel("cel_coordenador:");
		lblcel_coordenador.setBounds(10, 96, 68, 14);
		pnlCamposTela.add(lblcel_coordenador);
		
		lblemail_coordenador = new JLabel("email_coordenador:");
		lblemail_coordenador.setBounds(10, 121, 46, 14);
		pnlCamposTela.add(lblemail_coordenador);
		
		
		txtFIdID = new JTextField();
		txtFIdID.setBackground(new Color(192, 192, 192));
		txtFIdID.setBounds(103, 46, 115, 20);
		pnlCamposTela.add(txtFIdID);
		txtFIdID.setColumns(10);
		
		textFldnome_coordenador = new JTextField();
		textFldnome_coordenador.setBackground(new Color(192, 192, 192));
		textFldnome_coordenador.setColumns(10);
		textFldnome_coordenador.setBounds(103, 71, 115, 20);
		pnlCamposTela.add(textFldnome_coordenador);
		
		textFldTelefeno = new JTextField();
		textFldTelefeno.setBackground(new Color(192, 192, 192));
		textFldTelefeno.setColumns(10);
		textFldTelefeno.setBounds(103, 96, 115, 20);
		pnlCamposTela.add(textFldTelefeno);
		
		textFldemail_coordenador = new JTextField();
		textFldemail_coordenador.setBackground(new Color(192, 192, 192));
		textFldemail_coordenador.setColumns(10);
		textFldemail_coordenador.setBounds(103, 121, 115, 20);
		pnlCamposTela.add(textFldemail_coordenador);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringSQL = new String();
				stringSQL = "UPDATE coordenador SET  nome_coordenador = ?,cel_coordenador= ?, email_coordenador=?  WHERE id_coordenador = ?";
		
				//cria um objeto de conexão a partir da bilioteca importada do SQLite
				conexao = ConnectDb();
				if (conexao != null) {
					try {
						// Cria um objeto PreparedStatement com a instrução SQL
						PreparedStatement mypst = conexao.prepareStatement(stringSQL);
						// Define os valores para os parâmetros da instrução SQL
						mypst.setString(1, textFldnome_coordenador.getText());
						mypst.setString(2, textFldTelefeno.getText());
						mypst.setString(3, textFldemail_coordenador.getText());
						mypst.setString(4, txtFIdID.getText());
                        System.out.print(mypst);
						// Executa a instrução SQL de atualização
						mypst.executeUpdate();

						// Fecha o objeto PreparedStatement e a conexão com o banco de dados
						mypst.close();
						conexao.close();	
				        JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!");			
				       
					}
					catch (Exception eInclusao) {
						JOptionPane.showMessageDialog(null, eInclusao);
					}
				}				
				 atualizar();
			}
		});
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setBounds(418, 183, 89, 23);
		frmCoordenador.getContentPane().add(btnAlterar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				//cria um objeto de conexão a partir da bilioteca importada do SQLite
				conexao = ConnectDb();
				System.out.print(txtFIdID);
				if (!txtFIdID.getText().isEmpty()) {
					if (conexao != null) {
						String sql = "SELECT * FROM  coordenador WHERE id_coordenador = " + "'"+txtFIdID.getText()+ "'" ;
						System.out.print(sql);			
						try {
							mypst = conexao.prepareStatement(sql);
							myrs = mypst.executeQuery();
							Object[] columnData = new Object[4];
							// Itera sobre o dataset recuperado pelo select e insere dados na tabela da interface
							while (myrs.next()) {
								columnData[0] = myrs.getString("id_coordenador");
								columnData[1] = myrs.getString("nome_coordenador");
								columnData[2] = myrs.getString("cel_coordenador");
								columnData[3] = myrs.getString("email_coordenador");

								model.addRow(columnData);
								//System.out.println("Sei lá!");
							}
	
						}
						catch (Exception c) {  
							JOptionPane.showMessageDialog(null, c);
						}
				  }				  	
				}else if (!textFldnome_coordenador.getText().isEmpty()) {
					if (conexao != null) {
						String sql = "SELECT * FROM  coordenacao WHERE nome_coordenador = '"+ textFldnome_coordenador.getText()+"'" ;
						System.out.print(sql);			
						try {
							mypst = conexao.prepareStatement(sql);
							myrs = mypst.executeQuery();
							Object[] columnData = new Object[4];
							// Itera sobre o dataset recuperado pelo select e insere dados na tabela da interface
							while (myrs.next()) {
								columnData[0] = myrs.getString("id_coordenador");
								columnData[1] = myrs.getString("nome_coordenador");
								columnData[2] = myrs.getString("cel_coordenador");
								columnData[3] = myrs.getString("email_coordenador");

								model.addRow(columnData);
								//System.out.println("Sei lá!");
							}
	
						}
						catch (Exception c) {  
							JOptionPane.showMessageDialog(null, c);
						}
				 } 		
			  }else if (!textFldTelefeno.getText().isEmpty()) {
					if (conexao != null) {
						String sql = "SELECT * FROM  professor WHERE cel_coordenador = "+"'" + textFldTelefeno.getText() +"'";
						System.out.print(sql);			
						try {
							mypst = conexao.prepareStatement(sql);
							myrs = mypst.executeQuery();
							Object[] columnData = new Object[3];
							// Itera sobre o dataset recuperado pelo select e insere dados na tabela da interface
							while (myrs.next()) {
								columnData[0] = myrs.getString("id_coordenador");
								columnData[1] = myrs.getString("nome_coordenador");
								columnData[2] = myrs.getString("cel_coordenador");
								columnData[3] = myrs.getString("email_coordenador");

								model.addRow(columnData);
								//System.out.println("Sei lá!");
							}
	
						}
						catch (Exception c) {  
							JOptionPane.showMessageDialog(null, c);
						}
				 } 		
			 
			 }else if (!textFldTelefeno.getText().isEmpty()) {
				 if (conexao != null) {
						String sql = "SELECT * FROM  coordenador WHERE email_coordenador = "+"'" + textFldemail_coordenador.getText() +"'";
						System.out.print(sql);			
						try {
							mypst = conexao.prepareStatement(sql);
							myrs = mypst.executeQuery();
							Object[] columnData = new Object[3];
							// Itera sobre o dataset recuperado pelo select e insere dados na tabela da interface
							while (myrs.next()) {
								columnData[0] = myrs.getString("id_coordenador");
								columnData[1] = myrs.getString("nome_coordenador");
								columnData[2] = myrs.getString("cel_coordenador");
								columnData[3] = myrs.getString("email_coordenador");

								model.addRow(columnData);
								//System.out.println("Sei lá!");
							}
	
						}
						catch (Exception c) {  
							JOptionPane.showMessageDialog(null, c);
						}
				 } 		
			  }else {
				 atualizar();
			 }
			}
		});
		btnConsultar.setBackground(Color.WHITE);
		btnConsultar.setBounds(418, 215, 89, 23);
		frmCoordenador.getContentPane().add(btnConsultar);
		
		btnExculir = new JButton("Excluir");
		btnExculir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringSQL = new String();
				stringSQL = "DELETE FROM coordenador WHERE id_coordenador=?";

				//cria um objeto de conexão a partir da bilioteca importada do SQLite
				conexao = ConnectDb();
				if (conexao != null) {
					try {
						// Cria um objeto PreparedStatement com a instrução SQL
						PreparedStatement mypst = conexao.prepareStatement(stringSQL);

						// Define o valor para o parâmetro da instrução SQL
						mypst.setString(1, txtFIdID.getText());

						// Executa a instrução SQL de exclusão
						mypst.executeUpdate();

						// Fecha o objeto PreparedStatement e a conexão com o banco de dados
						mypst.close();
						conexao.close();
				        JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");			
				        
					}
					catch (Exception eInclusao) {
						JOptionPane.showMessageDialog(null, eInclusao);
					}
				}
				 atualizar();
			}
		});
		btnExculir.setBackground(Color.WHITE);
		btnExculir.setBounds(418, 246, 89, 23);
		frmCoordenador.getContentPane().add(btnExculir);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField temp = null;
				// Para cada componente existente no panel verifica se o componente
				// contém em seu nome_coordenador de classe a string "javax.swing.JTextField"
				// para checar se o componente é do tipo JTextField
				for (Component c:pnlCamposTela.getComponents()) {
					if (c.getClass().toString().contains("javax.swing.JTextField")) {
						System.out.println("Estou Aqui");
						temp=(JTextField)c;
						temp.setText(null);
					}
				}
			}
			
		});
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(418, 276, 89, 23);
		frmCoordenador.getContentPane().add(btnLimpar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBackground(Color.WHITE);
		btnImprimir.setBounds(418, 306, 89, 23);
		frmCoordenador.getContentPane().add(btnImprimir);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringSQL = new String();
				stringSQL = "INSERT INTO coordenacao (nome_coordenador, cel_coordenador, email_coordenador, id_coordenador) VALUES (";
				stringSQL = stringSQL + "'" + textFldnome_coordenador.getText() + "'" + ", " + "'" + textFldTelefeno.getText() + "'" + ", " + "'" + textFldemail_coordenador.getText() + "'" + ", " + "'" + txtFIdID.getText() + "'" + ")";
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
				        //updateTable();
					}
					catch (Exception eInclusao) {
						JOptionPane.showMessageDialog(null, eInclusao);
					}
				}
				
				atualizar();
			}
		});
		btnIncluir.setBackground(Color.WHITE);
		btnIncluir.setBounds(418, 153, 89, 23);
		frmCoordenador.getContentPane().add(btnIncluir);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 196, 394, 133);
		frmCoordenador.getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 374, 111);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(192, 192, 192));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"id_coordenador ", "nome_coordenador", "cel_coordenador", "email_coordenador"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(228, 11, 279, 133);
		frmCoordenador.getContentPane().add(panel_1);
	}
}

