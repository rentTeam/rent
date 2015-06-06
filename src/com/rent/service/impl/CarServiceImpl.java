package com.rent.service.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.dao.impl.BaseDaoImpl;
import com.rent.domin.Car;
import com.rent.service.CarService;

@Scope("prototype")
@Service
public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService{
	
}
