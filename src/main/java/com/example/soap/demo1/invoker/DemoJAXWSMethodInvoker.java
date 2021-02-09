package com.example.soap.demo1.invoker;

import com.google.gson.Gson;
import org.apache.cxf.jaxws.JAXWSMethodInvoker;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.service.invoker.Factory;

import javax.xml.ws.spi.Invoker;
import java.lang.reflect.Method;
import java.util.List;

public class DemoJAXWSMethodInvoker extends JAXWSMethodInvoker {
    public DemoJAXWSMethodInvoker(Object bean) {
        super(bean);
    }

    public DemoJAXWSMethodInvoker(Factory factory) {
        super(factory);
    }

    public DemoJAXWSMethodInvoker(Invoker i) {
        super(i);
    }

    @Override
    protected Object invoke(Exchange exchange, Object serviceObject, Method m, List<Object> params) {
        String t=new Gson().toJson(params);
        System.out.println(this.getClass().getSimpleName()+":执行invoke，这个事件是在触发Service方法之前执行，参数："+t);
        return super.invoke(exchange, serviceObject, m, params);
    }
}
