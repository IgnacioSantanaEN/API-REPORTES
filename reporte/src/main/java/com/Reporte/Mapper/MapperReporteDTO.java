package com.Reporte.Mapper;

import com.Reporte.DTO.ReporteDTO;
import com.Reporte.Model.Reporte;

public class MapperReporteDTO {
    public static ReporteDTO toDTO(Reporte reporte){
        if (reporte == null) return null;

        ReporteDTO dto = new ReporteDTO(
            reporte.getIdReporte(),
            "http://localhost:8081/api/reportes/"+ reporte.getIdReporte()
        );

        return dto;

    }

    public static Reporte toModel(ReporteDTO dto) {
        if (dto == null) return null;

        Reporte reporte = new Reporte();
        reporte.setIdReporte(dto.getIdReporte());
        return reporte;
    }
}
