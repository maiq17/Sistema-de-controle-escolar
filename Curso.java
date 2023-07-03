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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class Curso {

    public JFrame frmCurso;
    private JTextField txtFldid_Curso;
    private JTextField txtFldNomeCursos;
    private JTextField textFldid_Coordenador;
    private JTable table;
    private JComboBox<String> comboBox;
    private DefaultTableModel model;
    private Connection conexao;
    private PreparedStatement mypst;
    private ResultSet myrs;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Curso window = new Curso();
                    window.frmCurso.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Curso() {
        initialize();
    }

    private void initialize() {
        frmCurso = new JFrame();
        frmCurso.getContentPane().setBackground(SystemColor.activeCaption);
        frmCurso.setBackground(SystemColor.inactiveCaption);
        frmCurso.setForeground(SystemColor.inactiveCaption);
        frmCurso.setFont(new Font("Arial", Font.PLAIN, 14));
        frmCurso.setTitle("Cadastro de Cursos");
        frmCurso.setBounds(100, 100, 800, 500);
        frmCurso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCurso.getContentPane().setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("ID_Curso");
        model.addColumn("Nome_Curso");
        model.addColumn("ID_Coordenador");
        
        txtFldid_Curso = new JTextField();
        txtFldid_Curso.setFont(new Font("Arial", Font.PLAIN, 12));
        txtFldid_Curso.setBounds(101, 68, 163, 35);
        txtFldid_Curso.setColumns(10);
        frmCurso.getContentPane().add(txtFldid_Curso);

        txtFldNomeCursos = new JTextField();
        txtFldNomeCursos.setFont(new Font("Arial", Font.PLAIN, 12));
        txtFldNomeCursos.setBounds(101, 113, 163, 35);
        txtFldNomeCursos.setColumns(10);
        frmCurso.getContentPane().add(txtFldNomeCursos);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        comboBox.setBounds(101, 159, 163, 31);
        frmCurso.getContentPane().add(comboBox);
        try {
            conexao = oi.conexao.ConnectDb();
            if (conexao != null) {
                String sql = "SELECT * FROM coordenacao";
                try (Statement stmt = conexao.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        String idCoordenador = rs.getString("id_Coordenador");
                        comboBox.addItem(idCoordenador);
                    }
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(frmCurso, erro);
        }

        frmCurso.setVisible(true);

        JLabel lblNewLabel = new JLabel("ID Curso:");
        lblNewLabel.setForeground(SystemColor.infoText);
        lblNewLabel.setBounds(41, 70, 58, 31);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        frmCurso.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Curso:");
        lblNewLabel_1.setBounds(57, 118, 42, 31);
        lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
        frmCurso.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("ID Coord.:");
        lblNewLabel_2.setBounds(32, 160, 66, 31);
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
        frmCurso.getContentPane().add(lblNewLabel_2);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(SystemColor.text);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String idCurso = txtFldid_Curso.getText();
                String nomeCurso = txtFldNomeCursos.getText();
                String idCoordenador = comboBox.getSelectedItem().toString();
                try {
                    conexao = oi.conexao.ConnectDb();
                    String sql = "INSERT INTO curso (id_curso, nome_curso, id_coordenador) VALUES (?,?,?)";
                    mypst = conexao.prepareStatement(sql);
                    mypst.setString(1, idCurso);
                    mypst.setString(2, nomeCurso);
                    mypst.setString(3, idCoordenador);
                    mypst.executeUpdate();
                    JOptionPane.showMessageDialog(frmCurso, "Dados inseridos com sucesso!");
                    mypst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frmCurso, e);
                }
                updateTable();
            }
        });
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalvar.setBounds(686, 69, 89, 31);
        frmCurso.getContentPane().add(btnSalvar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBackground(SystemColor.text);
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frmCurso, "Selecione um curso para alterar.");
                } else {
                    String idCurso = txtFldid_Curso.getText();
                    String nomeCurso = txtFldNomeCursos.getText();
                    String idCoordenador = comboBox.getSelectedItem().toString();
                    try {
                        conexao = oi.conexao.ConnectDb();
                        String sql = "UPDATE curso SET nome_curso=?, id_coordenador=? WHERE id_curso=?";
                        mypst = conexao.prepareStatement(sql);
                        mypst.setString(1, nomeCurso);
                        mypst.setString(2, idCoordenador);
                        mypst.setString(3, idCurso);
                        mypst.executeUpdate();
                        JOptionPane.showMessageDialog(frmCurso, "Dados alterados com sucesso!");
                        mypst.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frmCurso, ex);
                    }
                    updateTable();
                }
            }
        });
        btnAlterar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAlterar.setBounds(686, 114, 90, 31);
        frmCurso.getContentPane().add(btnAlterar);

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
                        String id_Curso = table.getValueAt(row, 0).toString();
                        String sql = "DELETE FROM curso WHERE id_Curso=?";
                        try {
                            mypst = conexao.prepareStatement(sql);
                            mypst.setString(1, id_Curso);
                            mypst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Cursos excluído com sucesso!");
                            updateTable();
                            txtFldid_Curso.setText("");
                            txtFldNomeCursos.setText("");
                            textFldid_Coordenador.setText("");
                            
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
                        JOptionPane.showMessageDialog(null, "Selecione um curso na tabela para realizar a exclusão.");
                    }
                }
            }
        });
        frmCurso.getContentPane().add(btnExcluir);
        
        JButton btnConsulta = new JButton("Consultar");
        btnConsulta.setBackground(new Color(255, 255, 255));
		btnConsulta.setFont(new Font("Arial", Font.BOLD, 12));
		btnConsulta.setBounds(686, 203, 89, 35);
		frmCurso.getContentPane().add(btnConsulta);
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_Curso = JOptionPane.showInputDialog(null, "Informe a matrícula do aluno:");
				if (id_Curso != null && !id_Curso.isEmpty()) {
					String sql = "SELECT id_Curso, nome_Curso, id_Coordenador FROM curso WHERE id_Curso = ?";
					conexao = oi.conexao.ConnectDb();
					if (conexao != null) {
						try {
							mypst = conexao.prepareStatement(sql);
							mypst.setString(1, id_Curso);
							myrs = mypst.executeQuery();
							if (myrs.next()) {
								JOptionPane.showMessageDialog(null, "Matrícula: " + myrs.getString("id_Curso")
										+ "\nNome: " + myrs.getString("nome_Curso")
										+ "\nid_Coordenador: " + myrs.getString("id_Coordenador")
										);
							} else {
								JOptionPane.showMessageDialog(null, "Curso não encontrado.");
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
		frmCurso.getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        txtFldid_Curso.setText("");
		        txtFldNomeCursos.setText("");
		        textFldid_Coordenador.setText("");
		        
		    }
		});
        
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setBackground(new Color(255, 255, 255));
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 12));
        btnImprimir.setBounds(686, 292, 90, 35);
        frmCurso.getContentPane().add(btnImprimir);
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
        frmCurso.getContentPane().add(scrollPane);
        
        JLabel lblNewLabel_11 = new JLabel("Todos os direitos são reservados a V.G.R.B.S Serviços ");
    	lblNewLabel_11.setForeground(SystemColor.infoText);
    	lblNewLabel_11.setToolTipText("");
    	lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
    	lblNewLabel_11.setBackground(SystemColor.activeCaption);
    	lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	lblNewLabel_11.setBounds(10, 411, 765, 42);
    	frmCurso.getContentPane().add(lblNewLabel_11);

        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    txtFldid_Curso.setText(table.getValueAt(row, 0).toString());
                    txtFldNomeCursos.setText(table.getValueAt(row, 1).toString());
                    textFldid_Coordenador.setText(table.getValueAt(row, 2).toString());
                    
                }
            }
        });
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_11_1 = new JLabel("Cadastro de Cursos");
        lblNewLabel_11_1.setToolTipText("");
        lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11_1.setForeground(SystemColor.infoText);
        lblNewLabel_11_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_11_1.setBackground(SystemColor.activeCaption);
        lblNewLabel_11_1.setBounds(0, 10, 785, 42);
        frmCurso.getContentPane().add(lblNewLabel_11_1);
        updateTable();
    }
    	
    private void updateTable() {
        conexao = oi.conexao.ConnectDb();
        if (conexao != null) {
            String sql = "SELECT * FROM curso";
            try {
                mypst = conexao.prepareStatement(sql);
                myrs = mypst.executeQuery();

                model.setRowCount(0); // Limpar tabela antes de atualizar

                while (myrs.next()) {
                    String id_Curso = myrs.getString("id_Curso");
                    String nome = myrs.getString("nome_Curso");
                    String id_Coordenador = myrs.getString("id_Coordenador");
                    
                    model.addRow(new Object[]{id_Curso, nome, id_Coordenador});
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