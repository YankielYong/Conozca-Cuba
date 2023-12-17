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
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.ActivityDTO;
import dto.ContractDTO;
import dto.ContractEventDTO;
import dto.ContractLodgingDTO;
import dto.ContractTransportDTO;
import dto.EventDTO;
import dto.FoodPlanDTO;
import dto.HotelDTO;
import dto.LodgingDTO;
import dto.PlaceDTO;
import dto.RoomDTO;
import dto.SeasonDTO;
import dto.TouristPackageDTO;
import dto.TransportDTO;
import dto.TransportModalityDTO;
import dto.VehicleDTO;
import services.ActivityServices;
import services.ContractEventServices;
import services.ContractLodgingServices;
import services.ContractTransportServices;
import services.EventServices;
import services.FoodPlanServices;
import services.HotelServices;
import services.LodgingServices;
import services.PlaceServices;
import services.RoomServices;
import services.SeasonServices;
import services.ServicesLocator;
import services.TouristPackageServices;
import services.TransportModalityServices;
import services.TransportServices;
import services.VehicleServices;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class VerContrato extends MiJPanel{

	private ActivityServices activityServices = ServicesLocator.getActivityServices();
	private ContractEventServices contractEventServices = ServicesLocator.getContractEventServices();
	private ContractLodgingServices contractLodgingServices = ServicesLocator.getContractLodgingServices();
	private ContractTransportServices contractTransportServices = ServicesLocator.getContractTransportServices();
	private EventServices eventServices = ServicesLocator.getEventServices();
	private LodgingServices lodgingServices = ServicesLocator.getLodgingServices();
	private PlaceServices placeServices = ServicesLocator.getPlaceServices();
	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	private TransportServices transportServices = ServicesLocator.getTransportServices();
	private HotelServices hotelServices = ServicesLocator.getHotelServices();
	private SeasonServices seasonServices = ServicesLocator.getSeasonServices();
	private RoomServices roomServices = ServicesLocator.getRoomServices();
	private FoodPlanServices foodPlanServices = ServicesLocator.getFoodPlanServices();
	private VehicleServices vehicleServices = ServicesLocator.getVehicleServices();
	private TransportModalityServices transportModalityServices = ServicesLocator.getTransportModalityServices();

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
	private VerContrato este;
	private ContractDTO contrato;
	private TouristPackageDTO tp;

	public VerContrato(Principal p, Gestion a, ContractDTO cont){
		este = this;
		padre = p;
		anterior = a;
		contrato=cont;
		setTipoPanel(Paneles.PANEL_VER_CONTRATO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelVerContrato(este);
		setBounds(pantalla.width/2-226, pantalla.height/2-276, 452, 502);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 450, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Información Contrato");
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
		btnCerrar.setBounds(405, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 450, 470);
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

		JLabel codigo = new JLabel("Código: "+contrato.getContractCode());
		codigo.setBounds(50, 110, 290, 30);
		codigo.setForeground(Color.black);
		codigo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(codigo);

		JLabel tipo = new JLabel("Tipo de contrato: "+contrato.getContractType());
		tipo.setBounds(50, 140, 350, 30);
		tipo.setForeground(Color.black);
		tipo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(tipo);
		
		JLabel des = new JLabel("Descripción: "+contrato.getContractDescription());
		des.setBounds(50, 170, 350, 30);
		des.setForeground(Color.black);
		des.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(des);

		try {
			tp = touristPackageServices.findTouristPackage(contrato.getPackageCode());
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		JLabel paq = new JLabel("Paquete Turístico: "+tp.getPromotionalName());
		paq.setBounds(50, 200, 350, 30);
		paq.setForeground(Color.black);
		paq.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(paq);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		JLabel fechaCon = new JLabel("Fecha de conciliación:  "+
				format.format(contrato.getContractConciliationDate()));
		fechaCon.setBounds(50, 230, 350, 30);
		fechaCon.setForeground(Color.black);
		fechaCon.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(fechaCon);

		JLabel fechaIni = new JLabel("Fecha de inicio:  "+format.format(contrato.getContractStartDate()));
		fechaIni.setBounds(50, 260, 350, 30);
		fechaIni.setForeground(Color.black);
		fechaIni.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(fechaIni);

		JLabel fechaFin = new JLabel("Fecha de finalización:  "+format.format(contrato.getContractEndDate()));
		fechaFin.setBounds(50, 290, 350, 30);
		fechaFin.setForeground(Color.black);
		fechaFin.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(fechaFin);

		if(contrato.getContractType().equals("Hotelero")){
			hotelero();
		}
		else if(contrato.getContractType().equals("Transporte")){
			transporte();
		}
		else{
			complementario();
		}
		
		este.repaint();
	}

	private void hotelero(){
		try {
			ContractLodgingDTO cl = contractLodgingServices.findContractLodging(contrato.getContractCode());
			LodgingDTO l = lodgingServices.findLodging(cl.getLodgingCode());
			HotelDTO h = hotelServices.findHotel(l.getHotelCode());
			SeasonDTO s = seasonServices.findSeason(l.getSeasonCode());
			RoomDTO r = roomServices.findRoom(l.getRoomCode());
			FoodPlanDTO f = foodPlanServices.findFoodPlan(r.getFoodPlanCode());

			JLabel aloj = new JLabel("Hospedaje");
			aloj.setBounds(50, 350, 350, 30);
			aloj.setForeground(Color.black);
			aloj.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
			panelInferior.add(aloj);

			JLabel hotel = new JLabel("Hotel: "+h.getHotelName());
			hotel.setBounds(50, 390, 350, 30);
			hotel.setForeground(Color.black);
			hotel.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(hotel);

			JLabel hab = new JLabel("Habitación: "+r.getRoomType());
			hab.setBounds(50, 420, 350, 30);
			hab.setForeground(Color.black);
			hab.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(hab);

			JLabel plan = new JLabel("Plan Alimenticio: "+f.getTypeOfFoodPlan());
			plan.setBounds(50, 450, 350, 30);
			plan.setForeground(Color.black);
			plan.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(plan);

			JLabel temp = new JLabel("Temporada: "+s.getSeasonName());
			temp.setBounds(50, 480, 350, 30);
			temp.setForeground(Color.black);
			temp.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(temp);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		este.setBounds(pantalla.width/2-226, pantalla.height/2-311, 452, 572);
		panelInferior.setBounds(1, 31, 450, 540);
	}

	private void transporte(){
		try{
			ContractTransportDTO ct = contractTransportServices.findContractTransport(contrato.getContractCode());
			TransportDTO t = transportServices.findTransport(ct.getTransportCode());
			VehicleDTO v = vehicleServices.findVehicle(t.getVehicleCode());
			TransportModalityDTO tm = transportModalityServices.findTransportModality(t.getModalityCode());

			JLabel tra = new JLabel("Transporte");
			tra.setBounds(50, 350, 350, 30);
			tra.setForeground(Color.black);
			tra.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
			panelInferior.add(tra);
			
			JLabel veh = new JLabel("Vehículo: "+v.getVehicleBrand());
			veh.setBounds(50, 390, 350, 30);
			veh.setForeground(Color.black);
			veh.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(veh);

			JLabel mod = new JLabel("Modalidad: "+tm.getModalityType());
			mod.setBounds(50, 420, 350, 30);
			mod.setForeground(Color.black);
			mod.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(mod);

			JLabel tran = new JLabel("Transportista: "+t.getTransportBorrower());
			tran.setBounds(50, 450, 350, 30);
			tran.setForeground(Color.black);
			tran.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(tran);

		} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}

		este.setBounds(pantalla.width/2-226, pantalla.height/2-296, 452, 542);
		panelInferior.setBounds(1, 31, 450, 510);
	}

	private void complementario(){
		try {
			ContractEventDTO ce = contractEventServices.findContractEvent(contrato.getContractCode());
			EventDTO ev = eventServices.findEvent(ce.getEventCode());
			PlaceDTO p = placeServices.findPlace(ev.getPlaceCode());
			ActivityDTO a = activityServices.findActivity(ev.getActivityCode());

			JLabel eve = new JLabel("Evento");
			eve.setBounds(50, 350, 350, 30);
			eve.setForeground(Color.black);
			eve.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
			panelInferior.add(eve);

			JLabel lug = new JLabel("Lugar: "+p.getPlaceName());
			lug.setBounds(50, 390, 350, 30);
			lug.setForeground(Color.black);
			lug.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(lug);

			JLabel ac = new JLabel("Actividad: "+a.getActivityDescription());
			ac.setBounds(50, 420, 350, 30);
			ac.setForeground(Color.black);
			ac.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(ac);

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a");

			JLabel fec = new JLabel("Fecha:  "+format.format(a.getActivityDate())
					+"     "+format2.format(a.getActivityDate()));
			fec.setBounds(50, 450, 350, 30);
			fec.setForeground(Color.black);
			fec.setFont(new Font("Arial", Font.BOLD, 16));
			panelInferior.add(fec);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		este.setBounds(pantalla.width/2-226, pantalla.height/2-296, 452, 542);
		panelInferior.setBounds(1, 31, 450, 510);
	}
}
