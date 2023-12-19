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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dto.TouristPackageDTO;
import services.ServicesLocator;
import services.TouristPackageServices;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.Validaciones;

public class EditarPaquete extends MiJPanel{
	
	private TouristPackageServices touristPackageServices = ServicesLocator.getTouristPackageServices();
	private TouristPackageDTO tp;
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtNombre;
	private boolean nameChanged = true;
	private JButton btnEditar;
	
	private Principal padre;
	private EditarPaquete este;
	private Gestion anterior;
	
	public EditarPaquete(Principal p, Gestion a, TouristPackageDTO t) {
		anterior = a;
		este = this;
		padre = p;
		tp = t;
		setTipoPanel(Paneles.PANEL_EDITAR_PAQUETES);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelEditarPaquete(este);
		setBounds(pantalla.width/2-181, pantalla.height/2-156, 362, 262);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 360, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Editar Paquete Turístico");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 220, 30);
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
		panelInferior.setBounds(1, 31, 360, 230);
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

		txtNombre = new JTextField(tp.getPromotionalName());
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumeros(e);
			}
		});
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNombre.getText().equals("Nombre Promocional") && !nameChanged){
					txtNombre.setText("");
					nameChanged = true;
					txtNombre.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(txtNombre.getText().equals("")){
					txtNombre.setText("Nombre Promocional");
					nameChanged = false;
					txtNombre.setForeground(Color.gray);
				}
			}
		});
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setForeground(Color.black);
		txtNombre.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		txtNombre.setBounds(50, 110, 260, 30);
		panelInferior.add(txtNombre);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 18));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();

				try{
					String nombre = "";
					if(nameChanged) nombre = txtNombre.getText();
					Validaciones.paquete(nombre, tp.getNumberOfPeople(), tp.getNumberOfDays(), tp.getNumberOfNights());
					touristPackageServices.updateTouristPackage(tp.getPackageCode(), nombre, tp.getPackagePrice(), 
							tp.getPackageCost(), tp.getNumberOfPeople(), tp.getNumberOfDays(), tp.getNumberOfNights());
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El paquete turístico fue "
							+ "editado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerPaquetes();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
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
		btnEditar.setBounds(50, 170, 260, 35);
		btnEditar.setBackground(colorAzul);
		btnEditar.setForeground(Color.black);
		btnEditar.setFocusable(false);
		btnEditar.setBorderPainted(false);
		panelInferior.add(btnEditar);
	}

}