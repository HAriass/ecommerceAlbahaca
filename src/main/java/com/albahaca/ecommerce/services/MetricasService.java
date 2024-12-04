
package com.albahaca.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albahaca.ecommerce.models.MetricasModel;
import com.albahaca.ecommerce.repositories.MetricasRepository;

@Service
public class MetricasService {

    @Autowired
    private MetricasRepository metricasRepository;

    // Método para guardar una métrica
    public MetricasModel guardarMetrica(MetricasModel metrica) {
        return metricasRepository.save(metrica);
    }

}