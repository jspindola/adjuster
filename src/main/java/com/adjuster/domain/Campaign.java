package com.adjuster.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Each Campaign is linked to one or more Creative objects.
 * @author JRS
 *
 */
@Entity
public class Campaign {
	@Id
	private int id;
	@Column
	private String campaignName;
	@Column
	private int customerId;

	// Creatives
//	@OneToMany(mappedBy = "campaign")
//	private List<Creative> creatives;

	@SuppressWarnings("unused")
	private Campaign() {
		
	}
	
	public Campaign(int id, String name, int customer_id) {
		this.id = id;
		this.campaignName = name;
		this.customerId = customer_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", campaignName=" + campaignName + ", customerId=" + customerId + "]";
	}

}
