package com.Reporte.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Reporte.DTO.ReporteDTO;
import com.Reporte.Mapper.MapperReporteDTO;
import com.Reporte.Model.Reporte;
import com.Reporte.Service.ReporteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/")
    public ResponseEntity<?> obtenerReportes() {
        List<Reporte> reportes = reporteService.getAllReportes();
        List<ReporteDTO> dtos = reportes.stream()
                                        .map(MapperReporteDTO::toDTO)
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id){
        Reporte reporte = reporteService.getReporteById(id);
        if (reporte != null) {
            return ResponseEntity.ok(reporte); 
        } else {
            return ResponseEntity.status(404).body(new Mensaje("Reporte no encontrado"));
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<?> crearReporte(@RequestBody Reporte nuevoReporte) {
        try {
            Reporte guardado = reporteService.saveReporte(nuevoReporte);
            return ResponseEntity.status(201).body(guardado); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje("Error al crear el reporte"));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReporte(@PathVariable Integer id, @RequestBody Reporte nuevoReporte) {
        Reporte existente = reporteService.getReporteById(id);
        if (existente == null) {
            return ResponseEntity.status(404).body(new Mensaje("Reporte no encontrado"));
        }

        existente.setTipoReporte(nuevoReporte.getTipoReporte());
        existente.setFechaGeneracion(nuevoReporte.getFechaGeneracion());
        existente.setDescripcion(nuevoReporte.getDescripcion());
        existente.setJsonDatos(nuevoReporte.getJsonDatos());

        Reporte actualizado = reporteService.saveReporte(existente);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReporte(@PathVariable Integer id) {
        Reporte reporte = reporteService.getReporteById(id);
        if (reporte == null) {
            return ResponseEntity.status(404).body(new Mensaje("Reporte no encontrado"));
        }

        reporteService.deleteReporte(id);
        return ResponseEntity.ok(new Mensaje("Reporte eliminado correctamente"));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mensaje {
        private String mensaje;
    }
}
