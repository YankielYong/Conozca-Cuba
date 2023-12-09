package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.MyButtonModel;
import utils.MiJPanel;

public class MensajeAviso extends JDialog{

	private static final long serialVersionUID = 1L;

	public final static int CORRECTO = 0;
	public final static int ERROR = 1;
	public final static int INFORMACION = 2;

	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel contentPane;
	private JFrame login;
	private Principal padre;
	private MiJPanel panel;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private String mensaje;
	private JLabel lblMensaje;
	private JLabel tipo;
	private JLabel imagen;
	private int type;
	private boolean valor;

	public MensajeAviso(JFrame l, Principal p, MiJPanel pa, String m, int t){
		super(p, true);
		login = l;
		padre = p;
		panel = pa;
		inicializar(m, t);
		inicializarBotonesPorPanel();
	}

	private void inicializar(String m, int t){
		mensaje = m;
		type = t;
		valor = true;
		setResizable(false);
		setUndecorated(true);
		setBounds(pantalla.width/2-211, pantalla.height/2-101, 422, 152);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(Color.darkGray);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		panelSuperior = new JPanel();
		panelSuperior.setBackground(colorAzul);
		panelSuperior.setBounds(1, 1, 420, 30);
		panelSuperior.setLayout(null);
		contentPane.add(panelSuperior);

		panelInferior = new JPanel();
		panelInferior.setBackground(Color.white);
		panelInferior.setBounds(1, 31, 420, 120);
		panelInferior.setLayout(null);
		contentPane.add(panelInferior);

		tipo = new JLabel("");
		tipo.setFont(new Font("Arial", Font.PLAIN, 18));
		tipo.setForeground(Color.BLACK);
		tipo.setBounds(8, 0, 150, 30);
		panelSuperior.add(tipo);
		switch(type){
		case 0: tipo.setText("Correcto"); break;
		case 1: tipo.setText("Error"); break;
		case 2: tipo.setText("Información"); break;
		}

		imagen = new JLabel("");
		imagen.setBounds(15, 15, 50, 50);
		panelInferior.add(imagen);
		switch(type){
		case 0: imagen.setIcon(new ImageIcon(getClass().getResource("/visual/imagenes/icono verificacion 50.png"))); break;
		case 1: imagen.setIcon(new ImageIcon(getClass().getResource("/visual/imagenes/icono error 50.png"))); break;
		case 2: imagen.setIcon(new ImageIcon(getClass().getResource("/visual/imagenes/icono advertencia 50.png"))); break;
		}

		lblMensaje = new JLabel(mensaje);
		lblMensaje.setFont(new Font("Arial", Font.BOLD, 16));
		lblMensaje.setBounds(70, 20, 350, 40);
		panelInferior.add(lblMensaje);
	}

	private void inicializarBotonesPorPanel(){
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setModel(new MyButtonModel());
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if(login == null){
					if(type == 0 || type == 1){
						padre.getPanelPrincipal().add(panel);
						padre.getPanelPrincipal().repaint();
						padre.setPanelAbierto(panel.getTipoPanel());
					}
				}
				else{
					login.setVisible(true);
				}
			}
		});
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAceptar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAceptar.setBackground(colorAzul);
			}
		});
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setBorder(null);
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 18));
		btnAceptar.setBackground(colorAzul);
		btnAceptar.setBounds(160, 75, 100, 30);
		btnAceptar.setFocusable(false);
		panelInferior.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setModel(new MyButtonModel());
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valor = false;
				dispose();
				padre.getPanelPrincipal().add(panel);
				padre.getPanelPrincipal().repaint();
				padre.setPanelAbierto(panel.getTipoPanel());
			}
		});
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setBackground(colorAzul);
			}
		});
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBorder(null);
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCancelar.setBackground(colorAzul);
		btnCancelar.setBounds(280, 75, 100, 30);
		btnCancelar.setFocusable(false);
		if(type == 2)
			panelInferior.add(btnCancelar);
	}

	public void agrandar(int ancho){
		int x = getX();
		int y = getY();
		setBounds(x-ancho/2, y, 422+ancho, 152);
		panelSuperior.setBounds(1, 1, 420+ancho, 30);
		panelInferior.setBounds(1, 31, 420+ancho, 120);
		lblMensaje.setBounds(70, 20, 350+ancho, 40);
	}

	public boolean getValor(){
		return valor;
	}
}
