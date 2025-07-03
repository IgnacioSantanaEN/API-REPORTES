package com.Reporte.Repository;

import org.springframework.stereotype.Repository;
import com.Reporte.Model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer>{

}
