package com.pm.cr.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Data {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BTC getBtc() {
		return btc;
	}

	public void setBtc(BTC btc) {
		this.btc = btc;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "btc_id")
	private BTC btc;

}
