package com.example.soap.demo1.client;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Assert;

import javax.xml.namespace.QName;

/**
 * Soap客户端，提供了动态访问机制，并对返回结果做了封装
 */
public class SoapClient {
    private String wsdl;
    private String namespaceURI;
    private JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
    private Client client;

    public SoapClient(@NonNull String wsdl){
        this.client=this.dcf.createClient(wsdl);
    }

    /**
     * 设置名字空间
     * @param namespaceURI
     * @return
     */
    public SoapClient namespaceURI(@NonNull String namespaceURI){
        this.namespaceURI=namespaceURI;
        return this;
    }

    public SoapClient authorize(@NonNull String userName, @NonNull String password){
        HTTPConduit httpConduit=(HTTPConduit)this.client.getConduit();
        //设置超时时间
        HTTPClientPolicy policy = new HTTPClientPolicy();
        long timeout = 60 * 60 * 1000;
        policy.setConnectionTimeout(timeout);
        policy.setReceiveTimeout(timeout);
        httpConduit.setClient(policy);
        //配置认证
        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();

        authorizationPolicy.setUserName(userName);
        authorizationPolicy.setPassword(password);
        authorizationPolicy.setAuthorizationType("Basic");
        httpConduit.setAuthorization(authorizationPolicy);
        return this;
    }

    @SneakyThrows
    public SoapResult invoke(final String operationName, Object... params){
        Assert.isTrue(Strings.isNotBlank(this.namespaceURI), "未设置namespaceURI");
        QName serviceName=new QName(this.namespaceURI, operationName);
        Object[] objects = this.client.invoke(serviceName, params);
        return new SoapResult(objects);
    }
}
