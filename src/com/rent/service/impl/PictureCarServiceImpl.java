package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.PictureCar;
import com.rent.service.PictureCarService;

@Scope("prototype")
@Service
public class PictureCarServiceImpl extends BaseServiceImpl<PictureCar> implements PictureCarService{

}
