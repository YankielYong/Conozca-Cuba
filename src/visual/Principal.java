package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.MyButtonModel;
import utils.Paneles;

public class Principal extends JFrame{
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	
	private Principal este;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	private Color colorFondoBotones = new Color(58, 239, 235);
	private int panelAbierto;
	
	private JPanel contentPane;
	
	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JButton btnMinimizar;
	private JButton btnPerfil;
	private JButton btnReservas;
	
	private JPanel panelPrincipal;
	
	/*
	 * Paneles
	 */
	private Descubrir panelDescubrir;
	private Perfil panelPerfil;
	
	public Principal(){
		este = this;
		setUndecorated(true);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, pantalla.width, pantalla.height);
		
		contentPane = new JPanel(null);
		setContentPane(contentPane);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(0, 0, pantalla.width, 50);
		panelSuperior.setBackground(colorAzul);
		contentPane.add(panelSuperior);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/minimize.png"));
        Image image = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        Icon iconMinimizar = new ImageIcon(image);
        
        btnMinimizar = new JButton(iconMinimizar);
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
        btnMinimizar.setModel(new MyButtonModel());
        btnMinimizar.setBounds(pantalla.width-150, 0, 75, 50);
        btnMinimizar.setBackground(colorFondoBotones);
        btnMinimizar.setFocusable(false);
        btnMinimizar.setBorderPainted(false);
        btnMinimizar.setContentAreaFilled(false);
		panelSuperior.add(btnMinimizar);
        
        img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
        image = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        Icon iconCerrar = new ImageIcon(image);
		
		btnCerrar = new JButton(iconCerrar);
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
		btnCerrar.setModel(new MyButtonModel());
		btnCerrar.setBounds(pantalla.width-75, 0, 75, 50);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		panelSuperior.add(btnCerrar);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/usuario.png"));
        image = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        Icon iconPerfil = new ImageIcon(image);
		
		btnPerfil = new JButton(iconPerfil);
		btnPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(panelAbierto){
				case 0:
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
					panelAbierto = Paneles.PANEL_PERFIL;
					break;
				case 2: 
					panelPrincipal.remove(panelDescubrir);
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
					panelAbierto = Paneles.PANEL_PERFIL;
					break;
				}
			}
		});
		btnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPerfil.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPerfil.setContentAreaFilled(false);
			}
		});
		btnPerfil.setModel(new MyButtonModel());
		btnPerfil.setBounds(0, 0, 75, 50);
		btnPerfil.setBackground(colorFondoBotones);
		btnPerfil.setFocusable(false);
		btnPerfil.setBorderPainted(false);
		btnPerfil.setContentAreaFilled(false);
		panelSuperior.add(btnPerfil);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/reservas.png"));
        image = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Icon iconReservas = new ImageIcon(image);
		
		btnReservas = new JButton(iconReservas);
		btnReservas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(panelAbierto){
				case 0:
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
					panelAbierto = Paneles.PANEL_DESCUBRIR;
					break;
				case 1:
					panelPrincipal.remove(panelPerfil);
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
					panelAbierto = Paneles.PANEL_DESCUBRIR;
					break;
				}
			}
		});
		btnReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservas.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReservas.setContentAreaFilled(false);
			}
		});
		btnReservas.setModel(new MyButtonModel());
		btnReservas.setBounds(75, 0, 75, 50);
		btnReservas.setBackground(colorFondoBotones);
		btnReservas.setFocusable(false);
		btnReservas.setBorderPainted(false);
		btnReservas.setContentAreaFilled(false);
		panelSuperior.add(btnReservas);
		
		panelPrincipal = new JPanel(null){
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/visual/imagenes/principal.jpg"));
				g.drawImage(img, 0, 0, panelPrincipal.getWidth(), panelPrincipal.getHeight(), this);
			}
		};
		panelPrincipal.setBounds(0, 50, pantalla.width, pantalla.height-50);
		contentPane.add(panelPrincipal);
	}
	
	public void setPanelAbierto(int panel){
		panelAbierto = panel;
	}
	
	public JPanel getPanelPrincipal(){
		return panelPrincipal;
	}

}
