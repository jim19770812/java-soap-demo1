package com.example.soap.demo1;

import com.example.soap.demo1.client.SoapClient;
import com.example.soap.demo1.client.SoapResult;
import com.example.soap.demo1.service.UserService;
import com.example.soap.demo1.service.WebServiceClient;
import lombok.SneakyThrows;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.xml.namespace.QName;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@SpringBootTest
class Demo1ApplicationTests {
    //接口地址
    private String address="http://127.0.0.1:8080/soap/user?wsdl";
    public static class Proxy2 extends Proxy{
        public Proxy2(InvocationHandler h) {
            super(h);
        }
        public InvocationHandler getInvocationHandler(){
            return this.h;
        }
    }
    @BeforeEach
    public void before(){

    }

    @Test
    @SneakyThrows
    void h1Test() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();//代理工厂
        jaxWsProxyFactoryBean.setAddress(address);//设置代理地址
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);//设置接口类型
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
    @SneakyThrows
    void t1Test2(){
        String wsdlUrl="http://127.0.0.1:8080/soap/user?wsdl";
        String namespaceURI="http://service.demo1.soap.example.com/";
        String ret=this.soapInvoke(wsdlUrl, namespaceURI, "hello", "吕布", "男");
        Assertions.assertEquals("hello 吕布/男", ret);
    }

    @Test
    public void h2Test(){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();//代理工厂
        jaxWsProxyFactoryBean.setAddress(address);//设置代理地址
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);//设置接口类型
        //创建一个代理接口实现
        UserService us = (UserService) jaxWsProxyFactoryBean.create();
        String result=us.hi2(Lists.list("张飞", "关羽", "刘备"));
        Assertions.assertNotNull(result);
        System.out.println("返回结果:" + result);
    }

    // 动态调用webservice接口
    @SneakyThrows
    public String soapInvoke(String wsdUrl, String namespaceURI, String operationName, String... params){
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdUrl);
        // 需要密码的情况需要加上用户名和密码
        //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        // invoke("方法名",参数1,参数2,参数3....);
        QName serviceName=new QName(namespaceURI, operationName);
        //objects = client.invoke(operationName, params);
        objects = client.invoke(serviceName, params);
        return objects[0].toString();
    }

    @Test
    void testSoapClient1() {
        String wsdlUrl="http://127.0.0.1:8080/soap/user?wsdl";
        String namespaceURI="http://service.demo1.soap.example.com/";
        SoapResult sr=new SoapClient(wsdlUrl).namespaceURI(namespaceURI)
                .invoke("hello", "吕布", "男");
        Assertions.assertTrue(sr.firstObject().isPresent());
        System.out.println(sr.firstString().get());
        System.out.println(sr.firstObject().get());
        sr.stream().forEach(System.out::println);
    }
}
