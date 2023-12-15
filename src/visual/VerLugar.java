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

import dto.PlaceDTO;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class VerLugar extends MiJPanel {

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;

	private Principal padre;
	private Gestion anterior;
	private VerLugar este;
	private PlaceDTO lugar;

	public VerLugar(Principal p, Gestion a,PlaceDTO pl ){
		este = this;
		padre = p;
		anterior = a;
		lugar=pl;
		setTipoPanel(Paneles.PANEL_VER_LUGAR);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelVerLugar(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-206, 442, 362);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Información Lugar");
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

		JLabel codigo = new JLabel("Código: "+lugar.getPlaceCode());
		codigo.setBounds(50, 110, 290, 30);
		codigo.setForeground(Color.black);
		codigo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(codigo);
		
		JLabel nombre = new JLabel("Nombre: "+lugar.getPlaceName());
		nombre.setBounds(50, 160, 290, 30);
		nombre.setForeground(Color.black);
		nombre.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(nombre);

		JLabel costoPorPersona = new JLabel("Costo por persona: "+lugar.getCostPerPerson());
		costoPorPersona.setBounds(50, 210, 290, 30);
		costoPorPersona.setForeground(Color.black);
		costoPorPersona.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(costoPorPersona);

		JLabel tipo = new JLabel("Tipo de servicio: "+lugar.getTypeOfService());
		tipo.setBounds(50, 260, 290, 30);
		tipo.setForeground(Color.black);
		tipo.setFont(new Font("Arial", Font.BOLD, 16));
		panelInferior.add(tipo);

	}
}
