package visual;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import services.ServicesLocator;

public class Reporte1 extends JPanel{

	private static final long serialVersionUID = 1L;

	private Reporte1 este;
	public Reporte1(){
		este = this;
		setBounds(0, 0, 1000, 570);
		setLayout(new BorderLayout());
		try {
			java.sql.Connection connection = ServicesLocator.getConnection();
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("reports/Reporte1.jasper");
			JasperPrint jp = JasperFillManager.fillReport(report, null, connection);
			JRViewer jr = new JRViewer(jp);
			este.add(jr);
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}
}
