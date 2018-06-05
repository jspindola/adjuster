package com.adjuster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adjuster.domain.Creative;
import com.adjuster.repository.CampaignRepository;
import com.adjuster.repository.CreativeRepository;

@Service
public class CreativeService {
	private CreativeRepository creativeRepository;
	private CampaignRepository campaignRepository;

	@Autowired	
	public CreativeService(CreativeRepository creativeRepository, CampaignRepository campaignRepository) {
		this.creativeRepository = creativeRepository;
		this.campaignRepository = campaignRepository;
	}

	public Iterable<Creative> list() {
		return creativeRepository.findAll();
	}
	
	public Long total() {
		return creativeRepository.count();
	}
	
	public Iterable<Creative> getAllByCampaignId(int campaignID) {
		return creativeRepository.findAllByCampaignId(campaignID);
	}
	

}
