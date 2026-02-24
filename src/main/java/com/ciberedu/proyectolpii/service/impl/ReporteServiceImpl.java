package com.ciberedu.proyectolpii.service.impl;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

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
        // 1. Obtener conexión a la base de datos
        Connection conn = dataSource.getConnection();

        // 2. Leer el archivo .jrxml desde src/main/resources/reportes/
        InputStream stream = getClass().getResourceAsStream("/reportes/" + nombreArchivo);

        // 3. Compilar el .jrxml en memoria
        JasperReport jasperReport = JasperCompileManager.compileReport(stream);

        // 4. Llenar el reporte con datos de la BD
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        // 5. Exportar a PDF y retornar bytes
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
