package visual;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import services.ServicesLocator;

public class Reporte6 extends JPanel{

	private static final long serialVersionUID = 1L;

	private Reporte6 este;
	private String cad;
	private String prov;
	public Reporte6(String cadena, String provincia){
		este = this;
		cad = cadena;
		prov = provincia;
		setBounds(0, 0, 1160, 650);
		setLayout(new BorderLayout());
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("foto", getClass().getResourceAsStream("/visual/imagenes/logo cc.png"));
			java.sql.Connection connection = ServicesLocator.getConnection();
			JasperReport report = null;
			if(cad.equals("Todas") && prov.equals("Todas")){
				report = (JasperReport)JRLoader.loadObject(getClass().getResource("/reports/Reporte6v1.jasper"));
			}
			else if(!cad.equals("Todas") && prov.equals("Todas")){
				report = (JasperReport)JRLoader.loadObject(getClass().getResource("/reports/Reporte6v2.jasper"));
				param.put("cadena", cad);
			}
			else if(cad.equals("Todas") && !prov.equals("Todas")){
				report = (JasperReport)JRLoader.loadObject(getClass().getResource("/reports/Reporte6v3.jasper"));
				param.put("provincia", prov);
			}
			else if(!cad.equals("Todas") && !prov.equals("Todas")){
				report = (JasperReport)JRLoader.loadObject(getClass().getResource("/reports/Reporte6v4.jasper"));
				param.put("cadena", cad);
				param.put("provincia", prov);
			}
			JasperPrint jp = JasperFillManager.fillReport(report, param, connection);
			JRViewer jr = new JRViewer(jp);
			este.add(jr);
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}
}
