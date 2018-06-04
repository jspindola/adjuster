package com.adjuster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adjuster.domain.Campaign;
import com.adjuster.repository.CampaignRepository;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {
	
	private CampaignRepository campaignRepository;
	
	@Autowired
	public CampaignController(CampaignRepository campaignRepository) {
		this.campaignRepository = campaignRepository;
	}
	
	@RequestMapping("/")
	public Iterable<Campaign> getCreativesList() {
		return campaignRepository.findAll();
	}

}
