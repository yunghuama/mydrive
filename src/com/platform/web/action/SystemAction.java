package com.platform.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.service.UsersService;


@Controller
@Scope("prototype")
public class SystemAction extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	private String message;
	@Autowired
	private UsersService usersService;



}