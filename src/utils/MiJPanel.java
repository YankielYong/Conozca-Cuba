package utils;

import javax.swing.JPanel;

public class MiJPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private int tipoPanel;
	
	public int getTipoPanel(){
		return tipoPanel;
	}
	
	protected void setTipoPanel(int tipo){
		this.tipoPanel = tipo;
	}

}
