package com.pm.cr.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CryptoData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@OneToMany(mappedBy = "crypto")
//	private List<CoinInfo> data;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public List<CoinInfo> getData() {
//		return data;
//	}
//
//	public void setData(List<CoinInfo> data) {
//		this.data = data;
//	}

}