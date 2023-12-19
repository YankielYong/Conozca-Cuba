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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dto.ContractLodgingDTO;
import dto.LodgingDTO;
import dto.RoomDTO;
import dto.TouristPackageDTO;
import services.ContractLodgingServices;
import services.ContractServices;
import services.LodgingServices;
import services.RoomServices;
import services.ServicesLocator;
import services.TouristPackageServices;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.Validaciones;

public class EditarHospedaje extends MiJPanel{

	private ContractLodgingServices contractLodgingServices = ServicesLocator.getContractLodgingServices();
	private ContractServices contractServices = ServicesLocator.getContractServices();
	private LodgingServices lodgingServices = ServicesLocator.getLodgingServices();
	private RoomServices roomServices = ServicesLocator.getRoomServices();
	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	private LodgingDTO ld;
	private RoomDTO r;
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JButton btnEditar;
	private JTextField txtPrecio;
	
	private Principal padre;
	private Gestion anterior;
	private EditarHospedaje este;
	
	public EditarHospedaje(Principal p, Gestion a, LodgingDTO l){
		este = this;
		padre = p;
		anterior = a;
		ld = l;
		try {
			r = roomServices.findRoom(ld.getRoomCode());
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
		setTipoPanel(Paneles.PANEL_EDITAR_HOSPEDAJES);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelEditarHospedaje(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-156, 442, 262);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Editar Hospedaje");
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
		panelInferior.setBounds(1, 31, 440, 230);
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
		
		JLabel pre = new JLabel("Precio sin recargo de habitación:");
		pre.setBounds(50, 110, 235, 30);
		pre.setForeground(Color.black);
		pre.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(pre);
		
		txtPrecio = new JTextField(String.valueOf(ld.getLodgingPrice()-r.getSurchargeRoom()));
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtPrecio.getText());
			}
		});
		txtPrecio.setBounds(285, 110, 105, 30);
		txtPrecio.setForeground(Color.black);
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecio.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtPrecio);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 18));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					int lCode = ld.getLodgingCode();
					int hCode = ld.getHotelCode();
					int rCode = ld.getRoomCode();
					int sCode = ld.getSeasonCode();
					String price = txtPrecio.getText();
					if(price.isEmpty()) throw new IllegalArgumentException("El campo del precio del hospedaje está vacío");
					double precioN = Double.valueOf(price);
					double precioV = ld.getLodgingPrice();
					lodgingServices.updateLodging(lCode, hCode, sCode, rCode, precioN+r.getSurchargeRoom());
					actualizar(lCode, precioN+r.getSurchargeRoom(), precioV);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El hospedaje fue editado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerHospedajes();
				} catch (IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					ma.setVisible(true);
				}
			}
		});
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(colorAzul);
			}
		});
		btnEditar.setModel(new MyButtonModel());
		btnEditar.setBounds(50, 170, 340, 35);
		btnEditar.setBackground(colorAzul);
		btnEditar.setForeground(Color.black);
		btnEditar.setFocusable(false);
		btnEditar.setBorderPainted(false);
		panelInferior.add(btnEditar);
	}
	
	private void actualizar(int code, double precioNuevo, double precioViejo){
		try {
			ArrayList<ContractLodgingDTO> listaLod = contractLodgingServices.selectAllContractLodging();
			for(ContractLodgingDTO cl : listaLod){
				if(cl.getLodgingCode()==code){
					int codeP = contractServices.findContract(cl.getContractCode()).getPackageCode();
					TouristPackageDTO tp = touristPackageServices.findTouristPackage(codeP);
					String nombre = tp.getPromotionalName();
					double costo = tp.getPackageCost();
					double precio = tp.getPackagePrice();
					int cantP = tp.getNumberOfPeople();
					int cantD = tp.getNumberOfDays();
					int cantN = tp.getNumberOfNights();
					touristPackageServices.updateTouristPackage(codeP, nombre, 
							precio-(precioViejo*cantP*cantN), 
							costo, cantP, cantD, cantN);
					precio-=precioViejo*cantP*cantN;
					touristPackageServices.updateTouristPackage(codeP, nombre, 
							precio+(precioNuevo*cantP*cantN), 
							costo, cantP, cantD, cantN);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}