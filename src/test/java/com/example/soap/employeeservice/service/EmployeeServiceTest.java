package com.example.soap.employeeservice.service;

import com.example.soap.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.example.soap.employeeservice.util.TestUtils.getCreateEmployeeRequest;
import static com.example.soap.employeeservice.util.TestUtils.getEmployeeDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for EmployeeService class
 *
 * @author Daniel
 */
class EmployeeServiceTest {

    /**
     * Tested class.
     */
    private EmployeeService employeeService;

    /**
     * Employee repository.
     */
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void init() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void shouldCreateAnEmployee() {
        when(employeeRepository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Optional.empty());
        when(employeeRepository.save(any())).thenReturn(getEmployeeDto());


        final var response = employeeService.createEmployee(getCreateEmployeeRequest());

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
    void shouldReturnAnEmployee_WhenAlreadyExists() {
        when(employeeRepository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Optional.of(getEmployeeDto()));

        final var response = employeeService.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("Error");
        assertThat(response.getErrorResponseMessage()).isNotNull().isEqualTo("The employee already exists");
        assertThat(response.getResponse().getFirstName()).isNotNull();
        assertThat(response.getResponse().getLastName()).isNotNull();
        assertThat(response.getResponse().getCargo()).isNotNull();
        assertThat(response.getResponse().getBirthDate()).isNotNull();
    }

    @Test
    void shouldThrowException_WhenQueryingEmployee() {
        when(employeeRepository.findByDocumentTypeAndDocumentNumber(any(), any())).thenThrow(new NullPointerException("Message"));

        final var response = employeeService.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorResponseMessage()).isNotNull().isEqualTo("Message");

    }

    @Test
    void shouldThrowException_WhenSavingEmployee() {
        when(employeeRepository.save(any())).thenThrow(new NullPointerException("Message"));

        final var response = employeeService.createEmployee(getCreateEmployeeRequest());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isNotNull().isEqualTo("Error");
        assertThat(response.getErrorResponseMessage()).isNotNull().isEqualTo("Message");

    }

}
