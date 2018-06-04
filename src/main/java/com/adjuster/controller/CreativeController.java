package com.adjuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adjuster.domain.Creative;
import com.adjuster.service.CreativeService;

@RestController
@RequestMapping("/creatives")
public class CreativeController {
	
	private CreativeService creativeService;
	
	@Autowired
	public CreativeController(CreativeService creativeService) {
		this.creativeService = creativeService;
	}
	
	@RequestMapping("/")
	public Iterable<Creative> getCreativesList() {
		return creativeService.list();
	}

}
