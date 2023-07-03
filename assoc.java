package oi;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class assoc {

    private JFrame frame;
    private JTextField txtNome;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    assoc window = new assoc();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public assoc() {
        initialize();
    } 

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBackground(SystemColor.activeCaption);
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Professor");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        lblNome.setBounds(344, 480, 93, 14);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setEditable(false);
        txtNome.setBounds(289, 500, 200, 20);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(97, 70, 235, 37);
        frame.getContentPane().add(comboBox);

        JList<String> listMaterias = new JList<>();
        listMaterias.setBounds(97, 70, 235, 420);
        frame.getContentPane().add(listMaterias);

        JButton btnPassar = new JButton(">>");
        btnPassar.setFont(new Font("Black Ops One", Font.PLAIN, 12));
        btnPassar.setBounds(346, 127, 89, 23);
        frame.getContentPane().add(btnPassar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(new Font("Black Ops One", Font.PLAIN, 12));
        btnLimpar.setBounds(346, 191, 89, 23);
        frame.getContentPane().add(btnLimpar);

        JButton btnRemover = new JButton("<<");
        btnRemover.setFont(new Font("Black Ops One", Font.PLAIN, 12));
        btnRemover.setBounds(346, 157, 89, 23);
        frame.getContentPane().add(btnRemover);

        JButton btnDefinir = new JButton("Definir");
        btnDefinir.setFont(new Font("Black Ops One", Font.PLAIN, 12));
        btnDefinir.setBounds(346, 327, 89, 23);
        frame.getContentPane().add(btnDefinir);

        JLabel lblMaterias = new JLabel("Disciplinas");
        lblMaterias.setFont(new Font("Arial Black", Font.BOLD, 14));
        lblMaterias.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaterias.setBounds(447, 44, 234, 23);
        frame.getContentPane().add(lblMaterias);

        JLabel lblMaterias_1 = new JLabel("Professores");
        lblMaterias_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaterias_1.setFont(new Font("Arial Black", Font.BOLD, 14));
        lblMaterias_1.setBounds(97, 44, 235, 23);
        frame.getContentPane().add(lblMaterias_1);

        JList<String> listMaterias_1 = new JList<>();
        listMaterias_1.setBounds(447, 70, 235, 420);
        frame.getContentPane().add(listMaterias_1);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(SystemColor.controlShadow);
        frame.setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("Menu");
        mnNewMenu.setHorizontalAlignment(SwingConstants.LEFT);
        mnNewMenu.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        mnNewMenu.setBackground(SystemColor.controlShadow);
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
        mntmNewMenuItem.setBackground(SystemColor.controlShadow);
        mnNewMenu.add(mntmNewMenuItem);
    }
    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
