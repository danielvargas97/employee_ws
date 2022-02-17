package com.example.soap.employeeservice.endpoint;

import com.example.soap.employeeservice.common.model.controller.CreateEmployeeResponse;
import com.example.soap.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.soap.employeeservice.util.TestUtils.getCreateEmployeeRequest;
import static com.example.soap.employeeservice.util.TestUtils.getCreateEmployeeResponse;
import static com.example.soap.employeeservice.util.TestUtils.getCreateFailedEmployeeResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for EmployeeEndpoint class
 *
 * @author Daniel
 */
class EmployeeEndpointTest {

    /**
     * Tested class
     */
    private EmployeeEndpoint employeeEndpoint;

    /**
     * Employee Service
     */
    private EmployeeService employeeService;

    @BeforeEach
    public void init(){
        employeeService = mock(EmployeeService.class);
        employeeEndpoint = new EmployeeEndpointImpl(employeeService);
    }

    @Test
    void shouldCreateAnEmployeeSuccessfully(){
        when(employeeService.createEmployee(any())).thenReturn(getCreateEmployeeResponse("", ""));

        final var response = employeeEndpoint.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("");
        assertThat(response.getErrorResponseMessage()).isEqualTo("");
        assertThat(response.getResponse().getFirstName()).isNotNull();
        assertThat(response.getResponse().getLastName()).isNotNull();
        assertThat(response.getResponse().getCargo()).isNotNull();
        assertThat(response.getResponse().getBirthDate()).isNotNull();
        assertThat(response.getResponse().getSalary()).isPositive();
    }

    @Test
    void shouldReturnAnEmployee_WhenEmployeeAlreadyExists(){
        when(employeeService.createEmployee(any())).thenReturn(getCreateEmployeeResponse("Error", "The employee already exists"));

        final var response = employeeEndpoint.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("Error");
        assertThat(response.getErrorResponseMessage()).isNotNull().isEqualTo("The employee already exists");
        assertThat(response.getResponse().getFirstName()).isNotNull();
        assertThat(response.getResponse().getLastName()).isNotNull();
        assertThat(response.getResponse().getCargo()).isNotNull();
        assertThat(response.getResponse().getBirthDate()).isNotNull();
        assertThat(response.getResponse().getSalary()).isPositive();

    }


    @Test
    void shouldReturnResponseEmpty_WhenServiceFails(){
        when(employeeService.createEmployee(any())).thenReturn(getCreateFailedEmployeeResponse("Error", "The employee already exists"));

        final var response = employeeEndpoint.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("Error");
        assertThat(response.getErrorResponseMessage()).isNotNull().isEqualTo("The employee already exists");
        assertThat(response.getResponse()).isNull();

    }

}
