package com.kingsleyohas.allstay24backoffice.service;

import com.kingsleyohas.allstay24backoffice.entity.PropertyType;
import com.kingsleyohas.allstay24backoffice.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;

    @Autowired
    public PropertyTypeService(PropertyTypeRepository propertyTypeRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
    }

    public PropertyType addPropertyType(String typeName) {
        PropertyType propertyType = new PropertyType(typeName);
        return propertyTypeRepository.save(propertyType);
    }
}