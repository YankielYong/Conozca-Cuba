package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class PropiedadesComboBox extends BasicComboBoxUI{
	
	private Color color = new Color(59, 165, 187);
	private Rectangle bounds;
	
	public static ComboBoxUI createUI(JComponent com, Rectangle r){
		return new PropiedadesComboBox(r);
	}
	
	public PropiedadesComboBox(Rectangle r) {
		bounds = r;
	}
	
	@Override
	protected JButton createArrowButton(){
		ImageIcon img = new ImageIcon(PropiedadesComboBox.class.getResource("/visual/imagenes/flecha abajo 25.png"));
		Image image = img.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		Icon icon = new ImageIcon(image);
		
		JButton btn = new JButton();
		btn.setModel(new MyButtonModel());
		btn.setIcon(icon);
		btn.setFocusable(false);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setBounds(bounds.width-30, 3, 30, 24);
		
		return btn;
	}
	
	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
		
	}

	@Override
	protected ListCellRenderer<Object> createRenderer() {
		
		return new DefaultListCellRenderer(){

			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				list.setSelectionBackground(color);
				return this;
			}
		};
	}

}
