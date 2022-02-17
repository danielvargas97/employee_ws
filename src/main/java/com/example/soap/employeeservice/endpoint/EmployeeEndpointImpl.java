package com.example.soap.employeeservice.endpoint;

import com.example.soap.employeeservice.common.model.controller.CreateEmployeeRequest;
import com.example.soap.employeeservice.common.model.controller.CreateEmployeeResponse;
import com.example.soap.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Implementation of Employee WS endpoint.
 *
 * @author Daniel
 */
@RequiredArgsConstructor
@Endpoint
@Slf4j
public class EmployeeEndpointImpl implements EmployeeEndpoint {

    /**
     * Employee Namespace URI
     */
    private static final String EMPLOYEE_NAMESPACE_URI = "http://localhost:10002/employees";

    /**
     * Employee Service.
     */
    private final EmployeeService employeeService;


    /**
     * WS Endpoint for creating an employee
     *
     * @param request a {@linkplain CreateEmployeeRequest} object with the request.
     * @return a {@linkplain CreateEmployeeResponse} response object.
     */
    @PayloadRoot(namespace = EMPLOYEE_NAMESPACE_URI, localPart = "createEmployeeRequest")
    @ResponsePayload
    @Override
    public CreateEmployeeResponse createEmployee(@RequestPayload final CreateEmployeeRequest request) {

        log.info("Employee creation request with Type=[{}] and Document=[{}]", request.getRequest().getDocumentType(), request.getRequest().getDocumentNumber());

        return employeeService.createEmployee(request);
    }


}
