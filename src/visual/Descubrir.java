package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utils.MyButtonModel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;

public class Descubrir extends JPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JPanel panelInferior;

	private JPanel este;
	private Principal padre;

	private JButton btnPaquetes;
	private JButton btnHoteles;
	private JButton btnEventos;
	private JButton btnTransportes;


	public Descubrir(Principal p){
		padre = p;
		este = this;
		setBounds(pantalla.width/2-601, pantalla.height/2-366, 1202, 682);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 1200, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

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
		btnCerrar.setBounds(1155, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 1200, 650);
		panelInferior.setBackground(Color.white);
		add(panelInferior);

		btnPaquetes = new JButton("Paquetes Turísticos");
		btnPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				else if(btnEventos.isBorderPainted()){
					btnEventos.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnPaquetes.setBorderPainted(true);
				}
			}
		});
		btnPaquetes.setBounds(20, 10, 275, 50);
		btnPaquetes.setForeground(Color.black);
		btnPaquetes.setModel(new MyButtonModel());
		btnPaquetes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaquetes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnPaquetes.setFont(new Font("Arial", Font.BOLD, 22));
		btnPaquetes.setFocusable(false);
		btnPaquetes.setContentAreaFilled(false);
		btnPaquetes.setBorderPainted(true);
		panelInferior.add(btnPaquetes);

		btnHoteles = new JButton("Hoteles");
		btnHoteles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				else if (btnEventos.isBorderPainted()) {
					btnEventos.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnHoteles.setBorderPainted(true);
				}
			}
		});
		btnHoteles.setBounds(315, 10, 275, 40);
		btnHoteles.setForeground(Color.black);
		btnHoteles.setModel(new MyButtonModel());
		btnHoteles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHoteles.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnHoteles.setFont(new Font("Arial", Font.BOLD, 22));
		btnHoteles.setFocusable(false);
		btnHoteles.setContentAreaFilled(false);
		btnHoteles.setBorderPainted(false);
		panelInferior.add(btnHoteles);

		btnEventos = new JButton("Eventos");
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				else if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
				else if(btnTransportes.isBorderPainted()){
					btnTransportes.setBorderPainted(false);
					btnEventos.setBorderPainted(true);
				}
			}
		});
		btnEventos.setBounds(610, 10, 275, 40);
		btnEventos.setForeground(Color.black);
		btnEventos.setModel(new MyButtonModel());
		btnEventos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEventos.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnEventos.setFont(new Font("Arial", Font.BOLD, 22));
		btnEventos.setFocusable(false);
		btnEventos.setContentAreaFilled(false);
		btnEventos.setBorderPainted(false);
		panelInferior.add(btnEventos);
		
		btnTransportes = new JButton("Transportes");
		btnTransportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPaquetes.isBorderPainted()){
					btnPaquetes.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				else if(btnHoteles.isBorderPainted()){
					btnHoteles.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
				else if(btnEventos.isBorderPainted()){
					btnEventos.setBorderPainted(false);
					btnTransportes.setBorderPainted(true);
				}
			}
		});
		btnTransportes.setBounds(905, 10, 275, 40);
		btnTransportes.setForeground(Color.black);
		btnTransportes.setModel(new MyButtonModel());
		btnTransportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransportes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		btnTransportes.setFont(new Font("Arial", Font.BOLD, 22));
		btnTransportes.setFocusable(false);
		btnTransportes.setContentAreaFilled(false);
		btnTransportes.setBorderPainted(false);
		panelInferior.add(btnTransportes);
	}

}
