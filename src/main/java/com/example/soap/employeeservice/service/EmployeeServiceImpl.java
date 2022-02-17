package com.example.soap.employeeservice.service;

import com.example.soap.employeeservice.common.model.controller.CreateEmployeeRequest;
import com.example.soap.employeeservice.common.model.controller.CreateEmployeeResponse;
import com.example.soap.employeeservice.common.model.controller.EmployeeRequest;
import com.example.soap.employeeservice.common.model.dto.EmployeeDto;
import com.example.soap.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.soap.employeeservice.service.EmployeeMapper.createEmployeeDto;
import static com.example.soap.employeeservice.service.EmployeeMapper.createEmployeeResponse;
import static com.example.soap.employeeservice.service.EmployeeMapper.createFailedEmployeeResponse;

/**
 * Implementation of Employee service
 *
 * @author Daniel
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Employee repository
     */
    private final EmployeeRepository employeeRepository;

    /**
     * Create an employee.
     *
     * @param request a {@linkplain CreateEmployeeRequest} request.
     * @return a {@linkplain CreateEmployeeResponse} with the result.
     */
    @Override
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest request) {

        log.info("Creating employee with Type=[{}], Number=[{}]", request.getRequest().getDocumentType(), request.getRequest().getDocumentNumber());
        final var response = getEmployee(request.getRequest());

        return response;
    }

    /**
     * Get a employee
     * In case of a new one, it saves the employee
     * In case of an already existing one, returns it.
     *
     * @param request a {@linkplain EmployeeRequest} with the employee data.
     * @return a {@linkplain CreateEmployeeResponse} with the employee result.
     */
    private CreateEmployeeResponse getEmployee(EmployeeRequest request) {

        final var requestDto = createEmployeeDto(request);


        try {
            if (!isEmployeeAlreadyExists(requestDto)) {
                return saveEmployee(requestDto);
            } else {
                final var errorMessage = String.format("Employee with Type=%s and Number=%s already exists", requestDto.getDocumentType(), requestDto.getDocumentNumber());
                log.warn(errorMessage);
                return createEmployeeResponse(requestDto, "Error", "The employee already exists");
            }
        } catch (Exception ex) {
            log.error("Error while querying employee data", ex);
            return createFailedEmployeeResponse("Error", ex.getMessage());
        }


    }

    /**
     * Validate if the employee already exists.
     *
     * @param requestDto a {@linkplain EmployeeDto} with the searched employee.
     * @return flag that determines if the employee already exists.
     */
    private boolean isEmployeeAlreadyExists(EmployeeDto requestDto) {
        final var response = employeeRepository.findByDocumentTypeAndDocumentNumber(requestDto.getDocumentType(), requestDto.getDocumentNumber());
        return response.isPresent();
    }

    /**
     * Save employee in the database
     *
     * @param requestDto a {@linkplain EmployeeDto} object with the data of the new employee.
     * @return a {@linkplain CreateEmployeeResponse} object with the response data.
     */
    private CreateEmployeeResponse saveEmployee(EmployeeDto requestDto) {

        try {
            final var response = employeeRepository.save(requestDto);
            log.info("Created employee with Id=[{}]", requestDto.getId());

            return createEmployeeResponse(requestDto, "", "");
        } catch (Exception ex) {
            log.error("Error while saving employee data", ex);
            return createFailedEmployeeResponse("Error", ex.getMessage());
        }

    }
}
