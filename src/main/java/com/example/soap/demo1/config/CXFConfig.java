package com.example.soap.demo1.config;

import com.example.soap.demo1.factorybean.DemoJaxWsServerFactoryBean;
import com.example.soap.demo1.interceptor.DemoPhaseInterceptor;
import com.example.soap.demo1.service.UserService;
import com.google.common.collect.Lists;
import org.apache.cxf.Bus;
import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class CXFConfig {
    @Resource
    private Bus bus;
    @Resource
    private UserService userService;

    @Bean
    public ServletRegistrationBean servletRegistration(){
        //wsdl地址：http://127.0.0.1:8080/soap/user?wsdl
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean
    public Endpoint userEndPoint(){
        EndpointImpl result=new EndpointImpl(this.bus, this.userService, new DemoJaxWsServerFactoryBean());
        //wsdl地址：http://127.0.0.1:8080/soap/user?wsdl
//        result.setInInterceptors(Lists.newArrayList(new DemoPhaseInterceptor()));
        //result.setOutInterceptors();
//        DemoJAXWSMethodInvoker invoker=new DemoJAXWSMethodInvoker();
//        result.setInvoker();
        result.setInInterceptors(Lists.newArrayList(new DemoPhaseInterceptor()));
        //result.setEndpointContext();
        //result.setBus();
        //JaxWsServerFactoryBean
//        result.setServiceFactory();
        result.publish("/user");
        return result;
    }
}
