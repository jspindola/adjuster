package com.adjuster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adjuster.domain.Creative;
import com.adjuster.repository.CampaignRepository;
import com.adjuster.repository.CreativeRepository;

@Service
public class CreativeServiceImpl implements CreativeService {
	
	private CampaignRepository campaignRepository;
	private CreativeRepository creativeRepository;
	
	@Autowired
	public CreativeServiceImpl(CampaignRepository campaignRepository, CreativeRepository creativeRepository) {
		this.campaignRepository = campaignRepository;
		this.creativeRepository = creativeRepository;
	}
	
	@Override
	public Iterable<Creative> list() {
		return creativeRepository.findAll();
	}

}
