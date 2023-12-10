package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import services.HotelServices;
import services.LodgingServices;
import services.RoomServices;
import services.SeasonServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.Validaciones;

public class AgregarHospedaje extends MiJPanel{
	
	private HotelServices hotelServices = ServicesLocator.getHotelServices();
	private LodgingServices lodgingServices = ServicesLocator.getLodgingServices();
	private RoomServices roomServices = ServicesLocator.getRoomServices();
	private SeasonServices seasonServices = ServicesLocator.getSeasonServices();
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtHotel;
	private JButton btnHotel;
	private JTextField txtHabitacion;
	private JButton btnHabitacion;
	private JTextField txtTemporada;
	private JButton btnTemporada;
	private JTextField txtPrecio;
	private JButton btnAgregar;
	
	private Principal padre;
	private Gestion anterior;
	private AgregarHospedaje este;
	
	public AgregarHospedaje(Principal p, Gestion a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_HOSPEDAJE);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarHospedaje(este);
		setBounds(pantalla.width/2-201, pantalla.height/2-231, 402, 412);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 400, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Transporte");
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
		panelInferior.setBounds(1, 31, 400, 380);
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
		
		JLabel hot = new JLabel("Código del Hotel:");
		hot.setBounds(50, 110, 130, 30);
		hot.setForeground(Color.black);
		hot.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(hot);
		
		txtHotel = new JTextField();
		txtHotel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtHotel.setBounds(180, 110, 110, 30);
		txtHotel.setForeground(Color.black);
		txtHotel.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHotel.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtHotel);
		
		btnHotel = new JButton("Ver");
		btnHotel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnHotel.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarHoteles panel = new ConsultarHoteles(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnHotel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHotel.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHotel.setBackground(colorAzul);
			}
		});
		btnHotel.setBounds(300, 110, 50, 30);
		btnHotel.setModel(new MyButtonModel());
		btnHotel.setFont(new Font("Arial", Font.PLAIN, 15));
		btnHotel.setMargin(new Insets(2, 5, 2, 5));
		btnHotel.setBackground(colorAzul);
		btnHotel.setForeground(Color.black);
		btnHotel.setFocusable(false);
		btnHotel.setBorderPainted(false);
		panelInferior.add(btnHotel);
		
		JLabel hab = new JLabel("Código de la Habitación:");
		hab.setBounds(50, 160, 180, 30);
		hab.setForeground(Color.black);
		hab.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(hab);
		
		txtHabitacion = new JTextField();
		txtHabitacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtHabitacion.setBounds(230, 160, 60, 30);
		txtHabitacion.setForeground(Color.black);
		txtHabitacion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHabitacion.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtHabitacion);
		
		btnHabitacion = new JButton("Ver");
		btnHabitacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnHabitacion.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarHabitaciones panel = new ConsultarHabitaciones(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnHabitacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHabitacion.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHabitacion.setBackground(colorAzul);
			}
		});
		btnHabitacion.setBounds(300, 160, 50, 30);
		btnHabitacion.setModel(new MyButtonModel());
		btnHabitacion.setFont(new Font("Arial", Font.PLAIN, 15));
		btnHabitacion.setMargin(new Insets(2, 5, 2, 5));
		btnHabitacion.setBackground(colorAzul);
		btnHabitacion.setForeground(Color.black);
		btnHabitacion.setFocusable(false);
		btnHabitacion.setBorderPainted(false);
		panelInferior.add(btnHabitacion);
		
		JLabel temp = new JLabel("Código de la Temporada:");
		temp.setBounds(50, 210, 185, 30);
		temp.setForeground(Color.black);
		temp.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(temp);
		
		txtTemporada = new JTextField();
		txtTemporada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtTemporada.setBounds(235, 210, 55, 30);
		txtTemporada.setForeground(Color.black);
		txtTemporada.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTemporada.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtTemporada);
		
		btnTemporada = new JButton("Ver");
		btnTemporada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTemporada.setBackground(colorAzul);
				padre.getPanelPrincipal().remove(este);
				ConsultarTemporadas panel = new ConsultarTemporadas(padre, este);
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnTemporada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTemporada.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnTemporada.setBackground(colorAzul);
			}
		});
		btnTemporada.setBounds(300, 210, 50, 30);
		btnTemporada.setModel(new MyButtonModel());
		btnTemporada.setFont(new Font("Arial", Font.PLAIN, 15));
		btnTemporada.setMargin(new Insets(2, 5, 2, 5));
		btnTemporada.setBackground(colorAzul);
		btnTemporada.setForeground(Color.black);
		btnTemporada.setFocusable(false);
		btnTemporada.setBorderPainted(false);
		panelInferior.add(btnTemporada);
		
		JLabel pre = new JLabel("Precio:");
		pre.setBounds(50, 260, 55, 30);
		pre.setForeground(Color.black);
		pre.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(pre);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtPrecio.getText());
			}
		});
		txtPrecio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!txtPrecio.getText().equals("")){
					String ca = txtPrecio.getText();
					if(ca.charAt(ca.length()-1)=='.'){
						ca = ca.substring(0, ca.length()-1);
						txtPrecio.setText(ca);
					}
				}
			}
		});
		txtPrecio.setBounds(105, 260, 245, 30);
		txtPrecio.setForeground(Color.black);
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecio.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtPrecio);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					String ho = txtHotel.getText();
					String ha = txtHabitacion.getText();
					String se = txtTemporada.getText();
					String pr = txtPrecio.getText();
					Validaciones.hospedaje(ho, ha, se, pr);
					int hotel = Integer.valueOf(ho);
					int habitacion = Integer.valueOf(ha);
					int temporada = Integer.valueOf(se);
					double precio = Double.valueOf(pr);
					try{hotelServices.findHotel(hotel);} 
					catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
						throw new IllegalArgumentException("No existe ningún hotel con ese código");}
					try{roomServices.findRoom(habitacion);} 
					catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
						throw new IllegalArgumentException("No existe ninguna habitación con ese código");}
					try{seasonServices.findSeason(temporada);} 
					catch(IllegalArgumentException | ClassNotFoundException | SQLException e2){
						throw new IllegalArgumentException("No existe ninguna temporada con ese código");}
					lodgingServices.insertLodging(hotel, temporada, habitacion, precio);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El hospedaje fue agregado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerHospedajes();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
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
		btnAgregar.setModel(new MyButtonModel());
		btnAgregar.setBounds(50, 320, 300, 35);
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}

}
