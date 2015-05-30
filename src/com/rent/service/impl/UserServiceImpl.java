package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.User;
import com.rent.service.UserService;

@Scope("prototype")
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

}
