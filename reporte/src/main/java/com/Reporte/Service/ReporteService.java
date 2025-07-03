package com.Reporte.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reporte.Model.Reporte;
import com.Reporte.Repository.ReporteRepository;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> getAllReportes(){
        return reporteRepository.findAll();
    }

    public Reporte getReporteById(Integer id){
        return reporteRepository.findById(id).orElse(null);
    }

    public Reporte saveReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Reporte updateReporte(Integer id, Reporte reporte) {
        Reporte existente = reporteRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setTipoReporte(reporte.getTipoReporte());
            existente.setFechaGeneracion(reporte.getFechaGeneracion());
            existente.setDescripcion(reporte.getDescripcion());
            existente.setJsonDatos(reporte.getJsonDatos());
            return reporteRepository.save(existente);
        }
        return null;
    }

    public boolean deleteReporte(Integer id) {
        if (reporteRepository.existsById(id)) {
            reporteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
