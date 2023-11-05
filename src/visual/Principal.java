package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.MyButtonModel;

public class Principal extends JFrame{
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);
	
	private JPanel contentPane;
	
	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JButton btnMinimizar;
	
	private JPanel panelPrincipal;
	
	public Principal(){
		setUndecorated(true);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, pantalla.width, pantalla.height);
		
		contentPane = new JPanel(null);
		setContentPane(contentPane);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(0, 0, pantalla.width, 50);
		panelSuperior.setBackground(colorAzul);
		contentPane.add(panelSuperior);
		
		ImageIcon img = new ImageIcon(getClass().getResource("/visual/imagenes/minimize.png"));
        Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        Icon iconMinimizar = new ImageIcon(image);
        
        img = new ImageIcon(getClass().getResource("/visual/imagenes/close.png"));
        image = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        Icon iconCerrar = new ImageIcon(image);
		
		btnCerrar = new JButton(iconCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
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
		btnCerrar.setModel(new MyButtonModel());
		btnCerrar.setBounds(pantalla.width-75, 0, 75, 50);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		panelSuperior.add(btnCerrar);
		
		panelPrincipal = new JPanel(null){
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/visual/imagenes/principal.jpg"));
				g.drawImage(img, 0, 0, panelPrincipal.getWidth(), panelPrincipal.getHeight(), this);
			}
		};
		panelPrincipal.setBounds(100, 50, pantalla.width-100, pantalla.height-50);
		contentPane.add(panelPrincipal);
	}

}
