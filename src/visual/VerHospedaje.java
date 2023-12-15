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
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import services.FoodPlanServices;
import services.HotelServices;
import services.RoomServices;
import services.SeasonServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import dto.FoodPlanDTO;
import dto.HotelDTO;
import dto.LodgingDTO;
import dto.RoomDTO;
import dto.SeasonDTO;

public class VerHospedaje extends MiJPanel{
	private RoomServices roomServices = ServicesLocator.getRoomServices();
	private HotelServices hotelServices = ServicesLocator.getHotelServices();
	private SeasonServices seasonServices = ServicesLocator.getSeasonServices();
	private FoodPlanServices foodPlanServices = ServicesLocator.getFoodPlanServices();

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;

	private Principal padre;
	private Gestion anterior;
	private VerHospedaje este;
	private LodgingDTO aloj;
	private HotelDTO h;
	private RoomDTO habit;
	private SeasonDTO t;
	private FoodPlanDTO planA;

	public VerHospedaje(Principal p, Gestion a, LodgingDTO r){
		este = this;
		padre = p;
		anterior = a;
		aloj=r;
		buscarPlanAlimenticio();
		setTipoPanel(Paneles.PANEL_VER_HOSPEDAJE);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelVerHospedaje(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-226, 442, 402);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Información Hospedaje");
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
		btnCerrar.setBounds(395, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 440, 370);
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
		logo.setBounds(90, 10, 250, 76);
		panelInferior.add(logo);

		JLabel codigo = new JLabel("Código: "+aloj.getLodgingCode());
		codigo.setBounds(50, 110, 290, 30);
		codigo.setForeground(Color.black);
		codigo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(codigo);
		
		JLabel hot = new JLabel("Hotel: "+h.getHotelName());
		hot.setBounds(50, 150, 290, 30);
		hot.setForeground(Color.black);
		hot.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(hot);
		
		JLabel temp = new JLabel("Temporada: "+t.getSeasonName());
		temp.setBounds(50, 190, 290, 30);
		temp.setForeground(Color.black);
		temp.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(temp);

		JLabel hab = new JLabel("Habitación: "+habit.getRoomType());
		hab.setBounds(50, 230, 290, 30);
		hab.setForeground(Color.black);
		hab.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(hab);
		
		JLabel plan = new JLabel("Plan Alimenticio: "+planA.getTypeOfFoodPlan());
		plan.setBounds(50, 270, 290, 30);
		plan.setForeground(Color.black);
		plan.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(plan);

		JLabel precio = new JLabel("Precio: "+aloj.getLodgingPrice());
		precio.setBounds(50, 310, 290, 30);
		precio.setForeground(Color.black);
		precio.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(precio);

	}
	private void buscarPlanAlimenticio(){
		try {
			h = hotelServices.findHotel(aloj.getHotelCode());
			t = seasonServices.findSeason(aloj.getSeasonCode());
			habit = roomServices.findRoom(aloj.getRoomCode());
			planA = foodPlanServices.findFoodPlan(habit.getFoodPlanCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
