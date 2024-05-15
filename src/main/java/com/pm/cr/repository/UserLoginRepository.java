package com.pm.cr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.cr.model.RegistrationModel;

@Repository
public interface UserLoginRepository extends JpaRepository<RegistrationModel, Integer> {

	RegistrationModel findByUsername(String username);

}
