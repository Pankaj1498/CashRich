package com.pm.cr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.cr.model.USD;


public interface CoinDataRepository extends JpaRepository<USD, Long> {

}
