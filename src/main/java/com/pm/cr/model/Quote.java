package com.pm.cr.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public USD getUsd() {
		return USD;
	}

	public void setUsd(USD usd) {
		this.USD = usd;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usd_id")
	private USD USD;

}
