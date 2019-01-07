package com.jurkiewicz.grzegorz.FlatApp2.repository;

import com.jurkiewicz.grzegorz.FlatApp2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  public  Role findByRole(String role);
}
