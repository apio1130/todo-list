package com.todolist.common.convert;

import com.todolist.common.domain.enums.SubTaskStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SubTaskStatusConverter implements AttributeConverter<SubTaskStatus, String> {

    @Override
    public String convertToDatabaseColumn(SubTaskStatus attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getCode();
    }

    @Override
    public SubTaskStatus convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        return SubTaskStatus.findStatus(dbData);
    }
}
