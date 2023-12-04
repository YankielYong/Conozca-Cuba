package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utils.MyButtonModel;

import java.awt.Font;

import javax.swing.border.MatteBorder;

import java.awt.Cursor;
import java.awt.Component;

public class Descubrir extends JPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JPanel panelInferior;

	private JPanel este;
	private Principal padre;

	private JButton btnPaquetes;
	private JButton btnHoteles;
	private JButton btnEventos;
	private JButton btnTransportes;
	private JTextField txtBuscarNombre;
	private JTextField txtBuscarProvincia;
	private JTextField txtBuscarCadenaHotelera;
	
	private boolean nameChanged = false;
	private boolean provChanged = false;
	private boolean chainChanged = false;
	private JLabel lblBuscarN;
	private JLabel lblBuscarP;
	private JLabel lblBuscarC;

	public Descubrir(Principal p){
		padre = p;
		este = this;
		setBounds(pantalla.width/2-601, pantalla.height/2-366, 1202, 682);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 1200, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

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
		btnCerrar.setBounds(1155, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 1200, 650);
		panelInferior.setBackground(Color.white);
		add(panelInferior);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/paquete turistico.png"));
		image = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		Icon iconPaquete = new ImageIcon(image);

		btnPaquetes = new JButton(" Paquetes Tur�sticos");
		btnPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				else if(btnEventos.isBorderPainted()){
					btnEventos.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(false);
				lblBuscarP.setVisible(false);
				txtBuscarCadenaHotelera.setVisible(false);
				lblBuscarC.setVisible(false);
			}
		});
		btnPaquetes.setBounds(20, 10, 275, 50);
		btnPaquetes.setIcon(iconPaquete);
		btnPaquetes.setForeground(Color.black);
		btnPaquetes.setModel(new MyButtonModel());
		btnPaquetes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaquetes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnPaquetes.setFont(new Font("Arial", Font.BOLD, 22));
		btnPaquetes.setFocusable(false);
		btnPaquetes.setContentAreaFilled(false);
		btnPaquetes.setBorderPainted(true);
		panelInferior.add(btnPaquetes);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/hotel.png"));
		image = img.getImage().getScaledInstance(24, 30, Image.SCALE_SMOOTH);
		Icon iconHotel = new ImageIcon(image);

		btnHoteles = new JButton("  Hoteles");
		btnHoteles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				else if (btnEventos.isBorderPainted()) {
					btnEventos.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(true);
				lblBuscarP.setVisible(true);
				txtBuscarCadenaHotelera.setVisible(true);
				lblBuscarC.setVisible(true);
			}
		});
		btnHoteles.setBounds(315, 10, 275, 50);
		btnHoteles.setIcon(iconHotel);
		btnHoteles.setForeground(Color.black);
		btnHoteles.setModel(new MyButtonModel());
		btnHoteles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHoteles.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnHoteles.setFont(new Font("Arial", Font.BOLD, 22));
		btnHoteles.setFocusable(false);
		btnHoteles.setContentAreaFilled(false);
		btnHoteles.setBorderPainted(false);
		panelInferior.add(btnHoteles);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/evento.png"));
		image = img.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		Icon iconEvento = new ImageIcon(image);

		btnEventos = new JButton("  Eventos");
		btnEventos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				else if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(true);
				lblBuscarP.setVisible(true);
				txtBuscarCadenaHotelera.setVisible(false);
				lblBuscarC.setVisible(false);
			}
		});
		btnEventos.setBounds(610, 10, 275, 50);
		btnEventos.setIcon(iconEvento);
		btnEventos.setForeground(Color.black);
		btnEventos.setModel(new MyButtonModel());
		btnEventos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEventos.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnEventos.setFont(new Font("Arial", Font.BOLD, 22));
		btnEventos.setFocusable(false);
		btnEventos.setContentAreaFilled(false);
		btnEventos.setBorderPainted(false);
		panelInferior.add(btnEventos);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/taxi.png"));
		image = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Icon iconTransporte = new ImageIcon(image);
		
		btnTransportes = new JButton("  Transportes");
		btnTransportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				else if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				else if(btnEventos.isBorderPainted()){
					btnEventos.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				txtBuscarProvincia.setVisible(false);
				lblBuscarP.setVisible(false);
				txtBuscarCadenaHotelera.setVisible(false);
				lblBuscarC.setVisible(false);
			}
		});
		btnTransportes.setBounds(905, 10, 275, 50);
		btnTransportes.setIcon(iconTransporte);
		btnTransportes.setForeground(Color.black);
		btnTransportes.setModel(new MyButtonModel());
		btnTransportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransportes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnTransportes.setFont(new Font("Arial", Font.BOLD, 22));
		btnTransportes.setFocusable(false);
		btnTransportes.setContentAreaFilled(false);
		btnTransportes.setBorderPainted(false);
		panelInferior.add(btnTransportes);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/search.png"));
		image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon iconBuscar = new ImageIcon(image);
		
		txtBuscarNombre = new JTextField("Nombre");
		txtBuscarNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtBuscarNombre.getText().equals("Nombre") && !nameChanged){
					txtBuscarNombre.setText("");
					nameChanged = true;
					txtBuscarNombre.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtBuscarNombre.getText().equals("")){
					txtBuscarNombre.setText("Nombre");
					nameChanged = false;
					txtBuscarNombre.setForeground(Color.gray);
				}
			}
		});
		txtBuscarNombre.setBounds(30, 80, 300, 30);
		txtBuscarNombre.setForeground(Color.gray);
		txtBuscarNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		panelInferior.add(txtBuscarNombre);

		lblBuscarN = new JLabel("");
		lblBuscarN.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarN.setIcon(iconBuscar);
		lblBuscarN.setBounds(330, 80, 50, 30);
		panelInferior.add(lblBuscarN);
		
		txtBuscarProvincia = new JTextField("Provincia");
		txtBuscarProvincia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtBuscarProvincia.getText().equals("Provincia") && !provChanged){
					txtBuscarProvincia.setText("");
					provChanged = true;
					txtBuscarProvincia.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtBuscarProvincia.getText().equals("")){
					txtBuscarProvincia.setText("Provincia");
					provChanged = false;
					txtBuscarProvincia.setForeground(Color.gray);
				}
			}
		});
		txtBuscarProvincia.setBounds(420, 80, 300, 30);
		txtBuscarProvincia.setForeground(Color.gray);
		txtBuscarProvincia.setFont(new Font("Arial", Font.PLAIN, 15));
		panelInferior.add(txtBuscarProvincia);
		txtBuscarProvincia.setVisible(false);

		lblBuscarP = new JLabel("");
		lblBuscarP.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarP.setIcon(iconBuscar);
		lblBuscarP.setBounds(720, 80, 50, 30);
		panelInferior.add(lblBuscarP);
		lblBuscarP.setVisible(false);
		
		txtBuscarCadenaHotelera = new JTextField("Cadena Hotelera");
		txtBuscarCadenaHotelera.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtBuscarCadenaHotelera.getText().equals("Cadena Hotelera") && !chainChanged){
					txtBuscarCadenaHotelera.setText("");
					chainChanged = true;
					txtBuscarCadenaHotelera.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtBuscarCadenaHotelera.getText().equals("")){
					txtBuscarCadenaHotelera.setText("Cadena Hotelera");
					chainChanged = false;
					txtBuscarCadenaHotelera.setForeground(Color.gray);
				}
			}
		});
		txtBuscarCadenaHotelera.setBounds(810, 80, 300, 30);
		txtBuscarCadenaHotelera.setForeground(Color.gray);
		txtBuscarCadenaHotelera.setFont(new Font("Arial", Font.PLAIN, 15));
		panelInferior.add(txtBuscarCadenaHotelera);
		txtBuscarCadenaHotelera.setVisible(false);
		
		lblBuscarC = new JLabel("");
		lblBuscarC.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarC.setIcon(iconBuscar);
		lblBuscarC.setBounds(1110, 80, 50, 30);
		panelInferior.add(lblBuscarC);
		lblBuscarC.setVisible(false);
	}

}