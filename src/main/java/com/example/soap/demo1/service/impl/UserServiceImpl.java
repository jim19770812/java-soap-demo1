package com.example.soap.demo1.service.impl;

import com.example.soap.demo1.dto.UserBean;
import com.example.soap.demo1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserServiceImpl implements UserService {
    @Override
    public String hi(String name, String gender) {
        System.out.println(this.getClass().getSimpleName()+":h1");
        return "hello "+ name+"/"+gender;
    }

    @Override
    public String hi2(List<String> names) {
        System.out.println(this.getClass().getSimpleName()+":h12");
        return "你好 "+names.parallelStream().collect(Collectors.joining(","));
    }

    @Override
    public String hi3(UserBean user) {
        System.out.println(this.getClass().getSimpleName()+":h13");
        return "你好 "+ (user!=null? user.getName()+"/"+user.getGender()+"/"+user.getAge():"empty");
    }
}
