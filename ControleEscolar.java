package oi;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class ControleEscolar {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControleEscolar window = new ControleEscolar();
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
	public ControleEscolar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("Cadastrar");
		frame.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
			/*CRIANDO MENU DE ALUNO*/
			JMenu menuAluno = new JMenu("Aluno");
			menuBar.add(menuAluno);
	        JMenuItem menuItemCadastrarAluno = new JMenuItem("Cadastrar");
	        JMenuItem menuItemVerFrequencia = new JMenuItem("Frequencia");
	        JMenuItem menuItemAlunoCurso = new JMenuItem("Curso");
	        menuAluno.add(menuItemCadastrarAluno);
	        menuAluno.add(menuItemVerFrequencia);
	        menuAluno.add(menuItemAlunoCurso);
	        
	        /*CRIANDO MENU DE PROFESSOR*/
	        JMenu menuProfessor = new JMenu("Professor");
	        menuBar.add(menuProfessor);
	        JMenuItem menuItemCadastrarProfessor = new JMenuItem("Cadastrar");
	        
	        JMenuItem menuItemDisponibilidade_professor = new JMenuItem("Disponibilidade");
	        menuProfessor.add(menuItemCadastrarProfessor);
	        
	        menuProfessor.add(menuItemDisponibilidade_professor  );
	        
	        /*CRIANDO MENU DE CURSO*/
	        JMenu menuCurso = new JMenu("Curso");
	        menuBar.add(menuCurso);
	        JMenuItem menuItemCadastrarCurso = new JMenuItem("Cadastrar");
	        JMenuItem menuItemVerificarTurmas = new JMenuItem("Turmas");
	        JMenuItem menuItemCoordenador = new JMenuItem("Coordenador");
	        JMenuItem menuItemDisciplina = new JMenuItem("Disciplina");
	        menuCurso.add(menuItemCadastrarCurso);
			menuCurso.add(menuItemVerificarTurmas);
	        menuCurso.add(menuItemCoordenador);
	        menuCurso.add(menuItemDisciplina);
	        
	        /*CRIANDO MENU DE RELATORIO*/
	        JMenu menuRelatorio = new JMenu("Relatório");
	        menuBar.add(menuRelatorio);
	        JMenuItem menuItemCadastrarRelatorio = new JMenuItem("Cadastrar");
	        JMenuItem menuItemVerificarRelatorio = new JMenuItem("Relatórios");
	        menuRelatorio.add(menuItemCadastrarRelatorio);
	        menuRelatorio.add(menuItemVerificarRelatorio);
	        
	        // Esta classe herda de ActionListener

	    	class ItemListener implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Coloque aqui o código que será executado quando o item de menu for selecionado
	    	    }
	    	}

	    	class MeuItemListener implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window2 = new ControleEscolar();
					Aluno window = new Aluno();
					window.frmAluno.setVisible(true);
	    	    
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }


	    	    	
	    	    }
	    	}
	        
	    	class MeuItemListener2 implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window3 = new ControleEscolar();
					Curso window = new Curso();
					window.frmCurso.setVisible(true);
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }
	    	    }
	    	}
	    	
	    	
	    	
	    	class MeuItemListener3 implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window4 = new ControleEscolar();
					Professor window = new Professor();
					window.frmProfessor.setVisible(true);
	    	    
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }


	    	    	
	    	    }
	    	}
	    	
	    	class MeuItemListener4 implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window5 = new ControleEscolar();
					Turma window = new Turma();
					window.frmTurma.setVisible(true);
	    	    
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }


	    	    	
	    	    }
	    	}
	    	
	    	
	    	class MeuItemListener5 implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window6 = new ControleEscolar();
					AlunoCurso window = new AlunoCurso();
					window.frmAlunoCurso.setVisible(true);
	    	    
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }


	    	    	
	    	    }
	    	}
	    	
	    	
	    	
	    	class MeuItemListener6 implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window7 = new ControleEscolar();
					Coordenador window = new Coordenador();
					window.frmCoordenador.setVisible(true);
	    	    
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }


	    	    	
	    	    }
	    	}
	    	
	    	
	    	class MeuItemListener7 implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window8 = new ControleEscolar();
					Disciplina window = new Disciplina();
					window.frmDisciplina.setVisible(true);
	    	    
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }


	    	    	
	    	    }
	    	}
	    	
	    	class MeuItemListener8 implements ActionListener {
	    	    public void actionPerformed(ActionEvent event) {
	    	        // Aqui você insere o código que deve ser executado quando o item de menu for selecionado
	    	    	System.out.println("O item de menu foi selecionado!");
	    	    	ControleEscolar window9 = new ControleEscolar();
	    	    	Disponibilidade_professor window = new Disponibilidade_professor();
					window.frDisponiblidade.setVisible(true);
	    	    
					
					// Aqui eu estou buscando saber qual foi item selecionado no menu.
					// Acho que assim podemos direcionar a opção selecionada para exibir a tela correspondente.
			        Object source = event.getSource();
			        if (source instanceof JMenuItem) {
			            JMenuItem menuItem = (JMenuItem) source;
			            String itemName = menuItem.getText();
			            System.out.println("O item de menu selecionado foi: " + itemName);
			        }


	    	    	
	    	    }
	    	}
	    	
	    	
	    	
	    	
	    	
	    	MeuItemListener meuItemListener = new MeuItemListener();
	    	menuItemCadastrarAluno.addActionListener(meuItemListener);
	    	
	    	MeuItemListener2 meuItemListener2 = new MeuItemListener2();
	    	menuItemCadastrarCurso.addActionListener(meuItemListener2);
	    	
	    	MeuItemListener3 meuItemListener3 = new MeuItemListener3();
	    	menuItemCadastrarProfessor.addActionListener(meuItemListener3);
	    	
	    	MeuItemListener4 meuItemListener4 = new MeuItemListener4();
	    	menuItemVerificarTurmas.addActionListener(meuItemListener4);
	    	
	    	MeuItemListener5 meuItemListener5 = new MeuItemListener5();
	    	menuItemAlunoCurso.addActionListener(meuItemListener5);
	    	
	    	MeuItemListener6 meuItemListener6 = new MeuItemListener6();
	    	menuItemCoordenador.addActionListener(meuItemListener6);
	    	
	    	MeuItemListener7 meuItemListener7 = new MeuItemListener7();
	    	menuItemDisciplina.addActionListener(meuItemListener7);
	    	
	    	MeuItemListener8 meuItemListener8 = new MeuItemListener8();
	    	menuItemDisponibilidade_professor.addActionListener(meuItemListener8);

	    	

	}
	


	

	

}
	    