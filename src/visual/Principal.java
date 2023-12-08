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
	private JButton btnDescubrir;
	private JButton btnGestion;
	
	private JPanel panelPrincipal;
	
	/*
	 * Paneles
	 */
	private Perfil panelPerfil;
	private Descubrir panelDescubrir;
	private Gestion panelGestion;
	private AgregarUsuario panelAgregarUsuario;
	private AgregarHotel panelAgregarHotel;
	private AgregarProvincia panelAgregarProvincia;
	private AgregarCadenaHotelera panelAgregarCadenaHotelera;
	private InfoHotel panelInfoHotel;
	private InfoPaquete panelInfoPaquete;
	
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
				if(panelAbierto == 0){
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_DESCUBRIR){
					panelPrincipal.remove(panelDescubrir);
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_GESTION){
					panelPrincipal.remove(panelGestion);
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_USUARIO){
					panelPrincipal.remove(panelAgregarUsuario);
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_HOTEL){
					panelPrincipal.remove(panelAgregarHotel);
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_PROVINCIA){
					panelPrincipal.remove(panelAgregarProvincia);
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_CADENA_HOTELERA){
					panelPrincipal.remove(panelAgregarCadenaHotelera);
					panelPerfil = new Perfil(este);
					panelPrincipal.add(panelPerfil);
					panelPrincipal.repaint();
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
		
		btnDescubrir = new JButton(iconReservas);
		btnDescubrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(panelAbierto == 0){
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_PERFIL){
					panelPrincipal.remove(panelPerfil);
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_GESTION){
					panelPrincipal.remove(panelGestion);
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_USUARIO){
					panelPrincipal.remove(panelAgregarUsuario);
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_HOTEL){
					panelPrincipal.remove(panelAgregarHotel);
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_PROVINCIA){
					panelPrincipal.remove(panelAgregarProvincia);
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_CADENA_HOTELERA){
					panelPrincipal.remove(panelAgregarCadenaHotelera);
					panelDescubrir = new Descubrir(este);
					panelPrincipal.add(panelDescubrir);
					panelPrincipal.repaint();
				}
			}
		});
		btnDescubrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDescubrir.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDescubrir.setContentAreaFilled(false);
			}
		});
		btnDescubrir.setModel(new MyButtonModel());
		btnDescubrir.setBounds(75, 0, 75, 50);
		btnDescubrir.setBackground(colorFondoBotones);
		btnDescubrir.setFocusable(false);
		btnDescubrir.setBorderPainted(false);
		btnDescubrir.setContentAreaFilled(false);
		panelSuperior.add(btnDescubrir);
		
		btnGestion = new JButton();
		btnGestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(panelAbierto == 0){
					panelGestion = new Gestion(este);
					panelPrincipal.add(panelGestion);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_PERFIL){
					panelPrincipal.remove(panelPerfil);
					panelGestion = new Gestion(este);
					panelPrincipal.add(panelGestion);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_DESCUBRIR){
					panelPrincipal.remove(panelDescubrir);
					panelGestion = new Gestion(este);
					panelPrincipal.add(panelGestion);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_USUARIO){
					panelPrincipal.remove(panelAgregarUsuario);
					panelGestion = new Gestion(este);
					panelPrincipal.add(panelGestion);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_HOTEL){
					panelPrincipal.remove(panelAgregarHotel);
					panelGestion = new Gestion(este);
					panelPrincipal.add(panelGestion);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_PROVINCIA){
					panelPrincipal.remove(panelAgregarProvincia);
					panelGestion = new Gestion(este);
					panelPrincipal.add(panelGestion);
					panelPrincipal.repaint();
				}
				else if(panelAbierto == Paneles.PANEL_AGREGAR_CADENA_HOTELERA){
					panelPrincipal.remove(panelAgregarCadenaHotelera);
					panelGestion = new Gestion(este);
					panelPrincipal.add(panelGestion);
					panelPrincipal.repaint();
				}
			}
		});
		btnGestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGestion.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGestion.setContentAreaFilled(false);
			}
		});
		btnGestion.setModel(new MyButtonModel());
		btnGestion.setBounds(150, 0, 75, 50);
		btnGestion.setBackground(colorFondoBotones);
		btnGestion.setFocusable(false);
		btnGestion.setBorderPainted(false);
		btnGestion.setContentAreaFilled(false);
		
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
		
		vistaGestorAgencia();
	}
	
	private void vistaGestorVentas(){
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/gestion venta.png"));
        Image image = img.getImage().getScaledInstance(38, 38, Image.SCALE_SMOOTH);
        Icon iconGestion = new ImageIcon(image);
		
		btnGestion.setIcon(iconGestion);
		panelSuperior.add(btnGestion);
	}
	
	private void vistaGestorAgencia(){
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/gestion agencia.png"));
        Image image = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        Icon iconGestion = new ImageIcon(image);
		
		btnGestion.setIcon(iconGestion);
		panelSuperior.add(btnGestion);
	}
	
	public void setPanelAgregarUsuario(AgregarUsuario agregarUsuario) {
		this.panelAgregarUsuario = agregarUsuario;
	}

	public void setPanelAgregarHotel(AgregarHotel panelAgregarHotel) {
		this.panelAgregarHotel = panelAgregarHotel;
	}

	public void setPanelAgregarProvincia(AgregarProvincia panelAgregarProvincia) {
		this.panelAgregarProvincia = panelAgregarProvincia;
	}

	public void setPanelAgregarCadenaHotelera(
			AgregarCadenaHotelera panelAgregarCadenaHotelera) {
		this.panelAgregarCadenaHotelera = panelAgregarCadenaHotelera;
	}

	public void setPanelAbierto(int panel){
		panelAbierto = panel;
	}
	
	public JPanel getPanelPrincipal(){
		return panelPrincipal;
	}

}
