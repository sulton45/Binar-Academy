package com.example.ProjectBinarFood.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import com.example.ProjectBinarFood.models.Item;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {

    public byte[] getItemReport(List<Item> items, String format) {

        JasperReport jasperReport;
        try {
            // Load JasperReport from classpath
            jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/jasper/item-report.jasper"));
        } catch (JRException e) {
            try {
                // Load and compile JasperReport from JRXML in classpath
                File file = ResourceUtils.getFile("classpath:jasper/item-report.jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, "item-report.jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("total", "Rp. 50.000.000,-");
        JasperPrint jasperPrint = null;
        byte[] reportContent;

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            switch (format) {
                case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
                case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }
}
