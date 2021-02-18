package com.example.soap.demo1.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

@Component
public class DemoPhaseInterceptor extends AbstractPhaseInterceptor {
    public DemoPhaseInterceptor() {
        super(Phase.USER_LOGICAL);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        System.out.println(this.getClass().getSimpleName()+"（拦截器）："+message);
    }

}
