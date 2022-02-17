package com.example.soap.employeeservice.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.Period;

/**
 * Employee utils class
 *
 * @author Daniel
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class EmployeeUtil {

    /**
     * years, months and days since date format
     */
    public static final String DATE_SINCE_FORMAT = "%d years, %d months, %d days";

    /**
     * Extract XMLGregorianDate from LocalDate
     *
     * @param localDate a {@linkplain LocalDate} object.
     * @return a {@linkplain XMLGregorianCalendar} date object.
     */
    public static XMLGregorianCalendar extractXMLGregorianDateFromLocalDate(final LocalDate localDate) {

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (Exception ex) {
            log.warn("Error during converting LocalDate to XMLGregorianCalendar, Message", ex);
            return null;
        }

    }

    /**
     * Extract date from {@linkplain XMLGregorianCalendar} object.
     *
     * @param xmlGregorianCalendar the requested {@linkplain XMLGregorianCalendar} date.
     * @return a {@linkplain LocalDate} object.
     */
    public static LocalDate extractDateFromXml(final XMLGregorianCalendar xmlGregorianCalendar) {
        return LocalDate.of(xmlGregorianCalendar.getYear(), xmlGregorianCalendar.getMonth(), xmlGregorianCalendar.getDay());
    }

    /**
     * Get years, months, days since a date.
     *
     * @param localDate the date.
     * @return A string with the years, months and days since the requested date.
     */
    public static String getDateSince(final LocalDate localDate) {
        final Period period = Period.between(localDate, LocalDate.now());

        return String.format(DATE_SINCE_FORMAT, period.getYears(), period.getMonths(), period.getDays());

    }
}
