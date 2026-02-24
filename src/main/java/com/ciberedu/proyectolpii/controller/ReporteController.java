package com.ciberedu.proyectolpii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ciberedu.proyectolpii.service.ReporteService;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // ── Vista: Ingresos por Mes ─────────────────────────────────
    @GetMapping("/ingresos")
    public String vistaIngresos(Model model) {
        return "reporte/ingresos";
    }

    // ── PDF: Ingresos por Mes ───────────────────────────────────
    @GetMapping("/ingresos/pdf")
    @ResponseBody
    public ResponseEntity<byte[]> pdfIngresos() throws Exception {
        byte[] pdf = reporteService.generarReporte("Reporte_Ingresos_Mes.jrxml");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=Reporte_Ingresos_Mes.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // ── Vista: Planes Más Vendidos ──────────────────────────────
    @GetMapping("/planes")
    public String vistaPlanes(Model model) {
        return "reporte/planes";
    }

    // ── PDF: Planes Más Vendidos ────────────────────────────────
    @GetMapping("/planes/pdf")
    @ResponseBody
    public ResponseEntity<byte[]> pdfPlanes() throws Exception {
        byte[] pdf = reporteService.generarReporte("Reporte_Planes_Top.jrxml");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=Reporte_Planes_Top.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}