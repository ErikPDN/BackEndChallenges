package dev.erik.twitter_simplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.erik.twitter_simplificado.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
