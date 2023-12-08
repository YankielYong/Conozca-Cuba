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

public class AgregarHabitacion extends MiJPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JComboBox<String> cbTipo;
	private JComboBox<String> cbPlan;
	private JTextField txtRecargo;
	private JButton btnAgregar;
	
	private Principal padre;
	private MiJPanel anterior;
	private AgregarHabitacion este;
	
	public AgregarHabitacion(Principal p, MiJPanel a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_AGREGAR_HABITACION);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelAgregarHabitacion(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-206, 442, 362);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Agregar Habitación");
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
		panelInferior.setBounds(1, 31, 440, 330);
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
		
		JLabel tipo = new JLabel("Tipo de Habitación:");
		tipo.setBounds(50, 110, 144, 30);
		tipo.setForeground(Color.black);
		tipo.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(tipo);
		
		cbTipo = new JComboBox<String>();
		cbTipo.setBounds(194, 110, 196, 30);
		cbTipo.setBackground(Color.white);
		cbTipo.setFocusable(false);
		cbTipo.setModel(ComboBoxModel.catHoteleraModel());
		cbTipo.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTipo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbTipo.setUI(PropiedadesComboBox.createUI(getRootPane(), cbTipo.getBounds()));
		panelInferior.add(cbTipo);
		
		JLabel plan = new JLabel("Plan Alimenticio:");
		plan.setBounds(50, 160, 125, 30);
		plan.setForeground(Color.black);
		plan.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(plan);
		
		cbPlan = new JComboBox<String>();
		cbPlan.setBounds(175, 160, 215, 30);
		cbPlan.setBackground(Color.white);
		cbPlan.setFocusable(false);
		cbPlan.setModel(ComboBoxModel.catHoteleraModel());
		cbPlan.setFont(new Font("Arial", Font.PLAIN, 16));
		cbPlan.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbPlan.setUI(PropiedadesComboBox.createUI(getRootPane(), cbPlan.getBounds()));
		panelInferior.add(cbPlan);
		
		JLabel recargo = new JLabel("Recargo de Habitación:");
		recargo.setBounds(50, 210, 174, 30);
		recargo.setForeground(Color.black);
		recargo.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(recargo);
		
		txtRecargo = new JTextField();
		txtRecargo.setBounds(224, 210, 166, 30);
		txtRecargo.setForeground(Color.black);
		txtRecargo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtRecargo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtRecargo);
		
		btnAgregar = new JButton("Agregar Habitación");
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
		btnAgregar.setBounds(50, 270, 340, 35);
		btnAgregar.setModel(new MyButtonModel());
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 18));
		btnAgregar.setBackground(colorAzul);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusable(false);
		btnAgregar.setBorderPainted(false);
		panelInferior.add(btnAgregar);
	}
}
