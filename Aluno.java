package oi;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

public class Aluno {

    public JFrame frmAluno;
    private JTextField txtFldMatricula;
    private JTextField txtFldNomeAluno;
    private JTextField textFldCpf;
    private JTextField txtFldEndereco;
    private JTable table;
    private DefaultTableModel model;
    private Connection conexao;
    private PreparedStatement mypst;
    private ResultSet myrs;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Aluno window = new Aluno();
                    window.frmAluno.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Aluno() {
        initialize();
    }

    private void initialize() {
        frmAluno = new JFrame();
        frmAluno.getContentPane().setBackground(SystemColor.activeCaption);
        frmAluno.setBackground(SystemColor.inactiveCaption);
        frmAluno.setForeground(SystemColor.inactiveCaption);
        frmAluno.setFont(new Font("Arial", Font.PLAIN, 14));
        frmAluno.setTitle("Cadastro de Alunos");
        frmAluno.setBounds(100, 100, 800, 500);
        frmAluno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAluno.getContentPane().setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("Nr. Matrícula");
        model.addColumn("Nome");
        model.addColumn("CPF");
        model.addColumn("Endereço");

        txtFldMatricula = new JTextField();
        txtFldMatricula.setBounds(101, 69, 163, 35);
        txtFldMatricula.setColumns(10);
        frmAluno.getContentPane().add(txtFldMatricula);

        txtFldNomeAluno = new JTextField();
        txtFldNomeAluno.setBounds(101, 113, 163, 35);
        txtFldNomeAluno.setColumns(10);
        frmAluno.getContentPane().add(txtFldNomeAluno);

        textFldCpf = new JTextField();
        textFldCpf.setBounds(101, 158, 163, 35);
        textFldCpf.setColumns(10);
        frmAluno.getContentPane().add(textFldCpf);

        txtFldEndereco = new JTextField();
        txtFldEndereco.setBounds(101, 203, 163, 35);
        txtFldEndereco.setColumns(10);
        frmAluno.getContentPane().add(txtFldEndereco);

        JLabel lblNewLabel = new JLabel("Nrº Matrícula:");
        lblNewLabel.setForeground(SystemColor.infoText);
        lblNewLabel.setBounds(10, 71, 89, 31);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        frmAluno.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nome:");
        lblNewLabel_1.setBounds(49, 123, 42, 14);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
        frmAluno.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("CPF:");
        lblNewLabel_2.setBounds(60, 168, 31, 14);
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
        frmAluno.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Endereço:");
        lblNewLabel_3.setBounds(29, 213, 70, 14);
        lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 13));
        frmAluno.getContentPane().add(lblNewLabel_3);

        JButton btnInserir = new JButton("Salvar");
        btnInserir.setFont(new Font("Arial", Font.BOLD, 12));
        btnInserir.setBackground(new Color(255, 255, 255));
        btnInserir.setBounds(686, 69, 89, 35);
        btnInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conexao = oi.conexao.ConnectDb();
                if (conexao != null) {
                    String sql = "INSERT INTO alunos(nr_matricula, nome_aluno, CPF, endereco_Aluno) VALUES (?, ?, ?, ?)";
                    try {
                        mypst = conexao.prepareStatement(sql);
                        mypst.setString(1, txtFldMatricula.getText());
                        mypst.setString(2, txtFldNomeAluno.getText());
                        mypst.setString(3, textFldCpf.getText());
                        mypst.setString(4, txtFldEndereco.getText());
                        mypst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!");
                        updateTable();
                        txtFldMatricula.setText("");
                        txtFldNomeAluno.setText("");
                        textFldCpf.setText("");
                        txtFldEndereco.setText("");
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
        frmAluno.getContentPane().add(btnInserir);

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
                        String matricula = table.getValueAt(row, 0).toString();
                        String sql = "UPDATE alunos SET nome_aluno=?, CPF=?, endereco_Aluno=? WHERE nr_matricula=?";
                        try {
                            mypst = conexao.prepareStatement(sql);
                            mypst.setString(1, txtFldNomeAluno.getText());
                            mypst.setString(2, textFldCpf.getText());
                            mypst.setString(3, txtFldEndereco.getText());
                            mypst.setString(4, matricula);
                            mypst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                            updateTable();
                            txtFldMatricula.setText("");
                            txtFldNomeAluno.setText("");
                            textFldCpf.setText("");
                            txtFldEndereco.setText("");
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
                        JOptionPane.showMessageDialog(null, "Selecione um aluno na tabela para realizar a alteração.");
                    }
                }
            }
        });
        frmAluno.getContentPane().add(btnAlterar);

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
                        String matricula = table.getValueAt(row, 0).toString();
                        String sql = "DELETE FROM alunos WHERE nr_matricula=?";
                        try {
                            mypst = conexao.prepareStatement(sql);
                            mypst.setString(1, matricula);
                            mypst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
                            updateTable();
                            txtFldMatricula.setText("");
                            txtFldNomeAluno.setText("");
                            textFldCpf.setText("");
                            txtFldEndereco.setText("");
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
                        JOptionPane.showMessageDialog(null, "Selecione um aluno na tabela para realizar a exclusão.");
                    }
                }
            }
        });
        frmAluno.getContentPane().add(btnExcluir);
        
        JButton btnConsulta = new JButton("Consultar");
        btnConsulta.setBackground(new Color(255, 255, 255));
		btnConsulta.setFont(new Font("Arial", Font.BOLD, 12));
		btnConsulta.setBounds(686, 203, 89, 35);
		frmAluno.getContentPane().add(btnConsulta);
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matricula = JOptionPane.showInputDialog(null, "Informe a matrícula do aluno:");
				if (matricula != null && !matricula.isEmpty()) {
					String sql = "SELECT nr_matricula, nome_aluno, CPF, endereco_aluno FROM alunos WHERE nr_matricula = ?";
					Connection conexao = oi.conexao.ConnectDb();
					if (conexao != null) {
						try {
							mypst = conexao.prepareStatement(sql);
							mypst.setString(1, matricula);
							myrs = mypst.executeQuery();
							if (myrs.next()) {
								JOptionPane.showMessageDialog(null, "Matrícula: " + myrs.getString("nr_matricula")
										+ "\nNome: " + myrs.getString("nome_aluno")
										+ "\nCPF: " + myrs.getString("CPF")
										+ "\nEndereço: " + myrs.getString("endereco_Aluno"));
							} else {
								JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
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
		frmAluno.getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        txtFldMatricula.setText("");
		        txtFldNomeAluno.setText("");
		        textFldCpf.setText("");
		        txtFldEndereco.setText("");
		    }
		});
        
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setBackground(new Color(255, 255, 255));
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 12));
        btnImprimir.setBounds(686, 292, 90, 35);
        frmAluno.getContentPane().add(btnImprimir);
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
        frmAluno.getContentPane().add(scrollPane);
        
        JLabel lblNewLabel_11 = new JLabel("Todos os direitos são reservados a V.G.R.B.S Serviços ");
    	lblNewLabel_11.setForeground(SystemColor.infoText);
    	lblNewLabel_11.setToolTipText("");
    	lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel_11.setBackground(SystemColor.activeCaption);
    	lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	lblNewLabel_11.setBounds(10, 411, 765, 42);
    	frmAluno.getContentPane().add(lblNewLabel_11);

        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    txtFldMatricula.setText(table.getValueAt(row, 0).toString());
                    txtFldNomeAluno.setText(table.getValueAt(row, 1).toString());
                    textFldCpf.setText(table.getValueAt(row, 2).toString());
                    txtFldEndereco.setText(table.getValueAt(row, 3).toString());
                }
            }
        });
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_11_1 = new JLabel("Cadastro de Alunos");
        lblNewLabel_11_1.setToolTipText("");
        lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11_1.setForeground(SystemColor.infoText);
        lblNewLabel_11_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_11_1.setBackground(SystemColor.activeCaption);
        lblNewLabel_11_1.setBounds(0, 10, 785, 42);
        frmAluno.getContentPane().add(lblNewLabel_11_1);
        updateTable();
    }
    	
    private void updateTable() {
        conexao = oi.conexao.ConnectDb();
        if (conexao != null) {
            String sql = "SELECT * FROM alunos";
            try {
                mypst = conexao.prepareStatement(sql);
                myrs = mypst.executeQuery();

                model.setRowCount(0); // Limpar tabela antes de atualizar

                while (myrs.next()) {
                    String matricula = myrs.getString("nr_matricula");
                    String nome = myrs.getString("nome_aluno");
                    String cpf = myrs.getString("CPF");
                    String endereco = myrs.getString("endereco_Aluno");
                    model.addRow(new Object[]{matricula, nome, cpf, endereco});
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

