package com.ciberedu.proyectolpii.service.impl;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciberedu.proyectolpii.service.ReporteService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private DataSource dataSource;

    @Override
    public byte[] generarReporte(String nombreArchivo) throws Exception {
        Connection conn = dataSource.getConnection();
        InputStream stream = getClass().getResourceAsStream("/reportes/" + nombreArchivo);

        // En Azure no hay garantía de poder escribir en disco,
        // así que compilamos directo en memoria sin caché de archivos
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
