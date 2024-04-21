package com.kingsleyohas.allstay24backoffice.controller;

import com.kingsleyohas.allstay24backoffice.entity.PropertyType;
import com.kingsleyohas.allstay24backoffice.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController

public class PropertyTypeController {

    @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    @GetMapping("/")
    public String index()
    {
        return "Hello mandams";
    }

    @GetMapping("/api/property-types")
    public ResponseEntity<List<PropertyType>> getAllPropertyTypes() {
        List<PropertyType> propertyTypes = propertyTypeRepository.findAll();
        return ResponseEntity.ok(propertyTypes);
    }
}
