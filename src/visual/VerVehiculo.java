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

import dto.VehicleDTO;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class VerVehiculo extends MiJPanel {

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
	private VerVehiculo este;
	private VehicleDTO vehiculo;

	public VerVehiculo(Principal p, Gestion a, VehicleDTO v){
		este = this;
		padre = p;
		vehiculo=v;
		anterior = a;
		setTipoPanel(Paneles.PANEL_VER_VEHICULO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelVerVehiculo(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-236, 442, 422);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Información Vehículo");
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
		panelInferior.setBounds(1, 31, 440, 390);
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

		img = new ImageIcon(getClass().getResource("/visual/imagenes/taxi.png"));
		image = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Icon iconTaxi = new ImageIcon(image);

		JLabel lblTaxi = new JLabel(iconTaxi);
		lblTaxi.setBounds(60, 0, 100, 100);
		panelInferior.add(lblTaxi);

		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(200, 61, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(190, 15, 200, 61);
		panelInferior.add(logo);

		JLabel codigo = new JLabel("Código: "+vehiculo.getVehicleCode());
		codigo.setBounds(50, 100, 340, 30);
		codigo.setForeground(Color.black);
		codigo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(codigo);

		JLabel chapa = new JLabel("Chapa: "+vehiculo.getVehiclePlate());
		chapa.setBounds(50, 140, 340, 30);
		chapa.setForeground(Color.black);
		chapa.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(chapa);

		JLabel marca = new JLabel("Marca: "+vehiculo.getVehicleBrand());
		marca.setBounds(50, 180, 340, 30);
		marca.setForeground(Color.black);
		marca.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(marca);

		JLabel fabricacion = new JLabel("Año de Fabricación: "+vehiculo.getYearOfProduction());
		fabricacion.setBounds(50, 220, 340, 30);
		fabricacion.setForeground(Color.black);
		fabricacion.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(fabricacion);

		JLabel capSin = new JLabel("Capacidad Sin Equipajes: "+vehiculo.getCapacityWithoutLuggage());
		capSin.setBounds(50, 260, 340, 30);
		capSin.setForeground(Color.black);
		capSin.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(capSin);

		JLabel capCon = new JLabel("Capacidad Con Equipajes: "+vehiculo.getCapacityWithLuggage());
		capCon.setBounds(50, 300, 340, 30);
		capCon.setForeground(Color.black);
		capCon.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(capCon);

		JLabel capTotal = new JLabel("Capacidad Total: "+vehiculo.getTotalCapacity());
		capTotal.setBounds(50, 340, 340, 30);
		capTotal.setForeground(Color.black);
		capTotal.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(capTotal);
	}
}
