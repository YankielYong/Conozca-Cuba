package visual;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import services.ServicesLocator;
import services.UserServices;
import utils.MD5;
import utils.MyButtonModel;
import utils.Validaciones;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

import dto.UserDTO;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Login l = new Login();
		//l.setVisible(true);
	}
	
	private UserServices userServices = ServicesLocator.getUserServices();
	private ArrayList<UserDTO> listaUsuarios;
	private UserDTO user;

	/*
	 * Utils
	 */
	private int xMouse;
	private int yMouse;
	private Color colorAzul = new Color(59, 165, 187);
	private Color colorFondoBotones = new Color(58, 239, 235);
	private ImageIcon pOculta = new ImageIcon(getClass().getResource("/visual/imagenes/contraseña oculta.png"));
	private ImageIcon pVisible = new ImageIcon(getClass().getResource("/visual/imagenes/contraseña visible.png"));

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
	private JButton btnMostrarPass;
	private JButton btnIniciarSesion;

	private boolean userChanged = false;
	private boolean passChanged = false;
	
	private Login este;

	public Login() {
		este = this;
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
		
		JLabel lblNombre = new JLabel("Iniciar Sesión");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 200, 30);
		panelSuperior.add(lblNombre);

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
		btnMinimizar.setBackground(colorFondoBotones);
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

		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(280, 85, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(50, 35, 280, 85);
		panelInferior.add(logo);

		txtUsuario = new JTextField("Usuario");
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarAccion(1, e.getKeyCode());
			}
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumerosSignos(e);
			}
		});
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUsuario.getText().equals("Usuario") && !userChanged){
					txtUsuario.setText("");
					userChanged = true;
					txtUsuario.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsuario.getText().equals("")){
					txtUsuario.setText("Usuario");
					userChanged = false;
					txtUsuario.setForeground(Color.gray);
				}
			}
		});
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUsuario.setForeground(Color.gray);
		txtUsuario.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtUsuario.setBounds(60, 160, 260, 30);
		panelInferior.add(txtUsuario);

		txtPassword = new JPasswordField("Contraseña");
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				realizarAccion(2, e.getKeyCode());
			}
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumerosYTodosSignos(e);
			}
		});
		txtPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassword.getText().equals("Contraseña") && !passChanged){
					txtPassword.setText("");
					passChanged = true;
					if(btnMostrarPass.getIcon().equals(pOculta))
						txtPassword.setEchoChar('●');
					txtPassword.setForeground(Color.black);
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().equals("")){
					txtPassword.setText("Contraseña");
					passChanged = false;
					txtPassword.setEchoChar((char) 0);
					txtPassword.setForeground(Color.gray);
				}
			}
		});
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPassword.setForeground(Color.gray);
		txtPassword.setEchoChar((char) 0);
		txtPassword.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtPassword.setBounds(60, 230, 225, 30);
		panelInferior.add(txtPassword);

		btnMostrarPass = new JButton(pOculta);
		btnMostrarPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passChanged){
					if(txtPassword.getEchoChar() == '●'){
						txtPassword.setEchoChar((char) 0);
						btnMostrarPass.setIcon(pVisible);
					}
					else{
						txtPassword.setEchoChar('●');
						btnMostrarPass.setIcon(pOculta);
					}
				}
				else
					btnMostrarPass.setIcon(pOculta);
			}
		});
		btnMostrarPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMostrarPass.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMostrarPass.setContentAreaFilled(false);
			}
		});
		btnMostrarPass.setModel(new MyButtonModel());
		btnMostrarPass.setBounds(285, 230, 35, 30);
		btnMostrarPass.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnMostrarPass.setBackground(colorAzul);
		btnMostrarPass.setFocusable(false);
		btnMostrarPass.setContentAreaFilled(false);
		panelInferior.add(btnMostrarPass);

		btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnIniciarSesion.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnIniciarSesion.setBackground(colorAzul);
			}
		});
		btnIniciarSesion.setModel(new MyButtonModel());
		btnIniciarSesion.setBounds(60, 305, 260, 30);
		btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 16));
		btnIniciarSesion.setBackground(colorAzul);
		btnIniciarSesion.setForeground(Color.black);
		btnIniciarSesion.setFocusable(false);
		btnIniciarSesion.setBorderPainted(false);
		panelInferior.add(btnIniciarSesion);

		try {
			listaUsuarios = userServices.selectAllUsers();
			if(listaUsuarios.size()==0)
				userServices.insertUser("Yankiel Yong Martínez", "el_yanko", "yankiel123", 1);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		iniciarPatricia();
	}
	
	@SuppressWarnings("deprecation")
	private void iniciarSesion(){
		este.setVisible(false);
		String usuario = "";
		String pass = "";
		try{
			if(userChanged) usuario = txtUsuario.getText();
			if(passChanged) pass = txtPassword.getText();
			Validaciones.usuario(null, usuario, pass);
			boolean esta = false;
			for(int i=0; i<listaUsuarios.size() && !esta; i++){
				UserDTO u = listaUsuarios.get(i);
				if(u.getUserNick().equals(usuario)){
					if(MD5.encrypt(pass).equals(u.getUserPassword())){
						esta = true;
						user = u;
					}
					else{
						throw new IllegalArgumentException("La contraseña es incorrecta, rectifique");
					}
				}
			}
			if(esta){
				este.dispose();
				Principal p = new Principal(user);
				p.setVisible(true);
			}
			else
				throw new IllegalArgumentException("El usuario no existe en la base de datos");
		}
		catch(IllegalArgumentException e1){
			MensajeAviso ma = new MensajeAviso(este, null, null, e1.getMessage(), MensajeAviso.ERROR);
			if(e1.getMessage().equals("La contraseña debe tener al menos caracteres")){
				ma.agrandar(15);
			}
			ma.setVisible(true);
		}
	}
	
	private void realizarAccion(int numTxt, int key){
		switch(numTxt) {
		case 1:
			switch(key){
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER: txtPassword.requestFocus(); break;
			}
			break;
		case 2:
			switch(key){
			case KeyEvent.VK_UP: txtUsuario.requestFocus(); break;
			case KeyEvent.VK_ENTER: iniciarSesion(); break;
			}
			break;
		}
	}
	
	private void iniciarYankiel(){
		UserDTO usu = null;
		for(int i=0; i<listaUsuarios.size(); i++){
			UserDTO u = listaUsuarios.get(i);
			if(u.getUserNick().equals("el_yanko")){
				usu = u;
				break;
			}
		}
		dispose();
		Principal p = new Principal(usu);
		p.setVisible(true);
	}
	
	private void iniciarPatricia(){
		UserDTO usu = null;
		for(int i=0; i<listaUsuarios.size(); i++){
			UserDTO u = listaUsuarios.get(i);
			if(u.getUserNick().equals("patrybarrios")){
				usu = u;
				break;
			}
		}
		dispose();
		Principal p = new Principal(usu);
		p.setVisible(true);
	}
	
}