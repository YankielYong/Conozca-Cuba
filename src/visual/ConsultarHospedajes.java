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
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import dto.FoodPlanDTO;
import dto.HotelDTO;
import dto.LodgingDTO;
import dto.RoomDTO;
import dto.SeasonDTO;
import services.FoodPlanServices;
import services.HotelServices;
import services.LodgingServices;
import services.RoomServices;
import services.SeasonServices;
import services.ServicesLocator;
import utils.LodgingTableModel;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;

public class ConsultarHospedajes extends MiJPanel{
	
	private HotelServices hotelServices = ServicesLocator.getHotelServices();
	private RoomServices roomServices = ServicesLocator.getRoomServices();
	private FoodPlanServices foodPlanServices = ServicesLocator.getFoodPlanServices();
	private SeasonServices seasonServices = ServicesLocator.getSeasonServices();
	private LodgingServices lodgingServices = ServicesLocator.getLodgingServices();
	
	private ArrayList<LodgingDTO> listaHospedajes;
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JButton btnCerrar;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JScrollPane scrollPane;
	private JTable table;
	private LodgingTableModel tableModel;
	
	private Principal padre;
	private MiJPanel anterior;
	private ConsultarHospedajes este;
	
	public ConsultarHospedajes(Principal p, MiJPanel a){
		este = this;
		padre = p;
		anterior = a;
		setTipoPanel(Paneles.PANEL_CONSULTAR_HOSPEDAJES);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelConsultarHospedajes(este);
		setBounds(pantalla.width/2-401, pantalla.height/2-276, 802, 502);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 800, 30);
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
		btnCerrar.setBounds(755, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);
		
		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 800, 470);
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
		
		JLabel lblAct = new JLabel("Hospedajes");
		lblAct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAct.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAct.setForeground(Color.black);
		lblAct.setFont(new Font("Arial", Font.BOLD, 22));
		lblAct.setBounds(300, 0, 200, 50);
		panelInferior.add(lblAct);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pos = table.getSelectedRow();
				int code = listaHospedajes.get(pos).getLodgingCode();
				if(anterior instanceof AgregarContrato)
					((AgregarContrato)anterior).setEntidad(code);
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().add(anterior);
				padre.getPanelPrincipal().repaint();
				padre.setPanelAbierto(anterior.getTipoPanel());
			}
		});
		table.setFocusable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(40);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.white);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBounds(30, 50, 740, 390);
		panelInferior.add(scrollPane);
		
		tableModel = new LodgingTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);
		DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
		Alinear.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(5).setCellRenderer(Alinear);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(85);
		table.getColumnModel().getColumn(5).setResizable(false);
		try{
			listaHospedajes = lodgingServices.selectAllLodgings();
			for(LodgingDTO l : listaHospedajes){
				HotelDTO ho = hotelServices.findHotel(l.getHotelCode());
				RoomDTO r = roomServices.findRoom(l.getRoomCode());
				FoodPlanDTO f = foodPlanServices.findFoodPlan(r.getFoodPlanCode());
				SeasonDTO s = seasonServices.findSeason(l.getSeasonCode());
				String[] datos = {String.valueOf(l.getLodgingCode()), ho.getHotelName(), r.getRoomType(),
						f.getTypeOfFoodPlan(), s.getSeasonName(), String.valueOf(l.getLodgingPrice())};
				tableModel.addRow(datos);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
