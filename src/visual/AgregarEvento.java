package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import services.ActivityServices;
import services.EventServices;
import services.PlaceServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.Validaciones;

import java.awt.Insets;
import java.sql.SQLException;

public class AgregarEvento extends MiJPanel{
	
	private ActivityServices activityServices = ServicesLocator.getActivityServices();
	private EventServices eventServices = ServicesLocator.getEventServices();
	private PlaceServices placeServices = ServicesLocator.getPlaceServices();
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtLugar;
	private JButton btnLugares;
	private JButton btnActividades;
	private JTextField txtActividad;
	private JButton btnAgregar;
	
	private Principal padre;
	private Gestion anterior;
	private AgregarEvento este;
	
	public AgregarEvento(Principal p, Gestion a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_EVENTO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarEvento(este);
		setBounds(pantalla.width/2-201, pantalla.height/2-186, 402, 312);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 400, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Evento");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 200, 30);
		panelSuperior.add(lblNombre);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
		Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon iconCerrar = new ImageIcon(image);

		btnCerrar = new JButton(iconCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				padre.setPanelAbierto(0);
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
		btnCerrar.setBounds(355, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);
		
		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 400, 280);
		panelInferior.setBackground(Color.white);
		add(panelInferior);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/atras.png"));
		image = img.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		Icon iconAtras = new ImageIcon(image);
		
		btnAtras = new JButton(iconAtras);
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().add(anterior);
				padre.getPanelPrincipal().repaint();
				padre.setPanelAbierto(anterior.getTipoPanel());
			}
		});
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setContentAreaFilled(false);
			}
		});
		btnAtras.setBounds(5, 5, 40, 40);
		btnAtras.setBackground(colorAzul);
		btnAtras.setFocusable(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setModel(new MyButtonModel());
		panelInferior.add(btnAtras);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(250, 76, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(75, 10, 250, 76);
		panelInferior.add(logo);
		
		JLabel lugar = new JLabel("Código del Lugar:");
		lugar.setBounds(50, 110, 132, 30);
		lugar.setForeground(Color.black);
		lugar.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(lugar);
		
		txtLugar = new JTextField();
		txtLugar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtLugar.setBounds(182, 110, 108, 30);
		txtLugar.setForeground(Color.black);
		txtLugar.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLugar.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtLugar);
		
		btnLugares = new JButton("Ver");
		btnLugares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLugares.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarLugares panel = new ConsultarLugares(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnLugares.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLugares.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLugares.setBackground(colorAzul);
			}
		});
		btnLugares.setBounds(300, 110, 50, 30);
		btnLugares.setModel(new MyButtonModel());
		btnLugares.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLugares.setMargin(new Insets(2, 5, 2, 5));
		btnLugares.setBackground(colorAzul);
		btnLugares.setForeground(Color.black);
		btnLugares.setFocusable(false);
		btnLugares.setBorderPainted(false);
		panelInferior.add(btnLugares);
		
		JLabel actividad = new JLabel("Código de la Actividad:");
		actividad.setBounds(50, 160, 170, 30);
		actividad.setForeground(Color.black);
		actividad.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(actividad);
		
		txtActividad = new JTextField();
		txtActividad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtActividad.setBounds(220, 160, 70, 30);
		txtActividad.setForeground(Color.black);
		txtActividad.setFont(new Font("Arial", Font.PLAIN, 16));
		txtActividad.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtActividad);
		
		btnActividades = new JButton("Ver");
		btnActividades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnActividades.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarActividades panel = new ConsultarActividades(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnActividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnActividades.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnActividades.setBackground(colorAzul);
			}
		});
		btnActividades.setBounds(300, 160, 50, 30);
		btnActividades.setModel(new MyButtonModel());
		btnActividades.setFont(new Font("Arial", Font.PLAIN, 15));
		btnActividades.setMargin(new Insets(2, 5, 2, 5));
		btnActividades.setBackground(colorAzul);
		btnActividades.setForeground(Color.black);
		btnActividades.setFocusable(false);
		btnActividades.setBorderPainted(false);
		panelInferior.add(btnActividades);
		
		btnAgregar = new JButton("Agregar Evento");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					String lug = txtLugar.getText();
					String act = txtActividad.getText();
					Validaciones.evento(lug, act);
					int lugar = Integer.valueOf(lug);
					int activ = Integer.valueOf(act);
					try{
						placeServices.findPlace(lugar);
					} catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
						throw new IllegalArgumentException("No existe ningún lugar con ese código");
					}
					try{
						activityServices.findActivity(activ);
					} catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
						throw new IllegalArgumentException("No existe ninguna actividad con ese código");
					}
					eventServices.insertEvent(lugar, activ);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El evento fue registrado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerEventos();
				}
				catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					ma.setVisible(true);
				}
			}
		});
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAgregar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAgregar.setBackground(colorAzul);
			}
		});
		btnAgregar.setBounds(50, 220, 300, 35);
		btnAgregar.setModel(new MyButtonModel());
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}
}
