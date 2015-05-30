package com.rent.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rent.domin.Comment;
import com.rent.service.CommentService;

@Scope("prototype")
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService{

}
