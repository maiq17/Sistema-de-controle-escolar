package oi;


import java.sql.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import com.toedter.calendar.JCalendar;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
@SuppressWarnings("unused")
public class Professor_Disciplina {

	private JFrame frame;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Professor_Disciplina window = new Professor_Disciplina();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Professor_Disciplina() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBounds(new Rectangle(0, 0, 800, 600));
		frame.setBounds(new Rectangle(0, 0, 800, 600));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProfessores = new JLabel("Professores");
		lblProfessores.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfessores.setBounds(284, 415, 212, 14);
		frame.getContentPane().add(lblProfessores);		

		JLabel lblNome = new JLabel("Professor");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(284, 471, 212, 14);
		frame.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(284, 496, 212, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);		
				
		var myConn = conexao.ConnectDb();
		if (myConn != null) {
		    try {
		        String sql = "SELECT * FROM professor";
		        Statement stmt = myConn.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);

		        // Cria uma array para armazenar os professores
		        ArrayList<String> professores = new ArrayList<>();

		        while (rs.next()) {
		            // Armazena todos os professores do banco nessa array
		            professores.add(rs.getString("nome_professor"));
		        }

		        DefaultComboBoxModel<String> listCombo = new DefaultComboBoxModel<>(professores.toArray(new String[0]));
		        JComboBox<String> comboBox = new JComboBox<>(listCombo);

		        // Adicionar um ActionListener à JComboBox
		        comboBox.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // Obter a informação selecionada no JComboBox
		                String selecao = (String) comboBox.getSelectedItem();
		                txtNome.setText(selecao);
		            }
		        });

		        comboBox.setBounds(284, 441, 212, 21);
		        frame.getContentPane().add(comboBox);

		        myConn.close();
		        stmt.close();
		    } catch (Exception erro) {
		        JOptionPane.showMessageDialog(lblProfessores, erro);
		    }
		}
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> listMaterias = new JList<>(listModel);
        listMaterias.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        listMaterias.setBounds(40, 36, 235, 350);
        frame.getContentPane().add(listMaterias);
        
        
        
        DefaultListModel<String> innerlistModel = new DefaultListModel<>();      		
        JList<String> list = new JList<>(innerlistModel);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBounds(510, 36, 235, 350);
		frame.getContentPane().add(list);
		
		JButton btnPassar = new JButton(">>\r\n");
		btnPassar.setBounds(355, 72, 89, 23);
		frame.getContentPane().add(btnPassar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
		        model.clear();
			}
		});
		btnLimpar.setBounds(355, 140, 89, 23);
		frame.getContentPane().add(btnLimpar);		

		JButton btnRemover = new JButton("<<");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obter os elementos selecionados da lista "listMaterias"
		        List<String> elementosSelecionados = list.getSelectedValuesList();

		        for (String elemento : elementosSelecionados) {
		            innerlistModel.removeElement(elemento);
		        }
			}
		});
		btnRemover.setBounds(355, 106, 89, 23);
		frame.getContentPane().add(btnRemover);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(284, 211, 212, 20);
		frame.getContentPane().add(dateChooser);			

		JDateChooser dateFinal = new JDateChooser();
		dateFinal.setDateFormatString("dd/MM/yyyy");
		dateFinal.setBounds(284, 269, 212, 20);
		frame.getContentPane().add(dateFinal);	
	
		JButton btnDefinir = new JButton("Definir");
		btnDefinir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(frame, "Confirmar inclusão do registro?", "Definir cronograma",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					
					//Verifica se todos os campos foram preenchidos
					if(!txtNome.getText().isEmpty()) {
						
						String idProfessor = null;
						String idMateria = null;
						
						var myConn = Conexao.ConnectDb();
						if(myConn != null) {
							try {
							    String sqlProfessor = "SELECT * FROM professor WHERE nome_professor = ?";							    
							    PreparedStatement stmtProfessor = myConn.prepareStatement(sqlProfessor);

							    // Obter o ID do professor
							    stmtProfessor.setString(1, txtNome.getText());
							    ResultSet rsProfessor = stmtProfessor.executeQuery();							    
							    							    
							    if (rsProfessor.next()) {
							        idProfessor = rsProfessor.getString("id_professor");
							        List<String> elementosSelecionados = new ArrayList<>();
							        DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();

							        for (int i = 0; i < listModel.getSize(); i++) {
							            String elemento = listModel.getElementAt(i);
							            elementosSelecionados.add(elemento);
							        }
							        
							        for (String elemento : elementosSelecionados) {
							        	System.out.println(elemento);
							            // Obter o ID da disciplina
							            String sqlDisciplina = "SELECT * FROM disciplina WHERE nome_disciplina = ?";
							            PreparedStatement stmtDisciplina = myConn.prepareStatement(sqlDisciplina);
							            stmtDisciplina.setString(1, elemento);
							            ResultSet rsDisciplina = stmtDisciplina.executeQuery();
							            
							            if (rsDisciplina.next()) {							            	
							                idMateria = rsDisciplina.getString("id_discipliina");
							                System.out.println(idMateria);							                
							                
							                if(dateChooser.getDate() != null && dateFinal.getDate() != null && !txtNome.getText().isEmpty()) {
												SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");									
												String novaData = formatador.format(dateChooser.getDate()); 
												System.out.println(novaData);
												
												String dataFinal = formatador.format(dateFinal.getDate());
												
												String sqlInsert = "INSERT INTO professor_disciplina (id_professor, id_disciplina,dt_inicio,dt_fim) VALUES('"+idProfessor+"','"+idMateria+"','"+novaData+"','"+dataFinal+"')";
												Statement pstmtInsert = myConn.createStatement();												
												System.out.println(pstmtInsert);
												
												if(!innerlistModel.isEmpty()) {
													pstmtInsert.executeUpdate(sqlInsert);
													JOptionPane.showMessageDialog(btnDefinir, "Definido com sucesso");
												}else {
													JOptionPane.showMessageDialog(btnDefinir, "Verifique se todos os campos foram preenchidos!");
												}
												
								                pstmtInsert.close();
										        
											}else {
													JOptionPane.showMessageDialog(btnDefinir, "Verifique se todos os campos foram preenchidos!");
											}							                
							            }
							            rsDisciplina.close();
							            stmtDisciplina.close();
							        }							        
							    }
							    rsProfessor.close();
							    stmtProfessor.close();
							    
							    myConn.close();
							} catch (SQLException ex) {
							    // Trate a exceção adequadamente ou exiba uma mensagem de erro
							    ex.printStackTrace();
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(btnDefinir, "Verifique se todos os campos foram preenchidos.");
					}
				}
			}
		});
		btnDefinir.setBounds(355, 527, 89, 23);
		frame.getContentPane().add(btnDefinir);
		
		myConn = Conexao.ConnectDb();
		if (myConn != null) {
		    try {
		        String sql = "SELECT * FROM disciplina";
		        Statement stmt = myConn.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);
		        
		        // Criar ArrayList para armazenar as disciplinas
		        ArrayList<String> disciplinas = new ArrayList<>();

		        while (rs.next()) {
		            disciplinas.add(rs.getString("nome_disciplina"));
		        }
		        
		        for (String disciplina : disciplinas) {
		            listModel.addElement(disciplina);
		        }

        		btnPassar.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        		        // Obter os elementos selecionados da lista "listMaterias"
        		        List<String> elementosSelecionados = listMaterias.getSelectedValuesList();

        		        // Adicionar os elementos selecionados à lista "list"
        		        DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
        		        for (String elemento : elementosSelecionados) {
        		        	if(!listModel.contains(elemento)) {
        		        		listModel.addElement(elemento);
        		        	}
        		        }
        		    }
        		});
        		
		        
		        myConn.close();
		        stmt.close();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(btnDefinir, e);
		    }		    
		}
		
		JLabel lblMaterias = new JLabel("Matérias\r\n");
		lblMaterias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaterias.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaterias.setBounds(341, 37, 118, 14);
		frame.getContentPane().add(lblMaterias);
		
		JLabel lblData = new JLabel("Data Inicial");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(285, 186, 211, 14);
		frame.getContentPane().add(lblData);
		
		JLabel lblNewLabel = new JLabel("Data Final");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(284, 244, 212, 14);
		frame.getContentPane().add(lblNewLabel);
			
}
}
