package com.example.soap.demo1.service.impl;

import com.example.soap.demo1.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public String hi(String name) {
        return "hello "+ name;
    }
}
