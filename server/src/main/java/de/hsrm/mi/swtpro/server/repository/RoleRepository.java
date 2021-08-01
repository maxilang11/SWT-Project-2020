package de.hsrm.mi.swtpro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hsrm.mi.swtpro.server.models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
}