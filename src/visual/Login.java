package visual;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utils.MyButtonModel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        Login l = new Login();
        l.setVisible(true);
    }

    /*
     * Utils
     */
    private int xMouse;
    private int yMouse;
    private Color colorAzul = new Color(59, 165, 187);

    private JPanel contentPane;
    /*
     * Panel Superior
     */
    private JPanel panelSuperior;
    private JButton btnMinimizar;
    private JButton btnCerrar;
    /*
     * Panel Inferior
     */
    private JPanel panelInferior;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JLabel registrarse;

    public Login() {
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 652, 402);
        setLocationRelativeTo(null);

        contentPane = new JPanel(null);
        contentPane.setBackground(Color.darkGray);
        setContentPane(contentPane);

        panelSuperior = new JPanel(null);
        panelSuperior.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
        panelSuperior.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        panelSuperior.setBounds(1, 1, 650, 30);
        panelSuperior.setBackground(colorAzul);
        contentPane.add(panelSuperior);

        ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/minimize.png"));
        Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Icon iconMinimizar = new ImageIcon(image);

        btnMinimizar = new JButton();
        btnMinimizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnMinimizar.setContentAreaFilled(false);
                setExtendedState(ICONIFIED);
            }
        });
        btnMinimizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimizar.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimizar.setContentAreaFilled(false);
            }
        });
        btnMinimizar.setBounds(560, 0, 45, 30);
        btnMinimizar.setBackground(Color.lightGray);
        btnMinimizar.setIcon(iconMinimizar);
        btnMinimizar.setFocusable(false);
        btnMinimizar.setBorderPainted(false);
        btnMinimizar.setContentAreaFilled(false);
        btnMinimizar.setModel(new MyButtonModel());
        panelSuperior.add(btnMinimizar);

        img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
        image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Icon iconCerrar = new ImageIcon(image);

        btnCerrar = new JButton();
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCerrar.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCerrar.setContentAreaFilled(false);
            }
        });
        btnCerrar.setBounds(605, 0, 45, 30);
        btnCerrar.setBackground(Color.red);
        btnCerrar.setIcon(iconCerrar);
        btnCerrar.setFocusable(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setModel(new MyButtonModel());
        panelSuperior.add(btnCerrar);

        JPanel panelFoto = new JPanel(null);
        panelFoto.setBounds(1, 31, 270, 370);
        contentPane.add(panelFoto);

        img = new ImageIcon(getClass().getResource("/visual/imagenes/login.jpg"));
        image = img.getImage().getScaledInstance(270, 370, Image.SCALE_SMOOTH);
        Icon fotoLogin = new ImageIcon(image);

        JLabel fotoIzq = new JLabel(fotoLogin);
        fotoIzq.setBounds(0, 0, 270, 370);
        panelFoto.add(fotoIzq);

        panelInferior = new JPanel(null);
        panelInferior.setBounds(271, 31, 380, 370);
        panelInferior.setBackground(Color.white);
        contentPane.add(panelInferior);
        
        img = new ImageIcon(getClass().getResource("/visual/imagenes/usuarioLogin.png"));
        image = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Icon iconUsuario = new ImageIcon(image);
        
        JLabel lUsuario = new JLabel(iconUsuario);
        lUsuario.setBounds(150, 90, 80, 80);
        panelInferior.add(lUsuario);
        
        txtUsuario = new JTextField("Usuario");
        txtUsuario.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if(txtUsuario.getText().equals("Usuario")){
        			txtUsuario.setText("");
        	        txtUsuario.setForeground(Color.black);
        		}
        	}
        	
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(txtUsuario.getText().equals("")){
        			txtUsuario.setText("Usuario");
        	        txtUsuario.setForeground(Color.gray);
        		}
        	}
        });
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setForeground(Color.gray);
        txtUsuario.setBorder(new MatteBorder(0, 0, 2, 0, colorAzul));
        txtUsuario.setBounds(60, 190, 260, 30);
        panelInferior.add(txtUsuario);

        txtPassword = new JPasswordField("Contraseña");
        txtPassword.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if(txtPassword.getText().equals("Contraseña")){
        			txtPassword.setText("");
        			txtPassword.setEchoChar('●');
        			txtPassword.setForeground(Color.black);
        		}
        	}
        	
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(txtPassword.getText().equals("")){
        			txtPassword.setText("Contraseña");
					txtPassword.setEchoChar((char) 0);
        			txtPassword.setForeground(Color.gray);
        		}
        	}
        });
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword.setForeground(Color.gray);
		txtPassword.setEchoChar((char) 0);
        txtPassword.setBorder(new MatteBorder(0, 0, 2, 0, colorAzul));
        txtPassword.setBounds(60, 250, 260, 30);
        panelInferior.add(txtPassword);

        JLabel noCuenta = new JLabel("¿No tienes una cuenta?");
        noCuenta.setFont(new Font("Arial", Font.PLAIN, 12));
        noCuenta.setForeground(Color.black);
        noCuenta.setBounds(93, 320, 134, 20);
        panelInferior.add(noCuenta);

        registrarse = new JLabel("Regístrate");
        registrarse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registrarse.setForeground(colorAzul);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registrarse.setForeground(new Color(40, 113, 128));
            }
        });
        registrarse.setFont(new Font("Arial", Font.BOLD, 12));
        registrarse.setForeground(new Color(48, 135, 153));
        registrarse.setBounds(229, 320, 60, 20);
        registrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelInferior.add(registrarse);
        
        

    }
}