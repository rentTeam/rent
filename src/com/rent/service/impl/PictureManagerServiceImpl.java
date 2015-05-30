package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.PictureManager;
import com.rent.service.PictureManagerService;

@Scope("prototype")
@Service
public class PictureManagerServiceImpl extends BaseServiceImpl<PictureManager> implements PictureManagerService{

}
