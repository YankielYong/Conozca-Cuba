package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.MyButtonModel;

public class Perfil extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JLabel nombre;
	private JLabel nombreUsuario;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	private JButton btnCerrar;
	private JButton btnCerrarSesion;
	
	public Perfil(){
	setBounds(pantalla.width/2-201, pantalla.height/2-201, 432, 392);	
	setBackground(Color.darkGray);
	setLayout(null);
	
	panelSuperior = new JPanel(null);
	panelSuperior.setBounds(1, 1, 430, 30);
	panelSuperior.setBackground(colorAzul);
	add(panelSuperior);
	
	ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
	Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
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
	btnCerrar.setBounds(385, 0, 45, 30);
	btnCerrar.setBackground(Color.red);
	btnCerrar.setIcon(iconCerrar);
	btnCerrar.setFocusable(false);
	btnCerrar.setBorderPainted(false);
	btnCerrar.setContentAreaFilled(false);
	btnCerrar.setModel(new MyButtonModel());
	panelSuperior.add(btnCerrar);
	
	
	
	panelInferior = new JPanel(null);
	panelInferior.setBounds(1, 31, 430, 360);
	panelInferior.setBackground(Color.white);
	add(panelInferior);
	
	img = new ImageIcon(getClass().getResource("/visual/imagenes/usuarioLogin.png"));
	image = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	Icon iconUsuario = new ImageIcon(image);

	JLabel lUsuario = new JLabel(iconUsuario);
	lUsuario.setBounds(173, 33, 80, 80);
	panelInferior.add(lUsuario);
	
	nombre = new JLabel("Nombre: ");
	nombre.setBounds(62, 206, 82, 20);
	nombre.setFont(new Font("Arial", Font.BOLD, 18));
	nombre.setForeground(Color.black);
	panelInferior.add(nombre);
	
	nombreUsuario = new JLabel("Usuario: ");
	nombreUsuario.setBounds(62, 138, 100, 20);
	nombreUsuario.setFont(new Font("Arial", Font.BOLD, 18));
	nombreUsuario.setForeground(Color.black);
	panelInferior.add(nombreUsuario);
	
	btnCerrarSesion = new JButton("Cerrar Sesión");
	btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 13));
	btnCerrarSesion.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	});
	btnCerrarSesion.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			btnCerrarSesion.setBackground(new Color(40, 113, 128));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnCerrarSesion.setBackground(colorAzul);
		}
	});
	btnCerrarSesion.setModel(new MyButtonModel());
	btnCerrarSesion.setBounds(85, 280, 260, 30);
	btnCerrarSesion.setBackground(colorAzul);
	btnCerrarSesion.setForeground(Color.black);
	btnCerrarSesion.setFocusable(false);
	btnCerrarSesion.setBorderPainted(false);
	panelInferior.add(btnCerrarSesion);
	
	}
	
	
}
