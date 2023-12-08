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
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class AgregarVehiculo extends MiJPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtChapa;
	private JTextField txtMarca;
	private JTextField txtFabricacion;
	private JTextField txtCapSinEq;
	private JTextField txtCapConEq;
	private JTextField txtCapTotal;
	private JButton btnAgregar;
	
	private Principal padre;
	private MiJPanel anterior;
	private AgregarVehiculo este;
	
	public AgregarVehiculo(Principal p, MiJPanel a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_VEHICULO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarVehiculo(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-251, 442, 452);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Vehículo");
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
		panelInferior.setBounds(1, 31, 440, 420);
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
		
		JLabel chapa = new JLabel("Chapa:");
		chapa.setBounds(50, 110, 60, 30);
		chapa.setForeground(Color.black);
		chapa.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(chapa);
		
		txtChapa = new JTextField();
		txtChapa.setBounds(110, 110, 280, 30);
		txtChapa.setForeground(Color.black);
		txtChapa.setFont(new Font("Arial", Font.PLAIN, 16));
		txtChapa.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtChapa);
		
		JLabel marca = new JLabel("Marca:");
		marca.setBounds(50, 150, 58, 30);
		marca.setForeground(Color.black);
		marca.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(marca);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(108, 150, 282, 30);
		txtMarca.setForeground(Color.black);
		txtMarca.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMarca.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtMarca);
		
		JLabel fabricacion = new JLabel("Año de Fabricación:");
		fabricacion.setBounds(50, 190, 150, 30);
		fabricacion.setForeground(Color.black);
		fabricacion.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(fabricacion);
		
		txtFabricacion = new JTextField();
		txtFabricacion.setBounds(200, 190, 190, 30);
		txtFabricacion.setForeground(Color.black);
		txtFabricacion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFabricacion.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtFabricacion);
		
		JLabel capSin = new JLabel("Capacidad Sin Equipajes:");
		capSin.setBounds(50, 230, 192, 30);
		capSin.setForeground(Color.black);
		capSin.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(capSin);
		
		txtCapSinEq = new JTextField();
		txtCapSinEq.setBounds(242, 230, 148, 30);
		txtCapSinEq.setForeground(Color.black);
		txtCapSinEq.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCapSinEq.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtCapSinEq);
		
		JLabel capCon = new JLabel("Capacidad Con Equipajes:");
		capCon.setBounds(50, 270, 197, 30);
		capCon.setForeground(Color.black);
		capCon.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(capCon);
		
		txtCapConEq = new JTextField();
		txtCapConEq.setBounds(247, 270, 143, 30);
		txtCapConEq.setForeground(Color.black);
		txtCapConEq.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCapConEq.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtCapConEq);
		
		JLabel capTotal = new JLabel("Capacidad Total:");
		capTotal.setBounds(50, 310, 128, 30);
		capTotal.setForeground(Color.black);
		capTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(capTotal);
		
		txtCapTotal = new JTextField();
		txtCapTotal.setBounds(178, 310, 212, 30);
		txtCapTotal.setForeground(Color.black);
		txtCapTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCapTotal.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtCapTotal);
		
		btnAgregar = new JButton("Agregar Vehículo");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		btnAgregar.setBounds(50, 365, 340, 35);
		btnAgregar.setModel(new MyButtonModel());
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}
}
