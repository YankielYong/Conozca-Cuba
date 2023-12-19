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

public class Reporte7 extends JPanel{

	private static final long serialVersionUID = 1L;

	private Reporte7 este;
	public Reporte7(){
		este = this;
		setBounds(0, 0, 800, 650);
		setLayout(new BorderLayout());
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("foto", getClass().getResourceAsStream("/visual/imagenes/logo cc.png"));
			param.put("ruta", "reports/Subreporte71.jasper");
			java.sql.Connection connection = ServicesLocator.getConnection();
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("reports/Reporte7.jasper");
			JasperPrint jp = JasperFillManager.fillReport(report, param, connection);
			JRViewer jr = new JRViewer(jp);
			este.add(jr);
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}
}
