package com.example.soap.demo1;

import com.example.soap.demo1.service.UserService;
import lombok.SneakyThrows;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {
    //接口地址
    private String address="http://127.0.0.1:8080/soap/user?wsdl";
    private JaxWsProxyFactoryBean jaxWsProxyFactoryBean;
    @BeforeEach
    public void before(){
        //代理工厂
        this.jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        //设置代理地址
        jaxWsProxyFactoryBean.setAddress(address);
        //设置接口类型
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
    }

    @Test
    @SneakyThrows
    void h1Test() {
        //创建一个代理接口实现
        UserService us = (UserService) jaxWsProxyFactoryBean.create();
        //数据准备
        String name = "张飞";
        //调用代理接口的方法调用并返回结果
        String result = us.hi(name, "男");
        Assertions.assertNotNull(result);
        System.out.println("返回结果:" + result);
    }

    @Test
    public void h2Test(){
        //创建一个代理接口实现
        UserService us = (UserService) jaxWsProxyFactoryBean.create();
        String result=us.hi2(Lists.list("张飞", "关羽", "刘备"));
        Assertions.assertNotNull(result);
        System.out.println("返回结果:" + result);
    }
}
