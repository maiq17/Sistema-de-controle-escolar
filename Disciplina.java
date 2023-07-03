package oi;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Disciplina {

    public JFrame frmDisciplina;
    private JTextField txtFldDIsciplina;
    private JTextField txtFldNomeDisciplinas;
    private JTextField textFldCurso;
    private JTable table;
    private DefaultTableModel model;
    private Connection conexao;
    private PreparedStatement mypst;
    private ResultSet myrs;
    private JTextField textFieldDescricao;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Disciplina window = new Disciplina();
                    window.frmDisciplina.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Disciplina() {
        initialize();
    }

    private void initialize() {
        frmDisciplina = new JFrame();
        frmDisciplina.setBounds(new Rectangle(0, 0, 800, 600));
        frmDisciplina.getContentPane().setBackground(SystemColor.activeCaption);
        frmDisciplina.setBackground(SystemColor.inactiveCaption);
        frmDisciplina.setForeground(SystemColor.inactiveCaption);
        frmDisciplina.setFont(new Font("Arial", Font.PLAIN, 14));
        frmDisciplina.setTitle("Cadastro de Disciplinas");
        frmDisciplina.setBounds(100, 100, 800, 500);
        frmDisciplina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmDisciplina.getContentPane().setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("ID Disciplina");
        model.addColumn("Disciplina");
        model.addColumn("Carga Horaria");
        model.addColumn("Descrição");
        

        txtFldDIsciplina = new JTextField();
        txtFldDIsciplina.setBounds(101, 69, 163, 35);
        txtFldDIsciplina.setColumns(10);
        frmDisciplina.getContentPane().add(txtFldDIsciplina);

        txtFldNomeDisciplinas = new JTextField();
        txtFldNomeDisciplinas.setBounds(101, 113, 163, 35);
        txtFldNomeDisciplinas.setColumns(10);
        frmDisciplina.getContentPane().add(txtFldNomeDisciplinas);

        textFldCurso = new JTextField();
        textFldCurso.setBounds(101, 158, 163, 35);
        textFldCurso.setColumns(10);
        frmDisciplina.getContentPane().add(textFldCurso);

        JLabel lblNewLabel = new JLabel("ID Disciplina:");
        lblNewLabel.setForeground(SystemColor.infoText);
        lblNewLabel.setBounds(12, 71, 85, 31);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        frmDisciplina.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Disciplina:");
        lblNewLabel_1.setBounds(29, 114, 65, 31);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
        frmDisciplina.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Carga Hora:");
        lblNewLabel_2.setBounds(19, 160, 81, 31);
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
        frmDisciplina.getContentPane().add(lblNewLabel_2);

        JButton btnInserir = new JButton("Salvar");
        btnInserir.setFont(new Font("Arial", Font.BOLD, 12));
        btnInserir.setBackground(new Color(255, 255, 255));
        btnInserir.setBounds(686, 69, 89, 35);
        btnInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conexao = oi.conexao.ConnectDb();
                if (conexao != null) {
                    String sql = "INSERT INTO disciplina(id_disciplina, nome_disciplina, carga_horaria, descricao_disciplina) VALUES (?, ?, ?, ?)";
                    try {
                        mypst = conexao.prepareStatement(sql);
                        mypst.setString(1, txtFldDIsciplina.getText());
                        mypst.setString(2, txtFldNomeDisciplinas.getText());
                        mypst.setString(3, textFldCurso.getText());
                        mypst.setString(4,textFieldDescricao.getText());
                        mypst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Disciplinas inserido com sucesso!");
                        updateTable();
                        txtFldDIsciplina.setText("");
                        txtFldNomeDisciplinas.setText("");
                        textFldCurso.setText("");
                        textFieldDescricao.setText("");
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    } finally {
                        try {
                            myrs.close();
                            mypst.close();
                            conexao.close();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                }
            }
        });
        frmDisciplina.getContentPane().add(btnInserir);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAlterar.setBackground(new Color(255, 255, 255));
        btnAlterar.setBounds(686, 115, 89, 35);
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conexao = oi.conexao.ConnectDb();
                if (conexao != null) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String id_disciplina = table.getValueAt(row, 0).toString();
                        String sql = "UPDATE disciplina SET nome_disciplina=?, carga_horaria=?, descricao_disciplina=? WHERE id_disciplina=?";
                        try {
                            mypst = conexao.prepareStatement(sql);
                            mypst.setString(1, txtFldNomeDisciplinas.getText());
                            mypst.setString(2, textFldCurso.getText());
                            mypst.setString(3, textFieldDescricao.getText());
                            mypst.setString(4, id_disciplina);
                            mypst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                            updateTable();
                            txtFldDIsciplina.setText("");
                            txtFldNomeDisciplinas.setText("");
                            textFldCurso.setText("");
                            textFieldDescricao.setText("");
                            
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        } finally {
                            try {
                                myrs.close();
                                mypst.close();
                                conexao.close();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma disciplina na tabela para realizar a alteração.");
                    }
                }
            }
        });
        frmDisciplina.getContentPane().add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 12));
        btnExcluir.setBackground(new Color(255, 255, 255));
        btnExcluir.setBounds(686, 158, 89, 35);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conexao = oi.conexao.ConnectDb();
                if (conexao != null) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String id_disciplina = table.getValueAt(row, 0).toString();
                        String sql = "DELETE FROM disciplina WHERE id_disciplina=?";
                        try {
                            mypst = conexao.prepareStatement(sql);
                            mypst.setString(1, id_disciplina);
                            mypst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Disciplina excluída com sucesso!");
                            updateTable();
                            txtFldDIsciplina.setText("");
                            txtFldNomeDisciplinas.setText("");
                            textFldCurso.setText("");
                            textFieldDescricao.setText("");
                            
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        } finally {
                            try {
                                myrs.close();
                                mypst.close();
                                conexao.close();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma disciplina na tabela para realizar a exclusão.");
                    }
                }
            }
        });
        frmDisciplina.getContentPane().add(btnExcluir);
        
        JButton btnConsulta = new JButton("Consultar");
        btnConsulta.setBackground(new Color(255, 255, 255));
		btnConsulta.setFont(new Font("Arial", Font.BOLD, 12));
		btnConsulta.setBounds(686, 203, 89, 35);
		frmDisciplina.getContentPane().add(btnConsulta);
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_disciplina = JOptionPane.showInputDialog(null, "Informe o id da disciplina:");
				if (id_disciplina != null && !id_disciplina.isEmpty()) {
					String sql = "SELECT id_disciplina, nome_disciplina, carga_horaria, descricao_disciplina FROM disciplina WHERE id_disciplina = ?";
					conexao = oi.conexao.ConnectDb();
					if (conexao != null) {
						try {
							mypst = conexao.prepareStatement(sql);
							mypst.setString(1, id_disciplina);
							myrs = mypst.executeQuery();
							if (myrs.next()) {
								JOptionPane.showMessageDialog(null, "Matrícula: " + myrs.getString("id_disciplina")
										+ "\nNome: " + myrs.getString("nome_disciplina")
										+ "\nCarga: " + myrs.getString("carga_horaria")
										+ "\nDescrição: " + myrs.getString("descricao_disciplina"));
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex);
						} finally {
							try {
								myrs.close();
								mypst.close();
								conexao.close();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							}
						}
					}
				}
			}
		});
        
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(new Color(0, 0, 0));
		btnLimpar.setBackground(new Color(255, 255, 255));
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 12));
		btnLimpar.setBounds(686, 248, 89, 35);
		frmDisciplina.getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        txtFldDIsciplina.setText("");
		        txtFldNomeDisciplinas.setText("");
		        textFldCurso.setText("");
		        textFieldDescricao.setText("");
		        
		    }
		});
        
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setBackground(new Color(255, 255, 255));
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 12));
        btnImprimir.setBounds(686, 292, 90, 35);
        frmDisciplina.getContentPane().add(btnImprimir);
        btnImprimir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    table.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao imprimir tabela: " + ex.getMessage());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(274, 67, 402, 342);
        frmDisciplina.getContentPane().add(scrollPane);
        
        JLabel lblNewLabel_11 = new JLabel("Todos os direitos são reservados a V.G.R.B.S Serviços ");
    	lblNewLabel_11.setForeground(SystemColor.infoText);
    	lblNewLabel_11.setToolTipText("");
    	lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel_11.setBackground(SystemColor.activeCaption);
    	lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	lblNewLabel_11.setBounds(10, 411, 765, 42);
    	frmDisciplina.getContentPane().add(lblNewLabel_11);

        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    txtFldDIsciplina.setText(table.getValueAt(row, 0).toString());
                    txtFldNomeDisciplinas.setText(table.getValueAt(row, 1).toString());
                    textFldCurso.setText(table.getValueAt(row, 2).toString());
                    textFieldDescricao.setText(table.getValueAt(row, 3).toString());
                }
            }
        });
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_11_1 = new JLabel("Cadastro de Disciplinas");
        lblNewLabel_11_1.setToolTipText("");
        lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11_1.setForeground(SystemColor.infoText);
        lblNewLabel_11_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_11_1.setBackground(SystemColor.activeCaption);
        lblNewLabel_11_1.setBounds(0, 10, 785, 42);
        frmDisciplina.getContentPane().add(lblNewLabel_11_1);
        
        JLabel lblNewLabel_2_1 = new JLabel("Descrição:");
        lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel_2_1.setBounds(29, 205, 71, 31);
        frmDisciplina.getContentPane().add(lblNewLabel_2_1);
        
        textFieldDescricao = new JTextField();
        textFieldDescricao.setColumns(10);
        textFieldDescricao.setBounds(101, 203, 163, 35);
        frmDisciplina.getContentPane().add(textFieldDescricao);
        updateTable();
    }
    	
    private void updateTable() {
        conexao = oi.conexao.ConnectDb();
        if (conexao != null) {
            String sql = "SELECT * FROM disciplina";
            try {
                mypst = conexao.prepareStatement(sql);
                myrs = mypst.executeQuery();

                model.setRowCount(0); // Limpar tabela antes de atualizar

                while (myrs.next()) {
                    String id_disciplina = myrs.getString("id_disciplina");
                    String nome = myrs.getString("nome_disciplina");
                    String carga = myrs.getString("carga_horaria");
                    String descricao = myrs.getString("descricao_disciplina");
                    
                    model.addRow(new Object[]{id_disciplina, nome, carga,descricao});
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            } finally {
                try {
                    myrs.close();
                    mypst.close();
                    conexao.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
 }
