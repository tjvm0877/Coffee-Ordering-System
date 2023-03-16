package com.hyun.CoffeOrderingSystem.util.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter
public class BigDecimalToBigIntegerAttributeConverter implements AttributeConverter<BigDecimal, Long> {

    @Override
    public Long convertToDatabaseColumn(BigDecimal attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.longValue();
    }

    @Override
    public BigDecimal convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        return BigDecimal.valueOf(dbData);
    }
}
