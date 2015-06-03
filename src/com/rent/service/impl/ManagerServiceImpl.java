package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.Manage;
import com.rent.service.ManagerService;

@Scope("prototype")
@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manage> implements ManagerService{

}
