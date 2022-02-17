package com.example.soap.employeeservice.endpoint.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration class for Web Service
 *
 * @author Daniel
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {


    /**
     * Creates the {@linkplain ServletRegistrationBean} bean.
     *
     * @param applicationContext a {@linkplain ApplicationContext} object.
     * @return a {@linkplain ServletRegistrationBean} bean.
     */
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     * Creates the WSDL 1.1 object for employees
     *
     * @param employeesSchema A {@linkplain XsdSchema} object.
     * @return a {@linkplain DefaultWsdl11Definition} object.
     */
    @Bean(name = "employees")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeesSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setSchema(employeesSchema);
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setPortTypeName("EmployeeService");
        defaultWsdl11Definition.setTargetNamespace("http://localhost:10002/employees");
        return defaultWsdl11Definition;
    }


    /**
     * XsdSchema for employees
     *
     * @return A {@linkplain XsdSchema} bean.
     */
    @Bean
    public XsdSchema employeesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("employees.xsd"));
    }

}
