package com.divyansh.flightreservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divyansh.flightreservation.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
