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

import javafx.scene.control.ComboBox;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import utils.ComboBoxModel;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;
import utils.Validaciones;

import javax.swing.SwingConstants;

public class AgregarActividad extends MiJPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JComboBox<Integer> cbDia;
	private JComboBox<Integer> cbMes;
	private JComboBox<Integer> cbYear;
	private JTextField txtHora;
	private JTextField txtMin;
	private JComboBox<String> cbMeridiano;
	private JTextField txtPrecio;
	private JTextField txtDescripcion;
	private JButton btnAgregar;
	private boolean priceChanged = false;
	private boolean descChanged = false;
	
	private Principal padre;
	private Gestion anterior;
	private AgregarActividad este;
	
	public AgregarActividad(Principal p, Gestion a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_ACTIVIDAD);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarActividad(este);
		setBounds(pantalla.width/2-181, pantalla.height/2-246, 362, 442);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 360, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Actividad");
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
		panelInferior.setBounds(1, 31, 360, 410);
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
		
		JLabel dia = new JLabel("Día");
		dia.setHorizontalAlignment(SwingConstants.CENTER);
		dia.setHorizontalTextPosition(SwingConstants.CENTER);
		dia.setFont(new Font("Arial", Font.PLAIN, 14));
		dia.setBounds(50, 100, 50, 20);
		panelInferior.add(dia);
		
		cbDia = new JComboBox<Integer>();
		cbDia.setBounds(50, 120, 80, 30);
		cbDia.setBackground(Color.white);
		cbDia.setFocusable(false);
		cbDia.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDia.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbDia.setModel(ComboBoxModel.diasModel(31));
		cbDia.setUI(PropiedadesComboBox.createUI(getRootPane(), cbDia.getBounds()));
		panelInferior.add(cbDia);
		
		JLabel mes = new JLabel("Mes");
		mes.setHorizontalAlignment(SwingConstants.CENTER);
		mes.setHorizontalTextPosition(SwingConstants.CENTER);
		mes.setFont(new Font("Arial", Font.PLAIN, 14));
		mes.setBounds(140, 100, 50, 20);
		panelInferior.add(mes);
		
		cbMes = new JComboBox<Integer>();
		cbMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlDiasMeses();
			}
		});
		cbMes.setBounds(140, 120, 80, 30);
		cbMes.setBackground(Color.white);
		cbMes.setFocusable(false);
		cbMes.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMes.setModel(ComboBoxModel.mesesModel());
		cbMes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbMes.setUI(PropiedadesComboBox.createUI(getRootPane(), cbMes.getBounds()));
		panelInferior.add(cbMes);
		
		JLabel year = new JLabel("Año");
		year.setHorizontalAlignment(SwingConstants.CENTER);
		year.setHorizontalTextPosition(SwingConstants.CENTER);
		year.setFont(new Font("Arial", Font.PLAIN, 14));
		year.setBounds(230, 100, 50, 20);
		panelInferior.add(year);
		
		cbYear = new JComboBox<Integer>();
		cbYear.setBounds(230, 120, 80, 30);
		cbYear.setBackground(Color.white);
		cbYear.setFocusable(false);
		cbYear.setFont(new Font("Arial", Font.PLAIN, 16));
		cbYear.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbYear.setModel(ComboBoxModel.yearsModel());
		cbYear.setSelectedItem(2024);
		cbYear.setUI(PropiedadesComboBox.createUI(getRootPane(), cbYear.getBounds()));
		panelInferior.add(cbYear);
		
		JLabel hora = new JLabel("Hora");
		hora.setHorizontalAlignment(SwingConstants.CENTER);
		hora.setHorizontalTextPosition(SwingConstants.CENTER);
		hora.setFont(new Font("Arial", Font.PLAIN, 14));
		hora.setBounds(84, 170, 50, 20);
		panelInferior.add(hora);
		
		txtHora = new JTextField();
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.hora(e, txtHora.getText());
			}
		});
		txtHora.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHora.setForeground(Color.black);
		txtHora.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtHora.setBounds(80, 190, 60, 30);
		panelInferior.add(txtHora);
		
		JLabel pun = new JLabel(":");
		pun.setHorizontalAlignment(SwingConstants.CENTER);
		pun.setHorizontalTextPosition(SwingConstants.CENTER);
		pun.setFont(new Font("Arial", Font.PLAIN, 16));
		pun.setBounds(140, 190, 10, 30);
		panelInferior.add(pun);
		
		JLabel min = new JLabel("Minutos");
		min.setHorizontalAlignment(SwingConstants.CENTER);
		min.setHorizontalTextPosition(SwingConstants.CENTER);
		min.setFont(new Font("Arial", Font.PLAIN, 14));
		min.setBounds(155, 170, 50, 20);
		panelInferior.add(min);
		
		txtMin = new JTextField();
		txtMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.minuto(e, txtMin.getText());
			}
		});
		txtMin.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMin.setForeground(Color.black);
		txtMin.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtMin.setBounds(150, 190, 60, 30);
		panelInferior.add(txtMin);
		
		cbMeridiano = new JComboBox<String>();
		cbMeridiano.setBounds(220, 190, 60, 30);
		cbMeridiano.setBackground(Color.white);
		cbMeridiano.setFocusable(false);
		cbMeridiano.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMeridiano.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbMeridiano.setModel(ComboBoxModel.meridianosModel());
		cbMeridiano.setUI(PropiedadesComboBox.createUI(getRootPane(), cbMeridiano.getBounds()));
		panelInferior.add(cbMeridiano);
		
		txtPrecio = new JTextField("Precio");
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtPrecio.getText());
				priceChanged = true;
			}
		});
		txtPrecio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPrecio.getText().equals("Precio")){
					txtPrecio.setText("");
					txtPrecio.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtPrecio.getText().equals("")){
					txtPrecio.setText("Precio");
					txtPrecio.setForeground(Color.gray);
					priceChanged = false;
				}
				else{
					String ca = txtPrecio.getText();
					if(ca.charAt(ca.length()-1)==','){
						ca = ca.substring(0, ca.length()-1);
						txtPrecio.setText(ca);
					}
				}
			}
		});
		txtPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrecio.setForeground(Color.gray);
		txtPrecio.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtPrecio.setBounds(50, 240, 260, 30);
		panelInferior.add(txtPrecio);
		
		txtDescripcion = new JTextField("Descripción");
		txtDescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtDescripcion.getText().equals("Descripción") && !descChanged){
					txtDescripcion.setText("");
					descChanged = true;
					txtDescripcion.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtDescripcion.getText().equals("")){
					txtDescripcion.setText("Descripción");
					descChanged = false;
					txtDescripcion.setForeground(Color.gray);
				}
			}
		});
		txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDescripcion.setForeground(Color.gray);
		txtDescripcion.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtDescripcion.setBounds(50, 290, 260, 30);
		panelInferior.add(txtDescripcion);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				
				try{
					String cadena = "";
					String costo = "";
					if(nameChanged) cadena = txtNombre.getText();
					if(costChanged) costo = txtCosto.getText();
					double cos = Double.valueOf(costo);
					String tipo = cbTipo.getItemAt(cbTipo.getSelectedIndex());
					Validaciones.lugar(cadena);
					placeServices.insertPlace(cadena, cos, tipo);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El lugar fue agregado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerLugares();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					ma.setVisible(true);
				}*/
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
		btnAgregar.setBounds(50, 350, 260, 35);
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}
	
	private void controlDiasMeses(){
		int mes = (int)cbMes.getSelectedItem();
		if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12)
			cbDia.setModel(ComboBoxModel.diasModel(31));
		else if(mes==2)
			cbDia.setModel(ComboBoxModel.diasModel(28));
		else
			cbDia.setModel(ComboBoxModel.diasModel(30));
	}
}
