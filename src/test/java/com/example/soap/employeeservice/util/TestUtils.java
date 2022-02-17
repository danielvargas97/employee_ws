package com.example.soap.employeeservice.util;

import com.example.soap.employeeservice.common.model.controller.CreateEmployeeRequest;
import com.example.soap.employeeservice.common.model.controller.CreateEmployeeResponse;
import com.example.soap.employeeservice.common.model.controller.EmployeeRequest;
import com.example.soap.employeeservice.common.model.controller.EmployeeResponse;
import com.example.soap.employeeservice.common.model.dto.EmployeeDto;
import com.example.soap.employeeservice.common.util.EmployeeUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Util for tests
 *
 * @author Daniel
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

    /**
     * Create a mock {@linkplain CreateEmployeeRequest} object
     *
     * @return Create a mock {@linkplain CreateEmployeeRequest} object
     */
    public static CreateEmployeeRequest getCreateEmployeeRequest() {

        final var request = EmployeeRequest.builder()
                .birthDate(EmployeeUtil.extractXMLGregorianDateFromLocalDate(LocalDate.of(1997,12,27)))
                .jobStartDate(EmployeeUtil.extractXMLGregorianDateFromLocalDate(LocalDate.of(2021,03,01)))
                .salary(new BigDecimal(100.00))
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("123456789")
                .documentType("CC")
                .cargo("Software Engineer")
                .build();

        return CreateEmployeeRequest.builder()
                .request(request)
                .build();
    }

    /**
     * Create a mock {@linkplain EmployeeDto} object
     *
     * @return a mock {@linkplain EmployeeDto} object
     */
    public static EmployeeDto getEmployeeDto() {
        return EmployeeDto.builder()
                .birthDate(LocalDate.of(1997,12,27))
                .jobStartDate(LocalDate.of(2021,03,01))
                .id(UUID.randomUUID().toString())
                .salary(100.00)
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("123456789")
                .documentType("CC")
                .cargo("Software Engineer")
                .build();
    }

    /**
     * Create a mock {@linkplain CreateEmployeeResponse} object
     *
     * @param status status
     * @param errorResponseMessage error response message
     * @return a mock {@linkplain CreateEmployeeResponse} object
     */
    public static CreateEmployeeResponse getCreateEmployeeResponse(final String status, final String errorResponseMessage) {
        return CreateEmployeeResponse.builder()
                .status(status)
                .errorResponseMessage(errorResponseMessage)
                .response(createEmployeeResponse())
                .build();
    }

    /**
     * Create a mock {@linkplain CreateEmployeeResponse} object for fail scenarios.
     *
     * @param status status
     * @param errorResponseMessage error response message
     * @return a mock {@linkplain CreateEmployeeResponse} object
     */
    public static CreateEmployeeResponse getCreateFailedEmployeeResponse(final String status, final String errorResponseMessage) {
        return CreateEmployeeResponse.builder()
                .status(status)
                .errorResponseMessage(errorResponseMessage)
                .build();
    }

    /**
     * Create a mock {@linkplain EmployeeResponse} object.
     *
     * @return a mock {@linkplain EmployeeResponse} object.
     */
    private static EmployeeResponse createEmployeeResponse() {
        return EmployeeResponse.builder()
                .birthDate(EmployeeUtil.extractXMLGregorianDateFromLocalDate(LocalDate.of(1997,12,27)))
                .jobStartDate(EmployeeUtil.extractXMLGregorianDateFromLocalDate(LocalDate.of(2021,03,01)))
                .salary(new BigDecimal(100.00))
                .firstName("Alex")
                .lastName("Sanchez")
                .documentNumber("123456789")
                .documentType("CC")
                .cargo("Software Engineer")
                .build();
    }
}
