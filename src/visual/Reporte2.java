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

public class Reporte2 extends JPanel{

	private static final long serialVersionUID = 1L;

	private Reporte2 este;
	public Reporte2(){
		este = this;
		setBounds(0, 0, 1300, 650);
		setLayout(new BorderLayout());
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("foto", getClass().getResourceAsStream("/visual/imagenes/logo cc.png"));
			java.sql.Connection connection = ServicesLocator.getConnection();
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("reports/Reporte2.jasper");
			JasperPrint jp = JasperFillManager.fillReport(report, param, connection);
			JRViewer jr = new JRViewer(jp);
			este.add(jr);
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}
}
