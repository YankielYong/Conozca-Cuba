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

import dto.TouristPackageDTO;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class VerPaquete extends MiJPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;

	private Principal padre;
	private MiJPanel anterior;
	private VerPaquete este;
	private TouristPackageDTO tp;
	
	public VerPaquete(Principal p, MiJPanel a, TouristPackageDTO t){
		este = this;
		padre = p;
		anterior = a;
		tp=t;
		setTipoPanel(Paneles.PANEL_VER_PAQUETE);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelVerPaquete(este);
		setBounds(pantalla.width/2-226, pantalla.height/2-241, 452, 432);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 450, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Información Paquete Turístico");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 250, 30);
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
		btnCerrar.setBounds(405, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 450, 400);
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

		JLabel codigo = new JLabel("Código: "+tp.getPackageCode());
		codigo.setBounds(50, 110, 290, 30);
		codigo.setForeground(Color.black);
		codigo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(codigo);

		JLabel nomb = new JLabel("Nombre Promocional: "+tp.getPromotionalName());
		nomb.setBounds(50, 150, 350, 30);
		nomb.setForeground(Color.black);
		nomb.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(nomb);

		JLabel cantp = new JLabel("Cantidad de personas: "+tp.getNumberOfPeople());
		cantp.setBounds(50, 190, 350, 30);
		cantp.setForeground(Color.black);
		cantp.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(cantp);

		JLabel cantd = new JLabel("Cantidad de días: "+tp.getNumberOfDays());
		cantd.setBounds(50, 230, 350, 30);
		cantd.setForeground(Color.black);
		cantd.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(cantd);

		JLabel cantn = new JLabel("Cantidad de noches: "+tp.getNumberOfNights());
		cantn.setBounds(50, 270, 350, 30);
		cantn.setForeground(Color.black);
		cantn.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(cantn);

		JLabel prec = new JLabel("Precio: "+tp.getPackagePrice());
		prec.setBounds(50, 310, 350, 30);
		prec.setForeground(Color.black);
		prec.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(prec);

		JLabel cos = new JLabel("Costo: "+tp.getPackageCost());
		cos.setBounds(50, 350, 350, 30);
		cos.setForeground(Color.black);
		cos.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(cos);
	}
}