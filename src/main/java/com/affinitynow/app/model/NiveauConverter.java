package com.affinitynow.app.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NiveauConverter implements AttributeConverter<Niveau, String> {

    @Override
    public String convertToDatabaseColumn(Niveau attribute) {
        return attribute.getNiveau();
    }

    @Override
    public Niveau convertToEntityAttribute(String dbData) {
        return Niveau.valueOf(dbData);
    }
    
}
