package com.example.soap.demo1.service;

import com.example.soap.demo1.dto.UserBean;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://service.springbootcxfdemo.demo.com", serviceName = "user", name = "u")
public interface UserService {
    /**
     * 对外暴露的webservice接口
     * operationName：在wsdl文档中显示的方法名
     * action：是对应的服务完整url，如果不指定，使用CXF客户端是可以正常调用的，但别的客户端会报错 The given SOAPAction http://127.0.0.1:8080/soap/user does not match an operation.
     * @param name
     * @return
     */
    //@WebMethod(operationName = "hello") //这样定义会报错 The given SOAPAction http://127.0.0.1:8080/soap/user does not match an operation.
    @WebMethod(operationName = "hello", action = "hello") //这样定义ok
    String hi(final String name, String gender);

    @WebMethod(operationName = "hello2", action = "hello2") //这样定义ok
    String hi2(final List<String> names);

    @WebMethod(operationName = "hello3", action = "hello3") //这样定义ok
    String hi3(final UserBean user);
}
