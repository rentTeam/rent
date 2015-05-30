package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.Label;
import com.rent.service.LabelService;


@Scope("prototype")
@Service
public class LabelServiceImpl extends BaseServiceImpl<Label> implements LabelService{

}
