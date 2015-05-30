package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.Car;
import com.rent.service.CarService;

@Scope("prototype")
@Service
public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService{

}
