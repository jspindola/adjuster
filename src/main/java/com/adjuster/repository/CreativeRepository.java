package com.adjuster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adjuster.domain.Creative;

@Repository
public interface CreativeRepository extends CrudRepository<Creative, Integer> {
	
	List<Creative> findAllByCampaignId(int campaignId);
//	List<Creative> findAllCreativesByCustomerId(@Param("customerid") int customerId);

}
