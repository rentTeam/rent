package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.Picture;
import com.rent.service.PictureService;

@Scope("prototype")
@Service
public class PictureServiceImpl extends BaseServiceImpl<Picture> implements PictureService{

}
