package com.example.soap.employeeservice.common.model.controller;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Converter to transform LocalDate to sql.Date
 *
 * @author Daniel Vargas
 */
@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements
        AttributeConverter<LocalDate, Date> {

    /**
     * Converts the LocalDate object to sql.Date object.
     *
     * @param entityValue the request {@linkplain LocalDate} object.
     * @return a {@linkplain Date} object.
     */
    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
        return java.sql.Date.valueOf(entityValue);
    }

    /**
     * Converts the sql.Date object to LocalDate object
     *
     * @param databaseValue the request {@linkplain Date} object.
     * @return a {@linkplain LocalDate} object.
     */
    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
        return databaseValue.toLocalDate();
    }
}