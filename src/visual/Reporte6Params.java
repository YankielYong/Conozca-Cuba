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
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import dto.HotelChainDTO;
import dto.ProvinceDTO;
import services.HotelChainServices;
import services.ProvinceServices;
import services.ServicesLocator;
import utils.MyButtonModel;
import utils.PropiedadesComboBox;

public class Reporte6Params extends JDialog{
	
	private HotelChainServices hotelChainServices = ServicesLocator.getHotelChainServices();
	private ProvinceServices provinceServices = ServicesLocator.getProvinceServices();
	
	private ArrayList<HotelChainDTO> listaCadenasHoteleras;
	private ArrayList<ProvinceDTO> listaProvincias;

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel contentPane;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JComboBox<String> cbCadena;
	private JComboBox<String> cbProvincia;
	private JButton btnAceptar;
	private String cadena;
	private String provincia;
	
	private Principal padre;
	
	public Reporte6Params(Principal p){
		super(p, true);
		padre = p;
		setResizable(false);
		setUndecorated(true);
		setBounds(pantalla.width/2-181, pantalla.height/2-181, 362, 312);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.darkGray);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		panelSuperior = new JPanel();
		panelSuperior.setBackground(colorAzul);
		panelSuperior.setBounds(1, 1, 360, 30);
		panelSuperior.setLayout(null);
		contentPane.add(panelSuperior);

		panelInferior = new JPanel();
		panelInferior.setBackground(Color.white);
		panelInferior.setBounds(1, 31, 360, 280);
		panelInferior.setLayout(null);
		contentPane.add(panelInferior);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		Image image = img.getImage().getScaledInstance(220, 67, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(68, 15, 220, 67);
		panelInferior.add(logo);
		
		cbCadena = new JComboBox<String>();
		cbCadena.setBounds(50, 110, 260, 30);
		cbCadena.setBackground(Color.white);
		cbCadena.setForeground(Color.black);
		cbCadena.setFocusable(false);
		cbCadena.setFont(new Font("Arial", Font.PLAIN, 16));
		cbCadena.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbCadena.setUI(PropiedadesComboBox.createUI(getRootPane(), cbCadena.getBounds()));
		panelInferior.add(cbCadena);
		cbCadena.addItem("Todas");
		
		cbProvincia = new JComboBox<String>();
		cbProvincia.setBounds(50, 160, 260, 30);
		cbProvincia.setBackground(Color.white);
		cbProvincia.setForeground(Color.black);
		cbProvincia.setFocusable(false);
		cbProvincia.setFont(new Font("Arial", Font.PLAIN, 16));
		cbProvincia.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbProvincia.setUI(PropiedadesComboBox.createUI(getRootPane(), cbProvincia.getBounds()));
		panelInferior.add(cbProvincia);
		cbProvincia.addItem("Todas");
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadena = (String)cbCadena.getSelectedItem();
				provincia = (String)cbProvincia.getSelectedItem();
				dispose();
				padre.getPanelPrincipal().repaint();
				
			}
		});
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAceptar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAceptar.setBackground(colorAzul);
			}
		});
		btnAceptar.setModel(new MyButtonModel());
		btnAceptar.setBounds(50, 220, 260, 35);
		btnAceptar.setBackground(colorAzul);
		btnAceptar.setForeground(Color.black);
		btnAceptar.setFocusable(false);
		btnAceptar.setBorderPainted(false);
		panelInferior.add(btnAceptar);	
		
		llenarComboBox();
	}
	
	private void llenarComboBox(){
		try {
			listaCadenasHoteleras = hotelChainServices.selectAllHotelChains();
			listaProvincias = provinceServices.selectAllProvinces();
			
			for(HotelChainDTO h : listaCadenasHoteleras)
				cbCadena.addItem(h.getHotelChainName());
			for(ProvinceDTO p : listaProvincias)
				cbProvincia.addItem(p.getProviceName());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public String getCadena() {
		return cadena;
	}

	public String getProvincia() {
		return provincia;
	}
}
