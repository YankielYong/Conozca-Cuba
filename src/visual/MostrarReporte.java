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

	private String cadena;
	private String provincia;
	private Reporte1 reporte1;
	private Reporte2 reporte2;
	private Reporte3 reporte3;
	private Reporte4 reporte4;
	private Reporte5 reporte5;
	private Reporte6 reporte6;
	private Reporte7 reporte7;
	private Reporte8 reporte8;

	public MostrarReporte(Principal p, int rep){
		este = this;
		padre = p;
		numReporte = rep;

		if(numReporte == 5 || numReporte == 6){
			Reporte6Params rp = new Reporte6Params(padre);
			rp.setVisible(true);
			cadena = rp.getCadena();
			provincia = rp.getProvincia();
		}

		setTipoPanel(Paneles.PANEL_MOSTRAR_REPORTES);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelMostrarReporte(este);
		setBounds(pantalla.width/2-651, pantalla.height/2-366, 1302, 682);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}
		});
		panelSuperior.setBounds(1, 1, 1300, 30);
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
		btnCerrar.setBounds(1255, 0, 45, 30);
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
		panelInferior.setBounds(1, 31, 1300, 650);
		panelInferior.setBackground(Color.white);
		add(panelInferior);

		if(numReporte == 3){
			setBounds(pantalla.width/2-601, pantalla.height/2-366, 1202, 682);
			panelSuperior.setBounds(1, 1, 1200, 30);
			btnCerrar.setBounds(1155, 0, 45, 30);
			panelInferior.setBounds(1, 31, 1200, 650);
		}
		else if(numReporte == 5 || numReporte == 6){
			setBounds(pantalla.width/2-581, pantalla.height/2-366, 1162, 682);
			panelSuperior.setBounds(1, 1, 1160, 30);
			btnCerrar.setBounds(1115, 0, 45, 30);
			panelInferior.setBounds(1, 31, 1160, 650);
		}
		else if(numReporte == 7){
			setBounds(pantalla.width/2-401, pantalla.height/2-366, 802, 682);
			panelSuperior.setBounds(1, 1, 800, 30);
			btnCerrar.setBounds(755, 0, 45, 30);
			panelInferior.setBounds(1, 31, 800, 650);
		}
	}

	private void mostrar(){
		btnCerrar.setBorderPainted(true);
		btnCerrar.setBorderPainted(false);	
		switch(numReporte){
		case 1:
			reporte1 = new Reporte1();
			panelInferior.add(reporte1);
			break;
		case 2:
			reporte2 = new Reporte2();
			panelInferior.add(reporte2);
			break;
		case 3: 
			reporte3 = new Reporte3();
			panelInferior.add(reporte3);
			break;
		case 4: 
			reporte4 = new Reporte4();
			panelInferior.add(reporte4);
			break;
		case 5: 
			reporte5 = new Reporte5(cadena, provincia);
			panelInferior.add(reporte5);
			break;
		case 6: 
			reporte6 = new Reporte6(cadena, provincia);
			panelInferior.add(reporte6);
			break;
		case 7: 
			reporte7 = new Reporte7();
			panelInferior.add(reporte7);
			break;
		case 8: 
			reporte8 = new Reporte8();
			panelInferior.add(reporte8);
			break;
		}
		mostrado = true;
	}
}
