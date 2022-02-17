package com.example.soap.employeeservice.service;

import com.example.soap.employeeservice.common.model.controller.CreateEmployeeRequest;
import com.example.soap.employeeservice.common.model.controller.CreateEmployeeResponse;

/**
 * Interface to define the employee services
 *
 * @author Daniel
 */
public interface EmployeeService {

    /**
     * Create an employee.
     *
     * @param request a {@linkplain CreateEmployeeRequest} request.
     * @return a {@linkplain CreateEmployeeResponse} with the result.
     */
    CreateEmployeeResponse createEmployee(CreateEmployeeRequest request);
}
