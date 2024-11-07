
package com.albahaca.ecommerce.controllers;


import com.albahaca.ecommerce.models.MetricasModel;
import com.albahaca.ecommerce.services.MetricasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metricas")
public class MetricasController {

    @Autowired
    private MetricasService metricasService;

    // Endpoint para recibir y guardar una métrica
    @PostMapping("/guardar")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public MetricasModel guardarMetrica(@RequestBody MetricasModel metrica) {
        return metricasService.guardarMetrica(metrica);
    }
}