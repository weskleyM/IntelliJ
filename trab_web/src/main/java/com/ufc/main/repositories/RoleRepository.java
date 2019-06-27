package com.ufc.main.repositories;

import com.ufc.main.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    public Role findByRoleUsuario(String roleUsuario);
}
