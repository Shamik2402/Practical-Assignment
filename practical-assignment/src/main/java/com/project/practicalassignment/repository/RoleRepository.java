package com.project.practicalassignment.repository;

import com.project.practicalassignment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
