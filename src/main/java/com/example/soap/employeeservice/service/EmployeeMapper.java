package com.example.soap.employeeservice.service;

import com.example.soap.employeeservice.common.model.controller.CreateEmployeeResponse;
import com.example.soap.employeeservice.common.model.controller.EmployeeRequest;
import com.example.soap.employeeservice.common.model.controller.EmployeeResponse;
import com.example.soap.employeeservice.common.model.dto.EmployeeDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.soap.employeeservice.common.util.EmployeeUtil.extractXMLGregorianDateFromLocalDate;
import static com.example.soap.employeeservice.common.util.EmployeeUtil.extractDateFromXml;
import static com.example.soap.employeeservice.common.util.EmployeeUtil.getDateSince;

/**
 * Mapper for building Employee objects
 *
 * @author Daniel
 * @since 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper {


    /**
     * Create a {@linkplain EmployeeDto} object.
     *
     * @param request the {@linkplain EmployeeRequest} object with the request data.
     * @return a {@linkplain EmployeeDto} object.
     */
    public static EmployeeDto createEmployeeDto(final EmployeeRequest request) {


        return EmployeeDto.builder()
                .cargo(request.getCargo())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .jobStartDate(extractDateFromXml(request.getJobStartDate()))
                .birthDate(extractDateFromXml(request.getBirthDate()))
                .salary(request.getSalary().doubleValue())
                .build();
    }

    /**
     * Create a {@linkplain CreateEmployeeResponse} object with successful response.
     *
     * @param employeeDto a {@linkplain EmployeeDto} object with the employee data.
     * @param status a status.
     * @param errorMessage an message.
     * @return a {@linkplain CreateEmployeeResponse} object with successful response.
     */
    public static CreateEmployeeResponse createEmployeeResponse(final EmployeeDto employeeDto, final String status, final String errorMessage) {

        return CreateEmployeeResponse.builder()
                .errorResponseMessage(errorMessage)
                .status(status)
                .response(buildEmployeeResponse(employeeDto))
                .build();

    }


    /**
     * Create a {@linkplain CreateEmployeeResponse} object with already exist response.
     *
     * @param employeeDto a {@linkplain EmployeeDto} object with the employee data.
     * @param status a status.
     * @param errorMessage an message.
     * @return a {@linkplain CreateEmployeeResponse} object with successful response.
     */
    public static CreateEmployeeResponse createExistingEmployeeResponse(final EmployeeDto employeeDto, final String status, final String errorMessage) {

        return CreateEmployeeResponse.builder()
                .errorResponseMessage(errorMessage)
                .status(status)
                .response(buildExistingEmployee(employeeDto))
                .build();

    }

    /**
     * Create the {@linkplain EmployeeResponse} object for an existing employee.
     *
     * @param employeeDto a {@linkplain EmployeeDto} object with employee data.
     * @return a {@linkplain EmployeeResponse} object.
     */
    private static EmployeeResponse buildExistingEmployee(EmployeeDto employeeDto) {
        return EmployeeResponse.builder()
                .firstName("")
                .lastName("")
                .documentType(employeeDto.getDocumentType())
                .documentNumber(employeeDto.getDocumentNumber().replaceAll("\\w+", "*"))
                .cargo("")
                .birthDate(extractXMLGregorianDateFromLocalDate(LocalDate.now()))
                .jobStartDate(extractXMLGregorianDateFromLocalDate(LocalDate.now()))
                .age("")
                .dateSinceJobStart("")
                .salary(BigDecimal.ZERO)
                .build();
    }

    /**
     * Create a {@linkplain CreateEmployeeResponse} object with failed response.
     *
     * @param status a status.
     * @param errorMessage an message.
     * @return a {@linkplain CreateEmployeeResponse} object with successful response.
     */
    public static CreateEmployeeResponse createFailedEmployeeResponse(final String status, final String errorMessage) {

        return CreateEmployeeResponse.builder()
                .errorResponseMessage(errorMessage)
                .status(status)
                .build();

    }


    /**
     * Create the {@linkplain EmployeeResponse} object.
     *
     * @param employeeDto a {@linkplain EmployeeDto} object with employee data.
     * @return a {@linkplain EmployeeResponse} object.
     */
    private static EmployeeResponse buildEmployeeResponse(final EmployeeDto employeeDto) {
        return EmployeeResponse.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .documentType(employeeDto.getDocumentType())
                .documentNumber(employeeDto.getDocumentNumber())
                .cargo(employeeDto.getCargo())
                .birthDate(extractXMLGregorianDateFromLocalDate(employeeDto.getBirthDate()))
                .jobStartDate(extractXMLGregorianDateFromLocalDate(employeeDto.getJobStartDate()))
                .age(getDateSince(employeeDto.getBirthDate()))
                .dateSinceJobStart(getDateSince(employeeDto.getJobStartDate()))
                .salary(new BigDecimal(employeeDto.getSalary()))
                .build();
    }


}
