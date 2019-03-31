package test.main;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import test.factory.CompanyFactory;


public class MyReportSample {

	public static void main(String[] args) {
		
		buildReport();
	}

	static void buildReport()
	{
		try {
			
			// compile sub report
			JasperCompileManager.compileReportToFile("reports/source/PersonSubReport.jrxml", "reports/compiled/PersonSubReport.jasper");
			
			JasperReport subreport = (JasperReport)JRLoader.loadObjectFromFile("reports/compiled/PersonSubReport.jasper");
			
			//Preparing parameters
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("PersonsSubReport", subreport);
						
			// compile master report
			JasperCompileManager.compileReportToFile("reports/source/CompanyReport.jrxml", "reports/compiled/CompanyReport.jasper");
		
			// fill the report
			JasperFillManager.fillReportToFile("reports/compiled/CompanyReport.jasper", parameters, new JRBeanCollectionDataSource(CompanyFactory.getCompanies()));
		
			// export to pdf
			JasperExportManager.exportReportToPdfFile("reports/compiled/CompanyReport.jrprint", "reports/exported/CompanyReport.pdf");
			
			// see filled report
			JasperViewer.viewReport("reports/compiled/CompanyReport.jrprint", false, true);
			
			System.out.println("success");
			
		} catch (JRException e) {
			System.err.println(e);
		}		
	}
	
}
