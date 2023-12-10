package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dto.ProvinceDTO;
import services.PlaceServices;
import services.ProvincePlaceServices;
import services.ProvinceServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;
import utils.Validaciones;

public class AgregarLugar extends MiJPanel{
	
	private PlaceServices placeServices = ServicesLocator.getPlaceServices();
	private ProvinceServices provinceServices = ServicesLocator.getProvinceServices();
	private ProvincePlaceServices provincePlaceServices = ServicesLocator.getProvincePlaceServices();
	private ArrayList<ProvinceDTO> listaProvincias;

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtNombre;
	private JTextField txtCosto;
	private JComboBox<String> cbTipo;
	private JComboBox<String> cbProvincia;
	private JButton btnAgregar;
	private boolean costChanged = false;
	private boolean nameChanged = false;
	
	private Principal padre;
	private Gestion anterior;
	private AgregarLugar este;
	
	public AgregarLugar(Principal p, Gestion a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_LUGAR);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarLugar(este);
		setBounds(pantalla.width/2-181, pantalla.height/2-231, 362, 412);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 360, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Lugar");
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
		btnCerrar.setBounds(315, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);
		
		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 360, 380);
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
		image = img.getImage().getScaledInstance(220, 67, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(68, 15, 220, 67);
		panelInferior.add(logo);
		
		txtNombre = new JTextField("Nombre");
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumeros(e);
			}
		});
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNombre.getText().equals("Nombre") && !nameChanged){
					txtNombre.setText("");
					nameChanged = true;
					txtNombre.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtNombre.getText().equals("")){
					txtNombre.setText("Nombre");
					nameChanged = false;
					txtNombre.setForeground(Color.gray);
				}
			}
		});
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setForeground(Color.gray);
		txtNombre.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtNombre.setBounds(50, 110, 260, 30);
		panelInferior.add(txtNombre);
		
		txtCosto = new JTextField("Costo Por Persona");
		txtCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtCosto.getText());
				costChanged = true;
			}
		});
		txtCosto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCosto.getText().equals("Costo Por Persona")){
					txtCosto.setText("");
					txtCosto.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtCosto.getText().equals("")){
					txtCosto.setText("Costo Por Persona");
					txtCosto.setForeground(Color.gray);
					costChanged = false;
				}
				else{
					String ca = txtCosto.getText();
					if(ca.charAt(ca.length()-1)=='.'){
						ca = ca.substring(0, ca.length()-1);
						txtCosto.setText(ca);
					}
				}
			}
		});
		txtCosto.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCosto.setForeground(Color.gray);
		txtCosto.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtCosto.setBounds(50, 160, 260, 30);
		panelInferior.add(txtCosto);
		
		cbTipo = new JComboBox<String>();
		cbTipo.setBounds(50, 210, 260, 30);
		cbTipo.setBackground(Color.white);
		cbTipo.setFocusable(false);
		cbTipo.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbTipo.setModel(utils.ComboBoxModel.tiposServiciosModel());
		cbTipo.setUI(PropiedadesComboBox.createUI(getRootPane(), cbTipo.getBounds()));
		panelInferior.add(cbTipo);
		
		cbProvincia = new JComboBox<String>();
		cbProvincia.setBounds(50, 260, 260, 30);
		cbProvincia.setBackground(Color.white);
		cbProvincia.setFocusable(false);
		cbProvincia.setFont(new Font("Arial", Font.PLAIN, 16));
		cbProvincia.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbProvincia.setUI(PropiedadesComboBox.createUI(getRootPane(), cbTipo.getBounds()));
		panelInferior.add(cbProvincia);
		llenarComboBox();
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				
				try{
					String cadena = "";
					String costo = "";
					int prov = listaProvincias.get(cbProvincia.getSelectedIndex()).getProvinceCode();
					if(nameChanged) cadena = txtNombre.getText();
					if(costChanged) costo = txtCosto.getText();
					else throw new IllegalArgumentException("El campo del costo está vacío");
					double cos = Double.valueOf(costo);
					String tipo = cbTipo.getItemAt(cbTipo.getSelectedIndex());
					Validaciones.lugar(cadena);
					placeServices.insertPlace(cadena, cos, tipo);
					int lugar = placeServices.getLastPlaceCode();
					provincePlaceServices.insertProvincePlace(prov, lugar);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El lugar fue agregado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerLugares();
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
		btnAgregar.setBounds(50, 320, 260, 35);
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}
	
	private void llenarComboBox(){
		try {
			listaProvincias = provinceServices.selectAllProvinces();
			for(ProvinceDTO p : listaProvincias)
				cbProvincia.addItem(p.getProviceName());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
