package oi;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AlunoCurso {
	public JFrame frmAlunoCurso;
	private JTextField textFieldnr_matricula;
	private JTextField textFieldCurso;
	private JTable table;
	private DefaultTableModel tableModel;
	private int sequencial = 1;
	private Connection connection;
	private JLabel lblMensagem; // Adiciona o JLabel para exibir a mensagem

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AlunoCurso window = new AlunoCurso();
                    window.frmAlunoCurso.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AlunoCurso() {
        initialize();
        connectToDatabase();
    }

    private void initialize() {
        frmAlunoCurso = new JFrame();
        frmAlunoCurso.setBounds(100, 100, 450, 300);
        frmAlunoCurso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAlunoCurso.getContentPane().setLayout(null);

        JLabel lblnr_matricula = new JLabel("nr_matricula:");
        lblnr_matricula.setBounds(10, 33, 75, 14);
        frmAlunoCurso.getContentPane().add(lblnr_matricula);

        JLabel lblCurso = new JLabel("Id Curso:");
        lblCurso.setBounds(10, 64, 46, 14);
        frmAlunoCurso.getContentPane().add(lblCurso);

        textFieldnr_matricula = new JTextField();
        textFieldnr_matricula.setBounds(96, 30, 86, 20);
        frmAlunoCurso.getContentPane().add(textFieldnr_matricula);
        textFieldnr_matricula.setColumns(10);

        textFieldCurso = new JTextField();
        textFieldCurso.setBounds(96, 61, 86, 20);
        frmAlunoCurso.getContentPane().add(textFieldCurso);
        textFieldCurso.setColumns(10);

        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(335, 29, 89, 23);
        frmAlunoCurso.getContentPane().add(btnIncluir);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(335, 63, 89, 23);
        frmAlunoCurso.getContentPane().add(btnAlterar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(335, 97, 89, 23);
        frmAlunoCurso.getContentPane().add(btnConsultar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(335, 131, 89, 23);
        frmAlunoCurso.getContentPane().add(btnExcluir);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(335, 165, 89, 23);
        frmAlunoCurso.getContentPane().add(btnLimpar);

        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(335, 199, 89, 23);
        frmAlunoCurso.getContentPane().add(btnImprimir);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 148, 315, 99);
        frmAlunoCurso.getContentPane().add(scrollPane);
        
        lblMensagem = new JLabel();
        lblMensagem.setBounds(10, 120, 315, 20);
        frmAlunoCurso.getContentPane().add(lblMensagem);


        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "nr_matricula", "ID Curso" }
        ));
        scrollPane.setViewportView(table);

        tableModel = (DefaultTableModel) table.getModel();

        btnIncluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nr_matricula = textFieldnr_matricula.getText();
                String curso = textFieldCurso.getText();

                insertData(nr_matricula, curso);

                updateTable();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nr_matricula = textFieldnr_matricula.getText();
                    String curso = textFieldCurso.getText();
                    String selectednr_matricula = table.getValueAt(selectedRow, 0).toString();

                    updateData(selectednr_matricula, nr_matricula, curso);
                    updateTable();
                }
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String selectednr_matricula = table.getValueAt(selectedRow, 0).toString();
                    retrieveData(selectednr_matricula);
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String selectednr_matricula = table.getValueAt(selectedRow, 0).toString();
                    deleteData(selectednr_matricula);
                    updateTable();
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        
       
        
        btnImprimir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeArquivo = "tabela_" + sequencial + ".txt"; // 
                sequencial++; 

                try {
                    FileWriter fileWriter = new FileWriter(nomeArquivo);
                    PrintWriter printWriter = new PrintWriter(fileWriter);

                    
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        for (int j = 0; j < tableModel.getColumnCount(); j++) {
                            printWriter.print(tableModel.getValueAt(i, j) + "\t");
                        }
                        printWriter.println();
                    }

                    printWriter.close();
                    fileWriter.close();

                    System.out.println("Arquivo " + nomeArquivo + " gerado com sucesso!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frmAlunoCurso.setVisible(true);
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/controle_escolar";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertData(String nr_matricula, String id_curso) {
        String checkSql = "SELECT * FROM aluno_curso WHERE nr_matricula = ? AND id_curso = ?";
        try {
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, nr_matricula);
            checkStatement.setString(2, id_curso);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                lblMensagem.setText("Registro já existe no banco de dados.");
                resultSet.close();
                checkStatement.close();
                return; // Retorna imediatamente se a combinação já existir
            }
            resultSet.close();
            checkStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "INSERT INTO aluno_curso (nr_matricula, id_curso) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nr_matricula);
            statement.setString(2, id_curso);
            statement.executeUpdate();
            statement.close();
            
            lblMensagem.setText("Dados inseridos com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateData(String selectednr_matricula, String nr_matricula, String curso) {
        String sql = "UPDATE aluno_curso SET nr_matricula = ?, id_curso = ? WHERE nr_matricula = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nr_matricula);
            statement.setString(2, curso);
            statement.setString(3, selectednr_matricula);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteData(String selectednr_matricula) {
        String sql = "DELETE FROM aluno_curso WHERE nr_matricula = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, selectednr_matricula);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void retrieveData(String selectednr_matricula) {
        String sql = "SELECT nr_matricula, id_curso FROM aluno_curso WHERE nr_matricula = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, selectednr_matricula);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nr_matricula = resultSet.getString("nr_matricula");
                String curso = resultSet.getString("id_curso");
                textFieldnr_matricula.setText(nr_matricula);
                textFieldCurso.setText(curso);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {
        String sql = "SELECT nr_matricula, id_curso FROM aluno_curso";
        tableModel.setRowCount(0);

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nr_matricula = resultSet.getString("nr_matricula");
                String curso = resultSet.getString("id_curso");
                tableModel.addRow(new Object[]{nr_matricula, curso});
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    private void clearFields() {
        textFieldnr_matricula.setText("");
        textFieldCurso.setText("");
    }
}	