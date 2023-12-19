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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import services.RoleServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import dto.RoleDTO;
import dto.UserDTO;

public class VerUsuario extends MiJPanel {

	private RoleServices roleServices = ServicesLocator.getRoleServices();

	private static final long serialVersionUID = 1L;

	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JLabel nombre;
	private JLabel codigo;
	private JLabel nombreUsuario;
	private JLabel rol;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	private JButton btnCerrar;
	private JLabel lblNombre;

	private JButton btnAtras;

	private Principal padre;
	private Gestion anterior;
	private VerUsuario este;

	private UserDTO user;
	private RoleDTO roleUser;

	public VerUsuario(Principal p, Gestion a, UserDTO u){
		padre = p;
		este = this;
		anterior = a;
		user = u;
		establecerRol();
		setTipoPanel(Paneles.PANEL_VER_USUARIO);
		padre.setPanelAbierto(getTipoPanel());
		setBounds(pantalla.width/2-216, pantalla.height/2-226, 432, 402);	
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 430, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Información Usuario");
		lblNombre.setForeground(Color.black);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lblNombre.setBounds(10, 0, 200, 30);
		panelSuperior.add(lblNombre);

		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
		Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon iconCerrar = new ImageIcon(image);

		btnCerrar = new JButton();
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
		btnCerrar.setBounds(385, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setIcon(iconCerrar);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 430, 370);
		panelInferior.setBackground(Color.white);
		add(panelInferior);

		img = new ImageIcon(getClass().getResource("/visual/imagenes/usuarioLogin.png"));
		image = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		Icon iconUsuario = new ImageIcon(image);

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


		JLabel lUsuario = new JLabel(iconUsuario);
		lUsuario.setBounds(175, 30, 80, 80);
		panelInferior.add(lUsuario);

		codigo = new JLabel("Código: "+user.getUserCode());
		codigo.setBounds(50, 120, 350, 35);
		codigo.setFont(new Font("Arial", Font.BOLD, 18));
		codigo.setForeground(Color.black);
		panelInferior.add(codigo);

		nombre = new JLabel("Nombre: "+user.getUserName());
		nombre.setBounds(50, 180, 350, 35);
		nombre.setFont(new Font("Arial", Font.BOLD, 18));
		nombre.setForeground(Color.black);
		panelInferior.add(nombre);

		nombreUsuario = new JLabel("Usuario: "+user.getUserNick());
		nombreUsuario.setBounds(50, 240, 350, 35);
		nombreUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		nombreUsuario.setForeground(Color.black);
		panelInferior.add(nombreUsuario);

		rol = new JLabel("Rol: "+roleUser.getRoleName());
		rol.setBounds(50, 300, 350, 35);
		rol.setFont(new Font("Arial", Font.BOLD, 18));
		rol.setForeground(Color.black);
		panelInferior.add(rol);
	}
	private void establecerRol(){
		try {
			roleUser = roleServices.findRole(user.getRoleCode());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}