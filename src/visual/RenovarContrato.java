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
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import dto.ContractDTO;
import services.ContractServices;
import services.ServicesLocator;
import utils.ComboBoxModel;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;

public class RenovarContrato extends MiJPanel{

	private ContractServices contractServices = ServicesLocator.getContractServices();
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JComboBox<Integer> cbDia;
	private JComboBox<Integer> cbMes;
	private JComboBox<Integer> cbYear;
	private JButton btnRenovar;
	
	private Principal padre;
	private Gestion anterior;
	private RenovarContrato este;
	private ContractDTO contrato;
	
	@SuppressWarnings("deprecation")
	public RenovarContrato(Principal p, Gestion a, ContractDTO cont){
		este = this;
		padre = p;
		anterior = a;
		contrato=cont;
		setTipoPanel(Paneles.PANEL_RENOVAR_CONTRATO);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelRenovarContrato(este);
		setBounds(pantalla.width/2-181, pantalla.height/2-176, 362, 302);
		setBackground(Color.darkGray);
		setLayout(null);

		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 360, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);

		lblNombre = new JLabel("Renovar Contrato");
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
		btnCerrar.setBounds(315, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);

		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 360, 270);
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
		image = img.getImage().getScaledInstance(220, 67, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(68, 15, 220, 67);
		panelInferior.add(logo);
		
		JLabel fechaRe = new JLabel("Fecha de renovación");
		fechaRe.setBounds(50, 110, 150, 20);
		fechaRe.setForeground(Color.black);
		fechaRe.setFont(new Font("Arial", Font.PLAIN, 14));
		panelInferior.add(fechaRe);

		cbDia = new JComboBox<Integer>();
		cbDia.setBounds(50, 150, 80, 30);
		cbDia.setBackground(Color.white);
		cbDia.setFocusable(false);
		cbDia.setFont(new Font("Arial", Font.PLAIN, 16));
		cbDia.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbDia.setModel(ComboBoxModel.diasModel(31));
		cbDia.setUI(PropiedadesComboBox.createUI(getRootPane(), cbDia.getBounds()));
		((JLabel)cbDia.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbDia);

		cbMes = new JComboBox<Integer>();
		cbMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlDiasMeses();
			}
		});
		cbMes.setBounds(140, 150, 80, 30);
		cbMes.setBackground(Color.white);
		cbMes.setFocusable(false);
		cbMes.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMes.setModel(ComboBoxModel.mesesModel());
		cbMes.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbMes.setUI(PropiedadesComboBox.createUI(getRootPane(), cbMes.getBounds()));
		((JLabel)cbMes.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbMes);

		cbYear = new JComboBox<Integer>();
		cbYear.setBounds(230, 150, 80, 30);
		cbYear.setBackground(Color.white);
		cbYear.setFocusable(false);
		cbYear.setFont(new Font("Arial", Font.PLAIN, 16));
		cbYear.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbYear.setModel(ComboBoxModel.yearsModel());
		cbYear.setUI(PropiedadesComboBox.createUI(getRootPane(), cbYear.getBounds()));
		((JLabel)cbYear.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(cbYear);
		
		btnRenovar = new JButton("Renovar");
		btnRenovar.setFont(new Font("Arial", Font.BOLD, 18));
		btnRenovar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					int codigo = contrato.getContractCode();
					int dia = (int)cbDia.getSelectedItem();
					int mes = (int)cbMes.getSelectedItem();
					int year = (int)cbYear.getSelectedItem();
					Date f = new Date(year-1900, mes-1, dia);
					if(f.compareTo(new Date()) > 0)
						throw new IllegalArgumentException("La fecha no es válida porque no ha pasado");
					if(f.compareTo(contrato.getContractStartDate()) <= 0)
						throw new IllegalArgumentException("La fecha debe ser posterior a la fecha de inicio");
					String descripcion = contrato.getContractDescription();
					Date fechaInicio = f;
					Date fechaFinal = new Date(year-1899, mes-1, dia);
					Date fechaConc = f;
					String tipo = contrato.getContractType();
					int paquete = contrato.getPackageCode();
					contractServices.updateContract(codigo, descripcion, fechaInicio, fechaFinal, fechaConc, tipo, paquete);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El contrato fue renovado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerContratos();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					if(e1.getMessage().equals("La fecha debe ser posterior a la fecha de inicio"))
						ma.agrandar(30);
					ma.setVisible(true);
				}
			}
		});
		btnRenovar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRenovar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRenovar.setBackground(colorAzul);
			}
		});
		btnRenovar.setModel(new MyButtonModel());
		btnRenovar.setBounds(50, 210, 260, 35);
		btnRenovar.setBackground(colorAzul);
		btnRenovar.setForeground(Color.black);
		btnRenovar.setFocusable(false);
		btnRenovar.setBorderPainted(false);
		panelInferior.add(btnRenovar);
		
		cbDia.setSelectedItem(contrato.getContractStartDate().getDate());
		cbMes.setSelectedItem(contrato.getContractStartDate().getMonth()+1);
		cbYear.setSelectedItem(contrato.getContractStartDate().getYear()+1900);
		controlDiasMeses();
		cbDia.setSelectedItem(contrato.getContractStartDate().getDate());
	}
	
	private void controlDiasMeses(){
		int val = (int)cbDia.getSelectedItem();
		int mes = (int)cbMes.getSelectedItem();
		if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
			cbDia.setModel(ComboBoxModel.diasModel(31));
			cbDia.setSelectedItem(val);
		}
		else if(mes==2){
			cbDia.setModel(ComboBoxModel.diasModel(28));
			if(val<=28)
				cbDia.setSelectedItem(val);
		}
		else{
			cbDia.setModel(ComboBoxModel.diasModel(30));
			if(val<=30)
				cbDia.setSelectedItem(val);
		}
	}
}