package com.pm.cr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.cr.model.RegistrationModel;

@Repository
public interface RegistrationRespository extends JpaRepository<RegistrationModel, Integer> {

	Object findByEmail(String email);

	Object findByUsername(String username);

}
