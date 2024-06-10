package com.project.shopapp.Repositories;

import com.project.shopapp.models.Order;
import com.project.shopapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
