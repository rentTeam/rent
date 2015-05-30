package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.RentInfo;
import com.rent.service.RentInfoService;

@Scope("prototype")
@Service
public class RentInfoServiceImpl extends BaseServiceImpl<RentInfo> implements RentInfoService{

}
