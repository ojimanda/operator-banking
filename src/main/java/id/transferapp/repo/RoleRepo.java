package id.transferapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import id.transferapp.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
