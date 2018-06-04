package com.adjuster.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adjuster.domain.Creative;

@Repository
public interface CreativeRepository extends CrudRepository<Creative, Integer> {
	
	//List<Creative> findAllByCampaignId();

}
