package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dto.FoodPlanDTO;
import dto.RoomDTO;
import services.FoodPlanServices;
import services.RoomServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.PropiedadesComboBox;
import utils.Validaciones;

public class EditarHabitacion extends MiJPanel {
	
	private FoodPlanServices foodPlanServices = ServicesLocator.getFoodPlanServices();
	private RoomServices roomServices = ServicesLocator.getRoomServices();
	
	private ArrayList<FoodPlanDTO> listaPlanes;
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JLabel lblNombre;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JButton btnEditar;
	
	private JComboBox<String> cbPlan;
	private JTextField txtRecargo;
	
	private Principal padre;
	private Gestion anterior;
	private EditarHabitacion este;
	private RoomDTO habit;
	
	public EditarHabitacion(Principal p, Gestion a, RoomDTO h){
		este = this;
		padre = p;
		anterior = a;
		habit = h;
		setTipoPanel(Paneles.PANEL_EDITAR_HABITACION);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelEditarHabitacion(este);
		setBounds(pantalla.width/2-221, pantalla.height/2-281, 442, 312);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 440, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Editar Habitación");
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
		panelInferior.setBounds(1, 31, 440, 280);
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
		
		JLabel plan = new JLabel("Plan Alimenticio:");
		plan.setBounds(50, 110, 125, 30);
		plan.setForeground(Color.black);
		plan.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(plan);
		
		cbPlan = new JComboBox<String>();
		cbPlan.setBounds(175, 110, 215, 30);
		cbPlan.setBackground(Color.white);
		cbPlan.setFocusable(false);
		cbPlan.setForeground(Color.black);
		cbPlan.setFont(new Font("Arial", Font.PLAIN, 16));
		cbPlan.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		cbPlan.setUI(PropiedadesComboBox.createUI(getRootPane(), cbPlan.getBounds()));
		panelInferior.add(cbPlan);
		
		JLabel recargo = new JLabel("Recargo de Habitación:");
		recargo.setBounds(50, 160, 174, 30);
		recargo.setForeground(Color.black);
		recargo.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(recargo);
		
		txtRecargo = new JTextField(String.valueOf(habit.getSurchargeRoom()));
		txtRecargo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeroYUnaComa(e, txtRecargo.getText());
			}
		});
		txtRecargo.setBounds(224, 160, 166, 30);
		txtRecargo.setForeground(Color.black);
		txtRecargo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtRecargo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtRecargo);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 18));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					int code = habit.getRoomCode();
					String tipo = habit.getRoomType();
					int codF = listaPlanes.get(cbPlan.getSelectedIndex()).getFoodPlanCode();
					String recargo = txtRecargo.getText();
					if(recargo.isEmpty()) throw new IllegalArgumentException("El campo de recargo de habitación está vacío");
					if(recargo.charAt(recargo.length()-1)=='.') recargo = recargo.substring(0, recargo.length()-1);
					double rec = Double.valueOf(recargo);
					roomServices.updateRoom(code, tipo, rec, codF);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "La habitación fue editada con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerHabitacion();
				} catch (IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					ma.setVisible(true);
				}
			}
		});
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(40, 113, 128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(colorAzul);
			}
		});
		btnEditar.setModel(new MyButtonModel());
		btnEditar.setBounds(50, 220, 340, 35);
		btnEditar.setBackground(colorAzul);
		btnEditar.setForeground(Color.black);
		btnEditar.setFocusable(false);
		btnEditar.setBorderPainted(false);
		panelInferior.add(btnEditar);
		
		llenarComboBox();
	}
	
	private void llenarComboBox(){
		try {
			listaPlanes = foodPlanServices.selectAllFoddPlans();
			for(FoodPlanDTO f : listaPlanes)
				cbPlan.addItem(f.getTypeOfFoodPlan());
			cbPlan.setSelectedItem(habit.getRoomType());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
