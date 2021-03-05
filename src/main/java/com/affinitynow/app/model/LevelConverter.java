package com.affinitynow.app.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LevelConverter implements AttributeConverter<Level, String> {

    @Override
    public String convertToDatabaseColumn(Level attribute) {
        return attribute.getLevel();
    }

    @Override
    public Level convertToEntityAttribute(String dbData) {
        return Level.valueOf(dbData);
    }
    
}
