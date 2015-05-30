package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.Role;
import com.rent.service.RoleService;

@Scope("prototype")
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

}
