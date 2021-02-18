package com.example.soap.demo1.factorybean;

import com.example.soap.demo1.invoker.DemoJAXWSMethodInvoker;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.service.invoker.Invoker;
import org.apache.cxf.service.invoker.SingletonFactory;


public class DemoJaxWsServerFactoryBean extends JaxWsServerFactoryBean {
    @Override
    protected Invoker createInvoker() {
        if (getServiceBean() == null) {
            return new DemoJAXWSMethodInvoker(new SingletonFactory(getServiceClass()));
        }
        return new DemoJAXWSMethodInvoker(getServiceBean());
    }
}
