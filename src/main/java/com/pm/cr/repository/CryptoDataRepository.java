package com.pm.cr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.cr.model.Status;

public interface CryptoDataRepository extends JpaRepository<Status, Long> {

}
