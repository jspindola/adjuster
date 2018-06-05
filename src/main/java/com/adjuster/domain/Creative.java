package com.adjuster.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The Creative object is a child of the Campaign object.
 * @author JRS
 *
 */
@Entity
public class Creative {
	@Id
	private int id;
	@Column
	private int clicks;
	@Column
	private int views;
	@Column
	private double cpm;

	@ManyToOne
	private Campaign campaign;
	
	private Creative() {
		
	}	

	public Creative(int id, int clicks, int views, double cpm) {
		super();
		this.id = id;
		this.clicks = clicks;
		this.views = views;
		this.cpm = cpm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public double getCpm() {
		return cpm;
	}

	public void setCpm(double cpm) {
		this.cpm = cpm;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	@Override
	public String toString() {
		return "Creative [id=" + id + ", clicks=" + clicks + ", views=" + views + ", cpm=" + cpm + "]";
	}


}
