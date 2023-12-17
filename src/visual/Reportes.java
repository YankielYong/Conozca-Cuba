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
import javax.swing.border.MatteBorder;

import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;

public class Reportes extends MiJPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private Reportes este;
	private Principal padre;

	private JPanel panelSuperior;
	private JLabel lblNombre;
	private JButton btnCerrar;
	private JPanel panelInferior;
	private JComboBox<String> cbReporte;
	private JButton btnMostrar;

	public Reportes(Principal p){
		este = this;
		padre = p;
		setTipoPanel(Paneles.PANEL_REPORTES);
		padre.setPanelAbierto(getTipoPanel());
		setBounds(pantalla.width/2-301, pantalla.height/2-186, 602, 322);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 600, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Reportes");
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
		btnCerrar.setBounds(555, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 600, 290);
		panelInferior.setBackground(Color.white);
		add(panelInferior);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/reporte.png"));
		image = img.getImage().getScaledInstance(91, 91, Image.SCALE_SMOOTH);
		Icon iconReporte = new ImageIcon(image);
		
		JLabel repo = new JLabel(iconReporte);
		repo.setBounds(80, 30, 90, 90);
		panelInferior.add(repo);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(300, 91, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(210, 30, 300, 91);
		panelInferior.add(logo);
		
		cbReporte = new JComboBox<String>();
		cbReporte.setBounds(50, 150, 500, 30);
		cbReporte.setBackground(Color.white);
		cbReporte.setFocusable(false);
		cbReporte.setFont(new Font("Arial", Font.PLAIN, 16));
		cbReporte.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbReporte.setModel(utils.ComboBoxModel.reportesModel());
		cbReporte.setUI(PropiedadesComboBox.createUI(getRootPane(), cbReporte.getBounds()));
		panelInferior.add(cbReporte);
		
		btnMostrar = new JButton("Mostrar Reporte");
		btnMostrar.setFont(new Font("Arial", Font.BOLD, 18));
		btnMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				MostrarReporte mr = new MostrarReporte(padre, cbReporte.getSelectedIndex()+1);
				padre.getPanelPrincipal().add(mr);
				padre.getPanelPrincipal().repaint();
			}
		});
		btnMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMostrar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMostrar.setBackground(colorAzul);
			}
		});
		btnMostrar.setModel(new MyButtonModel());
		btnMostrar.setBounds(50, 220, 500, 35);
		btnMostrar.setBackground(colorAzul);
		btnMostrar.setForeground(Color.black);
		btnMostrar.setFocusable(false);
		btnMostrar.setBorderPainted(false);
		panelInferior.add(btnMostrar);
	}
}
