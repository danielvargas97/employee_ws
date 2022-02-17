package com.example.soap.employeeservice.endpoint;

import com.example.soap.employeeservice.common.model.controller.CreateEmployeeRequest;
import com.example.soap.employeeservice.common.model.controller.CreateEmployeeResponse;

/**
 * Interface to define the method on the Employee Endpoint
 */
public interface EmployeeEndpoint {

    /**
     * WS Endpoint for creating an employee
     *
     * @param request a {@linkplain CreateEmployeeRequest} object with the request.
     * @return a {@linkplain CreateEmployeeResponse} response object.
     */
    CreateEmployeeResponse createEmployee(CreateEmployeeRequest request);
}
