package com.Reporte.Model;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer idReporte;

    @Column(name = "tipo_reporte")
    private String tipoReporte;

    @Column(name = "fecha_generacion")
    private LocalDate fechaGeneracion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "json_datos")
    private String jsonDatos;
}
