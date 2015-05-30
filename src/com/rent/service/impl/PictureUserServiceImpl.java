package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.PictureUser;
import com.rent.service.PictureUserService;

@Scope("prototype")
@Service
public class PictureUserServiceImpl extends BaseServiceImpl<PictureUser> implements PictureUserService{

}
