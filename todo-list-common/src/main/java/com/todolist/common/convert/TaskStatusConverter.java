package com.todolist.common.convert;

import com.todolist.common.domain.enums.TaskStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {

    @Override
    public String convertToDatabaseColumn(TaskStatus attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getCode();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        return TaskStatus.findStatus(dbData);
    }
}
