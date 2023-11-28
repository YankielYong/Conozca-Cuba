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

public class InfoPaquete extends JPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JPanel panelInferior;
	
	private InfoPaquete este;

	public InfoPaquete(){
		este = this;
		setBounds(pantalla.width/2-501, pantalla.height/2-326, 1002, 602);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 1000, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
		Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon iconCerrar = new ImageIcon(image);

		btnCerrar = new JButton(iconCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		btnCerrar.setBounds(955, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);
		
		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 1000, 570);
		panelInferior.setBackground(Color.white);
		add(panelInferior);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/paquete turistico.png"));
		image = img.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		Icon iconPaquete = new ImageIcon(image);
		
		JLabel fotoPaquete = new JLabel(iconPaquete);
		fotoPaquete.setBounds(190, 30, 120, 120);
		panelInferior.add(fotoPaquete);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(400, 122, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(530, 30, 400, 122);
		panelInferior.add(logo);
		
		JLabel nombre = new JLabel("Nombre: ");
		nombre.setBounds(50, 180, 400, 30);
		nombre.setForeground(Color.black);
		nombre.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(nombre);
		
		JLabel cantDias = new JLabel("Cantidad de Días: ");
		cantDias.setBounds(50, 220, 200, 30);
		cantDias.setForeground(Color.black);
		cantDias.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(cantDias);
		
		JLabel cantNoches = new JLabel("Cantidad de Noches: ");
		cantNoches.setBounds(280, 220, 200, 30);
		cantNoches.setForeground(Color.black);
		cantNoches.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(cantNoches);
		
		JLabel cantPersonas = new JLabel("Cantidad de Personas: ");
		cantPersonas.setBounds(50, 260, 400, 30);
		cantPersonas.setForeground(Color.black);
		cantPersonas.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(cantPersonas);
		
		JLabel hotel = new JLabel("Hotel: ");
		hotel.setBounds(50, 300, 400, 30);
		hotel.setForeground(Color.black);
		hotel.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(hotel);
		
		JLabel habitacion = new JLabel("Tipo de Habitación: ");
		habitacion.setBounds(50, 340, 400, 30);
		habitacion.setForeground(Color.black);
		habitacion.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(habitacion);
		
		JLabel planAlimenticio = new JLabel("Plan Alimenticio: ");
		planAlimenticio.setBounds(50, 380, 400, 30);
		planAlimenticio.setForeground(Color.black);
		planAlimenticio.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(planAlimenticio);
		
		JLabel precioHotel = new JLabel("Precio Total del Hotel: ");
		precioHotel.setBounds(50, 420, 400, 30);
		precioHotel.setForeground(Color.black);
		precioHotel.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(precioHotel);
		
		JLabel precioHA = new JLabel("Precio del Transporte Hotel-Aereopuerto: ");
		precioHA.setBounds(50, 460, 400, 30);
		precioHA.setForeground(Color.black);
		precioHA.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(precioHA);
		
		JLabel precioTransp = new JLabel("Precio Total de Transportes: ");
		precioTransp.setBounds(50, 500, 400, 30);
		precioTransp.setForeground(Color.black);
		precioTransp.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(precioTransp);
		
		JLabel actividades = new JLabel("Plan de Actividades Diarias: ");
		actividades.setBounds(600, 180, 300, 30);
		actividades.setForeground(Color.black);
		actividades.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(actividades);
		
		JLabel precioTotal = new JLabel("Precio Total del Paquete: ");
		precioTotal.setBounds(600, 500, 400, 30);
		precioTotal.setForeground(Color.black);
		precioTotal.setFont(new Font("Arial", Font.BOLD, 18));
		panelInferior.add(precioTotal);
	}
}
