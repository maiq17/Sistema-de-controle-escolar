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
public class Turma {

	
    public JFrame frmTurma;
    private JTextField txtFldID;
    private JTextField txtFldIdCurso;
    private JTextField textFldIdData;
    private JTextField txtFldIdturno;
    private JTable table;
    private DefaultTableModel model;
    private Connection conexao;
    private PreparedStatement mypst;
    private ResultSet myrs;
    private JTextField txtFldMatricula;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Turma window = new Turma();
                    window.frmTurma.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Turma() {
        initialize();
    }

    private void initialize() {
         frmTurma = new JFrame();
        frmTurma.getContentPane().setBounds(new Rectangle(0, 0, 800, 600));
        frmTurma.getContentPane().setBackground(SystemColor.activeCaption);
        frmTurma.setBackground(SystemColor.inactiveCaption);
        frmTurma.setForeground(SystemColor.inactiveCaption);
        frmTurma.setFont(new Font("Arial", Font.PLAIN, 14));
        frmTurma.setTitle("Cadastro de s");
        frmTurma.setBounds(100, 100, 800, 500);
        frmTurma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmTurma.getContentPane().setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("ID ");
        model.addColumn("ID Curso");
        model.addColumn("Data ");
        model.addColumn("turno");
        model.addColumn("Nrº Matricula");

        txtFldID = new JTextField();
        txtFldID.setBounds(101, 69, 163, 35);
        txtFldID.setColumns(10);
        frmTurma.getContentPane().add(txtFldID);

        txtFldIdCurso = new JTextField();
        txtFldIdCurso.setBounds(101, 113, 163, 35);
        txtFldIdCurso.setColumns(10);
        frmTurma.getContentPane().add(txtFldIdCurso);

        textFldIdData = new JTextField();
        textFldIdData.setBounds(101, 158, 163, 35);
        textFldIdData.setColumns(10);
        frmTurma.getContentPane().add(textFldIdData);

        txtFldIdturno = new JTextField();
        txtFldIdturno.setBounds(101, 203, 163, 35);
        txtFldIdturno.setColumns(10);
        frmTurma.getContentPane().add(txtFldIdturno);

        JLabel lblNewLabel = new JLabel("ID :");
        lblNewLabel.setForeground(SystemColor.infoText);
        lblNewLabel.setBounds(35, 71, 64, 31);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        frmTurma.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID Curso:");
        lblNewLabel_1.setBounds(40, 115, 58, 31);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
        frmTurma.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Data :");
        lblNewLabel_2.setBounds(18, 160, 80, 31);
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
        frmTurma.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Turno:");
        lblNewLabel_3.setBounds(54, 205, 42, 31);
        lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 13));
        frmTurma.getContentPane().add(lblNewLabel_3);

        JButton btnInserir = new JButton("Salvar");
        btnInserir.setFont(new Font("Arial", Font.BOLD, 12));
        btnInserir.setBackground(new Color(255, 255, 255));
        btnInserir.setBounds(686, 69, 89, 35);
        btnInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conexao = oi.conexao.ConnectDb();
                if (conexao != null) {
                    String sql = "INSERT INTO s(id_turma, id_turmacurso, data_turma, turno, nr_matricula) VALUES (?, ?, ?, ?, ?)";
                    try {
                        mypst = conexao.prepareStatement(sql);
                        mypst.setString(1, txtFldID.getText());
                        mypst.setString(2, txtFldIdCurso.getText());
                        mypst.setString(3, textFldIdData.getText());
                        mypst.setString(4, txtFldIdturno.getText());
                        mypst.setString(5, txtFldMatricula.getText());
                        mypst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "s cadastrado com sucesso!");
                        updateTable();
                        txtFldID.setText("");
                        txtFldIdCurso.setText("");
                        textFldIdData.setText("");
                        txtFldIdturno.setText("");
                        txtFldMatricula.setText("");
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
        frmTurma.getContentPane().add(btnInserir);

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
                        String id = table.getValueAt(row, 0).toString();
                        String sql = "UPDATE s SET id_turmacurso=?, data_turma=?, turno=?, nr_matricula=? WHERE id_turma=?";
                        try {
                            mypst = conexao.prepareStatement(sql);
                            mypst.setString(1, txtFldIdCurso.getText());
                            mypst.setString(2, textFldIdData.getText());
                            mypst.setString(3, txtFldIdturno.getText());
                            mypst.setString(4, txtFldMatricula.getText());
                            mypst.setString(5, id);
                            mypst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                            updateTable();
                            txtFldID.setText("");
                            txtFldIdCurso.setText("");
                            textFldIdData.setText("");
                            txtFldIdturno.setText("");
                            txtFldMatricula.setText("");
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
        frmTurma.getContentPane().add(btnAlterar);

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
                        String id = table.getValueAt(row, 0).toString();
                        String sql = "DELETE FROM s WHERE id_turma=?";
                        try {
                            mypst = conexao.prepareStatement(sql);
                            mypst.setString(1, id);
                            mypst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Professor excluído com sucesso!");
                            updateTable();
                            txtFldID.setText("");
                            txtFldIdCurso.setText("");
                            textFldIdData.setText("");
                            txtFldIdturno.setText("");
                            txtFldMatricula.setText("");
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
        frmTurma.getContentPane().add(btnExcluir);
        
        JButton btnConsulta = new JButton("Consultar");
        btnConsulta.setBackground(new Color(255, 255, 255));
		btnConsulta.setFont(new Font("Arial", Font.BOLD, 12));
		btnConsulta.setBounds(686, 203, 89, 35);
		frmTurma.getContentPane().add(btnConsulta);
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(null, "Informe a matrícula do aluno:");
				if (id != null && !id.isEmpty()) {
					String sql = "SELECT id_turma, id_turmacurso, data_turma, turno, nr_matricula FROM s WHERE id_turma = ?";
					conexao = oi.conexao.ConnectDb();
					if (conexao != null) {
						try {
							mypst = conexao.prepareStatement(sql);
							mypst.setString(1, id);
							myrs = mypst.executeQuery();
							if (myrs.next()) {
								JOptionPane.showMessageDialog(null, "data_turmas: " + myrs.getString("id_turma")
										+ "\nid_turmacurso: " + myrs.getString("id_turmacurso")
										+ "\ndata_turma: " + myrs.getString("data_turma")
										+ "\nid_turmaDisponibilidade: " + myrs.getString("turno")
										+ "\nid_turma: " + myrs.getString("nr_matricula"));
							} else {
								JOptionPane.showMessageDialog(null, "Professor não encontrado.");
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
		frmTurma.getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        txtFldID.setText("");
		        txtFldIdCurso.setText("");
		        textFldIdData.setText("");
		        txtFldIdturno.setText("");
		        txtFldMatricula.setText("");
		    }
		});
        
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setBackground(new Color(255, 255, 255));
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 12));
        btnImprimir.setBounds(686, 292, 90, 35);
        frmTurma.getContentPane().add(btnImprimir);
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
        frmTurma.getContentPane().add(scrollPane);
        
        JLabel lblNewLabel_11 = new JLabel("Todos os direitos são reservados a V.G.R.B.S Serviços ");
    	lblNewLabel_11.setForeground(SystemColor.infoText);
    	lblNewLabel_11.setToolTipText("");
    	lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel_11.setBackground(SystemColor.activeCaption);
    	lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	lblNewLabel_11.setBounds(10, 411, 765, 42);
    	frmTurma.getContentPane().add(lblNewLabel_11);

        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    txtFldID.setText(table.getValueAt(row, 0).toString());
                    txtFldIdCurso.setText(table.getValueAt(row, 1).toString());
                    textFldIdData.setText(table.getValueAt(row, 2).toString());
                    txtFldIdturno.setText(table.getValueAt(row, 3).toString());
                    txtFldMatricula.setText(table.getValueAt(row, 4).toString());
                }
            }
        });
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_11_1 = new JLabel("Cadastro de s");
        lblNewLabel_11_1.setToolTipText("");
        lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11_1.setForeground(SystemColor.infoText);
        lblNewLabel_11_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_11_1.setBackground(SystemColor.activeCaption);
        lblNewLabel_11_1.setBounds(0, 10, 785, 42);
        frmTurma.getContentPane().add(lblNewLabel_11_1);
        
        txtFldMatricula = new JTextField();
        txtFldMatricula.setColumns(10);
        txtFldMatricula.setBounds(101, 248, 163, 35);
        frmTurma.getContentPane().add(txtFldMatricula);
        
        JLabel lbl = new JLabel("Nrº Matricula:");
        lbl.setLabelFor(txtFldMatricula);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setBounds(10, 250, 89, 31);
        frmTurma.getContentPane().add(lbl);
        updateTable();
    }
    	
    private void updateTable() {
        conexao = oi.conexao.ConnectDb();
        if (conexao != null) {
            String sql = "SELECT * FROM s";
            try {
                mypst = conexao.prepareStatement(sql);
                myrs = mypst.executeQuery();

                model.setRowCount(0); // Limpar tabela antes de atualizar

                while (myrs.next()) {
                    String id = myrs.getString("id_turma");
                    String idProfessor = myrs.getString("id_turmacurso");
                    String idDisciplina = myrs.getString("data_turma");
                    String turno = myrs.getString("turno");
                    String matricula = myrs.getString("nr_matricula");
                    model.addRow(new Object[]{id, idProfessor, idDisciplina, turno,matricula});
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