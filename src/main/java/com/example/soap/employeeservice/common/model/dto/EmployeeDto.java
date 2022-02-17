package com.example.soap.employeeservice.common.model.dto;

import com.example.soap.employeeservice.common.model.controller.LocalDatePersistenceConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;


/**
 * Employee data object
 *
 * @author Daniel
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    /**
     * Id
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * First name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Employee document type.
     */
    @Column(name = "document_type")
    private String documentType;

    /**
     * Employee document number.
     */
    @Column(name = "document_number")
    private String documentNumber;

    /**
     * Employee birth date.
     */
    @Column(name = "birth_date")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate birthDate;

    /**
     * Employee job start date.
     */
    @Column(name = "job_start_date")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate jobStartDate;

    /**
     * Employee job title.
     */
    @Column(name = "job_title")
    private String cargo;

    /**
     * Employee salary.
     */
    @Column
    private double salary;

}
