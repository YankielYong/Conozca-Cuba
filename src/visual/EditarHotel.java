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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dto.HotelDTO;
import services.HotelServices;
import services.ServicesLocator;
import utils.MiJPanel;
import utils.MyButtonModel;
import utils.Paneles;
import utils.Validaciones;

public class EditarHotel extends MiJPanel{

	private HotelServices hotelServices = ServicesLocator.getHotelServices();
	private HotelDTO hotel;
	
	private static final long serialVersionUID = 1L;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private Color colorAzul = new Color(59, 165, 187);

	private JPanel panelSuperior;
	private JLabel lblNombre;
	private JButton btnCerrar;
	private JPanel panelInferior;
	private JButton btnAtras;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtFax;
	private JTextField txtCorreo;
	private JTextField txtCantHab;
	private JRadioButton mLunaMiel;
	private JRadioButton mEcoturismo;
	private JRadioButton mHistorico;
	private JRadioButton mGolf;
	private JRadioButton mVerano;
	private JRadioButton mNavidad;
	private JRadioButton mTodoIncluido;
	private JButton btnEditar;
	
	private Principal padre;
	private Gestion anterior;
	private EditarHotel este;
	
	public EditarHotel(Principal p, Gestion a, HotelDTO h){
		este = this;
		padre = p;
		anterior = a;
		hotel = h;
		setTipoPanel(Paneles.PANEL_EDITAR_HOTEL);
		padre.setPanelAbierto(getTipoPanel());
		padre.setPanelEditarHotel(este);
		setBounds(pantalla.width/2-201, pantalla.height/2-331, 402, 612);
		setBackground(Color.darkGray);
		setLayout(null);
		
		panelSuperior = new JPanel(null);
		panelSuperior.setBounds(1, 1, 400, 30);
		panelSuperior.setBackground(colorAzul);
		add(panelSuperior);
		
		lblNombre = new JLabel("Editar Hotel");
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
		btnCerrar.setBounds(355, 0, 45, 30);
		btnCerrar.setBackground(Color.red);
		btnCerrar.setFocusable(false);
		btnCerrar.setBorderPainted(false);
		btnCerrar.setContentAreaFilled(false);
		btnCerrar.setModel(new MyButtonModel());
		panelSuperior.add(btnCerrar);
		
		panelInferior = new JPanel(null);
		panelInferior.setBounds(1, 31, 400, 580);
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
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/hotel.png"));
		image = img.getImage().getScaledInstance(56, 70, Image.SCALE_SMOOTH);
		Icon iconHotel = new ImageIcon(image);
		
		JLabel fotoHotel = new JLabel(iconHotel);
		fotoHotel.setBounds(60, 20, 56, 70);
		panelInferior.add(fotoHotel);
		
		img = new ImageIcon(getClass().getResource("/visual/imagenes/logo cc.png"));
		image = img.getImage().getScaledInstance(196, 60, Image.SCALE_SMOOTH);
		Icon iconLogo = new ImageIcon(image);

		JLabel logo = new JLabel(iconLogo);
		logo.setBounds(150, 25, 196, 60);
		panelInferior.add(logo);
		
		JLabel nombre = new JLabel("Nombre: ");
		nombre.setBounds(50, 110, 70, 30);
		nombre.setForeground(Color.black);
		nombre.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(nombre);
		
		txtNombre = new JTextField(hotel.getHotelName());
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.letrasNumeros(e);
			}
		});
		txtNombre.setBounds(120, 110, 230, 30);
		txtNombre.setForeground(Color.black);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtNombre);
		
		JLabel telefono = new JLabel("Teléfono: ");
		telefono.setBounds(50, 160, 70, 30);
		telefono.setForeground(Color.black);
		telefono.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(telefono);
		
		txtTelefono = new JTextField(hotel.getHotelPhone());
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtTelefono.setBounds(123, 160, 227, 30);
		txtTelefono.setForeground(Color.black);
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTelefono.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtTelefono);
		
		JLabel fax = new JLabel("Fax: ");
		fax.setBounds(50, 210, 40, 30);
		fax.setForeground(Color.black);
		fax.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(fax);
		
		txtFax = new JTextField(hotel.getHotelFax());
		txtFax.setBounds(90, 210, 260, 30);
		txtFax.setForeground(Color.black);
		txtFax.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFax.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtFax);
		
		JLabel correo = new JLabel("Correo: ");
		correo.setBounds(50, 260, 60, 30);
		correo.setForeground(Color.black);
		correo.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(correo);
		
		txtCorreo = new JTextField(hotel.getHotelMail());
		txtCorreo.setBounds(110, 260, 240, 30);
		txtCorreo.setForeground(Color.black);
		txtCorreo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCorreo.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtCorreo);
		
		JLabel totalHab = new JLabel("Total de habitaciones: ");
		totalHab.setBounds(50, 310, 160, 30);
		totalHab.setForeground(Color.black);
		totalHab.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(totalHab);
		
		txtCantHab = new JTextField(String.valueOf(hotel.getNumberOfRooms()));
		txtCantHab.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Validaciones.soloNumeros(e);
			}
		});
		txtCantHab.setBounds(210, 310, 140, 30);
		txtCantHab.setForeground(Color.black);
		txtCantHab.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCantHab.setBorder(new MatteBorder(0, 0, 3, 0, colorAzul));
		panelInferior.add(txtCantHab);
		
		JLabel modalidades = new JLabel("Modalidades: ");
		modalidades.setBounds(50, 360, 100, 30);
		modalidades.setForeground(Color.black);
		modalidades.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(modalidades);
		
		mLunaMiel = new JRadioButton("Luna de Miel");
		mLunaMiel.setBounds(60, 390, 120, 30);
		mLunaMiel.setBackground(Color.white);
		mLunaMiel.setForeground(Color.black);
		mLunaMiel.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(mLunaMiel);
		
		mGolf = new JRadioButton("Golf");
		mGolf.setBounds(240, 390, 120, 30);
		mGolf.setBackground(Color.white);
		mGolf.setForeground(Color.black);
		mGolf.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(mGolf);
		
		mEcoturismo = new JRadioButton("Ecoturismo");
		mEcoturismo.setBounds(60, 420, 120, 30);
		mEcoturismo.setBackground(Color.white);
		mEcoturismo.setForeground(Color.black);
		mEcoturismo.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(mEcoturismo);
		
		mHistorico = new JRadioButton("Histórico");
		mHistorico.setBounds(240, 420, 120, 30);
		mHistorico.setBackground(Color.white);
		mHistorico.setForeground(Color.black);
		mHistorico.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(mHistorico);
		
		mVerano = new JRadioButton("Verano");
		mVerano.setBounds(60, 450, 120, 30);
		mVerano.setBackground(Color.white);
		mVerano.setForeground(Color.black);
		mVerano.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(mVerano);
		
		mNavidad = new JRadioButton("Navidad");
		mNavidad.setBounds(240, 450, 120, 30);
		mNavidad.setBackground(Color.white);
		mNavidad.setForeground(Color.black);
		mNavidad.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(mNavidad);
		
		mTodoIncluido = new JRadioButton("Todo Incluido");
		mTodoIncluido.setBounds(135, 480, 120, 30);
		mTodoIncluido.setBackground(Color.white);
		mTodoIncluido.setForeground(Color.black);
		mTodoIncluido.setFont(new Font("Arial", Font.PLAIN, 16));
		panelInferior.add(mTodoIncluido);
		
		btnEditar = new JButton("Editar Hotel");
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPanelPrincipal().remove(este);
				padre.getPanelPrincipal().repaint();
				try{
					int codigo = hotel.getHotelCode();
					String nombre = txtNombre.getText();
					int categoria = hotel.getHotelCategory();
					int cadenaHotelera = hotel.getHotelChainCode();
					int provincia = hotel.getProvinceCode();
					String direccion = hotel.getHotelAddress();
					String localizacion = hotel.getHotelLocation();
					String telefono = txtTelefono.getText();
					String fax = txtFax.getText();
					String correo = txtCorreo.getText();
					String cantP = String.valueOf(hotel.getNumberOfFloors());
					String cantH = txtCantHab.getText();
					String distC = String.valueOf(hotel.getNearbyCityDistance());
					String distA = String.valueOf(hotel.getAirportDistance());
					Validaciones.hotel(nombre, direccion, telefono, fax, correo, cantP, cantH, distC, distA);
					String modalidades = obtenerModalidades();
					int cantPisos = Integer.valueOf(cantP);
					int cantHabitaciones = Integer.valueOf(cantH);
					double distCiudad = Double.valueOf(distC);
					double distAereopuerto = Double.valueOf(distA);
					hotelServices.updateHotel(codigo, nombre, direccion, categoria, telefono, fax, correo, 
							localizacion, modalidades, cantHabitaciones, cantPisos, distCiudad, distAereopuerto, 
							cadenaHotelera, provincia);
					MensajeAviso ma = new MensajeAviso(null, padre, anterior, "El hotel fue editado con éxito", MensajeAviso.CORRECTO);
					ma.setVisible(true);
					anterior.ponerHotel();
				} catch(IllegalArgumentException | ClassNotFoundException | SQLException e1){
					MensajeAviso ma = new MensajeAviso(null, padre, este, e1.getMessage(), MensajeAviso.ERROR);
					if(e1.getMessage().equals("El campo de la cantidad de pisos del hotel está vacío"))
						ma.agrandar(60);
					if(e1.getMessage().equals("El campo de la cantidad de habitaciones del hotel está vacío"))
						ma.agrandar(120);
					if(e1.getMessage().equals("El campo de distancia a la ciudad más cercana está vacío"))
						ma.agrandar(100);
					if(e1.getMessage().equals("El campo de distancia al aereopuerto está vacío"))
						ma.agrandar(40);
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
		btnEditar.setBounds(50, 525, 300, 35);
		btnEditar.setModel(new MyButtonModel());
		btnEditar.setFont(new Font("Arial", Font.BOLD, 18));
		btnEditar.setBackground(colorAzul);
		btnEditar.setForeground(Color.black);
		btnEditar.setFocusable(false);
		btnEditar.setBorderPainted(false);
		panelInferior.add(btnEditar);
		
		marcarComboBox();
	}
	
	private String obtenerModalidades(){
		String mod = "";
		if(mLunaMiel.isSelected()){
			mod += "Luna de Miel";
			if(mGolf.isSelected()){
				mod += ", Golf";
				if(mEcoturismo.isSelected()){
					mod += ", Ecoturismo";
					if(mHistorico.isSelected()){
						mod += ", Histórico";
						if(mVerano.isSelected()){
							mod += ", Verano";
							if(mNavidad.isSelected()){
								mod += ", Navidad";
								if(mTodoIncluido.isSelected()){
									mod += ", Todo Incluido";
								}
							}
							else if(mTodoIncluido.isSelected()){
								mod += ", Todo Incluido";
							}
						}
						else if(mNavidad.isSelected()){
							mod += ", Navidad";
							if(mTodoIncluido.isSelected()){
								mod += ", Todo Incluido";
							}
						}
						else if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mVerano.isSelected()){
						mod += ", Verano";
						if(mNavidad.isSelected()){
							mod += ", Navidad";
							if(mTodoIncluido.isSelected()){
								mod += ", Todo Incluido";
							}
						}
						else if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mHistorico.isSelected()){
					mod += ", Histórico";
					if(mVerano.isSelected()){
						mod += ", Verano";
						if(mNavidad.isSelected()){
							mod += ", Navidad";
							if(mTodoIncluido.isSelected()){
								mod += ", Todo Incluido";
							}
						}
						else if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mVerano.isSelected()){
					mod += ", Verano";
					if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mEcoturismo.isSelected()){
				mod += ", Ecoturismo";
				if(mHistorico.isSelected()){
					mod += ", Histórico";
					if(mVerano.isSelected()){
						mod += ", Verano";
						if(mNavidad.isSelected()){
							mod += ", Navidad";
							if(mTodoIncluido.isSelected()){
								mod += ", Todo Incluido";
							}
						}
						else if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mVerano.isSelected()){
					mod += ", Verano";
					if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mHistorico.isSelected()){
				mod += ", Histórico";
				if(mVerano.isSelected()){
					mod += ", Verano";
					if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mVerano.isSelected()){
				mod += ", Verano";
				if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mNavidad.isSelected()){
				mod += ", Navidad";
				if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mTodoIncluido.isSelected()){
				mod += ", Todo Incluido";
			}
		}
		else if(mGolf.isSelected()){
			mod += "Golf";
			if(mEcoturismo.isSelected()){
				mod += ", Ecoturismo";
				if(mHistorico.isSelected()){
					mod += ", Histórico";
					if(mVerano.isSelected()){
						mod += ", Verano";
						if(mNavidad.isSelected()){
							mod += ", Navidad";
							if(mTodoIncluido.isSelected()){
								mod += ", Todo Incluido";
							}
						}
						else if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mVerano.isSelected()){
					mod += ", Verano";
					if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mHistorico.isSelected()){
				mod += ", Histórico";
				if(mVerano.isSelected()){
					mod += ", Verano";
					if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mVerano.isSelected()){
				mod += ", Verano";
				if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mNavidad.isSelected()){
				mod += ", Navidad";
				if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mTodoIncluido.isSelected()){
				mod += ", Todo Incluido";
			}
			
		}
		else if(mEcoturismo.isSelected()){
			mod += "Ecoturismo";
			if(mHistorico.isSelected()){
				mod += ", Histórico";
				if(mVerano.isSelected()){
					mod += ", Verano";
					if(mNavidad.isSelected()){
						mod += ", Navidad";
						if(mTodoIncluido.isSelected()){
							mod += ", Todo Incluido";
						}
					}
					else if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mVerano.isSelected()){
				mod += ", Verano";
				if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mNavidad.isSelected()){
				mod += ", Navidad";
				if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mTodoIncluido.isSelected()){
				mod += ", Todo Incluido";
			}
		}
		else if(mHistorico.isSelected()){
			mod += "Histórico";
			if(mVerano.isSelected()){
				mod += ", Verano";
				if(mNavidad.isSelected()){
					mod += ", Navidad";
					if(mTodoIncluido.isSelected()){
						mod += ", Todo Incluido";
					}
				}
				else if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mNavidad.isSelected()){
				mod += ", Navidad";
				if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mTodoIncluido.isSelected()){
				mod += ", Todo Incluido";
			}
		}
		else if(mVerano.isSelected()){
			mod += "Verano";
			if(mNavidad.isSelected()){
				mod += ", Navidad";
				if(mTodoIncluido.isSelected()){
					mod += ", Todo Incluido";
				}
			}
			else if(mTodoIncluido.isSelected()){
				mod += ", Todo Incluido";
			}
		}
		else if(mNavidad.isSelected()){
			mod += "Navidad";
			if(mTodoIncluido.isSelected()){
				mod += ", Todo Incluido";
			}
		}
		else if(mTodoIncluido.isSelected()){
			mod += "Todo Incluido";
		}
		else
			throw new IllegalArgumentException("No seleccion� ninguna modalidad");
		return mod;
	}
	
	private void marcarComboBox(){
		String mod = hotel.getHotelModality();
		if(mod.contains("Luna de Miel"))
			mLunaMiel.setSelected(true);
		if(mod.contains("Golf"))
			mGolf.setSelected(true);
		if(mod.contains("Ecoturismo"))
			mEcoturismo.setSelected(true);
		if(mod.contains("Histórico"))
			mHistorico.setSelected(true);
		if(mod.contains("Verano"))
			mVerano.setSelected(true);
		if(mod.contains("Navidad"))
			mNavidad.setSelected(true);
		if(mod.contains("Todo Incluido"))
			mTodoIncluido.setSelected(true);
	}
}
