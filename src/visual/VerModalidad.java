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

import dto.CostPerEstablishedToursDTO;
import dto.CostPerHourKilometerDTO;
import dto.CostPerKilometerDTO;
import dto.TransportModalityDTO;
import services.CostPerEstablishedToursServices;
import services.CostPerHourKilometerServices;
import services.CostPerKilometerServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class VerModalidad extends MiJPanel{
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	private CostPerHourKilometerServices costPerHourKilometerServices = ServicesLocator.getCostPerHourKilometerServices();
	private CostPerEstablishedToursServices costPerEstablishedToursServices = ServicesLocator.getCostPerEstablishedToursServices();
	private CostPerKilometerServices costPerKilometerServices = ServicesLocator.getCostPerKilometerServices();

	private CostPerHourKilometerDTO h;
	private CostPerEstablishedToursDTO t;
	private CostPerKilometerDTO k;

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;

	private Principal padre;
	private Gestion anterior;
	private VerModalidad este;
	private TransportModalityDTO mod;

	private String type;

	public VerModalidad(Principal p, Gestion a, TransportModalityDTO r){
		este = this;
		padre = p;
		anterior = a;
		mod=r;
		type=mod.getModalityType();
		setTipoPanel(Paneles.PANEL_VER_MODALIDAD);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelVerModalidad(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-206, 442, 400);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Información Modalidad");
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
		panelInferior.setBounds(1, 31, 440, 368);
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

		JLabel codigo = new JLabel("Código: "+mod.getModalityCode());
		codigo.setBounds(50, 110, 290, 30);
		codigo.setForeground(Color.black);
		codigo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(codigo);

		JLabel tipo = new JLabel("Tipo de Modalidad: "+mod.getModalityType());
		tipo.setBounds(50, 160, 420, 30);
		tipo.setForeground(Color.black);
		tipo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(tipo);

		if(type.equals("Costo por kilometraje"))
			costoPorKilometraje();
		else if(type.equals("Costo por horas y kilómetros"))
			costoHorasRecorridas();
		else{
			costoPorRecorrido();
		}
	}
	private void costoPorKilometraje(){
		try {
			k=costPerKilometerServices.findCostPerKilometer(mod.getModalityCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		JLabel km = new JLabel("Costo por kilómetro: "+k.getCostPerKm());
		km.setBounds(50, 210, 340, 30);
		km.setForeground(Color.black);
		km.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(km);

		JLabel idaVuelta = new JLabel("Costo por kilómetro ida y vuelta: "+k.getCostPerKmRoundTrip());
		idaVuelta.setBounds(50, 260, 340, 30);
		idaVuelta.setForeground(Color.black);
		idaVuelta.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(idaVuelta);

		JLabel horas = new JLabel("Costo por horas de espera: "+k.getCostPerWaitingHours());
		horas.setBounds(50, 310, 340, 30);
		horas.setForeground(Color.black);
		horas.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(horas);

	}

	private void costoHorasRecorridas(){
		try {
			h=costPerHourKilometerServices.findCostPerHourKilometer(mod.getModalityCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		JLabel kmRecorrido = new JLabel("Costo por kilómetro recorrido: "+h.getCostPerKmTraveled());
		kmRecorrido.setBounds(50, 210, 340, 30);
		kmRecorrido.setForeground(Color.black);
		kmRecorrido.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(kmRecorrido);

		JLabel horas = new JLabel("Costo por horas: "+h.getCostPerHour());
		horas.setBounds(50, 260, 340, 30);
		horas.setForeground(Color.black);
		horas.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(horas);

		JLabel kmExtras = new JLabel("Costo por kilómetros extras: "+h.getCostForExtraKm());
		kmExtras.setBounds(50, 310, 340, 30);
		kmExtras.setForeground(Color.black);
		kmExtras.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(kmExtras);

		JLabel horasExtras = new JLabel("Costo por horas extras: "+h.getCostForExtraHours());
		horasExtras.setBounds(50, 360, 340, 30);
		horasExtras.setForeground(Color.black);
		horasExtras.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(horasExtras);

	}
	private void costoPorRecorrido(){
		try {
			t=costPerEstablishedToursServices.findCostPerEstablishedTours(mod.getModalityCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		setBounds(pantalla.width/2-246, pantalla.height/2-226, 492, 402);
		btnCerrar.setBounds(445, 0, 45, 30);
		panelSuperior.setBounds(1, 1, 490, 30);
		panelInferior.setBounds(1, 31, 490, 370);
		JLabel desc = new JLabel("Descripción del recorrido: "+t.getTourDescription());
		desc.setBounds(50, 210, 340, 30);
		desc.setForeground(Color.black);
		desc.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(desc);

		JLabel recorrido = new JLabel("Costo por recorrido: "+t.getCostPerTour());
		recorrido.setBounds(50, 260, 340, 30);
		recorrido.setForeground(Color.black);
		recorrido.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(recorrido);

		JLabel idaVuelta = new JLabel("Costo por ida y vuelta: "+t.getCostPerTourRoundTrip());
		idaVuelta.setBounds(50, 310, 340, 30);
		idaVuelta.setForeground(Color.black);
		idaVuelta.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(idaVuelta);

	}

}