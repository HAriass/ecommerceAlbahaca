
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.MetricasModel;
import com.albahaca.ecommerce.repositories.MetricasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricasService {

    @Autowired
    private MetricasRepository metricasRepository;

    // Método para guardar una métrica
    public MetricasModel guardarMetrica(MetricasModel metrica) {
        return metricasRepository.save(metrica);
    }

}