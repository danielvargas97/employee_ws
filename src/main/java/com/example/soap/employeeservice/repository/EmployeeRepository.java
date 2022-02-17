package com.example.soap.employeeservice.repository;

import com.example.soap.employeeservice.common.model.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Employee jpa repository.
 *
 * @author Daniel
 */
public interface EmployeeRepository extends JpaRepository<EmployeeDto, String> {

    /**
     * Find a {@linkplain EmployeeDto} by Document Type and Document Number.
     *
     * @param documentType   the Document type.
     * @param documentNumber the Document number.
     * @return a {@linkplain Optional<EmployeeDto>} object.
     */
    Optional<EmployeeDto> findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
