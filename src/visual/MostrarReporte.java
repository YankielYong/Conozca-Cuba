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

import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class MostrarReporte extends MiJPanel{

	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JPanel panelInferior;

	private MostrarReporte este;
	private Principal padre;
	private int numReporte;
	private boolean mostrado = false;

	private Reporte1 reporte1;

	public MostrarReporte(Principal p, int rep){
		este = this;
		padre = p;
		numReporte = rep;
		setTipoPanel(Paneles.PANEL_MOSTRAR_REPORTES);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelMostrarReporte(este);
		setBounds(pantalla.width/2-501, pantalla.height/2-326, 1002, 602);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
		});
		panelSuperior.setBounds(1, 1, 1000, 30);
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
		btnCerrar.setBounds(955, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!mostrado)
					mostrar();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!mostrado)
					mostrar();
			}
		});
		panelInferior.setBounds(1, 31, 1000, 570);
		panelInferior.setBackground(Color.white);
		add(panelInferior);
	}
	
	private void mostrar(){
		btnCerrar.setBorderPainted(true);
		btnCerrar.setBorderPainted(false);	
		switch(numReporte){
		case 1:
			reporte1 = new Reporte1();
			panelInferior.add(reporte1);
			break;
		}
		mostrado = true;
	}
}
