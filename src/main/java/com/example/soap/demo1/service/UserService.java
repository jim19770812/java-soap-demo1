package com.example.soap.demo1.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://service.springbootcxfdemo.demo.com")
public interface UserService {
    /**
     * 对外暴露的webservice接口
     * operationName：在wsdl文档中显示的方法名
     * action：是对应的服务完整url，如果不指定，使用CXF客户端是可以正常调用的，但别的客户端会报错 The given SOAPAction http://127.0.0.1:8080/soap/user does not match an operation.
     * @param name
     * @return
     */
    //@WebMethod(operationName = "hello") //这样定义会报错 The given SOAPAction http://127.0.0.1:8080/soap/user does not match an operation.
    @WebMethod(operationName = "hello", action = "http://127.0.0.1:8080/soap/user") //这样定义ok
    String hi(final String name);
}
