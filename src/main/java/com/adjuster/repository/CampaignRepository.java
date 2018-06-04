package com.adjuster.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adjuster.domain.Campaign;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Integer> {

}
